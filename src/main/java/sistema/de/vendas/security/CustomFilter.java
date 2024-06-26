package sistema.de.vendas.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sistema.de.vendas.database.UserService;

@Service
public class CustomFilter extends OncePerRequestFilter{
    
    private static final int BASIC_LENGTH = 6;

    @Autowired
    private UserService userService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        var headerAuthorization = request.getHeader("Authorization");

        if(headerAuthorization == null || !headerAuthorization.startsWith("Basic ")){
            filterChain.doFilter(request, response);
            return;
        }

        var basicToken = headerAuthorization.substring(BASIC_LENGTH);
        byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);

        String basicTokenValue = new String(basicTokenDecoded);

        String[] basicAuthSplit = basicTokenValue.split(":");

        if(userService.getUserByNameAndPassword(basicAuthSplit[0],basicAuthSplit[1]) != null){
            var authToken = new UsernamePasswordAuthenticationToken(basicAuthSplit[0], null, null);
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        filterChain.doFilter(request, response);
    }
}

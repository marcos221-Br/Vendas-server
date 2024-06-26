package sistema.de.vendas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http, CustomFilter customFilter) throws Exception{
        http.authorizeHttpRequests(authorizeConfig -> {
            authorizeConfig.anyRequest().authenticated();
        }).sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).addFilterBefore(customFilter, BasicAuthenticationFilter.class).csrf(csrf -> csrf.disable());
        return http.build();
    }
}

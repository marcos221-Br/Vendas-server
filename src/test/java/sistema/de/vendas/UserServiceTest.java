package sistema.de.vendas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import sistema.de.vendas.database.UserRepository;
import sistema.de.vendas.models.User;

@SuppressWarnings("null")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class UserServiceTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UserRepository repository;

    User user;
    HttpHeaders headers;

    @BeforeAll
    public void start(){
        this.user = new User("Marcos","Marcos123","administrator");
        this.headers = new HttpHeaders();
        headers.add("Authorization", "Basic VXNlclRlc3RlOjEyMzQ1Ng==");
    }

    @Test
    public void createUserTest(){
        HttpEntity<User> httpEntity = new HttpEntity<>(this.user, headers);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/user", HttpMethod.POST, httpEntity, User.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void findAllUsersTest(){
        HttpEntity<User> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<User[]> response = this.testRestTemplate.exchange("/api/user", HttpMethod.GET, httpEntity, User[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findUserByIdTest(){
        User user = this.repository.save(this.user);

        HttpEntity<User> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/user/" + user.getId(), HttpMethod.GET, httpEntity, User.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void alterUserTest(){
        User user = this.repository.save(this.user);

        User userUpdate = user;
        userUpdate.setPassword("MarcosUpdate");

        HttpEntity<User> httpEntity = new HttpEntity<>(userUpdate, headers);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/user/" + user.getId(), HttpMethod.PUT, httpEntity, User.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getPassword(), "MarcosUpdate");
    }

    @Test
    public void deleteUserTest(){
        User user = this.repository.save(this.user);

        HttpEntity<User> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/user/" + user.getId(), HttpMethod.DELETE, httpEntity, User.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findUserByNameTest(){
        User user = this.repository.save(this.user);

        HttpEntity<User> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/user/" + user.getName(), HttpMethod.GET, httpEntity, User.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }
}

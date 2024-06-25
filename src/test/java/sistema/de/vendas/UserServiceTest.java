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

    @BeforeAll
    public void start(){
        this.user = new User("Marcos","Marcos123","administrator");
    }

    @Test
    public void createUserTest(){
        User user = new User("Marcos","Marcos123","administrator");

        HttpEntity<User> httpEntity = new HttpEntity<>(user);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/users", HttpMethod.POST, httpEntity,
                                                                        User.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void findAllUsersTest(){
        ResponseEntity<User[]> response = this.testRestTemplate.exchange("/api/users", HttpMethod.GET, null, User[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findUserByIdTest(){
        User user = this.repository.save(this.user);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/users/" + user.getId(), HttpMethod.GET, null, User.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void alterUser(){
        User user = this.repository.save(this.user);

        User userUpdate = user;
        userUpdate.setPassword("MarcosUpdate");

        HttpEntity<User> httpEntity = new HttpEntity<>(userUpdate);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/users/" + user.getId(), HttpMethod.PUT, httpEntity, User.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getPassword(), "MarcosUpdate");
    }

    @Test
    public void deleteUser(){
        User user = this.repository.save(this.user);

        ResponseEntity<User> response = this.testRestTemplate.exchange("/api/users/" + user.getId(), HttpMethod.DELETE, null, User.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}

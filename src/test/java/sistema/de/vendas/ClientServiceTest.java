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

import sistema.de.vendas.database.ClientRepository;
import sistema.de.vendas.models.Client;

@SuppressWarnings("null")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@SpringBootTest(webEnvironment =  WebEnvironment.RANDOM_PORT)
public class ClientServiceTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ClientRepository repository;

    Client client;
    HttpHeaders headers;

    @BeforeAll
    public void start(){
        this.client = new Client("Marcos","42998314622");
        this.headers = new HttpHeaders();
        headers.add("Authorization", "Basic VXNlclRlc3RlOjEyMzQ1Ng==");
    }

    @Test
    public void createClientTest(){
        this.client = new Client("Marcos","42998314622");

        HttpEntity<Client> httpEntity = new HttpEntity<>(client, headers);

        ResponseEntity<Client> response = this.testRestTemplate.exchange("/api/client", HttpMethod.POST, httpEntity, Client.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void findAllClientsTest(){
        HttpEntity<Client> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Client[]> response = this.testRestTemplate.exchange("/api/client", HttpMethod.GET, httpEntity, Client[].class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void findClientByIdTest(){
        Client client = this.repository.save(this.client);

        HttpEntity<Client> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Client> response = this.testRestTemplate.exchange("/api/client/" + client.getId(), HttpMethod.GET, httpEntity, Client.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "Marcos");
    }

    @Test
    public void alterClientTest(){
        Client client = this.repository.save(this.client);

        Client clientUpdate = client;
        clientUpdate.setName("MarcosUpdate");

        HttpEntity<Client> httpEntity = new HttpEntity<>(clientUpdate, headers);

        ResponseEntity<Client> response = this.testRestTemplate.exchange("/api/client/" + client.getId(), HttpMethod.PUT, httpEntity, Client.class);
        
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getName(), "MarcosUpdate");
    }

    @Test
    public void deleteClientTest(){
        Client client = this.repository.save(this.client);

        HttpEntity<Client> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Client> response = this.testRestTemplate.exchange("/api/client/" + client.getId(), HttpMethod.DELETE, httpEntity, Client.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}

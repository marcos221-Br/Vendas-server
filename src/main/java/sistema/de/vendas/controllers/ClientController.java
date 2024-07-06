package sistema.de.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import sistema.de.vendas.database.ClientService;
import sistema.de.vendas.models.Client;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return this.clientService.createClient(client);
    }
    
    @GetMapping
    public List<Client> getAllClients() {
        return this.clientService.getAllClients();
    }
    
    //@GetMapping("/{id}")
    public Client getClientById(@PathVariable Integer id) {
        return this.clientService.getClientById(id);
    }
    
    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Integer id, @RequestBody Client client) {
        return this.clientService.updateClient(id,client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id){
        this.clientService.deleteClient(id);
    }

    @GetMapping("/{cellphone}")
    public Client getClientByCellphone(@PathVariable String cellphone) {
        return this.clientService.getClientByCellphone(cellphone);
    }
}

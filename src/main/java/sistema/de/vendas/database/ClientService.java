package sistema.de.vendas.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.de.vendas.models.Client;
import sistema.de.vendas.models.Order;

@Service
public class ClientService{
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private OrderService orderService;

    public Client createClient(Client client){
        return this.clientRepository.save(client);
    }

    public List<Client> getAllClients(){
        return this.clientRepository.findAll();
    }

    public Client getClientById(Integer id){
        return this.clientRepository.findById(id).orElse(null);
    }

    public Client updateClient(Integer id, Client client){
        client.setId(id);
        return this.clientRepository.save(client);
    }

    public void deleteClient(Integer id){
        for (Order order : this.orderService.getOrdersByIdClient(id)) {
            this.orderService.deleteOrder(order.getId());
        }
        this.clientRepository.deleteById(id);
    }

    public Client getClientByCellphone(String cellphone){
        return this.clientRepository.findByCellphone(cellphone);
    }
}

package sistema.de.vendas.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.de.vendas.models.Order;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public Order createOrder(Order order){
        return this.orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }

    public Order getOrderById(Integer id){
        return this.orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Integer id, Order order){
        order.setId(id);
        return this.orderRepository.save(order);
    }

    public void deleteOrder(Integer id){
        this.orderRepository.deleteById(id);
    }

    public List<Order> getOrdersByIdClient(Integer idClient){
        return this.orderRepository.findOrderByIdClient(idClient);
    }
}

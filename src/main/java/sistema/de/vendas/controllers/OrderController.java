package sistema.de.vendas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.de.vendas.database.OrderService;
import sistema.de.vendas.models.Order;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order){
        return this.orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return this.orderService.getAllOrders();
    }

    //@GetMapping("/{id}")
    public Order getOrderById(@PathVariable Integer id){
        return this.orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Integer id, @RequestBody Order order){
        return this.orderService.updateOrder(id,order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Integer id){
        this.orderService.deleteOrder(id);
    }

    @GetMapping("/{idClient}")
    public List<Order> getOrdersByIdClient(@PathVariable Integer idClient){
        return this.orderService.getOrdersByIdClient(idClient);
    }
}

package sistema.de.vendas.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sistema.de.vendas.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
    
    @Query(value = "SELECT * FROM orders WHERE idclient=?",nativeQuery = true)
    public List<Order> findOrderByIdClient(Integer idclient);
}

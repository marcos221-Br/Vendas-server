package sistema.de.vendas.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sistema.de.vendas.models.Item;

public interface ItemRepository extends JpaRepository<Item, Integer>{

    @Query(value = "SELECT * FROM itens WHERE idorder=?",nativeQuery = true)
    public List<Item> findItensByOrder(Integer idorder);
}

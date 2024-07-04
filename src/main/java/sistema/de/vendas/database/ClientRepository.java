package sistema.de.vendas.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sistema.de.vendas.models.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{
    
    @Query(value = "SELECT * FROM clients WHERE cellphone=?",nativeQuery = true)
    public Client findByCellphone(String cellphone);
}

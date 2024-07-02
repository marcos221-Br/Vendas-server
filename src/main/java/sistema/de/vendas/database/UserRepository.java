package sistema.de.vendas.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sistema.de.vendas.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
    @Query(value = "SELECT * FROM users WHERE name=? AND password=?",nativeQuery = true)
    public User findByNameAndPassword(String name, String password);

    @Query(value = "SELECT * FROM users WHERE name=?",nativeQuery = true)
    public User findByName(String name);
}

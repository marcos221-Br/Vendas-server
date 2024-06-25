package sistema.de.vendas.database;

import org.springframework.data.jpa.repository.JpaRepository;

import sistema.de.vendas.models.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    
}

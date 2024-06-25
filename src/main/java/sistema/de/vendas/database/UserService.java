package sistema.de.vendas.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sistema.de.vendas.models.User;

@Service
public class UserService{
    
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Integer id){
        return this.userRepository.findById(id).orElse(null);
    }

    public User updatUser(Integer id, User user){
        user.setId(id);
        return this.userRepository.save(user);
    }

    public void deleteUser(Integer id){
        this.userRepository.deleteById(id);
    }
}

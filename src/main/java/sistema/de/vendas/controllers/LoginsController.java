package sistema.de.vendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sistema.de.vendas.database.UserService;
import sistema.de.vendas.models.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/login")
public class LoginsController {
    
    @Autowired
    private UserService userService;

    @PostMapping
    public User login(@RequestBody User user) {
        System.out.println(user);
        System.out.println(this.userService.getUserByNameAndPassword(user.getName(), user.getPassword()));
        return this.userService.getUserByNameAndPassword(user.getName(), user.getPassword());
    }
    
}

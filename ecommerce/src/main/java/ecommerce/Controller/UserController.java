package ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ecommerce.Model.User;
import ecommerce.Service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        System.out.println("LOGIN API HIT");
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        return service.login(user.getEmail(), user.getPassword());
    }
}
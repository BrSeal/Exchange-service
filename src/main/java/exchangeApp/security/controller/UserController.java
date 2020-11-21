package exchangeApp.security.controller;


import exchangeApp.security.entity.User;
import exchangeApp.security.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;

    @Autowired
    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String addUser(User user) {
        return "newUserForm";
    }

    @PostMapping("/save")
    public String saveEmployee(User user) {
       service.save(user);
        return "redirect:exchange";
    }
}

package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.service.ExchangeService;
import exchangeApp.security.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final ExchangeService exchangeService;
    private final UserService userService;

    public StatsController(ExchangeService service, UserService userService) {
        this.userService = userService;
        this.exchangeService = service;
    }

    @GetMapping("/get")
    public List<Exchange> getStatsOfLoggedUser(Principal principal) {
        int userId = userService.get(principal.getName()).getId();
        return exchangeService.findAllByUserId(userId);
    }

    @GetMapping("/user/{id}")
    public List<Exchange> getStatsOfLoggedUser(@PathVariable int id) {
        return exchangeService.findAllByUserId(id);
    }


    @GetMapping("/overall")
    public List<Exchange> getAll() {
        return exchangeService.getAll();
    }
}

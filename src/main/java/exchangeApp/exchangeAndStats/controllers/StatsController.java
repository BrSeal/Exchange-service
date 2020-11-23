package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.service.ExchangeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final ExchangeService exchangeService;

    public StatsController(ExchangeService service) {
        this.exchangeService = service;
    }

    @GetMapping("/user/{id}")
    public List<Exchange> getStatsOfLoggedUser(@PathVariable String username) {
        return exchangeService.findAllByUsername(username);
    }


    @GetMapping("/overall")
    public List<Exchange> getAll() {
        return exchangeService.getAll();
    }
}

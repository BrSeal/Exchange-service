package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExchangeRating;
import exchangeApp.exchangeAndStats.service.ExchangeRatingService;
import exchangeApp.exchangeAndStats.service.ExchangeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final ExchangeRatingService exchangeRatingService;
    private final ExchangeService exchangeService;

    public StatsController(ExchangeRatingService exchangeRatingService, ExchangeService exchangeService) {
        this.exchangeRatingService = exchangeRatingService;
        this.exchangeService = exchangeService;
    }

    @GetMapping("/user/{username}")
    public List<Exchange> getStatsOfLoggedUser(@PathVariable String username) {
        return exchangeService.findAllByUsername(username);
    }

    @GetMapping("/overall")
    public List<Exchange> getAll() {
        return exchangeService.getAll();
    }

    @GetMapping("/rating")
    public List<ExchangeRating> getConvertRating() {
        return exchangeRatingService.getRating();
    }
}

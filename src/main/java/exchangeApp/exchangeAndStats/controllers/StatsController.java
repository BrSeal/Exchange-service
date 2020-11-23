package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.Exchange;
import exchangeApp.exchangeAndStats.entity.ExchangeRating;
import exchangeApp.exchangeAndStats.service.ExchangeRatingService;
import exchangeApp.exchangeAndStats.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final ExchangeRatingService exchangeRatingService;

    public StatsController(StatsService service, ExchangeRatingService exchangeRatingService) {
        this.statsService = service;
        this.exchangeRatingService = exchangeRatingService;
    }

    @GetMapping("/user/{username}")
    public List<Exchange> getStatsOfLoggedUser(@PathVariable String username) {
        return statsService.findAllByUsername(username);
    }

    @GetMapping("/overall")
    public List<Exchange> getAll() {
        return statsService.getAll();
    }

    // Пользователи, запросившие конвертацию больше 10 000 $ за один запрос.

    //Пользователи, суммарный запрошенный объём которых больше 100 000 $.

    @GetMapping("/rating")
    public List<ExchangeRating> getConvertRating() {
        return exchangeRatingService.getRating();
    }
}

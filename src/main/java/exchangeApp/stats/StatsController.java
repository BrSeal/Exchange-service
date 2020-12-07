package exchangeApp.stats;

import exchangeApp.stats.DTO.ExchangeRatingDto;
import exchangeApp.stats.DTO.StatsRequest;
import exchangeApp.stats.DTO.StatsResponse;
import exchangeApp.stats.exchangeRating.ExchangeRatingService;
import exchangeApp.stats.exchangeRating.entity.ExchangeRating;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping
@AllArgsConstructor
public class StatsController {

    private final StatsService statsService;
    private final ExchangeRatingService ratingService;

    @RequestMapping(method = {GET, POST}, path = "/stats")
    public StatsResponse getStats(@RequestBody(required = false) StatsRequest statsRequest, Authentication auth) {
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return statsService.getStats(auth.getName(), statsRequest, authorities);
    }

    @GetMapping("/rating")
    public List<ExchangeRatingDto> getRating() {
        return ratingService.getRating();
    }
}

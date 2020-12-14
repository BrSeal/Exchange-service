package exchangeApp.stats;

import exchangeApp.exchange.DTO.ExchangeResultDTO;
import exchangeApp.stats.DTO.ExchangeRatingDto;
import exchangeApp.stats.DTO.StatsRequest;
import exchangeApp.stats.DTO.StatsResponse;
import exchangeApp.stats.exchangeRating.ExchangeRatingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation(
            value = "Shows statistics depending on user role",
            notes = "If user is not Admin, he would see all his exchanges.\n"+
                    "Admin can see his exchanges and can request some special info."
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "If user logged in", response = ExchangeResultDTO.class),
            @ApiResponse(code = 500,message = "If user is not logged in")
    })
    @RequestMapping(method = {GET, POST}, path = "/stats")
    public StatsResponse getStats(StatsRequest statsRequest, @ApiIgnore Authentication auth) {
        List<String> authorities = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return statsService.getStats(auth.getName(), statsRequest, authorities);
    }


    @ApiOperation(value = "Returns exchange ratings in descendant order")
    @ApiResponses({
            @ApiResponse(code = 200,message = "If user logged in", response = ExchangeResultDTO.class),
            @ApiResponse(code = 500,message = "If user is not logged in")
    })
    @GetMapping("/rating")
    public List<ExchangeRatingDto> getRating() {
        return ratingService.getRating();
    }
}

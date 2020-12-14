package exchangeApp.exchange;

import exchangeApp.exchange.DTO.ExchangeRequestDTO;
import exchangeApp.exchange.DTO.ExchangeResultDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RestController
@RequestMapping
@AllArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @ApiOperation(
            value = "Provides exchange operation",
            notes = "Available only for registered users."
    )
    @ApiResponses({
            @ApiResponse(code = 200,message = "Successfully exchanged", response = ExchangeResultDTO.class),
            @ApiResponse(code = 400,message = "On error (not found currency code, invalid data etc.)"),
            @ApiResponse(code = 500,message = "When user is unauthorized")
    })
    @PostMapping("/exchange")
    public ExchangeResultDTO doExchange(@RequestBody ExchangeRequestDTO dto, Principal principal) {
        return exchangeService.doExchange(dto.toExchange(),principal.getName());
    }

}

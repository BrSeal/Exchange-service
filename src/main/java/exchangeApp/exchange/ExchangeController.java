package exchangeApp.exchange;

import exchangeApp.exchange.DTO.ExchangeRequestDTO;
import exchangeApp.exchange.DTO.ExchangeResultDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
@AllArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    @PostMapping("/exchange")
    public ExchangeResultDTO doExchange(@RequestBody ExchangeRequestDTO dto, Principal principal) {
        return exchangeService.doExchange(dto.toExchange(),principal.getName());
    }

}

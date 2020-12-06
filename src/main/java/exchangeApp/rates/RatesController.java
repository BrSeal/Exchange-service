package exchangeApp.rates;

import exchangeApp.rates.DTO.CurrentRatesWithBaseDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RatesController {

    private final RatesService ratesService;

    @GetMapping("/rates")
    public CurrentRatesWithBaseDTO getRatesWithBase(@RequestParam(required = false)String base) {
        return ratesService.getActualRates(base);
    }
}

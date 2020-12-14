package exchangeApp.rates;

import exchangeApp.rates.DTO.CurrentRatesWithBaseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RatesController {

    private final RatesService ratesService;

    @GetMapping("/rates")
    @ApiOperation(value="Returns the base currency and a map {currency name}:{rate in that currency}",
            response = CurrentRatesWithBaseDTO.class)
    public CurrentRatesWithBaseDTO getRatesWithBase(
            @ApiParam(value = "3 letter shortcut of the national currency")
            @RequestParam(required = false)String base) {
        return ratesService.getActualRates(base);
    }
}

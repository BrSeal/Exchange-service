package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.DTO.CurrentRatesWithBaseDTO;
import exchangeApp.exchangeAndStats.service.RatesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatesController {

    private final RatesService ratesService;

    public RatesController(RatesService ratesService) {
        this.ratesService = ratesService;
    }


    @GetMapping("/rates")
    public CurrentRatesWithBaseDTO getRatesWithBase(@RequestParam(required = false)String base) {
        return ratesService.getActualRates(base);
    }
}

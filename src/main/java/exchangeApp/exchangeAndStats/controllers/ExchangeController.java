package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.service.ExchangeService;

import exchangeApp.exchangeAndStats.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final RatesService ratesService;

    @Autowired
    public ExchangeController(ExchangeService service, RatesService ratesService) {
        this.exchangeService = service;
        this.ratesService = ratesService;
    }

    @PostMapping("/rates")
    public Map<String, Double> getRatesWithBase(String base) {
        if(base==null) return ratesService.getActualRates();
        return ratesService.getActualRates(base);
    }

    @PostMapping("/doExchange")
    public ExchangeResultDTO doExchange(ExchangeRequestDTO dto) {
        return exchangeService.doExchange(dto.toExchange());
    }

}

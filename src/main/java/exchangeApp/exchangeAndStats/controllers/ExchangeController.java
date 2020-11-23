package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.service.ExchangeService;

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

    @Autowired
    public ExchangeController(ExchangeService service) {
        this.exchangeService = service;
    }

    @GetMapping("/rates")
    public Map<String, Double> getRatesWithBase(String base) {
        return exchangeService.requestRatesFromExternalAPI(base);
    }

    @PostMapping("/doExchange")
    public ExchangeResultDTO doExchange(ExchangeRequestDTO dto) {
        return exchangeService.doExchange(dto.toExchange());
    }

}

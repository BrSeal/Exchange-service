package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
public class ExchangeController {
    private final ExchangeService exchangeService;


    @Autowired
    public ExchangeController(ExchangeService service) {
        this.exchangeService = service;
    }

    @PostMapping("/exchange")
    public ExchangeResultDTO doExchange(@RequestBody ExchangeRequestDTO dto, Principal principal) {
        dto.setUsername(principal.getName());
        return exchangeService.doExchange(dto.toExchange());
    }

}

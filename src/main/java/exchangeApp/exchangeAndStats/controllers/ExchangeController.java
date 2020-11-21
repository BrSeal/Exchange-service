package exchangeApp.exchangeAndStats.controllers;

import exchangeApp.exchangeAndStats.entity.DTO.ExchangeRequestDTO;
import exchangeApp.exchangeAndStats.entity.DTO.ExchangeResultDTO;
import exchangeApp.exchangeAndStats.service.ExchangeService;
import exchangeApp.security.entity.User;
import exchangeApp.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final UserService userService;

    @Autowired
    public ExchangeController(ExchangeService service, UserService userService) {
        this.exchangeService = service;
        this.userService = userService;
    }

    @GetMapping("/rates")
    public Map<String, Double> getRatesWithBase(String type) {
        return exchangeService.requestRatesFromExternalAPI(type);
    }

    @PostMapping("/doExchange")
    public ExchangeResultDTO doExchange(ExchangeRequestDTO dto, Principal principal) {
        User user = userService.get(principal.getName());

        return exchangeService.doExchange(dto, user);
    }

}

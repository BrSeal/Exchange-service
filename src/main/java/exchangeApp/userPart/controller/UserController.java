package exchangeApp.userPart.controller;

import exchangeApp.userPart.DTO.UserDTO;
import exchangeApp.userPart.DTO.UserInfoDTO;
import exchangeApp.userPart.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/stats/atOnceMoreThan/{usd}")
    public List<UserInfoDTO> exchangedInTotalMoreThanUSD(@PathVariable double usd) {
        return service.getUsersChangedMoreUSDThan(usd);
    }

    @GetMapping("/stats/moreThan/{usd}")
    public List<UserInfoDTO> exchangedAtOnesMoreThan(@PathVariable double usd) {
        return service.getUsersTotalExchangedMoreThan(usd);
    }
}

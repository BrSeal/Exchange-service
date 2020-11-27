package exchangeApp.UserAndSecurity.controller;

import exchangeApp.UserAndSecurity.entity.DTO.NewUserDTO;
import exchangeApp.UserAndSecurity.entity.DTO.UserInfoDTO;
import exchangeApp.UserAndSecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/user/register")
    public String addUser(NewUserDTO dto){
       return service.add(dto);
    }
}

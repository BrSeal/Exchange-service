package exchangeApp.UserAndSecurity.controller;

import exchangeApp.UserAndSecurity.entity.DTO.NewUserDTO;
import exchangeApp.UserAndSecurity.entity.DTO.UserInfoDTO;
import exchangeApp.UserAndSecurity.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/user/new")
    public String addUser(NewUserDTO dto){
       return service.add(dto);
    }
}

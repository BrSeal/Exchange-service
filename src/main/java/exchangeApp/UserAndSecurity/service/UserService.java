package exchangeApp.UserAndSecurity.service;

import exchangeApp.UserAndSecurity.entity.DTO.NewUserDTO;
import exchangeApp.UserAndSecurity.entity.DTO.UserInfoDTO;

import java.util.List;

public interface UserService {
    List<UserInfoDTO> getUsersChangedMoreUSDThan(double amount);

    List<UserInfoDTO> getUsersTotalExchangedMoreThan(double amount);

    String add(NewUserDTO dto);
}

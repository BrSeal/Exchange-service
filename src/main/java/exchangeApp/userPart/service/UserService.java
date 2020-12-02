package exchangeApp.userPart.service;

import exchangeApp.userPart.DTO.UserDTO;
import exchangeApp.userPart.DTO.UserInfoDTO;

import java.util.List;

public interface UserService {
    List<UserInfoDTO> getUsersChangedMoreUSDThan(double amount);

    List<UserInfoDTO> getUsersTotalExchangedMoreThan(double amount);
}

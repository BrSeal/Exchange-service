package exchangeApp.userPart.service;

import exchangeApp.security.entity.Authority;
import exchangeApp.userPart.DTO.UserDTO;
import exchangeApp.userPart.DTO.UserInfoDTO;
import exchangeApp.security.entity.Role;
import exchangeApp.security.entity.User;
import exchangeApp.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final UserCheckProvider check;

    public UserServiceImpl(UserRepository repository, UserCheckProvider check) {
        this.repository = repository;
        this.check = check;
    }

    @Override
    public List<UserInfoDTO> getUsersChangedMoreUSDThan(double amount) {
        return repository.findUsersByMinExchangeAmount(amount)
                .stream().map(UserInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInfoDTO> getUsersTotalExchangedMoreThan(double amount) {
        return repository.findUsersByExchangeSum(amount)
                .stream().map(UserInfoDTO::new)
                .collect(Collectors.toList());
    }
}

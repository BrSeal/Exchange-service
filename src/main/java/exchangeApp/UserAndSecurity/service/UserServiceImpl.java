package exchangeApp.UserAndSecurity.service;

import exchangeApp.UserAndSecurity.entity.Authority;
import exchangeApp.UserAndSecurity.entity.DTO.NewUserDTO;
import exchangeApp.UserAndSecurity.entity.DTO.UserInfoDTO;
import exchangeApp.UserAndSecurity.entity.Role;
import exchangeApp.UserAndSecurity.entity.User;
import exchangeApp.UserAndSecurity.repository.UserRepository;
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
    public String add(NewUserDTO dto){
        check.validateNew(dto);
        User user=dto.toUser();
        user.setAuthorities(Set.of(new Authority(0,user, Role.ROLE_USER)));
        return repository.save(user).getUsername();
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

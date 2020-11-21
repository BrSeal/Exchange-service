package exchangeApp.security.service;

import exchangeApp.security.entity.Authority;
import exchangeApp.security.entity.User;
import exchangeApp.security.entity.UserRole;
import exchangeApp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserCheckProvider check;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserCheckProvider check, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.check = check;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User get(String username) {
        User user = userRepository.findByUsername(username);
        check.ifExists(user);
        return user;
    }

    @Override
    public void save(User user) {
        check.ifExists(user);

        List<User> users = (List<User>) userRepository.findAll();

        Set<Authority> authorities=new HashSet<>();
        authorities.add(new Authority(0,user, UserRole.ROLE_USER));

        user.setAuthorities(authorities);

        check.validateUser(user, users);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

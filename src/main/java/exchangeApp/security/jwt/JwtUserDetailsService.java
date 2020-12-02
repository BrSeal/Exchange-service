package exchangeApp.security.jwt;

import exchangeApp.security.entity.User;
import exchangeApp.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("JwtUserDetailsService")
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findById(s)
                .orElseThrow(() -> new IllegalArgumentException("User with username \"" + s + "\" not found!"));

        JwtUser jwtUser = JwtUserFactory.create(user);
        return jwtUser;
    }
}

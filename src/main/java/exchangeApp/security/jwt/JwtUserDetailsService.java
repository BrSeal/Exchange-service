package exchangeApp.security.jwt;

import exchangeApp.security.UserRepository;
import exchangeApp.security.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("JwtUserDetailsService")
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findById(s)
                .orElseThrow(() -> new IllegalArgumentException("User with username \"" + s + "\" not found!"));

        return JwtUserFactory.create(user);
    }
}

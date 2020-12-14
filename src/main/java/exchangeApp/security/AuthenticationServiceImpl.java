package exchangeApp.security;

import exchangeApp.security.DTO.AuthResponseDto;
import exchangeApp.security.DTO.SecurityUserDTO;
import exchangeApp.security.entity.Authority;
import exchangeApp.security.entity.User;
import exchangeApp.security.jwt.JwtAuthenticationException;
import exchangeApp.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Set;

import static exchangeApp.security.entity.Role.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String BAD_CREDENTIALS = "Invalid username or password";

    @Value("${db.admin.user}")
    private String ADMIN_NAME;
    @Value("${db.admin.pass}")
    private String ADMIN_PASS;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserCheckProvider checkProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository, UserCheckProvider checkProvider, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.checkProvider = checkProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initAdmin(){
        if (!userRepository.existsByUsername(ADMIN_NAME)){
            User admin=new User();
            admin.setUsername(ADMIN_NAME);
            admin.setPassword(passwordEncoder.encode(ADMIN_PASS));
            admin.setAuthorities(Set.of(
                    new Authority(0,admin, ROLE_ADMIN),
                    new Authority(0,admin, ROLE_USER)
            ));
            userRepository.save(admin);
        };
    }

    public AuthResponseDto getAuthResponseDto(SecurityUserDTO dto) {
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

            String token = jwtTokenProvider.createToken(username, user.getAuthorities());

            return new AuthResponseDto(username,token);
        } catch (JwtAuthenticationException ex) {
            throw new IllegalArgumentException(BAD_CREDENTIALS);
        }
    }

    @Override
    public String registerNewUser(SecurityUserDTO dto) {
        checkProvider.validateNew(dto);
        User user=dto.toUser();
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userRepository.save(user).getUsername();
    }
}

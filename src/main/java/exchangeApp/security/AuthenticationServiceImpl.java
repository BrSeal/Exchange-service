package exchangeApp.security;

import exchangeApp.security.DTO.SecurityUserDTO;
import exchangeApp.security.entity.User;
import exchangeApp.security.jwt.JwtAuthenticationException;
import exchangeApp.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final String BAD_CREDENTIALS = "Invalid username or password";

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserCheckProvider checkProvider;
    private final PasswordEncoder passwordEncoder;

    public Map<Object, Object> getAuthResponseDto(SecurityUserDTO dto) {
        try {
            String username = dto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, dto.getPassword()));
            User user = userRepository.findById(username).orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

            String token = jwtTokenProvider.createToken(username, user.getAuthorities());

            Map<Object, Object> resultMap = new HashMap<>();
            resultMap.put("username", username);
            resultMap.put("token", token);

            return resultMap;
        } catch (JwtAuthenticationException ex) {
            throw new BadCredentialsException(BAD_CREDENTIALS);
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

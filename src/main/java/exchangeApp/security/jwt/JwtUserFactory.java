package exchangeApp.security.jwt;

import exchangeApp.security.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {
    public JwtUserFactory() {}

    public static JwtUser create(User user){
        return new JwtUser(user.getUsername(),
                user.getPassword(),
                getAuthorities(user));
    }

    private static List<GrantedAuthority> getAuthorities(User user){
        return user.getAuthorities()
                .stream()
                .map(auth-> new SimpleGrantedAuthority(auth.getRole().name()))
                .collect(Collectors.toList());}
}

package exchangeApp.UserAndSecurity.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthConfig extends GlobalAuthenticationConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("1").password(passwordEncoder.encode("1")).roles(USER)
                .and()
                .withUser("2").password(passwordEncoder.encode("2")).roles(USER, ADMIN)
                .and()
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder BCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
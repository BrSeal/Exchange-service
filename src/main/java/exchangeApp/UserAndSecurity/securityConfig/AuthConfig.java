package exchangeApp.UserAndSecurity.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthConfig extends GlobalAuthenticationConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";


    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("admin").roles(USER,ADMIN);
    }
}

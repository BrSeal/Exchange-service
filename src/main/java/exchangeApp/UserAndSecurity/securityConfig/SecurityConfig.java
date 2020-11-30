package exchangeApp.UserAndSecurity.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/home",
                        "/exchange/rates",
                        "/api/**",
                        "/swagger-ui/**",
                        "/registration",
                        "user/add").permitAll()
                .antMatchers("/exchange/doExchange", "/stats/rating", "/stats/user/**").hasAnyRole(USER, ADMIN)
                .antMatchers("/stats/overall",
                        "/stats/moreThan/**",
                        "/stats/atOnesMoreThan/**").hasRole(ADMIN)
                .and()
                .formLogin()
                .loginProcessingUrl("/authenticate")
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }
}

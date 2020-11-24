package exchangeApp.UserAndSecurity.securityConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/exchange/rates","/api/**", "/swagger-ui/**").permitAll()
                .antMatchers("/exchange/doExchange", "/stats/rating").hasAnyRole(USER, ADMIN)
                .antMatchers("/stats/user/**",
                        "/stats/overall",
                        "/stats/moreThan/**",
                        "/stats/atOnesMoreThan/**").hasRole(ADMIN)
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}

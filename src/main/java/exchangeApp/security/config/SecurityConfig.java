package exchangeApp.security.config;

import exchangeApp.security.jwt.JwtConfigurer;
import exchangeApp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String USER = "USER";
    private static final String ADMIN = "ADMIN";

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Autowired
    public SecurityConfig(DataSource dataSource, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
        ;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/stats/overall/**", "/stats/moreThan/**", "/stats/atOnesMoreThan/**").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider))
                .and()
                .formLogin()
                //     .loginProcessingUrl("/authenticate")
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    //  @Bean
    //  public WebMvcConfigurer corsConfig(){
    //      return new WebMvcConfigurer() {
    //          @Override
    //          public void addCorsMappings(CorsRegistry registry) {
    //              registry.addMapping("/**")
    //                      .allowedMethods("GET","POST","PUT","DELETE")
    //                      .allowedHeaders("*")
    //                      .allowedOrigins("*");
    //          }
    //      };
    //  }
}

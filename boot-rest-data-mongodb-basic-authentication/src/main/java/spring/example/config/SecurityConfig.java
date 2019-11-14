package spring.example.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String REALM_NAME = "example.com";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/swagger-resources/**", "swagger-ui.html").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .realmName(REALM_NAME)
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("manager").password("password").roles("MANAGER");
    }/*or

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return new ProviderManager(
                Arrays.asList(
                        (AuthenticationProvider) authenticationProvider
                )
        );
    }*/

}
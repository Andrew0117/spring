package org.web.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.web.user.config.provider.CustomAuthenticationProvider;

import java.util.Collections;

@Configuration
@ComponentScan("org.web.user")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        http
                .csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()

                .antMatchers("/osoba/**")
                .access("hasAuthority('ROLE_USER')")

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/check")
                .defaultSuccessUrl("/allowed", true)
                .failureUrl("/fail?error=true")
                .usernameParameter("ju")
                .passwordParameter("jp")
                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutUrl("/exit")
                .logoutSuccessUrl("/logout");
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() {
        return new ProviderManager(
                Collections.singletonList(authenticationProvider)
        );
    }

}

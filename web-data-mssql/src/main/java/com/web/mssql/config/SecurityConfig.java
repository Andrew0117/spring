package com.web.mssql.config;

import com.web.mssql.security.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Arrays;

@Configuration
@ComponentScan("com.web.mssql")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.csrf().disable().addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeRequests()
                .antMatchers(new String[]{"/css/**"}).permitAll()
                .antMatchers(new String[]{"/js/**"}).permitAll()
                .antMatchers(new String[]{"/img/**"}).permitAll()
                .antMatchers("/na/**").access("hasAuthority('ROLE_USER')")

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
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(
                Arrays.asList((AuthenticationProvider) authenticationProvider)
        );
    }
}

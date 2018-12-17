package com.web.mssql.security.provider;

import com.web.mssql.entity.projection.VwUserWithRole;
import com.web.mssql.jpa.UsersRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com>
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        WebAuthenticationDetails wad = (WebAuthenticationDetails) authentication.getDetails();

        VwUserWithRole vwUserWithRole = usersRepository.findActiveUserForLogin(
                authentication.getName(), authentication.getCredentials().toString()
        );

        if (vwUserWithRole != null && vwUserWithRole.getActive() != 1) {
            return null;
        }

        if (vwUserWithRole != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(vwUserWithRole.getAuthority());
            grantedAuths.add(grantedAuthority);

            UsernamePasswordAuthenticationToken result =
                    new UsernamePasswordAuthenticationToken(authentication.getName(),
                            authentication.getCredentials(),
                            grantedAuths);

            result.setDetails(authentication.getDetails());

            return result;
        }
        return null;
    }
}

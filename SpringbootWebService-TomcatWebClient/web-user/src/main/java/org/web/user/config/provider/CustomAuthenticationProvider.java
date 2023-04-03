package org.web.user.config.provider;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.web.user.service.UserService;
import org.web.user.vm.UserWithRoleVM;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "singleton")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserWithRoleVM userWithRoleVM = new UserWithRoleVM();
        userWithRoleVM.setName(authentication.getName());
        userWithRoleVM.setPass(authentication.getCredentials().toString());
        userWithRoleVM = userService.user(userWithRoleVM);

        if (userWithRoleVM != null && userWithRoleVM.getActive() != 1) {
            return null;
        }

        if (userWithRoleVM != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userWithRoleVM.getAuthority());
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

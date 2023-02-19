package org.web.springboot.component;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component(value = "userComponent")
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserComponent {

    private String token = null;
    private String tokenWithPrefix = null;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenWithPrefix() {
        return tokenWithPrefix;
    }

    public void setTokenWithPrefix(String tokenWithPrefix) {
        this.tokenWithPrefix = tokenWithPrefix;
    }
}

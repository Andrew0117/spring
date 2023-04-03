package org.web.user.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service(value = "appService")
@Scope(value = "singleton")
public class AppService implements AppServiceI, InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(AppService.class);

    private static final long serialVersionUID = 5826781119417533735L;

    @Value("${service.user.api.protocol}")
    private String protocol;

    @Value("${service.user.api.address}")
    private String address;

    @Value("${service.user.api.port}")
    private String port;

    private String host;

    @Override
    public void afterPropertiesSet() {
        StringBuilder builder = new StringBuilder();
        builder.append(protocol).append("://");
        builder.append(address).append(":");
        builder.append(port);

        host = builder.substring(0, builder.length());
    }

    public String getHost() {
        return host;
    }

}

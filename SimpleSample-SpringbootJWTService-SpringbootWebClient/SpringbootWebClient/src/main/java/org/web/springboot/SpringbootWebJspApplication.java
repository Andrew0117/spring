package org.web.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
public class SpringbootWebJspApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootWebJspApplication.class);
        app.run(args);
    }
}

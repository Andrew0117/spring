package org.web.springboot;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWebJspApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(SpringbootWebJspApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.servlet.context-path", "/springboot2webapp"));
        app.run(args);
    }
}

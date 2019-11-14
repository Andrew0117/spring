package spring.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import spring.example.config.SecurityConfig;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@Import({SecurityConfig.class})
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}

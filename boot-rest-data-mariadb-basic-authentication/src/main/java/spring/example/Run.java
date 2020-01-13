package spring.example;

import org.springframework.context.annotation.ComponentScan;
import spring.example.config.SecurityConfig;
import spring.example.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created on : 30.06.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@EnableJpaRepositories(basePackages = "spring.example.repositories")
@Import({SecurityConfig.class, JpaConfig.class})
public class Run {

    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}

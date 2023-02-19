package jwt;

import jwt.user.Role;
import jwt.user.User;
import jwt.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// jwt.io
// https://www.allkeysgenerator.com/Random/Security-Encryption-Key-Generator.aspx
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository repository) {
        return (args) -> {
            repository.save(User.builder()
                    .firstname("test")
                    .lastname("test")
                    .email("test@test.org")
                    .password("$2a$10$z/xfNyeGB.iplYGitoBwQOHJIFgKxLvGE/HTqJbPC6hCS8WQx00lS") // test
                    .role(Role.USER)
                    .build());
        };
    }

}

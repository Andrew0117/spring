package spring.jwt.security.json;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
}
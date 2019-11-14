package spring.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on : 14.12.18
 *
 * @author Berezhnov Andrey <Andrey at andrew.my@yahoo.com>
 */

@RestController
public class HomeRestController {

    @GetMapping("/")
    public String greeting() {
        return "Hello, World!";
    }

}

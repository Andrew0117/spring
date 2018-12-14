package spring.example.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on : 30.06.18
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

package org.web.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.web.springboot.component.UserComponent;
import org.web.springboot.util.Constants;
import org.web.springboot.vm.JwtResponse;
import org.web.springboot.vm.UserVM;

@Controller
public class UserController {
    @Autowired
    private UserComponent userComponent;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginGET() {

        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPOST(
            @RequestParam String username,
            @RequestParam String password,
            Model model
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        UserVM userVM = new UserVM(username, password);

        HttpEntity<UserVM> request = new HttpEntity<>(userVM, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JwtResponse> authenticationResponse = null;

        userComponent.setToken(null);
        userComponent.setTokenWithPrefix(null);

        try {
            authenticationResponse =
                    restTemplate.exchange(
                            Constants.AUTHENTICATION_URL,
                            HttpMethod.POST, request,
                            JwtResponse.class
                    );
        } catch (HttpClientErrorException ex) {
            model.addAttribute("message", "Invalid credential");

            return "login";
        }

        if (authenticationResponse.getStatusCode().equals(HttpStatus.OK)) {
            userComponent.setToken(authenticationResponse.getBody().getToken());
            userComponent.setTokenWithPrefix("Bearer " + authenticationResponse.getBody().getToken());
            model.addAttribute("message", "Success");
            return "welcome";
        }

        model.addAttribute("message", "Invalid credential");
        return "login";
    }

    @RequestMapping("helloClient")
    public String secureMethodJWT(Model model) {
        if (userComponent.getToken() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", userComponent.getTokenWithPrefix());
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<String> jwtEntity = new HttpEntity<>(headers);
            ResponseEntity<String> helloResponse =
                    restTemplate.exchange(
                            Constants.CLIENT_END_POINT_URL,
                            HttpMethod.GET,
                            jwtEntity,
                            String.class
                    );
            if (helloResponse.getStatusCode().equals(HttpStatus.OK)) {
                String message = helloResponse.getBody();
                model.addAttribute("message", message);
                return "welcome";
            }
        }
        model.addAttribute("message", "Please login");
        return "login";
    }
}

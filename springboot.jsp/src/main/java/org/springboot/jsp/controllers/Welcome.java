package org.springboot.jsp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Welcome {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

}

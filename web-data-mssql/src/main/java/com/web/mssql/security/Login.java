package com.web.mssql.security;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com>
 */
@Controller
public class Login implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(Login.class);

    private static final long serialVersionUID = -4246808363043402878L;

    @RequestMapping(value = "/allowed", method = RequestMethod.GET)
    public String executeSecurity() {
        return "redirect:/success";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/isu", method = RequestMethod.GET)
    public String isu() {
        return "login";
    }

    @RequestMapping(value = "/notfound", method = RequestMethod.GET)
    public String notfound() {
        return "notfound";
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String loginerror() {
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login";
    }
}

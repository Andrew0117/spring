package org.web.user.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultUrl {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String defaultUrlRootSlash() {

        return "redirect:/login";
    }

}

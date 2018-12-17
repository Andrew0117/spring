package com.web.mssql.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com>
 */
@Controller
public class DefaultUrl {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String defaultUrlRootSlash() {

        return "redirect:/login";
    }

}

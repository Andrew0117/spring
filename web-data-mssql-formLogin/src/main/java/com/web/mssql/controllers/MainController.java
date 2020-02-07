package com.web.mssql.controllers;

import com.web.mssql.filters.UserFilter;
import com.web.mssql.services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 16.12.2018 19:42
 */
@Controller
public class MainController implements Serializable {

    private static final long serialVersionUID = 2888641946358648301L;

    @Autowired
    private RolesService rolesService;

    @RequestMapping(value = "/success", method = {RequestMethod.GET})
    public String main(Model model) {
        model.addAttribute("roles", rolesService.getRolesList());
        model.addAttribute("userFilter", new UserFilter());
        model.addAttribute("newUser", null);

        return "success";
    }

}

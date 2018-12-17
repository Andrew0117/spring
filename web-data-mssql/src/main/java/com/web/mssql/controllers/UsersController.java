package com.web.mssql.controllers;

import com.web.mssql.entity.Users;
import com.web.mssql.filters.UserFilter;
import com.web.mssql.jpa.UsersRepository;
import com.web.mssql.services.RolesService;
import com.web.mssql.validators.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Andrew <Andrey at andrew.my@yahoo.com> on 17.12.2018 12:51
 */
@Controller
@RequestMapping(value = "/user")
public class UsersController implements Serializable {

    private static final Logger LOGGER = Logger.getLogger(UsersController.class);

    private static final long serialVersionUID = -752714613033659000L;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String empty(Model model) {
        model.addAttribute("roles", rolesService.getRolesList());
        model.addAttribute("userFilter", new UserFilter());
        model.addAttribute("newUser", null);

        return "success";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(
            @ModelAttribute(value = "userFilter") UserFilter userFilter,
            BindingResult result,
            Model model
    ) {
        model.addAttribute("roles", rolesService.getRolesList());

        userValidator.validate(userFilter, result);
        if (result.hasErrors()) {
            model.addAttribute("userFilter", userFilter);

            return "success";
        }

        Users user = new Users();
        user.setName(userFilter.getName());
        user.setPass(userFilter.getPass());
        user.setActive(userFilter.getActive());
        user.setRoles(rolesService.findRoles(userFilter.getRole()));
        user.setDate(Instant.now());

        Users newUser = usersRepository.save(user);

        model.addAttribute("userFilter", new UserFilter());
        model.addAttribute("newUser", newUser);

        return "success";
    }
}

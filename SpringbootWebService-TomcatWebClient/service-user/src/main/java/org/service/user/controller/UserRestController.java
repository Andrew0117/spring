package org.service.user.controller;

import org.service.user.service.UsersService;
import org.service.user.vm.UserWithRoleVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserRestController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/")
    public ResponseEntity<UserWithRoleVM> user(@RequestBody UserWithRoleVM user) {
        user = usersService.getUserWithRoleVM(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}

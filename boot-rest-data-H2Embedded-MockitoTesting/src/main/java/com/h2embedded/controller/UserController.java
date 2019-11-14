package com.h2embedded.controller;

import com.h2embedded.entity.User;
import com.h2embedded.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orgs")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/{orgId}/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User addUser(
            @PathVariable("orgId") Long orgId,
            @RequestBody User user
    ) {
        user = userService.save(orgId, user);

        return user;
    }

    @PutMapping("/{orgId}/users/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable("orgId") Long orgId,
            @PathVariable("userId") Long userId,
            @RequestBody User user) {
        user = userService.updateUser(orgId, userId, user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{orgId}/users/{userId}")
    public ResponseEntity<User> getUser(
            @PathVariable("orgId") Long orgId,
            @PathVariable("userId") Long userId
    ) {
        User user = userService.findUser(orgId, userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{orgId}/users/{userId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PathVariable("orgId") Long orgId,
            @PathVariable("userId") Long userId
    ) {
        userService.delete(orgId, userId);
    }

    @GetMapping("/{orgId}/users")
    public ResponseEntity<List<User>> getAllByOrganization(
            @PathVariable("orgId") Long orgId
    ) {
        List<User> lUser = userService.getAllByOrganization(orgId);
        return new ResponseEntity<>(lUser, HttpStatus.OK);
    }
}

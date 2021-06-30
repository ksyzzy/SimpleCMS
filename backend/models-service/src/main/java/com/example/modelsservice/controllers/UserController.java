package com.example.modelsservice.controllers;

import com.example.modelsservice.models.User;
import com.example.modelsservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User newUser) {
        return userService.addUser(newUser);
    }

    @PostMapping(value = "/user/{id}")
    public User setAdminForUser(@PathVariable Long id) {
        return userService.setAdminPermissions(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public UserService getUserService() {
        return userService;
    }


}

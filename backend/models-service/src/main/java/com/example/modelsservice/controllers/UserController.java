package com.example.modelsservice.controllers;

import com.example.modelsservice.models.User;
import com.example.modelsservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    private ObjectMapper mapper;

    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        try {
            newUser = userService.addUser(newUser);
            String json = mapper.writeValueAsString(newUser);
            System.out.println(json);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"Could not parse\"}");
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"message\": \"User already exists\"}");
        }
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

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}

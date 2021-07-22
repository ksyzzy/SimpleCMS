package com.example.modelsservice.controllers;

import com.example.modelsservice.enums.ErrorCodes;
import com.example.modelsservice.helpers.ResponseBuilder;
import com.example.modelsservice.models.User;
import com.example.modelsservice.services.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private UserService userService;

    private ObjectMapper mapper;

    private ResponseBuilder responseBuilder;

    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        try {
            newUser = userService.addUser(newUser);
            return responseBuilder.build(newUser, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCodes.USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        try {
            User userById = userService.getUserById(id).orElseThrow();
            return responseBuilder.build(userById, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCodes.USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
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

    public ResponseBuilder getJsonBuilder() {
        return responseBuilder;
    }

    @Autowired
    public void setJsonBuilder(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }
}

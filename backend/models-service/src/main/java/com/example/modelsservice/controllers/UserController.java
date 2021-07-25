package com.example.modelsservice.controllers;

import com.example.modelsservice.enums.ErrorCode;
import com.example.modelsservice.helpers.ResponseBuilder;
import com.example.modelsservice.models.User;
import com.example.modelsservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class UserController {

    private UserService userService;

    private ObjectMapper mapper;

    private ResponseBuilder responseBuilder;

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User newUser) {
        try {
            newUser = userService.addUser(newUser);
            return responseBuilder.build(newUser, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCode.USER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        } catch (PasswordException e) {
            return responseBuilder.build(ErrorCode.PASSWORD_IS_BLANK, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable Long id) {
        try {
            User userById = userService.getUserById(id).orElseThrow();
            return responseBuilder.build(userById, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCode.USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateUserById(@PathVariable long id, @RequestBody User updatedUser) {
        try {
            updatedUser = userService.updateUser(updatedUser);
            return responseBuilder.build(updatedUser, HttpStatus.OK);
        } catch (NoSuchElementException | IllegalArgumentException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCode.USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/users/{id}/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setAdminForUser(@PathVariable Long id) {
        try {
            User user = userService.setAdminPermissions(id);
            return responseBuilder.build(user, HttpStatus.OK);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            e.printStackTrace();
            return responseBuilder.build(ErrorCode.USER_DOES_NOT_EXIST, HttpStatus.NOT_FOUND);
        }
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

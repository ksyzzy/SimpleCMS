package com.example.modelsservice.controllers;

import com.example.modelsservice.configuration.SwaggerConfig;
import com.example.modelsservice.dto.UserDTO;
import com.example.modelsservice.enums.ErrorCode;
import com.example.modelsservice.helpers.ResponseBuilder;
import com.example.modelsservice.models.User;
import com.example.modelsservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bouncycastle.openssl.PasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.NoSuchElementException;

@Api(value = "User controller", tags = {SwaggerConfig.USER_TAG})
@RestController
public class UserController {

    private UserService userService;

    private ObjectMapper mapper;

    private ResponseBuilder responseBuilder;

    @ApiOperation(value = "Get list of all users from the database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = UserDTO.class, responseContainer = "List")
    })
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllUsers() {
        return responseBuilder.build(userService.getAllUsers(), HttpStatus.OK);
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

    @ApiOperation(value = "Get user with provided id from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK", response = UserDTO.class),
            @ApiResponse(code = 404, message = "Not Found", response = String.class)
    })
    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserById(@PathVariable long id) {
        try {
            UserDTO user = userService.getUserDTOById(id);
            return responseBuilder.build(user, HttpStatus.OK);
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

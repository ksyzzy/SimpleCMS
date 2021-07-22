package com.example.modelsservice.helpers;

import com.example.modelsservice.enums.ErrorCodes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ResponseBuilder {
    private final HashMap<ErrorCodes, String> errorsList;

    private ObjectMapper mapper;

    public ResponseBuilder() {
        HashMap<ErrorCodes, String> errorsList = new HashMap<>();
        errorsList.put(ErrorCodes.JSON_PARSE_ERROR, "Could not parse the result");
        errorsList.put(ErrorCodes.USER_DOES_NOT_EXIST, "User does not exist");
        errorsList.put(ErrorCodes.USER_ALREADY_EXISTS, "User already exists");
        this.errorsList = errorsList;
    }

    public String buildJSON(Object object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return buildJSON("JSON_PARSE_ERROR", errorsList.get(ErrorCodes.JSON_PARSE_ERROR));
        }
    }

    public String buildJSON(ErrorCodes errorCode) {
        return new JSONObject()
                .appendField("message", errorsList.getOrDefault(errorCode, "Unspecified error"))
                .toJSONString();
    }

    public String buildJSON(String key, Object value) {
        return new JSONObject().appendField(key, value).toJSONString();
    }

    public ResponseEntity<String> build(Object object, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(buildJSON(object));
    }

    public ResponseEntity<String> build(ErrorCodes errorCode, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(buildJSON(errorCode));
    }

    public ResponseEntity<String> build(String jsonResponse, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(jsonResponse);
    }

    public HashMap<ErrorCodes, String> getErrorsList() {
        return errorsList;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
package com.example.modelsservice.helpers;

import com.example.modelsservice.enums.ErrorCode;
import com.example.modelsservice.models.Eligible;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseBuilder {
    private JSONBuilder builder;

    public <T extends Eligible> ResponseEntity<String> build(T object, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(builder.buildJSON(object));
    }

    public <T extends Eligible> ResponseEntity<String> build(List<T> objectsList, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(builder.buildJSON(objectsList));
    }

    public ResponseEntity<String> build(ErrorCode errorCode, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(builder.buildJSON(errorCode));
    }

    public ResponseEntity<String> build(String jsonResponse, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(jsonResponse);
    }

    public JSONBuilder getBuilder() {
        return builder;
    }

    @Autowired
    public void setBuilder(JSONBuilder builder) {
        this.builder = builder;
    }
}
package com.example.modelsservice.helpers;

import com.example.modelsservice.enums.ErrorCode;
import com.example.modelsservice.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
    private JSONBuilder builder;

    public <T extends BaseEntity> ResponseEntity<String> build(T object, HttpStatus httpstatus) {
        return ResponseEntity
                .status(httpstatus)
                .body(builder.buildJSON(object));
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
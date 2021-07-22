package com.example.modelsservice.exceptions;

public class KeyNotFoundException extends RuntimeException {
    public KeyNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}

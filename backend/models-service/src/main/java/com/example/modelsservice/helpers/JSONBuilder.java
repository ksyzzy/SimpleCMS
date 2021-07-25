package com.example.modelsservice.helpers;

import com.example.modelsservice.enums.ErrorCode;
import com.example.modelsservice.models.BaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class JSONBuilder {
    private final HashMap<ErrorCode, String> errorsList;
    private ObjectMapper mapper;

    public JSONBuilder() {
        HashMap<ErrorCode, String> errorsList = new HashMap<>();
        errorsList.put(ErrorCode.JSON_PARSE_ERROR, "Could not parse the result");
        errorsList.put(ErrorCode.USER_DOES_NOT_EXIST, "User does not exist");
        errorsList.put(ErrorCode.USER_ALREADY_EXISTS, "User already exists");
        errorsList.put(ErrorCode.PASSWORD_IS_BLANK, "Password cannot be blank");
        this.errorsList = errorsList;
    }

    public <T extends BaseEntity> String buildJSON(T object) {
        try {
            return this.mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return buildJSON("JSON_PARSE_ERROR", errorsList.get(ErrorCode.JSON_PARSE_ERROR));
        }
    }

    public String buildJSON(ErrorCode errorCode) {
        return new JSONObject()
                .appendField("message", errorsList.getOrDefault(errorCode, "Unspecified error"))
                .toJSONString();
    }

    public String buildJSON(String key, String value) {
        return new JSONObject().appendField(key, value).toJSONString();
    }

    public HashMap<ErrorCode, String> getErrorsList() {
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

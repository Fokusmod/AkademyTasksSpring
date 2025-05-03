package com.example.AkademyTasks.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class JsonMapper {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String toJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

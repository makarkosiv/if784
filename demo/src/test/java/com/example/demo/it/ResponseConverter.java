package com.example.demo.it;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Convert JSON to Object and vice versa.
 */
public class ResponseConverter {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String requestToJson(T userRequest) throws JsonProcessingException {
        return objectMapper.writeValueAsString(userRequest);
    }

    public static <T> T jsonToResponse(String response, Class<T> type) throws IOException {
        return objectMapper.readValue(response, type);
    }
}

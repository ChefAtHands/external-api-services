package com.chefathands.recipesearch.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<Map<String, Object>> handleExternalApiException(ExternalApiException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", "External API Error");
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpClientError(HttpClientErrorException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Client error when calling external API");
        body.put("error", ex.getStatusText());
        
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<Map<String, Object>> handleHttpServerError(HttpServerErrorException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Server error from external API");
        body.put("error", ex.getStatusText());
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage());
        body.put("error", "Internal Server Error");
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
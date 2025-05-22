package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SensitiveDataException.class)
    public ResponseEntity<Map<String, String>> handleSensitiveDataException(
            SensitiveDataException ex, HttpServletRequest request) {
        
        // Log the error with context but without sensitive data
        log.error("Error processing sensitive data request: {}", ex.getMessage());
        
        Map<String, String> response = new HashMap<>();
        response.put("error", "An error occurred while processing your request");
        response.put("message", "Please contact support if the problem persists");
        response.put("path", request.getRequestURI());
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception ex, HttpServletRequest request) {
        // Log the error with context but without stack trace
        log.error("Unexpected error occurred: {}", ex.getMessage());
        
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("errorMessage", "An unexpected error occurred");
        mav.addObject("requestedUrl", request.getRequestURI());
        mav.addObject("timestamp", java.time.LocalDateTime.now());
        
        return mav;
    }
} 
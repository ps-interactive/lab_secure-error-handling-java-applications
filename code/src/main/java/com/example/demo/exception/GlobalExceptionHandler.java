package com.example.demo.exception;

import com.example.demo.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Global exception handler using @ControllerAdvice
 * 
 * This class demonstrates:
 * 1. Separation of user-friendly and developer messages
 * 2. Proper error response structure
 * 3. MDC usage for contextual logging
 * 4. Secure logging practices
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserManagementException.class)
    public ResponseEntity<ErrorResponse> handleUserManagementException(
            UserManagementException ex,
            HttpServletRequest request) {
        
        // Add request context to MDC for logging
        MDC.put("requestId", request.getHeader("X-Request-ID"));
        MDC.put("userId", request.getHeader("X-User-ID"));
        
        // Log the error with developer message (never log user message to avoid confusion)
        log.error("User management error occurred: {}", ex.getMessage(), ex);
        
        // Create error response with user-friendly message
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getUserMessage()) // User-friendly message
                .errorCode(ex.getErrorCode())
                .path(request.getRequestURI())
                .build();

        // Clear MDC after logging
        MDC.clear();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex,
            HttpServletRequest request) {
        
        MDC.put("requestId", request.getHeader("X-Request-ID"));
        
        // Log the full stack trace for unexpected errors
        log.error("Unexpected error occurred", ex);
        
        // Create a generic error response
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred. Please try again later.") // Generic user message
                .errorCode("INTERNAL_ERROR")
                .path(request.getRequestURI())
                .build();

        MDC.clear();
        
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 
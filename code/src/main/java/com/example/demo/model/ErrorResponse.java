package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Standardized error response model
 * 
 * This class provides a consistent structure for error responses
 * across the application. It includes both user-friendly information
 * and technical details needed for debugging.
 */
@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;      // User-friendly message
    private String errorCode;    // Technical error code for support
    private String path;         // Request path where error occurred
} 
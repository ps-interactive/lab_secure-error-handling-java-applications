package com.example.demo.exception;

import lombok.Getter;

/**
 * Custom exception for user management operations
 * 
 * This exception demonstrates the separation between user-friendly messages
 * and developer messages. The user message is safe to show to end users,
 * while the developer message contains technical details for debugging.
 */
@Getter
public class UserManagementException extends RuntimeException {
    
    private final String userMessage;
    private final String errorCode;

    public UserManagementException(String userMessage, String developerMessage, String errorCode) {
        super(developerMessage);
        this.userMessage = userMessage;
        this.errorCode = errorCode;
    }

    public UserManagementException(String userMessage, String developerMessage, String errorCode, Throwable cause) {
        super(developerMessage, cause);
        this.userMessage = userMessage;
        this.errorCode = errorCode;
    }
} 
package com.example.exception;

public class SensitiveDataException extends RuntimeException {
    public SensitiveDataException(String message) {
        super(message);
    }

    public SensitiveDataException(String message, Throwable cause) {
        super(message, cause);
    }
} 
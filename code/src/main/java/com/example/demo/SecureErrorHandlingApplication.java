package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Secure Error Handling Demo
 * 
 * This application demonstrates:
 * 1. User-friendly error messages vs developer messages
 * 2. Secure logging practices
 * 3. MDC (Mapped Diagnostic Context) usage
 * 4. Custom error handling with @ControllerAdvice
 */
@SpringBootApplication
public class SecureErrorHandlingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecureErrorHandlingApplication.class, args);
    }
} 
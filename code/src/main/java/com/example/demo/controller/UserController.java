package com.example.demo.controller;

import com.example.demo.exception.UserManagementException;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User management controller
 * 
 * This controller demonstrates:
 * 1. Proper use of MDC for contextual logging
 * 2. Secure logging practices
 * 3. Throwing custom exceptions with user-friendly messages
 * 4. Request context tracking
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, @RequestHeader("X-Request-ID") String requestId) {
        // Add request context to MDC
        MDC.put("requestId", requestId);
        MDC.put("operation", "createUser");
        
        try {
            // Log user creation (without sensitive data)
            log.info("Creating new user: {}", user.toString());
            
            // Simulate validation error
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                throw new UserManagementException(
                    "Please provide a valid email address", // User-friendly message
                    "Email validation failed for user: " + user.getUsername(), // Developer message
                    "USER_001" // Error code
                );
            }
            
            // Simulate duplicate user error
            if ("existing@email.com".equals(user.getEmail())) {
                throw new UserManagementException(
                    "An account with this email already exists", // User-friendly message
                    "Duplicate email detected: " + user.getEmail(), // Developer message
                    "USER_002" // Error code
                );
            }
            
            // In a real application, we would save the user here
            // For demo purposes, we'll just return the user
            return ResponseEntity.ok(user);
            
        } finally {
            // Always clear MDC after the request is processed
            MDC.clear();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id, @RequestHeader("X-Request-ID") String requestId) {
        MDC.put("requestId", requestId);
        MDC.put("userId", id);
        MDC.put("operation", "getUser");
        
        try {
            // Simulate user not found
            if ("not-found".equals(id)) {
                throw new UserManagementException(
                    "User not found", // User-friendly message
                    "User not found with ID: " + id, // Developer message
                    "USER_003" // Error code
                );
            }
            
            // Simulate a user
            User user = User.builder()
                    .id(id)
                    .username("demoUser")
                    .email("user@example.com")
                    .password("sensitive-password") // This won't be logged due to custom toString
                    .creditCardNumber("4111111111111111") // This won't be logged due to custom toString
                    .fullName("Demo User")
                    .address("123 Main St")
                    .build();
            
            // Log user retrieval (sensitive data is automatically excluded)
            log.info("Retrieved user: {}", user);
            
            return ResponseEntity.ok(user);
            
        } finally {
            MDC.clear();
        }
    }
} 
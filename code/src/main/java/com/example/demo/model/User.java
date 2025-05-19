package com.example.demo.model;

import lombok.Data;
import lombok.Builder;

/**
 * User model class
 * 
 * This class demonstrates how to handle sensitive data in logging.
 * Note that fields like password and creditCardNumber should never be logged.
 */
@Data
@Builder
public class User {
    private String id;
    private String username;
    private String email;
    private String password; // Sensitive data - never log this!
    private String creditCardNumber; // Sensitive data - never log this!
    private String fullName;
    private String address;
    
    // Custom toString to prevent logging sensitive data
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
} 
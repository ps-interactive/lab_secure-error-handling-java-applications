package com.example.service;

import com.example.exception.SensitiveDataException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SensitiveDataService {
    
    public String processSensitiveData(String userId, String creditCardNumber) {
        try {
            // Put non-sensitive context in MDC
            MDC.put("userId", userId);
            MDC.put("requestId", java.util.UUID.randomUUID().toString());
            
            // Log without sensitive data
            log.info("Processing payment request for user");
            
            // Simulate some processing
            if (creditCardNumber == null || creditCardNumber.length() < 16) {
                throw new SensitiveDataException("Invalid credit card number");
            }
            
            // Log success without exposing sensitive data
            log.info("Payment processed successfully");
            
            return "Payment processed for user " + userId;
        } finally {
            // Always clear MDC to prevent data leakage
            MDC.clear();
        }
    }
    
    public String getSensitiveData(String userId) {
        try {
            MDC.put("userId", userId);
            MDC.put("requestId", java.util.UUID.randomUUID().toString());
            
            // Simulate fetching sensitive data
            String sensitiveData = "Sensitive data for " + userId;
            
            // Log without exposing the actual sensitive data
            log.info("Retrieved sensitive data for user");
            
            return sensitiveData;
        } finally {
            MDC.clear();
        }
    }
} 
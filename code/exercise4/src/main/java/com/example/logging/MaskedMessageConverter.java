package com.example.logging;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class MaskedMessageConverter extends MessageConverter {
    @Override
    public String convert(ILoggingEvent event) {
        String message = event.getFormattedMessage();
        
        // Mask credit card numbers (assuming 16 digits)
        message = message.replaceAll("\\b\\d{16}\\b", "****-****-****-****");
        
        // Mask other sensitive patterns as needed
        // For example, email addresses
        message = message.replaceAll("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b", "***@***.***");
        
        return message;
    }
} 
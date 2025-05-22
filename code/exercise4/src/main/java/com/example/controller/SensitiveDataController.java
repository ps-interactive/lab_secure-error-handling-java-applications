package com.example.controller;

import com.example.service.SensitiveDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/data")
@RequiredArgsConstructor
public class SensitiveDataController {

    private final SensitiveDataService sensitiveDataService;

    @PostMapping("/process")
    public ResponseEntity<String> processData(
            @RequestParam String userId,
            @RequestParam String creditCardNumber) {
        String result = sensitiveDataService.processSensitiveData(userId, creditCardNumber);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getData(@PathVariable String userId) {
        String result = sensitiveDataService.getSensitiveData(userId);
        return ResponseEntity.ok(result);
    }
} 
package com.example.symptom_checker.controller;


import com.google.firebase.FirebaseApp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class TestController {

    @GetMapping("/test-firebase")
    public ResponseEntity<String> testFirebase() {
        try {
            return ResponseEntity.ok("Firebase initialized: " + FirebaseApp.getInstance().getName());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Firebase not initialized.");
        }
    }
}


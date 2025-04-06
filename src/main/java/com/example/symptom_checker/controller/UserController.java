package com.example.symptom_checker.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

public class UserController {
    @GetMapping("/current-user")
public Map<String, String> getCurrentUser(HttpServletRequest request) {
    String userId = (String) request.getSession().getAttribute("userId"); // Fetch from session
    return Map.of("userId", userId != null ? userId : "guest");
}

}

package com.example.symptom_checker.service;

import com.example.symptom_checker.model.GeminiRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
public class GeminiService {
    private final WebClient webClient;

    @Value("${gemini.api.key:}")
    private String apiKey; // ✅ Ensures API key is present

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent")
                .build();
    }

    public String analyzeSymptoms(String symptoms) {
        // ✅ Validate API Key
        if (apiKey.isBlank()) {
            throw new IllegalStateException("❌ Missing GEMINI API Key! Add 'gemini.api.key' in application.properties.");
        }

        // ✅ Construct Request Body Using DTO
        GeminiRequest.Content.Part part = new GeminiRequest.Content.Part();
        part.setText("A person is experiencing these symptoms: " + symptoms + 
                     ". What disease might they have? Suggest a doctor to consult.");

        GeminiRequest.Content content = new GeminiRequest.Content();
        content.setParts(List.of(part));

        GeminiRequest requestBody = new GeminiRequest();
        requestBody.setContents(List.of(content));

        // ✅ Send API Request
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.queryParam("key", apiKey).build())
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .toEntity(new ParameterizedTypeReference<Map<String, Object>>() {}) // Retrieve as Map
                .timeout(Duration.ofSeconds(10)) // Timeout after 10 seconds
                .map(entity -> {
                    Map<String, Object> response = entity.getBody();
                    if (response != null && response.containsKey("candidates")) {
                        Object candidates = response.get("candidates");
                        if (candidates instanceof Iterable<?> iterable) {
                            for (Object obj : iterable) {
                                if (obj instanceof Map<?, ?> map && map.containsKey("content")) {
                                    return map.get("content").toString(); // Extract and return AI response
                                }
                            }
                        }
                    }
                    return "No diagnosis available."; // Fallback if no candidates found
                })
                .onErrorResume(e -> {
                    e.printStackTrace(); // Log the error
                    return Mono.just("⚠️ Error fetching AI response");
                })
                .block(); // Execute and block to get synchronous response
    }
}

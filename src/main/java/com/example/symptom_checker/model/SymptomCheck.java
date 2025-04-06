package com.example.symptom_checker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "symptom_checks")
public class SymptomCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String symptoms;
    
    @Column(columnDefinition = "TEXT") // ✅ Fixes MySQL error (long AI response)
    private String severity; 
    
    private LocalDateTime timestamp = LocalDateTime.now(); 

    public SymptomCheck() {}

    public SymptomCheck(String userId, String symptoms, String severity) {
        this.userId = userId;
        this.symptoms = symptoms;
        this.severity = severity;
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "SymptomCheck{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", severity='" + severity + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

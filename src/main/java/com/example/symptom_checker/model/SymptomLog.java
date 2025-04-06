package com.example.symptom_checker.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "symptom_logs")
public class SymptomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String symptoms;
    private String severity;
    private int duration; // ✅ How long the symptom lasted (days)
    
    private LocalDateTime dateLogged = LocalDateTime.now(); // ✅ Timestamp when logged

    public SymptomLog() {}

    public SymptomLog(String userId, String symptoms, String severity, int duration) {
        this.userId = userId;
        this.symptoms = symptoms;
        this.severity = severity;
        this.duration = duration;
        this.dateLogged = LocalDateTime.now();
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

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public LocalDateTime getDateLogged() { return dateLogged; }
    public void setDateLogged(LocalDateTime dateLogged) { this.dateLogged = dateLogged; }

    @Override
    public String toString() {
        return "SymptomLog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", severity='" + severity + '\'' +
                ", duration=" + duration +
                ", dateLogged=" + dateLogged +
                '}';
    }
}

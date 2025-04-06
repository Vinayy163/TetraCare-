package com.example.symptom_checker.controller;

import com.example.symptom_checker.model.SymptomCheck;
import com.example.symptom_checker.model.SymptomLog;
import com.example.symptom_checker.service.RecoveryTrackerService;
import com.example.symptom_checker.service.SymptomCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SymptomController {

    @Autowired
    private SymptomCheckerService symptomCheckerService;

    @Autowired
    private RecoveryTrackerService recoveryTrackerService;

    // Analyze Symptoms using Gemini AI & Save to DB
    @PostMapping("/analyze-symptoms")
    public ResponseEntity<SymptomCheck> analyzeSymptoms(@RequestBody Map<String, String> request) {
        try {
            String userId = request.get("userId");
            String symptoms = request.get("symptoms");

            if (userId == null || userId.isBlank() || symptoms == null || symptoms.isBlank()) {
                return ResponseEntity.badRequest().build();
            }

            SymptomCheck result = symptomCheckerService.analyzeAndSaveSymptoms(userId, symptoms);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    // Get Past Symptom Checks
    @GetMapping("/symptom-history/{userId}")
    public ResponseEntity<?> getSymptomHistory(@PathVariable String userId) {
        try {
            List<SymptomCheck> history = symptomCheckerService.getSymptomHistory(userId);
            if (history.isEmpty()) {
                return ResponseEntity.status(404).body("No symptom history found for user: " + userId);
            }
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while fetching symptom history.");
        }
    }

    // Log Symptoms for Recovery Tracking
    @PostMapping("/log-symptoms")
    public ResponseEntity<String> logSymptoms(@RequestBody SymptomLog log) {
        try {
            recoveryTrackerService.logSymptoms(log.getUserId(), log.getSymptoms(), log.getSeverity(), log.getDuration());
            return ResponseEntity.ok("Symptoms logged successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while logging symptoms.");
        }
    }

    // Get Recovery Timeline Data
    @GetMapping("/recovery-tracker/{userId}")
    public ResponseEntity<?> getRecoveryData(@PathVariable String userId) {
        try {
            List<SymptomLog> logs = recoveryTrackerService.getUserLogs(userId);
            if (logs.isEmpty()) {
                return ResponseEntity.status(404).body("No recovery data found for user: " + userId);
            }
            return ResponseEntity.ok(logs);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred while fetching recovery data.");
        }
    }

    // Export Symptom Report as PDF
    @GetMapping("/export-pdf/{userId}")
    public ResponseEntity<byte[]> exportPDF(@PathVariable String userId) {
        try {
            byte[] pdfContent = recoveryTrackerService.generateSymptomReport(userId);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=Symptom_Report.pdf")
                    .header("Cache-Control", "max-age=3600")
                    .body(pdfContent);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}

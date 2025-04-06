package com.example.symptom_checker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.symptom_checker.model.SymptomLog;
import com.example.symptom_checker.repository.SymptomRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Comparator;
import java.util.logging.Logger;

@Service
public class SymptomService {
    private final SymptomRepository symptomRepository;
    private static final Logger LOGGER = Logger.getLogger(SymptomService.class.getName());

    @Autowired
    public SymptomService(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    // ✅ Save symptoms in the database (With Debugging Logs)
    @Transactional
    public String logSymptoms(String userId, String symptoms, String severity, Integer duration) {
        try {
            LOGGER.info("🚀 logSymptoms() called - User: " + userId + ", Symptoms: " + symptoms + ", Severity: " + severity + ", Duration: " + duration);

            if (userId == null || symptoms == null || severity == null || duration == null) {
                LOGGER.warning("❌ Invalid input: One or more required fields are missing.");
                return "❌ Invalid input. Please provide all required fields.";
            }

            SymptomLog log = new SymptomLog(userId, symptoms, severity, duration);
            symptomRepository.save(log);
            
            LOGGER.info("✅ Symptoms successfully logged for user: " + userId);
            return "✅ Symptoms logged successfully!";
        } catch (Exception e) {
            LOGGER.severe("❌ Error logging symptoms: " + e.getMessage());
            return "❌ Error logging symptoms: " + e.getMessage();
        }
    }

    // ✅ Get past symptoms (sorted by date)
    public List<SymptomLog> getSymptomHistory(String userId) {
        LOGGER.info("Fetching symptom history for user: " + userId);
        List<SymptomLog> logs = symptomRepository.findByUserId(userId);
        logs.sort(Comparator.comparing(SymptomLog::getDateLogged).reversed());
        return logs;
    }
}

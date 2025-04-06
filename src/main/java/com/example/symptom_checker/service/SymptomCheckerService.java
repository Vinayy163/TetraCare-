package com.example.symptom_checker.service;

import com.example.symptom_checker.model.SymptomCheck;
import com.example.symptom_checker.repository.SymptomCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomCheckerService {

    @Autowired
    private SymptomCheckRepository symptomCheckRepository;

    @Autowired
    private GeminiService geminiService;

    public SymptomCheck analyzeAndSaveSymptoms(String userId, String symptoms) {
        String diagnosis = geminiService.analyzeSymptoms(symptoms);
    
        // ✅ If response is empty, assign default value
        if (diagnosis == null || diagnosis.isBlank()) {
            diagnosis = "No diagnosis available.";
        }
    
        // ✅ Create new SymptomCheck object
        SymptomCheck check = new SymptomCheck(userId, symptoms, diagnosis);
    
        // ✅ Save to database and return
        return symptomCheckRepository.save(check);
    }

    public List<SymptomCheck> getSymptomHistory(String userId) {
        return symptomCheckRepository.findByUserId(userId);
    }
}

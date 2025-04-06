package com.example.symptom_checker.service;

import com.example.symptom_checker.model.SymptomLog;
import com.example.symptom_checker.repository.SymptomRepository;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Comparator;

@Service
public class RecoveryTrackerService {

    @Autowired
    private SymptomRepository symptomRepository;

    // ✅ Log Symptoms (Stores date automatically)
    public void logSymptoms(String userId, String symptoms, String severity, int duration) {
        SymptomLog log = new SymptomLog(userId, symptoms, severity, duration);
        symptomRepository.save(log);
    }

    // ✅ Retrieve User Logs (Sorted by Date)
    public List<SymptomLog> getUserLogs(String userId) {
        List<SymptomLog> logs = symptomRepository.findByUserId(userId);
        logs.sort(Comparator.comparing(SymptomLog::getDateLogged).reversed()); // Newest first
        return logs;
    }

    // ✅ Generate PDF Report (Doctor Sharing Mode)
    public byte[] generateSymptomReport(String userId) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
             PDDocument document = new PDDocument()) {
    
            PDPage page = new PDPage();
            document.addPage(page);
            
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
                contentStream.newLineAtOffset(100, 700);
                contentStream.showText("Symptom Report for User: " + userId);
                contentStream.endText();
            }
    
            document.save(outputStream);
            return outputStream.toByteArray();
    
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}

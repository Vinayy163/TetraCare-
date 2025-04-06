package com.example.symptom_checker.utils;

import com.example.symptom_checker.model.SymptomLog;
import java.util.List;

public class PDFGenerator {
    public static byte[] generateReport(List<SymptomLog> logs) {
        StringBuilder report = new StringBuilder("Symptom History Report\n\n");
        for (SymptomLog log : logs) {
            report.append(log.getDateLogged()).append(": ")
                  .append(log.getSymptoms()).append(" - ")
                  .append(log.getSeverity()).append("\n");
        }
        return report.toString().getBytes();
    }
}

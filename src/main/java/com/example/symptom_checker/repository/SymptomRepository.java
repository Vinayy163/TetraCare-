package com.example.symptom_checker.repository;

import com.example.symptom_checker.model.SymptomLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SymptomRepository extends JpaRepository<SymptomLog, Long> {
    List<SymptomLog> findByUserId(String userId);
}

package com.example.symptom_checker.repository;

import com.example.symptom_checker.model.SymptomCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SymptomCheckRepository extends JpaRepository<SymptomCheck, Long> {
    List<SymptomCheck> findByUserId(String userId);
}

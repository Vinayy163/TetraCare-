package com.example.symptom_checker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.symptom_checker.repository.SymptomRepository;

@SpringBootTest
class SymptomCheckerApplicationTests {

	@MockBean
    private SymptomRepository symptomRepository; // ✅ Mock database

	@Test
	void contextLoads() {
		System.out.println("Test environment loaded successfully!");
	}

}

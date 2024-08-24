package com.openclassrooms.Mediscreen;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.openclassrooms.Mediscreen.domain.Patient;
import com.openclassrooms.Mediscreen.repository.PatientRepository;


public class PatientRepositoryTest {
	
	@Autowired
	PatientRepository patientRepository;
	Patient patient1;
	
	@BeforeEach
	void init () {
	//	PatientRepository	patientRepository = new PatientRepository();	
	 patient1 = new Patient("Test","Tester","1995-04-25","M","123 Main St","1234567890");	
	}

	@Test
	public void testFindByFamilyName() {
		
	//	patientRepository.save(patient1);
	//	Patient patient = patientRepository.findByFamilyName("Tester");
	//	assertNotNull(patient);
	//	assertEquals(patient1.getAddress(),"123 Main St");
		
		
	}
	
}

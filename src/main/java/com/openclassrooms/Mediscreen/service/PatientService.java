package com.openclassrooms.Mediscreen.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinylog.Logger;

import com.openclassrooms.Mediscreen.domain.Patient;
import com.openclassrooms.Mediscreen.exception.DataNotFoundException;
import com.openclassrooms.Mediscreen.repository.PatientRepository;

@Service
public class PatientService {
	
	PatientRepository patientRepository;

	public PatientService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}
	
	public Iterable<Patient> findAllPatients() {
		
		return this.patientRepository.findAll();
	}
	
	public Patient addPatient(Patient patient) {
		
	//	System.out.println(patient.getDateOfBirth());
		return this.patientRepository.save(patient);
	}	
	
	public Patient getByFamilyName(String name) {
		
	//	Logger.info("GET findByFamilyName called with param name " + name);
		
		return this.patientRepository.findByFamilyName(name);
	}	
	
	public Patient getById(Long  id) {
		
		//Logger.info("GET findByFamilyName called with param name " + id);
		
		return this.patientRepository.findById(id);
	}
	
	public Patient getByFirstLast(String  givenName, 
			String  familyName) {
		
	//	Logger.info("GET getByFirstLast called with param name " + givenName + " " + familyName);
		
		
		return this.patientRepository.findByFirstLast(givenName, familyName);
	}

	public ResponseEntity<String> updatePatient(Patient patient) {
		
		try {
			Patient existPatient = getByFirstLast(patient.getGivenName(), patient.getFamilyName());
			
			
			if (existPatient != null) {
				existPatient.setDateOfBirth(patient.getDateOfBirth());
				existPatient.setPhoneNumber(patient.getPhoneNumber());
				existPatient.setAddress(patient.getAddress());
				patientRepository.save(existPatient);
				return ResponseEntity.status(HttpStatus.OK).body("Patient updated");
			} else {
			throw new DataNotFoundException("Patient not found in attempting to update");
			}
		} catch (DataNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND); 
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR); 
		}
			
	}	

}

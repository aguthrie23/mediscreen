package com.openclassrooms.Mediscreen.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


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
		
		return this.patientRepository.save(patient);
	}	
	
	public Patient getByFamilyName(String name) {
		
		
		return this.patientRepository.findByFamilyName(name);
	}	
	
	public Patient getById(Long  id) {
		
		return this.patientRepository.findById(id);
	}
	
	public Patient getByFirstLast(String  givenName, 
			String  familyName) {
		
		
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

package com.openclassrooms.Mediscreen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tinylog.Logger;

import com.openclassrooms.Mediscreen.domain.Note;
import com.openclassrooms.Mediscreen.domain.Patient;
import com.openclassrooms.Mediscreen.service.PatientHistoryService;
import com.openclassrooms.Mediscreen.service.PatientService;

@RestController
public class PatientController {
	
	PatientService patientService;
	PatientHistoryService patientHistoryService;
		
	
	public PatientController(PatientService patientService,
			PatientHistoryService patientHistoryService) {
		this.patientService = patientService;
		this.patientHistoryService = patientHistoryService;
	}
	

	// Post a Note and return Response
	@PostMapping("/patHistory/add")
	public ResponseEntity<String> addNote(@RequestBody Note note) {
		
		Logger.info("POST Add History Called");
		
		return patientHistoryService.addHistory(note);
	}
	

	// Get Patient Assessment by Family Name
	// curl -d "familyName=Tester" -X GET http://localhost:8080/assessbyfamilyname/familyName
	@RequestMapping(value="/assessbyfamilyname", method=RequestMethod.GET)
	public String assessPatientByFamilyName(@RequestParam("familyName") String familyName) {
		
		Logger.info("Assess patient by FamilyName called ");
		
		return patientHistoryService.assessPatientByFamilyName(familyName);
	}	
		
	
	
   // Get Patient Assessment By Id	
	@RequestMapping(value="/assessbyid", method=RequestMethod.GET)
	public String assessPatientById(@RequestParam("id") Long id) {
		
		Logger.info("Assess patient by Id called ");
		
		return patientHistoryService.assessPatientById(id);
	}	
	

	// Find a patients History
	@RequestMapping(value="/patient/history", method=RequestMethod.GET)
	public List<Note> findPatientHistory(@RequestParam("givenName") String  givenName, 
			@RequestParam("familyName") String  familyName) {
		
		Logger.info("GET patient history called");
			
		return this.patientHistoryService.findPatientHistory(givenName, familyName);
	}	

	// Add a Patient 
	@PostMapping("/patient")
	public Patient addPatient(@RequestBody Patient patient) {
		
		Logger.info("Post addPatient called");
		
		return this.patientService.addPatient(patient);
	}

	// Update a Patient and return Response
	@PutMapping("/patient")
	public ResponseEntity<String> updatePatient(@RequestBody Patient patient) {
		
		Logger.info("Put Update Patient called ");
		
	return	patientService.updatePatient(patient);
	}

	
	// Find List Patients by Family Name
	// http://localhost:8080/findByFamilyName?name=Jefferson
	@RequestMapping(value="/findByFamilyName", method=RequestMethod.GET)
	public Patient getByFamilyName(@RequestParam("name") String name) {
		
		Logger.info("GET findByFamilyName called with param name " + name);
		
		return this.patientService.getByFamilyName(name);
	}	

	
	// Find Patient By Id
	// http://localhost:8080/findById?id=8
	@RequestMapping(value="/findById", method=RequestMethod.GET)
	public Patient getById(@RequestParam("id") Long  id) {
		
		Logger.info("GET findById called with param id " + id);
		
		return this.patientService.getById(id);
	}
	

	// Find Patient by First and Last Name
    // http://localhost:8080/findByFirstLast?givenName=Abe&familyName=Lincoln
	@RequestMapping(value="/findByFirstLast", method=RequestMethod.GET)
	public Patient getByFirstLast(@RequestParam("givenName") String  givenName, 
			@RequestParam("familyName") String  familyName) {
		
		Logger.info("GET getByFirstLast called with param first " + givenName + " and last " + familyName);
		
		
		return this.patientService.getByFirstLast(givenName, familyName);
	}
	
	
//// TESTS TO MAKE SURE DATA PRESENT
	
    // Get All Patients
	// http://localhost:8080/patients	
	@GetMapping("/patients")
	public Iterable<Patient> findAllPatients() {
		
		Logger.info("GET findAllPatients called");
			
		return this.patientService.findAllPatients();
	}	
	
	// Get All Notes
	// http://localhost:8080/allNotes
	@GetMapping("/allNotes")
	public List<Note> getAllNotes() {
		
		Logger.info("GET All History called");
		
		return patientHistoryService.getAllNotes();
	}
	
	// Get All notes by patient id
	@RequestMapping(value="/notesbyperson", method=RequestMethod.GET)
	public List<Note> findPatientHistoryTest(@RequestParam("id") Long id) {
		
		Logger.info("GET History by person ");
		
		return patientHistoryService.findNotesByPatient(id);
	}	

}

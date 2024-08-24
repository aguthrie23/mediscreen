package com.openclassrooms.Mediscreen.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tinylog.Logger;

import com.openclassrooms.Mediscreen.domain.Note;
import com.openclassrooms.Mediscreen.domain.Patient;
import com.openclassrooms.Mediscreen.domain.TriggerTerms;
import com.openclassrooms.Mediscreen.exception.DataNotFoundException;
import com.openclassrooms.Mediscreen.repository.PatientHistoryRepository;


@Service
public class PatientHistoryService {
	
	PatientHistoryRepository patientHistoryRepository;
	PatientService patientService;
	
	public static final String NONE = "None";
	public static final String BORDERLINE= "Borderline";
	public static final String INDANGER= "In Danger";
	public static final String EARLYONSET= "Early Onset";

	@Autowired
	public PatientHistoryService(PatientHistoryRepository patientHistoryRepository,
			PatientService patientService) {
		super();
		this.patientHistoryRepository = patientHistoryRepository;
		this.patientService = patientService;
		
	}
	
	
	public ResponseEntity<String> addHistory (Note note) {
		Logger.info("Adding History Service");
		
		try {
			Patient patient = patientService.getById(note.getPatientFk());
			
			if (patient != null) {
				patientHistoryRepository.save(note);
				
			} else {
				throw new DataNotFoundException("Patient not found in adding History " + note.getPatientFk());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Note Added", HttpStatus.OK);
		
		
		
	}
	
	public List<Note> getAllNotes() {
		return patientHistoryRepository.findAll();
	}
	
	public List<Note> findNotesByPatient(Long id) {
		return patientHistoryRepository.findNoteByPatientfk(id);
			
	}
	
	public List<Note> findPatientHistory(String  givenName, 
			String  familyName) {
		
		Logger.info("Finding Patient History");
		
		Patient patientRet = patientService.getByFirstLast(givenName, familyName);
		
		if (patientRet != null) {
			return findNotesByPatient(patientRet.getId());
		}
		
		return null;
	}


	public String assessPatientById(Long id)  {
		Integer age = 0;
		Integer count = 0;
		String status = null;	
		String retMessage = null;

		try {
	
			Patient patient = patientService.getById(id);
			
			if (patient != null) {
	         age = getAgeByDateOfBirth(patient.getDateOfBirth());
			List <Note> retNotes = findNotesByPatient(id);
			count = getCountTriggerTerms(retNotes);	
			Logger.debug("count terms " + count);
			}
			else {
				throw new DataNotFoundException("Patient not found in assessing by id " + id);
			}

			
			status = getStatusByCountAgeSex(count, age, patient.getSex());
			retMessage = "Patient: " + patient.getGivenName() + " " + patient.getFamilyName() 
					+ "(age " + age + ") diabetes assessment is: " 
					+ status;
			
			return retMessage;
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Integer getAgeByDateOfBirth(String DOB) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(DOB, formatter);
		int age = Period.between(date, LocalDate.now()).getYears();
		return age;
	}

	// get the status by count, age, and gender
	private String getStatusByCountAgeSex (Integer count, Integer age, String gender) {

		String status = NONE;
		switch (count) {
		case 0: case 1: status=NONE;
		break;
		case 2:
			if (age > 30)
				status=BORDERLINE;
			else
			    status=NONE;
			break;
		case 3:	
			if (age < 30 && gender.equals("M")) 			
				status=INDANGER;
			break;
		case 4:
			if (age < 30 && gender.equals("F"))
		       status=INDANGER;
			break;
		case 5:
			if (age < 30 && gender.equals("M"))
				status=EARLYONSET;
			break;
		case 6:
			if (age > 30) 
				status=INDANGER;
			break;
		case 7: 
			if (age < 30 && gender.equals("F"))
				status=EARLYONSET;
			break;
		case 8:
			if (age > 30) 
			status=EARLYONSET;
			break;
		default:
			status=EARLYONSET;
			break;		
		}
		
		return status;
		
	}

	// count the number of trigger terms
	private Integer getCountTriggerTerms(List<Note> retNotes) {
		int count=0;
		List <String> triggerTermList = TriggerTerms.getTriggerterms();
	   //	outerLoop:
		for (Note note : retNotes) {
		  for (String termsString : triggerTermList) {
			if (note.getMessage().toLowerCase().contains(termsString.toLowerCase())) {
				count++;
				Logger.debug("note qualifies with Term " +  termsString.toLowerCase() + 
						" full note message " + note.getMessage());
				//break;
				// continue outerLoop;
			}
		}
		}
		return count;
	}


	public String assessPatientByFamilyName(String familyName) {
		// TODO Auto-generated method stub
		Patient patient = patientService.getByFamilyName(familyName);
		
		try {
			if (patient != null) {
				return assessPatientById(patient.getId());
			} else {
				throw new DataNotFoundException("patient not found by familyName");
			}
		} catch (DataNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	

}

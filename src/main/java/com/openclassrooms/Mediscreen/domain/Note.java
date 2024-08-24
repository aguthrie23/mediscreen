package com.openclassrooms.Mediscreen.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Document("notes")
public class Note {
	
	@Id
	private String id;
	
	private Long patientFk;
	private String message;
	
	public Note(//String id, 
			Long  patientFk, String message) {
		super();
		//this.id = id;
		this.patientFk = patientFk;
		this.message = message;
	}

	public String getId() {
		return id;
	}

	public Long getPatientFk() {
		return patientFk;
	}

	public String getMessage() {
		return message;
	}

	public void setPatientFk(Long patientFk) {
		this.patientFk = patientFk;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}

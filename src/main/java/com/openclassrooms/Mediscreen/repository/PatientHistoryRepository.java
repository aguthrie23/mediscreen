package com.openclassrooms.Mediscreen.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.openclassrooms.Mediscreen.domain.Note;

public interface PatientHistoryRepository extends MongoRepository<Note, String> {
	
	@Query("{ patientFk: ?0 }")
	List<Note> findNoteByPatientfk (Long patientFk);

}

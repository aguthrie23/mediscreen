package com.openclassrooms.Mediscreen.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.Mediscreen.domain.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>, 
JpaRepository<Patient, Integer>{
	
	Patient findByFamilyName(String familyName);
	
	Patient findById(Long id);
	
	@Query("select p from Patient p where p.givenName = ?1 and p.familyName = ?2")
	Patient findByFirstLast(String givenName, String familyName);

	

}

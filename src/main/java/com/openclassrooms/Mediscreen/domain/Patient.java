package com.openclassrooms.Mediscreen.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
public class Patient {
	
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name="First_Name")
    private String givenName;
	
	@Column(name="Last_Name")
	private String familyName;
	
	@Column(name="Birth_Date")
	private String dateOfBirth;
	
	@Column(name="Gender")
	private String sex;

	private String address;
	private String phoneNumber;
	

	// hibernate expects a no arg constructor but it doesnt have to be public
	private Patient() {
	}
	

	public Patient(String givenName, String familyName, String dateOfBirth, String sex, String address,
			String phoneNumber) {
		this.givenName = givenName;
		this.familyName = familyName;
		this.dateOfBirth = dateOfBirth;
		this.sex = sex;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}



	public Long getId() {
		return id;
	}


	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}


	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}


	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getGivenName() {
		return givenName;
	}


	public String getFamilyName() {
		return familyName;
	}

	
	public String getAddress() {
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getSex() {
		return sex;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}



	
	

}

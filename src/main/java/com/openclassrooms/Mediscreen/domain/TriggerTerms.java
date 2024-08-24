package com.openclassrooms.Mediscreen.domain;

import java.util.List;

public class TriggerTerms {
	
	public static final List <String> triggerTerms = 
    List.of
    ("Hemoglobin A1C","Microalbumin","Body Height","Body Weight","Smoker",
    "Abnormal","Cholesterol","Dizziness","Relapse","Reaction","Antibodies");
	
	public static List<String> getTriggerterms() {
		return triggerTerms;
	}

}

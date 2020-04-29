package com.bloodorganmanagementsystem.app.dto;

import com.bloodorganmanagementsystem.app.entities.Blood;
import com.bloodorganmanagementsystem.app.entities.BodyTest;
import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;

import java.time.LocalDate;

import java.util.List;

import lombok.Data;

@Data
public class IndividualProfileToShow {
   
	Gender gender;
    LocalDate dateOfBirth, DateOfLastUpdate;
    Integer height;
    Integer weight;
    Blood blood;
    List<DonationPreference> donationPreferences;
    List<BodyTest>tests;
    
	
    
}
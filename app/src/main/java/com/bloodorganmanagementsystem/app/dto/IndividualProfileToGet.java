package com.bloodorganmanagementsystem.app.dto;

import java.util.List;

import com.bloodorganmanagementsystem.app.entities.Blood;
import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;

import lombok.Data;

@Data
public class IndividualProfileToGet {
	Gender gender;
    Integer height;
    Integer weight;
    Blood blood;
    List<DonationPreference> donationPreferences;
}

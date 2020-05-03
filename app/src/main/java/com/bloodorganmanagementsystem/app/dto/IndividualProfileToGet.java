package com.bloodorganmanagementsystem.app.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.bloodorganmanagementsystem.app.entities.Blood;
import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;

import lombok.Data;

@Data
public class IndividualProfileToGet {
    @NotNull(message="Gender cannot be null")
    Gender gender;
    @NotNull (message="Height cannot be null")
    Integer height;
    @NotNull (message="Weight cannot be null")
    Integer weight;
    @NotNull (message="Blood cannot be null")
    Blood blood;
    @NotNull (message="preferences cannot be null")
    List<DonationPreference> donationPreferences;
}

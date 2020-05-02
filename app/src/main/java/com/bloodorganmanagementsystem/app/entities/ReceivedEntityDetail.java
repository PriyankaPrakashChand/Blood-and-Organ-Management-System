package com.bloodorganmanagementsystem.app.entities;

import java.time.LocalDate;
import java.util.Optional;

import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedEntityDetail {
	public enum rState {
		NULL, WAITING_TO_BE_RECEIVED, HAS_BEEN_RECEIVED
	}
	public enum DonorType {
		NULL, INDIVIDUAL, ORGANIZATION
	}
	
	private EntityName entityName;
	private String DonorId;
	private rState stateOfEntity;
	private LocalDate dateOfReceivingDonation;
	private DonorType DonorType; // did the org receive from another org or an individual
	private Optional<BloodTypeQty> bloodDetail;
	
	


	
}
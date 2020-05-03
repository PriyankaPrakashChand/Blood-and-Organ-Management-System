package com.bloodorganmanagementsystem.app.entities;

import java.time.LocalDate;

import java.util.Optional;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DonationEntityDetail {
	public enum EntityName { // or type
		NULL, BLOOD, BONE_MARROW, EYES, HEART, KIDNEY, LIVER
	}

	public enum dState {
		NULL, AVAILABLE_TO_DONATE, HAS_BEEN_DONATED
	}

	public enum ReceiverType {
		NULL, INDIVIDUAL, ORGANIZATION
	}
	private EntityName entityName;
	private dState state;  // stateof donation gets updated
	private String receiverId; // HealthOrganization ID always- only organizations can receive
	private LocalDate dateOfDonation;
	private ReceiverType receiverType; // whether it is an organization or an individual
	private Optional<BloodTypeQty> bloodDetail;
}
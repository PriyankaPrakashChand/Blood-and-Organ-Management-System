package com.bloodorganmanagementsystem.app.entities;

import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.Role;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.Data;


import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



@Document(collection = "INDIVIDUAL_COLLECTION")
@Data
@Component
public class Individual {
	public static Integer idGenerator = 0;

	public enum DonationPreference {
		NULL, BLOOD, BONE_MARROW, EYES, HEART, KIDNEY, LIVER
	}

	public enum Gender {
		NULL, MALE, FEMALE
	}

	// public enum BloodType {
	// NULL, A_POSITIVE, A_NEGATIVE, B_NEGATIVE, B_POSITIVE, O_POSITIVE, O_NEGATIVE,
	// AB_POSITIVE, AB_NEGATIVE
	// }

	@Id
	String id;
	@PartitionKey
	private String email; // unique
	private String password;
	private String appliedLicenseKey;
	private MemberDetail memeberDetails;
	private LocalDate birthday;
	private String firstName;
	private String lastName;
	private Gender gender;
	private Integer heightCm;
	private Integer weightKg;
	private Blood bloodDetails;
	private List<BodyTest> testDetails;
	private List<DonationPreference> donationPreference;

	// Details to be filed while Entity has been donated
	private List<DonationEntityDetail> donationEntityDetails;
	private static Role role = Role.IND;

	/**
	 * NoArgsConstructor
	 */
	public Individual() {

		this.id = "I" + Integer.toString(++idGenerator);
		this.password = new String(""); // F:should I keep it like this?
		this.email = UUID.randomUUID().toString();
		this.memeberDetails = new MemberDetail("", "", "", "", "");
		this.bloodDetails = new Blood(BloodType.NULL, 20, 20,0);
		this.appliedLicenseKey = this.lastName = this.firstName = "";
		this.birthday = LocalDate.now();
		List<DonationPreference> dP = new ArrayList<DonationPreference>();
		dP.add(DonationPreference.NULL);
		this.donationPreference = dP;
		this.gender = Gender.NULL;
		this.heightCm = -1;
		this.weightKg = -1;
		List<BodyTest> testList = new ArrayList<BodyTest>();
		this.testDetails = testList;
		DonationEntityDetail dE = new DonationEntityDetail();
		List<DonationEntityDetail> dEs = new ArrayList<DonationEntityDetail>();
		this.donationEntityDetails = dEs;

	}

	/**
	 * *****************************List Functions********************************
	 */
	public void addTest(BodyTest test) {
		testDetails.add(test);
	}

	public void removeTest(BodyTest test) {
		testDetails.remove(test);
	}

	public void addDonationPreference(DonationPreference donationPreference) {
		this.donationPreference.add(donationPreference);
	}

	public void removeDonationPreference(DonationPreference donationPreference) {
		this.donationPreference.remove(donationPreference);
	}

	public void addDonationEntityDetails(DonationEntityDetail donationEntityDetails) {
		this.donationEntityDetails.add(donationEntityDetails);
	}

	public void removeDonationEntityDetails(DonationEntityDetail donationEntityDetails) {
		this.donationEntityDetails.remove(donationEntityDetails);
	}

}
package com.bloodorganmanagementsystem.app.entities;


import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyTest {
	public enum TestName {

		NULL, ANTIBODY, BLOOD, BONEMARROW, CANCER, CHEST, COLONSCOPY, CROSSMATCH, CT, DOPPLER, ECHOCARDIOGRAM, EKG,
		ELECTROCARDIOGRAM, GYENCOLOGICAL, HEPATITIS_B, HEPATITIS_C, HIV, HLA, HTIV, PET, PHYSICAL, PULMONARY, SYPHILIS,
		URINE
	}

	private TestName testName;
	private boolean hasPassed;
	private LocalDate dateOFLastUpdate;

}
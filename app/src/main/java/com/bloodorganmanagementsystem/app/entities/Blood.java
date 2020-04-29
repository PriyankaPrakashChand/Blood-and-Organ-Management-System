package com.bloodorganmanagementsystem.app.entities;

import java.math.BigDecimal;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor

public class Blood {

	public enum BloodType {
		NULL,A_POSITIVE, A_NEGATIVE, B_NEGATIVE, B_POSITIVE, O_POSITIVE, O_NEGATIVE, AB_POSITIVE, AB_NEGATIVE
	}
	private BloodType bloodType; // what is the bloodtype
	private int bloodUnitsDonated; // how much has already been donated
	private int bloodUnitsAvailable; // howmuch they can still donate- or when an organization adds to blood to donate
	private int bloodUnitsRequired; // howmuch they can still donate - or when an organization adds blood to receive.
	


}
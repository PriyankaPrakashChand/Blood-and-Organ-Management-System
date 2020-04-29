package com.bloodorganmanagementsystem.app.entities;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the MEMBER database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberDetail {
	private String address;

	private String city;

	private String country;

	private String password; 

	private String phoneNumber; // unique

}
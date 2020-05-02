package com.bloodorganmanagementsystem.app.entities;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

	@NotNull
	private String address;

	@NotNull
	private String city;

	@NotNull
	private String country;

	@NotNull
	@Size(min=8, max=17, message="Password must be be between 8 to 16 charecters")
	private String password;

	@NotNull
	private String phoneNumber; // unique

}
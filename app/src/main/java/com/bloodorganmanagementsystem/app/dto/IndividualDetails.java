package com.bloodorganmanagementsystem.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class IndividualDetails extends MemberDetails {
    String firstName;
    String lastName;
    String licenceKey; //email
    String email; // email
    Date dateOfBirth;
    String bloodType;
    String address;
    String city;
    String country;
    String password;
    String phoneNumber; // unique

}
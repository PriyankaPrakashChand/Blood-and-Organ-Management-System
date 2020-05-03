package com.bloodorganmanagementsystem.app.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.bloodorganmanagementsystem.app.entities.MemberDetail;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndividualDetails  {
    @NotNull (message= "firstname cannot be null")
    String firstName;
    @NotNull (message= "Lastname cannot be null")
    String lastName;
    @NotNull (message= "licence Key cannot be null")
    String licenceKey;

    @NotNull(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    String email; // email

    @NotNull (message= "DOB cannot be null")
    @Past (message="DOB must be in the past")
    LocalDate dateOfBirth;
    
    @NotNull (message="member details must not be empty")
    MemberDetail memberDetails;

}
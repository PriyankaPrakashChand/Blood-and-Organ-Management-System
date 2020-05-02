package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.bloodorganmanagementsystem.app.entities.MemberDetail;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;

import lombok.Data;

@Data
public class OrgRegisterationDetails {
    @NotNull(message = " name cannot be empty")
    String orgName;

    @NotNull(message = "email cannot be empty")
    @Email(message = "Invalid email format")
    String email;

    @NotNull (message="Organization Interest must be BOTH/DONATE/RECEIVE")
    OrganizationInterest organizationInterest;


    @NotNull (message="member details must not be empty")
    MemberDetail memberDetails;

}
package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import com.bloodorganmanagementsystem.app.entities.MemberDetail;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;

import lombok.Data;

@Data
public class OrgRegisterationDetails {
    String orgName;
    String email;
    OrganizationInterest organizationInterest;
    MemberDetail memberDetails;
    
    
}
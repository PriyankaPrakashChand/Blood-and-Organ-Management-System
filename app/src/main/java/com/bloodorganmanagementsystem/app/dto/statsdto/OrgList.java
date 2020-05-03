package com.bloodorganmanagementsystem.app.dto.statsdto;

import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrgList {
   String OrgName;
   OrganizationInterest orgInterest;
}
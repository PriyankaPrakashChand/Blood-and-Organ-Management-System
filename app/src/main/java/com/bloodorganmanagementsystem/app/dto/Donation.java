package com.bloodorganmanagementsystem.app.dto;

import java.time.LocalDate;


import com.bloodorganmanagementsystem.app.entities.BloodTypeQty;

import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;

import lombok.Data;

@Data
public class Donation {

    EntityName entityName;
    LocalDate dateOfDonation;
    String recipientName;
    BloodTypeQty bloodDetails;
}

package com.bloodorganmanagementsystem.app.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Donation {

    String entityName;
    Date dateOfDonation;
    String recipientName;
}

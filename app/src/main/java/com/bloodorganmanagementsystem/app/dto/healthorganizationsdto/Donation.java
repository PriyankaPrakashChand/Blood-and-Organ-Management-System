package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import javax.validation.constraints.NotNull;

import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Donation {
    @NotNull(message = "Entity name cannot be null")
    EntityName entityName;

    @NotNull(message = "Receiver id cannot be null")
    String ReceiverId;
}
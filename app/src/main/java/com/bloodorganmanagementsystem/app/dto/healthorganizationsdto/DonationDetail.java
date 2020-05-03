package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
import com.bloodorganmanagementsystem.app.entities.BloodTypeQty;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DonationDetail {
    @NotNull(message = "Entity name cannot be null")
   EntityName entityName;
   Optional<BloodTypeQty> bloodDetail;

   public DonationDetail(EntityName entityName) {
       this.entityName = entityName;
   }

   public DonationDetail(@NotNull(message = "Entity name cannot be null") EntityName entityName,
           BloodTypeQty bloodDetail) {
       this.entityName = entityName;
       this.bloodDetail = Optional.of(bloodDetail);
   }


   
}
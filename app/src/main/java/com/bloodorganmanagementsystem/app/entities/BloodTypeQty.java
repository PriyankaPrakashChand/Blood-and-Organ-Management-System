package com.bloodorganmanagementsystem.app.entities;

import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BloodTypeQty {
    Integer Qty;
    BloodType bloodType;

}
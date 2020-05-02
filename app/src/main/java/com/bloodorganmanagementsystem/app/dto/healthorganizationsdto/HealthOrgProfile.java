package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class HealthOrgProfile {

    String id;
    String name;
    String email;
    String licenceKey;
    String address;

}




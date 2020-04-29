package com.bloodorganmanagementsystem.app.dto;

import lombok.Data;

@Data
public abstract class MemberDetails {
    String email;
    String password;
    String phoneNumber;
    String  address;
    String city;
    String Country;
    char memberType;
    int activityId;
    

}
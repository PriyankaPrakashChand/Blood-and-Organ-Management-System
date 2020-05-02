package com.bloodorganmanagementsystem.app.dto.healthorganizationsdto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class OrgLogin {
    
    @NotNull (message="Email cannot be null")
    @Email(message=" Invalid Email Format")
    String email;

    @NotNull
    String password;
}
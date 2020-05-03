package com.bloodorganmanagementsystem.app.dto;

import javax.validation.constraints.NotNull;

import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BodyTestDetails {
    @NotNull(message="Gender cannot be null")
    TestName testName;
    @NotNull(message=" Test Results must not be null")
	 boolean hasPassed;
	
    
}
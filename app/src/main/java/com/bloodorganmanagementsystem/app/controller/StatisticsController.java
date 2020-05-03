package com.bloodorganmanagementsystem.app.controller;

import java.time.LocalDate;

import com.bloodorganmanagementsystem.app.dto.BodyTestDetails;
import com.bloodorganmanagementsystem.app.dto.EntityStatistic;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.Donation;
import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class StatisticsController {

    @GetMapping()
    public Donation  test(){
        return new Donation(EntityName.HEART,"iii");
        
    }
    

}

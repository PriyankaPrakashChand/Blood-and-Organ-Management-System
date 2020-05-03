package com.bloodorganmanagementsystem.app.controller;

import java.time.LocalDate;
import java.util.List;

import com.bloodorganmanagementsystem.app.dto.BodyTestDetails;
import com.bloodorganmanagementsystem.app.dto.EntityStatistic;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.Donation;
import com.bloodorganmanagementsystem.app.dto.statsdto.OrgList;
import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
import com.bloodorganmanagementsystem.app.service.StatsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class StatisticsController {
    private final StatsService statsSer;

	@Autowired
	public StatisticsController(StatsService statsSer) {
		this.statsSer = statsSer;
	}
    @GetMapping()
    public List<OrgList>  test(){
        return statsSer.getOrgDetails();
    }
    

}

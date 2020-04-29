package com.bloodorganmanagementsystem.app.controller;

import com.bloodorganmanagementsystem.app.dto.EntityStatistic;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Stats")
public class StatisticsController {

    @RequestMapping("/{entityName}")
    public Integer getTotalDonationCount(@PathVariable String entityName) {
        return 50;
    }

    @RequestMapping("/Entity/{entityName}")
    public EntityStatistic viewEntityStats(@PathVariable String entityName) {
        return new EntityStatistic("Donations till Date", 50);

    }

}

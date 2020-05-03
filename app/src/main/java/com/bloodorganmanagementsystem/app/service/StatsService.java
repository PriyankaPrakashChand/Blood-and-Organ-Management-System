package com.bloodorganmanagementsystem.app.service;

import java.util.List;

import com.bloodorganmanagementsystem.app.dto.statsdto.OrgList;

public interface StatsService {
    List<OrgList> getOrgDetails();
    
}
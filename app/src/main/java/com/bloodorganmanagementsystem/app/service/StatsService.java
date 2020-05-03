package com.bloodorganmanagementsystem.app.service;

import java.util.List;

import com.bloodorganmanagementsystem.app.dto.statsdto.OrgList;

import org.springframework.stereotype.Service;

@Service
public interface StatsService {
    List<OrgList> getOrgDetails();
    
}
package com.bloodorganmanagementsystem.app.service;

import java.util.ArrayList;
import java.util.List;

import com.bloodorganmanagementsystem.app.dto.statsdto.OrgList;
import com.bloodorganmanagementsystem.app.repository.HealthOrganizationRepository;
import com.bloodorganmanagementsystem.app.repository.IndividualRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class StatsServiceImplementation implements StatsService {
    IndividualRepository indRepos;
    HealthOrganizationRepository orgRepos;

    /**
     * Public Constructor
     * 
     * @param indRepos
     * @param orgRepos
     */
    @Autowired
    public StatsServiceImplementation(IndividualRepository indRepos,
            HealthOrganizationRepository orgRepos) {
        this.indRepos = indRepos;
        this.orgRepos = orgRepos;
    }

    @Override
    public List<OrgList> getOrgDetails() {
        try { // TODO Auto-generated method stub
            List<OrgList> list = new ArrayList<OrgList>();
            orgRepos.findAll().forEach(o-> list.add(new OrgList(o.getOrgName(),o.getOrganizationInterest())));
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
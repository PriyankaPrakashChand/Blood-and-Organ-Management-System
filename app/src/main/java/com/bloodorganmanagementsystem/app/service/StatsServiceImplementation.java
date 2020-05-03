package com.bloodorganmanagementsystem.app.service;

import java.util.ArrayList;
import java.util.List;

import com.bloodorganmanagementsystem.app.dto.statsdto.OrgList;

public class StatsServiceImplementation implements StatsService {

    @Override
    public List<OrgList> getOrgDetails() {
        try { // TODO Auto-generated method stub
            List<OrgList> list = new ArrayList<OrgList>();
            
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

}
package com.bloodorganmanagementsystem.app.service;

import java.util.List;
import java.util.Optional;

import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.Donation;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.DonationDetail;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.HealthOrgProfile;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.IndividualToShow;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgRegisterationDetails;
import com.bloodorganmanagementsystem.app.entities.Blood;

import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.service.exception.AppException;

import org.springframework.stereotype.Service;

@Service
public interface HealthOrganizationService {
 
 
    public Optional<HealthOrganization> findById(String findId); 

    public boolean Register(OrgRegisterationDetails orgRegisterationDetails) throws AppException;

    public boolean Login(String email, String password)throws AppException;

    
    public HealthOrgProfile viewMyProfile(String id) throws AppException;  


    public List<IndividualToShow> viewMyDonors(String id) throws AppException;

    public boolean addEntityToDonate(DonationDetail donationDetails,String healthOrgId) throws AppException;

    public boolean addEntityToReceive(DonationDetail requestDetails,String healthOrgId) throws AppException;

    public boolean donateOrgan(Donation donation,String healthOrgId) throws AppException; 

    public boolean donateBlood(Blood blood,String healthOrgId); // updates list of bloods + donation entitye details

    public boolean receiveBlood(Blood blood,String healthOrgId); // updates list of bloods + receiver entity details of the heath org + donor entity details of the donor


       

        

    
    
}

// modify entity- should be done while donating
// view all entites  returns a list of entities with name, quantity, and status
// request donation- might not need this 
// view donation by filter -- not doing this
// request partner
// add partner
//view partners
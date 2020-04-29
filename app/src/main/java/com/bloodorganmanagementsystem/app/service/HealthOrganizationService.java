package com.bloodorganmanagementsystem.app.service;

import java.util.List;
import java.util.Optional;

import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.IndividualToShow;
import com.bloodorganmanagementsystem.app.entities.Blood;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail;

import org.springframework.stereotype.Service;

@Service
public interface HealthOrganizationService {
 
 
    public Optional<HealthOrganization> findById(String findId); 

    public boolean Register(HealthOrganization healthOrganization) throws AppException;

    public boolean Login(String email, String password)throws AppException;

    
    public String viewMyLicenseKey(String id) throws AppException;  


    public List<IndividualToShow> viewMyDonors(String id) throws AppException;

   

    public boolean addOrganToDonate(DonationEntityDetail EntityDonationDetails,String healthOrgId) throws AppException;


    public boolean addOrganToReceive(ReceivedEntityDetail receivedEntityDetails,String healthOrgId) throws AppException;

    public boolean donateOrgan(DonationEntityDetail EntityDonationDetails,String healthOrgId); 

    public boolean donateBlood(Blood blood,String healthOrgId); // updates list of bloods + donation entitye details

    public boolean recevieOrgan(ReceivedEntityDetail receivedEntityDetails); // updates donated entitiy detials 

    public boolean receiveBlood(Blood blood,String healthOrgId); // updates list of bloods + receiver entity details of the heath org + donor entity details of the donor


       

        

    
    
}

// modify entity- should be done while donating
// view all entites  returns a list of entities with name, quantity, and status
// request donation- might not need this 
// view donation by filter -- not doing this
// request partner
// add partner
//view partners
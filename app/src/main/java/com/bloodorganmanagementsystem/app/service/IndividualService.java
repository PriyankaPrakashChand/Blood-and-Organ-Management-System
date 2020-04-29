package com.bloodorganmanagementsystem.app.service;

import java.util.List;
import java.util.Optional;

import com.bloodorganmanagementsystem.app.dto.Donation;
import com.bloodorganmanagementsystem.app.dto.DonationFromIndividual;
import com.bloodorganmanagementsystem.app.dto.IndividualDetails;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToGet;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToShow;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.BodyTest;

import org.springframework.stereotype.Service;

@Service
public interface IndividualService {

    public Optional<Individual> findById(String findId);

    public boolean Register(Individual individual) throws AppException;

    public boolean Login(String email, String password)throws AppException;

    public boolean addTest(String individualId, BodyTest test, String licenseKey) throws AppException;

    /**
     * 
     * @param individualId
     * @param profile- blood, physical features, Donation preferences
     * @param licenseKey
     * @return
     * @throws AppException
     */
    public boolean AddProfile(String individualId, IndividualProfileToGet profile,String licenseKey) throws AppException;

    public boolean Donate(DonationFromIndividual donationFromIndividual, String individualId, String licenceKey) throws AppException;

    public List<Donation> viewDonations(String individualId) throws AppException;

    public IndividualProfileToShow viewProfile(String individualId) throws AppException;

    // view requests


}
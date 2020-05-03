package com.bloodorganmanagementsystem.app.service;

import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.dState;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;


import com.bloodorganmanagementsystem.app.dto.BodyTestDetails;
import com.bloodorganmanagementsystem.app.dto.Donation;
import com.bloodorganmanagementsystem.app.dto.DonationFromIndividual;
import com.bloodorganmanagementsystem.app.dto.IndividualDetails;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToGet;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToShow;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;
import com.bloodorganmanagementsystem.app.entities.BloodTypeQty;
import com.bloodorganmanagementsystem.app.entities.BodyTest;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail;
import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.repository.HealthOrganizationRepository;
import com.bloodorganmanagementsystem.app.repository.IndividualRepository;
import com.bloodorganmanagementsystem.app.service.exception.AppException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;


@Service
@Data
public class IndividualServiceImplementation implements IndividualService {

    IndividualRepository indRepos;
    HealthOrganizationRepository orgRepos;

    final int minimumHeight = 25;
    final int minimumWeight = 45;

    /**
     * Public Constructor
     * 
     * @param indRepos
     * @param orgRepos
     */
    @Autowired
    public IndividualServiceImplementation(IndividualRepository indRepos, HealthOrganizationRepository orgRepos) {
        this.indRepos = indRepos;
        this.orgRepos = orgRepos;
    }

    @Override
    public Optional<Individual> findById(String findId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String Register(IndividualDetails detail) throws AppException {
        // 1-check if email is unique
        List<Individual> dbIndividualList = indRepos.findByEmail(detail.getEmail());
        if (!dbIndividualList.isEmpty()) {
            throw new AppException("Email Id Already Exists");
        }
        // 2-check that license key exists
        List<HealthOrganization> dBOrganizationList = orgRepos.findByLisenceKey(detail.getLicenceKey());
        if (dBOrganizationList.isEmpty()) {
            throw new AppException("Invalid licence Key Applied");
        }

        // 3-check that patient is between 18-65 years in age
        if (LocalDate.now().getYear()- detail.getDateOfBirth().getYear()< 18||LocalDate.now().getYear()- detail.getDateOfBirth().getYear()>65 )
        {
            throw new AppException("Only people of ages 18 - 65 can register!");
        }
       
        // 4-check that firstname,lasnameand address,city and country is not
        // empty

        if (detail.getFirstName().isBlank() || detail.getMemberDetails().getAddress().isBlank()
                || detail.getMemberDetails().getCity().isBlank() || detail.getMemberDetails().getCountry().isBlank()
                || detail.getMemberDetails().getPhoneNumber().isBlank()) {
            throw new AppException("No fields must be Left Incomplete");

        }

        // 5- create an individual and save individual to repository
        Individual ind = new Individual();
        ind.setFirstName(detail.getFirstName());
        ind.setLastName(detail.getLastName());
        ind.setBirthday(detail.getDateOfBirth());
        ind.setMemeberDetails(detail.getMemberDetails());
        ind.setAppliedLicenseKey(detail.getLicenceKey());
        ind.setEmail(detail.getEmail());

        ind = indRepos.save(ind);

        return ind.getId();
    }

    @Override
    public boolean Login(String email, String password) throws AppException {
        // TODO Auto-generated method stub

        // 1- check if email exists
        try {
            List<Individual> dbIndividualList = indRepos.findByEmail(email);

            if (dbIndividualList.isEmpty()) {
                throw new AppException("Individual does not exist. Email not found.");
            }

            Individual individual = dbIndividualList.get(0);

            // 2- check if password matches
            String indPassword = individual.getMemeberDetails().getPassword();
            if (!indPassword.equals(password)) {
                throw new AppException("Incorrect Password.");
            }

            return true;

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;

        }
    }

    @Override
    public List<Donation> viewDonations(String individualId) throws AppException {
        try {// TODO Auto-generated method stub
            final Optional<Individual> dbIndividualList = indRepos.findById(individualId);
            if (dbIndividualList.isEmpty()) {
                throw new AppException("Individual does not exist");
            }
            Individual individual = dbIndividualList.get();
            List<Donation> list = new ArrayList<Donation>();
            Donation donation = new Donation();
            donation.setBloodDetails(new BloodTypeQty(0, individual.getBloodDetails().getBloodType()));

            // first append without blood entity
            List<DonationEntityDetail> dedList = individual.getDonationEntityDetails().stream().filter(
                    d -> d.getState().equals(dState.HAS_BEEN_DONATED) && !d.getEntityName().equals(EntityName.BLOOD))
                    .collect(Collectors.toList());

            for (DonationEntityDetail ded : dedList) {
                donation.setEntityName(ded.getEntityName());
                donation.setDateOfDonation(ded.getDateOfDonation());
                donation.setRecipientName(orgRepos.findById(ded.getReceiverId()).get().getOrgName());
                list.add(donation);
            }
            // first append with blood entity
            List<DonationEntityDetail> dedBList = individual.getDonationEntityDetails().stream().filter(
                    d -> d.getState().equals(dState.HAS_BEEN_DONATED) && d.getEntityName().equals(EntityName.BLOOD))
                    .collect(Collectors.toList());

            for (DonationEntityDetail ded : dedBList) {
                donation.setEntityName(EntityName.BLOOD);
                donation.setDateOfDonation(ded.getDateOfDonation());
                donation.setRecipientName(orgRepos.findById(ded.getReceiverId()).get().getOrgName());
                donation.getBloodDetails().setQty(ded.getBloodDetail().get().getQty());
                list.add(donation);
            }

            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;

        }

    }

    /**
     * @param individualId
     * @return Profile
     * @throws AppException
     */
    @Override
    public IndividualProfileToShow viewProfile(final String individualId) throws AppException {
        try {
            final Optional<Individual> dbIndividualList = indRepos.findById(individualId);
            if (dbIndividualList.isEmpty()) {
                throw new AppException("Individual does not exist");
            }
            Individual individual = dbIndividualList.get();
            IndividualProfileToShow profile = new IndividualProfileToShow();
            profile.setBlood(individual.getBloodDetails());
            profile.setDateOfBirth(individual.getBirthday());
            profile.setGender(individual.getGender());
            profile.setHeight(individual.getHeightCm());
            profile.setWeight(individual.getWeightKg());
            profile.setDonationPreferences(individual.getDonationPreference());
            profile.setTests(individual.getTestDetails());
            profile.setDateOfLastUpdate(individual.getDataOFLastProfileUpdate());
            return profile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean addTest(String individualId, BodyTestDetails detail, String licenseKey) throws AppException {
        // TODO Auto-generated method stub

        try {
            // 1- check ind id exist (optional ind isPresent() from repository)
            Optional<Individual> dbIndividual = indRepos.findById(individualId);

            // 2- check ifEmpty() -> return error app exception class
            if (dbIndividual.isEmpty()) {
                throw new AppException("Individual does not exist");
            }

            Individual individual = dbIndividual.get();

            // 3- license key should be equal to ind previously set key
            if (!individual.getAppliedLicenseKey().equals(licenseKey)) {
                throw new AppException("Individual not valid");
            }

            // 4- check test name is not null
            if (detail == null || detail.getTestName().equals(TestName.NULL)) {
                throw new AppException("Cannot have empty test");
            }

            // 5- date of last update -> create new date.
            BodyTest test = new BodyTest(detail.getTestName(), detail.isHasPassed(), LocalDate.now());

            // 6- Add test to individual
            individual.addTest(test);

            // 7- Update database
            individual = indRepos.save(individual);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;

        }
    }

    @Override
    public boolean AddProfile(String individualId, IndividualProfileToGet profile, String licenseKey)
            throws AppException {

        // TODO Auto-generated method stub

        // 1- check Id exists
        try {

            Optional<Individual> dbIndividual = indRepos.findById(individualId);

            if (dbIndividual.isEmpty()) {
                throw new AppException("Individual does not exist");
            }

            Individual individual = dbIndividual.get();

            // 2- license key
            List<HealthOrganization> dBOrganizationList = orgRepos.findByLisenceKey(licenseKey);
            if (dBOrganizationList.isEmpty()) {
                // throw new AppException("Invalid licence Key Applied");
            }

            // 3- validate gender
            if (profile.getGender() == Gender.NULL) {
                throw new AppException("Gender can not be null");
            }

            // 4- validate blood
            if (profile.getBlood().getBloodType() == BloodType.NULL) {
                throw new AppException("blood type cannot be null");
            }

            if (profile.getBlood().getBloodUnitsAvailable() < 0 || profile.getBlood().getBloodUnitsDonated() < 0) {
                throw new AppException("blood units invalid");
            }

            // 5-validate height and weight
            if (profile.getHeight() < minimumHeight || profile.getWeight() < minimumWeight) {
                throw new AppException("Height/Weight not allowed");
            }

            // 6- validate donation preference
            if (!profile.getDonationPreferences().isEmpty()) {
                List<DonationPreference> preferences = profile.getDonationPreferences();
                for (DonationPreference preference : preferences) {
                    if (preference == DonationPreference.NULL) {
                        throw new AppException("preference cannot be NULL");
                    }
                }
            }

            individual.setGender(profile.getGender());
            profile.getBlood().setBloodUnitsRequired(0);
            individual.setBloodDetails(profile.getBlood());
            individual.setHeightCm(profile.getHeight());
            individual.setWeightKg(profile.getWeight());
            individual.setDonationPreference(individual.getDonationPreference());

            // Update database
            individual = indRepos.save(individual);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
            
        }
    }

    @Override
    public boolean Donate(DonationFromIndividual donationFromIndividual, String individualId, String licenceKey)
            throws AppException {
        // TODO Auto-generated method stub
        return false;
    }

}

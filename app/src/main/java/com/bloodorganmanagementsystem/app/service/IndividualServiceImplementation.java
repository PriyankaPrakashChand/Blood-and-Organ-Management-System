package com.bloodorganmanagementsystem.app.service;

import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.bloodorganmanagementsystem.app.dto.Donation;
import com.bloodorganmanagementsystem.app.dto.DonationFromIndividual;
import com.bloodorganmanagementsystem.app.dto.IndividualDetails;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToGet;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToShow;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;
import com.bloodorganmanagementsystem.app.entities.BodyTest;
import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.repository.HealthOrganizationRepository;
import com.bloodorganmanagementsystem.app.repository.IndividualRepository;
import com.bloodorganmanagementsystem.app.service.exception.AppException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

@Service
@Data
public class IndividualServiceImplementation implements IndividualService {

    IndividualRepository indRepos;
    HealthOrganizationRepository orgRepos;

    final int minimumHeight=25;
    final int minimumWeight=45;
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
    public boolean Register(Individual individual) throws AppException {
       // 1-check if email is unique
       List<Individual> dbIndividualList=indRepos.findByEmail(individual.getEmail());
       if(!dbIndividualList.isEmpty()){
           throw new AppException("Email Id Already Exists");
       }
       // 2-check that license key exists
    //    List<HealthOrganization> dBOrganizationList=orgRepos.findByLisenceKey(individual.getAppliedLicenseKey());
    //    if (dBOrganizationList.isEmpty()){
    //     throw new AppException("Invalid licence Key Applied");
    //    }

       // 3-check that patient is between 18-65 years in age

	
    //    LocalDate d = individual.getBirthday();
    //    Calendar c = Calendar.getInstance();
    //    c.setTime(d);
    //    int year = c.get(Calendar.YEAR);
    //    int month = c.get(Calendar.MONTH) + 1;
    //    int date = c.get(Calendar.DATE);
    //    LocalDate l1 = LocalDate.of(year, month, date);
    //    LocalDate now1 = LocalDate.now();
    //    Period diff1 = Period.between(l1, now1);
    //    int age = diff1.getYears();
       
    //    if(age < 18 || age > 65) {
    //        throw new AppException("The user is not of suitable age to be a Donor");
    //    }
	    
       //4-check that firstname,lasnameand password address,city and country is not empty
       int minimumPasswordLength=8;
       // maybe set a maximum password length too
    

       if(individual.getFirstName().isBlank() || individual.getHeightCm()<minimumHeight||individual.getWeightKg()<minimumWeight||
       individual.getMemeberDetails().getAddress().isBlank() ||individual.getMemeberDetails().getCity().isBlank()|| individual.getMemeberDetails().getCity().isBlank()||
       individual.getMemeberDetails().getPhoneNumber().isBlank()|| individual.getMemeberDetails().getPassword().length()<minimumPasswordLength){
        throw new AppException("No fields must be Left Incomplete");

       }
       
       //5- save individual to repository
     
          individual= indRepos.save(individual);
       
        return true;
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
        
        Individual individual= dbIndividualList.get(0);
        
        // 2- check if password matches
        String indPassword = individual.getMemeberDetails().getPassword();
        if(!indPassword.equals(password)) {
        	throw new AppException("Incorrect Password.");
        }
        
        return true;
    	
        }
    	
    	catch (Exception e){
    		System.out.println(e.getMessage());
            return false;	
    	}
    }

    @Override
    public List<Donation> viewDonations(String individualId) throws AppException {
        // TODO Auto-generated method stub
        return null;
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
         Individual individual= dbIndividualList.get();
            IndividualProfileToShow profile = new IndividualProfileToShow();
			profile.setBlood(individual.getBloodDetails());
			profile.setDateOfBirth(individual.getBirthday());
			profile.setGender(individual.getGender());
			profile.setHeight(individual.getHeightCm());
			profile.setWeight(individual.getWeightKg());
			profile.setDonationPreferences(individual.getDonationPreference());
            profile.setTests(individual.getTestDetails());
            return profile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

	@Override
	public boolean addTest(String individualId, BodyTest test, String licenseKey) throws AppException {
		// TODO Auto-generated method stub
		
		try {
//		1-	check ind id exist (optional ind isPresent() from repository)
		Optional<Individual> dbIndividual = indRepos.findById(individualId);
				
//		2-	check ifEmpty() -> return error app exception class
		if(dbIndividual.isEmpty()) {
			throw new AppException("Individual does not exist");
		}
		
		Individual individual = dbIndividual.get();
		
//		3-	license key should be equal to ind previously set key
		if(!individual.getAppliedLicenseKey().equals(licenseKey)) {
			throw new AppException("Individual not valid");
		}
		
//		4-  check test name is not null
		if(test == null || test.getTestName().equals(TestName.NULL)) {
			throw new AppException("Cannot have empty test");
		}
		
//		5- 	date of last update -> create new date.
		test.setDateOFLastUpdate(new Date());
		
//		6- Add test to individual
		individual.addTest(test);
		
//		7- Update database
		individual = indRepos.save(individual);
		
		return true;
		
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
}

	@Override
	public boolean AddProfile(String individualId, IndividualProfileToGet profile, String licenseKey)
			throws AppException {
				
		// TODO Auto-generated method stub
		
		//1- check Id exists
		try{ 
			
		Optional<Individual> dbIndividual = indRepos.findById(individualId);
		
		if(dbIndividual.isEmpty()) {
			throw new AppException("Individual does not exist");
		}
		
		Individual individual = dbIndividual.get();
		
		//2- license key
	    List<HealthOrganization> dBOrganizationList=orgRepos.findByLisenceKey(licenseKey);
	    if (dBOrganizationList.isEmpty()){
	         //throw new AppException("Invalid licence Key Applied");
	        }
		
	    //3- validate gender
		 if(profile.getGender()== Gender.NULL) {
			throw new AppException("Gender can not be null"); 
		 }
		 
		 //4- validate blood
		 if(profile.getBlood().getBloodType() == BloodType.NULL) {
			throw new AppException("blood type cannot be null");
		 }
		 
		 if(profile.getBlood().getBloodUnitsAvailable()<0 || profile.getBlood().getBloodUnitsDonated()<0) {
				throw new AppException("blood units invalid");
			 }
		 
		 //5-validate height and weight
		 if(profile.getHeight()<minimumHeight||profile.getWeight()<minimumWeight) {
				throw new AppException("Height/Weight not allowed");
		 }
		 
		 //6- validate donation preference
		 if(!profile.getDonationPreferences().isEmpty()) {
			 List<DonationPreference> preferences = profile.getDonationPreferences();
			 for(DonationPreference preference:preferences) {
				 if(preference == DonationPreference.NULL) {
					 throw new AppException("preference cannot be NULL");
				 }
			 }
		 }
		 
		 
		 individual.setGender(profile.getGender());
		 individual.setBloodDetails(profile.getBlood());
		 individual.setHeightCm(profile.getHeight());
		 individual.setWeightKg(profile.getWeight());
		 individual.setDonationPreference(individual.getDonationPreference());

		
//		 Update database
		individual = indRepos.save(individual);
		
		return true;
		}
		catch (Exception e) {
		System.out.println(e.getMessage());
		return false;}
	}

	@Override
	public boolean Donate(DonationFromIndividual donationFromIndividual, String individualId, String licenceKey)
			throws AppException {
		// TODO Auto-generated method stub
		return false;
	}



}



///////////////////////////////////////
// public Optional<Individual> findById(final String findId) {
    // return indRepos.findById(findId);
    // }

    // @Override
    // public boolean addTest(String individualId, Integer testId, Integer
    // testValue, String licenseKey)
    // throws AppException {

    // // // check if the individual exists
    // // final Optional<Individual> dbIndividual =
    // // individualRepository.findById(individualId);
    // // if (dbIndividual.isEmpty()) {
    // // throw new AppException("Individual does not exist");
    // // }
    // // // check if licence key matches
    // // Individual individual = dbIndividual.get();
    // // if (!licenseKey.equals(individual.getAppliedLicenseKey())){
    // // throw new AppException("Invalid licence Key");
    // // }

    // // Test testTable=individual.getMember().getTest();

    // return false;
    // }

    // @Override
    // public boolean Register(final IndividualDetails details) {
    // // TODO Auto-generated method stub
    // return false;
    // }

    // public boolean AddProfile(final String individualId,  IndividualProfileToShow profile,
    // String licenseKey) throws AppException {
        
        
        
        
    //    return false;
   // if (profile.getBloodType() == null || profile.getBloodUnits() == null ||
   // profile.getHeight() == null|| profile.getWeight()==null
   // ||profile.getDateOfBirth()==null||profile.getGender()==0) {
   // throw new AppException("Incomplete information");
   // }
   // // 1 check if the individual exists
   // final Optional<Individual> dbIndividual =
   // individualRepository.findById(individualId);
   // if (dbIndividual.isEmpty()) {
   // throw new AppException("Individual does not exist");
   // }
   // Individual individual = dbIndividual.get();

   // // 2check if licency key belongs to individual
   // if (!licenseKey.equals(individual.getAppliedLicenseKey())) {
   // throw new AppException("Invalid licence Key");
    // }

   // // 3validate height
   // Integer minimumHeight = 0;
   // if (profile.getHeight() < minimumHeight) {
   // throw new AppException("Invalid Height");
   // }

   // // 4-validate weight
   // Integer minimumWeight = 0;
   // if (profile.getWeight() < minimumWeight) {
   // throw new AppException("Invalid Weight");
   // }
   // // 5- check if blood units is valid
   // Integer minimumBloodUnits = 0;
   // if (profile.getBloodUnits() < minimumBloodUnits) {
   // throw new AppException("Invalid BloodUnits");
   // }

   // // dont check for get gender and bloodType because will have buttons to
   // select
   // // in front end from

   // // 6-validate date if birth
   // // Integer maxAge=65;
   // // Integer minAge=18;
   // // Date currentDate= new Date();
   // // if(currentDate-profile.getDateOfBirth()< minAge) //should be between 18-65
   // // years

   // // 7-------now set
   // individual.setGender(profile.getGender());
   // individual.setHeightCm(profile.getHeight());
   // individual.setWeightKg(profile.getWeight());
   // individual.setBirthday(profile.getDateOfBirth());
   // individual.getMember().getBloods().clear(); // ensure that the list is alwasy
   // empty

   // // 8-calculate units that the individual can donate
   // Blood blood = new Blood(); // autogenerated id

   // Optional<BloodType> dbBloodType =
   // bloodTypeRepository.findById(profile.getBloodType());
   // BloodType bloodType= dbBloodType.get();
   // blood.setBloodType(bloodType);// find from repository
   // blood.setBloodUnits(new BigDecimal(profile.getBloodUnits()));
   // // 9-add them to each other
   // blood.setMember(individual.getMember());
   // individual.getMember().addBlood(blood);

   // // 10- save blood and individual back to repository

   // individual = individualRepository.save(individual);
   // blood = bloodRepository.save(blood);

   // return true;
   // }

   // @Override
   // public boolean Donate(final long donationEntityTypeId, final String
   // individualId, final String licenceKey)
   // throws AppException {
   // // check if the individual exists
   // final Optional<Individual> dbIndividual =
   // individualRepository.findById(individualId);
   // if (dbIndividual.isEmpty()) {
   // throw new AppException("Individual does not exist");
   // }
   // Individual individual = dbIndividual.get();
   // // check if license key is valid
   // List<HealthOrganization> dbHealthOrganizations =
   // healthOrganizationRepository.findByLisenceKey(licenceKey);
   // if (dbHealthOrganizations.isEmpty()) {
   // throw new AppException("Invalid Licence Key");
   // }
   // Integer maximumValue = 1;
   // // check if donation enity exists and matches the individualid and is in
   // // inactive state
   // List<DonationEntity> donationEnitities =
   // individual.getMember().getDonationEntities();
   // DonationEntity donationEntity = null;
   // // check if entity exists
   // for (DonationEntity entity : donationEnitities) {
   // if (entity.getEntityType().getEntityTypeId() == donationEntityTypeId) {
   // donationEntity = entity;
   // break;
   // }
   // }
   // if (donationEntity == null) {
   // throw new AppException(
   // individual.getFirstName() + " " + individual.getLastName() + "is not
   // interested in donation");
   // }
   // // check if the entity is available for donation
   // if (donationEntity.getStateId().intValue() !=
   // constants.AVAILABLE_STATE.ordinal()) {

   // throw new AppException(
   // donationEntity.getEntityType().getEntityTypeName() + " is not available for
   // donation");

   // }

   // // check if the donor is eligible to donate it

   // // update donation log and entity status and save entity to repository.

   // return false;
   // }

   // static Integer isEligible(EligibilityToDonate eligibilityToDonate,
   // DonationEntity entity) {
   // if (entity.getDonationEntityId() == entities.BLOOD.ordinal()) {
   // return eligibilityToDonate.getBlood();
   // } else if (entity.getDonationEntityId() == entities.BONE_MARROW.ordinal()) {
   // return eligibilityToDonate.getBoneMarrow();
   // } else if (entity.getDonationEntityId() == entities.EYE.ordinal()) {
   // return eligibilityToDonate.getEyes();
   // }

   // else if (entity.getDonationEntityId() == entities.HEART.ordinal()) {
   // return eligibilityToDonate.getHeart();
   // } else if (entity.getDonationEntityId() == entities.KIDNEY.ordinal()) {
   // return eligibilityToDonate.getKidney();
   // } else if (entity.getDonationEntityId() == entities.LIVER.ordinal()) {
   // return eligibilityToDonate.getLiver();
   // }

   // return 0;

   // }

   // /**
   // * @param individualId
   // * @return List<Donation>
   // * @throws AppException
   // */

   // @Override
   // public List<Donation> viewDonations(final String individualId) throws
   // AppException {

   // // check if the individual exists
   // final Optional<Individual> dbIndividual =
   // individualRepository.findById(individualId);
   // if (dbIndividual.isEmpty()) {
   // throw new AppException("Individual does not exist");
   // }
   // Individual individual = dbIndividual.get();

   // List<Donation> donations = new ArrayList<Donation>();
   // Donation donation = new Donation();
   // List<CompletedDonationLog> logs =
   // individual.getMember().getCompletedDonationLogs();

   // for (CompletedDonationLog log : logs) {
   // // set donation entity name
   // donation.setEntityName(log.getDonationEntity().getEntityType().getEntityTypeName());

   // // set recepient name
   // donation.setRecipientName(log.getMember().getIndividual().getFirstName() + "
   // "
   // + log.getMember().getIndividual().getLastName());

   // // set date of donation
   // donation.setDateOfDonation(log.getDonationDate());

   // // append to list that needs to be returned
   // donations.add(donation);

   // }

   // return donations;

   // // TODO Auto-generated method stub

   // }

   
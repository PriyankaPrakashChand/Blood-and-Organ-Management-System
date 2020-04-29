package com.bloodorganmanagementsystem.app.individualservices;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bloodorganmanagementsystem.app.dto.IndividualProfileToGet;
import com.bloodorganmanagementsystem.app.entities.Blood;

import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.MemberDetail;
import com.bloodorganmanagementsystem.app.entities.BodyTest;
import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;

import com.bloodorganmanagementsystem.app.entities.Individual.DonationPreference;
import com.bloodorganmanagementsystem.app.entities.Individual.Gender;
import com.bloodorganmanagementsystem.app.entities.BodyTest.TestName;
import com.bloodorganmanagementsystem.app.repository.IndividualRepository;
import com.bloodorganmanagementsystem.app.service.AppException;
import com.bloodorganmanagementsystem.app.service.IndividualServiceImplementation;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterationTest {
    @Autowired
    IndividualServiceImplementation indSer;
    @Autowired
    IndividualRepository indRepos;

    Individual ind;

    IndividualProfileToGet profile;

    @Before
    public void init() {

        ind = new Individual();
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.setAddress("address");
        memberDetail.setCity("city");
        memberDetail.setCountry("country");
        memberDetail.setPassword("password");
        ind.setMemeberDetails(memberDetail);
        memberDetail.setPhoneNumber("phoneNumber");
        Blood blood = new Blood();
        blood.setBloodType(BloodType.AB_NEGATIVE);
        ind.setFirstName("IndivisualFirstName");
        ind.setLastName("IndivisualLastName");
        ind.setAppliedLicenseKey("Org1LicenceKey");
        ind.setBirthday(LocalDate.now());
        ind.setBloodDetails(blood);
        ind.setHeightCm(134);
        ind.setWeightKg(72);
        ind.setEmail("HarryPotter@email.com");
        ind= indRepos.save(ind); // save an individual to the repositoory
       

    }

    @After
    public void cleanup() {
           
         try{ if (indRepos.findById(ind.getId()).isPresent()){
            indRepos.delete(indRepos.findById(ind.getId()).get());
          }}
          catch (Exception e){
            System.out.println(e.getMessage());
           
         }
    }

    @Test
    public void successTest() throws AppException {

        indRepos.delete(ind);
        boolean result = indSer.Register(ind);
        assert (result == true);

    }

    @Test
    public void loginSuccessTest() throws AppException {
      
        Boolean result = indSer.Login(ind.getEmail(), ind.getMemeberDetails().getPassword());
        assert (result == true);

    }

    @Test
    public void AddProfileTest() throws AppException {

        profile = new IndividualProfileToGet();

        List<DonationPreference> listOfDonationPref = new ArrayList<DonationPreference>();
        Blood newBlood = new Blood();

        listOfDonationPref.add(DonationPreference.BLOOD);
        listOfDonationPref.add(DonationPreference.KIDNEY);
        newBlood.setBloodType(BloodType.A_POSITIVE);
        newBlood.setBloodUnitsAvailable(25);
        newBlood.setBloodUnitsDonated(30);

        profile.setBlood(newBlood);
        profile.setDonationPreferences(listOfDonationPref);
        profile.setGender(Gender.MALE);
        profile.setHeight(170);
        profile.setWeight(65);

        Boolean result = indSer.AddProfile(ind.getId(), profile, ind.getAppliedLicenseKey());
        assert (result == true);

    }

    @Test
    public void addBodytestTest() throws AppException {
            
            
        BodyTest test = new BodyTest();
        test.setTestName(TestName.BLOOD);
        test.setHasPassed(true);
        boolean result= indSer.addTest(ind.getId(), test, ind.getAppliedLicenseKey());
        assert(result==true);

   

}
   
}
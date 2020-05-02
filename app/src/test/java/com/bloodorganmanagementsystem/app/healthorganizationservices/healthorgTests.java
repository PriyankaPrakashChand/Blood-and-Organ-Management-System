// package com.bloodorganmanagementsystem.app.healthorganizationservices;

// import java.util.List;

// import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.IndividualToShow;
// import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgRegisterationDetails;
// import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail;
// import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
// import com.bloodorganmanagementsystem.app.entities.Individual;
// import com.bloodorganmanagementsystem.app.entities.MemberDetail;
// import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail;
// import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
// import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.dState;
// import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;
// import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail.rState;
// import com.bloodorganmanagementsystem.app.repository.HealthOrganizationRepository;
// import com.bloodorganmanagementsystem.app.repository.IndividualRepository;
// import com.bloodorganmanagementsystem.app.service.exception.AppException;
// import com.bloodorganmanagementsystem.app.service.HealthOrganizationServiceImplementation;
// import com.bloodorganmanagementsystem.app.service.IndividualServiceImplementation;

// import org.junit.After;
// import org.junit.Before;
// import org.junit.Test;

// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringRunner.class)
// @SpringBootTest
// public class healthorgTests {

//    @Autowired
//    HealthOrganizationRepository healthOrgRepos;

//    @Autowired
//    HealthOrganizationServiceImplementation healthOrgSer;

//    @Autowired
//    IndividualRepository indRepos;

//    @Autowired
//    IndividualServiceImplementation indSer;

//    Individual ind1, ind2, ind3;

//    HealthOrganization healthOrg1, healthOrg2;

// //    @Before
//    public void init() {
//       healthOrgRepos.deleteAll();
//       indRepos.deleteAll();
//       ind1 = new Individual();
//       ind2 = new Individual();
//       ind3 = new Individual();
//       healthOrg1 = new HealthOrganization();
//       healthOrg2 = new HealthOrganization();

//       healthOrg1.setEmail("healthOrg1@gmail.com");
//       healthOrg2.setEmail("healthOrg2@gmail.com");


//       healthOrg1.setOrganizationInterest(OrganizationInterest.DONATE);
//       healthOrg2.setOrganizationInterest(OrganizationInterest.RECEIVE);

//       ind1.setFirstName("ind");
//       ind2.setFirstName("ind");
//       ind3.setFirstName("ind");

//       ind1.setLastName("1");
//       ind2.setLastName("2");
//       ind3.setLastName("3");

//       ind1.getMemeberDetails().setPhoneNumber("phone1");
//       ind2.getMemeberDetails().setPhoneNumber("phone2");
//       ind3.getMemeberDetails().setPhoneNumber("phone3");

//       ind1.setEmail("ïnd1@gmail.com");
//       ind2.setEmail("ïnd2@gmail.com");
//       ind3.setEmail("ïnd3@gmail.com");

//       ind1.setAppliedLicenseKey(healthOrg1.getLisenceKey());
//       ind2.setAppliedLicenseKey(healthOrg2.getLisenceKey());
//       ind3.setAppliedLicenseKey(healthOrg1.getLisenceKey());

//       indRepos.save(ind1);
//       indRepos.save(ind2);
//       indRepos.save(ind3);

//       healthOrgRepos.save(healthOrg1);
//       healthOrgRepos.save(healthOrg2);

//    }

// //    @After
//    public void cleanUp() {
//       try {
//          healthOrgRepos.deleteAll();
//          indRepos.deleteAll();
//       } catch (Exception e) {
//          System.out.println(e.getMessage());

//       }
//    }

//    @Test
//    public void viewMyDonorsTest() throws AppException {
//       List<IndividualToShow> individualsToShow = healthOrgSer.viewMyDonors(healthOrg1.getId());
//       assert (individualsToShow != null);
//       individualsToShow.forEach(ind -> System.out
//             .println("name: " + ind.getName() + "phone: " + ind.getPhone() + "email: " + ind.getEmail()));

//    }

//    @Test
//    public void addOrganToDonateTest() throws AppException {
//       DonationEntityDetail detail = new DonationEntityDetail();
//       detail.setEntityName(EntityName.BONE_MARROW);
     
//       Boolean result = healthOrgSer.addOrganToDonate(detail, healthOrg1.getId());
//       assert (result == true);
//    }

//    @Test
//    public void addOrganToDonateInvalidOrgInterestTest() throws AppException {
//       try {
//          DonationEntityDetail detail = new DonationEntityDetail();
//          detail.setEntityName(EntityName.HEART);
//          Boolean result = healthOrgSer.addOrganToDonate(detail, healthOrg2.getId());

//       } catch (Exception e) {
//          assert (e.getMessage().equals("Invalid Health Organization Interest"));
//       }
//    }

//    @Test
//    public void addOrganToReceiveTest() throws AppException {
//       ReceivedEntityDetail detail = new ReceivedEntityDetail();
//       detail.setEntityName(EntityName.BONE_MARROW);
//       Boolean result = healthOrgSer.addOrganToReceive(detail, healthOrg2.getId());
//       assert (result == true);
//    }


// //**********************DONATION TESTS************************ */

// @Test
// public void  donationSuccessTest() throws AppException {
   
//    DonationEntityDetail donation = new DonationEntityDetail();
//    donation.setEntityName(EntityName.HEART);
//    healthOrgSer.addOrganToDonate(donation, healthOrg1.getId());

  
//    ReceivedEntityDetail request = new ReceivedEntityDetail();
//    request.setEntityName(EntityName.HEART);
//    healthOrgSer.addOrganToReceive(request, healthOrg2.getId());

//    DonationEntityDetail detail =new DonationEntityDetail();
//    detail.setEntityName(EntityName.HEART);
//    detail.setReceiverId(healthOrg2.getId());
   
// boolean result= healthOrgSer.donateOrgan(detail,healthOrg1.getId());


//    assert (result == true);

// }

// @Test
// public void RegisterationControllerTest() throws AppException {

//     OrgRegisterationDetails d= new OrgRegisterationDetails();
//     MemberDetail m= new MemberDetail();
//     m.setAddress("adress");
//     m.setCity("city");
//     m.setCountry("country");
//     m.setPassword("password");
//     m.setPhoneNumber("phoneNumber");

//     d.setMemberDetails(m);
//     d.setEmail("email1");
//     d.setOrgName("orgName1");
//     d.setOrganizationInterest(OrganizationInterest.DONATE);

//     assert(healthOrgSer.Register(d)==true);

// }
// }
package com.bloodorganmanagementsystem.app.controller;

import javax.websocket.server.PathParam;

import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.HealthOrgProfile;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgRegisterationDetails;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.MemberDetail;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;
import com.bloodorganmanagementsystem.app.service.HealthOrganizationServiceImplementation;
import com.bloodorganmanagementsystem.app.service.exception.AppException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
@RestController
@RequestMapping("/")
public class HealthOrganizationController {

    private final HealthOrganizationServiceImplementation orgSer;

    /**
     * Public Constructor
     * 
     * @param orgSer
     */
    public HealthOrganizationController(HealthOrganizationServiceImplementation orgSer) {
        this.orgSer = orgSer;
    }

    // @GetMapping("/P/{orgId}")
    // public String viewOrgProfile(@PathVariable String orgId, Model model) {

    // // howe we access the class in other folder
    // // List<String> list = HealthOrganizations.// get profile
    // try {
    // //HealthOrgProfile profile = orgSer.viewMyProfile(orgId);
    // HealthOrgProfile profile= new HealthOrgProfile(orgId,"234","abc","ppp");
    // model.addAttribute("HealthOrg", profile);

    // return "healthOrgProfile";
    // } catch (Exception e) {
    // return e.getMessage();
    // }
    // }

    @GetMapping("/R")
    public OrgRegisterationDetails PostFunctionsJsonObjects() {

        OrgRegisterationDetails d = new OrgRegisterationDetails();
        MemberDetail m = new MemberDetail();
        m.setAddress("adress");
        m.setCity("city");
        m.setCountry("country");
        m.setPassword("password");
        m.setPhoneNumber("phoneNumber");

        d.setMemberDetails(m);
        d.setEmail("email1");
        d.setOrgName("orgName1");
        d.setOrganizationInterest(OrganizationInterest.DONATE);

        return d;

    }

    @PostMapping("/Register")
    public String RegisterOrg(@RequestBody OrgRegisterationDetails orgRegisterationDetails) throws AppException {

            // try{

                if(orgSer.Register(orgRegisterationDetails)==true){
                    return "Registeration Successfull";

                }
                else {
                    return "Registeration Failed";
                }
            // }
            // catch(Exception e) {
            //     return e.getMessage();

            // }
        }


        @PostMapping("/Register-check")
        public OrgRegisterationDetails CheckRegisterOrg(@RequestBody OrgRegisterationDetails orgRegisterationDetails){

           return orgRegisterationDetails;
        }


}

/***
 * @Request body - tells the program that it will receive raw data in json format 
 */
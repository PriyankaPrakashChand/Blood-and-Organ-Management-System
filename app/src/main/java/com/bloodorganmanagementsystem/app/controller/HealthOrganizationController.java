package com.bloodorganmanagementsystem.app.controller;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.bloodorganmanagementsystem.app.dto.Donation;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.DonationDetail;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.HealthOrgProfile;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgLogin;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgRegisterationDetails;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.MemberDetail;
import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;
import com.bloodorganmanagementsystem.app.entities.BloodTypeQty;
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
@RequestMapping("/Org")
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

    

    @GetMapping("/{orgId}")
    public HealthOrgProfile viewProfile(@PathVariable String orgId) throws AppException {

        return orgSer.viewMyProfile(orgId);

    }

    @PostMapping("/Register")
    public String RegisterOrg(@Valid @RequestBody OrgRegisterationDetails orgRegisterationDetails) throws AppException {

        orgSer.Register(orgRegisterationDetails);
        return "Registeration Successfull";

    }

    @PostMapping("/Login")
    public String login(@Valid @RequestBody OrgLogin orgLogin) throws AppException {

        orgSer.Login(orgLogin.getEmail(), orgLogin.getPassword());
        return "Login Successfull";

    }

    @PostMapping("/AvailableToDonate/{orgId}")
    public String addEntityToDonate(@PathVariable String orgId, @Valid @RequestBody DonationDetail detail)
            throws AppException {

        orgSer.addEntityToDonate(detail, orgId);
        return "Donation item added Successfully";

    }

    @PostMapping("/WaitingToReceive/{orgId}")
    public String addEntityToReceive(@PathVariable String orgId, @Valid @RequestBody DonationDetail detail)
            throws AppException {

        orgSer.addEntityToReceive(detail, orgId);
        return " item Request added Successfully";

    }

    @PostMapping("/Donation/{donorOrgId}")
    public String Donation(@PathVariable String donorOrgId, @Valid @RequestBody Donation detail)
            throws AppException {

        // orgSer.addEntityToReceive(detail, donorOrgId);
        return " Donation was successfull";

    }

    

}

/***
 * @Request body - tells the program that it will receive raw data in json
 *          format
 */
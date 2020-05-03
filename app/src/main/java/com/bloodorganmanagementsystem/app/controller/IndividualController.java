package com.bloodorganmanagementsystem.app.controller;

import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.bloodorganmanagementsystem.app.dto.BodyTestDetails;
import com.bloodorganmanagementsystem.app.dto.Donation;
import com.bloodorganmanagementsystem.app.dto.IndividualDetails;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToGet;
import com.bloodorganmanagementsystem.app.dto.IndividualProfileToShow;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgLogin;

import com.bloodorganmanagementsystem.app.service.exception.AppException;
import com.bloodorganmanagementsystem.app.service.IndividualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Ind")
public class IndividualController {

	private final IndividualService indSer;

	@Autowired
	public IndividualController(IndividualService individualService) {
		this.indSer = individualService;
	}

	// @RequestMapping()
	// public String getIndividual(String individualId) {
	// 	Individual individual = indSer.findById("I0001").get();

	// 	return individual.getFirstName();
	// }

	@PostMapping("/Register")
    public String RegisterOrg(@Valid @RequestBody IndividualDetails detail) throws AppException {

		return "Registeration Successfull- id= "+ indSer.Register(detail);
        

	}
	@PostMapping("/Login")
	public String login(@Valid @RequestBody OrgLogin indLogin) throws AppException {

		indSer.Login(indLogin.getEmail(), indLogin.getPassword());
		return "Login Successfull";
	}

	@GetMapping("Profile/{individualId}")
	public IndividualProfileToShow getProfile(@PathVariable String individualId) throws AppException {

		return indSer.viewProfile(individualId);

	}


	@PostMapping("AddTest/{individualId}/{key}")
	public String addTest(@Valid @RequestBody BodyTestDetails detail, @PathVariable String individualId,
			@PathVariable String key) throws AppException {
		indSer.addTest(individualId, detail, key);
		return "Successfully added Test";
	}

	@PostMapping("Profile/{individualId}/{key}")
	public String addProfile(@Valid @RequestBody IndividualProfileToGet profile, @PathVariable String individualId,
			@PathVariable String key) throws AppException {
		indSer.AddProfile(individualId, profile, key);
		return "Successfully added profile";
	}

	@GetMapping("Donation/{individualId}")
	public List<Donation> viewDonations(@PathVariable String individualId) throws AppException {

		return indSer.viewDonations(individualId);

	}

}

package com.bloodorganmanagementsystem.app.controller;

import javax.websocket.server.PathParam;

import com.bloodorganmanagementsystem.app.dto.IndividualProfileToShow;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.service.AppException;
import com.bloodorganmanagementsystem.app.service.IndividualService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Individual")
public class IndividualController {

	private final IndividualService individualService;

	@Autowired
	public IndividualController(IndividualService individualService) {
		this.individualService = individualService;
	}

	@RequestMapping()
	public String getIndividual(String individualId) {
		Individual individual = individualService.findById("I0001").get();

		return individual.getFirstName();
	}

	@GetMapping("ViewProfile/{individualId}")
	public IndividualProfileToShow getProfile(@PathVariable String individualId) {

		try {
			return individualService.viewProfile(individualId);
			
		} catch (AppException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}

package com.bloodorganmanagementsystem.app;

import com.microsoft.azure.spring.data.cosmosdb.repository.config.EnableCosmosRepositories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.entities"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.service"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.controller"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.configration"})
// @EnableCosmosRepositories(basePackages = {"com.bloodorganmanagementsystem.app.repository"})
@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

}

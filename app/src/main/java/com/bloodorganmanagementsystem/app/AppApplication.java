package com.bloodorganmanagementsystem.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.entities"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.service"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.controller"})
@ComponentScan(basePackages = {"com.bloodorganmanagementsystem.app.configration"})
@SpringBootApplication
public class AppApplication extends SpringBootServletInitializer {

@Override
		protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
			return application.sources(AppApplication.class);
		}
	public static void main(String[] args) {

		
		SpringApplication.run(AppApplication.class, args);
	}

}

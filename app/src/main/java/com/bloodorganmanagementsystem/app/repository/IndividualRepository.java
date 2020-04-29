package com.bloodorganmanagementsystem.app.repository;

import com.bloodorganmanagementsystem.app.entities.Individual;
import com.microsoft.azure.spring.data.cosmosdb.repository.CosmosRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends CosmosRepository<Individual, String> {

public Optional<Individual> findById(String id);
public  List<Individual> findByEmail(String email); 
List<Individual> findByAppliedLicenseKey(String appliedLicenseKey);
}
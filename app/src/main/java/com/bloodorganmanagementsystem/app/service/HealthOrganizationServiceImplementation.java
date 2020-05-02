package com.bloodorganmanagementsystem.app.service;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.HealthOrgProfile;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.IndividualToShow;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.OrgRegisterationDetails;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.Donation;
import com.bloodorganmanagementsystem.app.dto.healthorganizationsdto.DonationDetail;

import com.bloodorganmanagementsystem.app.entities.Blood;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization;
import com.bloodorganmanagementsystem.app.entities.Individual;
import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail;
import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.EntityName;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.ReceiverType;
import com.bloodorganmanagementsystem.app.entities.DonationEntityDetail.dState;
import com.bloodorganmanagementsystem.app.entities.HealthOrganization.OrganizationInterest;
import com.bloodorganmanagementsystem.app.entities.BloodTypeQty;
import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail.DonorType;
import com.bloodorganmanagementsystem.app.entities.ReceivedEntityDetail.rState;
import com.bloodorganmanagementsystem.app.repository.HealthOrganizationRepository;
import com.bloodorganmanagementsystem.app.repository.IndividualRepository;
import com.bloodorganmanagementsystem.app.service.exception.AppException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

@Service
@Data
public class HealthOrganizationServiceImplementation implements HealthOrganizationService {

    IndividualRepository indRepos;
    HealthOrganizationRepository orgRepos;

    /**
     * Public Constructor
     * 
     * @param indRepos
     * @param orgRepos
     */
    @Autowired
    public HealthOrganizationServiceImplementation(IndividualRepository indRepos,
            HealthOrganizationRepository orgRepos) {
        this.indRepos = indRepos;
        this.orgRepos = orgRepos;
    }

    @Override
    public Optional<HealthOrganization> findById(String findId) {
        // TODO Auto-generated method stub
        // check if this is correct
        Optional<HealthOrganization> dbHealthOrganization = orgRepos.findById(findId);

        return dbHealthOrganization;

    }

    @Override
    public boolean Register(OrgRegisterationDetails orgRegisterationDetails) throws AppException {
        try { // TODO Auto-generated method stub

            // 1-check if email is unique
            List<HealthOrganization> dbHealthOrganizationList = orgRepos
                    .findByEmail(orgRegisterationDetails.getEmail());

            if (!dbHealthOrganizationList.isEmpty()) {
                throw new AppException("Email Id Already Exists");
            }
            // 2- check name , password , city , country is not empty

            int minimumPasswordLength = 8;

            if (orgRegisterationDetails.getOrgName().isBlank()
                    || orgRegisterationDetails.getMemberDetails().getAddress().isBlank()
                    || orgRegisterationDetails.getMemberDetails().getCity().isBlank()
                    || orgRegisterationDetails.getMemberDetails().getCity().isBlank()
                    || orgRegisterationDetails.getMemberDetails().getPhoneNumber().isBlank()
                    || orgRegisterationDetails.getMemberDetails().getPassword().length() < minimumPasswordLength) {
                throw new AppException("No fields must be Left Incomplete");
            }

            // 3- organizationInterest must not be null
            if (orgRegisterationDetails.getOrganizationInterest().equals(OrganizationInterest.NULL)) {
                throw new AppException("Organization Interest cannot be null");
            }

            // 4- create a new organization with these details -
            //*id, license key and role is auto generated */
            HealthOrganization org= new HealthOrganization();
            org.setOrgName(orgRegisterationDetails.getOrgName());
            org.setEmail(orgRegisterationDetails.getEmail());
            org.setMemberDetails(orgRegisterationDetails.getMemberDetails());
            org.setOrganizationInterest(orgRegisterationDetails.getOrganizationInterest());
          


            // 5- save organization to repository

            org = orgRepos.save(org);
            return true;
        } catch (Exception e) {

            System.out.println(e.getMessage());
            throw e;
        }
       

    }

    @Override
    public boolean Login(String email, String password) throws AppException {
        try {
            List<HealthOrganization> dbOrganization = orgRepos.findByEmail(email);

            if (dbOrganization.isEmpty() || dbOrganization.size() > 1) {
                throw new AppException("Individual does not exist. Email not found.");
            }

            HealthOrganization organization = dbOrganization.get(0);

            // 2- check if password matches
            String orgPassword = organization.getMemberDetails().getPassword();
            if (!orgPassword.equals(password)) {
                throw new AppException("Incorrect Password.");
            }

            return true;

        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        // must enter interest in donation or receiving or both
    }

    @Override
    public HealthOrgProfile viewMyProfile(String id) throws AppException {

        try {
            // 1-check if organization exists
            Optional<HealthOrganization> dbOrganization = orgRepos.findById(id);
            if (dbOrganization.isEmpty()) {
                throw new AppException("Health Organization does not exist");
            }

            // 2- return license key
            HealthOrganization org = dbOrganization.get();
            HealthOrgProfile profile = new HealthOrgProfile(org.getId(), org.getOrgName(), org.getEmail(),org.getLisenceKey(),
                    org.getMemberDetails().getAddress());
            return profile;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

        // TODO Auto-generated method stub

    }

    @Override
    public List<IndividualToShow> viewMyDonors(String id) throws AppException {

        // 1- check if organization exists
        Optional<HealthOrganization> dbOrganization = orgRepos.findById(id);

        if (dbOrganization.isEmpty()) {
            throw new AppException("Health Organization does not exist");
        }
        List<Individual> individuals = indRepos.findByAppliedLicenseKey(dbOrganization.get().getLisenceKey());

        List<IndividualToShow> individualsToShow = new ArrayList<IndividualToShow>();

        individuals.stream()
                .forEach(ind -> individualsToShow.add(new IndividualToShow(ind.getFirstName() + " " + ind.getLastName(),
                        ind.getMemeberDetails().getPhoneNumber(), ind.getEmail())));
        // return all donors name phone numbers and emails
        return individualsToShow;

    }

    @Override
    public boolean addEntityToDonate(DonationDetail donationDetails, String healthOrgId)
            throws AppException {
        // TODO Auto-generated method stub

        try {

            // 1- if entitiy name is invalid return error
            if (donationDetails.getEntityName().equals(EntityName.NULL)) {
                throw new AppException("Invalid Entity Chosen");
            }

            // 1.1 if entity is blood then qty mustbe>0 and type must not be null
            if (donationDetails.getEntityName().equals(EntityName.BLOOD)) {
                if (donationDetails.getBloodDetail()==null||donationDetails.getBloodDetail().isEmpty()) {
                    throw new AppException("Blood Details must be filled");
                }

                BloodTypeQty bloodDetail = donationDetails.getBloodDetail().get();
                if (bloodDetail.getBloodType().equals(BloodType.NULL) || bloodDetail.getQty() < 1) {
                    throw new AppException("Invalid blood details");
                }

            }
            // 2-if health org does not exist return error
            Optional<HealthOrganization> dbOrganization = orgRepos.findById(healthOrgId);

            if (dbOrganization.isEmpty()) {
                throw new AppException("Health Organization does not exist");
            }
            HealthOrganization healthOrg = dbOrganization.get();
            // 3- if organization interest is not to donoate or both return error
            if (healthOrg.getOrganizationInterest().equals(OrganizationInterest.RECEIVE)
                    || healthOrg.getOrganizationInterest().equals(OrganizationInterest.NULL)) {
                throw new AppException("Invalid Health Organization Interest");
            }
            // not checking their preferences because thats just a part of their profile
            // nothing specific

            // purpose is to add this entity and make it available for donation
            DonationEntityDetail details= new DonationEntityDetail();
            details.setEntityName(donationDetails.getEntityName());
            details.setState(dState.AVAILABLE_TO_DONATE);
            details.setReceiverType(ReceiverType.NULL);
            details.setReceiverId(null);
            details.setDateOfDonation(null);
            details.setBloodDetail(null);

            

            // if entity is blood then update blood units available for that blood type
            if (donationDetails.getEntityName().equals(EntityName.BLOOD)) {
                details.setBloodDetail(donationDetails.getBloodDetail());
                healthOrg.getBloods().stream().filter(
                        blood -> blood.getBloodType() == donationDetails.getBloodDetail().get().getBloodType())
                        .forEach(bloodType -> bloodType.setBloodUnitsAvailable(bloodType.getBloodUnitsAvailable()
                                + donationDetails.getBloodDetail().get().getQty()));

            }
            healthOrg.addDonationEntityDetails(details);
            orgRepos.save(healthOrg);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }

    }

    @Override
    public boolean addEntityToReceive(DonationDetail requestDetails, String healthOrgId)
            throws AppException {
        // TODO Auto-generated method stub
        try {
            // 1- entity name should not be null
            if (requestDetails.getEntityName().equals(EntityName.NULL)) {
                throw new AppException("Invalid Entity Chosen");
            }

            // 1.1 if entity is blood then qty mustbe>0 and type must not be null
            if (requestDetails.getEntityName().equals(EntityName.BLOOD)) {
                if (requestDetails.getBloodDetail().isEmpty()) {
                    throw new AppException("Blood Details must be filled");
                }

                BloodTypeQty bloodDetail = requestDetails.getBloodDetail().get();
                if (bloodDetail.getBloodType().equals(BloodType.NULL) || bloodDetail.getQty() < 1) {
                    throw new AppException("Invalid blood details");
                }

            }

            // 2-if health org does not exist return error
            Optional<HealthOrganization> dbOrganization = orgRepos.findById(healthOrgId);

            if (dbOrganization.isEmpty()) {
                throw new AppException("Health Organization does not exist");
            }

            HealthOrganization healthOrg = dbOrganization.get();

            // 3- if organization interest is not to receive or both return error
            if (healthOrg.getOrganizationInterest().equals(OrganizationInterest.DONATE)
                    || healthOrg.getOrganizationInterest().equals(OrganizationInterest.NULL)) {
                throw new AppException("Invalid Health Organization Interest");
            }

            // not checking their preferences because thats just a part of their profile
            // nothing specific
           ReceivedEntityDetail details= new ReceivedEntityDetail();
           details.setEntityName(requestDetails.getEntityName());
           details.setStateOfEntity(rState.WAITING_TO_BE_RECEIVED);
           details.setDateOfReceivingDonation(null);
           details.setDonorType(DonorType.NULL); // because didnt receive yet - only adding to the list
           details.setDonorId(null);
           details.setBloodDetail(null);
           

            // if entity is blood then update blood units required for that blood type
            if (requestDetails.getEntityName().equals(EntityName.BLOOD)) {
                details.setBloodDetail(requestDetails.getBloodDetail());
                healthOrg.getBloods().stream().filter(
                        blood -> blood.getBloodType() == requestDetails.getBloodDetail().get().getBloodType())
                        .forEach(bloodType -> bloodType.setBloodUnitsRequired(bloodType.getBloodUnitsRequired()
                                + requestDetails.getBloodDetail().get().getQty()));

            }
            
            healthOrg.addReceivedEntityDetails(details);
            orgRepos.save(healthOrg);

            return true;
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean donateOrgan(Donation donation, String healthOrgId) throws AppException {
        try { // TODO Auto-generated method stub
            /**
             * Validate the organization
             */
            // 1-if health org does not exist return error
            Optional<HealthOrganization> dbOrganization = orgRepos.findById(healthOrgId);

            if (dbOrganization.isEmpty()) {
                throw new AppException("Health Organization does not exist");
            }

            HealthOrganization healthOrg = dbOrganization.get();

            // 2- if organization interest is not to donate or both return error
            if (healthOrg.getOrganizationInterest().equals(OrganizationInterest.RECEIVE)
                    || healthOrg.getOrganizationInterest().equals(OrganizationInterest.NULL)) {
                throw new AppException("Invalid Health Organization Interest");
            }

            /**
             * Validate the entity details
             */

            // 3- entity name must not be null
            if (donation.getEntityName().equals(EntityName.NULL)) {
                throw new AppException("Invalid Entity Chosen");
            }

            // 4- Only health orgs can receive then they must exist and interested in
            // receiving or both
            HealthOrganization receiverOrg;

            receiverOrg = orgRepos.findById(donation.getReceiverId())
                    .orElseThrow(() -> new AppException("Receiver does not exist"));

            // 5- if organization interest is not to receive or both return error
            if (receiverOrg.getOrganizationInterest().equals(OrganizationInterest.DONATE)
                    || healthOrg.getOrganizationInterest().equals(OrganizationInterest.NULL)) {
                throw new AppException("Invalid Health Organization Interest");
            }

            // 6- the entity must be present in health orgs donation log and must be
            // available to donate
            boolean Donationavailable = false;
            int entityDonationIndex = -1;

            List<DonationEntityDetail> detailsD = healthOrg.getDonationEntityDetails().stream()
                    .filter(detail -> detail.getEntityName().equals(donation.getEntityName()))
                    .collect(Collectors.toList());

            for (int i = 0; i < detailsD.size(); i++) {
                if (detailsD.get(i).getState().equals(dState.AVAILABLE_TO_DONATE)) {
                    entityDonationIndex = i;
                    Donationavailable = true;
                    break;
                }
            }

            if (!Donationavailable) {
                throw new AppException("Entity is not  available for donation");
            }

            // 7- entity must be present in receivers log waiting to be donated

            boolean waitingToReceive = false;
            int entityReceivedIndex = -1;

            List<ReceivedEntityDetail> detailsR = receiverOrg.getReceivedEntityDetails().stream()
                    .filter(detail -> detail.getEntityName().equals(donation.getEntityName()))
                    .collect(Collectors.toList());

            for (int i = 0; i < detailsR.size(); i++) {
                if (detailsR.get(i).getStateOfEntity().equals(rState.WAITING_TO_BE_RECEIVED)) {
                    entityReceivedIndex = i;
                    waitingToReceive = true;
                    break;
                }
            }

            if (!waitingToReceive) {
                throw new AppException(" Entity is not required by Receiver");
            }

            /**
             * Update the donors log
             */
            DonationEntityDetail donationLog = detailsD.get(entityDonationIndex);
            donationLog.setDateOfDonation(LocalDate.now());
            donationLog.setReceiverType(ReceiverType.ORGANIZATION);
            donationLog.setState(dState.HAS_BEEN_DONATED);
            donationLog.setReceiverId(donation.getReceiverId());
            healthOrg.getDonationEntityDetails().set(entityDonationIndex, donationLog);
            orgRepos.save(healthOrg);

            /**
             * Update the receivers log
             */

            ReceivedEntityDetail receiverLog = detailsR.get(entityReceivedIndex);
            receiverLog.setDateOfReceivingDonation(LocalDate.now());
            receiverLog.setDonorId(healthOrg.getId());
            receiverLog.setDonorType(DonorType.ORGANIZATION); // for individual donors set it to individual
            receiverLog.setStateOfEntity(rState.HAS_BEEN_RECEIVED);
            receiverOrg.getReceivedEntityDetails().set(entityReceivedIndex, receiverLog);
            orgRepos.save(receiverOrg);
            return true;
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public boolean donateBlood(Blood blood, String healthOrgId) {
        // TODO Auto-generated method stub
        // organization must exist
        //

        return false;
    }

   

    @Override
    public boolean receiveBlood(Blood blood, String healthOrgId) {
        // TODO Auto-generated method stub
        return false;
    }

}

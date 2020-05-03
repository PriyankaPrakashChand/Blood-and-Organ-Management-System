package com.bloodorganmanagementsystem.app.entities;

import java.util.ArrayList;
import java.util.List;


import com.bloodorganmanagementsystem.app.entities.Blood.BloodType;
import com.bloodorganmanagementsystem.app.entities.enums.EntityPreference;
// import com.bloodorganmanagementsystem.app.entities.enums.OrganizationInterest;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.Document;
import com.microsoft.azure.spring.data.cosmosdb.core.mapping.PartitionKey;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "HEALTH_ORGANIZATION_COLLECTION")
@Data
@Component
@AllArgsConstructor

public class HealthOrganization {
	public static Integer idGenerator = 0;

	public enum Role {
		NULL, ORG, IND
	}

	public enum OrganizationInterest {
		NULL, RECEIVE, DONATE, BOTH
	}

	@Id // registeration
	private String id;
	@PartitionKey
	private String email; // unique
	private String lisenceKey; // unique
	private String orgName;
	private MemberDetail memberDetails;
	// expansion activities
	private List<String> requestedPartnerIds; // ids to whom partner request is sent
	private List<String> partnerOrganzationIds; // accepted requests
	private List<String> receivedPartnerRequestIds;// requests received waiting for acceptance
	private OrganizationInterest organizationInterest;

	// profile Related
	private List<Blood> bloods;
	private List<EntityPreference> entityPreferences;
	// Details to be filed while Entity has been donated or while offering a donation
	private List<DonationEntityDetail> donationEntityDetails;
	// details to be filed while and entity has been received or while requesting
	private List<ReceivedEntityDetail> receivedEntityDetails;

	private static Role role = Role.ORG;

	/**
	 * NoArgsConstructor
	 */
	public HealthOrganization() {

		this.id = "O" + Integer.toString(++idGenerator);
		// this.lisenceKey = UUID.randomUUID().toString();
		this.lisenceKey = "Org" + Integer.toString(idGenerator); // -- temporary
		this.email = "";
		this.orgName = "";
		this.memberDetails = new MemberDetail("", "", "", "", "");
		this.organizationInterest = OrganizationInterest.NULL;

		/**
		 * Initialize all lists
		 * 
		 */

		this.receivedEntityDetails=new ArrayList<ReceivedEntityDetail>();
		this.donationEntityDetails=new ArrayList<DonationEntityDetail>();
		this.entityPreferences=new ArrayList<EntityPreference>();

		// by default all orgs have all blood types
		this.bloods=new ArrayList<Blood>();
		bloods.add(new Blood(BloodType.AB_NEGATIVE,0,0,0));
		bloods.add(new Blood(BloodType.AB_POSITIVE,0,0,0));
		bloods.add(new Blood(BloodType.A_NEGATIVE,0,0,0));
		bloods.add(new Blood(BloodType.A_POSITIVE,0,0,0));
		bloods.add(new Blood(BloodType.B_NEGATIVE,0,0,0));
		bloods.add(new Blood(BloodType.B_POSITIVE,0,0,0));
		bloods.add(new Blood(BloodType.O_NEGATIVE,0,0,0));
		bloods.add(new Blood(BloodType.O_POSITIVE,0,0,0));


		// unused lists
		this.partnerOrganzationIds=new ArrayList<String>();
		this.requestedPartnerIds=new ArrayList<String>();
		this.receivedPartnerRequestIds=new ArrayList<String>();

	}

	/**
	 * *****************************List Functions********************************
	 */

	public void addRequestedPartnerId(String requestedPartnerId) {
		this.requestedPartnerIds.add(requestedPartnerId);
	}

	public void removeRequestedPartnerId(String requestedPartnerId) {
		this.requestedPartnerIds.remove(requestedPartnerId);
	}

	public void addPartnerOrganizationId(String partnerOrganizationId) {
		this.partnerOrganzationIds.add(partnerOrganizationId);
	}

	public void removePartnerOrganizationId(String PartnerOrganizationId) {
		this.partnerOrganzationIds.remove(PartnerOrganizationId);

	}

	public void addReceivedPartnerRequestId(String receivedPartnerRequestId) {
		this.receivedPartnerRequestIds.add(receivedPartnerRequestId);
	}

	public void removeReceivedPartnerRequestId(String receivedPartnerRequestId) {
		this.receivedPartnerRequestIds.remove(receivedPartnerRequestId);

	}

	public void addBlood(Blood blood) {
		this.bloods.add(blood);
	}

	public void removeBlood(Blood blood) {
		this.bloods.remove(blood);
	}

	public void addEntityPreference(EntityPreference entityPreference) {
		this.entityPreferences.add(entityPreference);
	}

	public void removeEntityPreference(EntityPreference entityPreference) {
		this.entityPreferences.remove(entityPreference);
	}

	public void addDonationEntityDetails(DonationEntityDetail donationEntityDetails) {
		this.donationEntityDetails.add(donationEntityDetails);
	}

	public void removeDonationEntityDetails(DonationEntityDetail donationEntityDetails) {
		this.donationEntityDetails.remove(donationEntityDetails);
	}

	public void addReceivedEntityDetails(ReceivedEntityDetail receivedEntityDetails){
		this.receivedEntityDetails.add(receivedEntityDetails);
	}

	public void removeReceivedEntityDetails(ReceivedEntityDetail receivedEntityDetails){
		this.receivedEntityDetails.remove(receivedEntityDetails);
	}

}
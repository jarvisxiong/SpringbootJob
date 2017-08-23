package com.sistic.be.patron.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Deprecated
public class PatronSolicitation implements Serializable {
	
	// Need to redesign this. Temporary copied from Booking Engine.
	// Will not work for new design due to BookingEngine being shared.
	
	// Will boolean be able to hold the flags for all whitelabels?

	/**
	 * Solocitation Type
	 * Unsubscribe; Venue; Promoter SMS; PromoterDirectMailLocal; Tenant; OrganiserPhone; OrganiserPost; Promoter; PromoterSubEmail
	 * Makes no sense to subscribe to Unsubscribe???
	 */
	private String solicitationType;
	
	private String acctNum; // Is this used? Not in DB
	
	private OffsetDateTime whenLastUpdate;
	private String organizationID;	// Subscribed organization
	private String status ;	// 0 - Unsubscribe, 1 - Subscribe

	// Why do I need these flags??? NOOOO MOREEE!
//	private Boolean isreceivesistic;
//	private Boolean isreceivepromoter;
//	private Boolean isreceivevenue;
//	private Boolean isDoNotSignUp;	// no subscriptions
	
	private String whoLastUpdate ;

	public String getSolicitationType() {
		return solicitationType;
	}

	public void setSolicitationType(String solicitationType) {
		this.solicitationType = solicitationType;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public OffsetDateTime getWhenLastUpdate() {
		return whenLastUpdate;
	}

	public void setWhenLastUpdate(OffsetDateTime whenLastUpdate) {
		this.whenLastUpdate = whenLastUpdate;
	}

	public String getOrganizationID() {
		return organizationID;
	}

	public void setOrganizationID(String organizationID) {
		this.organizationID = organizationID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWhoLastUpdate() {
		return whoLastUpdate;
	}

	public void setWhoLastUpdate(String whoLastUpdate) {
		this.whoLastUpdate = whoLastUpdate;
	}
	
	
	
}

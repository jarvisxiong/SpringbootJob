package com.sistic.be.patron.model;

import java.util.List;

@Deprecated
public class Patron {
		
	// Patron Profile
	private PatronProfile patronProfile;

	// Advanced Profile - Please review
	private PatronAdvProfile patronAdvProfile;
	
	// Internet Account, userid password
	private PatronInternetAccount patronInternetAccount;
	
	// Phone
	private List<PatronPhone> patronPhones;
	
	// Billing and Mailing Address
	private List<Address> addresses;
	
	// Subscription - Need to redesign this class
	private List<PatronSolicitation> patronSolicitations;

	// Getter Setter
	public PatronProfile getPatronProfile() {
		return patronProfile;
	}

	public void setPatronProfile(PatronProfile patronProfile) {
		this.patronProfile = patronProfile;
	}

	public PatronAdvProfile getPatronAdvProfile() {
		return patronAdvProfile;
	}

	public void setPatronAdvProfile(PatronAdvProfile patronAdvProfile) {
		this.patronAdvProfile = patronAdvProfile;
	}

	public List<PatronPhone> getPatronPhones() {
		return patronPhones;
	}

	public void setPatronPhones(List<PatronPhone> patronPhones) {
		this.patronPhones = patronPhones;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<PatronSolicitation> getPatronSolicitations() {
		return patronSolicitations;
	}

	public void setPatronSolicitations(List<PatronSolicitation> patronSolicitations) {
		this.patronSolicitations = patronSolicitations;
	}

	public PatronInternetAccount getPatronInternetAccount() {
		return patronInternetAccount;
	}

	public void setPatronInternetAccount(PatronInternetAccount patronInternetAccount) {
		this.patronInternetAccount = patronInternetAccount;
	}


}

package com.sistic.be.patron.model;

import java.io.Serializable;

/**
 * 
 * @author jianhong
 * Reference the Patron_Profile table
 *
 */
@Deprecated
public class PatronProfile implements Serializable {
	
	private long patronProfileId;
	private String identityNo;
	private String idType;
	private long accountNo;
	private int status;
	
	private String firstName;
	private String lastName;
	private String emailAddress;
	
	private boolean isReceivesistic;
	private boolean isReceivePromoter;
	private boolean isReceiveVenue;
	private boolean isDoNotSignUp;	// no subscriptions
	
	// Getter Setter
	public long getPatronProfileId() {
		return patronProfileId;
	}
	public void setPatronProfileId(long patronProfileId) {
		this.patronProfileId = patronProfileId;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isReceivesistic() {
		return isReceivesistic;
	}
	public void setReceivesistic(boolean isReceivesistic) {
		this.isReceivesistic = isReceivesistic;
	}
	public boolean isReceivePromoter() {
		return isReceivePromoter;
	}
	public void setReceivePromoter(boolean isReceivePromoter) {
		this.isReceivePromoter = isReceivePromoter;
	}
	public boolean isReceiveVenue() {
		return isReceiveVenue;
	}
	public void setReceiveVenue(boolean isReceiveVenue) {
		this.isReceiveVenue = isReceiveVenue;
	}
	public boolean isDoNotSignUp() {
		return isDoNotSignUp;
	}
	public void setDoNotSignUp(boolean isDoNotSignUp) {
		this.isDoNotSignUp = isDoNotSignUp;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	

}

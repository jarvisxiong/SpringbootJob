package com.sistic.be.cart.api.model;

import java.io.Serializable;

public class SolicitationUpdate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8026482707755397946L;
	
	private String solicitationType;
	private Long organizationID;
	private Boolean subscribed;
	
	public SolicitationUpdate() {}

	public SolicitationUpdate(String solicitationType, Long organizationID, Boolean subscribed) {
		this.solicitationType = solicitationType;
		this.organizationID = organizationID;
		this.subscribed = subscribed;
	}
	
	public String getSolicitationType() {
		return solicitationType;
	}
	public void setSolicitationType(String solicitationType) {
		this.solicitationType = solicitationType;
	}
	public Long getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(Long organizationID) {
		this.organizationID = organizationID;
	}
	public Boolean getSubscribed() {
		return subscribed;
	}
	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}
	
	@Override
	public String toString() {
		return "SolicitationUpdate [solicitationType=" + solicitationType + ", organizationID=" + organizationID
				+ ", subscribed=" + subscribed + "]";
	}
	
}


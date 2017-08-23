package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class UpdateSolicitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7466729511540626910L;
	
	private String solicitationType;
	private Long organizationId;
	private Boolean subscribed;
	
	public UpdateSolicitation() {
		
	}
	
	public UpdateSolicitation(String solicitationType, Long organizationId, Boolean subscribed) {
		this.solicitationType = solicitationType;
		this.organizationId = organizationId;
		this.subscribed = subscribed;
	}
	
	public String getSolicitationType() {
		return solicitationType;
	}
	public void setSolicitationType(String solicitationType) {
		this.solicitationType = solicitationType;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public Boolean getSubscribed() {
		return subscribed;
	}
	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}

	@Override
	public String toString() {
		return "UpdateSolicitation [solicitationType=" + solicitationType + ", organizationId=" + organizationId
				+ ", subscribed=" + subscribed + "]";
	}
	
}

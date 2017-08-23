package com.sistic.be.patron.api.model;

import java.io.Serializable;

public class Solicitation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4351997812037802606L;
	
	private String organizationName;
	private String organizationUrl;
	private Integer order;
	private String solicitationType;
	private Long organizationID;
	private Boolean subscribed;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationUrl() {
		return organizationUrl;
	}

	public void setOrganizationUrl(String organizationUrl) {
		this.organizationUrl = organizationUrl;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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
		return "Solicitation [organizationName=" + organizationName + ", organizationUrl=" + organizationUrl
				+ ", order=" + order + ", solicitationType=" + solicitationType + ", organizationID=" + organizationID
				+ ", subscribed=" + subscribed + "]";
	}
	
}

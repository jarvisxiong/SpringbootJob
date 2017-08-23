package com.sistic.be.membership.api.model;

import java.io.Serializable;

public class MembershipConfigOption implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4594065071945620629L;
	
	private String subKey;
	private String subAlias;
	private String type;
	private Integer ordering;
	private Boolean isMandatory;
	private Boolean isValidationReq;
	private Boolean isSelected;
	private String selectedValue;

	public String getSubKey() {
		return subKey;
	}

	public void setSubKey(String subKey) {
		this.subKey = subKey;
	}

	public String getSubAlias() {
		return subAlias;
	}

	public void setSubAlias(String subAlias) {
		this.subAlias = subAlias;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}
	
	public Boolean getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public Boolean getIsValidationReq() {
		return isValidationReq;
	}

	public void setIsValidationReq(Boolean isValidationReq) {
		this.isValidationReq = isValidationReq;
	}

	public Boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	@Override
	public String toString() {
		return "MembershipConfigOption [subKey=" + subKey + ", subAlias=" + subAlias
				+ ", type=" + type + ", ordering=" + ordering
				+ ", isMandatory=" + isMandatory + ", isValidationReq=" + isValidationReq
				+ ", isSelected=" + isSelected + ", selectedValue=" + selectedValue
				 + "]";
	}

}

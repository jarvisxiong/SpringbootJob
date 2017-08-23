package com.sistic.be.patron.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Deprecated
public class PatronAdvProfile implements Serializable {
	
	// Temporary Coped from current Booking Engine. Please Review.
	private String acctNum = "";
	private OffsetDateTime dateOfBirth;
	private OffsetDateTime anniversary;
	private String race = "";
	private String religion = "";
	private String nationality = "";
	private String nationalityOther = "";
	private String placeOfBirth = "";
	private String eduLevel = "";
	private String incomeLevel = "";
	private String mStatus = "";
	private Long numOfChildren;
	private String keyword = "";
	private String companyName = "";
	private String occupation = "";
	private String designation = "";
	private String industry = "";
	private String areaOfActivity = "";
	private List tagList;
	private String countryOfOrigin = "";
	private String countryOfOriginOther = "";
	private String whoLastUpdate = "";
	private OffsetDateTime whenLastUpdate;
	private String companyId = "";
	private String tag;
	private String maritalStatus="";
	private String syncResult;
	public String getAcctNum() {
		return acctNum;
	}
	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}
	public OffsetDateTime getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(OffsetDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public OffsetDateTime getAnniversary() {
		return anniversary;
	}
	public void setAnniversary(OffsetDateTime anniversary) {
		this.anniversary = anniversary;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNationalityOther() {
		return nationalityOther;
	}
	public void setNationalityOther(String nationalityOther) {
		this.nationalityOther = nationalityOther;
	}
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	public String getEduLevel() {
		return eduLevel;
	}
	public void setEduLevel(String eduLevel) {
		this.eduLevel = eduLevel;
	}
	public String getIncomeLevel() {
		return incomeLevel;
	}
	public void setIncomeLevel(String incomeLevel) {
		this.incomeLevel = incomeLevel;
	}
	public String getmStatus() {
		return mStatus;
	}
	public void setmStatus(String mStatus) {
		this.mStatus = mStatus;
	}
	public Long getNumOfChildren() {
		return numOfChildren;
	}
	public void setNumOfChildren(Long numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getAreaOfActivity() {
		return areaOfActivity;
	}
	public void setAreaOfActivity(String areaOfActivity) {
		this.areaOfActivity = areaOfActivity;
	}
	public List getTagList() {
		return tagList;
	}
	public void setTagList(List tagList) {
		this.tagList = tagList;
	}
	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}
	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}
	public String getCountryOfOriginOther() {
		return countryOfOriginOther;
	}
	public void setCountryOfOriginOther(String countryOfOriginOther) {
		this.countryOfOriginOther = countryOfOriginOther;
	}
	public String getWhoLastUpdate() {
		return whoLastUpdate;
	}
	public void setWhoLastUpdate(String whoLastUpdate) {
		this.whoLastUpdate = whoLastUpdate;
	}
	public OffsetDateTime getWhenLastUpdate() {
		return whenLastUpdate;
	}
	public void setWhenLastUpdate(OffsetDateTime whenLastUpdate) {
		this.whenLastUpdate = whenLastUpdate;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getSyncResult() {
		return syncResult;
	}
	public void setSyncResult(String syncResult) {
		this.syncResult = syncResult;
	}
	
	

}

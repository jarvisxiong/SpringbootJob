package com.sistic.be.patron.api.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Joiner;

public class TransactionProduct implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7980203644024172709L;
	
	private String name;
	private OffsetDateTime startDate;
	private OffsetDateTime endDate;
	private String priceCategoryNum;
	private String venue;
	private String sectionType;
	private String section;
	private String level;
	private String row;
	private List<Integer> seats;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OffsetDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(OffsetDateTime startDate) {
		this.startDate = startDate;
	}
	public OffsetDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(OffsetDateTime endDate) {
		this.endDate = endDate;
	}
	public String getPriceCategoryNum() {
		return priceCategoryNum;
	}
	public void setPriceCategoryNum(String priceCategoryNum) {
		this.priceCategoryNum = priceCategoryNum;
	}
	public String getVenue() {
		return venue;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public String getSectionType() {
		return sectionType;
	}
	public void setSectionType(String sectionType) {
		this.sectionType = sectionType;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getRow() {
		return row;
	}
	public void setRow(String row) {
		this.row = row;
	}
	public List<Integer> getSeats() {
		return seats;
	}
	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}
	
	@JsonIgnore
	public String getSeatsFormatted() {
		return Joiner.on(",").join(seats);
	}
	
	@Override
	public String toString() {
		return "TransactionProduct [name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", priceCategoryNum=" + priceCategoryNum + ", venue=" + venue + ", sectionType=" + sectionType
				+ ", section=" + section + ", level=" + level + ", row=" + row + ", seats=" + seats + "]";
	}

}

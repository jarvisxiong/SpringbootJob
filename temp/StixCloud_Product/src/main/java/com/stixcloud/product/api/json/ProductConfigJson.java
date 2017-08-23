package com.stixcloud.product.api.json;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.domain.ProductConfig;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"productName",
		"startDateTime",
		"endDateTime",
/*		"startTime",
		"endTime",*/
		"productId",
		"festival",
		"festivalCode",
		"productGroupCode",
		"organization",
		"venueName",
		"productType",
		"productCategory",
		"productCode"})
public class ProductConfigJson implements Serializable {

	private static final long serialVersionUID = 1120565048005048076L;

	private String productName, startDate, endDate, startTime, endTime, festival, festivalCode, productGroupCode,
			organization, venueName, productCategory, productCode;

	private OffsetDateTime startDateTime, endDateTime;
	
	private Long productId;

	private List<String> productType = new ArrayList<String>();

	public ProductConfigJson(ProductConfig pc) {
		this.productName = pc.getProductName();
		this.startDateTime = pc.getStartDateTime();
		this.endDateTime = pc.getEndDateTime();
		this.festival = pc.getFestival();
		this.festivalCode = pc.getFestivalCode();
		this.productGroupCode = pc.getProductGroupCode();
		this.venueName = pc.getVenueName();
		this.organization = pc.getOrganization();
		this.productCategory = pc.getProductCategory();
		this.productCode = pc.getProductCode();
		this.productId = pc.getProductId();
	}

	public String getProductName() {
		return productName;
	}

	public OffsetDateTime getStartDateTime() {
		return startDateTime;
	}


	public void setStartDateTime(OffsetDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}


	public OffsetDateTime getEndDateTime() {
		return endDateTime;
	}


	public void setEndDateTime(OffsetDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getFestival() {
		return festival;
	}

	public void setFestival(String festival) {
		this.festival = festival;
	}

	public String getFestivalCode() {
		return festivalCode;
	}

	public void setFestivalCode(String festivalCode) {
		this.festivalCode = festivalCode;
	}

	public String getProductGroupCode() {
		return productGroupCode;
	}

	public void setProductGroupCode(String productGroupCode) {
		this.productGroupCode = productGroupCode;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getProductType() {
		return productType;
	}

	public void setProductType(List<String> productType) {
		this.productType = productType;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
				.append("productName", getProductName())
				.append("startDateTime", getStartDateTime())
				.append("endDateTime", getEndDateTime())
				.append("productId", getProductId())
				.append("festival", getFestival())
				.append("festivalCode", getFestivalCode())
				.append("productGroupCode", getProductGroupCode())
				.append("venueName", getVenueName())
				.append("eventType", getProductType())
				.append("organization", getOrganization())
				.append("productCategory", getProductCategory())
				.append("productCode", getProductCode())
				.toString();
	}
}

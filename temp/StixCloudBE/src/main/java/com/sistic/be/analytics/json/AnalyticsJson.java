package com.sistic.be.analytics.json;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.money.CurrencyUnit;

import com.sistic.be.analytics.builder.AnalyticsJsonBuilder;

public class AnalyticsJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9026845352831341842L;
	
	private String icc;
	private Long productId;
	private String productName;
	private OffsetDateTime productDate;
	private String venue;
	private String priceClassCode;
	private String priceClassName;
	private Integer quantity;
	private String unitPrice;
	private CurrencyUnit currency;
	
	public AnalyticsJson() {}
	
	public AnalyticsJson(AnalyticsJsonBuilder builder) {
		this.icc = builder.icc;
		this.productId = builder.productId;
		this.productName = builder.productName;
		this.productDate = builder.productDate;
		this.venue = builder.venue;
		this.priceClassCode = builder.priceClassCode;
		this.priceClassName = builder.priceClassName;
		this.quantity = builder.quantity;
		this.unitPrice = builder.unitPrice;
		this.currency = builder.currency;		
	}

	public String getIcc() {
		return icc;
	}

	public void setIcc(String icc) {
		this.icc = icc;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public OffsetDateTime getProductDate() {
		return productDate;
	}

	public void setProductDate(OffsetDateTime productDate) {
		this.productDate = productDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getPriceClassCode() {
		return priceClassCode;
	}

	public void setPriceClassCode(String priceClassCode) {
		this.priceClassCode = priceClassCode;
	}

	public String getPriceClassName() {
		return priceClassName;
	}

	public void setPriceClassName(String priceClassName) {
		this.priceClassName = priceClassName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public CurrencyUnit getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencyUnit currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "AnalyticsJson [icc=" + icc + ", productId=" + productId + ", productName=" + productName
				+ ", productDate=" + productDate + ", venue=" + venue + ", priceClassCode=" + priceClassCode
				+ ", priceClassName=" + priceClassName + ", quantity=" + quantity + ", unitPrice=" + unitPrice
				+ ", currency=" + currency + "]";
	}

}

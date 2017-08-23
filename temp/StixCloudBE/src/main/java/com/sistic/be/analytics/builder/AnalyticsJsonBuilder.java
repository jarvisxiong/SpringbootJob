package com.sistic.be.analytics.builder;

import java.time.OffsetDateTime;

import javax.money.CurrencyUnit;

import com.sistic.be.analytics.json.AnalyticsJson;

public class AnalyticsJsonBuilder {
	
	public String icc;
	public Long productId;
	public String productName;
	public OffsetDateTime productDate;
	public String venue;
	public String priceClassCode;
	public String priceClassName;
	public Integer quantity;
	public String unitPrice;
	public CurrencyUnit currency;
	
	public AnalyticsJsonBuilder httpStatus(String icc) {
		this.icc = icc;
		return this;
	}
	public AnalyticsJsonBuilder productId(Long productId) {
		this.productId = productId;
		return this;
	}
	public AnalyticsJsonBuilder productName(String productName) {
		this.productName = productName;
		return this;
	}
	public AnalyticsJsonBuilder productDate(OffsetDateTime productDate) {
		this.productDate = productDate;
		return this;
	}
	public AnalyticsJsonBuilder venue(String venue) {
		this.venue = venue;
		return this;
	}
	public AnalyticsJsonBuilder priceClassCode(String priceClassCode) {
		this.priceClassCode = priceClassCode;
		return this;
	}
	public AnalyticsJsonBuilder priceClassName(String priceClassName) {
		this.priceClassName = priceClassName;
		return this;
	}
	public AnalyticsJsonBuilder quantity(Integer quantity) {
		this.quantity = quantity;
		return this;
	}
	public AnalyticsJsonBuilder unitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
		return this;
	}
	public AnalyticsJsonBuilder currency(CurrencyUnit currency) {
		this.currency = currency;
		return this;
	}
	
	public AnalyticsJson build() {
		return new AnalyticsJson(this);
	}
}

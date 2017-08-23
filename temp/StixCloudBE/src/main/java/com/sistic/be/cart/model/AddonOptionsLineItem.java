package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.List;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;

public class AddonOptionsLineItem extends LineItem{
	
	private static final long serialVersionUID = 1017862994391189424L;

	public AddonOptionsLineItem() {
		super("ADDON");
	}

	private String addonType;
	private Long productID;
	private String productDescription;
	private String productMessage;
	private String lightboxContent;
	private Boolean showLightbox;

	public String getAddonType() {
		return addonType;
	}
	public void setAddonType(String addonType) {
		this.addonType = addonType;
	}
	public Boolean getShowLightbox() {
		return showLightbox;
	}
	public void setShowLightbox(Boolean showLightbox) {
		this.showLightbox = showLightbox;
	}
	public String getLightboxContent() {
		return lightboxContent;
	}
	public void setLightboxContent(String lightboxContent) {
		this.lightboxContent = lightboxContent;
	}
	private List<AddonPriceTable> priceTableList;
	public Long getProductID() {
		return productID;
	}
	public void setProductID(Long productID) {
		this.productID = productID;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductMessage() {
		return productMessage;
	}
	public void setProductMessage(String productMessage) {
		this.productMessage = productMessage;
	}
	public List<AddonPriceTable> getPriceTableList() {
		return priceTableList;
	}
	public void setPriceTableList(List<AddonPriceTable> priceTableList) {
		this.priceTableList = priceTableList;
	}

	@JsonIgnore
	public List<PriceCat> addonOptions() {
		List<AddonPriceTable> tmp = getPriceTableList();
		if(tmp != null && tmp.size() > 0){
			return tmp.get(0).getPriceCatList();
		}
		return null;
	}

	@JsonIgnore
	public String getPriceClassCode(){
		List<AddonPriceTable> tmp = getPriceTableList();
		if(tmp != null && tmp.size() > 0){
			return tmp.get(0).getPriceClassCode();
		}
		return null;
	}

}

class AddonPriceTable implements Serializable{

	private static final long serialVersionUID = -7466278683162041627L;
	
	private String priceClassCode;
	private String priceClassAlias;
	private List<PriceCat> priceCatList;

	public String getPriceClassCode() {
		return priceClassCode;
	}
	public void setPriceClassCode(String priceClassCode) {
		this.priceClassCode = priceClassCode;
	}
	public String getPriceClassAlias() {
		return priceClassAlias;
	}
	public void setPriceClassAlias(String priceClassAlias) {
		this.priceClassAlias = priceClassAlias;
	}
	public List<PriceCat> getPriceCatList() {
		return priceCatList;
	}
	public void setPriceCatList(List<PriceCat> priceCatList) {
		this.priceCatList = priceCatList;
	}

}

class PriceCat implements Serializable{

	private static final long serialVersionUID = 5573807029751269948L;
	
	private Long priceCatNum;
	private MonetaryAmount priceValue;
	private Long priceStatus;
	private Long priceCategoryId;
	private Long seatSectionId;

	public Long getPriceCatNum() {
		return priceCatNum;
	}
	public void setPriceCatNum(Long priceCatNum) {
		this.priceCatNum = priceCatNum;
	}
	public MonetaryAmount getPriceValue() {
		return priceValue;
	}
	public void setPriceValue(MonetaryAmount priceValue) {
		this.priceValue = priceValue;
	}
	public Long getPriceStatus() {
		return priceStatus;
	}
	public void setPriceStatus(Long priceStatus) {
		this.priceStatus = priceStatus;
	}
	public Long getPriceCategoryId() {
		return priceCategoryId;
	}
	public void setPriceCategoryId(Long priceCategoryId) {
		this.priceCategoryId = priceCategoryId;
	}
	public Long getSeatSectionId() {
		return seatSectionId;
	}
	public void setSeatSectionId(Long seatSectionId) {
		this.seatSectionId = seatSectionId;
	}

	@JsonIgnore
	public String getPriceValueFormatted() {
		return MoneyUtil.getFormattedMonetaryString(priceValue);
	}
}

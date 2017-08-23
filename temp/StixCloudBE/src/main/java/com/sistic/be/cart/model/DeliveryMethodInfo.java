/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Locale;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.javamoney.moneta.Money;

import com.sistic.be.patron.model.MailingAddress;

/**
 * @author jianhong
 *
 */
public class DeliveryMethodInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1952646560585623502L;
	private String code;
	private String alias;
	private String description;
	private String deliveryType;
	private MonetaryAmount charge;
	private int order;	// default 0
	private boolean isAddressRequired;
	private boolean isMobileNumberRequired;
	private boolean isFeeWaived;
	private boolean isEmailRequired;
	private boolean isApplicable;
	private MailingAddress mailingAddress;
	private String mobileNumber;
//	private String eTicketLink;	// what is this for?
	private OffsetDateTime expiryDate;	// what?
	private boolean isDefault;
	
	public DeliveryMethodInfo() {
		
	}

	public DeliveryMethodInfo(String code, String alias, String description, String deliveryType, MonetaryAmount charge,
			int order, boolean isAddressRequired, boolean isMobileNumberRequired, boolean isFeeWaived,
			boolean isEmailRequired, boolean isApplicable, MailingAddress mailingAddress, String mobileNumber,
			OffsetDateTime expiryDate, boolean isDefault) {
		super();
		this.code = code;
		this.alias = alias;
		this.description = description;
		this.deliveryType = deliveryType;
		this.charge = charge;
		this.order = order;
		this.isAddressRequired = isAddressRequired;
		this.isMobileNumberRequired = isMobileNumberRequired;
		this.isFeeWaived = isFeeWaived;
		this.isEmailRequired = isEmailRequired;
		this.isApplicable = isApplicable;
		this.mailingAddress = mailingAddress;
		this.mobileNumber = mobileNumber;
		this.expiryDate = expiryDate;
		this.isDefault = isDefault;
	}

	/**
	 * TODO: remove the setChargeBigDecimal
	 */
	public DeliveryMethodInfo(String code, String deliveryType, BigDecimal charge, String description) {
		super();
		this.code = code;
		this.description = description;
		this.deliveryType = deliveryType;
		this.setChargeBigDecimal(charge);
	}

	public String getCode() {
		return code;
	}
	

	public void setCode(String code) {
		this.code = code;
	}


	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDeliveryType() {
		return deliveryType;
	}


	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}


	public MonetaryAmount getCharge() {
		return charge;
	}


	public void setCharge(MonetaryAmount charge) {
		this.charge = charge;
	}
	
	public void setChargeBigDecimal(BigDecimal charge) {
		CurrencyUnit currencyUnit = Monetary.getCurrency(Locale.getDefault());
		this.charge = Money.of(charge, currencyUnit);
	}


	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}


	public boolean isAddressRequired() {
		return isAddressRequired;
	}


	public void setAddressRequired(boolean isAddressRequired) {
		this.isAddressRequired = isAddressRequired;
	}


	public boolean isMobileNumberRequired() {
		return isMobileNumberRequired;
	}


	public void setMobileNumberRequired(boolean isMobileNumberRequired) {
		this.isMobileNumberRequired = isMobileNumberRequired;
	}


	public boolean isFeeWaived() {
		return isFeeWaived;
	}


	public void setFeeWaived(boolean isFeeWaived) {
		this.isFeeWaived = isFeeWaived;
	}


	public boolean isEmailRequired() {
		return isEmailRequired;
	}


	public void setEmailRequired(boolean isEmailRequired) {
		this.isEmailRequired = isEmailRequired;
	}


	public boolean isApplicable() {
		return isApplicable;
	}


	public void setApplicable(boolean isApplicable) {
		this.isApplicable = isApplicable;
	}


	public MailingAddress getMailingAddress() {
		return mailingAddress;
	}


	public void setMailingAddress(MailingAddress mailingAddress) {
		this.mailingAddress = mailingAddress;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public OffsetDateTime getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(OffsetDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}


	public boolean isDefault() {
		return isDefault;
	}


	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	

}

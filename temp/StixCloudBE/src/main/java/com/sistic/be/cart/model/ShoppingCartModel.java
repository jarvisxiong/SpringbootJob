/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.membership.api.model.MembershipBenefit;
import com.sistic.be.membership.api.model.MembershipRedemption;
import com.sistic.be.membership.api.model.ProfileConfig;

/**
 * @author jianhong
 * This ShoppingCartModel may be renamed to ShoppingCart to replace the test class
 *
 */

public class ShoppingCartModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -347772380521005194L;

	private List<ProductLineItem> lineItemList;	// view_shoppingcart_include.jsp in BE, ProductAvailInfoFResponse class
	private List<FeeLineItem> feeLineItems;
	private List<AddonOptionsLineItem> addOnLineItems;
	private MonetaryAmount lineItemTotal;
	private MonetaryAmount ticketProtectorAmount;
	private CommonPaymentMethod commonPaymentMethod;
	private CommonDeliveryMethod commonDeliveryMethod;
	private CurrencyUnit currencyUnit;
	private List<MembershipBenefit> membershipBenefit;
	private MembershipRedemption membershipRedemption;
	private List<ProfileConfig> membershipProfileConfig;

	@JsonIgnore
	public String getLineItemsTotalPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(lineItemTotal);
	}

	@JsonIgnore
	public String getTicketProtectorPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(ticketProtectorAmount);
	}

	@JsonIgnore
	public List<LineItem> displayLineItems() {
		List<LineItem> lineItems = new ArrayList<LineItem>();
		if (lineItemList != null && !lineItemList.isEmpty()) {
			lineItems.addAll(this.lineItemList);
		}
		if (feeLineItems != null && !feeLineItems.isEmpty()) {
			lineItems.addAll(this.feeLineItems);
		}

		if (addOnLineItems != null && addOnLineItems.size() > 0) {
			lineItems.addAll(this.addOnLineItems);
		}
		return lineItems;
	}

	@JsonIgnore
	public ProductLineItem convertToProduct(LineItem lineItem) {
		if (lineItem instanceof ProductLineItem) {
			return (ProductLineItem) lineItem;
		} else {
			return null;
		}
	}

	@JsonIgnore
	public FeeLineItem convertToFee(LineItem lineItem) {
		if (lineItem instanceof FeeLineItem) {
			return (FeeLineItem) lineItem;
		} else {
			return null;
		}
	}

	public List<ProductLineItem> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<ProductLineItem> lineItemList) {
		this.lineItemList = lineItemList;
	}

	public MonetaryAmount getLineItemTotal() {
		return lineItemTotal;
	}
	
	public List<AddonOptionsLineItem> getAddOnLineItems() {
		return addOnLineItems;
	}

	public void setAddOnLineItems(List<AddonOptionsLineItem> addOnLineItems) {
		this.addOnLineItems = addOnLineItems;
	}

	public void setLineItemTotal(MonetaryAmount lineItemTotal) {
		this.lineItemTotal = lineItemTotal;
	}

	public MonetaryAmount getTicketProtectorAmount() {
		return ticketProtectorAmount;
	}

	public void setTicketProtectorAmount(MonetaryAmount ticketProtectorAmount) {
		this.ticketProtectorAmount = ticketProtectorAmount;
	}

	public CommonPaymentMethod getCommonPaymentMethod() {
		return commonPaymentMethod;
	}

	public void setCommonPaymentMethod(CommonPaymentMethod commonPaymentMethod) {
		this.commonPaymentMethod = commonPaymentMethod;
	}

	public CommonDeliveryMethod getCommonDeliveryMethod() {
		return commonDeliveryMethod;
	}

	public void setCommonDeliveryMethod(CommonDeliveryMethod commonDeliveryMethod) {
		this.commonDeliveryMethod = commonDeliveryMethod;
	}

	public CurrencyUnit getCurrencyUnit() {
		return currencyUnit;
	}

	public void setCurrencyUnit(CurrencyUnit currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public List<FeeLineItem> getFeeLineItems() {
		return feeLineItems;
	}

	public void setFeeLineItems(List<FeeLineItem> feeLineItems) {
		this.feeLineItems = feeLineItems;
	}

	public List<MembershipBenefit> getMembershipBenefit() {
		return membershipBenefit;
	}

	public void setMembershipBenefit(List<MembershipBenefit> membershipBenefit) {
		this.membershipBenefit = membershipBenefit;
	}	

	public MembershipRedemption getMembershipRedemption() {
		return membershipRedemption;
	}

	public void setMembershipRedemption(MembershipRedemption membershipRedemption) {
		this.membershipRedemption = membershipRedemption;
	}

	public List<ProfileConfig> getMembershipProfileConfig() {
		return membershipProfileConfig;
	}

	public void setMembershipProfileConfig(List<ProfileConfig> membershipProfileConfig) {
		this.membershipProfileConfig = membershipProfileConfig;
	}

	@Override
	public String toString() {
		return "ShoppingCartModel [lineItemList=" + lineItemList + ", feeLineItems=" + feeLineItems
				+ ", addOnLineItems=" + addOnLineItems + ", lineItemTotal=" + lineItemTotal + ", ticketProtectorAmount="
				+ ticketProtectorAmount + ", commonPaymentMethod=" + commonPaymentMethod + ", commonDeliveryMethod="
				+ commonDeliveryMethod + ", currencyUnit=" + currencyUnit + ", membershipBenefit=" + membershipBenefit
				+ ", membershipRedemption=" + membershipRedemption + ", membershipProfileConfig="
				+ membershipProfileConfig + "]";
	}
		
}

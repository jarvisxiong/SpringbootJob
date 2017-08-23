package com.sistic.be.sales.order;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.cart.model.DeliveryMethod;
import com.sistic.be.cart.model.ShoppingCartModel;

public class TransactionOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2287677093351736267L;
	
	private String emailAddress;
	private ShoppingCartModel shoppingCartModel;	// TODO: to be refactored and get rid of unused data
	private String transactionRefNumber;
	private String firstName;
	private String lastName;
	private OffsetDateTime purchaseDate;
	private String acctNum;
	private DeliveryMethod deliveryMethod;
	private List<TransactionOrderPaymentDetails> transactionOrderPaymentDetailsList;
	private MonetaryAmount totalPayment;
	
	@JsonIgnore
	public CurrencyUnit getCurrencyUnit() {
		return totalPayment.getCurrency();
	}
	
	@JsonIgnore
	public String getTotalPaymentPriceFormatted() {
		return MoneyUtil.getFormattedMonetaryString(totalPayment);
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public ShoppingCartModel getShoppingCartModel() {
		return shoppingCartModel;
	}

	public void setShoppingCartModel(ShoppingCartModel shoppingCartModel) {
		this.shoppingCartModel = shoppingCartModel;
	}

	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	public void setTransactionRefNumber(String transactionRefNumber) {
		this.transactionRefNumber = transactionRefNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public OffsetDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(OffsetDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public String getAcctNum() {
		return acctNum;
	}

	public void setAcctNum(String acctNum) {
		this.acctNum = acctNum;
	}

	public MonetaryAmount getTotalPayment() {
		return totalPayment;
	}
	
	public DeliveryMethod getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(DeliveryMethod deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public void setTotalPayment(MonetaryAmount totalPayment) {
		this.totalPayment = totalPayment;
	}
	
	public List<TransactionOrderPaymentDetails> getTransactionOrderPaymentDetailsList() {
		return transactionOrderPaymentDetailsList;
	}

	public void setTransactionOrderPaymentDetailsList(
			List<TransactionOrderPaymentDetails> transactionOrderPaymentDetailsList) {
		this.transactionOrderPaymentDetailsList = transactionOrderPaymentDetailsList;
	}

	@Override
	public String toString() {
		return "TransactionOrder [emailAddress=" + emailAddress + ", shoppingCartModel=" + shoppingCartModel
				+ ", transactionRefNumber=" + transactionRefNumber + ", firstName=" + firstName + ", lastName="
				+ lastName + ", purchaseDate=" + purchaseDate + ", acctNum=" + acctNum + ", deliveryMethod="
				+ deliveryMethod + ", transactionOrderPaymentDetailsList=" + transactionOrderPaymentDetailsList
				+ ", totalPayment=" + totalPayment + "]";
	}

}

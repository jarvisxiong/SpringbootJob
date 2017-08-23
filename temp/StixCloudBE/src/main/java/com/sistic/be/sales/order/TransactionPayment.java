package com.sistic.be.sales.order;

import java.io.Serializable;
import java.util.List;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sistic.be.cart.model.DeliveryMethod;

public class TransactionPayment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7742408747624137621L;
	
	private List<String> paymentMethodCodes;
	private String messageId;
	private String transactionRefNumber;
	private MonetaryAmount payableAmount;
	private CreditCardInfo creditCardInfo;
	private DeliveryMethod selectedDeliveryMethod;
	@JsonProperty("isFullyRedeemed")
	private Boolean isFullyRedeemed;
	
	public TransactionPayment() {
		
	}
	
	public TransactionPayment(String messageId, String transactionRefNumber) {
		this.messageId = messageId;
		this.transactionRefNumber = transactionRefNumber;
	}

	public List<String> getPaymentMethodCodes() {
		return paymentMethodCodes;
	}

	public void setPaymentMethodCodes(List<String> paymentMethodCodes) {
		this.paymentMethodCodes = paymentMethodCodes;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	public void setTransactionRefNumber(String transactionRefNumber) {
		this.transactionRefNumber = transactionRefNumber;
	}

	public MonetaryAmount getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(MonetaryAmount payableAmount) {
		this.payableAmount = payableAmount;
	}

	public CreditCardInfo getCreditCardInfo() {
		return creditCardInfo;
	}

	public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
		this.creditCardInfo = creditCardInfo;
	}

	public DeliveryMethod getSelectedDeliveryMethod() {
		return selectedDeliveryMethod;
	}

	public void setSelectedDeliveryMethod(DeliveryMethod selectedDeliveryMethod) {
		this.selectedDeliveryMethod = selectedDeliveryMethod;
	}

	public Boolean getIsFullyRedeemed() {
		return isFullyRedeemed;
	}

	public void setIsFullyRedeemed(Boolean isFullyRedeemed) {
		this.isFullyRedeemed = isFullyRedeemed;
	}

	@Override
	public String toString() {
		return "TransactionPayment [paymentMethodCodes=" + paymentMethodCodes + ", messageId=" + messageId
				+ ", transactionRefNumber=" + transactionRefNumber + ", payableAmount=" + payableAmount
				+ ", creditCardInfo=" + creditCardInfo + ", selectedDeliveryMethod=" + selectedDeliveryMethod
				+ ", isFullyRedeemed=" + isFullyRedeemed + "]";
	}

}

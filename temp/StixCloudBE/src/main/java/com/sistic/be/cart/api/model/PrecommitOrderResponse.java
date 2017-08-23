package com.sistic.be.cart.api.model;

import java.io.Serializable;

import javax.money.MonetaryAmount;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PrecommitOrderResponse extends JsonResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7826782884634966805L;
	
	@JsonProperty("messageId")
	private String messageId;
	@JsonProperty("transactionRefNumber")
	private String transactionRefNumber;
	@JsonProperty("paymentMethodCode")
	private String paymentMethodCode;
	@JsonProperty("payableAmount")
	private MonetaryAmount payableAmount;
	@JsonProperty("isFullyRedeemed")
	private Boolean isFullyRedeemed;
	
	
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
	public String getPaymentMethodCode() {
		return paymentMethodCode;
	}
	public void setPaymentMethodCode(String paymentMethodCode) {
		this.paymentMethodCode = paymentMethodCode;
	}
	public MonetaryAmount getPayableAmount() {
		return payableAmount;
	}
	public void setPayableAmount(MonetaryAmount payableAmount) {
		this.payableAmount = payableAmount;
	}
	public Boolean getIsFullyRedeemed() {
		return isFullyRedeemed;
	}
	public void setIsFullyRedeemed(Boolean isFullyRedeemed) {
		this.isFullyRedeemed = isFullyRedeemed;
	}
	
	@Override
	public String toString() {
		return "PrecommitOrderResponse [messageId=" + messageId + ", transactionRefNumber=" + transactionRefNumber
				+ ", paymentMethodCode=" + paymentMethodCode + ", payableAmount=" + payableAmount + ", isFullyRedeemed="
				+ isFullyRedeemed + "]";
	}
	
}

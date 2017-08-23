package com.sistic.be.payment.model;

import java.util.HashMap;
import java.util.Map;

import javax.money.MonetaryAmount;

import com.sistic.be.sales.order.CreditCardInfo;

public abstract class AbstractPayment3DSRequest {
	
	protected String runProfile;
	protected String clientServerURL;
	protected Map<String, String> properties = new HashMap<>();
	protected CreditCardInfo creditCardInfo;
	protected MonetaryAmount payableAmount;
	protected String transactionRefNumber;
	public abstract Map<String, String> getProperties();
	public abstract  String getClientServerURL() ;
	public abstract  Map<String, String> toVpcFieldsMap();
	
}

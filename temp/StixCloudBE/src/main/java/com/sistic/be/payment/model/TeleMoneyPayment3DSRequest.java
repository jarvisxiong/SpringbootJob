package com.sistic.be.payment.model;

import java.util.Map;
import java.util.TreeMap;

import javax.money.MonetaryAmount;

import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.payment.constants.PaymentGatewayConstants;
import com.sistic.be.sales.order.CreditCardInfo;

public class TeleMoneyPayment3DSRequest extends AbstractPayment3DSRequest{


	public TeleMoneyPayment3DSRequest() {
	}

	public TeleMoneyPayment3DSRequest(Map<String,String> paymentConfigMap, CreditCardInfo creditCardInfo, MonetaryAmount payableAmount, String transactionRefNumber, String runProfile) {
		this.properties=paymentConfigMap;
		this.creditCardInfo = creditCardInfo;
		this.payableAmount = payableAmount;
		this.transactionRefNumber = transactionRefNumber;
		this.clientServerURL=paymentConfigMap.get(PaymentGatewayConstants.CLIENT_SERVER_URL_KEY);
		this.runProfile = runProfile;
	}

	@Override
	public Map<String, String> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
	@Override
	public String getClientServerURL() {
		return clientServerURL;
	}
	public void setClientServerURL(String clientServerURL) {
		this.clientServerURL = clientServerURL;
	}

	public Map<String, String> toVpcFieldsMap() {

		TreeMap<String, String> vpcFieldsMap = new TreeMap<String, String>();

		vpcFieldsMap.put("mid",this.properties.get("merchant.id"));
		vpcFieldsMap.put("cur", "SGD");
		vpcFieldsMap.put("amt", payableAmount.getNumber().toString());
		vpcFieldsMap.put("ccdate", this.creditCardInfo.getCreditCardExpiryYear() + this.creditCardInfo.getCreditCardExpiryMonth());
		vpcFieldsMap.put("ccnum", this.creditCardInfo.getCreditCardNo());
		vpcFieldsMap.put("paytype", "5");
		vpcFieldsMap.put("transtype", "vereq");
		vpcFieldsMap.put("ref", this.transactionRefNumber);
		vpcFieldsMap.put("cccvv", this.creditCardInfo.getCreditCardCSC());
		vpcFieldsMap.put("returnurl", WebUrlUtil.getURLWithContextPathWithTenantCode()+this.properties.get("return.url"));
		String hashKey =this.properties.get("hash.key");
		return vpcFieldsMap;
	} 
}

package com.sistic.be.payment.model;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import javax.money.MonetaryAmount;
import javax.ws.rs.InternalServerErrorException;

import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.app.util.PaymentUtil;
import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.payment.constants.PaymentGatewayConstants;
import com.sistic.be.sales.order.CreditCardInfo;

public class MIGSPayment3DSRequest extends AbstractPayment3DSRequest{

	public MIGSPayment3DSRequest() {
	}

	public MIGSPayment3DSRequest(Map<String,String> paymentConfigMap, CreditCardInfo creditCardInfo, MonetaryAmount payableAmount, String transactionRefNumber, String runProfile) {
		this.properties=paymentConfigMap;
		this.creditCardInfo = creditCardInfo;
		this.payableAmount = payableAmount;
		this.transactionRefNumber = transactionRefNumber;
		this.clientServerURL = paymentConfigMap.get(PaymentGatewayConstants.CLIENT_SERVER_URL_KEY);
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
	@Override
	public Map<String, String> toVpcFieldsMap() {

		TreeMap<String, String> vpcFieldsMap = new TreeMap<String, String>();

		vpcFieldsMap.put("vpc_AccessCode", this.properties.get("merchant.access.code"));
		vpcFieldsMap.put("vpc_Amount", MoneyUtil.getAmountInCents(payableAmount).toString());
		vpcFieldsMap.put("vpc_CardExp", this.creditCardInfo.getCreditCardExpiryYear() + this.creditCardInfo.getCreditCardExpiryMonth());
		vpcFieldsMap.put("vpc_CardNum", this.creditCardInfo.getCreditCardNo());
		vpcFieldsMap.put("vpc_CardSecurityCode", this.creditCardInfo.getCreditCardCSC());
		vpcFieldsMap.put("vpc_Command", "pay");
		vpcFieldsMap.put("vpc_Locale", "en");
		vpcFieldsMap.put("vpc_MerchTxnRef", this.transactionRefNumber /*+ "mtr"*/);
		vpcFieldsMap.put("vpc_Merchant",  this.properties.get("merchant.id"));
		vpcFieldsMap.put("vpc_OrderInfo", this.transactionRefNumber /*+ "oi"*/);
		vpcFieldsMap.put("vpc_ReturnURL",WebUrlUtil.getURLWithContextPathWithTenantCode()+this.properties.get("return.url"));
		vpcFieldsMap.put("vpc_Version", "1");
		vpcFieldsMap.put("vpc_card", PaymentUtil.getVpcCardType(this.creditCardInfo.getCreditCardType()));
		vpcFieldsMap.put("vpc_gateway",  this.properties.get("secure.type"));
		
		/**
		 * @Deprecated - due to return url being resolved from relative path
		 * Condition to allow for return url to be localhost when using DEV profile
		 * put function will override value with the same key
		 */
//		if (runProfile != null && runProfile.equals(ProfileConsts.DEV)) {
//			// TODO: url to be configured in application-dev.properties
//			vpcFieldsMap.put("vpc_ReturnURL", "http://localhost:8080/be/v0/confirmorder/return3ds");
//		}

		/**
		 * Compute the vpc_SecureHash using the previous fields
		 */
		String secret = this.properties.get("secure.code");
		String secureHash;
		if(secret!=null && secret.length()>0) {
		try {
			secureHash = PaymentUtil.calculateSecureHash(secret, vpcFieldsMap);
			vpcFieldsMap.put("vpc_SecureHash", secureHash);
			vpcFieldsMap.put("vpc_SecureHashType", "SHA256");
		} catch (InvalidKeyException | NoSuchAlgorithmException | IllegalStateException
				| UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new InternalServerErrorException("Failed to generate secure hash", e);
		}
		}

		return vpcFieldsMap;
	}
}

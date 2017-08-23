package com.sistic.be.payment.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.money.MonetaryAmount;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.payment.constants.PaymentGatewayConstants;
import com.sistic.be.sales.order.CreditCardInfo;

public class CUPPurchasePaymentRequest extends AbstractPayment3DSRequest{


	public CUPPurchasePaymentRequest() {
	}

	public CUPPurchasePaymentRequest(Map<String,String> paymentConfigMap, CreditCardInfo creditCardInfo, MonetaryAmount payableAmount, String transactionRefNumber, String runProfile) {
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
	@Override
	public Map<String, String> toVpcFieldsMap() {
		TreeMap<String, String> vpcFieldsMap = new TreeMap<String, String>();
		//String orderTime = (String) JspUtil.getSessionAttribute(request, orderId+"orderTime");
		String orderTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		vpcFieldsMap.put("backEndUrl",WebUrlUtil.getURLWithContextPathWithTenantCode()+properties.get("newbe.backend.url"));
		vpcFieldsMap.put("charset", properties.get("cup.encode"));
		vpcFieldsMap.put("commodityUrl", "");//optional
		vpcFieldsMap.put("customerIp", request.getRemoteAddr());
		vpcFieldsMap.put("customerName", "");//optional
		vpcFieldsMap.put("defaultBankNumber", "");//optional
		vpcFieldsMap.put("defaultPayType", "");//optional
		vpcFieldsMap.put("frontEndUrl",WebUrlUtil.getURLWithContextPathWithTenantCode()+properties.get("newbe.frontend.url"));
		vpcFieldsMap.put("merAbbr",  properties.get("merchant.name"));
		vpcFieldsMap.put("merId",properties.get("merchant.user.id"));
		vpcFieldsMap.put("merReserved", "");//optional
		vpcFieldsMap.put("orderAmount",  MoneyUtil.getAmountInCents(payableAmount).toString());
		vpcFieldsMap.put("orderNumber", transactionRefNumber);
		vpcFieldsMap.put("orderTime", orderTime);
		vpcFieldsMap.put("origQid", "");
		vpcFieldsMap.put("transTimeout",properties.get("sales.con.timeout"));//optional
		vpcFieldsMap.put("transType", "01");//01 - Purchase. 04 - Refund
		vpcFieldsMap.put("version", properties.get("cup.version"));
		String postDataWoSig = buildPostData(vpcFieldsMap, false);
		vpcFieldsMap.put("signature", buildCUPSignature(properties.get("merchant.password"),postDataWoSig));
		vpcFieldsMap.put("signMethod", properties.get("cup.sign.method"));

		return  vpcFieldsMap;
	}

	public String buildPostData(TreeMap<String, String> fieldMap, boolean includeSignature) {
		StringBuilder sb = new StringBuilder();

		sb.append("backEndUrl=");
		if (StringUtils.isNotBlank(fieldMap.get("backEndUrl"))) sb.append(fieldMap.get("backEndUrl"));

		sb.append("&charset=");
		if (StringUtils.isNotBlank(fieldMap.get("charset"))) sb.append(fieldMap.get("charset"));

		sb.append("&commodityUrl=");
		if (StringUtils.isNotBlank(fieldMap.get("commodityUrl"))) sb.append(fieldMap.get("commodityUrl"));

		sb.append("&customerIp=");
		if (StringUtils.isNotBlank(fieldMap.get("customerIp"))) sb.append(fieldMap.get("customerIp"));

		sb.append("&customerName=");
		if (StringUtils.isNotBlank(fieldMap.get("customerName"))) sb.append(fieldMap.get("customerName"));

		sb.append("&defaultBankNumber=");
		if (StringUtils.isNotBlank(fieldMap.get("defaultBankNumber"))) sb.append(fieldMap.get("defaultBankNumber"));

		sb.append("&defaultPayType=");
		if (StringUtils.isNotBlank(fieldMap.get("defaultPayType"))) sb.append(fieldMap.get("defaultPayType"));

		sb.append("&frontEndUrl=");
		if (StringUtils.isNotBlank(fieldMap.get("frontEndUrl"))) sb.append(fieldMap.get("frontEndUrl"));

		sb.append("&merAbbr=");
		if (StringUtils.isNotBlank(fieldMap.get("merAbbr"))) sb.append(fieldMap.get("merAbbr"));

		sb.append("&merId=");
		if (StringUtils.isNotBlank(fieldMap.get("merId"))) sb.append(fieldMap.get("merId"));

		sb.append("&merReserved=");
		if (StringUtils.isNotBlank(fieldMap.get("merReserved"))) sb.append(fieldMap.get("merReserved"));

		sb.append("&orderAmount=");
		if (StringUtils.isNotBlank(fieldMap.get("orderAmount"))) sb.append(fieldMap.get("orderAmount"));

		sb.append("&orderNumber=");
		if (StringUtils.isNotBlank(fieldMap.get("orderNumber"))) sb.append(fieldMap.get("orderNumber"));

		sb.append("&orderTime=");
		if (StringUtils.isNotBlank(fieldMap.get("orderTime"))) sb.append(fieldMap.get("orderTime"));

		sb.append("&origQid=");
		if (StringUtils.isNotBlank(fieldMap.get("origQid"))) sb.append(fieldMap.get("origQid"));

		if (includeSignature && StringUtils.isNotBlank(fieldMap.get("signature")))
			sb.append("&signature=").append(fieldMap.get("signature"));
		if (includeSignature && StringUtils.isNotBlank(fieldMap.get("signMethod")))
			sb.append("&signMethod=").append(fieldMap.get("signMethod"));

		sb.append("&transTimeout=");
		if (StringUtils.isNotBlank(fieldMap.get("transTimeout"))) sb.append(fieldMap.get("transTimeout"));

		sb.append("&transType=");
		if (StringUtils.isNotBlank(fieldMap.get("transType"))) sb.append(fieldMap.get("transType"));

		sb.append("&version=");
		if (StringUtils.isNotBlank(fieldMap.get("version"))) sb.append(fieldMap.get("version"));

		return sb.toString();
	}

	public String buildCUPSignature(String password,String request) {
		password=DigestUtils.sha256Hex(password);
		String paramsWithoutSignature = request.concat("&" + password);
		return DigestUtils.sha256Hex(paramsWithoutSignature);
	}
}

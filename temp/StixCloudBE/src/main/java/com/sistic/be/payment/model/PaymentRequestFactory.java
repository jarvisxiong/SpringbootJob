package com.sistic.be.payment.model;

import java.util.Map;

import javax.money.MonetaryAmount;

import com.sistic.be.payment.constants.PaymentGatewayConstants;
import com.sistic.be.sales.order.CreditCardInfo;

public class PaymentRequestFactory {

	public static AbstractPayment3DSRequest paymentRequest(String payGatewayType,Map<String,String> paymentConfigMap, CreditCardInfo creditCardInfo, MonetaryAmount payableAmount, String transactionRefNumber, String runProfile){

		if(PaymentGatewayConstants.TYPE_MIGS.equals(payGatewayType)) {
			return new MIGSPayment3DSRequest(paymentConfigMap, creditCardInfo, payableAmount, transactionRefNumber, runProfile);
		}
		else if(PaymentGatewayConstants.TYPE_SINO.equals(payGatewayType)) {
			return new CUPPurchasePaymentRequest(paymentConfigMap, creditCardInfo, payableAmount, transactionRefNumber, runProfile);
		}
		else if(PaymentGatewayConstants.TYPE_TELEMONEY.equals(payGatewayType)) {
			return new TeleMoneyPayment3DSRequest(paymentConfigMap, creditCardInfo, payableAmount, transactionRefNumber, runProfile);
		}

		return null;
	}
}

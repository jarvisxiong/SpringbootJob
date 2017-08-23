package com.sistic.be.cart.api.service;

import java.util.HashMap;
import java.util.Map;

import javax.money.MonetaryAmount;

import org.springframework.stereotype.Service;

import com.sistic.be.cart.api.model.postcommit.PostCommitRequest;
import com.sistic.be.payment.model.AbstractPayment3DSRequest;
import com.sistic.be.sales.order.CreditCardInfo;
import com.sistic.be.sales.order.TransactionPayment;

@Service
public class ShoppingCartService {
	
public Map<String,String> populateTeleMoneyRequestMap(AbstractPayment3DSRequest abstractPayment3DSRequest,Map<String,String> resMap){
	 Map<String,String> telemoneyRequestMap= new HashMap<String,String>();
	 telemoneyRequestMap.put("PaReq", resMap.get("PaReq"));				 
	 telemoneyRequestMap.put("MD", abstractPayment3DSRequest.toVpcFieldsMap().get("ref"));
	 telemoneyRequestMap.put("TermUrl", abstractPayment3DSRequest.toVpcFieldsMap().get("returnurl"));
	 return telemoneyRequestMap;
}
public Map<String,String> populateTeleMoney3DSRequestMap(Map<String,String> threeDSecureMap,TransactionPayment transactionPayment,Map<String, String> paymentConfigMap){
	
	CreditCardInfo creditCardInfo=null;
	MonetaryAmount payableAmount = transactionPayment.getPayableAmount();
	Map<String,String> requestMap= new HashMap<String,String>();
	if (transactionPayment != null) {
		 creditCardInfo = transactionPayment.getCreditCardInfo();
	}
		requestMap.put("mid",paymentConfigMap.get("merchant.id"));
		requestMap.put("ref",transactionPayment.getTransactionRefNumber());
		requestMap.put("cur","SGD");
		requestMap.put("amt",payableAmount.getNumber().toString());
		requestMap.put("transtype","pares");
		requestMap.put("subtranstype","vereq");
		requestMap.put("pares",threeDSecureMap.get("PaRes"));
		return requestMap;
}

public PostCommitRequest buildPostCommitRequest(Map<String,String> threeDSecureMap){
	PostCommitRequest postCommitRequest=null;
	if(threeDSecureMap.get("TM_XID")!=null) { //Tele Money
		 postCommitRequest=new PostCommitRequest.Builder().vpc3DSECI(threeDSecureMap.get("TM_ECI"))
					.vpc3DSstatus(threeDSecureMap.get("TM_3DSStatus")).vpc3DSXID(threeDSecureMap.get("TM_XID"))
					.vpcVerToken(threeDSecureMap.get("TM_CAVV"))
					.build();
	}
	else {
	 postCommitRequest=new PostCommitRequest.Builder().vpc3DSECI(threeDSecureMap.get("vpc_3DSECI")).vpc3DSenrolled(threeDSecureMap.get("vpc_3DSenrolled"))
			.vpc3DSstatus(threeDSecureMap.get("vpc_3DSstatus")).vpc3DSXID(threeDSecureMap.get("vpc_3DSXID")).vpcVerSecurityLevel(threeDSecureMap.get("vpc_VerSecurityLevel"))
			.vpcVerStatus(threeDSecureMap.get("vpc_VerStatus")).vpcVerToken(threeDSecureMap.get("vpc_VerToken")).vpcVerType(threeDSecureMap.get("vpc_VerType"))
			.build();
	}
	return postCommitRequest;
}
}

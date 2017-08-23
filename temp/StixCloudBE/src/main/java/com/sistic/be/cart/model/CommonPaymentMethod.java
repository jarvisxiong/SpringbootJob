/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sistic.be.app.util.PaymentUtil;

/**
 * @author jianhong
 *
 */
public class CommonPaymentMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7260528594586016545L;
	private List<PaymentMethod> paymentMethodList;
	
	public List<PaymentMethod> getPaymentMethodList() {
		return paymentMethodList;
	}
	public void setPaymentMethodList(List<PaymentMethod> paymentMethodList) {
		// TODO: replace sorting by order returned by API
		this.paymentMethodList = paymentMethodList.stream().sorted(Comparator.comparing(PaymentMethod::getCode)).collect(Collectors.toList());
	}
	
//	@JsonIgnore
//	public String getPaymentCodes() {
//		String str = "[";
//		for (PaymentMethod paymentMethod : paymentMethodList) {
//			str += "\"" +paymentMethod.getCode() + "\",";
//		}
//		
//		return str.substring(0, str.length() - 1 ) + "]" ;
//	}
	
	@JsonIgnore
	public PaymentMethod findPaymentMethod(String paymentCode) {
		for (PaymentMethod paymentMethod : paymentMethodList) {
			if (paymentMethod.getCode().equals(paymentCode)) {
				return paymentMethod;
			}
		}
		return null;
	}
	
	@JsonIgnore
	public Map<String,String> getCreditCardTypes() {
		Map<String, String> creditCardTypes = new LinkedHashMap<String, String>();
		if (paymentMethodList != null) {
			for (PaymentMethod paymentMethod : paymentMethodList) {
				if (paymentMethod.getCode() != null) {
					String paymentMethodCode = paymentMethod.getCode();
					if (paymentMethodCode.contains("E_VOUCHER")) {
						continue;	// not interested in EVOUCHER for credit card types
					}
					String creditCardType = PaymentUtil.getCreditCardType(paymentMethodCode);
					String creditCardDisplayType = "";
					if (creditCardType != null) {
						creditCardDisplayType = PaymentUtil.getDisplayPaymentMethod(creditCardType);
					}
					
					creditCardTypes.put(creditCardType, creditCardDisplayType);
				}
				else {	// payment method  code is null
					continue;
				}
			}
		}
		return creditCardTypes;
	}
	
}

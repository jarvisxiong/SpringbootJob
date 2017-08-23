package com.sistic.be.app.util;

public class MaskSensitiveUtill {
	
	public static String maskCreditCardNo(String creditCardNo) {
		if (creditCardNo != null && !creditCardNo.isEmpty()) {
			return "XXXX-XXXX-XXXX-" + creditCardNo.substring(creditCardNo.length() - 4, creditCardNo.length());
		}
		return "";
	}
	
	public static String maskCreditCardCsc(String creditCardCSC) {
		if (creditCardCSC != null && !creditCardCSC.isEmpty()) {
			return creditCardCSC.replaceAll("[0-9]", "X");
		}
		return "";
	}

}

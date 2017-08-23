package com.sistic.be.app.util;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import javax.money.MonetaryAmount;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoneyUtil {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static String getFormattedMonetaryString(MonetaryAmount money) {
		if (money != null) {
			return money.getNumber().numberValueExact(BigDecimal.class).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
		}
		return null;
	}
	
	public static Long getAmountInCents(MonetaryAmount money) {
		if (money != null) {
			return money.getNumber().numberValueExact(BigDecimal.class).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100L)).longValueExact();
		}
		return null;
	}
	
	public static String getCurrencySymbol(MonetaryAmount money) {
		if (money != null) {
			Locale locale = Locale.getDefault();
			String currencySymbol = Currency.getInstance(money.getCurrency().getCurrencyCode()).getSymbol(locale);
			return currencySymbol;
		}
		return null;
	}
	
	public static String getCurrencyUnit(MonetaryAmount money) {
		if (money != null) {
			return money.getCurrency().getCurrencyCode();
		}
		return null;
	}
}

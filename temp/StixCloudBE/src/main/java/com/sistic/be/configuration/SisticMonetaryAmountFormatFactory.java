package com.sistic.be.configuration;

import java.util.Locale;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;

import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MonetaryAmountFormatFactory;

@Configuration
public class SisticMonetaryAmountFormatFactory implements MonetaryAmountFormatFactory {

	public MonetaryAmountFormat create(Locale locale) {
		return MonetaryFormats.getAmountFormat(AmountFormatQueryBuilder.of(locale)
													.set("pattern", "0.00")
													.build());
	}

}


////Creating a custom MonetaryAmountFormat
//MonetaryAmountFormat customFormat = MonetaryFormats.getAmountFormat(
// AmountFormatQueryBuilder.of(Locale.US)
//     .set(CurrencyStyle.NAME)
//     .set("pattern", "00,00,00,00.00 ¤")
//     .build());
//
////results in "00,01,23,45.67 US Dollar"
//String formatted = customFormat.format(amount);
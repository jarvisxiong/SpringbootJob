package com.sistic.be.controlleradvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sistic.be.app.util.MoneyUtil;

@ControllerAdvice
public class ThymeleafControllerAdvice {

	@ModelAttribute("#monetaryamounts")
	public MoneyUtil getMonetaryAmountUtils()
	{
		MoneyUtil moneyUtil = new MoneyUtil();
		return moneyUtil;
	}
	
}

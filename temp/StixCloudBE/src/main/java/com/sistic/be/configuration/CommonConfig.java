package com.sistic.be.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.jackson.datatype.money.MoneyModule;

import com.fasterxml.jackson.databind.Module;

@Configuration
public class CommonConfig {

	@Bean
	public Module moneyModule() {
		return new MoneyModule();
	}

}

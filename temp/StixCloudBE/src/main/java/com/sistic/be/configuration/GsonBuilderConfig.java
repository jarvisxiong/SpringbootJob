package com.sistic.be.configuration;

import org.springframework.context.annotation.Bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistic.be.configuration.gson.MoneyTypeAdapterFactory;

@Deprecated
public class GsonBuilderConfig {
	
	@Bean
	public Gson gson() {
		return new GsonBuilder().registerTypeAdapterFactory(new MoneyTypeAdapterFactory()).create();
	}

}

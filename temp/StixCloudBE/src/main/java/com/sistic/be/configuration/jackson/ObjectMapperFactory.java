package com.sistic.be.configuration.jackson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class ObjectMapperFactory {
	
	@Bean
	@Primary
	public ObjectMapper createObjectMapper(Jackson2ObjectMapperBuilder builder) {
		
		ObjectMapper objectMapper = builder.createXmlMapper(false).findModulesViaServiceLoader(true).build();

		return objectMapper;
	}
	
}
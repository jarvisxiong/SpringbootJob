package com.sistic.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class BookingEngineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingEngineApplication.class, args);
	}
	
	/**
	 * Shift this to configuration package
	 * Need this bean to support path variable validation
	 * @return
	 */
	@Bean //this could be provided via auto-configuration
    MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}

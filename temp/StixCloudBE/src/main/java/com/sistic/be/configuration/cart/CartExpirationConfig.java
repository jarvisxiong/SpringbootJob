package com.sistic.be.configuration.cart;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cart.expiration.tenant")
public class CartExpirationConfig {
	
	private long sistic = 0;

	public long getSistic() {
		return sistic;
	}

	public void setSistic(long sistic) {
		this.sistic = sistic;
	}

}

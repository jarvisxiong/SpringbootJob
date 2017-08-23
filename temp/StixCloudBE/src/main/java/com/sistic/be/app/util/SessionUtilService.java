package com.sistic.be.app.util;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistic.be.configuration.cart.CartExpirationConfig;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

@Service
public class SessionUtilService {
	
	@Autowired
	private CartExpirationConfig cartExpirationConfig;
	
	public Long getTimeLeftSeconds(OffsetDateTime reservedTime) {
		if (reservedTime != null) {
			long ttl = TenantContextHolder.getTenant().getCartExpiration();
			Long ttlRemaining = reservedTime.plusSeconds(ttl).toEpochSecond() - OffsetDateTime.now().toEpochSecond();
			return ttlRemaining;
		} else {
			return null;
		}
	}
	
}

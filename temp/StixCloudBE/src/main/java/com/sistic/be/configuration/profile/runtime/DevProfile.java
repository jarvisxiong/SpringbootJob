package com.sistic.be.configuration.profile.runtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sistic.be.configuration.profile.ProfileConsts;

@Configuration
@Profile("dev")
public class DevProfile {
	
	@Bean
	public RunProfile paymentProfile() {
		return new RunProfile(ProfileConsts.DEV);
	}

}

package com.sistic.be.configuration.profile.runtime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.sistic.be.configuration.profile.ProfileConsts;

@Configuration
@Profile("uat")
public class UatProfile {
	
	@Bean
	public RunProfile paymentProfile() {
		return new RunProfile(ProfileConsts.UAT);
	}

}

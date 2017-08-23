package com.stixcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableSpringConfigured
@EnableConfigurationProperties
@EnableCaching(	proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@EnableJpaRepositories(basePackages = {"com.stixcloud.patron","com.stixcloud.policyagent.repo"})

public class StixCloudPatronApplication {

	public static void main(String[] args) {
	    
		SpringApplication.run(StixCloudPatronApplication.class, args);
	}
}

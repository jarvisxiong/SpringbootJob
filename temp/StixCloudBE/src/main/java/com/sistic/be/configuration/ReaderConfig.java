/**
 * 
 */
package com.sistic.be.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author jianhong
 *
 */
@Configuration
@ConfigurationProperties(prefix = "spring.reader")
public class ReaderConfig {
	
	private String location = "/";

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

}

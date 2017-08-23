package com.sistic.be.configuration;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		http.headers().defaultsDisabled()
//				.addHeaderWriter(new StaticHeadersWriter("Cache-Control", " no-cache,max-age=0, must-revalidate"))
//				.addHeaderWriter(new StaticHeadersWriter("Expires", "0"));
//	}
}
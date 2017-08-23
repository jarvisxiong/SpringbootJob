package com.sistic.be.configuration.webmvcconfigurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sistic.be.configuration.interceptor.SessionValidatorInterceptor;

@Configuration
public class SessionMvcConfig extends WebMvcConfigurerAdapter {
		
	@Autowired
	SessionValidatorInterceptor sessionValidatorInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(sessionValidatorInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/bin/**")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/fonts/**")
        .excludePathPatterns("/images/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/assets/**")
        .excludePathPatterns("/public/**")
        .excludePathPatterns("/properties/**")
        .excludePathPatterns("/tenant/**");	
	}
	
}

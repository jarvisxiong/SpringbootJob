package com.stixcloud.common.config;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

@Configuration
public class ServiceConfig {
  @Bean
  @LoadBalanced
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.build();
  }

  @Bean
  public VelocityConfigurer velocityConfig() {
    VelocityConfigurer velocityConfig = new VelocityConfigurer();
    VelocityEngine velocityEngine = new VelocityEngine();
    velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "file");
    velocityEngine.setProperty("file.resource.loader.class", FileResourceLoader.class.getName());
    velocityEngine.setProperty("file.resource.loader.path", "/STIX/");
    velocityEngine.init();
    velocityConfig.setVelocityEngine(velocityEngine);
    return velocityConfig;
  }
}

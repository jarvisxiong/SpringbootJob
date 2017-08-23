package com.stixcloud.paymentgateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CommonPaymentConfig {
  @Value("${paymentgateway.ConnectTimeout}")
  private int connectTimeout;

  @Value("${paymentgateway.ReadTimeout}")
  private int readTimeout;

  @Bean
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    builder.setConnectTimeout(connectTimeout);
    builder.setReadTimeout(readTimeout);
    return builder.build();
  }
}

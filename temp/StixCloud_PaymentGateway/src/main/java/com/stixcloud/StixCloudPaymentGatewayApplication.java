package com.stixcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StixCloudPaymentGatewayApplication {
  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(StixCloudPaymentGatewayApplication.class, args);
  }
}

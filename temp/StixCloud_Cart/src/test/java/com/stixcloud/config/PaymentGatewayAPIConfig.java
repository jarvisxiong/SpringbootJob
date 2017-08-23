package com.stixcloud.config;

import static org.mockito.Mockito.mock;

import com.stixcloud.cart.IPaymentGatewayAPIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

/**
 * Created by chongye on 11/11/2016.
 */
@Profile("test")
@Configuration
public class PaymentGatewayAPIConfig {
  /**
   * used to mock sending of messages to queue for testing purposes
   */
  @Bean
  @Primary
  IPaymentGatewayAPIService paymentGatewayAPIService() {
    return mock(IPaymentGatewayAPIService.class);
  }
}

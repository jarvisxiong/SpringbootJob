package com.stixcloud.paymentgateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by chongye on 15/11/2016.
 */
@Component
@EnableConfigurationProperties(PaymentGatewayProperties.class)
public class PaymentGatewayLoader {
  private PaymentGatewayProperties paymentGatewayProperties;

  public PaymentGatewayLoader() {
  }

  @Autowired
  public PaymentGatewayLoader(
      PaymentGatewayProperties paymentGatewayProperties) {
    this.paymentGatewayProperties = paymentGatewayProperties;
  }

  public PaymentGatewayEnum getGatewayByPaymentMethodCode(String paymentMethodCode) {
    String gatewayKey = paymentGatewayProperties.getGateway().entrySet().stream()
        .filter(entry -> entry.getValue().contains(paymentMethodCode))
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse("none");

    return PaymentGatewayEnum.valueOf(gatewayKey.toUpperCase());
  }

  public String getQueueByPaymentGatewayEnum(PaymentGatewayEnum paymentGatewayEnum) {
    return paymentGatewayProperties.getQueue().getRoutingKey().entrySet().stream()
        .filter(entry -> entry.getKey().equalsIgnoreCase(paymentGatewayEnum.toString()))
        .map(Map.Entry::getValue)
        .findFirst()
        .orElse(null);
  }
}

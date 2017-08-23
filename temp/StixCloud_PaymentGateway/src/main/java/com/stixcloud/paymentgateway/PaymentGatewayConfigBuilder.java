package com.stixcloud.paymentgateway;

import com.stixcloud.paymentgateway.api.PaymentConfigDetailsJson;
import com.stixcloud.paymentgateway.api.PaymentGatewayConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sengkai on 11/17/2016.
 */
public class PaymentGatewayConfigBuilder {

  private String paymentMethod;
  private String paymentGatewayStatus;
  private Map<String, String> gatewayConfigs;

  public PaymentGatewayConfigBuilder() {
  }

  public PaymentGatewayConfigBuilder(String paymentMethod, String paymentGatewayStatus,
                                     Map<String, String> gatewayConfigs) {
    this.paymentMethod = paymentMethod;
    this.paymentGatewayStatus = paymentGatewayStatus;
    this.gatewayConfigs = gatewayConfigs;
  }

  private PaymentGatewayConfigBuilder(Builder builder) {
    paymentMethod = builder.paymentMethod;
    paymentGatewayStatus = builder.paymentGatewayStatus;
    gatewayConfigs = builder.gatewayConfigs;
  }


  public PaymentConfigDetailsJson build(String paymentMethod, String paymentGatewayStatus,
                                        Map<String, String> gatewayConfigs) {

    Map<String, Object> properties = new HashMap<>(gatewayConfigs);
    PaymentGatewayConfig buildPaymentGatewayConfiguration = new PaymentGatewayConfig.Builder()
        .additionalProperties(properties)
        .build();

    return new PaymentConfigDetailsJson.Builder()
        .paymentMethodCode(paymentMethod)
        .paymentGatewayStatus(paymentGatewayStatus)
        .paymentGatewayConfig(buildPaymentGatewayConfiguration)
        .build();
  }

  public PaymentConfigDetailsJson build(String paymentMethod, String paymentGatewayStatus) {

    return new PaymentConfigDetailsJson.Builder()
        .paymentMethodCode(paymentMethod)
        .paymentGatewayStatus(paymentGatewayStatus)
        .build();
  }

  public static final class Builder {
    private String paymentMethod;
    private String paymentGatewayStatus;
    private Map<String, String> gatewayConfigs;

    public Builder() {
    }

    public Builder paymentMethod(String paymentMethod) {
      this.paymentMethod = paymentMethod;
      return this;
    }

    public Builder paymentGatewayStatus(String paymentGatewayStatus) {
      this.paymentGatewayStatus = paymentGatewayStatus;
      return this;
    }

    public Builder gatewayConfigs(Map<String, String> gatewayConfigs) {
      this.gatewayConfigs = gatewayConfigs;
      return this;
    }

    public PaymentGatewayConfigBuilder build() {
      return new PaymentGatewayConfigBuilder(paymentMethod, paymentGatewayStatus, gatewayConfigs);
    }
  }
}

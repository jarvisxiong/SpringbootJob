package com.stixcloud.paymentgateway;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(value = "paymentgateway")
public class PaymentGatewayProperties {
  private Map<String, List<String>> gateway;
  private Queue queue;

  public PaymentGatewayProperties() {
  }

  public Map<String, List<String>> getGateway() {
    return gateway;
  }

  public void setGateway(Map<String, List<String>> gateway) {
    this.gateway = gateway;
  }

  public Queue getQueue() {
    return queue;
  }

  public void setQueue(Queue queue) {
    this.queue = queue;
  }

  public static class Queue {
    private Map<String, String> routingKey;
    private String directExchange;

    public Queue() {
    }

    public Map<String, String> getRoutingKey() {
      return routingKey;
    }

    public void setRoutingKey(Map<String, String> queue) {
      this.routingKey = queue;
    }

    public String getDirectExchange() {
      return directExchange;
    }

    public void setDirectExchange(String directExchange) {
      this.directExchange = directExchange;
    }
  }
}
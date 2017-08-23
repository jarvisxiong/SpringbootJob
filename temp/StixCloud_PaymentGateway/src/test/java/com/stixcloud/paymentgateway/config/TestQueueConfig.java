package com.stixcloud.paymentgateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.paymentgateway.PaymentGatewayProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.test.RabbitListenerTest;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by chongye on 11/11/2016.
 */
@Profile("test")
@RabbitListenerTest
@Configuration
@ConditionalOnClass(PaymentGatewayProperties.class)
@EnableConfigurationProperties(PaymentGatewayProperties.class)
public class TestQueueConfig {
  private PaymentGatewayProperties paymentGatewayProperties;

  public TestQueueConfig(PaymentGatewayProperties paymentGatewayProperties) {
    this.paymentGatewayProperties = paymentGatewayProperties;
  }

  @Bean
  Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
    Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    converter.setCreateMessageIds(true);
    converter.setJsonObjectMapper(objectMapper);
    return converter;
  }

  /**
   * Auto create queues from application.properties "paymentgateway.queue.routing-key" property
   * @return List of Queue object
   */
  @Bean
  public List<Queue> queues() {
    Map<String, String> queueMap = paymentGatewayProperties.getQueue().getRoutingKey();

    return queueMap.entrySet().stream().map(Map.Entry::getValue)
        .map(queueName -> new Queue(queueName, true))
        .collect(Collectors.toList());
  }
}

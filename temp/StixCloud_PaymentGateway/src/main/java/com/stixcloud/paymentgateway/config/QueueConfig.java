package com.stixcloud.paymentgateway.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.paymentgateway.PaymentGatewayProperties;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@Profile("!test")
@EnableRabbit
@Configuration
@ConditionalOnClass(PaymentGatewayProperties.class)
@EnableConfigurationProperties(PaymentGatewayProperties.class)
public class QueueConfig {
  private PaymentGatewayProperties paymentGatewayProperties;
  private ConnectionFactory connectionFactory;
  private ObjectMapper objectMapper;

  @Autowired
  public QueueConfig(PaymentGatewayProperties paymentGatewayProperties,
                     ConnectionFactory connectionFactory,
                     ObjectMapper objectMapper) {
    this.paymentGatewayProperties = paymentGatewayProperties;
    this.connectionFactory = connectionFactory;
    this.objectMapper = objectMapper;
  }

  @Bean
  @ConditionalOnProperty(prefix = "paymentgateway.queue", name = "direct-exchange")
  DirectExchange exchange() {
    return new DirectExchange(paymentGatewayProperties.getQueue().getDirectExchange(), true, false);
  }

  @Bean
  Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
    converter.setCreateMessageIds(true);
    converter.setJsonObjectMapper(objectMapper);
    return converter;
  }

  @Bean
  public RabbitTemplate rabbitTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
    return rabbitTemplate;
  }

  /**
   * Auto create queues from application.properties "paymentgateway.queue.routing-key" property
   * @return List of Queue object
   */
  @Bean
  public List<Queue> queues() {
    Map<String, String> queueMap = paymentGatewayProperties.getQueue().getRoutingKey();

    return queueMap.entrySet().stream().map(Map.Entry::getValue)
        .map(queueName -> QueueBuilder.durable(queueName).build())
        .collect(Collectors.toList());
  }

  /**
   * Create bindings for list of queues
   * @return List of Binding object
   */
  @Bean
  public List<Binding> bindings() {
    return queues().stream()
        .map(queue -> BindingBuilder.bind(queue).to(exchange())
            .with(queue.getName()))
        .collect(Collectors.toList());
  }
}

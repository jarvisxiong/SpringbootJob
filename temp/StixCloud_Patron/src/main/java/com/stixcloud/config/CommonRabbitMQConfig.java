package com.stixcloud.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by chetan on 01/19/2017.
 */
@EnableRabbit
@Configuration
@Profile("!test")
@ConditionalOnProperty(value = "spring.rabbitmq.host")
public class CommonRabbitMQConfig {
  @Value("${direct.exchange}")
  private String directExchange;
  @Value("${email.notification.queue}")
  private String emailNotificationQueue;
  @Autowired
  private ConnectionFactory connectionFactory;

  @Bean
  public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Bean
  public RabbitAdmin rabbitAdmin() {
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    return rabbitAdmin;
  }

  @Bean
  public AmqpTemplate amqpTemplate() {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setExchange(directExchange);
    return rabbitTemplate;
  }

  @Bean
  public DirectExchange directExchange() {
    DirectExchange
        directExchange =
        new DirectExchange("${direct.exchange}", true, false);
    return directExchange;
  }

  @Bean
  public Queue notificationQueue() {
    return new Queue(emailNotificationQueue, true);
  }

  @Bean
  public Binding emailNotificationBinding() {
    return BindingBuilder.bind(notificationQueue()).to(directExchange()).with("email.notification");
  }
}

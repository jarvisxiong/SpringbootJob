package com.stixcloud.paymentgateway;

import com.rabbitmq.client.Channel;
import com.stixcloud.paymentgateway.api.PaymentGatewayMessagePostProcessor;
import com.stixcloud.paymentgateway.api.PaymentGatewayRequest;
import com.stixcloud.paymentgateway.api.PaymentGatewayResponse;
import com.stixcloud.paymentgateway.repo.PaymentGatewayResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@EnableConfigurationProperties(PaymentGatewayProperties.class)
public class QueueService implements IQueueService {
  private static final Logger LOGGER = LoggerFactory.getLogger(QueueService.class);

  private PaymentGatewayResponseRepository paymentGatewayResponseRepository;
  private PaymentGatewayLoader paymentGatewayLoader;
  private RabbitTemplate rabbitTemplate;
  private PaymentGatewayMessagePostProcessor messagePostProcessor;

  @Value("${redis.queue.size}")
  private int redisQueueSize;

  @Value("${redis.queue.polled.ttl}")
  private long queuePolledTTL;

  @Value("${redis.queue.notpolled.ttl}")
  private long queueNotPolledTTL;

  @Autowired
  public QueueService(
      PaymentGatewayResponseRepository paymentGatewayResponseRepository,
      PaymentGatewayLoader paymentGatewayLoader,
      RabbitTemplate rabbitTemplate,
      PaymentGatewayMessagePostProcessor messagePostProcessor) {
    this.paymentGatewayResponseRepository = paymentGatewayResponseRepository;
    this.paymentGatewayLoader = paymentGatewayLoader;
    this.rabbitTemplate = rabbitTemplate;
    this.messagePostProcessor = messagePostProcessor;
  }

  @Override
  public String sendMessage(PaymentGatewayRequest paymentGatewayRequest) {
    PaymentGatewayEnum paymentGatewayEnum = paymentGatewayLoader
        .getGatewayByPaymentMethodCode(paymentGatewayRequest.getPaymentMethodCode().toUpperCase());
    String queueToRoute = paymentGatewayLoader.getQueueByPaymentGatewayEnum(paymentGatewayEnum);

    LOGGER.info(
        "Payment Method Code : " + paymentGatewayRequest.getPaymentMethodCode().toUpperCase());
    LOGGER.debug("Payment Gateway Enum : " + paymentGatewayEnum.toString());
    LOGGER.debug("Queue : " + queueToRoute);

    rabbitTemplate.convertAndSend(queueToRoute, paymentGatewayRequest, messagePostProcessor);
    LOGGER.info("UUID : " + messagePostProcessor.getMessageId());
    return messagePostProcessor.getMessageId();
  }

  /**
   * Listen to the queues created in QueueConfig.queues()
   */
  @RabbitListener(id = "receiveMessage", queues = {"#{queues}"})
  public void receiveMessage(PaymentGatewayRequest paymentGatewayRequest, Message message,
                             Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    LOGGER.info("receiveMessage Request : " + paymentGatewayRequest.toString());
    LOGGER.info("Message : " + message.toString());
    if (paymentGatewayResponseRepository.count() < redisQueueSize) {
      PaymentGatewayResponse response = new PaymentGatewayResponse.Builder()
          .uuid(message.getMessageProperties().getMessageId())
          .isPolled(false)
          .timeToLive(queueNotPolledTTL)
          .message(paymentGatewayRequest.toString())
          .build();
      paymentGatewayResponseRepository.save(response);

      //acknowledge message when it is saved into redis
      channel.basicAck(tag, false);
    } else {
      LOGGER.info("Redis queue is full");
      //reject message when redis queue size is hit
      channel.basicNack(tag, false, true);
//      throw new RuntimeException("Redis queue is full");
    }
  }

  /**
   * Retrieve messages that are stored by Consumers
   */
  @Override
  public PaymentGatewayResponse getMessage(String uuid) {
    LOGGER.info("getMessage : " + uuid);
    if (paymentGatewayResponseRepository.exists(uuid)) {
      PaymentGatewayResponse response = paymentGatewayResponseRepository.findOne(uuid);
      response =
          new PaymentGatewayResponse.Builder(response)
              .isPolled(true)
              .timeToLive(queuePolledTTL)
              .build();
      paymentGatewayResponseRepository.save(response);
      LOGGER.info("getMessage response : " + response);
      return response;
    }
    return null;
  }

  @Override
  public void removeMessage(String uuid) {
    if (paymentGatewayResponseRepository.exists(uuid)) {
      LOGGER.info("Deleting message  : " + uuid);
      paymentGatewayResponseRepository.delete(uuid);
    }
  }
}
package com.stixcloud.paymentgateway.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by chongye on 16/11/2016.
 */
@Component
public class PaymentGatewayMessagePostProcessor implements MessagePostProcessor {
  private static final Logger LOGGER =
      LogManager.getLogger(PaymentGatewayMessagePostProcessor.class);
  private String messageId = null;

  public String getMessageId() {
    return messageId;
  }

  @Override
  public Message postProcessMessage(Message message) throws AmqpException {
    messageId = message.getMessageProperties().getMessageId();
    LOGGER.debug(message);
    return message;
  }
}

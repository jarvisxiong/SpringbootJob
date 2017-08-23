package com.stixcloud.patron.service;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.dto.common.MessageDTO;


@Service
public class EmailService implements IEmailService {
  private static final Logger logger = LogManager.getLogger(EmailService.class);

  @Autowired
  AmqpTemplate amqpTemplate;

  @Override
  public void sendEmail(String content, String subject, String sender, String toAddress,
      HashMap<String, byte[]> attachmentMap) {
    logger.info("Start sending email to: " + toAddress + " with subject: " + subject);

    MessageDTO messageDTO = new MessageDTO(content, subject, sender, toAddress, attachmentMap);
    amqpTemplate.convertAndSend("email.notification", messageDTO);
    logger.info("Sending email to: " + toAddress + " with subject: " + subject + " is successful.");
  }

}

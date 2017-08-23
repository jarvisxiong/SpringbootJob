package com.stixcloud.patron.service;

import java.util.HashMap;

public interface IEmailService {
  void sendEmail(String content, String subject, String sender, String toAddress,
      HashMap<String, byte[]> attachmentMap);
}

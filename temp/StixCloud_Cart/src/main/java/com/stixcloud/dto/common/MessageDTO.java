package com.stixcloud.dto.common;

import java.io.Serializable;
import java.util.HashMap;

public class MessageDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String content;
  private String subject;
  private String from;
  private String to;// comma , to seperate email
  private HashMap<String, byte[]> attachmentMap;

  public MessageDTO() {
  }

  public MessageDTO(String content, String subject, String from, String to,
                    HashMap<String, byte[]> attachmentMap) {
    super();
    this.content = content;
    this.subject = subject;
    this.from = from;
    this.to = to;
    this.attachmentMap = attachmentMap;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public HashMap<String, byte[]> getAttachmentMap() {
    return attachmentMap;
  }

  public void setAttachmentMap(HashMap<String, byte[]> attachmentMap) {
    this.attachmentMap = attachmentMap;
  }

}

package com.stixcloud.patron.repo;

public interface IEmailTemplateRepository {
  String getEmailTemplatePath(String emailType, String languageCode);
}

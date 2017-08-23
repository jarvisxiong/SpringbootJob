package com.stixcloud.patron.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.common.config.multitenant.TenantContextHolder;

@Repository
public class EmailTemplateRepository implements IEmailTemplateRepository {
  private static final Logger logger = LogManager.getLogger(EmailTemplateRepository.class);

  @Autowired
  EntityManager em;

  @Override
  public String getEmailTemplatePath(String emailType, String languageCode) {
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
    
    logger.info("Start EmailTemplateRepository.getEmailTemplatePath()");
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT path ")
        .append("FROM email_template et ")
        .append("JOIN email_template_mapping etm ")
        .append("ON et.EMAILTEMPLATEID = etm.EMAIL_TEMPLATE_ID ")
        .append("JOIN profile_info pi ")
        .append("ON etm.ENTITY_ID     = pi.PROFILE_CHANNEL_ID ")
        .append("WHERE etm.TYPE       = 2 ")
        .append("AND pi.PROFILEINFOID = :profileInfoId ")
        .append("AND et.TYPE          = :emailType ")
        .append("AND et.LANGUAGE_CODE = :languageCode ")
        .append("UNION ALL ")
        .append("SELECT path ")
        .append("FROM email_template ")
        .append("WHERE TYPE     = :emailType ")
        .append("AND IS_DEFAULT = 'Y' ");

    @SuppressWarnings("unchecked")
    List<String> templatePathList = (List<String>) em.createNativeQuery(queryStr.toString())
        .setParameter("profileInfoId", profileInfoId)
        .setParameter("emailType", emailType)
        .setParameter("languageCode", languageCode)
        .getResultList();

    logger.info("End EmailTemplateRepository.getEmailTemplatePath()");

    if (templatePathList.isEmpty()) {
      return null;
    }

    return templatePathList.get(0);
  }

}

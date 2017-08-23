package com.stixcloud.patron.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.stixcloud.patron.constant.EmailConstants;
import com.stixcloud.patron.constant.TokenConstants;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.TenantConfig;
import com.stixcloud.domain.TokenForgotPwd;
import com.stixcloud.patron.repo.IEmailTemplateRepository;
import com.stixcloud.patron.repo.IForgotPasswordRepository;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ITenantConfigRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ForgotPasswordService implements IForgotPasswordService {
  private static final Logger logger = LogManager.getLogger(ForgotPasswordService.class);

  @Autowired
  private IPatronService patronService;

  @Autowired
  private IPatronRepository patronRepo;

  @Autowired
  private IForgotPasswordRepository forgotPasswordRepo;

  @Autowired
  private IEmailService emailService;

  @Autowired
  private VelocityEngine velocityEngine;

  @Autowired
  private ITenantConfigRepository tenantConfigRepo;

  @Autowired
  private IEmailTemplateRepository emailTemplateRepo;

  @Autowired
  private MessageSource messageSource;

  /**
   * Do some below steps: <br />
   * 1. Check availability of email <br />
   * 2. Invalidate all tokens of this patron <br />
   * 3. Generate token <br />
   * 4. Insert into patron_token_forgot_pwd <br />
   * 5. Send email
   * 
   * @param email
   * @return true if the email exists in the system, and there are no error when generate and send
   *         email
   */
  public boolean handleForgotPassword(String email, String languageCode) throws SisticApiException {
    logger.info("Start handleForgotPassword");
    // 1. Check availability of email
    if (!patronService.checkExistEmailAddress(email)) {
      return false;
    }

    // 2. Invalidate all tokens of this patron
    forgotPasswordRepo.invalidateAllActiveTokens(email);

    // 3. Generate token
    PatronProfile patron = patronRepo.getPatronProfileByEmail(email);
    TokenForgotPwd token = createNewToken(patron);

    // 4. Insert into patron_token_forgot_pwd
    forgotPasswordRepo.createNewToken(token);

    // 5. Send email
    prepareAndSendEmail(patron, token.getTokenHash(), languageCode);

    logger.info("End handleForgotPassword");
    return true;
  }


  /**
   * Check token is still available or not
   * 
   * @param token
   * @param email
   * @return
   */
  public boolean validateToken(String token, String email) {
    return forgotPasswordRepo.validateToken(token, email);
  }


  /**
   * Do below steps: Invalid all active tokens relating to the patron Encrypt password Update patron
   */
  public boolean updatePassword(String token, String email, String newPassword) {
    logger.info("Start updatePassword");
    boolean isValidToken = forgotPasswordRepo.validateToken(token, email);
    if (isValidToken) {
      logger.info("Token is valid-> start updating password");
      forgotPasswordRepo.invalidateAllActiveTokens(email);

      forgotPasswordRepo.updatePassword(email, newPassword);
      logger.info("Update password successfully");
    }

    logger.info("End updatePassword");
    return isValidToken;
  }

  private TokenForgotPwd createNewToken(PatronProfile patron) {
    logger.info("Start createNewToken");
    TokenForgotPwd token = new TokenForgotPwd();
    // generates the token hash
    String tokenHash = String.valueOf(UUID.randomUUID());

    token.setTokenHash(tokenHash);
    token.setPatron(patron.getPatronprofileid());
    token.setPatronEmail(patron.getEmailaddress());
    token.setExpiryTime(getTokenExpiryTime());
    token.setRequestOn(new Date());
    token.setRequestBy(patron.getPatronprofileid());
    token.setRequestByUser(TokenConstants.PATRON.value());
    token.setUpdateOn(null);
    token.setActiveStatus(Integer.parseInt(TokenConstants.ACTIVE.value()));
    token.setOrigin(TokenConstants.BOOKING_ENGINE.value());
    logger.info("Start createNewToken");
    return token;
  }

  /**
   * Get the expiry time based on the time when the token was requested. the expiry duration is
   * based on Application Config setting. (By default it's set to 1 day or 24 hours)
   * 
   * @return expiryTime
   */
  private Date getTokenExpiryTime() {
    logger.info("Start getTokenExpiryTime() ");
    String expiryDuration = forgotPasswordRepo.getTokenExpiryTime();
    logger.info("expiryDuration: " + expiryDuration);

    // returning the expiry time
    Calendar c = Calendar.getInstance();
    logger.info("Current time: " + c.getTime());
    c.add(Calendar.DATE, Integer.parseInt(expiryDuration));
    Date expiryTime = c.getTime();
    logger.info("Expiry time: " + expiryTime);

    logger.info("End getTokenExpiryTime() ");
    return expiryTime;
  }

  /**
   * Get activation link: config = null, then get default <br/>
   * Get tenant config to get email subject password reset <br/>
   * Get email sender from tenant config <br/>
   * Get email template path: get config, if config = null then get default Call email service to
   * send email
   * 
   * @param patron
   */
  private void prepareAndSendEmail(PatronProfile patron, String token, String languageCode)
      throws SisticApiException {
    logger.info("Start prepareAndSendEmail");
    String templatePath = emailTemplateRepo
        .getEmailTemplatePath(EmailConstants.EMAIL_TEMPLATE_FORGOT_PASSWORD_TYPE, languageCode);
    if (StringUtils.isEmpty(templatePath)) {
      logger.error("Not found email template path for language code: " + languageCode);
      throw new SisticApiException("patron.forgot.password.email.template.error",messageSource.getMessage(
          "patron.forgot.password.email.template.error", null, LocaleContextHolder.getLocale()));
    }

    String activationLink = forgotPasswordRepo.getActivationLink();
    if (StringUtils.isEmpty(activationLink)) {
      logger.error("Not found activation link");
      throw new SisticApiException("patron.forgot.password.activation.link.error", messageSource.getMessage(
          "patron.forgot.password.activation.link.error", null, LocaleContextHolder.getLocale()));
    }

    String patronEmail = patron.getEmailaddress();
    activationLink = activationLink + "email=" + patronEmail + "&token=" + token;

    String subject = EmailConstants.PASSWORD_RESET_SUBJECT;
    String sender = EmailConstants.EMAIL_SENDER;

    List<TenantConfig> tenantConfigList = (List<TenantConfig>) tenantConfigRepo.findAll();
    Map<String, String> tenantConfigMap = tenantConfigList.stream()
        .collect(Collectors.toMap(TenantConfig::getKey, TenantConfig::getValue));
    if (tenantConfigMap.containsKey(EmailConstants.EMAIL_SUBJECT_PASSWORD_RESET)) {
      subject = tenantConfigMap.get(EmailConstants.EMAIL_SUBJECT_PASSWORD_RESET);
      logger.info("Email subject of password reset in tenant configuration: " + subject);
    }

    if (tenantConfigMap.containsKey(EmailConstants.TENANT_BASED_FROM_ADDRESS)) {
      sender = tenantConfigMap.get(EmailConstants.TENANT_BASED_FROM_ADDRESS);
      logger.info("Based email sender in tenant configuration: " + sender);
    }

    Map<String, Object> map = new HashMap<String, Object>();
    map.put("name", patron.getFirstname() + " " + patron.getLastname());
    map.put("link", activationLink);

    String content =
        VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, "UTF-8", map);
    emailService.sendEmail(content, subject, sender, patronEmail, null);
    logger.info("End prepareAndSendEmail");
  }

}

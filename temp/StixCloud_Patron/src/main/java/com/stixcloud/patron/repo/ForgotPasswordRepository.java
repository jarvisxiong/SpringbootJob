package com.stixcloud.patron.repo;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.patron.constant.TokenConstants;
import com.stixcloud.domain.TokenForgotPwd;

@Repository
public class ForgotPasswordRepository implements IForgotPasswordRepository {
  private static final Logger logger = LogManager.getLogger(ForgotPasswordRepository.class);

  @Autowired
  EntityManager em;

  @Override
  public void invalidateAllActiveTokens(String email) {
    logger.info("Start invalidating all active tokens of email: " + email);
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("UPDATE PATRON_TOKEN_FORGOT_PWD ")
        .append("SET ACTIVESTATUS = 0, UPDATEDON = :currentTime ")
        .append("WHERE LOWER(PATRONEMAIL) = LOWER(:email) AND ACTIVESTATUS = 1 ");

    em.createNativeQuery(queryStr.toString()).setParameter("email", email)
        .setParameter("currentTime", Calendar.getInstance().getTime()).executeUpdate();
    logger.info("End invalidating all active tokens of email: " + email);
  }

  @Override
  public void createNewToken(TokenForgotPwd token) {
    logger.info(
        "Start creating new token forgot password for patron email: " + token.getPatronEmail());
    em.persist(token);
    logger
        .info("End creating new token forgot password for patron email: " + token.getPatronEmail());
  }

  @Override
  public boolean validateToken(String token, String email) {
    logger.info("Start validating token: " + token + " and email: " + email);
    Object selectResult = null;

    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT 1 FROM PATRON_TOKEN_FORGOT_PWD ").append(
        "WHERE LOWER(PATRONEMAIL) = LOWER(:email) AND TOKENHASH = :token AND EXPIRYTIME > :currentTime AND ACTIVESTATUS = 1 ");


    try {
      selectResult = em.createNativeQuery(queryStr.toString()).setParameter("email", email)
          .setParameter("token", token)
          .setParameter("currentTime", Calendar.getInstance().getTime()).getSingleResult();
    } catch (NoResultException e) {
      logger.error(
          "Not exist token: " + token + " and email: " + email + " or token already expired");
      return false;
    }

    logger.info("End validating token: " + token + " and email: " + email);
    return selectResult == null ? false : true;
  }

  @Override
  public boolean updatePassword(String email, String newPassword) {
    logger.info("Start updating patron password by email: " + email);
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("UPDATE PATRON_INTERNET_ACCOUNT ")
        .append("SET PASSWORD = :password, LOGINFAILEDCOUNT = 0 ").append("WHERE LOWER(USERID) = LOWER(:email)");

    int updateResult = em.createNativeQuery(queryStr.toString()).setParameter("email", email)
        .setParameter("password", newPassword).executeUpdate();
    boolean status = updateResult > 0 ? true : false;
    logger.info("Updating status: " + status);
    logger.info("End updating patron password by email: " + email);
    return status;
  }

  public String getTokenExpiryTime() {
    logger.info("Start getting token expiry time");
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT PARAM1 FROM APPLICATION_CONFIG ").append("WHERE TYPE = :type");

    String tokenExpiryTime = (String) em.createNativeQuery(queryStr.toString())
        .setParameter("type", TokenConstants.DB_TOKEN_EXPIRY_TIME.value()).getSingleResult();
    logger.info("Token expiry time: " + tokenExpiryTime);
    logger.info("End getting token expiry time");
    return tokenExpiryTime;
  }

  @Override
  public String getActivationLink() {
    logger.info("Start getting activation link");
    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();

    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT ac.PARAM1 ").append("FROM application_config ac ")
        .append("JOIN profile_info pi ON ac.PARAM2 = pi.PROFILE_CHANNEL_ID ")
        .append("WHERE pi.PROFILEINFOID = :profileInfoId ")
        .append("AND ac.TYPE LIKE 'ACTIVATION_LINK%' ").append("UNION ALL ")
        .append("SELECT ac.PARAM1 ").append("FROM application_config ac ")
        .append("WHERE ac.TYPE LIKE 'ACTIVATION_LINK' ");

    @SuppressWarnings("unchecked")
    List<String> activationLinkList = (List<String>) em.createNativeQuery(queryStr.toString())
        .setParameter("profileInfoId", profileInfoId).getResultList();

    if (activationLinkList.isEmpty()) {
      logger.error("Not found activation link of user profile info id: " + profileInfoId);
      return null;
    }

    String activationLink = activationLinkList.get(0);
    logger.info(
        "Activation link of user profile info id: " + profileInfoId + " is " + activationLink);
    logger.info("End getting activation link");
    return activationLink;
  }
}

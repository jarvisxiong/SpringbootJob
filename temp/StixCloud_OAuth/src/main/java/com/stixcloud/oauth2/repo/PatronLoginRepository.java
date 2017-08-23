package com.stixcloud.oauth2.repo;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.oauth2.domain.PatronLogin;

@Repository
public class PatronLoginRepository implements IPatronLoginRepository {
    
  private static final Logger logger = LogManager.getLogger(PatronLoginRepository.class);

  @Autowired
  private EntityManager em;

  /**
   * Retrieve info patron login
   * 
   * @param patronEmail
   * @return PatronLogin
   */
  @Override
  public PatronLogin retrievePatronLogin(String patronEmail) {
    String sb = new StringBuilder( "SELECT pia.PATRON_PROFILE_ID as PATRON_ID, pia.USERID as EMAIL, pp.FIRSTNAME as FIRST_NAME, pp.LASTNAME as LAST_NAME, pp.ACCNO as ACCOUNT_NO ")
        .append(" FROM PATRON_INTERNET_ACCOUNT pia , PATRON_PROFILE pp ")
        .append(" WHERE pia.USERID          = '")
        .append(patronEmail)
        .append("' AND pia.PATRON_PROFILE_ID = pp.PATRONPROFILEID")
        .toString();
    logger.debug("retrievePatronLogin: " + sb);
    List<PatronLogin> results = em.createNativeQuery(sb, PatronLogin.class).getResultList();
    return results.size() == 0 ? null : results.get(0);
  }
}

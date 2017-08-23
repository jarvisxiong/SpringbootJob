package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.patron.repo.IPatronInternetAccountRepository;
import com.stixcloud.patron.util.PatronProfileUtil;

@Service
public class PatronInternetAccountService {

  private static final Logger logger = LogManager.getLogger(PatronInternetAccountService.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IPatronInternetAccountRepository repo;

  public PatronInternetAccount findByEmailAddress(String emailAddress) {
    return repo.findByEmailAddress(emailAddress);
  }
  
  /**
   * 
   * @param patronProfileId
   * @return
   */
  public List<PatronInternetAccount> findByPatronProfileId(Long patronProfileId, String emailAddress){
    return repo.findByPatronIdEmailAddress(patronProfileId, emailAddress);
  }

  public boolean isChanged(PatronUpdateRequest request, PatronProfileJson original) {
    return (null == request.getDisableWebAccount() || !request.getDisableWebAccount()) && !StringUtils.isBlank(request.getNewPassword()) && !StringUtils.isBlank(request.getPassword());
  }

  public void insertPatronInternetAccount(PatronInternetAccount patron) {
    logger.info("Start updating patron internet account");
    repo.save(patron);
    logger.info("End updating patron internet account");
  }

  public PatronInternetAccount updatePatronLoginFailedCount(
      PatronInternetAccount patronInternetAccount, boolean loginSuccess) {
    logger.info("Start updating patron login failed count value");
    if (loginSuccess) {
      logger.info("Login success. Login failed count is reset to 0");
      patronInternetAccount.setLoginfailedcount(0);
      patronInternetAccount.setLastlogindate(OffsetDateTime.now());
    } else {
      int newLoginFailedCountValue = patronInternetAccount.getLoginfailedcount() + 1;
      logger.info("Login fail. New failed count is " + newLoginFailedCountValue);
      patronInternetAccount.setLoginfailedcount(newLoginFailedCountValue);
    }

    PatronInternetAccount result = repo.save(patronInternetAccount);
    logger.info("End updating patron login failed count value");
    return result;
  }

  public void updatePatron(PatronUpdateRequest request, Long patronProfileId, Long userInfoId) throws SisticApiException {
	  logger.info("Update patron internet account is in progress.");
	  List<PatronInternetAccount> patronAccountList = findByPatronProfileId(patronProfileId, request.getEmail());
	  
	  String encryptedPassword = null;
	  PatronInternetAccount patronInternetAccount = null;
	  for(PatronInternetAccount patronItAcc: patronAccountList){
	    if(patronItAcc.getUserid().equals(request.getEmail()) && !patronItAcc.getPatronProfileId().equals(patronProfileId)){
	      throw new SisticApiException("patron.new.email.exist",messageSource.getMessage("patron.new.email.exist", null,
              LocaleContextHolder.getLocale()));
	    }else if(patronItAcc.getPatronProfileId().equals(patronProfileId)){
	      patronInternetAccount = patronItAcc;
	    }
	  }
	  
	  //handle for new internet account creation
	  if(null != patronInternetAccount && StringUtils.isNotBlank(request.getNewPassword()) && StringUtils.isNotBlank(request.getPassword())) {
        encryptedPassword = PatronProfileUtil.encrypt(request.getPassword());
        
        if(!patronInternetAccount.getPassword().equals(encryptedPassword)){
          logger.error("Current password inputted is not same with old one");
          throw new SisticApiException("patron.internet.account.update.error",messageSource.getMessage("patron.internet.account.update.error",
            null, LocaleContextHolder.getLocale()));
        }else{
          String encryptedNewPassword = PatronProfileUtil.encrypt(request.getNewPassword());
          patronInternetAccount.setPassword(encryptedNewPassword);
          repo.save(patronInternetAccount);
          logger.info("Update patron internet account was successful.");
        }
      }
	  
	  if(null == patronInternetAccount && !StringUtils.isNotBlank(request.getPassword()) && null!= request.getDisableWebAccount() && !request.getDisableWebAccount()){
	    encryptedPassword = PatronProfileUtil.encrypt(request.getPassword());
	    patronInternetAccount = new PatronInternetAccount(request.getEmail(),
            encryptedPassword, null, patronProfileId, 0);
	    repo.save(patronInternetAccount);
	      logger.info("Create patron internet account was successful.");
      }
  }

}

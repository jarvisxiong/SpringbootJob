package com.stixcloud.patron.repo;

import java.util.List;

import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.domain.PatronProfileView;

public interface IPatronRepository {

  Integer checkExistEmailAddress(String emailAddress);
  List<PatronProfileView> getPatronProfile(Long patronProfileId);
  PatronProfile getPatronProfileByEmail(String email);
  PatronProfile getPatronProfileByEmailAcctType(String email, String accountTypeCode);
  Object isPatronLock(String email);
  List<Object[]> getPatronSolicitationByTypeTenant(Long patronProfileId, Long userInfoId);
  List<Object[]> getPatronSolicitationByTypePromoterSubEmail(Long patronProfileId, Long userInfoId);
  List<PatronProfileView> getPatronProfileByAccNum(Long accountNum);
  
  /**
   * get patron profile by account No or email, account type, and external cust id
   * @param accountNum
   * @param email
   * @param accountTypeCode
   * @param externalCustId
   * @return
   */
  PatronProfile getPatronProfileByEmailAcctType(Long accountNum, String email, String accountTypeCode, String externalCustId);

  public List<Object[]> getPatronSolicitationByTypePromoter(Long patronProfileId, Long userInfoId);
}

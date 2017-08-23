package com.stixcloud.patron.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.domain.PatronProfileView;

@Repository
public class PatronRepository implements IPatronRepository {

  private static final Logger logger = LogManager.getLogger(PatronRepository.class);
  @Autowired
  EntityManager em;

  @Override
  public Integer checkExistEmailAddress(String emailAddress) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT 1 FROM Patron_Internet_Account ");
    queryStr.append("WHERE LOWER(userid) = LOWER(:emailAddress)");
    try {
      Object result = em.createNativeQuery(queryStr.toString())
          .setParameter("emailAddress", emailAddress).getSingleResult();
      return Integer.valueOf(result.toString());
    } catch (NoResultException e) {
      logger.info("The given email does not exist in the system: " + emailAddress);
      return 0;
    }
  }

  @Override
  public List<PatronProfileView> getPatronProfile(Long patronProfileId) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT p FROM PatronProfileView p ");
    queryStr.append("WHERE p.patronProfileId = :patronProfileId ");
    List<PatronProfileView> result = em.createQuery(queryStr.toString(), PatronProfileView.class)
        .setParameter("patronProfileId", patronProfileId).getResultList();
    return result;
  }
  
  @Override
  public List<PatronProfileView> getPatronProfileByAccNum(Long accountNum) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT p FROM PatronProfileView p ");
    queryStr.append("WHERE p.acctNum = :acctNum ");
    List<PatronProfileView> result = em.createQuery(queryStr.toString(), PatronProfileView.class)
        .setParameter("acctNum", accountNum).getResultList();
    return result;
  }

  @Override
  public PatronProfile getPatronProfileByEmail(String email) {
    logger.info("Start getPatronProfileByEmail() ");
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT * FROM PATRON_PROFILE p").append(" JOIN  PATRON_INTERNET_ACCOUNT pia ")
        .append(" ON p.patronprofileid = pia.patron_profile_id ")
        .append(" WHERE LOWER(pia.userid) = LOWER(:email) ");
    PatronProfile result =
        (PatronProfile) em.createNativeQuery(queryStr.toString(), PatronProfile.class)
            .setParameter("email", email).getSingleResult();
    logger.info("End getPatronProfileByEmail() ");
    return result;
  }
  
  @Override
  public PatronProfile getPatronProfileByEmailAcctType(String email, String accountTypeCode) {
	    logger.info("Start getPatronProfileByEmail() ");
	    StringBuilder queryStr = new StringBuilder();
	    queryStr.append("SELECT p.patronprofileid, p.identityno, p.accno, p.status, p.firstname, ")
	    		.append(" p.lastname, p.emailaddress, p.isreceiveticketingagent, p.isreceivepromoter, p.isreceivevenue, p.isbillingaddresssameasmailing, ")
	    		.append("p.isdonotsignup, p.organizationname, p.companyid, p.companyname, p.employeeid, p.guestof, ")
	    		.append(" p.externalid, p.acctremark, p.acc_Type_Mct_Id, p.gender_Mct_Id, p.title_Mct_Id, p.nationality, ")
	    		.append(" p.countryofresidence, p.createddate,p.created_by, p.updateddate, p.updated_by, p.identity_No_Type_Mct_Id , p.prefer_Language ")
	    		.append(" FROM PATRON_PROFILE p ")
	    		.append(" JOIN  MASTER_CODE_TABLE mct  ON p.ACC_TYPE_MCT_ID = mct.MASTERCODEID and mct.CATEGORYCODE = 'Patron Account Type' ")
	    		.append(" WHERE LOWER(p.emailaddress) = LOWER(:email) and mct.DESCRIPTION2 = :code");
	    PatronProfile result =
	        (PatronProfile) em.createNativeQuery(queryStr.toString(), PatronProfile.class)
	            .setParameter("email", email)
	            .setParameter("code", accountTypeCode)
	            .getSingleResult();
	    logger.info("End getPatronProfileByEmail() " + queryStr);
	    return result;
	  }

  @Override
  public Object isPatronLock(String email) throws NoResultException {
    logger.info("Start isPatronLock() ");
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT CASE  WHEN LOGINFAILEDCOUNT >= ");
    queryStr.append("(SELECT param1 FROM Application_Config ");
    queryStr.append("WHERE type = 'BE_LOGINFAILED_CONFIG')  THEN 0 ");
    queryStr.append("ELSE 1 END ");
    queryStr.append("FROM Patron_Internet_Account ");
    queryStr.append("WHERE LOWER(userid) = LOWER(:email)");
    Object result =
        em.createNativeQuery(queryStr.toString()).setParameter("email", email).getSingleResult();
    logger.info("End isPatronLock() ");
    return result;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object[]> getPatronSolicitationByTypeTenant(Long patronProfileId, Long userInfoId) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT 'Tenant', o.organizationid, ")
        .append(" o.organizationname, o.organizationurl, p.subscriptionstatus, m.description2 ")
        .append("FROM Organization o ")
        .append("LEFT JOIN Patron_Solicitation p ON p.organization_id = o.organizationid ")
        .append("AND p.patron_profile_id = :patronProfileId ")
        .append("LEFT JOIN Master_Code_Table m ON m.mastercodeid = p.solicitation_type_mct_id ")
        .append("WHERE o.istenant = 'T' ")
        .append("AND p.UPDATED_BY = :userInfoId ");

    return em.createNativeQuery(queryStr.toString())
        .setParameter("patronProfileId", patronProfileId)
        .setParameter("userInfoId", userInfoId)
        .getResultList();
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<Object[]> getPatronSolicitationByTypePromoter(Long patronProfileId, Long userInfoId) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT 'Promoter', o.organizationid, ")
        .append(" o.organizationname, o.organizationurl, p.subscriptionstatus, m.description2 ")
        .append("FROM Organization o ")
        .append("LEFT JOIN Patron_Solicitation p ON p.organization_id = o.organizationid ")
        .append("AND p.patron_profile_id = :patronProfileId ")
        .append("LEFT JOIN Master_Code_Table m ON m.mastercodeid = p.solicitation_type_mct_id ")
        .append("AND m.description1 = 'PromoterSubEmail' ")
        .append("WHERE o.istenant = 'F' ")
        .append("AND p.UPDATED_BY = :userInfoId ");

    return em.createNativeQuery(queryStr.toString())
        .setParameter("patronProfileId", patronProfileId)
        .setParameter("userInfoId", userInfoId)
        .getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object[]> getPatronSolicitationByTypePromoterSubEmail(Long patronProfileId,
      Long userInfoId) {
    StringBuilder queryStr = new StringBuilder();
    queryStr.append("SELECT 'PromoterSubEmail', o.organizationid, ")
        .append(" o.organizationname, o.organizationurl, p.subscriptionstatus, m.description2 ")
        .append("FROM Organization o ")
        .append("LEFT JOIN Patron_Solicitation p ON p.organization_id = o.organizationid ")
        .append("AND p.patron_profile_id = :patronProfileId ")
        .append("JOIN User_Info u ON u.organization_id = o.organizationid ")
        .append("AND userinfoid = :userInfoId ")
        .append("LEFT JOIN Master_Code_Table m ON m.mastercodeid = p.solicitation_type_mct_id ")
        .append("AND m.description1 = 'PromoterSubEmail' ").append("WHERE istenant = 'F' ");
    return em.createNativeQuery(queryStr.toString())
        .setParameter("patronProfileId", patronProfileId).setParameter("userInfoId", userInfoId)
        .getResultList();
  }

  @Override
  public PatronProfile getPatronProfileByEmailAcctType(Long accountNo, String email, String accountTypeCode,
		  String externalCustId) {

	  StringBuilder queryStr = new StringBuilder();

	  queryStr.append("SELECT p.patronprofileid, p.identityno, p.accno, p.status, p.firstname, ")
	  .append(" p.lastname, p.emailaddress, p.isreceiveticketingagent, p.isreceivepromoter, p.isreceivevenue, p.isbillingaddresssameasmailing, ")
	  .append("p.isdonotsignup, p.organizationname, p.companyid, p.companyname, p.employeeid, p.guestof, ")
	  .append(" p.externalid, p.acctremark, p.acc_Type_Mct_Id, p.gender_Mct_Id, p.title_Mct_Id, p.nationality, ")
	  .append(" p.countryofresidence, p.createddate,p.created_by, p.updateddate, p.updated_by, p.identity_No_Type_Mct_Id , p.prefer_Language, p.ext_cust_id ")
	  .append(" FROM PATRON_PROFILE p ")
	  .append(" JOIN  MASTER_CODE_TABLE mct  ON p.ACC_TYPE_MCT_ID = mct.MASTERCODEID and mct.CATEGORYCODE = 'Patron Account Type' ")
	  .append(" WHERE (:accno is null or :accno=-1 or accno = :accno) ")
	  .append(" AND ((:email is null or :email = '' or (LOWER(p.emailaddress) = LOWER(:email))) " )
	  .append(" and (:code is null or :code = '' or (mct.DESCRIPTION2 = :code) )")
	  .append(" and (:ext_cust_id is null or :ext_cust_id = '' or ext_cust_id = :ext_cust_id))");
	  
	  List<PatronProfile> resultList = (List<PatronProfile>) em.createNativeQuery(queryStr.toString(), PatronProfile.class)
			  .setParameter("accno", accountNo)
			  .setParameter("email", email)
			  .setParameter("code", accountTypeCode)
			  .setParameter("ext_cust_id", externalCustId).setMaxResults(10)
			  .getResultList();
	  
	  //may return more than one record as email is not unique key
	  if(resultList != null && !resultList.isEmpty() && resultList.size() > 0){
		  return resultList.get(0);
	  }
	  
	  logger.info("End getPatronProfileByEmail() " + queryStr);
	  return null;
  }

}

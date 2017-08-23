package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.constant.PatronConstant;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.repo.IPatronProfileRepository;
import com.stixcloud.patron.repo.PatronProfileRepository;
import com.stixcloud.patron.util.MasterCodeTableUtil;
import com.stixcloud.policyagent.util.PAUtil;

@Service
public class PatronProfileService implements IServiceUpdate {

  private static final Logger LOGGER = LogManager.getLogger(PatronProfileService.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IPatronProfileRepository patronProfileRepo;
  @Autowired
  private MasterCodeTableService masterCodeTableService;
  @Autowired
  private MasterCodeTableUtil mctUtil;
  @Autowired
  private PatronProfileRepository patronProfileRepository;

  public boolean isChanged(PatronProfileJson request, PatronProfileJson original) {
    if (null != request.getFirstName() && !request.getFirstName().equals(original.getFirstName())
        || null != request.getLastName() && !request.getLastName().equals(original.getLastName())
        || null != request.getIdentification() && !request.getIdentification().equals(original.getIdentification())
        || null != request.getNationality() && !request.getNationality().equals(original.getNationality())
        || null != request.getCountryOfResidence() && !request.getCountryOfResidence().equals(original.getCountryOfResidence())
        || null !=request.getTitle()&&!request.getTitle().equals(original.getTitle())
        || null !=request.getPatronType()&&!request.getPatronType().equals(original.getPatronType())
        || null !=request.getPreferLanguage()&&!request.getPreferLanguage().equals(original.getPreferLanguage())
        || null !=request.getExternalCustomerID()&&!request.getExternalCustomerID().equals(original.getExternalCustomerID())
        || null !=request.getIsReceivePromoter()&&!request.getIsReceivePromoter().equals(original.getIsReceivePromoter())
        || null !=request.getIsReceiveTicketingAgent()&&!request.getIsReceiveTicketingAgent().equals(original.getIsReceiveTicketingAgent())
        || null !=request.getIsReceiveVenue()&&!request.getIsReceiveVenue().equals(original.getIsReceiveVenue())
        ) {
      return true;
    }
    return false;
  }

  public void updatePatron(PatronProfileJson request, Long entityId, Long userInfoId) throws SisticApiException {
    LOGGER.info("Update patron profile is in progress.");
    PatronProfile patron = patronProfileRepo.findOne(entityId);
    if (patron != null) {
      mapPatronProfileJson(patron, request, userInfoId);
      patronProfileRepo.save(patron);
      LOGGER.info("Update patron profile was successful.");
    }
  }

  public void updatePatron(PatronRequest request, Long accNo, Long userInfoId) throws SisticApiException {
    LOGGER.info("Update patron profile is in progress.");
    PatronProfile patron = patronProfileRepo.findByAccNo(accNo);
    if (patron != null) {
      mapPatronProfileJson(patron, request, userInfoId);
      patronProfileRepo.save(patron);
      LOGGER.info("Update patron profile was successful.");
    }
  }

  public PatronProfile insertPatronProfile(PatronProfile patronProfile) {
    return patronProfileRepo.save(patronProfile);
  }

  public Long getSequenceAccNo() {
    return patronProfileRepository.getSequenceAccNo();
  }

  public PatronProfile findPatronProfile(Long accNo) {
    return patronProfileRepo.findByAccNo(accNo);
  }

  /**
   * map {@link PatronProfileJson} to {@link PatronProfile}
   * 
   * @param patron
   * @param request
   * @param userInfoId 
   * @throws SisticApiException
   */
  public PatronProfile mapPatronProfileJson(PatronProfile patron, PatronProfileJson request, Long userInfoId)
      throws SisticApiException {

    if (patron == null) {
      patron = new PatronProfile();
    }

    if(StringUtils.isNotBlank(request.getFirstName())){
        patron.setFirstname(request.getFirstName());
    }
    
    if(StringUtils.isNotBlank(request.getLastName())){
    	patron.setLastname(request.getLastName());
    }
    
    patron.setIsbillingaddresssameasmailing(request.isBillingAddressSameAsMailing());
    List<MasterCodeTable> titleMctList =
        masterCodeTableService.findByCategory(PatronConstant.MASTER_CODE_TITLE_CATEGORY.getValue());
    List<MasterCodeTable> genderMctList = masterCodeTableService
        .findByCategory(PatronConstant.MASTER_CODE_GENDER_CATEGORY.getValue());
    List<MasterCodeTable> genderTitleMct =
        mctUtil.getMasterCodeTable(request.getTitle(), titleMctList, genderMctList);

    if (genderTitleMct == null || genderTitleMct.isEmpty()) {
      LOGGER.error("Title and gender data not found");
      throw new SisticApiException("patron.registration.title.gender.notFound", messageSource.getMessage(
          "patron.registration.title.gender.notFound", null, LocaleContextHolder.getLocale()));
    }

    if (null != request.getIdentification()) {
      patron.setIdentityno(request.getIdentification().getNo());
      List<MasterCodeTable> masterCodeTable = masterCodeTableService
          .findByCategory(PatronConstant.MASTER_CODE_CATEGORY_IDENTITY_NO.getValue());
      MasterCodeTable mct = masterCodeTable.stream()
          .filter(p -> p.getDescription1().equals(request.getIdentification().getType()))
          .findFirst().orElse(null);
      if (mct != null) {
        Long masterCodeTableId = mct.getMastercodeid();
        patron.setIdentityNoTypeMctId(masterCodeTableId);
      } else {
        LOGGER.error("Can't get identity no information");
        throw new SisticApiException("patron.profile.invalid.identification.error", messageSource.getMessage(
            "patron.profile.invalid.identification.error", null, LocaleContextHolder.getLocale()));
      }
    }
    MasterCodeTable mctTitle = genderTitleMct.get(1);
    MasterCodeTable mctGender = genderTitleMct.get(0);
    if (mctTitle != null) {
      Long masterCodeTableTitleId = mctTitle.getMastercodeid();
      patron.setTitleMctId(masterCodeTableTitleId);
    }

    if (mctGender != null) {
      Long masterCodeTableTitleId = mctGender.getMastercodeid();
      patron.setGenderMctId(masterCodeTableTitleId);
    }

    if(StringUtils.isNotBlank(request.getNationality())){
        patron.setNationality(request.getNationality());
      }
    
    if(StringUtils.isNotBlank(request.getCountryOfResidence())){
        patron.setCountryofresidence(request.getCountryOfResidence());
    }
    
    patron.setUpdateddate(OffsetDateTime.now());
    
    int userInfoIdInt = Math.toIntExact(userInfoId);
    patron.setUpdatedBy(userInfoIdInt);

    if (StringUtils.isNotBlank(request.getPatronType())) {
      List<MasterCodeTable> patronAccountTypeList = masterCodeTableService
          .findByCategory(PatronConstant.MASTER_CODE_CATEGORY_PATRON_ACCOUNT_TYPE.getValue());
      MasterCodeTable masterCodeTable = patronAccountTypeList.stream()
          .filter(p -> p.getDescription1().equals(request.getPatronType())).findFirst()
          .orElse(null);
      Long patronAccountType = masterCodeTable != null ? masterCodeTable.getMastercodeid() : 90L;
      patron.setAccTypeMctId(patronAccountType);
    }
    
    if(StringUtils.isNotBlank(request.getPreferLanguage())){
    	patron.setPreferLanguage(request.getPreferLanguage());
    }
    
    if(StringUtils.isNotBlank(request.getExternalCustomerID())){
    	patron.setExtCustId(request.getExternalCustomerID());
    }
    
    if(null != request.getIsReceivePromoter()){
       patron.setIsreceivepromoter(request.getIsReceivePromoter());
    }
    if(null != request.getIsReceiveTicketingAgent()){
       patron.setIsreceiveticketingagent(request.getIsReceiveTicketingAgent());
    }
    if(null != request.getIsReceiveVenue()){
       patron.setIsreceivevenue(request.getIsReceiveVenue());
    }
    return patron;
  }
}

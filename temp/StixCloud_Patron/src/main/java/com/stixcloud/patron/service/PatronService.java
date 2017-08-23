package com.stixcloud.patron.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.PatronAttributes;
import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.PatronSolicitationUpdateRequest;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.api.json.SolicitationJson;
import com.stixcloud.patron.constant.ActivationStatus;
import com.stixcloud.patron.constant.EmailConstants;
import com.stixcloud.patron.constant.PatronConstant;
import com.stixcloud.patron.constant.TenantConstant;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.domain.PatronProfileView;
import com.stixcloud.domain.TenantConfig;
import com.stixcloud.patron.repo.IEmailTemplateRepository;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ITenantConfigRepository;
import com.stixcloud.patron.repo.ProductLiveRepository;
import com.stixcloud.patron.util.MasterCodeTableUtil;
import com.stixcloud.patron.util.PatronProfileUtil;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED,
    rollbackFor = Exception.class)
public class PatronService implements IPatronService {

  private static final Logger logger = LogManager.getLogger(PatronService.class);
  private static final Integer EXIST_EMAIL = 1;
  public static final Long ACC_TYPE_MCT_ID = 90L;
  public static final String ACC_TYPE_INDIVIDUAL = "I";
  public static final String ACC_TYPE_GUEST = "G";
  public static final String NATIONALITY_DEFAULT = "-";
  public static final String COUNTRY_OF_RESIDENT_DEFAULT = "-";
  public static final Long IDENTITY_NO_TYPE_MCT_ID_DEFAULT = 648L;
  public static final String NAME_DEFAULT = "-";

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IPatronRepository repo;
  @Autowired
  private IPatronProfileJsonService patronProfileJsonService;
  @Autowired
  private PatronProfileService patronProfileService;
  @Autowired
  private PatronPhoneService patronPhoneService;
  @Autowired
  private PatronInternetAccountService patronInternetAccountService;
  @Autowired
  private PatronAdvanceProfileService patronAdvanceProfileService;
  @Autowired
  private PatronAddressService patronAddressService;
  @Autowired
  private MasterCodeTableService masterCodeTableService;
  @Autowired
  private PatronAttributesService patronAttributesService;
  @Autowired
  private MasterCodeTableUtil mctUtil;
  @Autowired
  private IPatronSolicitationViewService patronSolicitationViewService;
  @Autowired
  private IPatronSolicitationService patronSolicitationService;
  @Autowired
  private IEmailService emailService;
  @Autowired
  private IEmailTemplateRepository emailTemplateRepo;

  @Autowired
  private VelocityEngine velocityEngine;

  @Autowired
  private ITenantConfigRepository tenantConfigRepo;

  @Autowired
  private ProductLiveRepository productLiveRepository;

  private MasterCodeTable mct = null;
  private MasterCodeTable genderMct = null;

  @Override
  public boolean checkExistEmailAddress(String emailAddress) {
    Integer result = repo.checkExistEmailAddress(emailAddress);
    return EXIST_EMAIL.equals(result);
  }

  @Override
  public List<PatronProfileView> getPatronProfile(Long patronProfileId) {
    return repo.getPatronProfile(patronProfileId);
  }

  @Override
  public PatronProfileJson convertToResponse(List<PatronProfileView> patronProfileViews) {
    logger.info("Start converting patron to json response");
    PatronProfileJson response = new PatronProfileJson();
    PatronProfileView patronProfile = patronProfileViews.get(0);
    response.setEmail(patronProfile.getEmailAddress());
    response.setTitle(patronProfile.getTitle());
    response.setAcctNum(patronProfile.getAcctNum());
    response.setFirstName(patronProfile.getFirstName());
    response.setLastName(patronProfile.getLastName());
    response.setYearOfBirth(patronProfile.getYearOfDob());
    response.setNationality(patronProfile.getNationality());
    response.setCountryOfResidence(patronProfile.getCountryOfResidence());
    IdentificationJson identity =
        new IdentificationJson(patronProfile.getIdentityName(), patronProfile.getIdentityNo());
    response.setIdentification(identity);
    if (patronProfile.getIsBillingSameAsMailing() != null) {
      response.setBillingAddressSameAsMailing(
          "T".equals(patronProfile.getIsBillingSameAsMailing()) ? true : false);
    }
    
    List<AddressJson> addresses = new ArrayList<AddressJson>();
    List<ContactJson> contactNos = new ArrayList<ContactJson>();
    List<String> addressType = patronProfileViews.stream().map(PatronProfileView::getAddressType)
        .collect(Collectors.toList());
    if (addressType != null && !addressType.isEmpty() && !addressType.contains(null)) {
      Map<String, List<PatronProfileView>> addressMap = patronProfileViews.stream()
          .collect(Collectors.groupingBy(PatronProfileView::getAddressType));
      addressMap.forEach((key, value) -> {
        PatronProfileView tmpView = value.get(0);
        AddressJson add = new AddressJson(key, tmpView.getCountry(), tmpView.getBlockNo(),
            tmpView.getUnitNo(), tmpView.getBuildingName(), tmpView.getPostCode());
        addresses.add(add);

      });
    }

    List<String> phoneType = patronProfileViews.stream().map(PatronProfileView::getPhoneType).collect(Collectors.toList());
    if(null != phoneType && !phoneType.isEmpty() && !phoneType.contains(null)){
      Map<String, List<PatronProfileView>> phoneMap =
          patronProfileViews.stream().collect(Collectors.groupingBy(PatronProfileView::getPhoneType));
      phoneMap.forEach((key, value) -> {
        PatronProfileView tmpViewPhone = value.get(0);
        CountryPhoneCodeJson countryPhoneCode = new CountryPhoneCodeJson(
            tmpViewPhone.getCountryCode(), tmpViewPhone.getCountryCallingCode());
        ContactJson contactNo =
            new ContactJson(key, countryPhoneCode, tmpViewPhone.getPhoneNumber());
        contactNos.add(contactNo);
      });
    }
    
    if (!addresses.isEmpty()) {
      response.setAddresses(addresses);
    }
    response.setContacts(contactNos);
    logger.info("End converting patron to json response");
    return response;
  }

  @Override
  public void updatePatronSolicitation(PatronSolicitationUpdateRequest request, Long patronId)
      throws SisticApiException {
    logger.info("Start method updatePatronSolicitation");
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    List<PatronSolicitation> patronSolicitationUpdate =
        patronSolicitationService.findByPatronProfileId(patronId, userInfoId);
    List<MasterCodeTable> mctSolicitationType =
        masterCodeTableService.findByCategory("Solicitation Type");
    Set<Long> patronSolicitationIdSet = new HashSet<Long>();
    List<SolicitationJson> solicitationJsons = request.getSolicitationList();

    boolean isUpdate = false;
    if (solicitationJsons != null && !solicitationJsons.isEmpty()) {
      for (SolicitationJson sJson : solicitationJsons) {
        boolean isNew = false;
        if (patronSolicitationUpdate != null && !patronSolicitationUpdate.isEmpty()) {
          for (PatronSolicitation pUpdate : patronSolicitationUpdate) {

            isNew = false;
            MasterCodeTable mct = mctSolicitationType.stream()
                .filter(q -> q.getMastercodeid().equals(pUpdate.getSolicitationTypeMctId()))
                .findFirst().orElse(null);
            if (sJson.getSolicitationType().equals(mct.getDescription1())
                && sJson.getOrganizationID().equals(pUpdate.getOrganizationId())) {
              if (pUpdate.getSubscriptionstatus().compareTo(sJson.getSubscribed()) != 0) {
                pUpdate.setSubscriptionstatus(sJson.getSubscribed());
                pUpdate.setUpdatedBy(TenantContextHolder.getTenant().getUserInfoId());
                pUpdate.setUpdateddate(OffsetDateTime.now());
                isUpdate = true;
              }
              patronSolicitationIdSet.add(pUpdate.getPatronsolicitationid());
              break;
            } else {
              isNew = true;
            }
          }
        } else {
          isNew = true;
        }
        if (isNew) {
          if (!sJson.getSubscribed()) {
            logger.warn("New solicitation with status is false. Do not need to save.");
          } else {

            MasterCodeTable mct = mctSolicitationType.stream()
                .filter(p -> p.getDescription1().equals(sJson.getSolicitationType())).findFirst()
                .orElse(null);
            if (mct == null) {
              logger.error("Can't get solicitation type");
              throw new SisticApiException("patron.sucscriptions.invalid.solicitation.type.error",
                  messageSource.getMessage("patron.sucscriptions.invalid.solicitation.type.error",
                      null, LocaleContextHolder.getLocale()));
            }
            PatronSolicitation pNew = new PatronSolicitation(patronId, mct.getMastercodeid(), null,
                sJson.getOrganizationID(), sJson.getSubscribed(), OffsetDateTime.now(), userInfoId);
            patronSolicitationService.save(pNew);
          }
        }
      }

      if (isUpdate) {
        patronSolicitationService.update(patronSolicitationUpdate);
      }
    }
    logger.info("End method updatePatronSolicitation");
  }
  
  /**
   * insert Patron Profile
   * @param request
   * @param userInfoId 
   * @return {@link PatronRequest}
   * @throws SisticApiException
   */
  private PatronProfile insertPatronProfile(PatronRequest request, Long userInfoId) throws SisticApiException{
	  
	    Long titleMctId = null;
	    Long genderMctId = null;
	    Long identityMctId = IDENTITY_NO_TYPE_MCT_ID_DEFAULT;
	    String title = request.getTitle();
	    // get data MasterCodeTable by Title and Gender
	    List<MasterCodeTable> genderTitleMct = null;

	    List<MasterCodeTable> titleMctList =
	        masterCodeTableService.findByCategory(PatronConstant.MASTER_CODE_TITLE_CATEGORY.getValue());
	    List<MasterCodeTable> genderMctList = masterCodeTableService
	        .findByCategory(PatronConstant.MASTER_CODE_GENDER_CATEGORY.getValue());
	    genderTitleMct = mctUtil.getMasterCodeTable(title, titleMctList, genderMctList);

	    if (genderTitleMct == null || genderTitleMct.isEmpty()) {
	      logger.error("Title and gender data not found");
	      throw new SisticApiException("patron.registration.title.gender.notFound",messageSource.getMessage(
	          "patron.registration.title.gender.notFound", null, LocaleContextHolder.getLocale()));
	    }

	    genderMct = genderTitleMct.get(0);
	    mct = genderTitleMct.get(1);

	    titleMctId = mct != null ? mct.getMastercodeid() : null;
	    genderMctId = genderMct != null ? genderMct.getMastercodeid() : null;
	    
	    MasterCodeTable masterCodeTable = null;
	    
	    String identificationNo = null;
	    
	    if(null != request.getIdentification()){
	      identificationNo = request.getIdentification().getNo();
	      
    	    List<MasterCodeTable> identityMctList = masterCodeTableService
    	        .findByCategory(PatronConstant.MASTER_CODE_CATEGORY_IDENTITY_NO.getValue());
    	    masterCodeTable = identityMctList.stream()
    	        .filter(p -> p.getDescription1().equals(request.getIdentification().getType())).findFirst()
    	        .orElse(null);
    	    identityMctId = masterCodeTable != null ? masterCodeTable.getMastercodeid() : IDENTITY_NO_TYPE_MCT_ID_DEFAULT;
	    }
	    
	    List<MasterCodeTable> patronAccountTypeList =
		        masterCodeTableService.findByCategory(PatronConstant.MASTER_CODE_CATEGORY_PATRON_ACCOUNT_TYPE.getValue());
	    masterCodeTable = patronAccountTypeList.stream()
		        .filter(p -> p.getDescription2().equals(request.getPatronType())).findFirst()
		        .orElse(null);
	    Long patronAccountType =  masterCodeTable != null ? masterCodeTable.getMastercodeid() : ACC_TYPE_MCT_ID;
	    
	    Integer userInfoIdInt = Math.toIntExact(userInfoId);
	    
	    // Insert patron profile
	    Boolean isReceiveTicketingAgent =
	        request.getIsReceiveTicketingAgent() == null ? false : request.getIsReceiveTicketingAgent();
	    Boolean isReceivePromoter =
	        request.getIsReceivePromoter() == null ? false : request.getIsReceivePromoter();
	    Boolean isReceiveVenue = request.getIsReceiveVenue() == null ? false : request.getIsReceiveVenue();
	    Long accno = patronProfileService.getSequenceAccNo();
	    
	    int accountStatus = ActivationStatus.ACTIVE.getStatus();
	    if(request.getStatus() != null){
	      accountStatus = request.getStatus();
	    }
	    
	    String nationality = StringUtils.isBlank(request.getNationality()) ? NATIONALITY_DEFAULT : request.getNationality();
	    String countryOfResidence = StringUtils.isBlank(request.getCountryOfResidence()) ?COUNTRY_OF_RESIDENT_DEFAULT : request.getCountryOfResidence();
	    String firstName = StringUtils.isBlank(request.getFirstName()) ? NAME_DEFAULT : request.getFirstName();
	    String lastName = StringUtils.isBlank(request.getLastName()) ? NAME_DEFAULT : request.getLastName();
	    
	    PatronProfile patronProfile = new PatronProfile(identificationNo, accno,
	        accountStatus, firstName, lastName,
	        request.getEmail(), isReceiveTicketingAgent, isReceivePromoter, isReceiveVenue,
	        request.isBillingAddressSameAsMailing(), false, null, null, null, null, null, null, null,
	        patronAccountType, genderMctId, titleMctId, nationality,
	        countryOfResidence, OffsetDateTime.now(), userInfoIdInt, OffsetDateTime.now(),
	        userInfoIdInt, identityMctId, request.getPreferLanguage(),request.getExternalCustomerID());
	    PatronProfile insertedPatronProfile = patronProfileService.insertPatronProfile(patronProfile);
	    logger.info("Insert patron profile was successful");
	    
	    return insertedPatronProfile;
  }
  
  /**
   * send account created email
   * @param patron
   * @param languageCode
   * @throws NoSuchMessageException
   * @throws SisticApiException
   */
  private void prepareAndSendAccountCreatedEmail(PatronProfile patron, String preferLanguage)
      throws NoSuchMessageException, SisticApiException {
    logger.info("Start method prepareAndSendEmail");
    
    String templatePath = emailTemplateRepo
        .getEmailTemplatePath(EmailConstants.EMAIL_TEMPLATE_REGISTRATION_TYPE, preferLanguage);
    if (StringUtils.isBlank(templatePath)) {
      logger.error("Not found email template for registration");
      throw new SisticApiException("patron.registration.email.template.error", messageSource.getMessage("patron.registration.email.template.error",
          null, LocaleContextHolder.getLocale()));
    }

    String patronEmail = patron.getEmailaddress();

    String sender = EmailConstants.EMAIL_SENDER;

    List<TenantConfig> tenantConfigList = (List<TenantConfig>) tenantConfigRepo.findAll();
    Map<String, String> tenantConfigMap = tenantConfigList.stream()
        .collect(Collectors.toMap(TenantConfig::getKey, TenantConfig::getValue));

    if (tenantConfigMap.containsKey(EmailConstants.TENANT_BASED_FROM_ADDRESS)) {
      sender = tenantConfigMap.get(EmailConstants.TENANT_BASED_FROM_ADDRESS);
    }

    String subject = tenantConfigMap.get(EmailConstants.NEW_INTERNET_ACCOUNT_EMAIL_TITLE);

    Map<String, Object> map = new HashMap<String, Object>();
    String patronFullName = patron.getFirstname() + " " + patron.getLastname();

    map.put("name", patronFullName.toUpperCase());
    if (patron.getFirstname() != null && patron.getFirstname().trim().equals("-")) {
      map.put("firstname", "");
    } else {
      map.put("firstname", patron.getFirstname());
    }
    map.put("email", patron.getEmailaddress());
    
    String content =
        VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, templatePath, "UTF-8", map);
    emailService.sendEmail(content, subject, sender, patronEmail, null);
    logger.info("End method prepareAndSendEmail");
  }

  @Override
  public Object isPatronLock(String email) {
    try {
      return repo.isPatronLock(email);
    } catch (NoResultException e) {
      return null;
    }
  }

  @Override
  public List<PatronSolicitationJson> removeDuplicateSolicitation(
      List<PatronSolicitationJson> solicitationList) {
    if (CollectionUtils.isEmpty(solicitationList)) {
      return new ArrayList<>();
    }
    
    Map<String, PatronSolicitationJson> resultMap = new HashMap<>();
    solicitationList.stream().forEach(s -> {
      String key = s.getSolicitationType() + s.getOrganizationID();
      // add if patron solicitation doesn't exist or subscription status is true
      if (!resultMap.containsKey(key) || s.getSubscribed()) {
        resultMap.put(key, s);
      }
    });

    return new ArrayList<>(resultMap.values());
  }

  @Override
  public List<PatronSolicitationJson> getPatronSolicitationList(String tenantName,
      Long patronProfileId, List<Long> productIds, Boolean status) {
    logger.info("Start method getPatronSolicitationList");
    List<PatronSolicitationJson> solicitationJsonList = new ArrayList<PatronSolicitationJson>();
    AbstractPatronSolicitationService patronSoliService = null;
    switch (tenantName) {
      case TenantConstant.SISTIC:
        logger.info("Create Sistic patron solicitation service");
        patronSoliService = new SisticPatronSolicitationService(patronSolicitationService,
            productLiveRepository, masterCodeTableService, repo, patronSolicitationViewService);
        break;
      case TenantConstant.NGS:
        logger.info("Create NGS patron solicitation service");
        patronSoliService = new NgsPatronSolicitationService(repo, masterCodeTableService);
        break;
      case TenantConstant.ESPLANADE:
        logger.info("Create Esplanade patron solicitation service");
        patronSoliService = new EsplanadePatronSolicitationService();
        break;
      case TenantConstant.VIZPRO:
        logger.info("Create Vizpro patron solicitation service");
        patronSoliService = new VizproPatronSolicitationService(patronSolicitationService,
            productLiveRepository, masterCodeTableService, repo, patronSolicitationViewService);
        break;
      default:
        logger.info("Patron solicitation service has not implemented for tenant: " + tenantName);
        break;
    }

    if (patronSoliService != null) {
      solicitationJsonList =
          patronSoliService.getPatronSolicitationList(patronProfileId, productIds, status);
    }

    logger.info("End method getPatronSolicitationList");
    return removeDuplicateSolicitation(solicitationJsonList);
  }

  @Override
  public Long createUpdatePatronProfile(PatronUpdateRequest request, Long userInfoId) throws SisticApiException {

	if(ACC_TYPE_GUEST.equals(request.getPatronType()) && StringUtils.isBlank(request.getEmail())) {
		throw new SisticApiException("patron.profile.email.error",
				messageSource.getMessage("patron.profile.email.error", null, LocaleContextHolder.getLocale()));
	}else{
		
	}

	Long accountNo = StringUtils.isNotBlank(request.getAccountNo()) ? Long.parseLong(request.getAccountNo()) : -1L;
	PatronProfile patronfile = null;
	patronfile = repo.getPatronProfileByEmailAcctType(accountNo, request.getEmail(), request.getPatronType(), request.getExternalCustomerID());
	
	if(null != patronfile){
		accountNo = patronfile.getAccno();
		request.setAccountNo(patronfile.getAccno().toString());
		request.setAccNo(accountNo.intValue());
		request.setAcctNum(accountNo);
		updatePatronProfile(request, patronfile, accountNo, userInfoId);
	}else{
		return createPatronProfile(request, userInfoId);
	}
	
  	return accountNo;
  }
  
@Override
public Long createPatronProfile(PatronRequest request, Long userInfoId) throws SisticApiException {

	logger.info("Start method insertPatronProfile");
    // [START] check exist account patron
    
	if (!ACC_TYPE_GUEST.equals(request.getPatronType()) && (null == request.getDisableWebAccount() || !request.getDisableWebAccount()) && StringUtils.isNotBlank(request.getEmail())) {
		boolean isExistPatron = checkExistEmailAddress(request.getEmail());
		if (isExistPatron) {
			logger.info("Email is already existed");
			throw new SisticApiException("patron.new.email.exist",
					messageSource.getMessage("patron.new.email.exist", null, LocaleContextHolder.getLocale()));
		}
	} else if(ACC_TYPE_GUEST.equals(request.getPatronType())){
		if (StringUtils.isBlank(request.getEmail())) {
			throw new SisticApiException("patron.profile.email.error",
					messageSource.getMessage("patron.profile.email.error", null, LocaleContextHolder.getLocale()));
		}
		
		try{
			PatronProfile guestPatronProfile = repo.getPatronProfileByEmailAcctType(request.getEmail(), request.getPatronType());
			if (null != guestPatronProfile) {
				return guestPatronProfile.getAccno();
			}
		//record not found
		}catch(EmptyResultDataAccessException ex){
		}
	}
    // [END] check exist account patron
    
    Integer userInfoIdInt = Math.toIntExact(userInfoId);

    PatronProfile patronProfile = insertPatronProfile(request, userInfoId);
    Long patronProfileId = patronProfile.getPatronprofileid();
    
    // Insert patron advance profile
    patronAdvanceProfileService.insertPatronAdvanceProfile(patronProfileId, request.getYearOfBirth(),request.getTitle());

    // Insert patron phone
    patronPhoneService.insertPatronContact(patronProfileId, request.getContacts());

    // Insert patron address
    patronAddressService.insertPatronAddress(userInfoIdInt, patronProfileId, request.getAddresses());
    
    // Insert patron solicitation
    Boolean isReceiveTicketingAgent =
            request.getIsReceiveTicketingAgent() == null ? false : request.getIsReceiveTicketingAgent();
    if (isReceiveTicketingAgent) {
        patronSolicitationService.save(userInfoId, patronProfileId, request.getOrganizationId());
    }
    
    //Insert patron attributes
    if(null != request.getPatronAttributes() && !request.getPatronAttributes().isEmpty()){
      PatronAttributes patronAttributes = new PatronAttributes(patronProfileId, request.getPatronAttributes().toString(), OffsetDateTime.now(), userInfoId, OffsetDateTime.now(), userInfoId);
      patronAttributesService.save(patronAttributes);
    }
    
    // Insert patron internet account
    if( (null == request.getDisableWebAccount() || !request.getDisableWebAccount()) && !ACC_TYPE_GUEST.equals(request.getPatronType()) && StringUtils.isNotBlank(request.getEmail()) ){
      
      if(null == request.getPassword() || StringUtils.isEmpty(request.getPassword())){
        throw new SisticApiException("patron.password.empty", messageSource.getMessage("patron.password.empty", null,
            LocaleContextHolder.getLocale()));
      }
      
      String encryptPassword = PatronProfileUtil.encrypt(request.getPassword());
      PatronInternetAccount patronInternetAccount = new PatronInternetAccount(request.getEmail(),
      		encryptPassword, null, patronProfileId, 0);
      patronInternetAccountService.insertPatronInternetAccount(patronInternetAccount);
      
      //send email
      prepareAndSendAccountCreatedEmail(patronProfile, request.getPreferLanguage());
    }
    
	return patronProfile.getAccno();
}

@Override
public void updatePatronProfile(PatronUpdateRequest request, PatronProfile patronProfileParam ,Long accNo, Long userInfoId)
		throws SisticApiException {
	logger.info("Start updating patron");
    
	PatronProfile patronfile = patronProfileParam;
	if(null == patronfile){
		patronfile = patronProfileService.findPatronProfile(accNo);
	}
	//if patron not found
	if(null == patronfile){
		throw new SisticApiException("patron.profile.view.error",messageSource.getMessage("patron.not.found", null,
		          LocaleContextHolder.getLocale()));
	}
	Long patronId =patronfile.getPatronprofileid(); 
	
	PatronRequest original =
        patronProfileJsonService.getPatronProfileByAccNo(accNo);
    if (original == null) {
      original = new PatronRequest();
      original.setAddresses(new ArrayList<AddressJson>());
    }
    
    if (patronProfileService.isChanged(request, original)) {
      logger.info("Patron profile is changed -> start updating");
      patronProfileService.updatePatron(request, accNo, userInfoId);
      logger.info("Patron profile is changed -> end updating");
    }
    if (patronAddressService.isChanged(request, original)) {
      logger.info("Patron addresses are changed -> start updating");
      patronAddressService.updatePatron(request, patronId, userInfoId);
      logger.info("Patron addresses are changed -> end updating");
    }
    if (patronAdvanceProfileService.isChanged(request, original)) {
      logger.info("Patron advance profile is changed -> start updating");
      patronAdvanceProfileService.updatePatron(request, patronId, userInfoId);
      logger.info("Patron advance profile is changed -> end updating");
    }
    if (patronInternetAccountService.isChanged(request, original)) {
      logger.info("Patron internet account is changed -> start updating");
      patronInternetAccountService.updatePatron(request, patronId, userInfoId);
      logger.info("Patron internet account is changed -> end updating");
    }
    if (patronPhoneService.isChanged(request, original)) {
      logger.info("Patron phone is changed -> start updating");
      patronPhoneService.updatePatron(request, patronId, userInfoId);
      logger.info("Patron phone is changed -> end updating");
    }
    if(patronAttributesService.isChanged(request, original)){
    	patronAttributesService.update(request.getPatronAttributes(), patronId, userInfoId);
    }
    logger.info("End updating patron");
	
}

@Override
public List<PatronProfileView> getPatronProfileByAccNum(Long accNum) throws SisticApiException {
  return repo.getPatronProfileByAccNum(accNum);
}
}

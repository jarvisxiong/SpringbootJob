package com.stixcloud.patron.api;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.common.exception.JsonResponse;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.domain.PatronProfileView;
import com.stixcloud.patron.service.ICountryService;
import com.stixcloud.patron.service.IForgotPasswordService;
import com.stixcloud.patron.service.IPatronProfileJsonService;
import com.stixcloud.patron.service.IPatronService;
import com.stixcloud.patron.service.ITransactionHistoryService;
import com.stixcloud.patron.service.PatronInternetAccountService;
import com.stixcloud.patron.util.LanguageUtil;
import com.stixcloud.policyagent.util.PAUtil;
import com.stixcloud.patron.service.EmailService;

@RestController
@RequestMapping(value = "/{tenant}/", produces = "application/json")
public class PatronController {

  private static final Logger logger = LogManager.getLogger(PatronController.class);

  @Autowired
  private MessageSource messageSource;
  @Autowired
  private IPatronService patronService;
  @Autowired
  private IPatronProfileJsonService patronProfileJsonService;
  @Autowired
  private ITransactionHistoryService transactionHistoryService;
  @Autowired
  private IForgotPasswordService forgotPasswordService;
  @Autowired
  private ICountryService countryService;
  @Autowired
  PatronInternetAccountService patronInternetAccountService;
  @Autowired
  EmailService emailService;

  @RequestMapping(value = "/patrons/registration/availability/{email:.+}", method = RequestMethod.GET)
  @ResponseBody
  public JsonResponse checkEmail(@PathVariable("email") String email, Locale locale)
      throws NoSuchMessageException, SisticApiException {
    logger.info("Start checking availability of email for registration: " + email);
    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    boolean isExistEmail = patronService.checkExistEmailAddress(email);
    JsonResponse jresponse = null;
    if (isExistEmail) {
      logger.error("Given email already exist: " + email);
      throw new SisticApiException("patron.new.email.exist",
          messageSource.getMessage("patron.new.email.exist", null, locale));
    } else {
      logger.info("Given email is available: " + email);
      jresponse = new JsonResponse(HttpStatus.OK.toString(),
          messageSource.getMessage("patron.new.email.not.exist", null, locale));
    }
    logger.info("End checking availability of email for registration: " + email);
    return jresponse;
  }

  @RequestMapping(value = "/patrons/account/{accountNo}", method = RequestMethod.GET)
  @ResponseBody
  public PatronProfileJson getPatronProfile(@PathVariable("accountNo") Long accountNo, Locale locale)
      throws NoSuchMessageException, SisticApiException {
    PatronProfileJson response = null;
    logger.info("Start getting patron profile by accountNo: " + accountNo);
    List<PatronProfileView> patronProfileView = patronService.getPatronProfileByAccNum(accountNo);
    if (patronProfileView == null || patronProfileView.isEmpty()) {
      logger.error("Unable to get patron profile by accountNo: " + accountNo);
      throw new SisticApiException("patron.profile.view.error",
          messageSource.getMessage("patron.profile.view.error", null, locale));
    }

    response = patronService.convertToResponse(patronProfileView);
    patronProfileJsonService.savePatronProfileJson(response);
    logger.info("End getting patron profile by account No: " + accountNo);
    return response;
  }


  @RequestMapping(value = "/patrons/emailTest/{emailAddress}", method = RequestMethod.GET)
  @ResponseBody
  public String testEmail(@PathVariable String emailAddress, Locale locale) throws SisticApiException {
    try {
      emailService.sendEmail("testPatron", "testPatron", "customerservice@sistic.com.sg",
          emailAddress, null);
    } catch (Exception e) {
      logger.error(e);
    }

    return "success";
  }
  
  /**
   * update patron profile
   * @param accountNo
   * @param requestBody
   * @param request
   * @param locale
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/patrons/account/{accountNo}", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse updatePatronProfile(@PathVariable Long accountNo,
      @Valid @RequestBody PatronUpdateRequest requestBody, HttpServletRequest request, Locale locale)
      throws Exception {
    logger.info("Start updating patron profile with accountNo: " + accountNo);
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    patronService.updatePatronProfile(requestBody, null, accountNo,userInfoId);
    JsonResponse response = new JsonResponse(HttpStatus.OK.toString(),
        messageSource.getMessage("patron.profile.update.successful", null, locale));
    logger.info("End updating patron profile by accountNo: " + accountNo);
    return response;
  }

  @RequestMapping(value = "/patrons", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse createPatrons(@Valid @RequestBody PatronRequest requestBody, Locale locale)
      throws SisticApiException {
    logger.info("API : Start creating new patron with email: " + requestBody.getEmail());
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    Long accNo = patronService.createPatronProfile(requestBody, userInfoId);
    PatronCreationResponse response =
        new PatronCreationResponse(HttpStatus.OK.toString(), accNo.toString(),
            messageSource.getMessage("patron.profile.create.successful", null, locale));
    logger.info("API : End creating new patron with email: " + requestBody.getEmail());
    return response;
  }
  
  /**
   * create or update patron profile
   * @param accountNo
   * @param requestBody
   * @param request
   * @param locale
   * @return
   * @throws Exception
   */
  @RequestMapping(value = "/patrons/account", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse createUpdatePatronProfile(@Valid @RequestBody PatronUpdateRequest requestBody, HttpServletRequest request, Locale locale)
      throws Exception {

	Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    Long accNo = patronService.createUpdatePatronProfile(requestBody ,userInfoId);
    PatronCreationResponse response =
            new PatronCreationResponse(HttpStatus.CREATED.toString(), accNo.toString(),
                messageSource.getMessage("patron.profile.create.update.successful", null, locale));
    return response;
  }

  @RequestMapping(value = "/patrons/account/subscriptions", method = RequestMethod.POST)
  @ResponseBody
  public PatronSolicitationResponse getSolicitation(
      @Valid @RequestBody(required = false) List<Long> productIds,
      @RequestParam(value = "status", required = false) Boolean status, Locale locale)
      throws SisticApiException {
    Long patronProfileId = PAUtil.getPatronId();
    logger.info("Start getting patron solicitation by patron profile id: " + patronProfileId);

    String tenantName = TenantContextHolder.getTenant().getName();
    List<PatronSolicitationJson> solicitationJsonList =
        patronService.getPatronSolicitationList(tenantName, patronProfileId, productIds, status);

    if (solicitationJsonList != null && !solicitationJsonList.isEmpty()) {
      solicitationJsonList.sort(new Comparator<PatronSolicitationJson>() {
        public int compare(PatronSolicitationJson json1, PatronSolicitationJson json2) {
          return json1.getOrder().compareTo(json2.getOrder());
        }
      });
    }

    PatronSolicitationResponse response = new PatronSolicitationResponse(solicitationJsonList);
    logger.info("End getting patron solicitation by patron profile id: " + patronProfileId);
    return response;
  }

  @RequestMapping(value = "/patrons/account/subscriptions/update", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse updateSolicitation(
      @Valid @RequestBody PatronSolicitationUpdateRequest requestBody, Locale locale)
      throws SisticApiException {
    Long patronProfileId = PAUtil.getPatronId();
    logger.info("Start updating patron solicitation of patron with id: " + patronProfileId);
    patronService.updatePatronSolicitation(requestBody, patronProfileId);
    JsonResponse response = new JsonResponse(HttpStatus.OK.toString(),
        messageSource.getMessage("patron.solicitation.update.successful", null, locale));
    logger.info("End updating patron solicitation of patron with id: " + patronProfileId);
    return response;
  }

  @RequestMapping(value = "/patrons/account/transactions/{pageNo}/{pageSize}", method = RequestMethod.GET)
  @ResponseBody
  public TransactionHistoryResponse getTransactionHistory(
      @PathVariable(value = "pageNo") int pageNo, @PathVariable(value = "pageSize") int pageSize,
      Locale locale) {
    Long patronProfileId = PAUtil.getPatronId();
    logger.info("Start getting transactions by patron id: " + patronProfileId);
    TransactionHistoryResponse response =
        transactionHistoryService.getTransactions(patronProfileId, pageSize, pageNo, locale);
    logger.info("End getting transactions by patron id: " + patronProfileId);
    return response;
  }

  @RequestMapping(value = "/patrons/forgotpassword/{email:.+}", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse handleForgotPasswordRequest(@PathVariable(value = "email") String email,
      @RequestParam(value = "lang", required = false, defaultValue = "en_SG") String lang,
      Locale locale) throws SisticApiException {
    logger.info("Start handling resetting password for email: " + email);
    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    if (!forgotPasswordService.handleForgotPassword(email, lang)) {
      throw new SisticApiException("email.not.exist.error",messageSource.getMessage("email.not.exist.error", null, locale));
    }

    logger.info("End handling resetting password for email: " + email);
    return new JsonResponse(HttpStatus.OK.toString(),
        messageSource.getMessage("patron.forgot.password.success", null, locale));
  }


  @RequestMapping(value = "/patrons/forgotpassword/availability/{email}/{token}",
      method = RequestMethod.GET)
  @ResponseBody
  public JsonResponse validateForgotPasswordToken(@PathVariable(value = "email") String email,
      @PathVariable(value = "token") String token, Locale locale) throws SisticApiException {
    logger.info("Start validating email: " + email + " and token: " + token);
    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    if (!forgotPasswordService.validateToken(token, email)) {
      logger.error("Given email and token are invalid");
      throw new SisticApiException("patron.forgot.passsword.invalid.token.error",
          messageSource.getMessage("patron.forgot.passsword.invalid.token.error", null, locale));
    }

    logger.info("End validating email: " + email + " and token: " + token);
    return new JsonResponse(HttpStatus.OK.toString(),
        messageSource.getMessage("patron.forgot.password.token.available", null, locale));
  }

  @RequestMapping(value = "/patrons/changepassword/{email}/{token}", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse updatePassword(@PathVariable(value = "email") String email,
      @PathVariable(value = "token") String token,
      @RequestParam(value = "newPassword") String newPassword, Locale locale)
      throws SisticApiException {
    logger.info("Start changing password for patron with email: " + email + " and token: " + token);

    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    if (!forgotPasswordService.updatePassword(token, email, newPassword)) {
      logger.error("Given email and token are invalid");
      throw new SisticApiException("patron.forgot.password.invalid.reset.token.error", messageSource
          .getMessage("patron.forgot.password.invalid.reset.token.error", null, locale));
    }

    logger.info("End changing password for patron with email: " + email + " and token: " + token);
    return new JsonResponse(HttpStatus.OK.toString(),
        messageSource.getMessage("patron.reset.password.success", null, locale));
  }

  @RequestMapping(value = "/patrons/countries", method = RequestMethod.GET)
  @ResponseBody
  public CountryListResponse getCountryListResponse(
      @RequestParam(value = "lang", required = false, defaultValue = "en_SG") String lang,
      Locale locale) throws SisticApiException {
    logger.info("Start getting the list of countries");
    boolean isCorrectLang = LanguageUtil.checkLanguageCode(lang);
    if (!isCorrectLang) {
      lang = Locale.ENGLISH.getLanguage();
      logger.warn(messageSource.getMessage("patron.language.code.error", null, locale));
    }

    CountryListResponse response = countryService.getCountryListResponse(lang);
    if (response == null) {
      logger.error("No countries data found.");
      throw new SisticApiException("patron.country.nodata.error",
          messageSource.getMessage("patron.country.nodata.error", null, locale));
    }

    logger.info("End getting the list of countries");
    return response;
  }

  @RequestMapping(value = "/patrons/accountstatus", method = RequestMethod.GET)
  @ResponseBody
  public Boolean getPatronLockStatus(@RequestParam(value = "email", required = true) String email,
      Locale locale) throws SisticApiException {
    logger.info("Start getting lock status of patron with email: " + email);
    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    Object result = patronService.isPatronLock(email);
    if (result == null) {
      logger.error("Given email does not exist");
      throw new SisticApiException("email.not.exist.error", messageSource.getMessage("email.not.exist.error", null, locale));
    }
    int patronStatus = Integer.valueOf(result.toString());
    boolean isLocked = patronStatus == 0 ? true : false;
    logger.info("Lock status: " + isLocked);
    logger.info("End getting lock status of patron with email: " + email);
    return isLocked;
  }

  @RequestMapping(value = "/patrons/accountstatus", method = RequestMethod.POST)
  @ResponseBody
  public JsonResponse updatePatronLoginFailedCount(
      @RequestParam(value = "email", required = true) String email,
      @RequestParam(value = "loginSuccess", required = true) Boolean loginSuccess, Locale locale)
      throws SisticApiException {
    logger.info("Start updating login failed count of patron by email: " + email
        + " and login success status: " + loginSuccess);
    if (!EmailValidator.getInstance().isValid(email)) {
      logger.error("Given email is invalid: " + email);
      throw new SisticApiException("patron.email.wrong.format.error",
          messageSource.getMessage("patron.email.wrong.format.error", null, locale));
    }

    if (loginSuccess == null) {
      logger.error("Invalid loginSuccess value.");
      throw new SisticApiException("patron.invalid.loginsuccess.value",
          messageSource.getMessage("patron.invalid.loginsuccess.value", null, locale));
    }
    JsonResponse jresponse = null;

    PatronInternetAccount patronInternetAccount =
        patronInternetAccountService.findByEmailAddress(email);
    if (patronInternetAccount != null) {
      PatronInternetAccount patronLastUpdate = patronInternetAccountService
          .updatePatronLoginFailedCount(patronInternetAccount, loginSuccess);
      logger.info("Update patron lock failed count success. "
          + "Value current time loginFailedCount = " + patronLastUpdate.getLoginfailedcount());
      jresponse = new JsonResponse(HttpStatus.OK.toString(),
          messageSource.getMessage("patron.update.Loginfailedcount.success", null, locale));
    } else {
      // email does not exist
      logger.error("Given email does not exist.");
      throw new SisticApiException("email.not.exist.error",messageSource.getMessage("email.not.exist.error", null, locale));
    }

    logger.info("End updating login failed count of patron by email: " + email
        + " and login success status: " + loginSuccess);
    return jresponse;
  }

  @InitBinder("patronCreationRequest")
  protected void initBinderPatronCreation(WebDataBinder binder) {
    binder.setValidator(new PatronCreationRequestValidator());
  }

  @InitBinder("patronUpdateRequest")
  protected void initBinderPatronUpdate(WebDataBinder binder) {
    binder.setValidator(new PatronUpdateRequestValidator());
  }

  @InitBinder("patronSolicitationUpdateRequest")
  protected void initBinderPatronSolicitationUpdate(WebDataBinder binder) {
    binder.setValidator(new PatronSolicitationUpdateRequestValidator());
  }
}

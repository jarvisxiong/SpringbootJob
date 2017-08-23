package com.sistic.be.patron.api.service;

import java.io.IOException;
import java.util.List;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.AuthUtilService;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.PatronSolicitationUpdateRequest;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.countries.ListCountry;
import com.sistic.be.patron.api.auth.service.AuthenticationException;
import com.sistic.be.patron.api.auth.service.AuthorisationService;
import com.sistic.be.patron.api.model.PatronProfile;
import com.sistic.be.patron.api.model.PatronSolicitation;
import com.sistic.be.patron.api.model.PatronTransaction;
import com.sistic.be.patron.model.PatronJsonResponse;
import com.sistic.be.patron.model.PatronLogin;

/**
 * @author jianhong
 *
 */
@Service
public class ApiPatronService {
	
    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private MessageSource messageSource;
    
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private AuthUtilService authUtilService; 
	
	@Autowired
    private AuthorisationService authService;
	
	protected String serviceUrl;

	@Autowired
    public ApiPatronService(@Value("${service.patron.url}") String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
        
        logger.info(serviceUrl);
    }
	
	public JsonResponse checkPatronAvailability(String email) {
		// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/registration/availability/{email}", HttpMethod.GET, entity, JsonResponse.class, email);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/registration/availability/{email}", HttpMethod.GET, JsonResponse.class, null, email);
	}
	
//	public JsonResponse createNewPatron(PatronProfile patronProfile) throws JsonParseException, JsonMappingException, IOException {
//		// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/registration?org=1&lang=en", HttpMethod.POST, entity, JsonResponse.class);
//	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/registration?org=1&lang=en", HttpMethod.POST, JsonResponse.class, patronProfile);
//	}
	
	public PatronJsonResponse createNewPatron(PatronProfile patronProfile) throws JsonParseException, JsonMappingException, IOException {
		// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/registration?org=1&lang=en", HttpMethod.POST, entity, JsonResponse.class);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons", HttpMethod.POST, PatronJsonResponse.class, patronProfile);
	}
	
	public JsonResponse resetPatronPassword(String email, String preferLanguage) {
		//HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/forgotpassword/{email}?lang=en", HttpMethod.POST, entity, JsonResponse.class, email);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/forgotpassword/{email}?lang={preferLang}", HttpMethod.POST, JsonResponse.class, null, email, preferLanguage);
	}
	
	public JsonResponse checkResetPatronPasswordValidity(String email, String token) {
		// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/forgotpassword/availability/{email}/{token}", HttpMethod.GET, entity, JsonResponse.class, email, token);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/forgotpassword/availability/{email}/{token}", HttpMethod.GET, JsonResponse.class, null, email, token);
	}
	
	public JsonResponse changePatronPassword(String email, String token, String newPassword) {
		// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/changepassword/{email}/{token}?newPassword={newPassword}", HttpMethod.POST, entity, JsonResponse.class, email, token, newPassword);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/changepassword/{email}/{token}?newPassword={newPassword}", HttpMethod.POST, JsonResponse.class, null, email, token, newPassword);
	}
	
	public PatronProfile retrievePatronInfo() {
		// HttpEntity<PatronProfile> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/profile", HttpMethod.GET, entity, PatronProfile.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/profile", HttpMethod.GET, PatronProfile.class, null);
	}
	
	public JsonResponse updatePatronInfo(PatronProfile patronProfile) {
      // HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/profile", HttpMethod.POST, entity, JsonResponse.class);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/profile", HttpMethod.POST, JsonResponse.class, patronProfile);
	}
	
	public PatronProfile retrievePatronInfoForAccountNo(Long accountNo) {
		// HttpEntity<PatronProfile> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/profile", HttpMethod.GET, entity, PatronProfile.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/{accountNo}", HttpMethod.GET, PatronProfile.class, null, accountNo);
	}
	
	public PatronJsonResponse updatePatronInfoForAccountNo(PatronProfile patronProfile, Long accountNo) {
      // HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/profile", HttpMethod.POST, entity, JsonResponse.class);
	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/{accountNo}", HttpMethod.POST, PatronJsonResponse.class, patronProfile, accountNo);
	}
	
	public PatronSolicitation retrievePatronSubscriptions() throws JsonParseException, JsonMappingException, IOException {
		// HttpEntity<PatronSolicitation> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/subscriptions?status=true", HttpMethod.POST, entity, PatronSolicitation.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/subscriptions?status=true", HttpMethod.POST, PatronSolicitation.class, null);
	}
	
	public PatronSolicitation retrievePatronSubscriptionsForProducts(List<Long> productIds) throws JsonParseException, JsonMappingException, IOException {
		// HttpEntity<PatronSolicitation> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/subscriptions?status=false", HttpMethod.POST, entity, PatronSolicitation.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/subscriptions?status=false", HttpMethod.POST, PatronSolicitation.class, productIds);
	}
	
	public JsonResponse updatePatronSubscriptions(PatronSolicitationUpdateRequest patronSolicitationUpdateRequest) {
    	// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/subscriptions/update", HttpMethod.POST, entity, JsonResponse.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/subscriptions/update", HttpMethod.POST, JsonResponse.class, patronSolicitationUpdateRequest);
	}
	
	public PatronTransaction retrievePatronTransactions(Integer pageNo, Integer pageSize) throws JsonParseException, JsonMappingException, IOException {
    	// HttpEntity<String> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/account/transactions/{pageNo}/{pageSize}", HttpMethod.GET, entity, String.class, pageNo, pageSize);
    	String apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/account/transactions/{pageNo}/{pageSize}", HttpMethod.GET, String.class, null, pageNo, pageSize);
		
		if (apiResponse != null && !apiResponse.isEmpty()) {
			PatronTransaction patronTransaction = mapper.readValue(apiResponse, PatronTransaction.class);
			patronTransaction = patronTransaction.returnSortedPatronTransaction();
			return patronTransaction;
		}
		return new PatronTransaction();

	}
	
	public ListCountry retrieveCountryList(String lang) {
		if (lang == null) {
			lang = "en";
		}
		// HttpEntity<ListCountry> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/countries?lang={languageCode}", HttpMethod.GET, entity, ListCountry.class, lang);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/countries?lang={languageCode}", HttpMethod.GET, ListCountry.class, null, lang);
	}

	public Boolean getPatronLockStatus(String email) throws RestClientException {
    	// HttpEntity<Boolean> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/accountstatus?email=" + email, HttpMethod.GET, entity, Boolean.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/accountstatus?email=" + email, HttpMethod.GET, Boolean.class, null);
	}

	public JsonResponse updatePatronLoginFailedCount(String email, boolean loginSuccess)
			throws RestClientException {
    	// HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patron/auth/accountstatus?email=" + email + "&loginSuccess=" + loginSuccess, HttpMethod.POST, entity, JsonResponse.class);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/patrons/accountstatus?email=" + email + "&loginSuccess=" + loginSuccess, HttpMethod.POST, JsonResponse.class, null);
	}
	
	/**
	 * do login
	 * @param userName
	 * @param password
	 * @return PatronLogin info of the patron who logged in
	 * @throws JSONException 
	 */
	public PatronLogin login(String userName, String password) throws JSONException {
		// 1. check account lock
	  
		checkAccountLock(userName);
		String refreshToken = null;
		// 2. get access token
		try {
		    // this actually is refresh token 
		    refreshToken = authService.getAccessToken(userName, password);
			updatePatronLoginFailedCount(userName, true);
		} catch (HttpClientErrorException e) {
			updatePatronLoginFailedCount(userName, false);
			throw new AuthenticationException(e.getStatusCode().value(),
					messageSource.getMessage("login.account.invalid.password.error", null,
							LocaleContextHolder.getLocale()),
					OAuth2Exception.INVALID_GRANT);
		} catch (Exception e) {
			logger.error("Cannot get access token.", e);
			throw new AuthenticationException(HttpStatus.INTERNAL_SERVER_ERROR.value(),
		          messageSource.getMessage("system.error",
		              new Object[] {HttpStatus.INTERNAL_SERVER_ERROR.value()},
		              LocaleContextHolder.getLocale()),
		          null, e);
		}

		// 3. get and return PatronLogin
		PatronLogin patronLogin = authService.getPatronLogin(refreshToken);
		return patronLogin;
	}

	/**
	 * Check account lock status
	 * @param userName patron email
	 */
	private void checkAccountLock(String userName) {
		Boolean isAccountLock = false;
		try {
			isAccountLock = getPatronLockStatus(userName);
		} catch (RestClientException e) {
		    logger.error("Check account lock error.", e);
			if ("400 null".equals(e.getMessage())) {
				throw new AuthenticationException(HttpStatus.BAD_REQUEST.value(), messageSource
						.getMessage("login.email.notfound.error", null, LocaleContextHolder.getLocale()), null);
			} else {
				throw new AuthenticationException(HttpStatus.NOT_FOUND.value(),
						messageSource.getMessage("api.path.not.found", null, LocaleContextHolder.getLocale()),
						null);
			}
		}
		if (isAccountLock) {
			throw new AuthenticationException(HttpStatus.LOCKED.value(), messageSource
					.getMessage("login.account.lock.error", null, LocaleContextHolder.getLocale()), null);
		}
	}
	
}

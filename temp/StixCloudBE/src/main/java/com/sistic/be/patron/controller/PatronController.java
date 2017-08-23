/**
 * 
 */
package com.sistic.be.patron.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.InternalServerErrorException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.app.util.ObjectMapperUtil;
import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.app.util.SessionUtilService;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.app.util.ValidationUtilService;
import com.sistic.be.cart.api.model.CheckCartLineItem;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.PatronSolicitationUpdateRequest;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.countries.ListCountry;
import com.sistic.be.exception.ErrorJsonResponseException;
import com.sistic.be.exception.InvalidPatronLoginException;
import com.sistic.be.exception.RequireLoginException;
import com.sistic.be.patron.api.model.PatronProfile;
import com.sistic.be.patron.api.model.PatronSolicitation;
import com.sistic.be.patron.api.model.PatronTransaction;
import com.sistic.be.patron.api.model.Solicitation;
import com.sistic.be.patron.api.service.ApiPatronService;
import com.sistic.be.patron.form.PasswordResetForm;
import com.sistic.be.patron.form.PatronForm;
import com.sistic.be.patron.form.PatronFormMapper;
import com.sistic.be.patron.form.PatronSubscriptionsForm;
import com.sistic.be.patron.model.CheckCartPatron;
import com.sistic.be.patron.model.PatronCheckCartResponse;
import com.sistic.be.patron.model.PatronJsonResponse;
import com.sistic.be.patron.model.PatronJsonpCheckCartResponse;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.session.OnlineUserSession;
import com.sistic.be.recaptcha.RecaptchaService;

/**
 * @author jianhong This controller will return the template views related to
 *         patron management Example user registration, user management
 *
 */

@Validated
@Controller
@RequestMapping(value = "/{tenant}")
public class PatronController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ObjectMapper mapper;
	@Autowired
	Environment env;
	
	@Autowired
	SessionUtilService sessionUtil;
	@Autowired
	ValidationUtilService validationService;

	@Autowired
	ApiPatronService patronApi;
	@Autowired
	ApiCartService cartApi;
	@Autowired
	RecaptchaService recaptchaService;

	@Autowired
	PatronFormMapper patronFormMapper;


	@RequestMapping(value = "/patron/checkcart", method = RequestMethod.GET)
	@ResponseBody
	public PatronCheckCartResponse patronCheckCart(HttpServletResponse response, HttpSession session) {

//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", 0);
		
		PatronCheckCartResponse patronCheckCartResponse = new PatronCheckCartResponse();

		// get patron info from session
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession != null) {
			PatronLogin patronLogin = userSession.getPatronLogin();
			if (patronLogin == null) {
				throw new RequireLoginException();
			}
			
			CheckCartPatron checkCartPatron = (patronLogin != null) ? new CheckCartPatron(patronLogin.getFirstName(), patronLogin.getLastName()) : null;

			String cartGuid = userSession.getCartGuid();
			List<CheckCartLineItem> lineItemList = null;
			try {
				if (!cartGuid.isEmpty()) {
					lineItemList = cartApi.checkCart(cartGuid);
				}
			} catch (HttpStatusCodeException e) {
				/**
				 * Check cart will throw exception, cannot find cartGuid if no items, as cartGuid will be deleted
				 */
				logger.error("Check cart failed. Could not retrieve Cart for cartGuid");
			}
			
			patronCheckCartResponse.setCheckCartPatron(checkCartPatron);
			patronCheckCartResponse.setLineItemList(lineItemList);
			
			// Cart Timer		
			OffsetDateTime cartTimer = userSession.getCartTimer();
			if (lineItemList != null) {
				patronCheckCartResponse.setTotalLineItems((short) lineItemList.size());
			} else {
				patronCheckCartResponse.setTotalLineItems((short) 0);
				cartTimer = null;
			}

			patronCheckCartResponse.setReservedTime(cartTimer);
			patronCheckCartResponse.setTimeLeftSeconds(sessionUtil.getTimeLeftSeconds(cartTimer));

			// Set cartTimer in session to null if cartTimer == null (no items) but session cart time exists
			if (cartTimer == null && userSession.getCartTimer() != null) {
				userSession.setCartGuid("");
//				userSession.setCartTimer(null);	//TODO: Review timer fix solution
				SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
			}

			return patronCheckCartResponse;
		}
		else {
			return null;
		}
		
	}

	@RequestMapping(value = "/patron/checkcart/portal", method = RequestMethod.GET)
	@ResponseBody
	public PatronJsonpCheckCartResponse jsonpCheckcartResponse(HttpServletResponse response, HttpSession session) {
//		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//		response.setHeader("Pragma", "no-cache");
//		response.setDateHeader("Expires", 0);

		// get patron info from session
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession == null) {
			userSession = new OnlineUserSession();
		}
		PatronLogin patronLogin = userSession.getPatronLogin();

		if (patronLogin != null) {
			try {
				return new PatronJsonpCheckCartResponse(0,patronCheckCart(response, session));
			}catch(Exception e){
				throw new InternalServerErrorException();
			}
//			throw new RequireLoginException();
		}
		return new PatronJsonpCheckCartResponse(1);
	}

	@RequestMapping(value = "/patron/availability/{email:.+}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse checkPatronAvailability(HttpServletResponse response,
			@RequestHeader(value = "Authorization", required=false) String oauthToken,
			@PathVariable(value = "email", required = true) String email) {
		
		boolean isValidEmail = validationService.getEmailValid(email);
		if (!isValidEmail) {
			JsonResponse errorResponse = new JsonResponse(HttpStatus.BAD_REQUEST.value(), "Invalid email");
			throw new ErrorJsonResponseException(errorResponse);
		}
		
		try {
			JsonResponse apiResponse = patronApi.checkPatronAvailability(email);
			return apiResponse;
		} catch (HttpClientErrorException e) {
			logger.error("Could not retrieve patron availability", e);
			JsonResponse errorResponse = ObjectMapperUtil.getJsonResponse(e.getResponseBodyAsString());
			if (errorResponse == null) {
				errorResponse = new JsonResponse(HttpStatus.BAD_REQUEST.value(), null, "There is a problem registering the specified email");
				return errorResponse;
			}
			throw new ErrorJsonResponseException(errorResponse);
			
		}
	}

	/**
	 * Used for forgot password and resend token
	 */
	@RequestMapping(value = {"/patron/forgotpassword/{email:.+}", "/patron/forgotpassword" }, method = RequestMethod.GET)
	@ResponseBody
	public JsonResponse sendRetrievePasswordEmail(HttpServletResponse response,
			@RequestHeader(value = "Authorization", required=false) String oauthToken,
			@PathVariable(value = "email", required = false) String email) {
		
		// Email is required. Cannot use required=true or will not find request mapping
		if ((email == null) || email.isEmpty()) {
			JsonResponse emailRequiredError = new JsonResponse(HttpStatus.BAD_REQUEST.value(), "email address is required");
			throw new ErrorJsonResponseException(emailRequiredError);
		}
		String preferLanguage = TenantContextHolder.getTenant().getPreferLanguage();

		JsonResponse apiResponse = patronApi.resetPatronPassword(email, preferLanguage);
		return apiResponse;
	}

	@RequestMapping(value = "/patron/resetpassword", method = RequestMethod.GET)
	public String forgotPasswordForm(HttpServletResponse response,
			Model model,
			@ModelAttribute("passwordResetForm") PasswordResetForm passwordResetForm,
			@RequestParam(name = "email", required = true) String email,
			@RequestParam(name = "token", required = true) String token) {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		/**
		 * Call check reset password validity
		 * No exception thrown means 200, so its not expired
		 */
		try {
			patronApi.checkResetPatronPasswordValidity(email, token);
		} catch (HttpClientErrorException e) {
			// return 400, Forgot password token is invalid or already expired
//			JsonNode errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonNode.class);
			model.addAttribute("error", "This URL has expired. Please enter your email address to receive a new change password link.");
			return templateCode + "/view/passwordresend";
		}
		
		return templateCode + "/view/passwordreset";	// load the password reset form
	}

	//http://sistic.stixclouduat.com/SisticWebApp/ResetPassword.do?email=rocky_bogard@hotmail.com&token=fa367bc5-3e48-4680-b31b-62151e5e2ffb 
	@RequestMapping(value = "/patron/changepassword")
	public String changePatronPassword(HttpServletResponse response,
			@ModelAttribute("passwordResetForm") PasswordResetForm passwordResetForm,
			Model model) {
		
		String email = passwordResetForm.getEmail();
		String token = passwordResetForm.getToken();
		String password = passwordResetForm.getPassword();
		String confirmPwd = passwordResetForm.getConfirmPwd();
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		/**
		 * Check that password match confirm password
		 */
		if ((password!=null && !password.isEmpty()) && (confirmPwd !=null && !confirmPwd.isEmpty())) {
			
			if (password.equals(confirmPwd)) {
				/**
				 * Actual call to reset the password
				 */
				try {
					JsonResponse apiResponse = patronApi.changePatronPassword(email, token, password);
				} catch (HttpClientErrorException e) {
					model.addAttribute("error", "Your password has not been updated. Please call the SISTIC Hotline at (65) 6348 5555 for assistance.");
					return templateCode + "/view/passwordreset";
				}
			} else {
				model.addAttribute("error", "Your input passwords do not match.");
			}
		} else {
			model.addAttribute("error", "Your input passwords cannot be empty.");
		}

		// return success message to login redirect
		return templateCode + "/view/password-redirect";
	}

	@RequestMapping(value = "/patron/register", method = RequestMethod.GET)
	public String showPatronRegistration(HttpServletResponse response,
			@RequestParam(value = "applyemail", required = false) String applyemail,
			@RequestParam(value = "gotoUrl", required = false) String gotoUrl,
			@ModelAttribute("patronForm") PatronForm patronForm, Model model, HttpSession session) {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		// Check that patron is login, eg when user press Back button in browser
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession != null) {
			PatronLogin patronLogin = userSession.getPatronLogin();
			if (patronLogin != null) {
				// if logged in redirect to manageuser
				return "redirect:/" + TenantContextHolder.getTenantCode() + "/patron/management";
			}
		}
		
		// Check required request params were sent in. Use this way to prevent Json error showing
		if (applyemail == null || applyemail.isEmpty()|| gotoUrl == null || gotoUrl.isEmpty()) {
			String errorRedirectUrl = TenantContextHolder.getTenant().getDefaultRedirect();
			gotoUrl = errorRedirectUrl;
		}

		// Get list of countries
		ListCountry countryList = patronApi.retrieveCountryList(null);
		model.addAttribute("countryList", countryList);

		/**
		 * Set the applied email and default values
		 */
		patronForm.setEmailAddress(applyemail);
		patronForm = patronFormMapper.setPatronFormDefaults(patronForm);	// loads in info like Singapore as default

		model.addAttribute("patronForm", patronForm);

		model.addAttribute("gotoUrl", gotoUrl);

		return templateCode + "/view/registeruser";
	}

	/**
	 * After successful registration, will redirect to register-redirect.html and show success notification
	 * Patron will automatically be logged in after successful registration
	 * After patron confirms in registered.html, he will be redirected again to the initial gotoUrl
	 * If gotoUrl was blank, default will be sistic portal (set in properties file, based on {Whitelabel})
	 * @param gotoUrl is the url to redirect to after successful registration
	 * @param patronForm is the form posted by client
	 * @throws JSONException 
	 */
	@RequestMapping(value = "/patron/register", method = RequestMethod.POST)
	public String registerPatron(HttpServletResponse response,
		    HttpServletRequest request,
		    RedirectAttributes ra,
		    @RequestParam(value = "gotoUrl", required = false) String gotoUrl,
		    @RequestParam("g-recaptcha-response") String g_recaptcha_response,
			@Valid @ModelAttribute("patronForm") PatronForm patronForm, BindingResult bindingResult,
			Model model, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException, JSONException {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		// Decode URL to make sure gotoUrl is not encoded
		gotoUrl = URLDecoder.decode(gotoUrl, "UTF-8");
		
//		Cookie[] cookies = request.getCookies();
//		String oauthToken = "Bearer ";
		
		String errorMessage = null;	// used to output error messages back

//		/**
//		 * Please shift below to a service or util
//		 */
//		for(int i = 0; i < cookies.length; i++){
//			if("token".equalsIgnoreCase(cookies[i].getName())) {
//				oauthToken += cookies[i].getValue();
//				break;
//			}
//		}

		logger.info("patron form submit: " + patronForm.toString());
		
		/**
		 * Validate Recaptcha
		 */
		Boolean verificationSuccess = recaptchaService.verifyCaptcha(g_recaptcha_response);
		logger.info("g_recaptcha_response: " + g_recaptcha_response + " ,recaptcha verification: " + verificationSuccess);
		if (verificationSuccess != true) {
			// failed recaptcha
			ra.addFlashAttribute("error", "Error: Recaptcha has failed");
			ra.addFlashAttribute("patronForm", patronForm);
			ra.addAttribute("applyemail", patronForm.getEmailAddress());
			ra.addAttribute("gotoUrl", gotoUrl);
			return "redirect:/" + TenantContextHolder.getTenantCode() + "/patron/register";
		}
		
		// Get list of countries
		ListCountry countryList = patronApi.retrieveCountryList(null);

		// Create PatronProfile from PatronForm
		PatronProfile patronProfile = new PatronProfile();
		try {
			patronProfile = patronFormMapper.toPatronProfile(patronForm, countryList, "REGISTER");
			logger.info("Patron form to PatronProfileRequest Object: " + patronProfile.toString());
		} catch (IOException e) {
			logger.error("IO Exception in patron registration patronFormMapper.toPatronProfile");
		}

		JsonResponse apiResponse = null;
		try {
			apiResponse = patronApi.createNewPatron(patronProfile);
		} catch(HttpStatusCodeException e){
			try {
				apiResponse = mapper.readValue(e.getResponseBodyAsByteArray(), JsonResponse.class);
				errorMessage = apiResponse.getStatusMessage();
			} catch (JsonParseException ex) {
				logger.error("Json mapping error: " + e.getResponseBodyAsString(), e);
				errorMessage = e.getMessage();
			}
			ra.addFlashAttribute("error", errorMessage);
			ra.addFlashAttribute("patronForm", patronForm);
			ra.addAttribute("applyemail", patronForm.getEmailAddress());
			ra.addAttribute("gotoUrl", gotoUrl);
			return "redirect:/" + TenantContextHolder.getTenantCode() + "/patron/register";
		}
		logger.info("Create new patron api response: " + DebugUtil.writeWithPrettyPrinter(apiResponse));
		
		/**
		 * Login the patron
		 */
		PatronLogin patronLogin = patronApi.login(patronProfile.getEmail(), patronProfile.getPassword());
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession == null) {
			userSession = SessionUtil.createOnlineUser();
		}
        
		userSession.setPatronLogin(patronLogin);
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);

		/**
		 * Redirection related, should not be getting any registration errors here
		 */
		if (gotoUrl == null || gotoUrl.isEmpty()) {
			gotoUrl = TenantContextHolder.getTenant().getDefaultRedirect();
		}else{
			gotoUrl = URLDecoder.decode(gotoUrl, "utf-8");
		}

		model.addAttribute("email", patronForm.getEmailAddress());
		model.addAttribute("gotoUrl", gotoUrl);
		
		return templateCode + "/view/register-redirect";
	}

	@RequestMapping(value = "/patron/management", method = RequestMethod.GET)
	public String showPatronManagement(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("patronForm") PatronForm patronForm,
			@ModelAttribute("success") String successMessage,
			@ModelAttribute("error") String errorMessage,
			Locale locale, Model model, HttpSession session) {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}
		
		/**
		 * Remove unnecessary code
		 */
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		PatronProfile patronProfile = new PatronProfile();
		if (patronId != null) {
			patronProfile = patronApi.retrievePatronInfoForAccountNo(patronLogin.getAccountNo());
		}
		
		ListCountry countryList = patronApi.retrieveCountryList(null);
		model.addAttribute("countryList", countryList);

		try {
			patronForm = patronFormMapper.toPatronForm(patronProfile, countryList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("patronForm", patronForm);
		model.addAttribute("hasMembership", patronLogin.isHasMembership());
		
		logger.info("manage patron form: " + patronForm.toString());
		
		if (errorMessage!=null && !errorMessage.isEmpty()) {
			model.addAttribute("error", errorMessage);
		}
		else if (successMessage!=null && !successMessage.isEmpty()) {
			model.addAttribute("success", "User profile update successfully.");
		}

		return templateCode + "/view/manageuser";
	}

	@RequestMapping(value = "/patron/management", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView managePatron(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("patronForm") PatronForm patronForm,
			RedirectAttributes ra,
			Model model, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
		String errorMessage = "";

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}
		
//		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		/**
		 * Validate cross check passwords
		 */
		Boolean passwordsMatched = patronFormMapper.crossCheckPasswords(patronForm, "MANAGE");
		if (!passwordsMatched) {
			// TODO: shift this to read from properties file
			errorMessage = "Your passwords do not match. Please use the same passwords in both fields";
		}
		if (passwordsMatched) {	// Might need to refactor this to a genric verifed flag
			// Get list of countries
			ListCountry countryList = patronApi.retrieveCountryList(null);

			// Create PatronProfile from PatronForm
			PatronProfile patronProfile = new PatronProfile();
			try {
				patronProfile = patronFormMapper.toPatronProfile(patronForm, countryList, "MANAGE");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				logger.info("update patron Request: " + patronProfile.toString());
				PatronJsonResponse updatePatronResponse = patronApi.updatePatronInfoForAccountNo(patronProfile, patronLogin.getAccountNo());
				logger.info("update patron Response: " + DebugUtil.writeWithPrettyPrinter(updatePatronResponse));
				ra.addFlashAttribute("success", "User profile updated successfully.");	// redirect attributes
			} catch (HttpStatusCodeException e) {
				try {
					logger.error("Update Patron failed", e);
					JsonResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
					errorMessage = errorResponse.getStatusMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		/**
		 * To be removed / refactored after API response is changed with error code
		 */
		if (errorMessage != null && errorMessage.equals("Can't update patron password. Maybe current password is not correct.")) {
			errorMessage = "Please specify the correct Current Password.";
		}

		ra.addFlashAttribute("error", errorMessage);	// redirect attributes

		return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/patron/management");
	}

	@RequestMapping(value = "/patron/subscriptions", method = RequestMethod.GET)
	public String showPatronSubscriptions(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("patronSolicitation") PatronSolicitation patronSolicitation,
			@ModelAttribute("success") String successMessage,
			Model model,
			HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}		
		
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		String errorMessage = "";
		if (patronId != null) {
			try {
				patronSolicitation = patronApi.retrievePatronSubscriptions();
			} catch (HttpStatusCodeException e) {
				JsonResponse errorResponse;
				try {
					errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
					errorMessage = errorResponse.getStatusMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}

		Map<String, Collection<Solicitation>> patronSolicitationByGroupType = patronSolicitation.getSolicitationListGroupByType();
		
		model.addAttribute("patronSolicitation", patronSolicitationByGroupType);
		model.addAttribute("hasMembership", patronLogin.isHasMembership());
		model.addAttribute("success", successMessage);
		model.addAttribute("error", errorMessage);

		return templateCode + "/view/sidebar/subscriptions";
	}
	
	@RequestMapping(value = "/patron/subscriptions", method = RequestMethod.POST)
	public String updatePatronSubscriptions(HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("patronSubscriptionsForm") PatronSubscriptionsForm patronSubscriptionsForm, Model model,
			RedirectAttributes ra,
			HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
//		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		
		PatronSolicitation patronSolicitation = null;

		PatronLogin patronLogin = userSession.getPatronLogin();
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}		
		
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		// PatronSolicitation patronSolicitation = new PatronSolicitation();
		String errorMessage = "";
		if (patronId != null) {
			try {
				patronSolicitation = patronApi.retrievePatronSubscriptions();
			} catch (HttpStatusCodeException e) {
				JsonResponse errorResponse;
				try {
					errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
					errorMessage = errorResponse.getStatusMessage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		/**
		 * Get the PatronSolicitationUpdateRequest
		 */
		PatronSolicitationUpdateRequest patronSolicitationUpdateRequest = patronFormMapper.createPatronSolicitationUpdate(patronSubscriptionsForm, patronSolicitation);
		logger.info("patronSubscriptions patronSolicitationUpdateRequest: " + DebugUtil.writeWithPrettyPrinter(patronSolicitationUpdateRequest));
		// API call to update solicitation
		JsonResponse updateSubscriptionResponse = patronApi.updatePatronSubscriptions(patronSolicitationUpdateRequest);
		logger.info("patronSubscriptions patronSolicitationUpdateResponse: " + DebugUtil.writeWithPrettyPrinter(updateSubscriptionResponse));
		ra.addFlashAttribute("success", "User profile updated successfully.");	// redirect attributes
		
		return "redirect:/" + TenantContextHolder.getTenantCode() + "/patron/subscriptions";
	}

	@RequestMapping(value = "/patron/transactions", method = RequestMethod.GET)
	public String showPatronTransactions(@RequestParam(value = "pageNo", required = false) Integer pageNo,
			// @RequestParam(value="pageSize", required=false) Integer pageSize,
			HttpServletResponse response, HttpServletRequest request,
			Model model, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		if (pageNo == null) {
			pageNo = 1;
		}
		Integer pageSize = 10; // change this to property file

		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");

		PatronLogin patronLogin = userSession.getPatronLogin();
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (patronLogin == null) {
			throw new InvalidPatronLoginException(request.getRequestURL());
		}
		
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();

		PatronTransaction patronTransaction = new PatronTransaction();
		if (patronId != null) {
			patronTransaction = patronApi.retrievePatronTransactions(pageNo, pageSize);
		}
		
		logger.info("Retrieve patronTransaction API response: " + DebugUtil.writeWithPrettyPrinter(patronTransaction));

		model.addAttribute("patronTransaction", patronTransaction);
		model.addAttribute("hasMembership", patronLogin.isHasMembership());

		return templateCode + "/view/sidebar/booking-history";
	}

}

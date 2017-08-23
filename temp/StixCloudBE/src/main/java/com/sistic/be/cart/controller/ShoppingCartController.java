
/**
 *
 */
package com.sistic.be.cart.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.money.MonetaryAmount;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sistic.be.analytics.json.AnalyticsJson;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.app.util.ObjectMapperUtil;
import com.sistic.be.app.util.PaymentUtil;
import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.cart.api.model.EVoucherValidateResponse;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.PatronSolicitationUpdateRequest;
import com.sistic.be.cart.api.model.PaymentQueueResponse;
import com.sistic.be.cart.api.model.PrecommitOrderRequest;
import com.sistic.be.cart.api.model.PrecommitOrderResponse;
import com.sistic.be.cart.api.model.StatusResponse;
import com.sistic.be.cart.api.model.postcommit.PostCommitOrderJson;
import com.sistic.be.cart.api.model.postcommit.PostCommitRequest;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.cart.api.service.ApiPaymentService;
import com.sistic.be.cart.api.service.ApiProductService;
import com.sistic.be.cart.api.service.ShoppingCartService;
import com.sistic.be.cart.controller.validator.ShoppingCartModelValidator;
import com.sistic.be.cart.form.CartFormMapper;
import com.sistic.be.cart.form.ConfirmOrderForm;
import com.sistic.be.cart.helper.ShoppingCartModelHelper;
import com.sistic.be.cart.model.AddonOptionsLineItem;
import com.sistic.be.cart.model.CommonDeliveryMethod;
import com.sistic.be.cart.model.CommonPaymentMethod;
import com.sistic.be.cart.model.DeliveryMethod;
import com.sistic.be.cart.model.FeeLineItem;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.configuration.profile.runtime.RunProfile;
import com.sistic.be.countries.ListCountry;
import com.sistic.be.exception.ErrorJsonResponseException;
import com.sistic.be.exception.InvalidPatronLoginException;
import com.sistic.be.exception.InvalidSessionException;
import com.sistic.be.exception.PreCommitException;
import com.sistic.be.exception.ShoppingCartModelException;
import com.sistic.be.patron.api.model.PatronProfile;
import com.sistic.be.patron.api.model.PatronSolicitation;
import com.sistic.be.patron.api.service.ApiPatronService;
import com.sistic.be.patron.form.PatronFormMapper;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.service.LoginService;
import com.sistic.be.patron.session.OnlineUserSession;
import com.sistic.be.payment.constants.PaymentGatewayConstants;
import com.sistic.be.payment.model.AbstractPayment3DSRequest;
import com.sistic.be.payment.model.PaymentRequestFactory;
import com.sistic.be.sales.order.CreditCardInfo;
import com.sistic.be.sales.order.TransactionOrder;
import com.sistic.be.sales.order.TransactionOrderPaymentDetails;
import com.sistic.be.sales.order.TransactionPayment;

/**
 * @author jianhong
 */

@Controller
@RequestMapping(value = "/{tenant}")
public class ShoppingCartController {
	
	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private Environment env;
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private ApiProductService productService;
	
	@Autowired
	private ApiCartService cartApi;
	@Autowired
	private ApiPatronService patronService;
	@Autowired
	private LoginService loginService;
	@Autowired
	private ApiPaymentService bookingService;
	@Autowired
	private CartFormMapper cartFormMapper;
	@Autowired
	PatronFormMapper patronFormMapper;
	@Autowired
	private RunProfile paymentProfile;
	@Autowired
	private ShoppingCartModelHelper shoppingCartModelHelper;
	

	@Value("${paymentgateway.gateway.sinopay}")
	private String sinopayPaymentList;

	@Value("${paymentgateway.gateway.migs}")
	private String migsPaymentList;


	@Value("${paymentgateway.gateway.telemoneyAmex}")
	private String telemoneyPaymentListAmex;
	  
	@Value("${paymentgateway.gateway.telemoneyDiners}")
	private String telemoneyPaymentListDiners;
	
	@Autowired
	ShoppingCartService shoppingCartService;

	@RequestMapping(value = "/confirm/shoppingcart", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView shoppingcart(Model model, HttpServletResponse response, HttpSession session, Locale locale,
			@ModelAttribute("confirmorderform") ConfirmOrderForm confirmOrderForm) throws JsonParseException, JsonMappingException, IOException, ShoppingCartModelException {

		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		// Get parameters stored in session
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		String cartGuid = userSession.getCartGuid();

		if (cartGuid == null || cartGuid.isEmpty()) {
			model.addAttribute("error", "Your shopping cart is empty");
			return new ModelAndView(templateCode + "/view/error");
		}

		ShoppingCartModel shoppingCartModel = new ShoppingCartModel();

		/**
		 * Retrieve the ShoppingCartModel using the cartGuid from API
		 */
		if (!cartGuid.isEmpty()) {
			try {
				shoppingCartModel = cartApi.getCart(cartGuid);
			} catch (RestClientException e) {
				model.addAttribute("error", "Your shopping cart is empty");
				return new ModelAndView(templateCode + "/view/error");
			}
			// need check shoppingCartModel is properly populated REFACTOR
			if (shoppingCartModel.getLineItemList() == null) {
				model.addAttribute("error", "Your shopping cart is empty");
				return new ModelAndView(templateCode + "/view/error");
			}
		}
		
		/**
		 * Call Patron API to retrieve subscriptions for productIds in shoppingcart
		 */
		List<Long> productIds = shoppingCartModelHelper.getProductIds(shoppingCartModel);
        
        // get the list of addon
        AddonOptionsLineItem[] addOnLineItems = productService.getAddOn(productIds);
        // shoppingCartModel.setAddOnLineItems(shoppingCartModelHelper.getDisplayAddons(shoppingCartModel, addOnLineItems));
        shoppingCartModelHelper.displayAddons(shoppingCartModel, addOnLineItems, userSession.getAddonExclusion());
        
	  /* Do Validation on ShoppingCartModel to be valid for display before outputting to thymeleaf
       */
	    try {
	        ShoppingCartModelValidator.validate(shoppingCartModel);
	    } catch (ShoppingCartModelException e) {
	        logger.error("There is a problem with the shoppingcart object: " + e.getMessage());
	        model.addAttribute("error", e.getMessage());
	    }

		/**
		 * Add the Ticket protector amount into the ShoppingCartModel
		 */
		MonetaryAmount ticketProtectorAmount = cartApi.getTicketProtectorAmount(cartGuid);
		shoppingCartModel.setTicketProtectorAmount(ticketProtectorAmount);
		
		PatronSolicitation availableSubscriptions = new PatronSolicitation();
		try {
			availableSubscriptions = patronService.retrievePatronSubscriptionsForProducts(productIds);
		} catch (HttpStatusCodeException e) {
			logger.error("Could not retrieve patron subscriptions", e);
		}
		ListCountry countryList = patronService.retrieveCountryList(null);
		
		logger.info("Print shopping cart in view shopping cart /confirm/shoppingcart" 
				+ "\nAvailable subscriptions: " + DebugUtil.writeWithPrettyPrinter(availableSubscriptions) 
				+ "\nShoppingCartModel: " + DebugUtil.writeWithPrettyPrinter(shoppingCartModel));
		
/*		MembershipRedemption membershipRedemption = shoppingCartModel.getMembershipRedemption();
		if(membershipRedemption == null){
			membershipRedemption = new MembershipRedemption();
		}
		model.addAttribute("membershipRedemption", membershipRedemption);		
		
		List<MembershipBenefit> membershipPointList = new ArrayList<MembershipBenefit>();
		List<MembershipBenefit> membershipEvoucherList = new ArrayList<MembershipBenefit>(); 
		if(shoppingCartModel.getMembershipBenefit()!=null && !shoppingCartModel.getMembershipBenefit().isEmpty()){
			for(MembershipBenefit membershipBenefit : shoppingCartModel.getMembershipBenefit()){
				if(membershipBenefit.getType().equals("POINTS")){
					membershipPointList.add(membershipBenefit);
				}else if(membershipBenefit.getType().equals("EVOUCHER")){
					membershipEvoucherList.add(membershipBenefit);
				}			
			}
		}
		model.addAttribute("membershipPointList", membershipPointList);
		model.addAttribute("membershipEvoucherList", membershipEvoucherList);	*/	

		model.addAttribute("patronSolicitation", availableSubscriptions);
		model.addAttribute("shoppingcart", shoppingCartModel);
		model.addAttribute("countries", countryList);

		/**
		 * Patron Profile information to be inserted into confirmOrderForm to double bind with th:field
		 */
		PatronLogin patronLogin = userSession.getPatronLogin();
		Long patronId = (patronLogin == null) ? null : patronLogin.getPatronId();
		PatronProfile patronProfile = null;
		if (patronId != null) {
			patronProfile = patronService.retrievePatronInfoForAccountNo(patronLogin.getAccountNo());

			if (patronProfile != null) {
				confirmOrderForm = cartFormMapper.toConfirmOrderFormFromPatronProfile(patronProfile);
			}
		}
		
		List<AnalyticsJson> analytics = shoppingCartModelHelper.createCartAnalytics(shoppingCartModelHelper.getProductLineItems(shoppingCartModel));
		model.addAttribute("analytics", analytics);
		
		model.addAttribute("confirmorderform", confirmOrderForm);

		return new ModelAndView(templateCode + "/view/shoppingcart");

	}

	@RequestMapping("/action/confirmorder")
	@ResponseBody
	public JsonResponse confirmOrderAction(
			HttpServletResponse response, HttpServletRequest request,
			@ModelAttribute("confirmorderform") ConfirmOrderForm confirmOrderForm,
			Model model, HttpSession session) throws JsonParseException, JsonMappingException, IOException, ShoppingCartModelException {
		
		logger.info(confirmOrderForm);

		// Get parameters stored in session
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		String cartGuid = userSession.getCartGuid();

		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (userSession.getPatronLogin() == null) {
			throw new InvalidPatronLoginException(WebUrlUtil.getURLWithContextPathWithTenantCode() + "/confirm/shoppingcart");
		}
		
		/**
		 * Check that Terms & Conditions were accepted
		 */		
		if (confirmOrderForm.getTermsAccepted() == null || !confirmOrderForm.getTermsAccepted().equals("true")) {
			JsonResponse errorJson = new JsonResponse(400, null, "cart.terms.acceptance.false", "User did not accept the terms and conditions.");
			throw new ErrorJsonResponseException(errorJson);
		}
		
		/**
		 * Call Cart API to retrieve Shopping Cart again
		 */
		ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
		try {
			shoppingCartModel = cartApi.getCart(cartGuid);
		} catch (RestClientException e) {
			throw new ShoppingCartModelException("Could not get cart in confirmOrderAction", e);
		}
		
		/**
  		 * Populate patron solication list to send to api
  		 * In shopping cart page, patron subscribes by choosing a organization type category instead of individual organizationIds
  		 */
  		List<String> subscribedOrganizationTypes = confirmOrderForm.getSolicitation();
		List<Long> productIds = shoppingCartModelHelper.getProductIds(shoppingCartModel);
		PatronSolicitation availableSubscriptions = new PatronSolicitation();
		try {
			availableSubscriptions = patronService.retrievePatronSubscriptionsForProducts(productIds);
		} catch (HttpStatusCodeException e) {
			logger.error("Could not retrieve patron subscriptions", e);
			throw e;
		}
		PatronSolicitationUpdateRequest patronSolicitationUpdateRequest = patronFormMapper.createPatronSolicitationByOrganizationTypes(subscribedOrganizationTypes, availableSubscriptions);
		
		CommonPaymentMethod commonPaymentMethod = shoppingCartModel.getCommonPaymentMethod();
		PrecommitOrderRequest precommitOrderRequest = new PrecommitOrderRequest();
		precommitOrderRequest = cartFormMapper.toPrecommitOrderRequest(confirmOrderForm, commonPaymentMethod, patronSolicitationUpdateRequest);

//		if (confirmOrderForm.getMembershipProfileFields() != null) {
//			String[] memberProfiles = confirmOrderForm.getMembershipProfileFields().split("%%");
//			
//			for(String mem: memberProfiles) {
//				String[] keyval = mem.split("\\|\\|");						
//				MembershipProfile profile = new MembershipProfile();
//				profile.setKey(keyval[0]);
//				profile.setValue(keyval[1]);
//				precommitOrderRequest.addProfiles(profile);
//			}
//		}
		
		logger.info("print precommitorder request: " + precommitOrderRequest);
		
		/**
		 * Validate E-Vouchers. Done here to take info from preCommitOrderRequest which use actual paymentMethod
		 */
		List<String> evoucherIds = precommitOrderRequest.getEvoucherIdList();
		String paymentMethodCode = precommitOrderRequest.getPaymentMethodCode();
		String creditCardNo = precommitOrderRequest.getCreditCardNo();
		
  		if (evoucherIds != null) {
  		    evoucherIds.removeIf(p->StringUtils.isBlank(p));
  		    if (!evoucherIds.isEmpty()) {
        		EVoucherValidateResponse evoucherResponse = cartApi.validateEvoucherWithPayment(cartGuid, evoucherIds, paymentMethodCode, creditCardNo);
        		boolean evoucherPassed = evoucherResponse.allRulesPassed();
        		if (!evoucherPassed) {
        			JsonResponse errorJson = new JsonResponse(400, null, "validate.evoucher.fail.combination", "Validate EVoucher failed");
        			throw new ErrorJsonResponseException(errorJson);
        		}
  		    }
		}
		
  		/**
  		 * Call Cart API to get precommit order response
  		 */
  		PrecommitOrderResponse preCommitOrderResp = null;
		try {
			preCommitOrderResp = cartApi.precommitOrder(cartGuid, precommitOrderRequest);
		} catch (HttpStatusCodeException e) {
			logger.error("Could not precommit order", e);
			logger.error("PrecommitOrderRequest that failed", precommitOrderRequest);
			JsonResponse errorResponse = ObjectMapperUtil.getJsonResponse(e.getResponseBodyAsString());
			throw new ErrorJsonResponseException(errorResponse);
		}
		logger.info("/action/confirmorder Precommit Order Response: " + DebugUtil.writeWithPrettyPrinter(preCommitOrderResp));

		TransactionPayment transactionPayment = userSession.getTransactionPayment();
		if (transactionPayment == null) {
			transactionPayment = new TransactionPayment();
		}
		transactionPayment.setMessageId(preCommitOrderResp.getMessageId());
		transactionPayment.setTransactionRefNumber(preCommitOrderResp.getTransactionRefNumber());
		
		/*
		 *  if is not fully redeemed, there will be credit card info entered
		 */
		if (Boolean.TRUE.equals(preCommitOrderResp.getIsFullyRedeemed())) {
			transactionPayment.setIsFullyRedeemed(true);
		} else {
			CreditCardInfo creditCardInfo = new CreditCardInfo();
			creditCardInfo.setCreditCardCSC(precommitOrderRequest.getCreditCardCSC());
			creditCardInfo.setCreditCardExpiryMonth(precommitOrderRequest.getCreditCardMonth());
			creditCardInfo.setCreditCardExpiryYear(precommitOrderRequest.getCreditCardYear());
			creditCardInfo.setCreditCardNo(precommitOrderRequest.getCreditCardNo());
			creditCardInfo.setCreditCardType(confirmOrderForm.getCardType());	// This one is from form input
			creditCardInfo.setPaymentMethodCode(precommitOrderRequest.getPaymentMethodCode());		// TODO: should change to be return from API
			transactionPayment.setCreditCardInfo(creditCardInfo);
		}

		String deliveryMethodCode = precommitOrderRequest.getDeliveryMethodCode();
		// find the DeliveryMethod for this deliveryMethodCode (contains fees needed for order confirmation)
		CommonDeliveryMethod commonDeliveryMethod = shoppingCartModel.getCommonDeliveryMethod();
		if (commonDeliveryMethod != null) {
			DeliveryMethod selectedDeliveryMethod = commonDeliveryMethod.findDeliveryMethod(deliveryMethodCode);
			transactionPayment.setSelectedDeliveryMethod(selectedDeliveryMethod);
		}
		
		transactionPayment.setPayableAmount(preCommitOrderResp.getPayableAmount());
		userSession.setTransactionPayment(transactionPayment);
		logger.info("/action/confirmorder Transaction Payment: " + transactionPayment);
		SisticUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		
		JsonResponse confirmOrderJson = new JsonResponse(preCommitOrderResp.getHttpStatus(), null, preCommitOrderResp.getStatusMessage());
		confirmOrderJson.setAdditionalProperty("analytics", shoppingCartModelHelper.createCartAnalytics(shoppingCartModelHelper.getProductLineItems(shoppingCartModel)));
		
		DebugUtil.printLogWithPrettyPrinter(Level.INFO, confirmOrderJson);
		return confirmOrderJson;
	}
	
	@RequestMapping("/action/promotion")
	@ResponseBody
	public JsonResponse promotionAction(HttpServletResponse response, HttpServletRequest request,
			@RequestBody JsonNode json, Model model, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException, ShoppingCartModelException {
		// Get parameters stored in session
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		String cartGuid = userSession.getCartGuid();
		String ccNum = json.get("ccNum").asText();
		JsonResponse jsonResponse = null;
		try {
			jsonResponse = cartApi.getPromotionValidationResponse(cartGuid, ccNum);
		} catch (HttpStatusCodeException e) {
			JsonResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonResponse.class);
			throw new ErrorJsonResponseException(errorResponse);
		}
		if (jsonResponse!=null && jsonResponse.getPopUpMessage() != null) {
			return new JsonResponse(200, null, null, jsonResponse.getPopUpMessage(), jsonResponse.getPopUp());
		} else {
			JsonResponse errorJson = new JsonResponse(400, null, null, "Normal Flow");
			throw new ErrorJsonResponseException(errorJson);
		}
	}

	@RequestMapping(value="/confirmorder/redirect3ds", method=RequestMethod.GET)
	public ModelAndView confirmRedirect3ds(HttpServletResponse response, HttpServletRequest request,
			RedirectAttributes ra,
			HttpSession session,
			Model model) {

		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		// get credit card information from session
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		
		/**
		 * Implement check for patron login.
		 * If not login, then redirect to loginRedirect controller, gotoUrl=manageUser
		 */
		if (userSession.getPatronLogin() == null) {
			throw new InvalidPatronLoginException(WebUrlUtil.getURLWithContextPathWithTenantCode() + "/confirm/shoppingcart");
		}
		
		logger.info("redirect3ds sessionId: " + session.getId() + " , cartguiid " + userSession.getCartGuid());
		logger.info("redirect3ds patron  "+userSession.getPatronLogin());

		TransactionPayment transactionPayment = userSession.getTransactionPayment();
		if (transactionPayment != null) {
			CreditCardInfo creditCardInfo = transactionPayment.getCreditCardInfo();
			String transactionRefNumber = transactionPayment.getTransactionRefNumber();
			MonetaryAmount payableAmount = transactionPayment.getPayableAmount();
			// 3DSRequest get payment configuration
			Map<String, String> paymentConfigMap = null;
			if(creditCardInfo !=null && creditCardInfo.getPaymentMethodCode() != null) {
				try {
					paymentConfigMap = bookingService.getPaymentConfiguration(creditCardInfo.getPaymentMethodCode());
					if (paymentConfigMap == null) {
						logger.error("Failed to get payment configuration, paymentConfigMap: " + paymentConfigMap);
						ra.addFlashAttribute("error", "error.paymentgateway.missing-config");
						return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart");
					}
				} catch (HttpStatusCodeException e) {
					logger.error("Failed to get payment configuration, paymentConfigMap: " + paymentConfigMap);
				}
			} else {
				return new ModelAndView(templateCode + "/view/payment/postconfirmation");	// DUMMY PAYMENT GATEWAY
			}
			String payGatewayType=null;
			if(PaymentGatewayConstants.OFFLINE.equals(paymentConfigMap.get("paymentGatewayStatus"))){
				logger.error("Payment Gateway if Offline");
				ra.addFlashAttribute("error", "error.paymentgateway.offline");
				return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart");
			}
			if(paymentConfigMap!=null && paymentConfigMap.get("paymentGatewayStatus")!=null && PaymentGatewayConstants.TEST.equals(paymentConfigMap.get("paymentGatewayStatus"))){
				logger.info("Redirect3ds payment gateway is TEST mode, redirect to postconfirmation");
				return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirmorder/postconfirmation");
			}

			if (migsPaymentList.contains(creditCardInfo.getPaymentMethodCode())){
				payGatewayType=PaymentGatewayConstants.TYPE_MIGS;
			}
			else if (sinopayPaymentList.contains(creditCardInfo.getPaymentMethodCode())){
				payGatewayType=PaymentGatewayConstants.TYPE_SINO;
			}
			else if(telemoneyPaymentListAmex.contains(creditCardInfo.getPaymentMethodCode())) {
				payGatewayType=PaymentGatewayConstants.TYPE_TELEMONEY;
			}
			else if(telemoneyPaymentListDiners.contains(creditCardInfo.getPaymentMethodCode())) {
				return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirmorder/postconfirmation");
			}
			else{
				logger.error("Payment Gateway Configuration Missing");
				ra.addFlashAttribute("error", "error.paymentgateway.missing-config");
				return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart");
			}
			// set 3DSRequest
			String runProfile = paymentProfile.getProfile();
			AbstractPayment3DSRequest abstractPayment3DSRequest=PaymentRequestFactory.paymentRequest(payGatewayType,paymentConfigMap, creditCardInfo, payableAmount, transactionRefNumber, runProfile);
			// model add 3DSRequest
			model.addAttribute("paymentRequestURL", abstractPayment3DSRequest.getClientServerURL());
			model.addAttribute("vpcFieldsMap", abstractPayment3DSRequest.toVpcFieldsMap());
			if (telemoneyPaymentListAmex.contains(creditCardInfo.getPaymentMethodCode())){
				Map<String,String> resMap=null;
				try{
					logger.info("Call Cart API Hit End Point for sessionId: " + session.getId());
					resMap=	cartApi.hitEndPoint(abstractPayment3DSRequest.toVpcFieldsMap(), abstractPayment3DSRequest.getClientServerURL());
					if(resMap==null ||  resMap.containsKey("TM_Error")){
			    		throw new PreCommitException(resMap.get("TM_ErrorMsg"));
			    	}
				}
				catch(PreCommitException e){
					logger.error("Cart API Hit End Point Failed for sessionId: " + session.getId());
					logger.error(e);
					ra.addFlashAttribute("error", "error.paymentgateway.amex.fail");
					return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart");
					
				}
				catch(Exception e){
					logger.error("Cart API Hit End Point Failed for sessionId: " + session.getId(), e);
					ra.addFlashAttribute("error", "error.paymentgateway.internalserver");
					return new ModelAndView("redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart");
					
				}
				logger.info("resMap "+resMap);
				Map<String,String> telemoneyRequestMap= null;
				telemoneyRequestMap=shoppingCartService.populateTeleMoneyRequestMap(abstractPayment3DSRequest, resMap);
				logger.info("telemoneyRequestMap Request "+telemoneyRequestMap);
				model.addAttribute("paymentRequestURL", resMap.get("Acsurl"));
				model.addAttribute("vpcFieldsMap", telemoneyRequestMap);
			}
			return new ModelAndView(templateCode + "/view/payment/3ds-redirect-page");
		}
		else {
			model.addAttribute("error", "This error case will change to return error page saying due to no transaction");
			return new ModelAndView(templateCode + "/view/error");
		}

	}

	@RequestMapping(value="/confirmorder/return3ds")

	public ModelAndView returnUrl3ds(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws JsonParseException, JsonMappingException, IOException{
		
		logger.info("Entered 3DS return url call back by payment gateway");
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		Map<String, String> threeDSecureMap = new TreeMap<String, String>();
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Map.Entry<String, String[]> element : paramMap.entrySet()) {
			threeDSecureMap.put(element.getKey(), element.getValue()[0]);
		}
		JsonNode node = mapper.valueToTree(threeDSecureMap);
		logger.info("/confirmorder/return3ds request map: " + DebugUtil.writeWithPrettyPrinter(node));
		
		if(threeDSecureMap!=null && threeDSecureMap.get(PaymentGatewayConstants.PARES)!=null) {
			OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
			TransactionPayment transactionPayment = userSession.getTransactionPayment();
			Map<String,String> resMap=null;
			CreditCardInfo creditCardInfo=null;
			Map<String,String> requestMap= new HashMap<String,String>();
			if (transactionPayment != null) {
				creditCardInfo = transactionPayment.getCreditCardInfo();
			}	
			Map<String, String> paymentConfigMap = bookingService.getPaymentConfiguration(creditCardInfo.getPaymentMethodCode());
			requestMap=shoppingCartService.populateTeleMoney3DSRequestMap(threeDSecureMap, transactionPayment,paymentConfigMap);
			logger.info("requestMap Telemoney "+requestMap);
			try{
				resMap = cartApi.hitEndPoint(requestMap, paymentConfigMap.get(PaymentGatewayConstants.CLIENT_SERVER_URL_KEY));
			}
			catch(Exception e){
				logger.error(e);
			}
			logger.info("resMap  Telemoney "+resMap);
			model.addAttribute("paymentGateRespMap", resMap);
			return new ModelAndView(templateCode + "/view/payment/postconfirmation");
		}
		
		model.addAttribute("paymentGateRespMap", threeDSecureMap);

		return new ModelAndView(templateCode + "/view/payment/postconfirmation");
	}

	@RequestMapping(value="/confirmorder/postconfirmation")
	public String postConfirmation(Model model, HttpServletResponse response, HttpServletRequest request,
			HttpSession session,		
			RedirectAttributes ra,
			@ModelAttribute("postConfirmationForm") HashMap<String, String> postConfirmationForm) throws JsonParseException, JsonMappingException, IOException {
				
		logger.info("Entered PostConfirmation");
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();

		// Get user session
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		// DO NOT RE-INIT PATRON LOGIN BECAUSE ERROR REDIRECT TO SHOPPING CART!
		// Check for invalid session or login
		PatronLogin patronLogin;
		if (userSession != null || userSession.getPatronLogin() != null) {
			patronLogin = userSession.getPatronLogin();
		} else {
			String errorRedirectUrl = TenantContextHolder.getTenant().getDefaultRedirect();
			return "redirect:"+errorRedirectUrl;
		}

		String cartGuid = userSession.getCartGuid();
		
		logger.info("postconfirmation patron  "+ patronLogin);

		// Get request parameters
		Map<String, String> threeDSecureMap = new TreeMap<String, String>();
		Map<String, String[]> paramMap = request.getParameterMap();
		for (Map.Entry<String, String[]> element : paramMap.entrySet()) {
			threeDSecureMap.put(element.getKey(), element.getValue()[0]);
		}
		
		String  txnResponseCode=threeDSecureMap.get("vpc_TxnResponseCode");
		String verStatus3DS=threeDSecureMap.get("vpc_VerStatus");
		
		if (txnResponseCode!=null && (txnResponseCode.equalsIgnoreCase("F") || (!txnResponseCode.equals("0") && !txnResponseCode.equals("N")))) {
			logger.info("[PostConfirmationAction.3DS]>>>>>>>>txnResponseCode is F or not 0 and N so failed!!");
        	String errCode = PaymentUtil.getResponseErrCode(txnResponseCode);
        	if (!errCode.equals("")) {
        		ra.addFlashAttribute("error", "error.payment.err.failed.txn"+errCode);
        		return "redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart";
        	}
           	errCode = PaymentUtil.get3DSErrCode(verStatus3DS);
        	if (!errCode.equals("")) {
        		ra.addFlashAttribute("error", "error.payment.err.failed.3ds"+errCode);
        		return "redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart";
        	}
        } else if (verStatus3DS!=null && (!verStatus3DS.equals("Y") && !verStatus3DS.equals("E") && !verStatus3DS.equals("M"))) {
        	logger.info("[PostConfirmationAction.3DS]>>>>>>>>verStatus3DS is not Y or E or M so failed!!");
           	String errCode = PaymentUtil.get3DSErrCode(verStatus3DS);
        	if (!errCode.equals("")) {
        		ra.addFlashAttribute("error", "error.payment.err.failed.3ds"+errCode);
        		return "redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart";
        	}
        } 

		// Call API PostCommit - This deletes the cartGuid
		PostCommitRequest postCommitRequest=null;
		postCommitRequest=shoppingCartService.buildPostCommitRequest(threeDSecureMap);
		
		logger.info("/confirmorder/postconfirmation PostCommitRequest: " + postCommitRequest);
		
		/**
		 * Call the Cart API to handle post confirm order
		 */
		PostCommitOrderJson postCommitOrderJson = null;
		try {
			postCommitOrderJson = cartApi.postCommitOrder(cartGuid, postCommitRequest);
		} catch (HttpStatusCodeException e) {
			logger.error("Cart API Postcommit FAILED for sessionID: " + session.getId());
			ra.addFlashAttribute("error", "error.shopping.cart.postcommit.fail");
			return "redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart";
//			return "redirect:"+WebUrlUtil.getURLWithContextPathWithTenantCode()+"/confirm/shoppingcart";
		}
		
		logger.info("/confirmorder/postconfirmation PostCommitOrderJson postCommit response: " + postCommitOrderJson);

		if (postCommitOrderJson == null) {
			model.addAttribute("error", "There was no response from server for postcommit");
			return templateCode + "/view/error";
//			throw new IllegalStateException();
		}
		
		// Instantiate
		TransactionOrder transactionOrder = new TransactionOrder();
		TransactionPayment transactionPayment = userSession.getTransactionPayment();
		
		try {
			transactionOrder.setShoppingCartModel(postCommitOrderJson.getShoppingCart());
			List<TransactionOrderPaymentDetails> transactionOrderPaymentDetailsList = postCommitOrderJson.getTransactionPaymentDetailsList().stream()
					.map(s -> new TransactionOrderPaymentDetails(s.getPaymentMethodCode(), s.getSubAmount()))
					.collect(Collectors.toList());
			transactionOrder.setTransactionOrderPaymentDetailsList(transactionOrderPaymentDetailsList);
		} catch (NullPointerException e) {
			logger.error("Precommit Order failed", e);
			model.addAttribute("error", "An error occurred during postcommit");
			return templateCode + "/view/error";
		}
		
		// Add additional cart items to order confirmation as needed - MasterCard pickup waived etc
		if (transactionPayment != null) {
			List<FeeLineItem> feeLineItems = new ArrayList<FeeLineItem>();
			FeeLineItem deliveryLineItem = new FeeLineItem(FeeLineItem.DELIVERYFEE);
			
			DeliveryMethod deliveryMethod = transactionPayment.getSelectedDeliveryMethod();
			if (deliveryMethod != null) {
				deliveryLineItem.setCode(deliveryMethod.getCode());
				deliveryLineItem.setQuantity(1);
				deliveryLineItem.setUnitPrice(deliveryMethod.getCharge());
				MonetaryAmount subTotal = deliveryLineItem.getUnitPrice().multiply(deliveryLineItem.getQuantity());
				deliveryLineItem.setSubTotal(subTotal);
				// TODO: Shift description to be read from messages properties file
				deliveryLineItem.setDescription("Thank you for choosing MasterCard Pickup. The SGD 0.50 delivery fee for Collection from Singapore Authorised Agents has been waived.");
				
				feeLineItems.add(deliveryLineItem);
				logger.info("delivery method info: " + DebugUtil.writeWithPrettyPrinter(deliveryMethod));
				transactionOrder.setDeliveryMethod(deliveryMethod);
			}
			transactionOrder.getShoppingCartModel().setFeeLineItems(feeLineItems);
		}

		// Get transaction info from Post Commit Response
		Boolean txnOrderStatus = Boolean.valueOf(postCommitOrderJson.getOrderStatus());
		if (txnOrderStatus) {
			//TODO: change this
			transactionOrder.setPurchaseDate(postCommitOrderJson.getTransactionTime());
			transactionOrder.setTransactionRefNumber(postCommitOrderJson.getTransactionRefNumber());
			transactionOrder.setTotalPayment(postCommitOrderJson.getTotalPayment());
			// Payment Method code is set from user payment selection. Not payment method returned from API, due to Master Card vs MASTER
			transactionOrder.setEmailAddress(userSession.getPatronLogin().getEmail());
			transactionOrder.setFirstName(userSession.getPatronLogin().getFirstName());
			transactionOrder.setLastName(userSession.getPatronLogin().getLastName());
			transactionOrder.setAcctNum(String.valueOf(userSession.getPatronLogin().getAccountNo()));
		}
		
		ShoppingCartModel cartModel = postCommitOrderJson.getShoppingCart();
		cartModel.getAddOnLineItems();
		
		AddonOptionsLineItem[] addOnLineItems = productService.getAddOn(shoppingCartModelHelper.getProductIds(cartModel));
        // shoppingCartModel.setAddOnLineItems(shoppingCartModelHelper.getDisplayAddons(shoppingCartModel, addOnLineItems));
        shoppingCartModelHelper.displayAddons(cartModel, addOnLineItems, userSession.getAddonExclusion());
        // remove display addons
        cartModel.setAddOnLineItems(new ArrayList<AddonOptionsLineItem>());
		
		// Set in analytics information
		List<AnalyticsJson> analytics = shoppingCartModelHelper.createCartAnalytics(shoppingCartModelHelper.getProductLineItems(postCommitOrderJson.getShoppingCart()));
		model.addAttribute("analytics", analytics);
	
		// Logout user session
		loginService.logout(session);
		
		logger.info("Transaction Order: " + transactionOrder);

		// Add model attributes
		model.addAttribute("transactionOrder", transactionOrder);

		// return ModelView
		return templateCode + "/view/orderconfirmation";

	}
	
	@RequestMapping(value = "/payment/queue", method = RequestMethod.GET)
	@ResponseBody
	public PaymentQueueResponse checkPaymentQueueStatus(HttpServletResponse response, HttpSession session) throws JsonParseException, JsonMappingException, IOException {
		
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession != null) {
			// is fully redeemed, just skip the queue
			if ( (userSession.getTransactionPayment() != null) && Boolean.TRUE.equals(userSession.getTransactionPayment().getIsFullyRedeemed())) {
				PaymentQueueResponse queueStatus = new PaymentQueueResponse();
				queueStatus.setHttpStatus(HttpStatus.OK.value());
				queueStatus.setProceed(true);
				queueStatus.setStatusMessage("Fully redeemed. No payment gateway is used.");
				logger.info("Payment queue is not used for fully redeemed");
				return queueStatus;
			} else {
				// Not fully redeem, need to get status for messageId
				try {
					String paymentQueueGuid = userSession.getTransactionPayment().getMessageId();
					if (!paymentQueueGuid.isEmpty()) {
						JsonNode apiResponse = bookingService.getPaymentQueueStatus(paymentQueueGuid);
						PaymentQueueResponse queueStatus = mapper.treeToValue(apiResponse, PaymentQueueResponse.class);
						logger.info("Payment Queue Status for message id: " + paymentQueueGuid + DebugUtil.writeWithPrettyPrinter(queueStatus));
						return queueStatus;
					} else {
						logger.info("/payment/queue messageId in session is null");
						JsonResponse errorJson = new JsonResponse(HttpStatus.BAD_REQUEST.value(), "There is no messageId to get queue status for");
						throw new ErrorJsonResponseException(errorJson);
					}
				} catch (NullPointerException e) {
					logger.info(e);
					JsonResponse errorJson = new JsonResponse(HttpStatus.BAD_REQUEST.value(), "There is no transaction being processed");
					throw new ErrorJsonResponseException(errorJson);
				}
			}
		}
		throw new InvalidSessionException();
	}

	@RequestMapping("/cart/delete")
	@ResponseBody
	public JsonNode deleteCartItem(Model model, HttpServletResponse response, HttpSession session, Locale locale,
			@RequestBody JsonNode json) {

		String cartItemId;
		String priceClassCode;
		List<AnalyticsJson> analytics = null;
		
		if (json.findValue("cartItemId") == null) {
			cartItemId = null;
		} else {
			cartItemId = json.findValue("cartItemId").asText();
		}

		if (json.findValue("priceClassCode") == null) {
			priceClassCode = null;
		} else {
			priceClassCode = json.findValue("priceClassCode").asText();
		}

		// Get parameters stored in session
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session);
		if (userSession != null) {
			String cartGuid = userSession.getCartGuid();
	       
	        if (!cartGuid.isEmpty()) {
	            ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
	            try {
	                shoppingCartModel = cartApi.getCart(cartGuid);
	                if (cartItemId != null) {	// if remove specific item specified
	                	 analytics = shoppingCartModelHelper.createCartAnalytics(shoppingCartModelHelper.getProductLineItems(shoppingCartModel, cartItemId));
	                } else { 					// if remove all items
	                	analytics = shoppingCartModelHelper.createCartAnalytics(shoppingCartModelHelper.getProductLineItems(shoppingCartModel));
	                }
	                // TODO: Start - This chunk of addon code assumes only one line item is removed. Refactor to make it more explicit!
	                Long productId = shoppingCartModelHelper.getProductIdByCartItemId(shoppingCartModel, cartItemId);
	                List<Long> productIds = new ArrayList<>();
	                productIds.add(productId);
	                List<Long> addonProductIds = new ArrayList<Long>();
	                for (AddonOptionsLineItem addon : productService.getAddOn(productIds)) {
	                  addonProductIds.add(addon.getProductID());
	                }
	                if(addonProductIds.contains(productId)){
	                  // cart item being deleted is addon -> add to exclusion list
	                  userSession.getAddonExclusion().addAll(productIds);
	                  SessionUtil.setOnlineUserSession(session, userSession);
	                }
	                // TODO: End - This chunk of addon code assumes only one line item is removed. Refactor to make it more explicit!
	            } catch (Exception e) {
	              logger.error(e.getMessage());
	            }
	            
	        }
			

	        ObjectNode apiResponse = (ObjectNode) cartApi.deleteCartItem(cartGuid, cartItemId, priceClassCode);
			apiResponse.putPOJO("analytics", analytics);
			
			logger.info("Deleted cartItemId: " + cartItemId + "delete cartitem response: " + DebugUtil.writeWithPrettyPrinter(apiResponse));
			
			try {
				if (!cartGuid.isEmpty()) {
					cartApi.checkCart(cartGuid);
				}
			} catch (HttpStatusCodeException e) {
				/**
				 * Check cart will throw exception, cannot find cartGuid if no items, as cartGuid will be deleted
				 */
				userSession.setCartGuid("");
				userSession.setCartTimer(null);
				SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
			}

			return apiResponse;
		}
		else {
			throw new InvalidSessionException();
		}
	}

	@RequestMapping(value = "/evoucher/validation", method = RequestMethod.POST)
	@ResponseBody
	public JsonNode validateEvoucher(HttpServletResponse response, HttpSession session, Locale locale,
			@RequestBody List<String> evoucherIds) {
		
		logger.info("Validate evoucher" + DebugUtil.writeWithPrettyPrinter(evoucherIds));

		// Get parameters stored in session
		OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession != null) {
			String cartGuid = userSession.getCartGuid();

			if (cartGuid == null || cartGuid.isEmpty()) {
				throw new RestClientException("There is no cart existing for evoucher validation!");
			}
			
			boolean needToValidateEvoucher = evoucherIds != null;
			if(needToValidateEvoucher) {
			  evoucherIds.removeIf(p->StringUtils.isBlank(p));
			  needToValidateEvoucher = !evoucherIds.isEmpty();
			}
			if (!needToValidateEvoucher) {
			  throw new RestClientException("There is no evoucher for evoucher validation!");
			}
			
			JsonNode apiResponse = cartApi.validateEvoucher(cartGuid, evoucherIds);

			return apiResponse;
		}
		throw new InvalidSessionException();
	}
	
  @RequestMapping(value = "/action/addon/exclude", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<StatusResponse> excludeAddon(HttpSession session,
      @RequestParam Long productId) throws IOException {
    OnlineUserSession userSession =
        SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
    if (userSession != null) {
      String cartGuid = userSession.getCartGuid();
      if (cartGuid == null || cartGuid.trim().isEmpty()) {
        logger.error("There is no cart existing for addon exclusion!");
        throw new RestClientException("There is no cart existing for addon exclusion!");
      }
      // add product id to session
      userSession.excludeAddon(productId);
      SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
    }
    return new ResponseEntity<StatusResponse>(new StatusResponse(), HttpStatus.OK);
  }
	
}

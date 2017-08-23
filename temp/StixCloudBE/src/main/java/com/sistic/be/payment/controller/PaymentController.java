package com.sistic.be.payment.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.InternalServerErrorException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.masterpass.merchant.model.Card;
import com.mastercard.sdk.core.exceptions.SDKErrorResponseException;
import com.sistic.be.app.util.PaymentUtil;
import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.app.util.WebUrlUtil;
import com.sistic.be.cart.api.model.InitMasterpassResponse;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.cart.api.service.ApiPaymentService;
import com.sistic.be.cart.model.MasterpassPaymentMethod;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.patron.session.OnlineUserSession;

@Controller
@RequestMapping(value = "/{tenant}")
public class PaymentController {
	
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private Environment env;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private ApiPaymentService bookingService;
	@Autowired
	private ApiCartService cartApi;
	
	@RequestMapping(value = "/action/initialMasterpassConnection", method = RequestMethod.POST)
	@ResponseBody
	public InitMasterpassResponse initialMasterpassConnection(HttpSession session, HttpServletRequest request)
			throws SDKErrorResponseException, JsonParseException, JsonMappingException, IOException {
		ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
		OnlineUserSession userSession =
				SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		if (userSession == null) {
			userSession = new OnlineUserSession();
		}
		String cartGuid = userSession.getCartGuid();
		if (!cartGuid.isEmpty()) {
			shoppingCartModel = cartApi.getCart(cartGuid);
			// need check shoppingCartModel is properly populated REFACTOR
			if (shoppingCartModel.getLineItemList() == null) {
				logger.info("There is no shopping cart for the sent cartGuid");
				throw new InternalServerErrorException("There is no shopping cart for the sent cartGuid");
			}
		}

		//get current server
		String domainName = WebUrlUtil.getURLWithContextPathWithTenantCode();
		logger.info("domainName: " + domainName);
		String requestToken = bookingService.initialMasterpassConnect(shoppingCartModel, domainName);
		logger.info("requestToken: " + requestToken);
		logger.info("checkoutIdentifier: " + bookingService.getCheckoutIdentifier());
		return new InitMasterpassResponse(requestToken, bookingService.getCheckoutIdentifier());
	}

	/**
	 * Endpoint called for MasterPasss
	 * TODO: review the codes according to design
	 */
	@RequestMapping(value = "/action/getPaymentData", method = RequestMethod.POST)
	@ResponseBody
	public MasterpassPaymentMethod getPaymentData(HttpServletRequest request, HttpSession session)
			throws SDKErrorResponseException {
		MasterpassPaymentMethod response = null;
		String oauthToken = request.getParameter("oauth_token");
		String oauthVerifier = request.getParameter("oauth_verifier");
		String checkoutResourceUrl = request.getParameter("checkout_resource_url");
		Card cardInfo = bookingService.getPaymentData(oauthToken, oauthVerifier, checkoutResourceUrl);
		String ccNumber = cardInfo.getAccountNumber();
//		String maskCcNumber = "XXXX-XXXX-XXXX-" + ccNumber.substring(ccNumber.length() - 4);	// No need to mask to front end

		if (cardInfo != null) {
			String cardType = PaymentUtil.getMasterpassCreditCardType(cardInfo.getBrandId());
			response = new MasterpassPaymentMethod(cardType, cardInfo.getCardHolderName(), ccNumber,
					cardInfo.getExpiryMonth(), cardInfo.getExpiryYear());
			// Get parameters stored in session
			MasterpassPaymentMethod payment = new MasterpassPaymentMethod(cardType, cardInfo.getCardHolderName(),
					ccNumber, cardInfo.getExpiryMonth(), cardInfo.getExpiryYear());
			OnlineUserSession userSession =
					SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
			userSession.getPatronSelection().setPaymentInfo(payment);
			SisticUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		}

		return response;
	}

	/**
	 * Display cvv information
	 * @return path to html file
	 *
	 */
	@RequestMapping(value="/cart/cvv")
	public String testCvv () {
		return "fragment/cart/cvv-explainer";
	}

}

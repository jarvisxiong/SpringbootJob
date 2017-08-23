package com.sistic.be.cart.api.service;

import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastercard.masterpass.merchant.CheckoutApi;
import com.mastercard.masterpass.merchant.MerchantInitializationApi;
import com.mastercard.masterpass.merchant.ShoppingCartApi;
import com.mastercard.masterpass.merchant.model.Card;
import com.mastercard.masterpass.merchant.model.Checkout;
import com.mastercard.masterpass.merchant.model.MerchantInitializationRequest;
import com.mastercard.masterpass.merchant.model.ShoppingCart;
import com.mastercard.masterpass.merchant.model.ShoppingCartItem;
import com.mastercard.masterpass.merchant.model.ShoppingCartRequest;
import com.mastercard.sdk.core.MasterCardApiConfig;
import com.mastercard.sdk.core.exceptions.SDKErrorResponseException;
import com.mastercard.sdk.core.models.AccessTokenResponse;
import com.mastercard.sdk.core.models.RequestTokenResponse;
import com.mastercard.sdk.core.services.AccessTokenApi;
import com.mastercard.sdk.core.services.RequestTokenApi;
import com.sistic.be.app.util.AuthUtilService;
import com.sistic.be.cart.model.MasterpassConfiguration;
import com.sistic.be.cart.model.ProductLineItem;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

@Service
public class ApiPaymentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String MASTERPASS_URI = "/payment/masterpass/config";
	private MasterpassConfiguration config;
	private PrivateKey privateKey;

	@Value("${service.payment.url}")
	private String serviceUrl;
	
	@Value("${masterpass.issandbox}")
	private Boolean isSandbox;

	@Autowired
	private AuthUtilService authUtilService; 
	
	@Autowired
	private ObjectMapper mapper;

	public String getCheckoutIdentifier() {
		return config.getCheckoutIdentifier();
	}
	
	/**
	 * Get payment queue status from payment gateway
	 */
    public JsonNode getPaymentQueueStatus(String messageId) throws JsonParseException, JsonMappingException, IOException {
    	try {
    		// HttpEntity<JsonNode> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/payment/queue/{messageId}", HttpMethod.GET, entity, JsonNode.class, messageId);
    	  return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/payment/queue/{messageId}", HttpMethod.GET, JsonNode.class, null, messageId);
    	} catch (HttpStatusCodeException e) {
    		JsonNode errorResponse = mapper.readValue(e.getResponseBodyAsString(), JsonNode.class);
    		return errorResponse;
    	}
    }

	/**
	 * 
	 * @return MasterpassConfiguration
	 */
	private MasterpassConfiguration getMasterpassConfig() {
		MasterpassConfiguration response = null;
		try {
		    String mpConfigApiUrl = serviceUrl + TenantContextHolder.getTenant().getApiCode() + MASTERPASS_URI;
			logger.info("Trying to load Masterpass configuration at url: " + mpConfigApiUrl);
			
			//HttpEntity<JsonNode> response1 = restTemplate.exchange(mpConfigApiUrl, HttpMethod.GET, entity, JsonNode.class);
            JsonNode apiResponse = authUtilService.exchange(mpConfigApiUrl, HttpMethod.GET, JsonNode.class, null);
            response = mapper.convertValue(apiResponse, MasterpassConfiguration.class);
		} catch (RestClientException e) {
			logger.error("Error when getting Masterpass configuration.", e);
		}

		return response;
	}

	/**
	 * 
	 * @param model ShoppingCartModel
	 * @return Masterpass request token
	 * @throws MasterpassException
	 */
	public String initialMasterpassConnect(ShoppingCartModel model, String domainName)
			throws SDKErrorResponseException {
		logger.info("Start initialMasterpassConnect()");
		if (config == null) {
			logger.info("Start calling api to get masterpass configuration");
		  config = getMasterpassConfig();
		  logger.info("End calling api to get masterpass configuration");
		}
		
		if (privateKey == null) {
			logger.info("Start getting private key of masterpass");
		  privateKey = getPrivateKey();
		  logger.info("End getting private key of masterpass");
		}
		
		String requestToken = null;
		RequestTokenResponse requestResponse = null;

		String Url = domainName + config.getCheckOutCallbackUrl();
		MasterCardApiConfig.setSandBox(isSandbox);
		MasterCardApiConfig.setConsumerKey(config.getConsumerKey());
		MasterCardApiConfig.setPrivateKey(privateKey);
		try {
			requestResponse = RequestTokenApi.create(Url);
			requestToken = requestResponse.getOauthToken();
		} catch (SDKErrorResponseException e) {
			logger.error("Error when getting Masterpass access token.");
			throw e;
		}

		// Send shopping cart data to Masterpass
		ShoppingCartRequest shoppingCartRequest =
				new ShoppingCartRequest().shoppingCart(new ShoppingCart());
		ShoppingCart shoppingCart = new ShoppingCart();
		Long subtotalShoppingCart = 0L;
		for (ProductLineItem item : model.getLineItemList()) {
			shoppingCart = shoppingCart
					.shoppingCartItem(new ShoppingCartItem().description(item.getProduct().getProductName())
							.quantity(item.getQuantity()).value(item.getSubTotal().getNumber().longValue()));
			subtotalShoppingCart += item.getSubTotal().getNumber().longValue();
		}

		shoppingCart = shoppingCart.currencyCode("SGD").subtotal(subtotalShoppingCart);
		shoppingCartRequest = shoppingCartRequest.oAuthToken(requestToken).shoppingCart(shoppingCart);

		try {
			ShoppingCartApi.create(shoppingCartRequest);
		} catch (SDKErrorResponseException e) {
			logger.error("Error when calling Masterpass Shopping Cart Service.");
		}
		// Create an instance of MerchantInitializationRequest
		MerchantInitializationRequest merchantInitializationRequest =
				new MerchantInitializationRequest().originUrl(Url)
				.oAuthToken(requestToken);
		try {
			// Call the MerchantInitializationApi with required params
			MerchantInitializationApi.create(merchantInitializationRequest);
		} catch (SDKErrorResponseException e) {
			logger.error("Error when calling Masterpass Merchant Initialization Service.");
			throw e;
		}
		return requestToken;
	}

	/**
	 * 
	 * @param oauthToken
	 * @param oauthVerifier
	 * @param checkoutResourceUrl
	 * @return credit card details
	 */
	public Card getPaymentData(String oauthToken, String oauthVerifier, String checkoutResourceUrl)
			throws SDKErrorResponseException {
		logger.info("Start getPaymentData()");
		AccessTokenResponse response = null;
		try {
			response = AccessTokenApi.create(oauthToken, oauthVerifier);
		} catch (SDKErrorResponseException e) {
			logger.error("Error when getting Masterpass access token");
			throw e;
		}

		String accessToken = response.getOauthToken();
		int startIndex = checkoutResourceUrl.lastIndexOf('/') + 1;
		int endIndex = checkoutResourceUrl.indexOf('?') != -1 ? checkoutResourceUrl.indexOf('?')
				: checkoutResourceUrl.length();
		String checkoutId = checkoutResourceUrl.substring(startIndex, endIndex);

		try {
			Checkout checkout = CheckoutApi.show(checkoutId, accessToken);
			return checkout.getCard();
		} catch (SDKErrorResponseException e) {
			logger.error("Error when checking out Masterpass payment details.");
			throw e;
		}
	}

	/**
	 * 
	 * @return masterpass private key
	 */
	private PrivateKey getPrivateKey() {
		logger.info("Start getPrivateKey()");
		PrivateKey privateKey = null;
		if (config == null) {
			logger.error("Masterpass configuration is null");
			return privateKey;
		}

		String privKeyFile = config.getP12CertFileName();
		String kspw = config.getKeyPassword();

		try {
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(this.getClass().getClassLoader().getResourceAsStream(privKeyFile),
					kspw.toCharArray());
			Enumeration aliases = ks.aliases();
			String keyAlias = "";

			while (aliases.hasMoreElements()) {
				keyAlias = (String) aliases.nextElement();
			}

			privateKey = (PrivateKey) ks.getKey(keyAlias, kspw.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return privateKey;
	}
	
	public Map<String, String> getPaymentConfiguration(String paymentMethodCode) {
        //HttpEntity<JsonNode> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/payment/config/{paymentMethodCode}", HttpMethod.GET, entity, JsonNode.class, paymentMethodCode);
        JsonNode apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/payment/config/{paymentMethodCode}", HttpMethod.GET, JsonNode.class, null, paymentMethodCode);
		JsonNode paymentGatewayConfig = apiResponse.get("paymentGatewayConfig");
		Map<String, String> result = mapper.convertValue(paymentGatewayConfig, Map.class);
		String paymentStatus=mapper.convertValue(apiResponse.get("paymentGatewayStatus"), String.class);
		if(result==null && paymentStatus!=null){
			result=new HashMap<String,String>();
			result.put("paymentGatewayStatus",apiResponse.get("paymentGatewayStatus").toString());
		}
		else if(paymentStatus!=null){
			result.put("paymentGatewayStatus",paymentStatus);
		}
		return result;
	}
}

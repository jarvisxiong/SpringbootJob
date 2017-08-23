/**
 * 
 */
package com.sistic.be.cart.api.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sistic.be.app.util.AuthUtilService;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.cart.api.model.AddToCartRequest;
import com.sistic.be.cart.api.model.CartGuidResponse;
import com.sistic.be.cart.api.model.CheckCartLineItem;
import com.sistic.be.cart.api.model.CheckCartResponse;
import com.sistic.be.cart.api.model.EVoucherRequest;
import com.sistic.be.cart.api.model.EVoucherValidateResponse;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.PrecommitOrderRequest;
import com.sistic.be.cart.api.model.PrecommitOrderResponse;
import com.sistic.be.cart.api.model.postcommit.PostCommitOrderJson;
import com.sistic.be.cart.api.model.postcommit.PostCommitRequest;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.cart.seatmap.PatronSelection;
import com.sistic.be.cart.seatmap.constant.BookingModeConsts;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

/**
 * @author jianhong
 *
 */
@Service
public class ApiCartService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private AuthUtilService authUtilService; 
	
	@Autowired
	protected RestTemplate restTemplate;
	
	protected String serviceUrl;

	@Autowired
    public ApiCartService(@Value("${service.cart.url}") String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
        
        logger.info(serviceUrl);
    }
    
    public CartGuidResponse confirmseats() {
        return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/confirmseats", HttpMethod.GET, CartGuidResponse.class, null);
    }
    
    public List<CheckCartLineItem> checkCart(String cartGuid) {  	
//		HttpEntity<CheckCartResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/checkcart", HttpMethod.GET, entity, CheckCartResponse.class, cartGuid);
		CheckCartResponse apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/checkcart", HttpMethod.GET, CheckCartResponse.class, null, cartGuid);
    	return apiResponse.getLineItemList();
    }
    
    /**
     * To consider splitting into seperate functions for RS and GA
     * @param cartGuid
     * @param patronSelection
     * @return
     */
    public JsonNode addToCart(String cartGuid, PatronSelection patronSelection) throws RestClientException {
    	// Prepare
    	String icc = patronSelection.getInternetContentCode();
    	Integer maxQuantity = patronSelection.getMaxQuantity();
    	String mode = patronSelection.getMode();
    	Long productId = patronSelection.getProductId();
    	String priceClassMap = patronSelection.getPriceClassMap();
    	Long priceCatId = patronSelection.getPriceCatId();
    	List<Long> inventoryList = patronSelection.getSeatSelection().getInventoryIds();
    	Long seatSectionId = patronSelection.getSeatSectionId();
    	String promoCode = patronSelection.getPromoCode();
    	AddToCartRequest addToCartRequest = null;
    	// if RS
    	if (mode.equals(BookingModeConsts.BESTAVAILABLE) || mode.equals(BookingModeConsts.SELFPICK) || mode.equals(BookingModeConsts.HOTSHOW) ) {    		
    		addToCartRequest = new AddToCartRequest.Builder()
    				.cartGuid(cartGuid)
    				.icc(icc)
    				.productId(productId)
    				.priceClassMap(priceClassMap)
    				.priceCatId(priceCatId)
    				.inventoryList(inventoryList)
    				.mode(mode)
    				.maxQuantity(maxQuantity)
    				.build();

    		//response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart", HttpMethod.POST, entity, JsonNode.class);
    		// If error check API is using POST and not PUT. Solve this elegantly later.
    	}    	
    	// else if GA
    	else if (mode.equals(BookingModeConsts.GENERALADMISSION)) {

    		addToCartRequest = new AddToCartRequest.Builder()
    				.cartGuid(cartGuid)
    				.icc(icc)
    				.productId(productId)
    				.priceClassMap(priceClassMap)
    				.priceCatId(priceCatId)
    				.seatSectionId(seatSectionId)
    				.mode(mode)
    				.maxQuantity(maxQuantity)
    				.build();

    	}
		logger.info("ApiRequest: " + DebugUtil.writeWithPrettyPrinter(addToCartRequest));
    	
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart?promocode={promoCode}", HttpMethod.POST, JsonNode.class, addToCartRequest, promoCode);
    }

    /**
     * This calls cart api to retrieve the shopping cart for the given cartGuid
     * @param cartGuid
     * @return ShoppingCartModel
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    public ShoppingCartModel getCart(String cartGuid) throws JsonParseException, JsonMappingException, IOException, RestClientException {
		// HttpEntity<ShoppingCartModel> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.GET, entity, ShoppingCartModel.class, cartGuid);
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.GET, ShoppingCartModel.class, null, cartGuid);
    }
    
    /**
     * 
     * @param cartGuid
     * @return the Ticket Protector 
     * @throws IOException 
     */
    public MonetaryAmount getTicketProtectorAmount(String cartGuid) throws IOException {
    	// HttpEntity<MonetaryAmount> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/id/{cartGuid}/ticketprotector", HttpMethod.GET, entity, MonetaryAmount.class, cartGuid);
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/id/{cartGuid}/ticketprotector", HttpMethod.GET, MonetaryAmount.class, null, cartGuid);
    }
    
    public JsonNode deleteCartItem(String cartGuid, String cartItemId, String priceClassCode) throws RestClientException {
		ObjectNode node = null; 
		
		if (cartItemId != null && priceClassCode != null) {
		  node = JsonNodeFactory.instance.objectNode();
		  node.put("cartItemId", cartItemId);
	      node.put("priceClassCode", priceClassCode);
		}
			
		// ResponseEntity<JsonNode> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.DELETE, entity, JsonNode.class, cartGuid);
		return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.DELETE, JsonNode.class, node, cartGuid);
    }
    
    public JsonNode validateEvoucher(String cartGuid, List<String> evoucherIds) throws RestClientException {
    	EVoucherRequest evoucherRequest = new EVoucherRequest(evoucherIds);
    	//HttpEntity<JsonNode> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/evoucher/validation/{cartGuid}", HttpMethod.POST, entity, JsonNode.class, cartGuid);
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/evoucher/validation/{cartGuid}", HttpMethod.POST, JsonNode.class, evoucherRequest, cartGuid);
    }
    
    public EVoucherValidateResponse validateEvoucherWithPayment(String cartGuid, List<String> evoucherIds, String paymentMethodCode, String creditCardNo) throws RestClientException, JsonParseException, JsonMappingException, IOException {
    	EVoucherRequest evoucherRequest = new EVoucherRequest(evoucherIds, paymentMethodCode, creditCardNo);
    	// HttpEntity<String> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/evoucher/validation/{cartGuid}", HttpMethod.POST, entity, String.class, cartGuid);
    	String apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/evoucher/validation/{cartGuid}", HttpMethod.POST, String.class, evoucherRequest, cartGuid);

    	EVoucherValidateResponse evoucherValidateResponse = mapper.readValue(apiResponse, EVoucherValidateResponse.class);
    	return evoucherValidateResponse;
    }
    
    public PrecommitOrderResponse precommitOrder(String cartGuid, PrecommitOrderRequest precommitOrderRequest) throws JsonParseException, JsonMappingException, IOException {
    	// HttpEntity<String> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/precommit", HttpMethod.POST, entity, String.class, cartGuid);
    	// TODO: Solve: exchange function can return null, then mapper will throw NullPointerException for JsonFactory parser
    	String apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/precommit", HttpMethod.POST, String.class, precommitOrderRequest, cartGuid);
    	PrecommitOrderResponse precommitOrderResponse = mapper.readValue(apiResponse, PrecommitOrderResponse.class);
    	
    	return precommitOrderResponse;
    }
    
    public PostCommitOrderJson postCommitOrder(String cartGuid, PostCommitRequest postCommitRequest) throws JsonParseException, JsonMappingException, IOException {
    	// HttpEntity<String> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.POST, entity, String.class, cartGuid);
    	String apiResponse = authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}", HttpMethod.POST, String.class, postCommitRequest, cartGuid);
    	PostCommitOrderJson postCommitOrderJson = mapper.readValue(apiResponse, PostCommitOrderJson.class);

    	return postCommitOrderJson;
    }

    public Map<String,String> hitEndPoint(Map<String,String> map,String url) throws Exception  {
    	MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
    	multiValueMap.setAll(map);
    	// TODO: must not log sensitive info
    	logger.info(multiValueMap.toString());
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    	HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, headers);
    	String responseStr = restTemplate.postForObject(url, request, String.class);
    	if(responseStr==null){
    		return null;
    	}
    	StringTokenizer keyValuePairToken = new StringTokenizer(responseStr.trim(), "&");
    	String keyValuePair = null;
    	StringTokenizer keyValueDataToken = null;
    	String key = null;
    	String value = null;
    	Map<String,String> responseMap= new HashMap<String,String>();
    	while (keyValuePairToken.hasMoreTokens()) {
    		keyValuePair = keyValuePairToken.nextToken();
    		keyValueDataToken = new StringTokenizer(keyValuePair, "=");
    		while (keyValueDataToken.hasMoreTokens()) {
    			key = keyValueDataToken.nextToken();
    			if (keyValueDataToken.hasMoreTokens()) {
    				value = keyValueDataToken.nextToken();
    				if (StringUtils.isNotBlank(key) ) {
    					responseMap.put(key, value);
    				}  
    			}
    		} // end while
    	}
    	return responseMap;
    }

    public JsonResponse getPromotionValidationResponse(String cartGuid,String ccNum ) throws JsonParseException, JsonMappingException, IOException {
    	//HttpEntity<JsonResponse> response = restTemplate.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/promotion/{ccNum}", HttpMethod.GET, entity, JsonResponse.class, cartGuid,ccNum);
    	return authUtilService.exchange(serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/cart/{cartGuid}/promotion/{ccNum}", HttpMethod.GET, JsonResponse.class, null, cartGuid, ccNum);
    }

}
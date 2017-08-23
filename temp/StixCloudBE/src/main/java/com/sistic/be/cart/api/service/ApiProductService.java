package com.sistic.be.cart.api.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.databind.JsonNode;
import com.sistic.be.app.util.AuthUtilService;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.cart.api.model.ConfirmSeatResponse;
import com.sistic.be.cart.model.AddonOptionsLineItem;
import com.sistic.be.cart.model.ConfirmSeat;
import com.sistic.be.cart.seatmap.PatronSelection;
import com.sistic.be.cart.seatmap.constant.BookingModeConsts;
import com.sistic.be.configuration.multitenant.TenantContextHolder;

@Service
public class ApiProductService {
	
	private Logger logger = LogManager.getLogger(ApiProductService.class);

	protected String serviceUrl;
	
	@Autowired
    private AuthUtilService authUtilService;

	@Autowired
    public ApiProductService(@Value("${service.product.url}") String serviceUrl) {
        this.serviceUrl = serviceUrl.startsWith("http") ?
               serviceUrl : "http://" + serviceUrl;
        
        logger.info(serviceUrl);
    }
    
    
	/**
	 * Retrieve Detail Seatmap call to Product API
	 * @param productId
	 * @return
	 */
    public JsonNode retrieveDetailSeatmap(PatronSelection patronSelection, Integer quantity) {
    	
    	// Prepare
    	String mode = patronSelection.getMode();
    	Long productId = patronSelection.getProductId();
    	Long priceCatId = patronSelection.getPriceCatId();
    	Long seatSectionId = patronSelection.getSeatSectionId();
    	String promoCode = patronSelection.getPromoCode();
    	String requestUrl = serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/products/{productId}/seatmap/availability?priceCatId={priceCatId}&seatSectionId={seatSectionId}&quantity={quantity}&mode={mode}&promocode={promoCode}";
    	
    	StringBuilder requestInfo = new StringBuilder();
    	requestInfo.append("mode: " + mode);
    	requestInfo.append(", quantity: " + quantity);
    	requestInfo.append(", productId: " + productId);
    	requestInfo.append(", priceCatId: " + priceCatId);
    	requestInfo.append(", seatSectionId: " + seatSectionId);
    	requestInfo.append(", promoCode: " + promoCode);
    	
		// HttpEntity<JsonNode> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, JsonNode.class, productId, priceCatId, seatSectionId, quantity, mode);
		return authUtilService.exchange(requestUrl, HttpMethod.GET, JsonNode.class, null, productId, priceCatId, seatSectionId, quantity, mode, promoCode);
    }
    
    public ConfirmSeatResponse confirmReserveReleaseSeats(Long productId, String mode, List<Long> reservedSeatList, List<Long> releasedSeatList) throws RestClientException {
    	
    	ConfirmSeat confirmSeat = null;
    	if (BookingModeConsts.SELFPICK.equals(mode)) {
    		confirmSeat = new ConfirmSeat(reservedSeatList, releasedSeatList);
    	} else if (BookingModeConsts.BESTAVAILABLE.equals(mode)) {
    		confirmSeat = new ConfirmSeat(new ArrayList<Long>(), releasedSeatList);
    	} else if (BookingModeConsts.HOTSHOW.equals(mode)) {
    		confirmSeat = new ConfirmSeat(new ArrayList<Long>(), new ArrayList<Long>());
    	}
    	
    	logger.info("Reserve Release Seat request" + DebugUtil.writeWithPrettyPrinter(confirmSeat));
    	
    	String requestUrl = serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/products/{productId}/seats";
    	
    	logger.info(requestUrl);
    	// ConfirmSeatResponse apiResponse = restTemplate.postForObject(requestUrl, entity, ConfirmSeatResponse.class, productId);
    	ConfirmSeatResponse apiResponse = authUtilService.exchange(requestUrl, HttpMethod.POST, ConfirmSeatResponse.class, confirmSeat, productId);
    	
    	if (apiResponse == null) {
    		logger.info("apiResponse is null");
    		throw new RestClientException("There was no response returned for confirm seat");
    	}
    	else if (apiResponse.getStatus() == 1) { //currently api set status 1 for error, 0 for ok
    		logger.error("apiResponse: " + apiResponse.toString());
//    		throw new RestClientException(apiResponse.getStatusMessage());
    		throw new RestClientException("Api error, response is: " + apiResponse.toString());
    	}
    	
    	return apiResponse;
    }
    
    public AddonOptionsLineItem[] getAddOn(List<Long> productIds){
      if(productIds != null){
        try{
          StringBuilder sb = new StringBuilder();
          
          for (Long long1 : productIds) {
            sb.append(long1).append(",");
          }
          String requestUrl = serviceUrl + TenantContextHolder.getTenant().getApiCode() + "/addon/products/seatmap/pricetable?productIds={productIds}";
        
          AddonOptionsLineItem[] response = authUtilService.exchange(requestUrl, HttpMethod.GET, AddonOptionsLineItem[].class, null, sb.deleteCharAt(sb.lastIndexOf(",")).toString());
          return response;
        }catch(Exception e){
          logger.error("No addon returned: " + e.getMessage());
        }
      }
      return new AddonOptionsLineItem[0];
    }
}

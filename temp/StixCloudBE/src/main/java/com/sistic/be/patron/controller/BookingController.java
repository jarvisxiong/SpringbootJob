package com.sistic.be.patron.controller;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sistic.be.app.util.DebugUtil;
import com.sistic.be.app.util.SessionUtil;
import com.sistic.be.app.util.SessionUtilService;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.cart.api.model.ConfirmSeatResponse;
import com.sistic.be.cart.api.model.DetailSeatmapResponse;
import com.sistic.be.cart.api.model.StatusResponse;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.cart.api.service.ApiProductService;
import com.sistic.be.cart.helper.ShoppingCartModelHelper;
import com.sistic.be.cart.model.ConfirmSeat;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.cart.seatmap.PatronSelection;
import com.sistic.be.cart.seatmap.constant.BookingModeConsts;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.exception.InvalidPatronSelectionException;
import com.sistic.be.exception.InvalidSessionException;
import com.sistic.be.exception.RequireLoginException;
import com.sistic.be.patron.api.auth.service.AuthorisationService;
import com.sistic.be.patron.model.PatronLogin;
import com.sistic.be.patron.session.OnlineUserSession;

@Controller
@RequestMapping(value = "/{tenant}")
public class BookingController extends HttpServlet {

    private static final long serialVersionUID = 9115907199514970898L;

    private Logger logger = LogManager.getLogger(BookingController.class);

	@Autowired
	private Environment env;
	@Autowired
	AuthorisationService authService;	// TODO: remove after testing
    @Autowired
    ApiCartService cartapi;
    @Autowired
    ApiProductService productapi;
	@Autowired
	SessionUtilService sessionUtil;

    @Autowired
    ObjectMapper mapper;
    
    @Autowired
    private ApiCartService cartApi;
    
    @Autowired
    private ShoppingCartModelHelper shoppingCartModelHelper;


    /*
     * RS, GA
     */
    @RequestMapping(value = "/patron/products/productid", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StatusResponse> patronProductSelection(HttpServletResponse response, HttpSession session,
                                                                 @RequestBody JsonNode json) {

        Long productId = json.get("productId").asLong();
        String internetContentCode = json.get("internetContentCode").asText();

        StatusResponse statusResponse = new StatusResponse();

        OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
        if (userSession == null) {
        	logger.error("User session was not created first. Please reload booking step.");
        	throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
        }
        
        userSession.getPatronSelection().initProductSelection(internetContentCode, productId);
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
        
        logger.info("Set session with internetContentCode: " + internetContentCode + ", productId: " + productId);

        return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.OK);
       
    }

    /**
     * RS, GA
     */
    @RequestMapping(value = "/patron/products/catsection", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StatusResponse> patronSectionSelection(HttpServletResponse response, HttpSession session,
                                                                 @RequestBody JsonNode json) {

        Long seatSectionId = json.get("seatSectionId").asLong();
        Long priceCatId = json.get("priceCatId").asLong();
        String mode = json.get("mode").asText();

        StatusResponse statusResponse = new StatusResponse();

        OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
        if (userSession == null) {
        	logger.error("User session was not created first. Please reload booking step.");
        	throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
        }
        
        // Check previous steps were selected first
        if (userSession.getPatronSelection().validateProductSelection() != true) {
        	logger.error("Internet content code or productId was not set first.");
        	throw new InvalidPatronSelectionException("Please go back to your previous step or refresh and try again.");
        }
        
        userSession.getPatronSelection().initCatSectionSelection(priceCatId, seatSectionId, mode);
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
        
        logger.info("Set session with priceCatId: " + priceCatId + ", seatSectionId: " + seatSectionId + ", mode: " + mode);

        return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.OK);
       
    }

    /**
     * @return the response from API
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @RequestMapping(value = "/products/seatmap/availability", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DetailSeatmapResponse> detailSeatmapAvailability(
                                                              HttpServletResponse response, HttpSession session,
                                                              @RequestBody JsonNode json) throws JsonParseException, JsonMappingException, IOException {
    	
        Integer quantity = json.get("quantity").asInt();
        String promoCode = null;
        if (json.get("promocode") != null) {
          promoCode = json.get("promocode").asText();
        }
        OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
        if (userSession == null) {
        	logger.error("User session was not created first. Please reload booking step.");
        	throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
        }
        PatronSelection patronSelection = userSession.getPatronSelection();
        
        // Check previous steps were selected first
        if (patronSelection.validateCatSectionSelection() != true) {
        	logger.error("Cat Section was not set first.");
        	throw new InvalidPatronSelectionException("Please go back to your previous step or refresh and try again.");
        }
        
        // Get parameters stored in session
        String mode = patronSelection.getMode();

        if (mode.equals(BookingModeConsts.GENERALADMISSION)) {
        	logger.error("seatmap availability should not be used for GA");
        	throw new InvalidPatronSelectionException("There may be a problem with the configuration or request.");
        }

        // if mode is BA HS then return error if no quantity is specified
        if (mode.equals(BookingModeConsts.BESTAVAILABLE) || mode.equals(BookingModeConsts.HOTSHOW)) {
            if (quantity == 0) {
            	logger.error("Quantity must be specified for mode " + mode);
                throw new InvalidPatronSelectionException("Quantity must be specified for mode " + mode);
            }

        } else if (mode.equals(BookingModeConsts.SELFPICK)) {
            quantity = 0;
        }
        
        /**
         * Check with max quantity allowed for ICC with properties configuration
         */
        // TODO: Check if the current ICC has custom quantity set in properties
        Integer iccMaxQty = env.getProperty("booking.cart.max-number-of-seats." + patronSelection.getInternetContentCode(), Integer.class);
        if (iccMaxQty != null) {
        	if (quantity > iccMaxQty) {
        		logger.error("Ticket max quantity exceeded for selected ICC.");
        		throw new InvalidPatronSelectionException("booking.add.cart.max-limit-exceeded");
        	}
        }

        // POST to API get response
        JsonNode detailSeatmapJson = null;
        try {
            patronSelection.initPromoCode(promoCode);
        	detailSeatmapJson = productapi.retrieveDetailSeatmap(patronSelection, quantity);
        	if (detailSeatmapJson == null) {	// TODO: Should this be removed as BE check?
        		throw new Exception("Detail seatmap json response is success but null");
        	}
        } catch (HttpStatusCodeException e) {
        	StringBuilder sb = new StringBuilder();
        	sb.append("API failed for retrieve detail seatmap");
        	sb.append(", Patron Selection: " + patronSelection);
        	sb.append(", quantity: " + quantity);
        	logger.error(sb.toString());
        	throw e;
        } catch (Exception e) {
        	logger.error(e.getMessage());
        }
            
        OffsetDateTime cartTimer = null;
       
        boolean useApiCartTimer = (userSession.getCartGuid() == null || userSession.getCartGuid().isEmpty() || userSession.getCartTimer() == null);
        if (useApiCartTimer && (mode.equals(BookingModeConsts.BESTAVAILABLE) || mode.equals(BookingModeConsts.HOTSHOW))) {
        	// reset cart timer if first item to be added (cart empty) and user makes new selection
            // user makes new selection means cart is empty and cartTimer is not empty
        	String reservedTimeStr = null;
        	try {
        		reservedTimeStr = detailSeatmapJson.get("reservedTime").asText();
        	} catch (NullPointerException e) {
        		logger.error("Could not get reservedTime from json, detailSeatmapResponse: " + DebugUtil.writeWithPrettyPrinter(detailSeatmapJson));
        	}
        	OffsetDateTime apiCartTimer = (reservedTimeStr !=null && !reservedTimeStr.isEmpty()) ? OffsetDateTime.parse(reservedTimeStr) : null;
        	userSession.setCartTimer(apiCartTimer);
        	cartTimer = apiCartTimer; 
    	} 
        // Must always return time left back to front end
        else {
        	cartTimer = userSession.getCartTimer();
        }
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
        
        DetailSeatmapResponse detailSeatmapResponse = new DetailSeatmapResponse();
        detailSeatmapResponse.setDetailSeatmapResponse(detailSeatmapJson);
        detailSeatmapResponse.setTimeLeftSeconds(sessionUtil.getTimeLeftSeconds(cartTimer));

        // return response
        return new ResponseEntity<DetailSeatmapResponse>(detailSeatmapResponse, HttpStatus.OK);
    }

    /**
     * RS events
     *
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    @RequestMapping(value = "/patron/products/confirmation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<JsonNode> patronConfirmSeats(
                                                             HttpServletResponse response,
                                                             HttpSession session,
                                                             @RequestBody JsonNode json) throws JsonParseException, JsonMappingException, IOException {

        String seats = json.get("seats").asText();
        ConfirmSeat confirmSeat = mapper.readValue(seats, ConfirmSeat.class);

        ObjectNode jsonResponse = JsonNodeFactory.instance.objectNode();

        OnlineUserSession userSession = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
        if (userSession == null) {
        	logger.error("User session was not created first. Please reload booking step.");
        	throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
        }
        
        // Check previous steps were selected first
        if (userSession.getPatronSelection().validateCatSectionSelection() != true) {
        	logger.error("Cat Section was not set first.");
            throw new InvalidPatronSelectionException("Please go back to your previous step or refresh and try again.");
        }
        
        Long productId = userSession.getPatronSelection().getProductId();	// Should not be null or should have thrown InvalidPatronSelectionException

        ConfirmSeatResponse apiResponse = null;
        try {
        	apiResponse = productapi.confirmReserveReleaseSeats(productId, userSession.getPatronSelection().getMode(), confirmSeat.getReservedSeatList(), confirmSeat.getReleasedSeatList());
        } catch (HttpStatusCodeException e) {
        	logger.error("API failed for confirmReserveReleaseSeats: " + userSession.getPatronSelection().getMode() + "\n confirmSeat" + confirmSeat);
        	throw e;
        }
        logger.info("confirm seat response /patron/products/confirmation" + DebugUtil.writeWithPrettyPrinter(apiResponse));

        // Reservation is not successful, return error
        if (0 != apiResponse.getStatus()) {
        	jsonResponse.put("httpStatus", HttpStatus.BAD_REQUEST.value());
        	jsonResponse.put("statusMessage", apiResponse.getStatusMessage());
            return new ResponseEntity<JsonNode>(jsonResponse, HttpStatus.BAD_REQUEST);
        }

        userSession.getPatronSelection().initSeatSelections(confirmSeat.getReservedSeatList());
        logger.info("Set session with reservedSeatList: " + confirmSeat.getReservedSeatList().toString());
        
        OffsetDateTime apiCartTimer = apiResponse.getReservedTime();
        OffsetDateTime cartTimer = null;
    	// User session will hold the earliest reserved time
        String mode = userSession.getPatronSelection().getMode();
        
        boolean useApiCartTimer = (userSession.getCartGuid() == null || userSession.getCartGuid().isEmpty() || userSession.getCartTimer() == null);
        if (mode.equals(BookingModeConsts.SELFPICK) && useApiCartTimer) {
        	// reset cart timer if first item to be added (cart empty) and user makes new selection
            // user makes new selection means cart is empty and cartTimer is not empty
        	userSession.setCartTimer(apiCartTimer);
        	cartTimer = apiCartTimer;
    	} else {
    		cartTimer = userSession.getCartTimer();
    	}
        
        SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
        
        // Success
        jsonResponse.put("httpStatus", HttpStatus.OK.value());
    	jsonResponse.put("statusMessage", "OK");
    	jsonResponse.putPOJO("reservedTime", cartTimer);
    	jsonResponse.put("timeLeftSeconds", sessionUtil.getTimeLeftSeconds(cartTimer));
    	
    	logger.info("Patron seat confirmation response: " + DebugUtil.writeWithPrettyPrinter(jsonResponse));

        return new ResponseEntity<JsonNode>(jsonResponse, HttpStatus.OK);
    }

    /**
     * RS, GA add to cart
     * @throws IOException 
     */
    @RequestMapping(value = "/patron/cart", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StatusResponse> patronAddToCart(HttpServletResponse response, HttpSession session,
    		@RequestBody JsonNode json,
    		@RequestParam(value = "promocode", required = false, defaultValue = "") String promoCode)
    				throws IOException {

    	// Get parameters stored in session
    	OnlineUserSession onlineUser = SessionUtil.getOnlineUserSession(session);
    	if (onlineUser == null) {
    		logger.error("User session was not created first. Please reload booking step.");
    		throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
    	}

    	onlineUser = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
    	String cartGuid = onlineUser.getCartGuid();
    	if (cartGuid == null || cartGuid.isEmpty()) {
    		cartGuid = "";
    	}

    	PatronSelection patronSelection = onlineUser.getPatronSelection();

    	// ADDON BEGIN
    	// Add donation line item
    	JsonNode isAddon = (json != null) ? json.get("type") : null;
    	if(isAddon != null && "addon".equalsIgnoreCase(isAddon.asText())){

    		patronSelection = new PatronSelection();
    		Long productId, priceCategoryId, seatSectionId;
    		String priceClassMap;
    		try {
    			productId = json.get("productid").asLong();
    			priceClassMap = json.get("priceclasscode").asText();
    			priceCategoryId = json.get("pricecategoryid").asLong();
    			seatSectionId = json.get("seatsectionid").asLong();
    		} catch (NullPointerException e) {
    			throw new InvalidPatronSelectionException("Addon input(s) is not valid");
    		}

    		ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
    		// TODO: Refactor this!
    		if (!cartGuid.isEmpty()) {
    			try {
    				shoppingCartModel = cartApi.getCart(cartGuid);
    			} catch (HttpStatusCodeException e) {
    				logger.error("Could not retrieve cart, ", e.getMessage());
    			}
    			// need check shoppingCartModel is properly populated REFACTOR
    			if (shoppingCartModel.getLineItemList() != null) {
    				List<Long> productIds = shoppingCartModelHelper.getProductIds(shoppingCartModel);
    				if(productIds.contains(productId)){
    					// already in the list
    					return new ResponseEntity<StatusResponse>(new StatusResponse(), HttpStatus.OK);
    				}
    			}
    		}

    		// Addon is for GA mode only, seat section id is -1
    		patronSelection.setMode(BookingModeConsts.GENERALADMISSION);
    		patronSelection.setSeatSectionId(seatSectionId);

    		// product id
    		patronSelection.setProductId( productId);

    		// price class
    		patronSelection.initPriceClassMap(priceClassMap);
    		logger.info("Set session with priceClassMap: " + priceClassMap);

    		// price cat id
    		patronSelection.setPriceCatId(priceCategoryId);

    		onlineUser.setPatronSelection(patronSelection);

    		// set to user session
    		SessionUtil.setOnlineUserSession(session, onlineUser);
    		//ADDON END         
    	} else{
    		// Add normal line item       
    		String priceClassMap = null;
    		// if mode GA, seatSectionId must not null
    		if (patronSelection.getMode() == null || patronSelection.getMode().equals(BookingModeConsts.GENERALADMISSION)) {
    			if (patronSelection.getSeatSectionId() == null) {
    				logger.error("Seat Section Id cannot be empty for GA mode");
    				throw new InvalidPatronSelectionException("Please go back to your previous step or refresh and try again.");
    			}
    		}

    		// SP mode
    		else {
    			if (patronSelection.getSeatSelection().getInventoryIds().isEmpty()) {
    				logger.error("Inventory List must not be null for SP, HS, BA mode");
    				throw new InvalidPatronSelectionException("Please go back to your previous step or refresh and try again.");
    			}
    		}

    		// Only set the priceClassMap into the session if previous steps were set (validate) to prevent this endpoint from being called if previous step not set
    		if (json != null) {
    			priceClassMap = json.get("priceClassMap").asText();
    			// Store the priceClassCode:ticketQty mapping list
    			// TODO: Check if the current ICC has custom quantity set in properties
    			Integer iccMaxQty = env.getProperty("booking.cart.max-number-of-seats." + patronSelection.getInternetContentCode(), Integer.class);
    			logger.info("This event has configured iccMaxQty: " + iccMaxQty);
    			if (iccMaxQty != null) {
    				patronSelection.initIccMaxQty(iccMaxQty);
    			}
    			patronSelection.initPriceClassMap(priceClassMap);
    			logger.info("Set session with priceClassMap: " + priceClassMap);
    			onlineUser.setPatronSelection(patronSelection);
    			SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", onlineUser);
    		}

    	}

    	PatronLogin patronLogin = onlineUser.getPatronLogin();
    	/**
    	 * Implement check for patron login.
    	 * For add to cart, if not login then throw error status response, no redirect
    	 */
    	if (patronLogin == null) {
    		throw new RequireLoginException();
    	}
    	/**
    	 * Call to API add to cart
    	 *
    	 * Required params: String mode, Long productId, Map<String,Integer> priceClassMap, Long priceCatId
    	 * Dependent params: List<Long> inventoryList (RS), Long seatSectionId (GA)
    	 * Optional params:  String cartGuid
    	 * Returns: cartGuid
    	 */
    	JsonNode cartGuidResponse = null;
    	try {
    		patronSelection.initPromoCode(promoCode);
    		cartGuidResponse = cartapi.addToCart(cartGuid, patronSelection);
    		logger.info("Patron add to cart response: " + DebugUtil.writeWithPrettyPrinter(cartGuidResponse));
    	} catch (HttpStatusCodeException e) {
    		logger.error("API failed for addToCart: " + cartGuid + e.getResponseBodyAsString());
    		throw e;
    	}

    	// TODO: Catch exception from API eg sold out
    	cartGuid = cartGuidResponse.get("cartGuid").asText();

    	// User session will hold the earliest reserved time
    	String reservedTimeStr = cartGuidResponse.get("reservedTime").asText();
    	OffsetDateTime apiCartTimer = (reservedTimeStr !=null && !reservedTimeStr.isEmpty()) ? OffsetDateTime.parse(reservedTimeStr) : null;
    	OffsetDateTime cartTimer = null;

    	boolean useApiCartTimer = (onlineUser.getCartGuid() == null || onlineUser.getCartGuid().isEmpty() || onlineUser.getCartTimer() == null);
    	String mode = onlineUser.getPatronSelection().getMode();
    	if (useApiCartTimer && mode.equals(BookingModeConsts.GENERALADMISSION)) {
    		// reset cart timer if first item to be added (cart empty) and user makes new selection
    		// user makes new selection means cart is empty and cartTimer is not empty
    		onlineUser.setCartTimer(apiCartTimer);
    		logger.info("Set session with cartTimer: " + apiCartTimer);
    		cartTimer = apiCartTimer;
    	} else {
    		cartTimer = onlineUser.getCartTimer();
    	}
    	logger.info("cartTimer for add to cart: " + cartTimer);

    	onlineUser.setCartGuid(cartGuid);
    	onlineUser.setPatronSelection(new PatronSelection());
    	SessionUtil.setOnlineUserSession(session, "onlineUserSessionInfo", onlineUser);

    	return new ResponseEntity<StatusResponse>(new StatusResponse(), HttpStatus.OK);

    }

    /**
     * Add To Cart Redirection
     * @throws IOException 
     */
    @RequestMapping(value = "/patron/cart", method = RequestMethod.GET)
    public String getPatronAddToCart(HttpServletResponse response, HttpSession session) throws InvalidSessionException, IOException {
        try {
            patronAddToCart(null, session, null, null);
        } catch (HttpStatusCodeException e) {
            OnlineUserSession onlineUser = SessionUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
            if (onlineUser != null) {
                PatronSelection patronSelection = onlineUser.getPatronSelection();
                logger.error(patronSelection);
                if (patronSelection != null) {
                    String contentCode = patronSelection.getInternetContentCode();
                    return "redirect:/" + TenantContextHolder.getTenantCode() + "/booking/" + contentCode;
                }
            }
            logger.error("User session was not created first. Please reload booking step.");
        	throw new InvalidPatronSelectionException("Your session has expired. Please refresh the page and try again.");
        }

        return "redirect:/" + TenantContextHolder.getTenantCode() + "/confirm/shoppingcart";
    }

}

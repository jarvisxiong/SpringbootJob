package com.sistic.be.app.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistic.be.app.util.SisticUtil;
import com.sistic.be.cart.api.model.AddToCartRequest;
import com.sistic.be.cart.api.model.JsonResponse;
import com.sistic.be.cart.api.model.StatusResponse;
import com.sistic.be.cart.api.service.ApiCartService;
import com.sistic.be.cart.api.service.ApiProductService;
import com.sistic.be.cart.form.ConfirmOrderForm;
import com.sistic.be.cart.model.ConfirmSeat;
import com.sistic.be.cart.model.ShoppingCartModel;
import com.sistic.be.cart.seatmap.PatronSelection;
import com.sistic.be.cart.seatmap.constant.BookingModeConsts;
import com.sistic.be.configuration.multitenant.TenantContextHolder;
import com.sistic.be.countries.Country;
import com.sistic.be.countries.ListCountry;
import com.sistic.be.patron.api.auth.service.AuthorisationService;
import com.sistic.be.patron.api.service.ApiPatronService;
import com.sistic.be.patron.session.OnlineUserSession;

@RestController
@RequestMapping(value = "/{tenant}")
public class TestController {
	
	@Autowired
	private RedisTemplate<String, String> template;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ApiCartService cartApi;
	@Autowired
	ApiProductService productApi;
	@Autowired
	ApiPatronService patronService;
	
	@Autowired
	ObjectMapper mapper;	// used to enable mapping back
	
	@Autowired
	SessionRepository sessionRepo;
	@Autowired
	RedisOperationsSessionRepository redisSessionRepo;
	
	@Autowired
	MessageSource messageSource;
	
	@Autowired
    private AuthorisationService authService;
	
	
	@RequestMapping("/test/mapcart")
	public ModelAndView testmapcart (HttpSession session, Model model, Locale locale) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		
		String jsonContent = new String(Files.readAllBytes(Paths
		        .get(this.getClass().getResource("/assets/shoppingcart_edit.json").toURI())));
		
		ShoppingCartModel shoppingCart = mapper.readValue(jsonContent, ShoppingCartModel.class);
		
		ListCountry countryList = patronService.retrieveCountryList(null);

		model.addAttribute("shoppingcart", shoppingCart);
		model.addAttribute("countries", countryList);
		
		ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm();
		model.addAttribute("confirmorderform", confirmOrderForm);
		
		String templateCode = TenantContextHolder.getTenant().getTemplateCode();
		
		return new ModelAndView(templateCode + "/view/shoppingcart");
	}
	
	@RequestMapping("/test/viewmodel")
	public ShoppingCartModel mapcartViewModel (HttpSession session, Model model, Locale locale) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		
		String jsonContent = new String(Files.readAllBytes(Paths
		        .get(this.getClass().getResource("/assets/shoppingcart_edit.json").toURI())));
		
		ShoppingCartModel shoppingCart = mapper.readValue(jsonContent, ShoppingCartModel.class);
		
		ListCountry countryList = patronService.retrieveCountryList(null);

		model.addAttribute("shoppingcart", shoppingCart);
		model.addAttribute("countries", countryList);
		
		ConfirmOrderForm confirmOrderForm = new ConfirmOrderForm();
		model.addAttribute("confirmorderform", confirmOrderForm);
		
		return shoppingCart;
	}
	
	@RequestMapping("/test/addcart/generate")
	public AddToCartRequest testaddcartgenerate (HttpSession session) {
		
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		PatronSelection patronSelection = userSession.getPatronSelection();
		
		AddToCartRequest addToCartRequest = null;
		
		if (BookingModeConsts.GENERALADMISSION.equals(patronSelection.getMode())) {
			addToCartRequest = new AddToCartRequest.Builder()
					.cartGuid(userSession.getCartGuid())
					.productId(patronSelection.getProductId())
					.priceClassMap(patronSelection.getPriceClassMap())
					.priceCatId(patronSelection.getPriceCatId())
					.seatSectionId(patronSelection.getSeatSectionId())
					.mode(patronSelection.getMode())
					.build();
		} else if (BookingModeConsts.BESTAVAILABLE.equals(patronSelection.getMode()) || BookingModeConsts.SELFPICK.equals(patronSelection.getMode()) || BookingModeConsts.HOTSHOW.equals(patronSelection.getMode())) {
			addToCartRequest = new AddToCartRequest.Builder()
					.cartGuid(userSession.getCartGuid())
					.productId(patronSelection.getProductId())
					.priceClassMap(patronSelection.getPriceClassMap())
					.priceCatId(patronSelection.getPriceCatId())
					.inventoryList(patronSelection.getSeatSelection().getInventoryIds())
					.mode(patronSelection.getMode())
					.build();
		}
		
		return addToCartRequest;
	}
	
	@RequestMapping(value="/test/seatmap/availability/SP")
	@ResponseBody
	public ResponseEntity<JsonNode> testDetailSeatmapSP (HttpServletResponse response, HttpSession session) {
		
		PatronSelection patronSelection = new PatronSelection();
		patronSelection.setInternetContentCode("bill1114");
		patronSelection.setProductId(251326L);
		patronSelection.setMode(BookingModeConsts.SELFPICK);
		patronSelection.setPriceCatId(10200L);
		patronSelection.setSeatSectionId(357951L);
		
		// POST to API get response
		JsonNode detailSeatmapResponse = productApi.retrieveDetailSeatmap(patronSelection, null);
		
		//Sample Response
//		{
//		  "inventoryId": 122650600,
//		  "seatRowAlias": "CC",
//		  "seatAlias": "10",
//		  "seatType": 1
//		},
//		{
//		  "inventoryId": 122650601,
//		  "seatRowAlias": "CC",
//		  "seatAlias": "15",
//		  "seatType": 1
//		},
//		{
//		  "inventoryId": 122650602,
//		  "seatRowAlias": "CC",
//		  "seatAlias": "8",
//		  "seatType": 1
//		},
		
		// return response
		return new ResponseEntity<JsonNode>(detailSeatmapResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="/test/seatmap/availability/HS")
	@ResponseBody
	public ResponseEntity<JsonNode> testDetailSeatmapHS (HttpServletResponse response, HttpSession session) {
		
		PatronSelection patronSelection = new PatronSelection();
		patronSelection.setInternetContentCode("bill1114");
		patronSelection.setProductId(254854L);
		patronSelection.setMode(BookingModeConsts.HOTSHOW);
		patronSelection.setPriceCatId(2136L);
		patronSelection.setSeatSectionId(359061L);
		Integer quantity = null;
		

		// POST to API get response
		JsonNode detailSeatmapResponse = productApi.retrieveDetailSeatmap(patronSelection, quantity);
		
		// return response
		return new ResponseEntity<JsonNode>(detailSeatmapResponse, HttpStatus.OK);
	}
	
	// SP mode
	@RequestMapping(value="/test/products/confirmation")
	@ResponseBody
	public ResponseEntity<StatusResponse> patronConfirmSeats (HttpServletResponse response, HttpSession session) {
		
		// Get parameters stored in session
		StatusResponse statusResponse = new StatusResponse();
		
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		
		Long productId = 251326L;
		ConfirmSeat confirmSeat = new ConfirmSeat();
		Long[] reservedList = new Long[] {122650600L, 122650601L};
		confirmSeat.setReservedSeatList(Arrays.asList(reservedList));
		
		// call API to release seatList.releasedSeatList
		JsonResponse apiResponse = productApi.confirmReserveReleaseSeats(productId, BookingModeConsts.SELFPICK, confirmSeat.getReservedSeatList(), confirmSeat.getReservedSeatList());
		
		// Write seatList.reservedSeatList into session
		userSession.getPatronSelection().initSeatSelections(confirmSeat.getReservedSeatList());
		SisticUtil.setOnlineUserSession(session, "onlineUserSessionInfo", userSession);
		
		// return OK
        return new ResponseEntity<StatusResponse>(statusResponse, HttpStatus.OK);
	}
	
	@RequestMapping("/test/deliverymethods")
	@ResponseBody
	public String deliveryMethodsJson(HttpSession session) throws JsonParseException, JsonMappingException, RestClientException, IOException {
		// Get parameters stored in session
		OnlineUserSession userSession = SisticUtil.getOnlineUserSession(session, "onlineUserSessionInfo");
		String cartGuid = userSession.getCartGuid();
		String token = (userSession.getPatronLogin() != null) ? "Bearer " + authService.getAccessToken() : null;
		ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
				
		shoppingCartModel = cartApi.getCart(cartGuid);
		Object commonDeliveryMethod = shoppingCartModel.getCommonDeliveryMethod().getDeliveryMethodList();
		String json = mapper.writeValueAsString(commonDeliveryMethod);
		return json;
	}
	
	@RequestMapping("/uat/listCountries")
	@ResponseBody
	public List<Country> listCountries() {
		ListCountry countryList = patronService.retrieveCountryList(null);
		return countryList.getCountriesSortedByCountryName();
	}

	/*@RequestMapping("/storemap")
	@ResponseBody
	public String storemap(HttpServletResponse response) {
		
		/*ValueOperations<Object, Object> ops = template.opsForValue();
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("1", "value 1");
		valueMap.put("2", "value 2");
		valueMap.put("3", "value 3");
		valueMap.put("4", "value 4");
		ops.set("identifier", valueMap);
		
		HashOperations<String,String,String> ops = template.opsForHash();
		Map<String, String> valueMap = new HashMap<String, String>();
		valueMap.put("1", "value 1");
		valueMap.put("2", "value 2");
		valueMap.put("3", "value 3");
		valueMap.put("4", "value 4");
		ops.putAll("testkeyabc", valueMap);
		
		valueMap.keySet();
		
		String value = ops.get("testkeyabc", "4");
		
		return value;
	}*/

	@RequestMapping("/business")
	public ResponseEntity<Object> performBusinessLogic(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, 
			@RequestParam(name="preview", required=true) String preview) throws Exception {
		
		return new ResponseEntity<Object>("successful", HttpStatus.OK);
	}

}

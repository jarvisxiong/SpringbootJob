package com.stixcloud.product.api;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.AddonMapping;
import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.domain.InternetContentCodeListing;
import com.stixcloud.domain.InternetContentProducts;
import com.stixcloud.domain.PriceTableInfo;
import com.stixcloud.domain.RetrievePriceTypeView;
import com.stixcloud.domain.RetrieveSectionGaView;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.SeatmapOverviewProducts;
import com.stixcloud.product.api.json.ProductConfigJson;
import com.stixcloud.product.constant.EventConstants;
import com.stixcloud.product.constant.PromoConstants;
import com.stixcloud.product.event.IEventService;
import com.stixcloud.product.event.PmtChartLiveRepository;
import com.stixcloud.product.event.PromoService;
import com.stixcloud.product.util.FileInfoUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.money.Monetary;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value = "/{tenant}", produces = "application/json")
public class EventController {

  private static final Logger logger = LogManager.getLogger(EventController.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private IEventService eventService;
  
  @Autowired
  private DetailSeatmapFactory detailSeatmapFactory;

  @Autowired
  private IPriceClass priceClass;

  @Autowired
  private PmtChartLiveRepository pmtChartLiveRepository;
  
  @Autowired
  private PromoService promoService;

  @Value("${properties.max.tickets}")
  private int maxTickets;

  @Autowired
  private ApplicationEventPublisher eventPublisher;
  
	@RequestMapping(value = {"/icc"}, method = RequestMethod.GET)
	public Object  getIccList(Pageable pageable,
//			@PathVariable Map<String, String> pathVariables,
			Locale locale, HttpServletRequest request) 
	throws Exception {
//		LocalDate dt=getDateFromMap(pathVariables);
		LocalDate dt=LocalDate.now();
		Page<InternetContentCodeListing> iccListResponse=eventService.getIccList(dt, pageable, request);
		logger.info("---------------------------iccList : "  + iccListResponse);
		
		if (!iccListResponse.hasContent()) {
			JsonResponse jsonResponse = new JsonResponse.Builder()
					.httpStatus(HttpStatus.BAD_REQUEST.toString())
					.statusMessage("No icc found")
					.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
		}
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
		uriBuilder.queryParam("page", pageable.getPageNumber());
		uriBuilder.queryParam("size", pageable.getPageSize());
		PaginatedResultsRetrievedEvent<InternetContentCodeListing> event = new PaginatedResultsRetrievedEvent<InternetContentCodeListing>(InternetContentCodeListing.class, uriBuilder, iccListResponse, pageable, iccListResponse.getTotalPages());
		eventPublisher.publishEvent(event);

		return event.getPage();
	}

	@RequestMapping(value = {"/products/config/{updatedDate}", "/products/config"}, method = RequestMethod.GET)
	public Object productConfig(
			@PathVariable Map<String, String> pathVariables)			
//			@RequestParam(value = "updatedDate", required = false) String updatedDate) 
					throws Exception{
		final int maxRecs=30;
		LocalDate date=getDateFromMap(pathVariables);

	    List<ProductConfigJson> productsList =
	            eventService.getProductConfig(date);

		if (productsList.isEmpty()) {
			JsonResponse jsonResponse = new JsonResponse.Builder()
					.httpStatus(HttpStatus.BAD_REQUEST.toString())
					.statusMessage("No updated event found")
					.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
		} 
		/*else if (productsList.size()>maxRecs) {
			JsonResponse jsonResponse = new JsonResponse.Builder()
					.httpStatus(HttpStatus.BAD_REQUEST.toString())
					.statusMessage("Request returns more than "+maxRecs+" events")
					.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);			
		}*/
		return productsList;
	}

  @RequestMapping(value = "/icc/{contentCode}", method = RequestMethod.GET)
  @Cacheable(value = "icc",
      key = "{#root.methodName, #tenant, #contentCode, #startDate, #numOfDays, #promoCode}",
      unless = "#result instanceof T(org.springframework.http.ResponseEntity)")
  public Object getShowTimings(@PathVariable(value = "contentCode") String contentCode,
      @PathVariable String tenant,
      @RequestParam(value = "startDate", required = false) String startDate,
      @RequestParam(value = "numOfDays", defaultValue = "31", required = false) Integer numOfDays,
      @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode) {
    
    int promoState = promoService.getPromoState(contentCode, promoCode);
    List<InternetContentProducts> products = eventService.getShowTimings(contentCode, startDate, numOfDays, promoCode, promoState);
    
    if (products.isEmpty()) {
      JsonResponse jsonResponse =
          new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString())
              .statusMessage(
                  messageSource.getMessage("icc.error", null, LocaleContextHolder.getLocale()))
              .build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }

    InternetContentProducts contentProduct = products.get(0);
    ShowTimes showTimes = new ShowTimes();
    if (contentProduct.getSummaryimagepath() != null) {
      showTimes.setSummaryImagePath(
          FileInfoUtil.generateRelativeFilePath(contentProduct.getSummaryimagepath()));
    }
    if (contentProduct.getIcattributes() != null) {
      showTimes.setIcAttributesList(Arrays.asList(contentProduct.getIcattributes().split(",")));
    }

    boolean isPromo = PromoConstants.PROMO_CODE_VALID == promoState;
    List<ShowTimingList> showTimingList = new ArrayList<>();
    showTimingList.addAll(products
        .stream().map(icp -> new ShowTimingList(icp.getProductid(), icp.getProductname(),
            icp.getStartdate(), icp.getVenuename(), icp.getAvailablityStatus(), isPromo))
        .collect(Collectors.toSet()));

    Collections.sort(showTimingList, Comparator.comparing(ShowTimingList::getShowDateTime));

    showTimes.setPromoStatus(promoState + "");
    showTimes.setShowTimingList(showTimingList);
    logger.debug("showTimingList size = " + showTimingList.size());
    logger.debug("Show Times = " + showTimes.toString());

    return showTimes;
  }

  @RequestMapping(value = "/products/{productid}/seatmap/overview", method = RequestMethod.GET)
  @ResponseBody
  public Object getSeatmapOverview(
      @PathVariable("productid") Long productid,
      @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode,
      Locale locale) {

    List<SeatmapOverviewProducts> seatmapOverviewProductsList = null;
    try {
      seatmapOverviewProductsList = eventService.getSeatmapOverview(productid, promoCode);
    } catch (Exception e) {
      logger.error(e.getMessage());
      JsonResponse jsonResponse = new JsonResponse.Builder()
          .httpStatus(HttpStatus.BAD_REQUEST.toString()).statusMessage(e.getMessage()).build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
        
    if (seatmapOverviewProductsList.isEmpty()) {
      logger.info("seatmapOverviewProductsList is empty");
      return null;
    }

    //creation of SeatLevel objs, will be utilizing SeatmapOverviewProducts.equals() to determine if objects are similar
    LinkedHashMap<SeatmapOverviewProducts, SeatLevel> seatLevelHashMap = new LinkedHashMap<>();
    for (SeatmapOverviewProducts p : seatmapOverviewProductsList) {
      SeatLevel seatLevel = seatLevelHashMap.get(p);
      if (seatLevel == null) {
        Set<String> coordinatesList = null;
        if (StringUtils.isNotBlank(p.getCoordinates())) {
          coordinatesList = new HashSet<>();
          coordinatesList.add(p.getCoordinates());
        }

        seatLevel = new SeatLevel(p.getSeatlevelalias(), p.getSeatsectionid(),
            p.getSeatsectionslias(), p.getSeatsectiontype(),
            p.getSeatentrance(), p.getPricecatnum(), p.getPricecatid(),
            Money.of(p.getPricevalue(), Monetary.getCurrency(locale)), p.getRedeem().equals("T"),
            coordinatesList);
      } else {
        if (StringUtils.isNotBlank(p.getCoordinates())) {
          seatLevel.getCoordinatesList().add(p.getCoordinates());
        }
      }
      seatLevelHashMap.put(p, seatLevel);
    }

    //creation of SeatSectionList objs
    LinkedHashMap<String, SeatSectionList> seatSectionListHashMap = new LinkedHashMap<>();
    seatmapOverviewProductsList.forEach(p -> {
      BigDecimal
          standardPrice =
          pmtChartLiveRepository.getStandardPriceClassValue(p.getProductid(), p.getPricecatid());

      seatSectionListHashMap.put(p.getPricecatalias(),
          new SeatSectionList(p.getPricecatalias(),
              (standardPrice != null ? Money.of(standardPrice, Monetary.getCurrency(locale))
                  : null),
              new ArrayList<>()));
    });

    //place SeatLevel of same price cat alias into their respective SeatSectionList
    seatLevelHashMap.forEach((key, value) -> {
      SeatSectionList seatSectionList = seatSectionListHashMap.get(key.getPricecatalias());
      if (seatSectionList != null) {
        seatSectionList.getSeatLevel().add(value);
        seatSectionListHashMap.put(key.getPricecatalias(), seatSectionList);
      }
    });

    ArrayList<SeatSectionList> seatSectionLists = new ArrayList<>(
        seatSectionListHashMap.values());
    seatSectionLists.forEach(seatSectionList ->
        logger.debug(seatSectionList.getPriceCatAlias() + " : "
            + seatSectionList.getSeatLevel().size()));

    SeatmapOverviewProducts
        product =
        seatmapOverviewProductsList.stream().filter(
            p -> p.getSeatmapMappingId() != null && StringUtils.isNotEmpty(p.getImageurl()))
            .findFirst().orElse(seatmapOverviewProductsList.get(0));

    String
        imageUrl =
        (StringUtils.isNotEmpty(product.getImageurl()) && product.getNoSeatmapAttr() == 0 ?
            (product.getSeatmapMappingId() != null ? FileInfoUtil.getSeatmapOverviewFileLocation(
                product.getSeatmapMappingId(), product.getImageurl(), true) : null)
            : null);

    if (product.getInteractive() == 0 && product.getNoSeatmapAttr() == 0) {
      try {
        imageUrl = eventService.getOverviewImage(productid);
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
        throw new RuntimeException(e);
      }
    }
    return new SeatmapOverview(
        product.getHideStandardPrice(),
        (StringUtils.isNotEmpty(imageUrl) && product.getNoSeatmapAttr() == 0 ? 1 : 0),
        product.getInteractive(),
        product.getSalesMode(),
        imageUrl,
        seatSectionLists);
  }

  @RequestMapping(value = "/products/{productid}/seatmap/availability",
      method = RequestMethod.GET,
      params = {"!priceCatId", "!seatSectionId", "!quantity", "!mode"})
  @ResponseBody
  public List<SeatmapAvailability> getSeatmapAvailability(
      @PathVariable("productid") Long productid,
      @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode) {
    return eventService.getSeatmapAvailability(productid, promoCode)
        .stream()
        .map(s -> new SeatmapAvailability(
            s.getSeatsectionid(), s.getPriceCatId(), s.getSeatSectionAvailability()))
        .collect(Collectors.toList());
  }

  @RequestMapping(value = "/products/{productid}/seatmap/pricetable", method = RequestMethod.GET)
  @ResponseBody
  public List<PriceTable> getSeatmapPriceTable(
	      @PathVariable("productid") Long productid,
	      @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode,
	      @RequestParam(value = "ispackageclass", defaultValue = EventConstants.PACKAGE_CLASS_F, required = false) String isPackageClass,
	      Locale locale) {
    List<PriceTableInfo> priceTableInfoList =
        eventService.getSeatmapPriceTable(productid, promoCode, isPackageClass);

    LinkedHashMap<String, PriceTable> priceTableLinkedHashMap = new LinkedHashMap<>();
    priceTableInfoList.forEach(p -> priceTableLinkedHashMap.put(p.getPriceclasscode(),
        new PriceTable(p.getPriceclasscode(), p.getPriceclassname(), new ArrayList<>())));

    priceTableInfoList
        .forEach(p -> {
          PriceTable priceTable = priceTableLinkedHashMap.get(p.getPriceclasscode());
          if (priceTable != null) {
            priceTable.getPriceCatList().add(
                new PriceCatList(
                    p.getPricecategorynumber(),
                    (p.getPricevalue() != null ? Money
                        .of(p.getPricevalue(), Monetary.getCurrency(locale)) : null),
                    p.getPriceStatus()));
            priceTableLinkedHashMap.put(p.getPriceclasscode(), priceTable);
          }
        });

    for (Iterator<Map.Entry<String, PriceTable>> it = priceTableLinkedHashMap.entrySet().iterator();
         it.hasNext(); ) {
      Map.Entry<String, PriceTable> entry = it.next();
      PriceTable priceTable = entry.getValue();
      long
          size =
          priceTable.getPriceCatList().stream().filter(p -> p.getPriceValue() == null).count();
      if (size == priceTable.getPriceCatList().size()) {
        it.remove();
      }
    }
    return new ArrayList<>(priceTableLinkedHashMap.values());
  }

  @RequestMapping(value = "/products/{productId}/seatmap/availability", method = RequestMethod.GET)
  @ResponseBody
  public Object getSeatmapAvailability(
      @PathVariable("productId") long productId,
      @RequestParam(value = "priceCatId") long priceCatId,
      @RequestParam(value = "seatSectionId") long seatSectionId,
      @RequestParam(value = "quantity", required = false, defaultValue = "0") int quantity,
      @RequestParam(value = "mode") String mode,
      @RequestParam(value = "promocode", defaultValue = "", required = false) String promoCode) {

    List<DetailSeatmapProducts> detailSeatmapProductsList = new ArrayList<>();
    
    // Added to check if valid promo code with some configured hold codes
    boolean isHold = Boolean.FALSE;
    Set<Long> holdIds = new HashSet<>();
    if (StringUtils.isNotEmpty(promoCode)
        && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      holdIds = promoService.getHoldIds(productId, promoCode);
      isHold = CollectionUtils.isNotEmpty(holdIds);
      logger.info("Valid promo code with hold status: " + isHold + ", hold code id(s): " + holdIds);
    }
    // Added to check if valid promo code with some configured hold codes
    
    try {
      detailSeatmapProductsList =
          eventService.getDetailSeatmap(productId, priceCatId, seatSectionId, holdIds);

      if (seatSectionId > 0) {
        detailSeatmapProductsList = detailSeatmapProductsList.stream()
            .filter(list -> list.getSectionId() == seatSectionId)
            .collect(Collectors.toList());
      }

      Predicate<DetailSeatmapProducts>
          checkSeatPropertyNull =
          s -> s.isInteractive() && (s.getCoordinates() == null ||
              s.getTopLeftCoordinates() == null || s.getSeatAngle() == null);

      if ((SeatSelectionType.BEST_AVAILABLE.getSeatSelectionValue().equals(mode)
          || SeatSelectionType.HOT_SHOW.getSeatSelectionValue().equals(mode)
          || SeatSelectionType.SELF_PICK.getSeatSelectionValue().equals(mode))
          && detailSeatmapProductsList.stream().anyMatch(checkSeatPropertyNull)) {
        
        logger.error("overview.seatmap.selection.error: there is null value in seat map properties");
        JsonResponse jsonResponse = new JsonResponse.Builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString())
            .statusMessage("overview.seatmap.selection.error")
            .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonResponse);
      }

    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    if (detailSeatmapProductsList.isEmpty()) {
      return new DetailSeatmap();
    }
    
    IDetailSeatmap detailSeatmapImpl = detailSeatmapFactory.getSeatSelectionType(mode);
    DetailSeatmap
        detailSeatmap =
        detailSeatmapImpl
            .getDetailSeatmap(detailSeatmapProductsList, productId, priceCatId, seatSectionId,
                quantity, isHold);
    logger.debug("DetailSeatmap type: " + detailSeatmapImpl.getClass());
    if (!detailSeatmap.getSetsReservedList().isEmpty()) {
      //hold the seats in the reserved list
      List<Long> seatsHeldList = new ArrayList<>();

      for (SetsReservedList list : detailSeatmap.getSetsReservedList()) {
        for (Seat seat : list.getSetsReserved()) {
          seatsHeldList.add(seat.getInventoryId());
        }
      }
      eventService.reserveSeats(productId, seatsHeldList);
      detailSeatmap.setReservedTime(OffsetDateTime.now());
    } else {
      // no seats found so return the appropriate error code depending on the mode
      if (SeatSelectionType.BEST_AVAILABLE.getSeatSelectionValue().equals(mode)) {
        logger.error("There is no available seat for productId=" + productId + ", priceCatId="
            + priceCatId + ", seatSectionId=" + seatSectionId + ", quantity=" + quantity + ", mode="
            + mode + ", promocode=" + promoCode);
        
        JsonResponse jsonResponse = new JsonResponse.Builder()
            .httpStatus(HttpStatus.BAD_REQUEST.toString())
            .statusMessage("detail.seatmap.insufficient.seats")
            .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
      }
    }

    return detailSeatmap;
  }

  @RequestMapping(value = "/products/{productId}/seatmap/2d", method = RequestMethod.POST)
  @ResponseBody
  public Seatmap2D get2DSeatmap(
      @PathVariable("productId") long productId,
      @RequestBody List<Long> seatInvIds) {

    try {
      Seatmap2D seatmap2D = new Seatmap2D();
      Long startTime = System.nanoTime();
      seatmap2D.setOverview(eventService.getOverviewImage(seatInvIds, productId));
      logger.debug("Overview timing in ms : " + TimeUnit.MILLISECONDS
          .convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));

      startTime = System.nanoTime();
      seatmap2D.setDetail(eventService.getDetailImages(seatInvIds, productId));
      logger.debug("Detail timing in ms : " + TimeUnit.MILLISECONDS
          .convert(System.nanoTime() - startTime, TimeUnit.NANOSECONDS));
      return seatmap2D;
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  /**
   * Get Price Class information.
   * @return List Price Class information
   */
  @RequestMapping(value = "/products/{productId}/tickettype", method = RequestMethod.GET)
  public List<PriceClass> getTicketTypeList(
      @PathVariable("productId") Long productId,
      @RequestParam(value = "priceCatId", required = false) Long priceCatId,
      @RequestParam(value = "promocode", required = false) String promoCode,
      @RequestParam(value = "packageClassType", defaultValue = EventConstants.PACKAGE_CLASS_F, required = false) String packageClassType) {

    List<PriceClass> priceList = new ArrayList<PriceClass>();
    String[] validPackageClassTypes= {EventConstants.PACKAGE_CLASS_F, EventConstants.PACKAGE_CLASS_T};
    try {
      if (!(Arrays.asList(validPackageClassTypes).contains(packageClassType))) {
        logger.error("Invalid Package Class Type: packageClassType=" + packageClassType);
		throw new RuntimeException("Invalid Package Class Type");
      }
		
      List<RetrievePriceTypeView> retrievePriceTypeViewList =
          eventService.getPriceClassList(productId, priceCatId,
              TenantContextHolder.getTenant().getUserInfoId(),
              TenantContextHolder.getTenant().getProfileInfoId(),
              promoCode,
              packageClassType);

      logger.debug("Total ticket type gotten = " + retrievePriceTypeViewList.size());

      return priceClass.getPriceClassListFromPriceTypeView(retrievePriceTypeViewList);
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
    }

    return priceList;
  }

  /**
   * Retrieve show details for GA event.
   * @return List show detail
   */
  @RequestMapping(value = "/products/{productId}/seatmap/ga", method = RequestMethod.GET)
  public Object getSeatmapForGA(@PathVariable("productId") Long productId,
                                       @RequestParam(value = "promocode", required = false) String promoCode) throws Exception {
    List<SectionGa> sectionGaList = new ArrayList<SectionGa>();
    try {
      List<RetrieveSectionGaView> gaSeatSectionList =
          eventService
              .getSectionGaList(productId, TenantContextHolder.getTenant().getProfileInfoId(), promoCode);
      for (RetrieveSectionGaView sectionGa : gaSeatSectionList) {
        SectionGa tmpSectionGa =
            new SectionGa(sectionGa.getSeatSectionId(), sectionGa.getSeatSectionAlias(),
                sectionGa.getSeatSectionType(), sectionGa.getPriceCategoryNum(),
                sectionGa.getPriceCategoryId(), sectionGa.getSeatSectionAvailability(),
                Money.of(sectionGa.getPrice(),
                    Monetary.getCurrency(LocaleContextHolder.getLocale())));
        sectionGaList.add(tmpSectionGa);
      }
    } catch (Exception ex) {
      logger.error(ex.getMessage(), ex);
      JsonResponse jsonResponse = new JsonResponse.Builder()
          .httpStatus(HttpStatus.BAD_REQUEST.toString()).statusMessage(ex.getMessage()).build();
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(jsonResponse);
    }
    return new SectionGaList(sectionGaList);
  }

  @RequestMapping(value = "/products/{productid}/seats", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseBody
  public JsonResponse confirmSeats(@PathVariable("productid") Long productId,
                                   @RequestBody ConfirmSeat confirmSeat,
                                   Locale locale) {
    JsonResponse.Builder builder = new JsonResponse.Builder();
    List<Long> reservedSeats = confirmSeat.getReservedSeatList();

    if (reservedSeats != null && !reservedSeats.isEmpty()) {
      List<SalesSeatInventory> seatInventories =
          eventService.reserveSeats(productId, reservedSeats);
      if (reservedSeats.size() != seatInventories.size()) {
        builder.status("1");
        builder.statusMessage(
            messageSource.getMessage("confirm.seats.reserve.error", null, locale));
        logger.info("confirm.seats.reserve.error: productId=" + productId + ", reservedSeats" + confirmSeat);
        return builder.build();
      } else {
        if (!reservedSeats.isEmpty()) {
          OffsetDateTime
              reservedTime =
              seatInventories.stream()
                  .sorted(Comparator.comparing(SalesSeatInventory::getReserveexpirydate))
                  .findFirst().get().getReserveexpirydate();
          reservedTime =
              reservedTime
                  .minus(TenantContextHolder.getTenant().getSeatInventoryExpiry(),
                      ChronoUnit.MINUTES);
          builder
              .additionalProperties("reservedTime", reservedTime.toString());
        }
      }
    }

    List<Long> releasedSeats = confirmSeat.getReleasedSeatList();
    if (releasedSeats != null && !releasedSeats.isEmpty()) {
      List<SalesSeatInventory> seatInventories =
          eventService.releaseSeats(productId, releasedSeats);
      if (releasedSeats.size() != seatInventories.size()) {
        builder.status("1");
        builder.statusMessage(
            messageSource.getMessage("confirm.seats.release.error", null, locale));
        logger.info("confirm.seats.release.error: productId=" + productId + ", releasedSeats=" + releasedSeats);
        return builder.build();
      }

    }
    builder.status("0");
    builder.statusMessage(messageSource.getMessage("confirm.seats.success", null, locale));
    return builder.build();
  }


  @RequestMapping(value = "/icc/cache/evict", method = RequestMethod.DELETE)
  @CacheEvict(value = "icc", allEntries = true)
  @ResponseBody
  public JsonResponse evictIccCache() {
    return new JsonResponse.Builder().status(HttpStatus.OK.toString())
        .status("Cache was evicted successfully").build();
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonResponse handleMissingParameterException(MissingServletRequestParameterException e,
                                                      Locale locale) {
    logger.error(e.getMessage(), e);
    JsonResponse.Builder
        builder =
        new JsonResponse.Builder().httpStatus(HttpStatus.BAD_REQUEST.toString());
    switch (e.getParameterName()) {
      case "contentCode":
        builder
            .statusMessage(messageSource.getMessage("missing.param.contentCode", null, locale));
      case "productId":
        builder
            .statusMessage(messageSource.getMessage("missing.param.productId", null, locale));
        break;
      case "priceCatId":
        builder
            .statusMessage(messageSource.getMessage("missing.param.priceCatId", null, locale));
        break;
      case "seatSectionId":
        builder
            .statusMessage(
                messageSource.getMessage("missing.param.seatSectionId", null, locale));
        break;
      case "quantity":
        builder
            .statusMessage(messageSource.getMessage("missing.param.quantity", null, locale));
        break;
      case "mode":
        builder
            .statusMessage(messageSource.getMessage("missing.param.mode", null, locale));
        break;
      default:
        builder
            .statusMessage(e.getMessage());
        break;
    }
    return builder.build();
  }

  @RequestMapping(value = "/addon/products/seatmap/pricetable", method = RequestMethod.GET)
  @ResponseBody
  public List<AddOnPriceTable> getAddOnSeatmapPriceTable(
      @RequestParam(value = "productIds", required = true) String productIDList,
      Locale locale) {

    List<AddOnPriceTable> addOnPriceTableList = new ArrayList<AddOnPriceTable>();
    List<Long> addOnproductList = new ArrayList<Long>();
    HashMap<Long, AddOnPriceTable> addonsMap = new HashMap<Long, AddOnPriceTable>();
    List<Long> productList = Stream.of(productIDList.split(","))
        .map(Long::parseLong)
        .collect(Collectors.toList());
    
    // retrieve AddOnProductList
    List<AddonMapping> addOnProductList = eventService
        .findAddOnProductsByEventProductIds(productList);
    //Get product Id and Product description to be displayed for donation
    addOnProductList.stream().forEach(p -> {
      AddOnPriceTable temp = new AddOnPriceTable();
      // add product id to list
      addOnproductList.add(p.getAddonProductID());
      // build addon object
      temp.setAddonType(p.getAddonType());
      temp.setProductID(p.getAddonProductID());
      temp.setProductMessage(p.getMessage());
      temp.setProductDescription(p.getDescription());
      
      if(p.getShowLightbox() == true){
        temp.setLightboxContent(p.getLightboxContent());
      }
      addonsMap.put(p.getAddonProductID(), temp);
    });

    //Get Addon Product price table details
    if (addOnproductList != null && addOnproductList.size() > 0) {
      
      List<SeatmapOverviewProducts> priceTableInfoList = eventService.getSeatmapOverview(addOnproductList);

      priceTableInfoList.sort((SeatmapOverviewProducts obj1, SeatmapOverviewProducts obj2) -> obj1.getPricevalue()
          .compareTo(obj2.getPricevalue()));
      // Group by Productid
      Map<Long, List<SeatmapOverviewProducts>> map = priceTableInfoList
          .stream()
          .collect(
              Collectors.groupingBy(SeatmapOverviewProducts::getProductid));

      map.forEach((key, value) -> {
        List<SeatmapOverviewProducts> obj = (List<SeatmapOverviewProducts>) value;
        LinkedHashMap<String, PriceTable> priceTableLinkedHashMap = new LinkedHashMap<>();
        obj.forEach(p -> {
          priceTableLinkedHashMap.put(
              p.getPriceclasscode(),
              new PriceTable(p.getPriceclasscode(), p
                  .getPricecatalias(), new ArrayList<>()));
        });

        obj.forEach(p -> {
          PriceTable priceTable = priceTableLinkedHashMap.get(p
              .getPriceclasscode());
          if (priceTable != null) {
            priceTable.getPriceCatList().add(
                new PriceCatList(p.getPricecatnum(),
                    (p.getPricevalue() != null ? Money.of(p.getPricevalue(),
                        Monetary.getCurrency(locale)) : null),
                   p.getPricecatid(), p.getSeatsectionid()));
            priceTableLinkedHashMap.put(p.getPriceclasscode(),
                priceTable);
          }
        });

        AddOnPriceTable temp = addonsMap.get(key);
        temp.setPriceTableList(new ArrayList<>(priceTableLinkedHashMap.values()));
        addOnPriceTableList.add(temp);
      });
    }
    return addOnPriceTableList;
  }

	private LocalDate getDateFromMap(Map<String, String> pathVariables) {
		String updatedDate="";
		if (pathVariables.containsKey("updatedDate")) {
			updatedDate=pathVariables.get("updatedDate");
		}
			
		return (StringUtils.isNotEmpty(updatedDate)) ?   
	    		LocalDate.parse(updatedDate, DateTimeFormatter.ISO_DATE) :  
	    		LocalDate.now();
	}

}

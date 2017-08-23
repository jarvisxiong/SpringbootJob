/**
 *
 */
package com.stixcloud.product.event;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.AddonMapping;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.domain.InternetContentCodeListing;
import com.stixcloud.domain.InternetContentProducts;
import com.stixcloud.domain.PaymentMethod;
import com.stixcloud.domain.PriceTableInfo;
import com.stixcloud.domain.ProductConfig;
import com.stixcloud.domain.RetrievePriceTypeView;
import com.stixcloud.domain.RetrieveSectionGaView;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.SeatmapAvailablityProducts;
import com.stixcloud.domain.SeatmapOverviewProducts;
import com.stixcloud.dto.PromoCodeDTO;
import com.stixcloud.product.api.json.IccListPage;
import com.stixcloud.product.api.json.InternetContentCodeListingJson;
import com.stixcloud.product.api.json.ProductConfigJson;
import com.stixcloud.product.constant.InternetContentConstants;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.product.constant.PromoConstants;
import com.stixcloud.product.util.FileInfoUtil;
import com.stixcloud.seatmap.service.ImageCreationService;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class EventService implements IEventService {
	private final Logger logger = LogManager.getLogger(IEventService.class);

	@Autowired
	private MessageSource message;
	@Autowired
	IInternetContentProductsRepository showTimingRepository;
	@Autowired
	ISeatmapOverviewProductsRepository seatmapOverviewProductsRepository;
	@Autowired
	ISeatmapAvailablityProductsRepository seatmapAvailabilityRepository;
	@Autowired
	IPriceClassRepository priceClassRepository;
	@Autowired
	IDetailSeatmapRepository detailSeatmapRepository;
	@Autowired
	IPriceTableInfoRepository priceTableInfoRepository;
	@Autowired
	SalesSeatInventoryRepository seatInventoryRepository;
	@Autowired
	ImageCreationService imageCreationService;
	@Autowired
	ISectionGaRepository sectionGaRepository;
	@Autowired
	AddOnProductRepository addOnProductRepository;

	@Autowired
	private IDeliveryMethodRepository deliveryMethodRepository;

	@Autowired
	private IPaymentMethodRepository paymentMethodRepository;

	@Autowired
	private IProductConfigRepository productConfigRepository;

	@Autowired
	private IPromoService promoService;

	@Autowired
	IInternetContentListingRepository internetContentListingRepository;

	@Override
	public Page<InternetContentCodeListing> getIccList(LocalDate date, Pageable pageable, HttpServletRequest req) {
		OffsetDateTime osdt = OffsetDateTime.now().withYear(date.getYear()).withMonth(date.getMonthValue())
				.withDayOfMonth(date.getDayOfMonth()).withHour(0).withMinute(0).withSecond(0);
//		Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
//		Page<InternetContentCodeListing> iccList = internetContentListingRepository.getInternetCodeListingByPage(osdt,
//		profileInfoId, pageable);

		 Page<InternetContentCodeListing> iccList =
				 internetContentListingRepository.getInternetCodeListingByPage(osdt, pageable);
		
		 List <InternetContentCodeListingJson> js= transform(iccList.getContent(), req); //alters contents of iccList
//		 IccListPage returnListing=new IccListPage(iccList.getContent(), req);
		 IccListPage returnListing=new IccListPage(js, req);
		return returnListing;
	}
	
	private List<InternetContentCodeListingJson> transform(List<InternetContentCodeListing> iccList, HttpServletRequest req) {
		List<String> genres=null; 
		List <InternetContentCodeListingJson>retList=new ArrayList<InternetContentCodeListingJson>();
		
		String uri=req.getRequestURL().toString(),
				pathPart=req.getServletPath();
		String serverPart=uri.replaceAll(pathPart, "");
		
		for (InternetContentCodeListing e:iccList) {
			//update genre
			String genrestr=e.getGenreStr();
			if (genrestr!=null) {
				genres=Arrays.asList(genrestr.split(","));
				e.setGenreStr(null);
				e.setGenre(genres);
			}
			
			//append servername to summaryimagepath
			String summaryImagePath=e.getSummaryImagePath();
			if (summaryImagePath!=null)
				e.setSummaryImagePath(serverPart+summaryImagePath);
			InternetContentCodeListingJson iccjs=new InternetContentCodeListingJson(e);
			retList.add(iccjs);
		}
		return retList;
	}
  @Override
  public List<ProductConfigJson> getProductConfig(LocalDate updatedDate) {
      List <ProductConfigJson>productConfigItems=new ArrayList<>();
      List<ProductConfig> updatedShows=productConfigRepository.getUpdatedShows(updatedDate);
      for (ProductConfig e:updatedShows) {
          ProductConfigJson i=new ProductConfigJson(e);
          List<String> eventTypes=productConfigRepository.getEventTypes(e.getProductId(), e.getVenueId(), e.getVenueLayoutConfig_ID());
          if (eventTypes!=null)
              i.setProductType(eventTypes);
          productConfigItems.add(i);
      }
      
      return productConfigItems;
  }
  
  @Override
  public List<InternetContentProducts> getShowTimings(String contentCode, String startDate,
      int numOfDays, String promoCode, int promoState) {
    List<InternetContentProducts> products = null;
    LocalDate date = null;
    // default to sysdate
    if (StringUtils.isNotEmpty(startDate)) {
      date = LocalDate.parse(startDate, DateTimeFormatter.BASIC_ISO_DATE);
    }
    LocalDate endDate = null;
    if (date != null) {
      endDate = date.plusDays(numOfDays);
    }
    // promo start
    switch (promoState) {
      case PromoConstants.PROMO_CODE_VALID:
        // promo flow
        Map<Long, Boolean> avaiProducts =
            promoService.getProductAvailability(contentCode, promoCode);
        // only product tied to promo code
        products = showTimingRepository.getProductsByInternetContentCode(contentCode, date, endDate,
            avaiProducts.keySet());

        for (InternetContentProducts product : products) {
          Boolean isAvai = avaiProducts.get(product.getProductid());
          int status = product.getAvailablityStatus();
          if (status != InternetContentConstants.SHOW_CANCELLED.getValue()
              && status != InternetContentConstants.SHOW_POSTPONED.getValue()
              && status != InternetContentConstants.SHOW_RECONFIG.getValue()
              && (isAvai == null || !isAvai.booleanValue())) {
            product.setAvailablityStatus(InternetContentConstants.SOLD_OUT.getValue());
          } // else -> leave status as it is
        }
        break;

      default:
        products =
            showTimingRepository.getProductsByInternetContentCode(contentCode, date, endDate, null);
        // exclusive flow
        break;
    }

    return products;
  }
  
  
  @Override
  public List<InternetContentProducts> getProductsByInternetContentCode(String internetContentCode, LocalDate startDate, int numOfDays, Collection<Long> productIds) {
    LocalDate endDate = null;
    if (startDate != null) {
      endDate = startDate.plusDays(numOfDays);
    }
    return showTimingRepository.
        getProductsByInternetContentCode(internetContentCode, startDate, endDate, productIds);
  }
  
  @Override
  public List<InternetContentProducts> getProductsByInternetContentCode(String internetContentCode, LocalDate startDate, int numOfDays) {
    return getProductsByInternetContentCode(internetContentCode, startDate, numOfDays, null);
  }

  @Override
  public List<SeatmapOverviewProducts> getSeatmapOverview(Long productId, String promoCode)
      throws Exception {

    if (promoCode != null
        && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      // for now, handle the case for promo only
      Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
      List<DeliveryMethod> deliveryMethods = deliveryMethodRepository
          .getCommonDeliveryMethod(productId, false, profileInfoId, promoCode);
      List<PaymentMethod> paymentMethods =
          paymentMethodRepository.getCommonPaymentMethod(productId, profileInfoId, promoCode);

      // Check common delivery method and common payment method
      if (deliveryMethods.isEmpty() || paymentMethods.isEmpty()) {
        throw new Exception(message.getMessage("error.icc.common.method", null,
            LocaleContextHolder.getLocale()));
      }
    }
    // else: apply checks for delivery methods later
    return seatmapOverviewProductsRepository.getSeatmapOverview(productId);
  }

  @Override
  public List<SeatmapOverviewProducts> getSeatmapOverview(List<Long> productId) {
    return seatmapOverviewProductsRepository.getSeatmapOverview(productId);
  } 
  
  @Override
  public List<RetrievePriceTypeView> getPriceClassList(Long productId, Long priceCatId,
                                                       Long userInfoId, Long profileInfoId,
                                                       String promoCode, String packageClassType) {
    boolean exclusive = true;
    Set<Long> priceClassIds = null;
    if (promoCode != null
        && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      exclusive = false;
      priceClassIds = promoService.getPromoPriceClassIds(productId, promoCode);
    } else {
      priceClassIds = promoService.getPromoPriceClassIds(productId);
    }
    return priceClassRepository.getPriceClassList(productId, priceCatId, priceClassIds, userInfoId,
        profileInfoId, packageClassType, exclusive);
  }
  
  @Override
  public List<SeatmapAvailablityProducts> getSeatmapAvailability(Long productId, String promoCode) {
    List<PromoCodeDTO> promos = new ArrayList<>();
    boolean isPromo = false;
    if (promoCode != null && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      isPromo = true;
      // promo
      promos = promoService.getPromoCodes(productId, promoCode);
    } else {
      // non-promo
      promos = promoService.getPromoCodes(productId);
    }

    Set<Long> holdCodeIds = new HashSet<>();
    Set<Long> priceClassIds = new HashSet<>();
    
    promos.forEach(p -> {
      priceClassIds.add(p.getPriceClassId());
      if (null != p.getHoldCodeId()) {
        holdCodeIds.add(p.getHoldCodeId());
      }
    });
    
    
    List<SeatmapAvailablityProducts> seatMaps = seatmapAvailabilityRepository.getSeatmapAvailability(productId, priceClassIds, holdCodeIds, !isPromo);
    
    
    return seatMaps;
  }

  @Override
  public List<PriceTableInfo> getSeatmapPriceTable(Long productId, String promoCode, String isPackageClass) {

    Set<Long> priceCatIds = null;
    Set<Long> holdCodeIds = new HashSet<>();
    Set<Long> priceClassIds = new HashSet<>();
    boolean exclusive = true;
    List<PromoCodeDTO> promos = new ArrayList<>();
    
    if (promoCode != null && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      // promo
      promos = promoService.getPromoCodes(productId, promoCode);
      exclusive = false;
    } else {
      // non-promo
      promos = promoService.getPromoCodes(productId);
    }
    
    promos.forEach(p -> {
      priceClassIds.add(p.getPriceClassId());
      if (null != p.getHoldCodeId()) {
        holdCodeIds.add(p.getHoldCodeId());
      }
    });
//    priceCatIds = promoService.getSectionAvailability(productId, priceClassIds, holdCodeIds, !exclusive).entrySet().stream()
//        .filter(e -> e.getValue()).map(e -> e.getKey().getLeft()).collect(Collectors.toSet());
    
    priceCatIds = seatmapAvailabilityRepository
        .getSeatmapAvailability(productId, priceClassIds, holdCodeIds, exclusive).stream()
        .map(SeatmapAvailablityProducts::getPriceCatId).collect(Collectors.toSet());
    
    List<PriceTableInfo> priceTable = priceTableInfoRepository.getPriceInfoTable(productId,
        isPackageClass, priceClassIds, priceCatIds, exclusive);
    
    Collections.sort(priceTable,
        Comparator.comparing(PriceTableInfo::getOrdering)
            .thenComparing(PriceTableInfo::getPriceclasscode)
            .thenComparing(PriceTableInfo::getPricecategorynumber));
    logger.info("getSeatmapPriceTable: productId=" + productId + ", promocode=" + promoCode
        + ", isPackageClass=" + isPackageClass + ", priceTables=" + priceTable);
    return priceTable;
  }

  @Override
  public List<DetailSeatmapProducts> getDetailSeatmap(long productId, long priceCatId,
      long seatSectionId, Set<Long> holdIds) {

    Set<Long> seatIds = null;
    if (CollectionUtils.isNotEmpty(holdIds)) {
      // promo
      seatIds = promoService.getAvailableSeats(productId, priceCatId, seatSectionId, holdIds);
      if (CollectionUtils.isEmpty(seatIds)) {
        logger.info("DetailSeatmap is empty for prouctId=" + productId + ", priceCatId="
            + priceCatId + ", seatSectionId=" + seatSectionId + " (holdcodes=)" + holdIds);
        return new ArrayList<>();
      }
    }

    return detailSeatmapRepository.getDetailSeatmap(productId, priceCatId, seatIds, false);
  }
  
  @Override
  @Transactional
  public List<SalesSeatInventory> reserveSeats(Long productId, List<Long> reserveSeatList) {
    List<SalesSeatInventory> seatInvList =
        seatInventoryRepository.findAllByProductIdAndSeatInvIds(productId, reserveSeatList);
    Map<Integer, List<SalesSeatInventory>> seatInventoryMap =
        seatInvList.stream().collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
    for (Entry<Integer, List<SalesSeatInventory>> entry : seatInventoryMap.entrySet()) {
      if (InventoryConstants.SALES_STATUS_AVAILABLE.getValue().equals(entry.getKey())) {
        return updateSalesSeatInventory(productId, reserveSeatList, entry.getValue(),
            InventoryConstants.SALES_STATUS_AVAILABLE);
      } else if (InventoryConstants.SALES_STATUS_HOLD.getValue().equals(entry.getKey())) {
        return updateSalesSeatInventory(productId, reserveSeatList, entry.getValue(),
            InventoryConstants.SALES_STATUS_HOLD);
      }
    }

    return new ArrayList<>();
  }


  @Override
  @Transactional
  public List<SalesSeatInventory> releaseSeats(Long productId, List<Long> releaseSeatList) {
    List<SalesSeatInventory> seatInvList =
        seatInventoryRepository.findAllByProductIdAndSeatInvIds(productId, releaseSeatList);
    Map<Integer, List<SalesSeatInventory>> seatInventoryMap =
        seatInvList.stream().collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
    for (Entry<Integer, List<SalesSeatInventory>> entry : seatInventoryMap.entrySet()) {
      if (InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(entry.getKey())) {
        return updateSalesSeatInventory(productId, releaseSeatList, entry.getValue(),
            InventoryConstants.SALES_STATUS_RESERVED);
      } else if (InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue().equals(entry.getKey())) {
        return updateSalesSeatInventory(productId, releaseSeatList, entry.getValue(),
            InventoryConstants.SALES_STATUS_HOLD_RESERVED);
      }
    }
    return new ArrayList<>();
  }

  private List<SalesSeatInventory> updateSalesSeatInventory(Long productId, List<Long> seatIdList,
      List<SalesSeatInventory> seatInventoryList, InventoryConstants salesStatus) {
    List<SalesSeatInventory> returnList = new ArrayList<>();

    if (!seatInventoryList.isEmpty() && seatIdList.size() == seatInventoryList.size()) {
      seatInventoryList.forEach(seat -> {
        if (salesStatus.getValue().equals(seat.getSeatsalesstatus())) {
          switch (salesStatus) {
            case SALES_STATUS_RESERVED:
              setInventoryToAvailable(seat);
              break;
            case SALES_STATUS_AVAILABLE:
              setInventoryToReserved(seat);
              break;
            case SALES_STATUS_HOLD:
              setInventoryToReserveHold(seat);
              break;
            case SALES_STATUS_HOLD_RESERVED:
              setInventoryToHold(seat);
              break;
          }
        }
      });

      Iterable<SalesSeatInventory> returnIterable =
          seatInventoryRepository.save(seatInventoryList);
      returnIterable.forEach(returnList::add);
    }
    return returnList;
  }

  private SalesSeatInventory setInventoryToReserved(SalesSeatInventory salesSeatInventory) {
    // set timeout date
    OffsetDateTime expiryDateTime =
        OffsetDateTime.now().plusMinutes(TenantContextHolder.getTenant().getSeatInventoryExpiry());

    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_RESERVED.getValue());
    salesSeatInventory.setReserveexpirydate(expiryDateTime);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());

    return salesSeatInventory;
  }

  private SalesSeatInventory setInventoryToAvailable(SalesSeatInventory salesSeatInventory) {
    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_AVAILABLE.getValue());
    salesSeatInventory.setReserveexpirydate(null);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());

    return salesSeatInventory;
  }
  
  private SalesSeatInventory setInventoryToReserveHold(SalesSeatInventory salesSeatInventory) {
    OffsetDateTime expiryDateTime =
        OffsetDateTime.now().plusMinutes(TenantContextHolder.getTenant().getSeatInventoryExpiry());
    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue());
    salesSeatInventory.setReserveexpirydate(expiryDateTime);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());

    return salesSeatInventory;
  }
  
  private SalesSeatInventory setInventoryToHold(SalesSeatInventory salesSeatInventory) {
    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_HOLD.getValue());
    salesSeatInventory.setReserveexpirydate(null);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());

    return salesSeatInventory;
  }

  /**
   * Get list section that have type is GA.
   * @return List Section GA
   */
  @Override
  public List<RetrieveSectionGaView> getSectionGaList(Long productId, Long profileInfoId,
      String promoCode) throws Exception {

    List<PromoCodeDTO> promos = null;
    boolean exclusive = true;
    if (promoCode != null
        && PromoConstants.PROMO_CODE_VALID == promoService.getPromoState(productId, promoCode)) {
      // promo
      List<DeliveryMethod> deliveryMethods = deliveryMethodRepository
          .getCommonDeliveryMethod(productId, true, profileInfoId, promoCode);
      List<PaymentMethod> paymentMethods =
          paymentMethodRepository.getCommonPaymentMethod(productId, profileInfoId, promoCode);

      // Check common delivery method and common payment method
      if (deliveryMethods.isEmpty() || paymentMethods.isEmpty()) {
        logger.error("error.icc.common.method: productId=" + productId + ", profileInfoId=" + profileInfoId + ", promoCode=" + promoCode);
        throw new Exception(
            message.getMessage("error.icc.common.method", null, LocaleContextHolder.getLocale()));
      }

      exclusive = false;
      promos = promoService.getPromoCodes(productId, promoCode);
    } else {
      // exclusive
      promos = promoService.getPromoCodes(productId);
    }

    List<RetrieveSectionGaView> GASections =
        sectionGaRepository.getSectionGaList(productId, profileInfoId);
    List<Long> priceClassIds =
        promos.stream().map(PromoCodeDTO::getPriceClassId).collect(Collectors.toList());
    List<Long> holdCodeIds =
        promos.stream().map(PromoCodeDTO::getHoldCodeId).collect(Collectors.toList());
    boolean holdcode = holdCodeIds != null && holdCodeIds.size() > 0;

    if (exclusive) {
      if (holdCodeIds.size() > 0) {
        GASections.forEach(p -> {
          // if seat is not in the hold code or is not configured for any promo -> available
          if (!priceClassIds.contains(p.getPriceClassId()) && holdcode
              && !holdCodeIds.contains(p.getHoldCodeId())) {
            p.setSeatSectionAvailability(InventoryConstants.SEAT_SECTION_AVAILABLE.getValue());
          } // else -> let it be (???)
        });
      } else {
        // no holdcode
        GASections.forEach(p -> {
          // if seat is not configured for any promo -> available
          if (!priceClassIds.contains(p.getPriceClassId())) {
            p.setSeatSectionAvailability(InventoryConstants.SEAT_SECTION_AVAILABLE.getValue());
          } // else -> let it be (???)
        });
      }
    } else {
      // promo flow
      GASections.forEach(p -> {
        if (priceClassIds.contains(p.getPriceClassId())) {
          if (holdcode && holdCodeIds.contains(p.getHoldCodeId())) {
            p.setSeatSectionAvailability(InventoryConstants.SEAT_SECTION_AVAILABLE.getValue());
          } else if (holdcode) {
            // seat is in promo but is not held in the the hold code list -> unavailable
            p.setSeatSectionAvailability(InventoryConstants.SEAT_SECTION_UNAVAILABLE.getValue());
          }
        } else {
          // seat is not in promo list -> unavailable for promo flow
          p.setSeatSectionAvailability(InventoryConstants.SEAT_SECTION_UNAVAILABLE.getValue());
        }
      });
    }

    // Filter duplicate elements
    Map<String, RetrieveSectionGaView> filterMap = new LinkedHashMap<>();
    GASections.forEach(p -> {
      String key = "" + p.getSeatSectionId() + p.getSeatSectionAlias() + p.getSeatSectionType()
          + p.getPriceCategoryNum() + p.getPriceCategoryId() + p.getPrice();
      if (!filterMap.containsKey(key)
          || (filterMap.containsKey(key) && !InventoryConstants.SEAT_SECTION_UNAVAILABLE.getValue()
              .equals(p.getSeatSectionAvailability()))) {
        filterMap.put(key, p);
      }
    });

    return filterMap.entrySet().stream().map(m -> m.getValue()).collect(Collectors.toList());
  }
 
  @Override
  public String getOverviewImage(Long productId) throws IOException {
    return imageCreationService
        .getOverviewImage(null, productId, false, true,
            FileInfoUtil.getFileDirectory(FileInfoUtil.SAVE_EVENT_FOLDER_NAME, true, true));
  }

  @Override
  public String getOverviewImage(List<Long> seatIDs, Long productId) throws IOException {
    return imageCreationService
        .getOverviewImage(seatIDs, productId, false, false,
            FileInfoUtil.getFileDirectory(FileInfoUtil.SAVE_EVENT_FOLDER_NAME, true, true));
  }

  @Override
  public String getDetailImages(List<Long> seatIDs, Long productId)
      throws IOException {
    return imageCreationService
        .getDetailImages(seatIDs, productId,
            FileInfoUtil.getFileDirectory(FileInfoUtil.SAVE_EVENT_FOLDER_NAME, true, true));
  }

  @Override
    public List<PriceTableInfo> getSeatmapPriceTableProducts(List<Long> productList, String promoCode,
            String isPackageClass) {
        return priceTableInfoRepository.getPriceInfoTableProducts(productList, promoCode, isPackageClass);
    }

  @Override
  public List<AddonMapping> findAddOnProductsByEventProductIds(List<Long> eventProductIds) {
    List<Long> orgList = addOnProductRepository.findOrganizationIdForProducts(eventProductIds);
    List<AddonMapping> prdAddonList = null;
    prdAddonList = addOnProductRepository.findAddOnProductsByEventProductIds(eventProductIds);
    List<AddonMapping> orgAddonList = null;
    if (orgList != null && !orgList.isEmpty()) {
      orgAddonList = addOnProductRepository.findAddOnProductsByOrganizationId(orgList);
    }
    if (prdAddonList == null) {
      prdAddonList = new ArrayList<AddonMapping>();
    }
    
    logger.info("Product addons configured for eventProductIds=" + eventProductIds + ": " + prdAddonList);
    if (orgAddonList != null) {
      prdAddonList.addAll(orgAddonList);
      logger.info("Organization addons configured for orgList=" + orgList + ": " + orgAddonList);
    }

    return prdAddonList;
  }

}

package com.stixcloud.product.event;

import com.stixcloud.domain.AddonMapping;
import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.domain.InternetContentCodeListing;
import com.stixcloud.domain.InternetContentProducts;
import com.stixcloud.domain.PriceTableInfo;
import com.stixcloud.domain.RetrievePriceTypeView;
import com.stixcloud.domain.RetrieveSectionGaView;
import com.stixcloud.domain.SalesSeatInventory;
import com.stixcloud.domain.SeatmapAvailablityProducts;
import com.stixcloud.domain.SeatmapOverviewProducts;
import com.stixcloud.product.api.json.ProductConfigJson;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by chongye on 01-Aug-16.
 */
public interface IEventService {
  List<InternetContentProducts> getProductsByInternetContentCode(String internetContentCode, LocalDate date, int numOfDays);
  List<InternetContentProducts> getProductsByInternetContentCode(String internetContentCode, LocalDate date, int numOfDays, Collection<Long> productIds);

  List<SeatmapOverviewProducts> getSeatmapOverview(Long productId, String promoCode) throws Exception;

  List<RetrievePriceTypeView> getPriceClassList(Long productId, Long priceCatId, Long userInfoId,
                                                Long profileInfoId, String promoCode, String packageClassType);

  List<SeatmapAvailablityProducts> getSeatmapAvailability(Long productId, String promoCode);

  List<PriceTableInfo> getSeatmapPriceTable(Long productId, String promoCode, String isPackageClass);

  public List<DetailSeatmapProducts> getDetailSeatmap(long productId, long priceCatId,
      long seatSectionId, Set<Long> holdIds);

  List<SalesSeatInventory> reserveSeats(Long productid, List<Long> reserveSeatList);

  List<SalesSeatInventory> releaseSeats(Long productid, List<Long> releaseSeatList);

  List<RetrieveSectionGaView> getSectionGaList(Long productId, Long profileInfoId,
      String promoCode) throws Exception;

  String getOverviewImage(Long productId) throws IOException;

  String getOverviewImage(List<Long> seatIDs, Long productId) throws IOException;

  String getDetailImages(List<Long> seatIDs, Long productId) throws IOException;

  List<PriceTableInfo> getSeatmapPriceTableProducts(List<Long> produtList, String promoCode, String isPackageClass);

  List<AddonMapping> findAddOnProductsByEventProductIds(List<Long> eventProductIds);
  
  List<SeatmapOverviewProducts> getSeatmapOverview(List<Long> productId);
  
  List<ProductConfigJson> getProductConfig(LocalDate updatedDate);
  
  List<InternetContentProducts> getShowTimings(String contentCode, String startDate, int numOfDays,
      String promoCode, int promoState);
  Page<InternetContentCodeListing> getIccList(LocalDate date, Pageable pageable, HttpServletRequest req);
  
}

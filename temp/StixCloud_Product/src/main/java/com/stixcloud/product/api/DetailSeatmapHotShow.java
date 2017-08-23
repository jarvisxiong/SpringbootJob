package com.stixcloud.product.api;

import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.product.util.FileInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class DetailSeatmapHotShow implements IDetailSeatmap {

  private static final int NUMBER_OF_SETS = 1;

  @Override
  public DetailSeatmap getDetailSeatmap(List<DetailSeatmapProducts> detailSeatmapProductsList,
                                        long productId, long priceCatId, long seatSectionId,
                                        int quantity, boolean isHold) {

    SeatFilterByRank
        seatFilter =
        new SeatFilterByRank(detailSeatmapProductsList, NUMBER_OF_SETS, quantity, isHold);
    List<Seat> seatsAvailableList = new ArrayList<>();

    List<SeatUnavailable> seatUnavailable = new ArrayList<>();

    String seatmapOverviewFileLocation = "";
    if (detailSeatmapProductsList.get(0).getSeatMappingId() != null
        && detailSeatmapProductsList.get(0).getDetailImage() != null) {
      seatmapOverviewFileLocation = FileInfoUtil.getSeatmapOverviewFileLocation(
          detailSeatmapProductsList.get(0).getSeatMappingId(),
          detailSeatmapProductsList.get(0).getDetailImage(),
          true
      );
    }

    return new DetailSeatmap(
        detailSeatmapProductsList.get(0).getImageAvailable(),
        detailSeatmapProductsList.get(0).getViewFromSeatAvailable(),
        "IO",
        seatmapOverviewFileLocation,
        "/SisticWebApp/images",
        seatFilter.filter(),
        seatsAvailableList,
        seatUnavailable,
        detailSeatmapProductsList.get(0).getSeatSectionAlias()
    );
  }
}

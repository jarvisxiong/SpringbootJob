package com.stixcloud.product.api;

import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.product.util.FileInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class DetailSeatmapBestAvailable implements IDetailSeatmap {

  @Override
  public DetailSeatmap getDetailSeatmap(List<DetailSeatmapProducts> detailSeatmapProductsList,
                                        long productId, long priceCatId, long seatSectionId,
                                        int quantity, boolean isHold) {

//    if product has interactive seatmap, offer 2 sets of seat, else just offer 1 set of seats
    int numberOfSets = detailSeatmapProductsList.get(0).isInteractive() ? 2 : 1;

    SeatFilterByRank
        seatFilter =
        new SeatFilterByRank(detailSeatmapProductsList, numberOfSets, quantity, isHold);
    List<Seat> seatsAvailableList = new ArrayList<>();

    List<SeatUnavailable> seatUnavailable = new ArrayList<>();

    return new DetailSeatmap(
        detailSeatmapProductsList.get(0).getImageAvailable(),
        detailSeatmapProductsList.get(0).getViewFromSeatAvailable(),
        "IO",
        FileInfoUtil.getSeatmapOverviewFileLocation(
            detailSeatmapProductsList.get(0).getSeatMappingId(),
            detailSeatmapProductsList.get(0).getDetailImage(),
            true
        ),
        "/SisticWebApp/images",
        seatFilter.filter(),
        seatsAvailableList,
        seatUnavailable,
        detailSeatmapProductsList.get(0).getSeatSectionAlias()
    );

  }
}

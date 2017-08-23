package com.stixcloud.product.api;

import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.product.util.FileInfoUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetailSeatmapSelfPick implements IDetailSeatmap {

  @Override
  public DetailSeatmap getDetailSeatmap(List<DetailSeatmapProducts> detailSeatmapProductsList,
                                        long productId, long priceCatId, long seatSectionId,
                                        int quantity, boolean isHold) {
    List<SetsReservedList> setsReservedListList = new ArrayList<>();
    List<Seat> seatsAvailableList = detailSeatmapProductsList
        .stream()
        .filter(dsm -> dsm.isInteractive() && InventoryConstants.TICKETABLE_TRUE.getValue() == dsm.getTicketable()
            && (isHold ? dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_HOLD.getValue()
                : dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_AVAILABLE.getValue()))
        .sorted((dsm1, dsm2) -> dsm1.getInventoryId().compareTo(dsm2.getInventoryId()))
        .map(seat -> new Seat(
            seat.getInventoryId(),
            seat.getSeatRowAlias(),
            seat.getSeatAlias(),
            seat.getSeatType(),
            seat.getTopLeftCoordinates(),
            seat.getSeatAngle(),
            seat.getCoordinates()))
        .collect(Collectors.toList());

    List<SeatUnavailable> seatUnavailable = detailSeatmapProductsList
        .stream()
        //.filter(dsm -> dsm.isInteractive() && dsm.getSeatSalesStatus() != 0)
        .filter(dsm -> (dsm.isInteractive()
            && (isHold ? dsm.getSeatSalesStatus() != InventoryConstants.SALES_STATUS_HOLD.getValue()
                : dsm.getSeatSalesStatus() != InventoryConstants.SALES_STATUS_AVAILABLE.getValue()))
            || InventoryConstants.TICKETABLE_TRUE.getValue() != dsm.getTicketable())
        .sorted((dsm1, dsm2) -> dsm1.getInventoryId().compareTo(dsm2.getInventoryId()))
        .map(seat -> new SeatUnavailable(
            seat.getTopLeftCoordinates(),
            seat.getCoordinates(),
            seat.getSeatAngle()))
        .collect(Collectors.toList());

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
        setsReservedListList,
        seatsAvailableList,
        seatUnavailable,
        detailSeatmapProductsList.get(0).getSeatSectionAlias()
    );
  }
}

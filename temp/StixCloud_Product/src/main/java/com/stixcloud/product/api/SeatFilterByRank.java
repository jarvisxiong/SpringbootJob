package com.stixcloud.product.api;

import com.stixcloud.domain.DetailSeatmapProducts;
import com.stixcloud.product.constant.InventoryConstants;
import com.stixcloud.product.event.IDetailSeatmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Configurable
public class SeatFilterByRank implements ISeatFilter<SetsReservedList> {

  @Autowired
  IDetailSeatmapRepository detailSeatmapRepository;

  private List<DetailSeatmapProducts> detailSeatmapProductsList;
  private int numberOfSets;
  private int numberOfSeatsPerSet;
  private boolean isHold;

  public SeatFilterByRank(List<DetailSeatmapProducts> detailSeatmapProductsList, int numberOfSets,
                          int numberOfSeatsPerSet, boolean isHold) {
    this.detailSeatmapProductsList = detailSeatmapProductsList;
    this.numberOfSets = numberOfSets;
    this.numberOfSeatsPerSet = numberOfSeatsPerSet;
    this.isHold = isHold;
  }

  @Override
  @Transactional
  public List<SetsReservedList> filter() {

    // list to store all setsReserved
    List<SetsReservedList> setsReservedList = new ArrayList<>();

    // list to store the selected seats for each iteration
    List<Seat> selectedSeatsList = new ArrayList<>();

    // convert DetailSeatmapProducts to Seat and sort by rank
    List<Seat> setsReservedListByRank = detailSeatmapProductsList
        .stream()
        .filter(dsm -> InventoryConstants.TICKETABLE_TRUE.getValue() == dsm.getTicketable()
            && (isHold ? dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_HOLD.getValue()
                : dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_AVAILABLE.getValue()))
        .sorted(Comparator.comparing(DetailSeatmapProducts::getRank))
        .map(seat -> new Seat(
            seat.getInventoryId(),
            seat.getSeatRowAlias(),
            seat.getSeatAlias(),
            seat.getSeatType(),
            seat.getTopLeftCoordinates(),
            seat.getSeatAngle(),
            seat.getCoordinates()))
        .collect(Collectors.toList());

    List<Seat> seatsToCompare = new ArrayList<>(setsReservedListByRank);

    // if number of sets is more than total seats to compare divided by number of seats requested,
    // we get only the max available set that is available
    numberOfSets = numberOfSets > (seatsToCompare.size() / numberOfSeatsPerSet)
            ? (seatsToCompare.size() / numberOfSeatsPerSet) : numberOfSets;

    while (setsReservedList.size() < numberOfSets && !setsReservedListByRank.isEmpty()) {

      // get first ranked seat and remove it from the list so that we do not check it again.
      selectedSeatsList.add(setsReservedListByRank.get(0));
      setsReservedListByRank.remove(0);

      if (numberOfSeatsPerSet > 1) {

//        get seats in running sequence to the first rank seat
        for (int i = 1; i < numberOfSeatsPerSet; i++) {

          for (Seat currentSeat : seatsToCompare) {

//            if seat belongs to the same row and is the next seat
            if (currentSeat.getSeatRowAlias().equals(selectedSeatsList.get(0).getSeatRowAlias())
                && Integer.parseInt(currentSeat.getSeatAlias())
                == Integer.parseInt(selectedSeatsList.get(0).getSeatAlias()) + i) {
              selectedSeatsList.add(currentSeat);

//              break loop when number of seats per set is met
              if (selectedSeatsList.size() == numberOfSeatsPerSet) {
                break;
              }
            }
          }

        }

      }

      if (selectedSeatsList.size() == numberOfSeatsPerSet) {
        SetsReservedList seatList = new SetsReservedList();
        seatList.setSetsReserved(selectedSeatsList);
        setsReservedList.add(seatList);
        setsReservedListByRank.removeAll(selectedSeatsList);
        seatsToCompare.removeAll(selectedSeatsList);
      }

      selectedSeatsList = new ArrayList<>();


    }

    // if there are still remaining sets but no seats next to each other,
    // we restart the search and return the seats based on ranking

    if ((setsReservedList.size() < numberOfSets)) {

      // get all the seats by rank again
      setsReservedListByRank = detailSeatmapProductsList
              .stream()
              .filter(
                  dsm -> InventoryConstants.TICKETABLE_TRUE.getValue() == dsm.getTicketable()
                  && (isHold? dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_HOLD.getValue()
                      : dsm.getSeatSalesStatus() == InventoryConstants.SALES_STATUS_AVAILABLE.getValue()))
              .sorted(Comparator.comparing(DetailSeatmapProducts::getRank))
              .map(seat -> new Seat(
                      seat.getInventoryId(),
                      seat.getSeatRowAlias(),
                      seat.getSeatAlias(),
                      seat.getSeatType(),
                      seat.getTopLeftCoordinates(),
                      seat.getSeatAngle(),
                      seat.getCoordinates()))
              .collect(Collectors.toList());

      // remove all seats which are already reserved
      setsReservedListByRank.removeAll(selectedSeatsList);

      for (int i = setsReservedList.size(); i < numberOfSets; i++) {

        for (int j = 0; j < numberOfSeatsPerSet; j++) {
          selectedSeatsList.add(seatsToCompare.get(j));
        }

        SetsReservedList seatList = new SetsReservedList();
        seatList.setSetsReserved(selectedSeatsList);
        setsReservedList.add(seatList);
        seatsToCompare.removeAll(selectedSeatsList);
        selectedSeatsList = new ArrayList<>();

      }
    }

    return setsReservedList;
  }
}

package com.stixcloud.product.api;

import org.springframework.stereotype.Component;

@Component
public class DetailSeatmapFactory {

  public IDetailSeatmap getSeatSelectionType(String mode) {

    if (SeatSelectionType.SELF_PICK.getSeatSelectionValue().equals(mode)) {
      return new DetailSeatmapSelfPick();
    } else if (SeatSelectionType.BEST_AVAILABLE.getSeatSelectionValue().equals(mode)) {
      return new DetailSeatmapBestAvailable();
    } else if (SeatSelectionType.HOT_SHOW.getSeatSelectionValue().equals(mode)) {
      return new DetailSeatmapHotShow();
    } else {
      return new NoDetailSeatmap();
    }
  }

}

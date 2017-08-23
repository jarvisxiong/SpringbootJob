package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.tools.util.ColorScheme;
import com.stixcloud.seatmap.dto.SeatModel;

import java.awt.Graphics2D;
import java.util.List;

/**
 * The detail view renderer render an detail image for Sales purposes, like BA
 * or Internet patron. Only seats numbers are rendered. For those seats selected
 * by patron, they will be marked "X"
 * @author wai hong chan
 * @version 2.0
 */
public class DetailViewSalesImageRenderer extends DefaultDetailViewImageRenderer {

  /**
   * Instantiates a new detail view sales image renderer.
   * @param overviewHelper the overview helper
   * @param helper the helper
   */
  public DetailViewSalesImageRenderer(ImageCreatorHelper overviewHelper,
                                      ImageCreatorDetailViewHelper helper) {
    super(overviewHelper, helper);
  }

  @Override
  public void renderSeat(List<Long> seatInvIds, Graphics2D g2d, SeatModel seatModel) {
    boolean isSelectedSeat = isSelected(seatInvIds,
        seatModel.getSeatInvID());

    int xPos = seatModel.getXPosition() - detailviewHelper.getXOffset();
    int yPos = seatModel.getYPosition() - detailviewHelper.getYOffset();

    if (xPos < 1) {
      xPos = 10;
    }

    if (yPos < 1) {
      yPos = 10;
    }

    if (isSelectedSeat) {
      g2d.setColor(overviewHelper.getColorByCategory(seatModel
          .getPriceCatID()));
      g2d.fillArc(xPos, yPos, DETAIL_SEAT_WIDTH - 1, DETAIL_SEAT_HEIGHT - 1, 0, 360);
      g2d.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
      g2d.drawArc(xPos, yPos, DETAIL_SEAT_WIDTH - 1, DETAIL_SEAT_HEIGHT - 1, 0, 360);
      g2d.drawString(
          seatModel.getDisplayValue(),
          getMidX(xPos, DETAIL_SEAT_WIDTH, g2d, seatModel.getDisplayValue()),
          getMidY(yPos, DETAIL_SEAT_HEIGHT));
    } else {
      g2d.setColor(overviewHelper.getColorByCategory(seatModel
          .getPriceCatID()));
      g2d.drawArc(xPos, yPos, DETAIL_SEAT_WIDTH - 1, DETAIL_SEAT_HEIGHT - 1, 0, 360);
    }

  }

  /**
   * Check if the particular seat inventory id is found in the array of seat
   * inventory ids.
   * @param seatInvIds the array of ids. These ids are the seats selected by the patron to be
   * purchased
   * @param seatInvId a id of a seat in the block view. This seat seat may be the selected seat
   * @return true, if is selected
   */
  private boolean isSelected(List<Long> seatInvIds, Long seatInvId) {
    return seatInvIds.contains(seatInvId);
  }

}

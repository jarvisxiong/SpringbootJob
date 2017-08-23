package com.stixcloud.seatmap.imagerenderer;


import com.sistic.ticketing.venue.lc.uimodel.DetailLabelComponentModel;

import java.awt.Graphics2D;
import java.util.List;

/**
 * Defines the methods for rendering a detailview of a seatmap layout
 * @author wai hong chan
 * @version 2.0
 */
public interface IDetailViewImageRenderer extends IImageRenderer {

  public static int DETAIL_SEAT_WIDTH = 18;
  public static int DETAIL_SEAT_HEIGHT = 18;

  /**
   * Render seat.
   * @param seatInvIds the seat inv ids
   * @param graph the graph
   * @param seatModel the seat model
   */
  public void renderSeat(List<Long> seatInvIds, Graphics2D graph,
                         com.stixcloud.seatmap.dto.SeatModel seatModel);

  /**
   * Render stage.
   * @param g the g
   * @param dm the dm
   */
  public void renderStage(Graphics2D g, DetailLabelComponentModel dm);

  /**
   * Render label.
   * @param g the g
   * @param dm the dm
   */
  public void renderLabel(Graphics2D g, DetailLabelComponentModel dm);

  /**
   * Sets the selected price cat num.
   * @param priceCatNumList the new selected price cat num
   */
  public void setSelectedPriceCatNum(List priceCatNumList);

}

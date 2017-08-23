package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.lc.uimodel.DetailLabelComponentModel;
import com.sistic.ticketing.venue.tools.util.AppletUIConfig;
import com.sistic.ticketing.venue.tools.util.ColorScheme;
import com.sistic.ticketing.venue.tools.util.StringI18NUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.List;

/**
 * This default renderer renders the image for SeatMap report:- 1. All hold
 * codes are rendered if any (dependant on the user privilege) 2. All sold codes
 * are rendered if any (dependant on the user privilege) 3. All available seat
 * numbers are rendered
 * @author wai hong chan
 * @version 2.0
 */
public class DefaultDetailViewImageRenderer implements IDetailViewImageRenderer {

  protected ImageCreatorDetailViewHelper detailviewHelper;
  protected ImageCreatorHelper overviewHelper;
  private AffineTransform verticalTransform = new AffineTransform();

  /**
   * Instantiates a new default detail view image renderer.
   * @param overviewHelper the overview helper
   * @param helper the helper
   */
  public DefaultDetailViewImageRenderer(ImageCreatorHelper overviewHelper,
                                        ImageCreatorDetailViewHelper helper) {
    this.detailviewHelper = helper;
    this.overviewHelper = overviewHelper;
    this.verticalTransform.rotate(Math.toRadians(-90));
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.stixcloud.service.event.seatmap.imagerenderer.IDetailViewImageRenderer
   * #setSelectedPriceCatNum(java.util.List)
   */
  public void setSelectedPriceCatNum(List priceCatNumList) {

  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.stixcloud.service.event.seatmap.imagerenderer.IDetailViewImageRenderer
   * #renderSeat(java.util.List, java.awt.Graphics2D,
   * com.sistic.ticketing.seatmap.lc.uimodel.SeatModel)
   */
  public void renderSeat(List<Long> seatInvIds, Graphics2D g,
                         com.stixcloud.seatmap.dto.SeatModel seatModel) {

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

		/*
                 * Arc2D.Double arc = new Arc2D.Double(seatModel.getXPosition() ,
		 * seatModel.getYPosition() , DETAIL_SEAT_WIDTH-1 , DETAIL_SEAT_HEIGHT-1
		 * , 0, 360, Arc2D.CHORD); graph.fill(arc);
		 */

    int xPos = seatModel.getXPosition() - detailviewHelper.getXOffset();
    int yPos = seatModel.getYPosition() - detailviewHelper.getYOffset();

    if (xPos < 1) {
      xPos = 10;
    }

    if (yPos < 1) {
      yPos = 10;
    }

    Color color = overviewHelper.getColorByCategory(seatModel
        .getPriceCatID());
    g.setColor(color);
    // fill the circle
    g.fillArc(xPos, yPos, DETAIL_SEAT_WIDTH - 1, DETAIL_SEAT_HEIGHT - 1, 0,
        360);

    // draw the outline, draw outline after fill since fill will
    // overlay the outline
    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    g.drawArc(xPos, yPos, DETAIL_SEAT_WIDTH - 1, DETAIL_SEAT_HEIGHT - 1, 0,
        360);

    // render sold code
    // if no sold code, render hold code,
    // if no hold code, render seat num
    // NOTE THAT THE SOLD CODES AND HOLD CODES ARE ALREADY
    // FILTER WHEN CALLING THE VenueBKBizDelegate TO RETRIEVE
    // THE DETAIL DATA
    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    if (seatModel.getPriceClassCode() != null
        && !seatModel.getPriceClassCode().equals("")) {
      // render sold code - top priority
      g.drawString(
          seatModel.getPriceClassCode(),
          getMidX(xPos, DETAIL_SEAT_WIDTH, g,
              seatModel.getPriceClassCode()),
          getMidY(yPos, DETAIL_SEAT_HEIGHT));
    } else if (seatModel.getHoldCode() != null
        && !seatModel.getHoldCode().equals("")) {
      // render hold code - 2nd priority
      g.drawString(
          seatModel.getHoldCode(),
          getMidX(xPos, DETAIL_SEAT_WIDTH, g, seatModel.getHoldCode()),
          getMidY(yPos, DETAIL_SEAT_HEIGHT));

    } else {
      // render seat num - if not else
      g.drawString(
          seatModel.getDisplayValue(),
          getMidX(xPos, DETAIL_SEAT_WIDTH, g,
              seatModel.getDisplayValue()),
          getMidY(yPos, DETAIL_SEAT_HEIGHT));

    }

  }

  /**
   * Gets the mid x.
   * @param startX the start x
   * @param width the width
   * @param g2d the g2d
   * @param displayValue the display value
   * @return the mid x
   */
  protected int getMidX(int startX, int width, Graphics2D g2d,
                        String displayValue) {

    int strWidth = g2d.getFontMetrics().stringWidth(displayValue);
    int xPos = startX + (width / 2) - (strWidth / 2);
    return xPos;

    // return (width/4)+startX; //1/4 plus x //old method - not accurate
  }

  /**
   * Gets the mid y.
   * @param startY the start y
   * @param height the height
   * @return the mid y
   */
  protected int getMidY(int startY, int height) {
    return ((height / 4) * 3) + startY; // 3/4 plus y
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.stixcloud.service.event.seatmap.imagerenderer.IDetailViewImageRenderer
   * #renderStage(java.awt.Graphics2D,
   * com.sistic.ticketing.seatmap.lc.uimodel.DetailLabelComponentModel)
   */
  public void renderStage(Graphics2D g, DetailLabelComponentModel dm) {
    int xPos = dm.getXPosition() - detailviewHelper.getXOffset();
    int yPos = dm.getYPosition() - detailviewHelper.getYOffset();

    if (xPos < 1) {
      xPos = 10;
    }

    // why value 7? because after trial and err,
    // we found that at y<=7, the "STAGE" will NOT render in full
    if (yPos <= 7) {
      yPos = 10;
    }

    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    g.drawString(STAGE, xPos, yPos);
  }

  /**
   * Gets the font.
   * @param dm the dm
   * @return the font
   */
  private Font getFont(DetailLabelComponentModel dm) {
    int fontSize = dm.getFontSize();
    if (fontSize < 1) {
      fontSize = 10;
    }

    Font font = new Font(AppletUIConfig.getFontName(), 0, fontSize);
    if (dm.isVertical()) {
      return font.deriveFont(verticalTransform);
    } else {
      return font;
    }
  }

  /*
   * (non-Javadoc)
   *
   * @see
   * com.stixcloud.service.event.seatmap.imagerenderer.IDetailViewImageRenderer
   * #renderLabel(java.awt.Graphics2D,
   * com.sistic.ticketing.seatmap.lc.uimodel.DetailLabelComponentModel)
   */
  public void renderLabel(Graphics2D g, DetailLabelComponentModel dm) {

    Font originalFont = g.getFont();
    g.setFont(this.getFont(dm));

    int xPos = dm.getXPosition() - detailviewHelper.getXOffset();
    // after trial and error, this constant is fixed at 7
    int yPos = dm.getYPosition() - detailviewHelper.getYOffset() + (7);

    if (xPos < 1) {
      xPos = 10;
    }

    if (yPos < 1) {
      yPos = 10;
    }

    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    g.drawString(StringI18NUtil.convert(dm.getDisplayValue()), xPos, yPos);

    g.setFont(originalFont);
  }

}

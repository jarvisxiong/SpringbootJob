package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.tools.common.model.IRegion;
import com.sistic.ticketing.venue.tools.common.model.MapLabel;
import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;

public interface IOverviewImageRenderer extends IImageRenderer {

  public static final int X_DEVIATE = 2;

  public static final int Y_DEVIATE = 4;

  /**
   * Render legend.
   * @param graph the graph
   * @param region the region
   */
  public void renderLegend(Graphics2D graph, IRegion region);

  /**
   * Render stage.
   * @param graph the graph
   * @param region the region
   */
  public void renderStage(Graphics2D graph, IRegion region);

  /**
   * Render pricing section.
   * @param graph the graph
   * @param priceSection the price section
   * @param color the color
   * @param fill the fill
   */
  public void renderPricingSection(Graphics2D graph, PricingSection priceSection, Color color,
                                   boolean fill);

  /**
   * Render label.
   * @param graph the graph
   * @param lbl the lbl
   */
  public void renderLabel(Graphics2D graph, MapLabel lbl);

  /**
   * Render block.
   * @param graph the graph
   * @param region the region
   */
  public void renderBlock(Graphics2D graph, IRegion region);

  /**
   * Mark section as shape.
   * @param graph the graph
   * @param shape the shape
   */
  public void markSectionAsShape(Graphics2D graph, Shape shape);

  /**
   * Mark section as rect.
   * @param graph the graph
   * @param rect the rect
   */
  public void markSectionAsRect(Graphics2D graph, Rectangle rect);

  /**
   * Render outline.
   * @param graph the graph
   * @param region the region
   */
  public void renderOutline(Graphics2D graph, IRegion region);

}

package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;
import com.sistic.ticketing.venue.tools.util.ColorScheme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * This renderer renders similar to the default one. The only difference is that
 * it render the pricing section based on the boolean <code>fill</code> in the
 * <code>renderPricingSection</code> method. If <code>fill</code> is true, the
 * pricing section will be filled, else the pricing section will only be
 * rendered as as an outline. This render mark the selected section with a
 * bolder outline
 *
 * This is used for Sales purpose, like BA and internet patron
 * @author wai hong chan
 * @version 2.0
 */
public class OverviewSalesImageRenderer extends DefaultOverviewImageRenderer {

  private static BasicStroke borderStroke = new BasicStroke(3);

  /**
   * Instantiates a new overview sales image renderer.
   */
  public OverviewSalesImageRenderer() {
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.DefaultOverviewImageRenderer#renderPricingSection(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.pricing.model.PricingSection, java.awt.Color, boolean)
   */
  public void renderPricingSection(Graphics2D g, PricingSection priceSection, Color color,
                                   boolean fill) {

    if (fill) {
      g.setColor(color);
      g.fill(priceSection.getShape());
    }
    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    g.draw(priceSection.getShape());

  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.DefaultOverviewImageRenderer#markSectionAsShape(java.awt.Graphics2D, java.awt.Shape)
   */
  public void markSectionAsShape(Graphics2D g, Shape shape) {

    Stroke originalStroke = g.getStroke();
    g.setStroke(borderStroke);
    g.draw(shape);
    g.setStroke(originalStroke);

  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.DefaultOverviewImageRenderer#markSectionAsRect(java.awt.Graphics2D, java.awt.Rectangle)
   */
  public void markSectionAsRect(Graphics2D g, Rectangle rect) {

    Stroke originalStroke = g.getStroke();
    g.setStroke(borderStroke);
    g.draw(rect);
    g.setStroke(originalStroke);
  }

}

package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.tools.common.model.IRegion;
import com.sistic.ticketing.venue.tools.common.model.MapLabel;
import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;
import com.sistic.ticketing.venue.tools.util.ColorScheme;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * Render all the price sections with colors. This renderer does not render
 * labels like the price category or standard price on the pricing sections
 * @author wai hong chan
 * @version 2.0
 */
public class DefaultOverviewImageRenderer implements IOverviewImageRenderer {

  private static BasicStroke borderStroke = new BasicStroke(3);

  /**
   * Instantiates a new default overview image renderer.
   */
  public DefaultOverviewImageRenderer() {

  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderOutline(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.common.model.IRegion)
   */
  public void renderOutline(Graphics2D g, IRegion region) {
    g.setColor(region.getColor());
    g.draw(region.getShape());
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderLegend(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.common.model.IRegion)
   */
  public void renderLegend(Graphics2D g, IRegion r) {
    g.setColor(r.getColor());
    g.fill(r.getShape());
    g.setColor(ColorScheme.getColorScheme(ColorScheme.DEFAULT_COLOR));
    g.draw(r.getShape());
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderStage(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.common.model.IRegion)
   */
  public void renderStage(Graphics2D g, IRegion r) {
    g.setColor(r.getColor());
    g.draw(r.getShape());
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderPricingSection(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.pricing.model.PricingSection, java.awt.Color, boolean)
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
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#markSectionAsShape(java.awt.Graphics2D, java.awt.Shape)
   */
  public void markSectionAsShape(Graphics2D g, Shape shape) {

    Stroke originalStroke = g.getStroke();
    g.setStroke(borderStroke);
    g.draw(shape);
    g.setStroke(originalStroke);

  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#markSectionAsRect(java.awt.Graphics2D, java.awt.Rectangle)
   */
  public void markSectionAsRect(Graphics2D g, Rectangle rect) {
    Stroke originalStroke = g.getStroke();
    g.setStroke(borderStroke);
    g.draw(rect);
    g.setStroke(originalStroke);
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderLabel(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.common.model.MapLabel)
   */
  public void renderLabel(Graphics2D g, MapLabel lbl) {
    try {
      g.setFont(lbl.getDerivedFont());

      // g.drawString(StringI18NUtil.convert(lbl.getText()),lbl.getX(),lbl.getY());
      if (lbl.getText() != null && lbl.getText().length() > 0) {
        g.drawString(lbl.getText(), lbl.getX(), lbl.getY());
      }
    } catch (Exception e) {
      System.out.println("DefaultOverviewImageRenderer.renderLabel() : " + e.getMessage());
    }
  }

  /* (non-Javadoc)
   * @see com.stixcloud.service.event.seatmap.imagerenderer.IOverviewImageRenderer#renderBlock(java.awt.Graphics2D, com.sistic.ticketing.seatmap.tools.common.model.IRegion)
   */
  public void renderBlock(Graphics2D g, IRegion r) {

    g.draw(r.getShape());
    renderBlockName(g, r.getName(), r.getShape().getBounds());

  }

  /**
   * Render block name.
   * @param g the g
   * @param value the value
   * @param rect the rect
   */
  private void renderBlockName(Graphics g, String value, Rectangle rect) {

    int strWidth = g.getFontMetrics().stringWidth(value);

    int xPos = (int) (rect.getCenterX()) - (strWidth / 2);
    int yPos = (int) rect.getCenterY() + (g.getFontMetrics().getHeight() / 2);

    g.drawString(value, xPos, yPos);

  }

}

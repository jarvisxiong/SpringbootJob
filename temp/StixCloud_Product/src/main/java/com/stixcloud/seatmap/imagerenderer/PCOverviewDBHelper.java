package com.stixcloud.seatmap.imagerenderer;

import com.ecquaria.tools.uimodel.CircleModel;
import com.ecquaria.tools.uimodel.PolygonModel;
import com.ecquaria.tools.uimodel.RectangleModel;
import com.ecquaria.tools.uimodel.ShapeModel;
import com.sistic.ticketing.venue.lc.uimodel.OverviewComponentModel;
import com.sistic.ticketing.venue.lc.uimodel.OverviewLabelComponentModel;
import com.sistic.ticketing.venue.pc.uimodel.PricingSectionModel;
import com.sistic.ticketing.venue.tools.StringI18NUtil;
import com.sistic.ticketing.venue.tools.common.model.IRegion;
import com.sistic.ticketing.venue.tools.common.model.MapLabel;
import com.sistic.ticketing.venue.tools.common.model.PriceCategoryVO;
import com.sistic.ticketing.venue.tools.pricing.model.Legend;
import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;
import com.sistic.ticketing.venue.tools.util.AppletUIConfig;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * Provide the methods to create data model objects for persistency (saving) and
 * create of ui model objects for presentation (loading).
 * @author wai hong chan
 * @version 2.0
 */
@Component(value = "PCOverviewDBHelper")
public class PCOverviewDBHelper {

  /**
   * Instantiates a new pC overview db helper.
   */
  public PCOverviewDBHelper() {
  }

  /**
   * ******************************
   * Methods for creating data models
   * *****************************.
   *
   * @param label the label
   * @return the overview label component model
   */

  /**
   * create a label model
   **/
  public OverviewLabelComponentModel createDataLabelModel(MapLabel label) {
    OverviewLabelComponentModel labelModel = new OverviewLabelComponentModel();

    labelModel.setVertical(label.isVertical());
    labelModel.setDisplayValue(StringI18NUtil.convertToNative(label.getText()));
    labelModel.setFontSize(label.getDerivedFont().getSize());
    labelModel.setForeground(label.getLabelColor());

    RectangleModel rectModel = new RectangleModel();
    rectModel.setXPosition(label.getX());
    rectModel.setYPosition(label.getY());
    labelModel.setShape(rectModel);

    return labelModel;

  }

  /**
   * create a legend model.
   * @param legend the legend
   * @return the overview component model
   */
  public OverviewComponentModel createDataLegendModel(IRegion legend) {
    OverviewComponentModel stageModel = new OverviewComponentModel();
    stageModel.setDisplayValue(StringI18NUtil.convertToNative(legend.getName()));

    stageModel.setShape(getShapeModel(legend));
    stageModel.setForeground(legend.getColor());

    return stageModel;
  }

  /**
   * create a pricing section model.
   * @param ps the ps
   * @return the pricing section model
   */
  public PricingSectionModel createDataPricingSectionModel(PricingSection ps) {
    PricingSectionModel psModel = new PricingSectionModel();

    psModel.setSectionAlias(StringI18NUtil.convertToNative(ps.getName()));
    psModel.setDisplayValue(StringI18NUtil.convertToNative(ps.getName()));
    psModel.setForeground(ps.getColor());
    psModel.setShape(getShapeModel(ps));
    psModel.setLevelID(ps.getLevelId());
    psModel.setLevelAlias(StringI18NUtil.convertToNative(ps.getLevelAlias()));
    psModel.setAreaName(StringI18NUtil.convertToNative(ps.getAreaName()));
    psModel.setPriceCatNumber(ps.getCategoryNumber());
    psModel.setPriceCatID(ps.getPriceCategoryId());
    psModel.setParentSectionID(ps.getParentSectionId());
    psModel.setFull(ps.isFull());

    return psModel;
  }

  /**
   * ******************************
   * Methods for creating UI models
   * *****************************.
   *
   * @param dataModel the data model
   * @return the legend
   */
  /**
   * create Legend
   */
  public Legend createUILegend(OverviewComponentModel dataModel) {
    Legend uiModel = new Legend();

    uiModel.setName(StringI18NUtil.convert(dataModel.getDisplayValue()));
    uiModel.setId(dataModel.getComponentID());
    uiModel.setShape(getShapeUI(dataModel.getShape()));
    uiModel.setColor(new Color(dataModel.getForeground()));

    return uiModel;
  }

  /**
   * create Label.
   * @param dataModel the data model
   * @return the map label
   */
  public MapLabel createUIMapLabel(OverviewLabelComponentModel dataModel) {
    MapLabel label = null;
    RectangleModel rectModel = (RectangleModel) dataModel.getShape();

    String codedValue = StringI18NUtil.convert(dataModel.getDisplayValue());

    if (dataModel.isVertical()) {
      label =
          MapLabel.getInstance(
              new Font(AppletUIConfig.getFontName(), Font.PLAIN, dataModel.getFontSize()),
              codedValue, rectModel.getXPosition(), rectModel.getYPosition(),
              MapLabel.VERTICAL_LABEL);
    } else {
      label =
          MapLabel.getInstance(
              new Font(AppletUIConfig.getFontName(), Font.PLAIN, dataModel.getFontSize()),
              codedValue, rectModel.getXPosition(), rectModel.getYPosition(),
              MapLabel.HORIZONTAL_LABEL);

    }

    label.setLabelColor(new Color(dataModel.getForeground()));
    label.setId(dataModel.getComponentID());
    return label;

  }

  /**
   * create PricingSection.
   * @param dataModel the data model
   * @return the pricing section
   */
  public PricingSection createUIPricingSection(PricingSectionModel dataModel) {
    PricingSection uiModel = new PricingSection();

    uiModel.setName(StringI18NUtil.convert(dataModel.getSectionAlias()));
    uiModel.setLevelAlias(StringI18NUtil.convert(dataModel.getLevelAlias()));
    uiModel.setLevelId(dataModel.getLevelID());
    uiModel.setAreaName(StringI18NUtil.convert(dataModel.getAreaName()));
    uiModel.setId(dataModel.getComponentID());
    uiModel.setParentSectionId(dataModel.getParentSectionID());
    uiModel.setShape(getShapeUI(dataModel.getShape()));
    uiModel.setColor(new Color(dataModel.getForeground()));
    uiModel.setPriceCategoryAlias(PriceCategoryVO.CATEGORY_PREFIX + dataModel.getPriceCatNumber());
    uiModel.setLayoutType(dataModel.getSectionType());
//		uiModel.setDisplayablePriceCat(AppletUILang.getMessage("priceCategoryVO.category.desc", AppletUILang.BK) + " "
//				+ dataModel.getPriceCatNumber());
    // TODO to change into Language specific values. Right now using English
    uiModel.setDisplayablePriceCat("Price Category " + dataModel.getPriceCatNumber());
    uiModel.setId(dataModel.getSectionID());
    uiModel.setNumOfSeats(dataModel.getNumSeats());
    uiModel.setPriceCategoryId(dataModel.getPriceCatID());
    uiModel.setLayoutType(dataModel.getSectionType());
    return uiModel;
  }

  /**
   * Gets the shape ui.
   * @param shapeModel the shape model
   * @return the shape ui
   */
  private Shape getShapeUI(ShapeModel shapeModel) {
    if (shapeModel instanceof CircleModel) {
      // create Ellipse2D
      CircleModel model = (CircleModel) shapeModel;
      Ellipse2D
          elip =
          new Ellipse2D.Float(model.getXPosition(), model.getYPosition(), (model.getRadius() * 2),
              (model.getRadius() * 2));

      return elip;

    } else if (shapeModel instanceof RectangleModel) {
      // create a java.awt.Rectangle
      RectangleModel model = (RectangleModel) shapeModel;
      Rectangle rect = new Rectangle(model.getXPosition(), model.getYPosition(), model.getWidth(),
          model.getHeight());

      return rect;
    } else if (shapeModel instanceof PolygonModel) {
      // create a java.awt.Polygon
      PolygonModel model = (PolygonModel) shapeModel;
      Polygon poly = new Polygon();
      for (int i = 0; i < model.getXPositions().length; i++) {
        poly.addPoint((model.getXPositions())[i], (model.getYPositions()[i]));
      }

      return poly;
    }

    return null; // should not be null since it is loaded from DB.
  }

  /**
   * Create a ShapeModel (data model).
   * @param region the region
   * @return the shape model
   */
  private ShapeModel getShapeModel(IRegion region) {
    return getShapeModel(region.getShape());
  }

  /**
   * Create a ShapeModel based on the awt.Shape
   * @param shape the shape
   * @return the shape model
   */
  private ShapeModel getShapeModel(Shape shape) {

    if (shape instanceof Ellipse2D) {
      Ellipse2D elip = (Ellipse2D) shape;
      CircleModel circleMode = new CircleModel();

      circleMode.setXPosition((int) elip.getX());
      circleMode.setYPosition((int) elip.getY());
      circleMode.setRadius((int) elip.getWidth() / 2);

      return circleMode;

    } else if (shape instanceof Rectangle) {
      Rectangle rect = (Rectangle) shape;
      RectangleModel rectModel = new RectangleModel();

      rectModel.setWidth(rect.width);
      rectModel.setHeight(rect.height);
      rectModel.setPoint(rect.x, rect.y);

      return rectModel;

    } else if (shape instanceof Polygon) {
      Polygon polygon = (Polygon) shape;
      PolygonModel polyModel = new PolygonModel();

			/*
                         * -- IMPORTANT NOTE by wai hong--
			 *
			 * Somehow the Polygon will contains some coordinates of x=0,y=0.
			 * This is especially serious when the polygon have more sides. This
			 * may be a bug in the Polygon class. But no further testing has
			 * been carried out to verify if this is the case. In mean time, we
			 * just need to remove all the x=0,y=0 coordinates in the polygon,
			 * if this is not done, the polygon will be wrong when loaded. This
			 * may not be the best solution, but it works for now
			 */

      int xPts[] = polygon.xpoints;
      int yPts[] = polygon.ypoints;
      ArrayList<Integer> xList = new ArrayList<Integer>();
      ArrayList<Integer> yList = new ArrayList<Integer>();

      for (int i = 0; i < xPts.length; i++) {
        if (xPts[i] != 0 && yPts[i] != 0) {
          xList.add(new Integer(xPts[i]));
          yList.add(new Integer(yPts[i]));
        }
      }

      xPts = new int[xList.size()];
      yPts = new int[yList.size()];

      for (int i = 0; i < xPts.length; i++) {
        xPts[i] = xList.get(i).intValue();
        yPts[i] = yList.get(i).intValue();
      }

      // End of the workaround codes

      polyModel.setXPositions(xPts);
      polyModel.setYPositions(yPts);

      return polyModel;
    }

    return null; // should not happen
  }

}

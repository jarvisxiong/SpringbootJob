package com.stixcloud.seatmap.imagerenderer;

import com.ecquaria.tools.uimodel.CircleModel;
import com.ecquaria.tools.uimodel.PolygonModel;
import com.ecquaria.tools.uimodel.RectangleModel;
import com.ecquaria.tools.uimodel.ShapeModel;
import com.sistic.ticketing.venue.lc.uimodel.BlockModel;
import com.sistic.ticketing.venue.lc.uimodel.DetailLabelComponentModel;
import com.sistic.ticketing.venue.lc.uimodel.OutlineModel;
import com.sistic.ticketing.venue.lc.uimodel.OverviewComponentModel;
import com.sistic.ticketing.venue.lc.uimodel.OverviewLabelComponentModel;
import com.sistic.ticketing.venue.pc.uimodel.PricingSectionModel;
import com.stixcloud.domain.VenueDetailComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsPc;
import com.stixcloud.seatmap.dto.PricingSection;
import com.stixcloud.seatmap.repo.PricingSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

/**
 * Created by chongye on 07-Sep-16.
 */
@Component
public class ComponentModelBuilder {
  @Autowired
  PricingSectionRepository pricingSectionRepository;

  private Long componentid;
  private Long pcId;
  private Long lcId;
  private Integer type;
  private String displayvalue;
  private String shape;
  private String x;
  private String y;
  private Integer width;
  private Integer height;
  private Boolean isvertical;
  private Boolean isvisible;
  private Integer bgcolor;
  private Integer fgcolor;
  private Integer fontsize;
  private Long linkId;
  private String levelAlias;
  private String sectionalias;
  private Long blockId;
  private Integer rotation;

  public ComponentModelBuilder setVenueOverviewComponentsLc(VenueOverviewComponentsLc lc) {
    this.componentid = lc.getVenueoverviewcomponentslcid();
    this.lcId = lc.getLcId();
    this.type = lc.getType();
    this.x = lc.getX();
    this.y = lc.getY();
    this.width = lc.getWidth();
    this.height = lc.getHeight();
    this.isvertical = lc.getIsvertical();
    this.isvisible = lc.getIsvisible();
    this.bgcolor = lc.getBgcolor();
    this.fgcolor = lc.getFgcolor();
    this.fontsize = lc.getFontsize();
    this.linkId = lc.getLinkId();
    this.levelAlias = lc.getLevelalias();
    this.sectionalias = lc.getSectionalias();
    this.shape = lc.getShape();
    this.displayvalue = lc.getDisplayvalue();
    return this;
  }

  public ComponentModelBuilder setVenueOverviewComponentsPc(VenueOverviewComponentsPc pc) {
    this.componentid = pc.getComponentid();
    this.pcId = pc.getPcId();
    this.type = pc.getType();
    this.x = pc.getX();
    this.y = pc.getY();
    this.width = pc.getWidth();
    this.height = pc.getHeight();
    this.isvertical = pc.getIsvertical();
    this.isvisible = pc.getIsvisible();
    this.bgcolor = pc.getBgcolor();
    this.fgcolor = pc.getFgcolor();
    this.fontsize = pc.getFontsize();
    this.linkId = pc.getLinkId();
    this.shape = pc.getShape();
    this.displayvalue = pc.getDisplayvalue();
    return this;
  }

  public ComponentModelBuilder setVenueDetailComponentsLc(VenueDetailComponentsLc lc) {
    this.componentid = lc.getVenuedetailcomponentslcid();
    this.blockId = lc.getBlockId();
    this.lcId = lc.getLcId();
    this.type = lc.getType();
    this.x = lc.getX();
    this.y = lc.getY();
    this.width = lc.getWidth();
    this.height = lc.getHeight();
    this.isvertical = lc.getIsvertical();
    this.isvisible = lc.getIsvisible();
    this.bgcolor = lc.getBgcolor();
    this.fgcolor = lc.getFgcolor();
    this.fontsize = lc.getFontsize();
    this.shape = lc.getShape();
    this.displayvalue = lc.getDisplayvalue();
    this.rotation = lc.getRotation();
    return this;
  }

  public DetailLabelComponentModel toDetailLabelComponentModel() {
    if (this.componentid == null || this.lcId == null) {
      return new DetailLabelComponentModel();
    }

    DetailLabelComponentModel res = new DetailLabelComponentModel(
        this.componentid, this.blockId);
    res.setForeground(this.fgcolor);
    res.setVertical(this.isvertical != null && this.isvertical);
    res.setFontSize(this.fontsize);
    res.setType(this.type);
    res.setBackground(this.bgcolor);
    res.setDisplayValue(this.displayvalue);
    res.setRotation(this.rotation);
    res.setVisible(this.isvisible != null && this.isvisible);
    res.setXPosition(Integer.parseInt(x));
    res.setYPosition(Integer.parseInt(y));
    res.setShape(createShape());
    return res;
  }

  public BlockModel getBlockModel() {
    BlockModel model = new BlockModel(this.componentid, this.componentid);
    configOverviewComponentsModel(model);
    return model;
  }

  public OverviewLabelComponentModel getOverviewLabelComponentModel() {
    OverviewLabelComponentModel model = new OverviewLabelComponentModel();
    configOverviewComponentsModel(model);
    if (this.fontsize != null) {
      model.setFontSize(this.fontsize);
    }
    return model;
  }

  public OverviewComponentModel getOverviewComponentModel() {
    OverviewComponentModel model = new OverviewComponentModel();
    configOverviewComponentsModel(model);
    return model;
  }

  public OutlineModel getOutlineModel() {
    OutlineModel model = new OutlineModel();
    configOverviewComponentsModel(model);
    return model;
  }

  /*public com.sistic.ticketing.venue.pc.uimodel.OverviewLabelComponentModel getOverviewLabelComponentModelPc() {
    com.sistic.ticketing.venue.pc.uimodel.OverviewLabelComponentModel
        mod =
        new com.sistic.ticketing.venue.pc.uimodel.OverviewLabelComponentModel(this.componentid);
    configOverviewComponentsModel(mod);
    if (this.fontsize != null) {
      mod.setFontSize(this.fontsize);
    }
    return mod;
  }*/

  /*public com.sistic.ticketing.venue.pc.uimodel.OverviewComponentModel getOverviewComponentModelPc() {
    com.sistic.ticketing.venue.pc.uimodel.OverviewComponentModel
        mod = new com.sistic.ticketing.venue.pc.uimodel.OverviewComponentModel(this.componentid);
    configOverviewComponentsModel(mod);
    return mod;
  }*/

  public PricingSectionModel getPricingSectionModel() {
    PricingSection pricingSection = pricingSectionRepository.findOne(this.linkId);
    PricingSectionModel model = null;
    if (pricingSection != null) {
      model = new PricingSectionModel(pricingSection.getSectionID(), this.componentid);
      model.setLevelID(pricingSection.getLevelID());
      model.setLevelAlias(pricingSection.getLevelAlias());
      model.setSectionAlias(pricingSection.getSectionAlias());
      model.setAreaName(pricingSection.getAreaName());
      model.setPriceCatID(pricingSection.getPriceCatID());
      model.setPriceCatNumber(pricingSection.getPriceCatNumber());
      model.setParentSectionID(pricingSection.getParentSectionID());
      model.setFull(pricingSection.getFull().equals("T"));
      model.setSectionType(pricingSection.getSectionType());
      configOverviewComponentsModel(model);
    }
    return model;
  }

  private void configOverviewComponentsModel(OverviewComponentModel model) {
    model.setComponentID(this.componentid);
    model.setDisplayValue(this.displayvalue);
    model.setVertical(this.isvertical != null && this.isvertical);
    model.setVisible(this.isvisible != null && this.isvisible);
    model.setBackground(this.bgcolor != null ? this.bgcolor : 0);
    model.setForeground(this.fgcolor != null ? this.fgcolor : 0);
    model.setLevelAlias(this.levelAlias);
    model.setShape(createShape());
  }

  /*private void configOverviewComponentsModel(
      com.sistic.ticketing.venue.pc.uimodel.OverviewLabelComponentModel mod) {
    mod.setDisplayValue(this.displayvalue);
    mod.setVisible(this.isvisible);
    mod.setVertical(this.isvertical);
    mod.setBackground(this.bgcolor != null ? this.bgcolor : -1);
    mod.setForeground(this.fgcolor != null ? this.fgcolor : 0);
    mod.setFontSize(this.fontsize);
    mod.setShape(createShape());
  }*/

  private void configOverviewComponentsModel(PricingSectionModel mod) {
    mod.setDisplayValue(this.displayvalue);
    mod.setVisible(this.isvisible);
    mod.setBackground(this.bgcolor);
    mod.setForeground(this.fgcolor != null ? this.fgcolor : 0);
    mod.setShape(createShape());
  }

  /*private void configOverviewComponentsModel(
      com.sistic.ticketing.venue.pc.uimodel.OverviewComponentModel model) {
    model.setDisplayValue(this.displayvalue);
    model.setVisible(this.isvisible);
    model.setBackground(this.bgcolor);
    model.setForeground(this.fgcolor == null ? 0 : this.fgcolor);
    model.setShape(createShape());
  }*/

  private ShapeModel createShape() {
    int shapeType = Integer.parseInt(this.shape);

    if (shapeType == ShapeModel.CIRCLE) {
      CircleModel circle = new CircleModel();
      circle.setPoint(Integer.parseInt(this.x), Integer.parseInt(this.y));
      circle.setRadius(this.width);
      return circle;
    }

    if (shapeType == ShapeModel.RECTANGLE) {
      RectangleModel rect = new RectangleModel();
      rect.setPoint(Integer.parseInt(this.x), Integer.parseInt(this.y));
      rect.setWidth(this.width);
      rect.setHeight(this.height);
      return rect;
    }

    if (shapeType == ShapeModel.POLYGON) {
      PolygonModel poly = new PolygonModel();
      poly.setXPositions(convertStringToPoints(this.x));
      poly.setYPositions(convertStringToPoints(this.y));
      return poly;
    }

    throw new UnsupportedOperationException("Unknown ShapeModel type: " + shapeType);
  }

  private int[] convertStringToPoints(String strPoints) {
    StringTokenizer st = new StringTokenizer(strPoints, ";");
    int[] points = new int[st.countTokens()];
    for (int walker = 0, endOfList = st.countTokens(); walker < endOfList; walker++) {
      points[walker] = Integer.parseInt(st.nextToken());
    }
    return points;
  }
}

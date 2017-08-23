package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.imaging.JPEGWriter;
import com.sistic.ticketing.venue.lc.uimodel.DetailLabelComponentModel;
import com.sistic.ticketing.venue.tools.booking.model.overview.BKOverviewMapContainer;
import com.sistic.ticketing.venue.tools.common.model.IRegion;
import com.sistic.ticketing.venue.tools.common.model.MapLabel;
import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;
import com.sistic.ticketing.venue.tools.util.AppletUIConfig;
import com.stixcloud.domain.VenueDetailComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsLc;
import com.stixcloud.domain.VenueOverviewComponentsPc;
import com.stixcloud.seatmap.constants.SalesConstants;
import com.stixcloud.seatmap.dto.PriceCategoryDetail;
import com.stixcloud.seatmap.dto.SeatModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(value = "ImageCreator")
@SuppressWarnings("unchecked")
public class ImageCreator {
  private static final Logger logger = LogManager.getLogger(ImageCreator.class);
  @Autowired
  ComponentModelBuilder builder;
  private JPEGWriter jpegWriter;
  @Autowired
  private LCOverviewDBHelper lcDBHelper;
  @Autowired
  private PCOverviewDBHelper pcDBhelper;

  public ImageCreator() {
    this.jpegWriter = new JPEGWriter();
  }

  /**
   * Creates the overview image.
   * @param dataList the data list
   * @param sectionIds the section ids
   * @param renderBlock the render block
   * @param renderType the render type
   * @param fillPriceSection the fill price section
   * @param imagePath the image path
   * @return the layout image
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public String createOverviewImage(List<PriceCategoryDetail> priceCategories,
                                    List<VenueOverviewComponentsLc> venueOverviewComponentsLcs,
                                    List<VenueOverviewComponentsPc> venueOverviewComponentsPcs,
                                    Long sectionId, boolean renderBlock,
                                    boolean fillPriceSection, String imagePath)
      throws IOException {

    ImageCreatorHelper imageCreaterHelper = new ImageCreatorHelper();
    BKOverviewMapContainer container = new BKOverviewMapContainer();
    imageCreaterHelper.setPriceCategories(priceCategories);

    Map<Integer, List<VenueOverviewComponentsLc>> sortedLcMap =
        venueOverviewComponentsLcs.stream()
            .collect(Collectors.groupingBy(VenueOverviewComponentsLc::getType));

    Map<Integer, List<VenueOverviewComponentsPc>> sortedPcMap =
        venueOverviewComponentsPcs.stream().collect(
            Collectors.groupingBy(VenueOverviewComponentsPc::getType));

    storeComponents(container, sortedLcMap, sortedPcMap);
    imageCreaterHelper.calculateImageSize(container);

    Image image =
        new BufferedImage(imageCreaterHelper.getImageWidth(), imageCreaterHelper.getImageHeight(),
            BufferedImage.TYPE_INT_RGB);
    Graphics2D g = (Graphics2D) image.getGraphics();
    g.setColor(Color.white);
    g.fillRect(0, 0, imageCreaterHelper.getImageWidth(), imageCreaterHelper.getImageHeight());
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

    renderOverviewImage(container, imageCreaterHelper, sectionId, g, renderBlock,
        fillPriceSection);

    g.drawImage(image, 0, 0, null);
    g.dispose();
    image.flush();

    jpegWriter.writeImage(imagePath, (BufferedImage) image);
    return imagePath;
  }

  /**
   * Creates seatmap detailed images.
   * @param seatInvIds the seat inv ids
   * @param overviewDataMap the overview data map
   * @param blockId the block id
   * @param renderType the render type
   * @param imagePath the image path
   * @return the layout image
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public String createDetailImage(List<Long> seatInvIds, List<PriceCategoryDetail> priceCategories,
                                  List<VenueDetailComponentsLc> venueDetailComponentsLcs,
                                  List<SeatModel> seatModels, Long blockId, String imagePath)
      throws IOException {

    ImageCreatorHelper imageCreaterHelper = new ImageCreatorHelper();
    ImageCreatorDetailViewHelper detailviewHelper = new ImageCreatorDetailViewHelper();

    imageCreaterHelper.setPriceCategories(priceCategories);

    List<DetailLabelComponentModel> detailLabelComponentModelList =
        venueDetailComponentsLcs.stream().map(
            venueDetailComponentsLc -> builder.setVenueDetailComponentsLc(venueDetailComponentsLc)
                .toDetailLabelComponentModel()).collect(Collectors.toList());

    detailviewHelper.calculateImageSizeForSeatModels(seatModels, detailLabelComponentModelList);

    //Draw the image
    Image detailImage =
        new BufferedImage(detailviewHelper.getImageWidth(), detailviewHelper.getImageHeight(),
            BufferedImage.TYPE_INT_RGB);

    //fill the background with a color
    Graphics2D g2d = (Graphics2D) detailImage.getGraphics();
    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, detailviewHelper.getImageWidth(), detailviewHelper.getImageHeight());
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

    renderDetailviewImage(seatInvIds, g2d, seatModels, detailLabelComponentModelList,
        imageCreaterHelper, detailviewHelper);

    g2d.drawImage(detailImage, 0, 0, null);
    g2d.dispose();
    detailImage.flush();

    imagePath = imagePath.substring(0, imagePath.indexOf(".jpg")) + "_" + blockId + ".jpg";
    jpegWriter.writeImage(imagePath, (BufferedImage) detailImage);

    return imagePath;
  }

  /**
   * Store components.
   * @param container the container
   * @param sortedLcMap the sorted lc map
   * @param sortedPcMap the sorted pc map
   */

  private void storeComponents(BKOverviewMapContainer container,
                               Map<Integer, List<VenueOverviewComponentsLc>> sortedLcMap,
                               Map<Integer, List<VenueOverviewComponentsPc>> sortedPcMap) {
    if (sortedLcMap.containsKey(SalesConstants.BLOCK_MODEL_TYPE_LC)) {
      for (VenueOverviewComponentsLc venueComponentsLc : sortedLcMap
          .get(SalesConstants.BLOCK_MODEL_TYPE_LC)) {
        container.addBlock(lcDBHelper.createUIBlockModel(
            builder.setVenueOverviewComponentsLc(venueComponentsLc).getBlockModel()));
      }
    }
    if (sortedLcMap.containsKey(SalesConstants.OVERVIEW_LABEL_MODEL_TYPE_LC)) {
      for (VenueOverviewComponentsLc venueComponentsLc : sortedLcMap
          .get(SalesConstants.OVERVIEW_LABEL_MODEL_TYPE_LC)) {
        container.addLabel(
            lcDBHelper.createUIMapLabel(
                builder.setVenueOverviewComponentsLc(venueComponentsLc)
                    .getOverviewLabelComponentModel()));
      }
    }
    if (sortedLcMap.containsKey(SalesConstants.OVERVIEW_COMPONENT_MODEL_TYPE_LC)) {
      for (VenueOverviewComponentsLc venueComponentsLc : sortedLcMap
          .get(SalesConstants.OVERVIEW_COMPONENT_MODEL_TYPE_LC)) {
        container
            .addStage(lcDBHelper.createUIStageModel(
                builder.setVenueOverviewComponentsLc(venueComponentsLc)
                    .getOverviewComponentModel()));
      }
    }
    if (sortedLcMap.containsKey(SalesConstants.OVERVIEW_OUTLINE_MODEL_TYPE_LC)) {
      for (VenueOverviewComponentsLc venueComponentsLc : sortedLcMap
          .get(SalesConstants.OVERVIEW_OUTLINE_MODEL_TYPE_LC)) {
        container.addOutline(lcDBHelper.createUIOutlineModel(
            builder.setVenueOverviewComponentsLc(venueComponentsLc).getOutlineModel()));
      }
    }

    if (sortedPcMap.containsKey(SalesConstants.PRICING_SECTION_MODEL_TYPE_PC)) {
      for (VenueOverviewComponentsPc venueComponentsPc : sortedPcMap
          .get(SalesConstants.PRICING_SECTION_MODEL_TYPE_PC)) {
        container.addPricingSection(pcDBhelper
            .createUIPricingSection(
                builder.setVenueOverviewComponentsPc(venueComponentsPc).getPricingSectionModel()));
      }
    }

    if (sortedPcMap.containsKey(SalesConstants.OVERVIEW_LABEL_MODEL_TYPE_PC)) {
      for (VenueOverviewComponentsPc venueComponentsPc : sortedPcMap
          .get(SalesConstants.OVERVIEW_LABEL_MODEL_TYPE_PC)) {
        container.addLabel(
            lcDBHelper.createUIMapLabel(
                builder.setVenueOverviewComponentsPc(venueComponentsPc)
                    .getOverviewLabelComponentModel()));
      }
    }
    if (sortedPcMap.containsKey(SalesConstants.OVERVIEW_COMPONENT_MODEL_TYPE_PC)) {
      for (VenueOverviewComponentsPc venueComponentsPc : sortedPcMap
          .get(SalesConstants.OVERVIEW_COMPONENT_MODEL_TYPE_PC)) {
        container
            .addLegend(pcDBhelper.createUILegend(
                builder.setVenueOverviewComponentsPc(venueComponentsPc)
                    .getOverviewComponentModel()));
      }
    }
  }

  /**
   * Render overview image.
   * @param container the container
   * @param imageCreaterHelper the image creater helper
   * @param sectionIds the section ids
   * @param graph the graph
   * @param renderBlock the render block
   * @param renderType the render type
   * @param fillPriceSection the fill price section
   */
  private void renderOverviewImage(BKOverviewMapContainer container,
                                   ImageCreatorHelper imageCreaterHelper, Long sectionId,
                                   Graphics graph, boolean renderBlock, boolean fillPriceSection) {

    Graphics2D g = (Graphics2D) graph;
    IRegion r = null;
    //always default to SALES_RENDERER
    IOverviewImageRenderer renderer = new OverviewSalesImageRenderer();

    // paint the legends
    int listSize = container.getAllLegendsSize();
    for (int i = 0; i < listSize; i++) {
      r = container.getLegend(i);
      renderer.renderLegend(g, r);
    }

    // paint the stages
    listSize = container.getAllStagesSize();
    for (int i = 0; i < listSize; i++) {
      r = container.getStage(i);
      renderer.renderStage(g, r);
    }

    // paint the pricing sections
    PricingSection priceSection = null;
    for (int i = 0; i < container.getAllPricingSectionsSize(); i++) {
      priceSection = container.getPricingSection(i);
      try {
        if (sectionId != null && priceSection.getId() == sectionId) {
          renderer.renderPricingSection(g, priceSection,
              imageCreaterHelper.getColorByCategory(priceSection.getPriceCategoryAlias()), true);
          renderer.markSectionAsShape(g, priceSection.getShape());
        } else {
          renderer.renderPricingSection(g, priceSection,
              imageCreaterHelper.getColorByCategory(priceSection.getPriceCategoryAlias()),
              fillPriceSection);
        }
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
      }
    }

    // paint the labels
    listSize = container.getAllLabelsSize();
    MapLabel lbl = null;
    for (int i = 0; i < listSize; i++) {
      lbl = container.getLabel(i);
      renderer.renderLabel(g, lbl);
    }
    // render blocks if required
    if (renderBlock) {
      listSize = container.getAllBlocksSize();
      for (int i = 0; i < listSize; i++) {
        r = container.getBlock(i);
        renderer.renderBlock(g, r);
      }
    }
    // paint the outline
    listSize = container.getAllOutlinesSize();
    for (int i = 0; i < listSize; i++) {
      r = container.getOutline(i);
      renderer.renderOutline(g, r);
    }
  }

  /**
   * Return true if the sectionId is found in the array of section ids.
   * @param sectionId the section id
   * @param sectionIds the section ids
   * @return true, if is included
   */
  private boolean isIncluded(long sectionId, List<Long> sectionIds) {
    return sectionIds != null && sectionIds.contains(sectionId);
  }

  /**
   * Render the seats, labels onto the graphic.
   * Note that the actual rendering is done by a <code>Renderer</code>,
   * this method only contains the logic of when to call the renderer's
   * methods
   * @param seatInvIds the ids of the selected seats. These seats for these ids are rendered. Note
   * that not all renderers uses this ids for rendering
   * @param graph the graphic object to be used for rendering
   * @param sectionList the list that contains the seats
   * @param labelList the list that contains the labels to be rendered
   * @param blockId the id of the block to be rendered
   * @param renderType the type of render to be used
   * @param imageCreaterHelper the image creater helper
   * @param detailViewHelper the detail view helper
   */
  private void renderDetailviewImage(List<Long> seatInvIds, Graphics2D graph,
                                     List<SeatModel> seatModelList,
                                     List<DetailLabelComponentModel> labelList,
                                     ImageCreatorHelper imageCreaterHelper,
                                     ImageCreatorDetailViewHelper detailViewHelper) {

    setFontForDetailview(graph);
    //always default to IImageRenderer.SALES_RENDERER for our usage
    IDetailViewImageRenderer renderer =
        new DetailViewSalesImageRenderer(imageCreaterHelper, detailViewHelper);

    for (SeatModel seatModel : seatModelList) {
      renderer.renderSeat(seatInvIds, graph, seatModel);
    }
    for (DetailLabelComponentModel dm : labelList) {
      if (dm.getType() == DetailLabelComponentModel.STAGE_TYPE) {
        renderer.renderStage(graph, dm);
      } else if (dm.getType() == DetailLabelComponentModel.LABEL_TYPE) {
        renderer.renderLabel(graph, dm);
      }
    }
  }

  /**
   * Set the font to be used for rendering detail view.
   * @param graph the graphic object that is used for drawing
   */
  private void setFontForDetailview(Graphics graph) {
    Font seatfont = new Font(AppletUIConfig.getFontName(), 0, 10);
    graph.setFont(seatfont);
  }
}

package com.stixcloud.seatmap.imagerenderer;

import com.sistic.ticketing.venue.tools.booking.model.overview.BKOverviewMapContainer;
import com.sistic.ticketing.venue.tools.common.model.IRegion;
import com.sistic.ticketing.venue.tools.common.model.MapLabel;
import com.sistic.ticketing.venue.tools.pricing.model.PricingSection;
import com.stixcloud.seatmap.dto.PriceCategoryDetail;

import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;

public class ImageCreatorHelper {

  public final static String IMAGE_EXTENSION = ".jpg";
  private final static int WIDTH_BUFFER = 40;
  private final static int HEIGHT_BUFFER = 15;
  private List<PriceCategoryDetail> priceCategoryDetails;
  private String fileName;
  private int imageHeight;
  private int imageWidth;
  private int minX;
  private int minY;
  private int maxX;
  private int maxY;

  /**
   * Instantiates a new image creator helper.
   */
  public ImageCreatorHelper() {
    priceCategoryDetails = new ArrayList<>();
  }

  /**
   * Sets the price categories.
   * @param priceCategoryColorList the price category color list
   * @param pmtColumnList the PMT column live list
   */
  public void setPriceCategories(List<PriceCategoryDetail> priceCategoryDetails) {
    this.priceCategoryDetails.addAll(priceCategoryDetails);
  }

  /**
   * Calculate image size.
   * @param container the container
   */
  public void calculateImageSize(BKOverviewMapContainer container) {
    IRegion r = null;

    // we need to initialize the minX and minY, maxX and maxY first;
    r = container.getPricingSection(0);
    initMinMaxXY(r.getShape().getBounds());

    // check the pricing sections
    PricingSection priceSection = null;
    for (int i = 0; i < container.getAllPricingSectionsSize(); i++) {
      priceSection = container.getPricingSection(i);
      checkShape(priceSection.getShape().getBounds());
    }

    // check the legends
    int listSize = container.getAllLegendsSize();
    for (int i = 1; i < listSize; i++) {
      r = container.getLegend(i);
      checkShape(r.getShape().getBounds());
    }

    // check the stages
    listSize = container.getAllStagesSize();
    for (int i = 0; i < listSize; i++) {
      r = container.getStage(i);
      checkShape(r.getShape().getBounds());
    }

    // check the labels
    listSize = container.getAllLabelsSize();
    MapLabel lbl = null;
    for (int i = 0; i < listSize; i++) {
      lbl = container.getLabel(i);
      if (lbl.isVertical()) {
        // so funny workaround to get the actual x,y
        checkShape(lbl.getDerivedShape().getBounds());
      } else {
        checkShape(lbl.getDerivedShape());
      }
    }

    // by now, we will have the min/max X and Y

    // old algo for calculating the image size, not accurate
    // imageWidth = (maxX-minX) +WIDTH_BUFFER;
    // imageHeight = (maxY-minY) +HEIGHT_BUFFER;

    // new algo for calculating the image size
    imageWidth = (maxX) + WIDTH_BUFFER;
    imageHeight = (maxY) + HEIGHT_BUFFER;

  }

  /**
   * Inits the min max xy.
   * @param rect the rect
   */
  private void initMinMaxXY(Rectangle rect) {
    minX = (int) rect.getX();
    minY = (int) rect.getY();

    maxX = getBottomX(rect);
    maxY = getBottomY(rect);
  }

  /**
   * Check for shape.
   * @param shape the shape
   */
  private void checkShape(Shape shape) {

    if (shape instanceof Rectangle) {
      checkRectangle((Rectangle) shape);
    } else if (shape instanceof Polygon) {
      checkPolygon((Polygon) shape);
    }
  }

  /**
   * Extract the coordinates of the polygon and check for the min/max X and
   * min/max Y.
   * @param polygon the polygon to be checked
   */
  private void checkPolygon(Polygon polygon) {

    int[] xPts = polygon.xpoints;
    int[] yPts = polygon.ypoints;

    for (int i = 0; i < xPts.length; i++) {
      setMinMaxX(xPts[i]);
      setMinMaxY(yPts[i]);
    }

  }

  /**
   * Extract the coordinates of the rectangle and check for the min/max X and
   * min/max Y.
   * @param rect the rectangle to be checked
   */
  private void checkRectangle(Rectangle rect) {

    int x1 = (int) rect.getX();
    int y1 = (int) rect.getY();

    int x2 = getBottomX(rect);
    int y2 = getBottomY(rect);

    setMinMaxX(x1);
    setMinMaxX(x2);

    setMinMaxY(y1);
    setMinMaxY(y2);

  }

  /**
   * If the x value is smaller than minX, the y become the new minX. Vice
   * versa for max X
   * @param x the X value to be checked against current min/max X
   */
  private void setMinMaxX(int x) {

    if (x < minX) {
      minX = x;
      return;
    }

    if (x > maxX) {
      maxX = x;
    }
  }

  /**
   * If the y value is smaller than minY, the y become the new minY. Vice
   * versa for max Y
   * @param y the Y value to be checked against current min/max Y
   */
  private void setMinMaxY(int y) {

    if (y < minY) {
      minY = y;
      return;
    }

    if (y > maxY) {
      maxY = y;
    }
  }

  /**
   * Get the x coordinate of the bottom right point in a rectangle.
   * @param rect the rectangle
   * @return the bottom x
   */
  private int getBottomX(Rectangle rect) {
    return (int) (rect.getX() + rect.getWidth());
  }

  /**
   * Get the y coordinate of the bottom right point in a rectangle.
   * @param rect the rectangle
   * @return the bottom y
   */
  private int getBottomY(Rectangle rect) {
    return (int) (rect.getY() + rect.getHeight());
  }

  /**
   * Return the width of the image to be created.
   * @return width of image
   */
  public int getImageWidth() {
    return imageWidth;
  }

  /**
   * Return the height of the image to be created.
   * @return height of image
   */
  public int getImageHeight() {
    return imageHeight;
  }

  /**
   * Generate a file name based on the System time.
   * @param isNew if true, generated a new file name
   * @return a new file name if required, else return the current file name
   */
  public String getFileName(boolean isNew) {

    if (isNew) {
      fileName = String.valueOf(System.currentTimeMillis()) + IMAGE_EXTENSION;
    } else {
      if (fileName == null || fileName.equals("")) {
        fileName = String.valueOf(System.currentTimeMillis()) + IMAGE_EXTENSION;
      }
    }

    return fileName;

  }

  /**
   * Return the full path with file name. For example, the path is "c:\temp"
   * and file name is "layout.jpg". The full path will be "c:\temp\layout.jpg"
   * @param newFileName the new file name
   * @param imagePath the image path
   * @return the full path with file name
   */
  public String getFullPathFileName(String newFileName, String imagePath) {

    String tmpFileName = newFileName;

    if (newFileName == null || newFileName.equals("")) {
      tmpFileName = getFileName(false);
    } else {
      tmpFileName = newFileName + IMAGE_EXTENSION;
      fileName = tmpFileName;
    }

//		String[] serverPaths = imagePath.getServerPath();
//		for (int i = 0; i < serverPaths.length; i++) {
//			fullPaths[i] = serverPaths[i] + "/" + tmpFileName;
//		}
//
    return tmpFileName;
  }

  /**
   * Get the color of the category by the category str. This method is the
   * same as in the <code>DefaulConfigManager.getColorByCategory()<code>
   * method
   * @param catStr the name of the category
   * @return the color based on the category name. If not found, Black is returned
   */
  public Color getColorByCategory(String catStr) {
    for (int i = 0; i < priceCategoryDetails.size(); i++) {
      if (priceCategoryDetails.get(i).getCatAlias().equals(catStr)) {
        return new Color(priceCategoryDetails.get(i).getColor());
      }
    }
    return Color.black;
  }

  /**
   * Gets the color by category.
   * @param catNum the cat num
   * @return the color by category
   */
  public Color getColorByCategory(Long catNum) {
    for (int i = 0; i < priceCategoryDetails.size(); i++) {
      if (priceCategoryDetails.get(i).getPriceCategoryId().longValue() == catNum) {
        return new Color(priceCategoryDetails.get(i).getColor());
      }
    }
    return Color.black;
  }
}

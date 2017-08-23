package com.stixcloud.seatmap.imagerenderer;


import com.sistic.ticketing.venue.lc.uimodel.DetailComponentModel;
import com.sistic.ticketing.venue.lc.uimodel.DetailLabelComponentModel;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Like <code>ImageCreatorHelper</code>, this class is used for helping
 * <code>ImageCreator</code> for generating Detailview Images. It extends
 * <code>ImageCreatorHelper</code> to provide common methods
 * @author wai hong chan
 * @version 2.0
 */
public class ImageCreatorDetailViewHelper extends ImageCreatorHelper {

  /**
   * Buffer is used as a "margin" for the image, giving the image a little
   * more empty space at the sides
   */
  private final static int xBuffer = 60;

  private final static int yBuffer = 30;

  private int height;

  private int width;

  private int maxX;

  private int minX;

  private int minY;

  private int maxY;

  /**
   * A value to shift all seats position by the xOffset amount. For example,
   * say we have 3 seats in a row, where y = 10, and x of Seat A is 10, Seat B
   * is 20, and Seat C is 30. Using the min of X, i.e. 10, we div 10/2 to get
   * the offset. So now all seats are shifted by 5 to the left of the graphic.
   * We need this value because some seats are drawn far away from the
   * starting coordinate of 0, 0. So by doing so, we can "crop" or "trim" away
   * the emtpy spaces in an image. The concept is same for
   * <code>yOffset</code>
   */
  private int xOffset;

  private int yOffset;


  /**
   * Creates a new instance of this helper.
   */
  public ImageCreatorDetailViewHelper() {
    super();
  }

  /**
   * Return the width of the image.
   * @return width of the image
   */
  public int getImageWidth() {
    return this.width;
  }

  /**
   * Return the height of the image.
   * @return height of the image
   */
  public int getImageHeight() {
    return this.height;
  }

  public void calculateImageSizeForSeatModels(
      List<com.stixcloud.seatmap.dto.SeatModel> seatModelList,
      List<DetailLabelComponentModel> labels) {
    // Run thru the list of seats to get the min/max X,Y
    List<DetailComponentModel> models =
        Stream.concat(seatModelList.stream(), labels.stream()).collect(Collectors.toList());

    OptionalInt optionalMinX = models.stream().mapToInt(DetailComponentModel::getXPosition).min();
    OptionalInt optionalMaxX = models.stream().mapToInt(DetailComponentModel::getXPosition).max();

    OptionalInt optionalMinY = models.stream().mapToInt(DetailComponentModel::getYPosition).min();
    OptionalInt optionalMaxY = models.stream().mapToInt(DetailComponentModel::getYPosition).max();

    if (optionalMinX.isPresent() && optionalMaxX.isPresent() && optionalMinY.isPresent()
        && optionalMaxY.isPresent()) {
      minX = optionalMinX.getAsInt();
      minY = optionalMinY.getAsInt();

      maxX = optionalMaxX.getAsInt();
      maxY = optionalMaxY.getAsInt();
    }

    // you need the offset to calculate the image size
    calculateXYOffset();
    calculateWidthHeight();
  }

  /**
   * Return the xOffSet value.
   * @return the x offset
   */
  public int getXOffset() {
    return this.xOffset;
  }

  /**
   * Return the yOffSet value.
   * @return the y offset
   */
  public int getYOffset() {
    return this.yOffset;
  }

  /**
   * Calculate the offset for X,Y coordinates.
   */
  private void calculateXYOffset() {

    xOffset = minX / 2;
    yOffset = minY / 2;

  }

  /**
   * Calculate the width and height of the image to be created.
   */
  private void calculateWidthHeight() {
    width = maxX + xBuffer;
    height = maxY + yBuffer;

  }

}

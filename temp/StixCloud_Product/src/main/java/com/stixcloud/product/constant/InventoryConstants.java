package com.stixcloud.product.constant;

/**
 * Created by chongye on 02-Sep-16.
 */
public enum InventoryConstants {
  /**
   * Venue Type
   **/
  SECTION_TYPE_RS("RS", 1),
  SECTION_TYPE_GA("GA", 2),

  TICKETABLE_TRUE("True", 1),

  /**
   * Sales Status
   **/
  SALES_STATUS_AVAILABLE("Available", 0),
  SALES_STATUS_RESERVED("Reserved", 1),
  SALES_STATUS_HOLD("Hold", 2),
  SALES_STATUS_HOLD_RESERVED("Hold Reserved", 3),
  SALES_STATUS_SOLD("Sold", 5),

  /** Seat Attributes **/
  /**
   * Note that this is BITs value
   **/
  SEAT_ATTRIBUTE_AISLE_SEAT("Aisle Seat", 1),
  SEAT_ATTRIBUTE_LEFT_GAP_SEAT("Left Gap Seat", 2),
  SEAT_ATTRIBUTE_RIGHT_GAP_SEAT("Right Gap Seat", 4),

  /**
   * Seat Type
   **/
  SEAT_TYPE_NORMAL("Normal", 1),
  SEAT_TYPE_WHEEL_CHAIR("Wheel Chair", 2),
  SEAT_TYPE_RESTRICTED("Restricted", 3),
  SEAT_TYPE_BLOCKED("Blocked", 4),
  SEAT_TYPE_BANQUET("Banquet", 5),
  SEAT_TYPE_COUPLE_SEAT("Couple Seat", 6),

  SEATS_UNAVAILABLE("Seats Unavailable", 16),
  SEATS_AVAILABLE("Seats Available", 19),
  SEATS_LIMITED("Seats Limited", 18),
  SEATS_SINGLE_SEAT("Single Seat", 17),

  /* Default hold reason*/
  HOLD_REASON_GROUP_BOOKING("Group Booking", 1), // group booking
  
  /* Seat section availability */
  SEAT_SECTION_AVAILABLE("Seat Section available", 1),
  SEAT_SECTION_UNAVAILABLE("Seat Section unavailable", 0)
  ;


  String name;
  int value;

  /**
   * Instantiates a new inventory constants.
   * @param name the name
   * @param value the value
   */
  InventoryConstants(String name, int value) {
    this.name = name;
    this.value = value;
  }

  /**
   * Retrieves the seat attribute name based on the values in inventory record
   * Currently does not support i18.
   * @param value the value
   * @return the seat attribute name
   */
  public static String getSeatAttributeName(int value) {
    String attributeName = "";
    if (SEAT_ATTRIBUTE_AISLE_SEAT.value == value) {
      return SEAT_ATTRIBUTE_AISLE_SEAT.getName();
    } else if (SEAT_ATTRIBUTE_LEFT_GAP_SEAT.value == value) {
      return SEAT_ATTRIBUTE_LEFT_GAP_SEAT.getName();
    } else if (SEAT_ATTRIBUTE_RIGHT_GAP_SEAT.value == value) {
      return SEAT_ATTRIBUTE_RIGHT_GAP_SEAT.getName();
    }
    return attributeName;
  }

  /**
   * Retrieves the seat type based on the values in inventory record
   * Currently does not support i18.
   * @param value the value
   * @return the seat type name
   */
  public static String getSeatTypeName(int value) {
    String seatType = "";
    if (SEAT_TYPE_NORMAL.value == value) {
      return seatType;
    } else if (SEAT_TYPE_WHEEL_CHAIR.value == value) {
      return SEAT_TYPE_WHEEL_CHAIR.getName();
    } else if (SEAT_TYPE_RESTRICTED.value == value) {
      return SEAT_TYPE_RESTRICTED.getName();
    } else if (SEAT_TYPE_BLOCKED.value == value) {
      return SEAT_TYPE_BLOCKED.getName();
    } else if (SEAT_TYPE_BANQUET.value == value) {
      return SEAT_TYPE_BANQUET.getName();
    }
    return seatType;
  }

  /**
   * Gets the name.
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the value.
   * @return the value
   */
  public Integer getValue() {
    return Integer.valueOf(value);
  }
}

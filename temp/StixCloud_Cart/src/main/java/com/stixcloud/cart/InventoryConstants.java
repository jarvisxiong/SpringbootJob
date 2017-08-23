package com.stixcloud.cart;

/**
 * Created by chongye on 02-Sep-16.
 */
public enum InventoryConstants {
  /**
   * Venue Type
   **/
  SECTION_TYPE_RS("RS", 1),
  SECTION_TYPE_GA("GA", 2),
  SECTION_TYPE_SP("SP", 3),
  SECTION_TYPE_BA("BA", 4),

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

  SEATS_UNAVAILABLE("Seats Unavailable", 1),
  SEATS_AVAILABLE("Seats Available", 2),
  SEATS_LIMITED("Seats Limited", 3),
  SEATS_SINGLE_SEAT("Single Seat", 4),

  /* Default hold reason*/
  HOLD_REASON_GROUP_BOOKING("Group Booking", 1), // group booking
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

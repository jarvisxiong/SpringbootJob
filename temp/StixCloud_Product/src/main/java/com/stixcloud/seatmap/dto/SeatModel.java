package com.stixcloud.seatmap.dto;

import com.sistic.ticketing.venue.lc.Row;
import com.sistic.ticketing.venue.lc.uimodel.DetailComponentModel;
import com.sistic.ticketing.venue.lc.uimodel.Trackable;

import java.time.OffsetDateTime;

public class SeatModel extends DetailComponentModel implements Trackable {

  /**
   * @deprecated don't depend on this ... use AttributeSet instead
   */
  public static final int AISLE_SEAT = 1;
  /**
   * @deprecated don't depend on this ... use AttributeSet instead
   */
  public static final int TOILET_SEAT = 1 << 1;

  public static final int UNRANKED_STATE = Integer.MAX_VALUE;

  private static final long serialVersionUID = -607693562304483017L;

  private long seatInvID;
  private long seatID;
  private int seatNo;
  private long rank = UNRANKED_STATE;
  //set it to an arbitarily large value... to indicate its unranked state
  private int state;
  private int seatType;
  private String nearestEntrance;
  private String ticketType;
  private int priceCatNumber = -1;
  private long priceCatID = -1;
  private int catHoldNumber; // this cat hold number ranges from 1 .. 10.
  private int seatAttributes;
  private int salesStatus;
  private OffsetDateTime reserveExpDate;
  private OffsetDateTime holdExpDate;
  private String holdCode;
  private String priceClassCode;
  private String areaAlias;
  //added to store hold details...
  private String holdDesc;
  private String patronID;
  private String whoHold;

  //added to support span operations...
  private String spanKey;

  //To fix #3085 - Added by Than - 26 Sep 2013
  private String profileCode;

  transient private Row row;

//  private ProductSalesInfo salesInfo;

  /**
   * Instantiates a new seat model.
   */
  public SeatModel() {
    super();
    this.seatID = -1L;
  }

  /**
   * Instantiates a new seat model.
   * @param seatID the seat id
   * @param blockID the block id
   */
  public SeatModel(long seatID, long blockID) {
    super(blockID);
    this.seatID = seatID;
  }

  /**
   * Instantiates a new seat model.
   * @param seatID the seat id
   * @param blockID the block id
   * @param seatInvID the seat inv id
   */
  public SeatModel(long seatID, long blockID, long seatInvID) {
    this(seatID, blockID);
    this.seatInvID = seatInvID;
  }

  /**
   * Instantiates a new seat model.
   * @param seatID the seat id
   * @param blockID the block id
   * @param spanKey the span key
   */
  public SeatModel(long seatID, long blockID, String spanKey) {
    this(seatID, blockID);
    this.spanKey = spanKey;
  }

  /**
   * Instantiates a new seat model.
   * @param seatID the seat id
   * @param blockID the block id
   * @param seatInvID the seat inv id
   * @param spanKey the span key
   */
  public SeatModel(long seatID, long blockID, long seatInvID, String spanKey) {
    this(seatID, blockID, seatInvID);
    this.spanKey = spanKey;
  }

  public SeatModel(long seatID, long blockID, long seatInvID, String spanKey, String displayValue,
                   int seatNo, int rank, boolean isVisible, String coordinates, int seatType,
                   int seatAttributes, String nearestEntrance, String ticketType, int salesStatus,
                   String priceClassCode, String areaAlias, long priceCatID) {
    this(seatID, blockID, seatInvID, spanKey);
    this.setDisplayValue(displayValue);
    this.seatNo = seatNo;
    this.rank = rank;
    this.setVisible(isVisible);
    String[] points = coordinates.split(";");
    this.setXPosition(Integer.parseInt(points[0]));
    this.setYPosition(Integer.parseInt(points[1]));
    this.seatType = seatType;
    this.seatAttributes = seatAttributes;
    this.nearestEntrance = nearestEntrance;
    this.ticketType = ticketType;
    this.salesStatus = salesStatus;
    this.priceClassCode = priceClassCode;
    this.areaAlias = areaAlias;
    this.priceCatID = priceCatID;
  }


  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#toString()
   */
  public String toString() {
    return new StringBuffer().append("Seat Details--> SeatID: ").append(seatID).append(", State :")
        .append(state).append("\n").toString();
  }

  /**
   * Gets the price cat number.
   * @return the price cat number
   */
  public int getPriceCatNumber() {
    return priceCatNumber;
  }

  /**
   * Sets the price cat number.
   * @param priceCatNumber the new price cat number
   */
  public void setPriceCatNumber(int priceCatNumber) {
    markModified();
    this.priceCatNumber = priceCatNumber;
  }

  /**
   * Gets the price cat id.
   * @return the price cat id
   */
  public long getPriceCatID() {
    return priceCatID;
  }

  /**
   * Sets the price cat id.
   * @param priceCatID the new price cat id
   */
  public void setPriceCatID(long priceCatID) {
    markModified();
    //row.refreshRowSpan( this, priceCatID );
    this.priceCatID = priceCatID;
  }

  /**
   * Gets the cat hold number.
   * @return the cat hold number
   */
  public int getCatHoldNumber() {
    return catHoldNumber;
  }

  /**
   * Sets the cat hold number.
   * @param catHoldNumber the new cat hold number
   */
  public void setCatHoldNumber(int catHoldNumber) {
    markModified();
    this.catHoldNumber = catHoldNumber;
  }

  /**
   * Gets the seat id.
   * @return the seat id
   */
  public long getSeatID() {
    return seatID;
  }

  /**
   * Gets the seat inv id.
   * @return the seat inv id
   */
  public long getSeatInvID() {
    return seatInvID;
  }

  /**
   * Gets the no.
   * @return the no
   */
  public int getNo() {
    return seatNo;
  }

  /**
   * Sets the no.
   * @param seatNo the new no
   */
  public void setNo(int seatNo) {
    markModified();
    this.seatNo = seatNo;
  }

  /**
   * Gets the rank.
   * @return the rank
   */
  public long getRank() {
    return rank;
  }

  /**
   * Sets the rank.
   * @param rank the new rank
   */
  public void setRank(long rank) {
    markModified();
    this.rank = rank;
  }

  /**
   * Gets the seat type.
   * @return the seat type
   */
  public int getSeatType() {
    return seatType;
  }

  /**
   * Sets the seat type.
   * @param seatType the new seat type
   */
  public void setSeatType(int seatType) {
    markModified();
    this.seatType = seatType;
  }

  /**
   * Gets the nearest entrance.
   * @return the nearest entrance
   */
  public String getNearestEntrance() {
    return nearestEntrance;
  }

  /**
   * Sets the nearest entrance.
   * @param entrance the new nearest entrance
   */
  public void setNearestEntrance(String entrance) {
    markModified();
    this.nearestEntrance = entrance;
  }

  /**
   * Gets the ticket type.
   * @return the ticket type
   */
  public String getTicketType() {
    return ticketType;
  }

  /**
   * Sets the ticket type.
   * @param ticketType the new ticket type
   */
  public void setTicketType(String ticketType) {
    markModified();
    this.ticketType = ticketType;
  }

  /**
   * Gets the area alias.
   * @return the area alias
   */
  public String getAreaAlias() {
    return areaAlias;
  }

  /**
   * Sets the area alias.
   * @param areaAlias the new area alias
   */
  public void setAreaAlias(String areaAlias) {
    markModified();
    this.areaAlias = areaAlias;
  }

  /**
   * Gets the state.
   * @return the state
   */
  public int getState() {
    return state;
  }

  /**
   * Sets the state.
   * @param state the new state
   */
  public void setState(int state) {
    this.state = state;
  }

  /**
   * Gets the row.
   * @return the row
   */
  public Row getRow() {
    return row;
  }

  /**
   * Sets the row.
   * @param row the new row
   */
  public void setRow(Row row) {
    markModified();
    this.row = row;
  }

  /**
   * Gets the sales status.
   * @return the sales status
   */
  public int getSalesStatus() {
    return salesStatus;
  }

  /**
   * Sets the sales status.
   * @param salesStatus the new sales status
   */
  public void setSalesStatus(int salesStatus) {
    markModified();
    this.salesStatus = salesStatus;
    /*if (row != null && salesStatus != InventorySeat.SALES_STATUS_AVAILABLE) {
      row.markUnavailable(this);
    }*/
  }

  /**
   * Gets the hold code.
   * @return the hold code
   */
  public String getHoldCode() {
    return holdCode;
  }

  /**
   * Sets the hold code.
   * @param holdCode the new hold code
   */
  public void setHoldCode(String holdCode) {
    markModified();
    this.holdCode = holdCode;
  }

  /**
   * Gets the price class code.
   * @return the price class code
   */
  public String getPriceClassCode() {
    return priceClassCode;
  }

  /**
   * Sets the price class code.
   * @param priceClassCode the new price class code
   */
  public void setPriceClassCode(String priceClassCode) {
    markModified();
    this.priceClassCode = priceClassCode;
  }

  /**
   * Gets the reserve exp date.
   * @return the reserve exp date
   */
  public OffsetDateTime getReserveExpDate() {
    return reserveExpDate;
  }

  /**
   * Sets the reserve exp date.
   * @param time the new reserve exp date
   */
  public void setReserveExpDate(OffsetDateTime time) {
    markModified();
    reserveExpDate = time;
  }

  /**
   * Sets the reserve exp date.
   * @param time the new reserve exp date
   */
  public void setReserveExpDate(java.sql.Timestamp time) {
    markModified();
    reserveExpDate = null;
    if (time != null) {
      reserveExpDate = OffsetDateTime.from(time.toInstant());
    }
  }

  /**
   * Gets the reserve exp date.
   * @return the reserve exp date
   */
  public OffsetDateTime getHoldExpDate() {
    return holdExpDate;
  }

  /**
   * Sets the reserve exp date.
   * @param time the new reserve exp date
   */
  public void setHoldExpDate(OffsetDateTime time) {
    markModified();
    holdExpDate = time;
  }

  /**
   * Sets the reserve exp date.
   * @param time the new reserve exp date
   */
  public void setHoldExpDate(java.sql.Timestamp time) {
    markModified();
    holdExpDate = null;
    if (time != null) {
      holdExpDate = OffsetDateTime.from(time.toInstant());
    }
  }

  /**
   * Gets the seat attributes.
   * @return the seat attributes
   */
  public int getSeatAttributes() {
    return seatAttributes;
  }

  /**
   * Sets the seat attributes.
   * @param attribute the new seat attributes
   */
  public void setSeatAttributes(int attribute) {
    markModified();
    seatAttributes = attribute;
  }

  /**
   * Gets the sales info.
   * @return the sales info
   */
  /*public ProductSalesInfo getSalesInfo() {
    return salesInfo;
  }*/

  /**
   * Sets the sales info.
   * @param salesInfo the new sales info
   */
  /*public void setSalesInfo(ProductSalesInfo salesInfo) {
    markModified();
    this.salesInfo = salesInfo;
  }*/

  /**
   * Gets the hold desc.
   * @return the hold desc
   */
  public String getHoldDesc() {
    return holdDesc;
  }

  /**
   * Sets the hold desc.
   * @param holdDesc the new hold desc
   */
  public void setHoldDesc(String holdDesc) {
    markModified();
    this.holdDesc = holdDesc;
  }

  /**
   * Gets the patron id.
   * @return the patron id
   */
  public String getPatronID() {
    return patronID;
  }

  /**
   * Sets the patron id.
   * @param patronID the new patron id
   */
  public void setPatronID(String patronID) {
    markModified();
    this.patronID = patronID;
  }

  /**
   * Gets the who hold.
   * @return the who hold
   */
  public String getWhoHold() {
    return whoHold;
  }

  /**
   * Sets the who hold.
   * @param whoHold the new who hold
   */
  public void setWhoHold(String whoHold) {
    markModified();
    this.whoHold = whoHold;
  }

  /**
   * Gets the span key.
   * @return the span key
   */
  public String getSpanKey() {
    return spanKey;
  }

  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#setVisible(boolean)
   */
  /*public void setVisible(boolean visible) {
    super.setVisible(visible);
    if ((!visible) && (row != null)) {
      row.markUnavailable(this);
    } else if ((visible) &&
        (salesStatus == InventorySeat.SALES_STATUS_AVAILABLE) &&
        (row != null)) {
      row.markAvailable(this);
      row.refreshRowSpan(this, this.priceCatID);
    }
  }*/

  /**
   * Sets the aisle seat.
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public void setAisleSeat() {
    markModified();
    seatAttributes |= AISLE_SEAT;
  }

  /**
   * Unset aisle seat.
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public void unsetAisleSeat() {
    markModified();
    if (isAisleSeat()) {
      seatAttributes ^= AISLE_SEAT;
    }
  }

  /**
   * Sets the toilet seat.
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public void setToiletSeat() {
    markModified();
    seatAttributes |= TOILET_SEAT;
  }

  /**
   * Unset toilet seat.
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public void unsetToiletSeat() {
    markModified();
    if (isToiletSeat()) {
      seatAttributes ^= TOILET_SEAT;
    }
  }

  /**
   * Checks if is aisle seat.
   * @return true, if is aisle seat
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public boolean isAisleSeat() {
    return (seatAttributes & AISLE_SEAT) != 0;
  }

  /**
   * Checks if is toilet seat.
   * @return true, if is toilet seat
   * @deprecated don't depend on this ... use SeatAttributes instead
   */
  public boolean isToiletSeat() {
    return (seatAttributes & TOILET_SEAT) != 0;
  }

  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#markClean()
   */
  public void markClean() {
    state = CLEAN;
  }

  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#markNew()
   */
  public void markNew() {
    state = NEW;
  }

  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#markModified()
   */
  public void markModified() {
    if (state != NEW && state != REMOVE) {
      state = UPDATE;
    }
  }

  /* (non-Javadoc)
   * @see com.ecquaria.tools.uimodel.ComponentModel#markRemoved()
   */
  public void markRemoved() {
    state = REMOVE;
  }

  /**
   * Checks if is clean.
   * @return true, if is clean
   */
  public boolean isClean() {
    return (state == CLEAN);
  }

  /**
   * Checks if is new.
   * @return true, if is new
   */
  public boolean isNew() {
    return (state == NEW);
  }

  /**
   * Checks if is modified.
   * @return true, if is modified
   */
  public boolean isModified() {
    return (state == UPDATE);
  }

  /**
   * Checks if is removed.
   * @return true, if is removed
   */
  public boolean isRemoved() {
    return (state == REMOVE);
  }

  public String getProfileCode() {
    return profileCode;
  }

  public void setProfileCode(String profileCode) {
    this.profileCode = profileCode;
  }
}

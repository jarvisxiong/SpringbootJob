package com.stixcloud.patron.api.json;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "startDate", "endDate", "priceCategoryNum", "venue", "sectionType", "section",
    "level", "row", "seats"})
public class ProductJson implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -8425766456652307005L;

  @JsonProperty("name")
  private String name;
  @JsonProperty("startDate")
  private OffsetDateTime startDate;
  @JsonProperty("endDate")
  private OffsetDateTime endDate;
  @JsonProperty("priceCategoryNum")
  private String category;
  @JsonProperty("venue")
  private String venue;
  @JsonProperty("sectionType")
  private String sectionType;
  @JsonProperty("section")
  private String section;
  @JsonProperty("level")
  private String level;
  @JsonProperty("row")
  private String row;
  @JsonProperty("seats")
  private List<Long> seats;

  public ProductJson() {
  }

  public ProductJson(String name, OffsetDateTime startDate, OffsetDateTime endDate, String category,
      String venue, String sectionType, String section, String level, String row,
      List<Long> seats) {
    super();
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
    this.category = category;
    this.venue = venue;
    this.sectionType = sectionType;
    this.section = section;
    this.level = level;
    this.row = row;
    this.seats = seats;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the startDate
   */
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  /**
   * @param startDate the startDate to set
   */
  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  /**
   * @return the endDate
   */
  public OffsetDateTime getEndDate() {
    return endDate;
  }

  /**
   * @param endDate the endDate to set
   */
  public void setEndDate(OffsetDateTime endDate) {
    this.endDate = endDate;
  }

  /**
   * @return the category
   */
  public String getCategory() {
    return category;
  }

  /**
   * @param category the category to set
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * @return the venue
   */
  public String getVenue() {
    return venue;
  }

  /**
   * @param venue the venue to set
   */
  public void setVenue(String venue) {
    this.venue = venue;
  }

  /**
   * @return the sectionType
   */
  public String getSectionType() {
    return sectionType;
  }

  /**
   * @param sectionType the sectionType to set
   */
  public void setSectionType(String sectionType) {
    this.sectionType = sectionType;
  }

  /**
   * @return the section
   */
  public String getSection() {
    return section;
  }

  /**
   * @param section the section to set
   */
  public void setSection(String section) {
    this.section = section;
  }

  /**
   * @return the level
   */
  public String getLevel() {
    return level;
  }

  /**
   * @param level the level to set
   */
  public void setLevel(String level) {
    this.level = level;
  }

  /**
   * @return the row
   */
  public String getRow() {
    return row;
  }

  /**
   * @param row the row to set
   */
  public void setRow(String row) {
    this.row = row;
  }

  /**
   * @return the seats
   */
  public List<Long> getSeats() {
    return seats;
  }

  /**
   * @param seats the seats to set
   */
  public void setSeats(List<Long> seats) {
    this.seats = seats;
  }


}

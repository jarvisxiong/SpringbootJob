package com.stixcloud.product.api;

/**
 * Created by chongye on 13-Sep-16.
 */

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "overview",
    "detail"
})
public class Seatmap2D implements Serializable {

  @JsonProperty("overview")
  private String overview;
  @JsonProperty("detail")
  private String detail;

  public Seatmap2D() {
  }

  public Seatmap2D(String overview, String detail) {
    this.overview = overview;
    this.detail = detail;
  }

  /**
   * @return The overview
   */
  @JsonProperty("overview")
  public String getOverview() {
    return overview;
  }

  /**
   * @param overview The overview
   */
  @JsonProperty("overview")
  public void setOverview(String overview) {
    this.overview = overview;
  }

  /**
   * @return The detail
   */
  @JsonProperty("detail")
  public String getDetail() {
    return detail;
  }

  /**
   * @param detail The detail
   */
  @JsonProperty("detail")
  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("overview", overview)
        .append("detail", detail)
        .toString();
  }
}

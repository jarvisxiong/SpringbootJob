package com.stixcloud.patron.api.json;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"type", "no"})
public class IdentificationJson implements Serializable {

  private static final long serialVersionUID = -4567588170761125746L;
  @JsonProperty("type")
  private String type;
  @JsonProperty("no")
  private String no;

  public IdentificationJson() {

  }

  public IdentificationJson(String type, String no) {
    this.type = type;
    this.no = no;
  }

  /**
   * @return the type.
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * @param type the type
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the no.
   */
  @JsonProperty("no")
  public String getNo() {
    return no;
  }

  /**
   * @param statusMessage the statusMessage
   */
  @JsonProperty("no")
  public void setNo(String no) {
    this.no = no;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    IdentificationJson that = (IdentificationJson) o;
    return new EqualsBuilder().append(type, that.getType()).append(no, that.getNo()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(type).append(no).toHashCode();
  }

}

package com.stixcloud.patron.api;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.PatronSolicitationJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"solicitationList"})
public class PatronSolicitationResponse implements Serializable {

  private static final long serialVersionUID = -1645634042643511780L;

  @JsonProperty("solicitationList")
  private List<PatronSolicitationJson> solicitationList;

  public PatronSolicitationResponse() {

  }

  public PatronSolicitationResponse(List<PatronSolicitationJson> solicitationList) {
    this.solicitationList = solicitationList;
  }

  /**
   * @return the solicitationList
   */
  @JsonProperty("solicitationList")
  public List<PatronSolicitationJson> getSolicitationList() {
    return solicitationList;
  }

  /**
   * @param solicitationList the solicitationList to set
   */
  @JsonProperty("solicitationList")
  public void setSolicitationList(List<PatronSolicitationJson> solicitationList) {
    this.solicitationList = solicitationList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PatronSolicitationResponse that = (PatronSolicitationResponse) o;
    return new EqualsBuilder().append(solicitationList, that.getSolicitationList()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(solicitationList).toHashCode();
  }

}

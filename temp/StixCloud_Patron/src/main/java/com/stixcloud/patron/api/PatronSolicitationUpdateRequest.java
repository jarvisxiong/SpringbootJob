package com.stixcloud.patron.api;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.stixcloud.patron.api.json.SolicitationJson;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"solicitationList"})
public class PatronSolicitationUpdateRequest {

  private List<SolicitationJson> solicitationList;

  public PatronSolicitationUpdateRequest() {

  }

  public PatronSolicitationUpdateRequest(List<SolicitationJson> solicitationList) {
    this.solicitationList = solicitationList;
  }

  /**
   * @return the solicitationList
   */
  @JsonProperty("solicitationList")
  public List<SolicitationJson> getSolicitationList() {
    return solicitationList;
  }

  /**
   * @param solicitationList the solicitationList to set
   */
  @JsonProperty("solicitationList")
  public void setSolicitationList(List<SolicitationJson> solicitationList) {
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

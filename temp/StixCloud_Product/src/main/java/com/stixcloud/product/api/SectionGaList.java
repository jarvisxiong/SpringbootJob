package com.stixcloud.product.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

/**
 * Created by dbthan on 06-Sep-16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({"gaSeatSectionList"})
public class SectionGaList {

  @JsonProperty("gaSeatSectionList")
  private List<SectionGa> gaSeatSectionList = new ArrayList<SectionGa>();

  public SectionGaList() {

  }

  public SectionGaList(List<SectionGa> gaSeatSectionList) {
    this.gaSeatSectionList = gaSeatSectionList;
  }

  /**
   * @return The sectionGaList.
   */
  public List<SectionGa> getGaSeatSectionList() {
    return gaSeatSectionList;
  }

  /**
   * @param sectionGaList.
   */
  public void setGaSeatSectionList(List<SectionGa> gaSeatSectionList) {
    this.gaSeatSectionList = gaSeatSectionList;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("sectionGaList", gaSeatSectionList).toString();
  }

}

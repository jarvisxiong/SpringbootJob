package com.stixcloud.product.api.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LinkJsonObject {

  @JsonProperty("rel")
  private String rel;
  @JsonProperty("href")
  private String href;
  
  public String getRel() {
    return rel;
  }
  public void setRel(String rel) {
    this.rel = rel;
  }
  public String getHref() {
    return href;
  }
  public void setHref(String href) {
    this.href = href;
  }
  
  
}

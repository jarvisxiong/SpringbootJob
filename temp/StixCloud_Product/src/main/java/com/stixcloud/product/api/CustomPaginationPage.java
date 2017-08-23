package com.stixcloud.product.api;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.stixcloud.product.api.json.LinkJsonObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPaginationPage<T> extends PageImpl<T> implements Serializable{

  private static final long serialVersionUID = 3784098214492484229L;
  @JsonProperty("links")
  private List<LinkJsonObject> links;
  @JsonProperty("httpStatus")
  private String httpStatus;
  @JsonProperty("url")
  private String url;
  
  public CustomPaginationPage(List<T> content) {
    super(content);
    setHttpStatus(HttpStatus.OK.toString());
    setUrl("");
  }
  
  public CustomPaginationPage(List<T> content, Pageable pageable, long total) {
    super(content, pageable, total);
  }

  public List<LinkJsonObject> getLinks() {
    return links;
  }

  public void setLinks(List<LinkJsonObject> links) {
    this.links = links;
  }

	public String getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}  
  
}

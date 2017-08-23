package com.stixcloud.domain;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "PRODUCT_CONFIG")
public class ProductConfig implements Serializable{
	  private static final long serialVersionUID = -7916961705797808113L;
	  private String productName;
	  private String festival;
	  private String festivalCode;
	  private String productGroupCode;
	  private String venueName;
	  private String organization;
	  private String productCategory;
	  private String productCode;
	  private OffsetDateTime updatedDate;
	  private OffsetDateTime startDateTime;
	  private OffsetDateTime endDateTime;
	  private Long productId;
	  private Long venueId;
	  private Long venueLayoutConfig_ID;
	  
	  @Id
	  @Column(name="PRODUCTID", nullable=false)
	  public Long getProductId()
	  {
	    return this.productId;
	  }
	  
	  public void setProductId(Long productId)
	  {
	    this.productId = productId;
	  }
	  
	  @Column(name="PRODUCTNAME", nullable=false)
	  public String getProductName()
	  {
	    return this.productName;
	  }
	  
	  public void setProductName(String productName)
	  {
	    this.productName = productName;
	  }
	  
	  @Column(name="FESTIVAL")
	  public String getFestival()
	  {
	    return this.festival;
	  }
	  
	  public void setFestival(String festival)
	  {
	    this.festival = festival;
	  }
	  
	  @Column(name="FESTIVALCODE")
	  public String getFestivalCode()
	  {
	    return this.festivalCode;
	  }
	  
	  public void setFestivalCode(String festivalCode)
	  {
	    this.festivalCode = festivalCode;
	  }
	  
	  @Column(name="PRODUCTGROUPCODE", nullable=false)
	  public String getProductGroupCode()
	  {
	    return this.productGroupCode;
	  }
	  
	  public void setProductGroupCode(String productGroupCode)
	  {
	    this.productGroupCode = productGroupCode;
	  }
	  
	  @Column(name="VENUENAME")
	  public String getVenueName()
	  {
	    return this.venueName;
	  }
	  
	  public void setVenueName(String venueName)
	  {
	    this.venueName = venueName;
	  }
	  
	  @Column(name="ORGANIZATION", nullable=false)
	  public String getOrganization()
	  {
	    return this.organization;
	  }
	  
	  public void setOrganization(String organization)
	  {
	    this.organization = organization;
	  }
	  
	  @Column(name="PRODUCTCATEGORY", nullable=false)
	  public String getProductCategory()
	  {
	    return this.productCategory;
	  }
	  
	  public void setProductCategory(String productCategory)
	  {
	    this.productCategory = productCategory;
	  }
	  
	  @Column(name="PRODUCTCODE", nullable=false)
	  public String getProductCode()
	  {
	    return this.productCode;
	  }
	  
	  public void setProductCode(String productCode)
	  {
	    this.productCode = productCode;
	  }
	  
	  @Column(name="VENUELAYOUTCONFIG_ID")
	  public Long getVenueLayoutConfig_ID()
	  {
	    return this.venueLayoutConfig_ID;
	  }
	  
	  public void setVenueLayoutConfig_ID(Long venueLayoutConfig_ID)
	  {
	    this.venueLayoutConfig_ID = venueLayoutConfig_ID;
	  }
	  
	  @Column(name="UPDATEDDATE", nullable=false)
	  public OffsetDateTime getUpdatedDate()
	  {
	    return this.updatedDate;
	  }
	  
	  public void setUpdatedDate(OffsetDateTime updatedDate)
	  {
	    this.updatedDate = updatedDate;
	  }
	  
	  @Column(name="VENUEID")
	  public Long getVenueId()
	  {
	    return this.venueId;
	  }
	  
	  public void setVenueId(Long venueId)
	  {
	    this.venueId = venueId;
	  }
	  
	  @Column(name="STARTDATETIME")
	  public OffsetDateTime getStartDateTime()
	  {
	    return this.startDateTime;
	  }
	  
	  public void setStartDateTime(OffsetDateTime startDateTime)
	  {
	    this.startDateTime = startDateTime;
	  }
	  
	  @Column(name="ENDDATETIME")
	  public OffsetDateTime getEndDateTime()
	  {
	    return this.endDateTime;
	  }
	  
	  public void setEndDateTime(OffsetDateTime endDateTime)
	  {
	    this.endDateTime = endDateTime;
	  }
	
}

package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by chetan on 18/1/2017.
 */
@Entity
@Table(name = "PRDT_GROUP_PROMOTER_LIVE")
public class ProductGroupPromoterLive {
  private Long prdtGrpPromoterID;

  @Id
  @Column(name = "PRDTGRPPROMOTERID", nullable = false, precision = 0)
  public Long getPrdtGrpPromoterID() {
    return prdtGrpPromoterID;
  }

  public void setPrdtGrpPromoterID(Long prdtGrpPromoterID) {
    this.prdtGrpPromoterID = prdtGrpPromoterID;
  }

  private Long prdtGrpId;

  @Column(name = "PRDT_GRP_ID", nullable = false, length = 255)
  public Long getPrdtGrpId() {
    return prdtGrpId;
  }

  public void setPrdtGrpId(Long prdtGrpId) {
    this.prdtGrpId = prdtGrpId;
  }

  private Long promoterId;

  @Column(name = "PROMOTER_ID", nullable = false, length = 50)
  public Long getPromoterId() {
    return promoterId;
  }

  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }

  private String promoterGLCode;

  @Column(name = "PROMOTERGLCODE", nullable = true, length = 50)
  public String getPromoterGLCode() {
    return promoterGLCode;
  }

  public void setPromoterGLCode(String promoterGLCode) {
    this.promoterGLCode = promoterGLCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ProductGroupPromoterLive that = (ProductGroupPromoterLive) o;

    return Objects.equals(this.prdtGrpPromoterID, that.prdtGrpPromoterID) &&
        Objects.equals(this.prdtGrpId, that.prdtGrpId) &&
        Objects.equals(this.promoterId, that.promoterId) &&
        Objects.equals(this.promoterGLCode, that.promoterGLCode);
  }

  @Override
  public int hashCode() {
    return Objects
        .hash(prdtGrpPromoterID, prdtGrpId, promoterId, promoterGLCode);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("prdtGrpPromoterID", prdtGrpPromoterID)
        .append("prdtGrpId", prdtGrpId)
        .append("promoterId", promoterId)
        .append("promoterGLCode", promoterGLCode)
        .toString();
  }
}

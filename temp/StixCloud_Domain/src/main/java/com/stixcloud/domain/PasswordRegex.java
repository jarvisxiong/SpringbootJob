package com.stixcloud.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by sengkai on 3/9/2017.
 */
@Entity
@Table(name = "PASSWORD_REGEX")
public class PasswordRegex implements Serializable {

  private static final long serialVersionUID = 6231680727262204057L;
  private Long passwordregexid;
  private String expression;
  private Integer noofdigits;
  private Integer maxusagefrequency;
  private Long fileInfoId;
  private Long promotionPasswordId;

  public PasswordRegex() {
  }

  public PasswordRegex(Long passwordregexid, String expression, Integer noofdigits,
                       Integer maxusagefrequency, Long fileInfoId) {

    this.passwordregexid = passwordregexid;
    this.expression = expression;
    this.noofdigits = noofdigits;
    this.maxusagefrequency = maxusagefrequency;
    this.fileInfoId = fileInfoId;
  }

  @Id
  @Column(name = "PASSWORDREGEXID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "promotionRegexIdSeq")
  @GenericGenerator(name = "promotionRegexIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "PASSWORD_REGEX_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getPasswordregexid() {
    return passwordregexid;
  }

  public void setPasswordregexid(Long passwordregexid) {
    this.passwordregexid = passwordregexid;
  }

  @Column(name = "EXPRESSION", nullable = false, length = 50)
  public String getExpression() {
    return expression;
  }

  public void setExpression(String expression) {
    this.expression = expression;
  }

  @Column(name = "NOOFDIGITS", nullable = true, precision = 0)
  public Integer getNoofdigits() {
    return noofdigits;
  }

  public void setNoofdigits(Integer noofdigits) {
    this.noofdigits = noofdigits;
  }

  @Column(name = "MAXUSAGEFREQUENCY", nullable = false, precision = 0)
  public Integer getMaxusagefrequency() {
    return maxusagefrequency;
  }

  public void setMaxusagefrequency(Integer maxusagefrequency) {
    this.maxusagefrequency = maxusagefrequency;
  }

  @Column(name = "FILE_INFO_ID", nullable = true, precision = 0)
  public Long getFileInfoId() {
    return fileInfoId;
  }

  public void setFileInfoId(Long fileInfoId) {
    this.fileInfoId = fileInfoId;
  }

  @Column(name = "PROMOTION_PASSWORD_ID", nullable = false, precision = 0)
  public Long getPromotionPasswordId() {
    return promotionPasswordId;
  }

  public void setPromotionPasswordId(Long promotionPasswordId) {
    this.promotionPasswordId = promotionPasswordId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordRegex that = (PasswordRegex) o;
    return Objects.equals(passwordregexid, that.passwordregexid) &&
        Objects.equals(expression, that.expression) &&
        Objects.equals(noofdigits, that.noofdigits) &&
        Objects.equals(maxusagefrequency, that.maxusagefrequency) &&
        Objects.equals(fileInfoId, that.fileInfoId) &&
        Objects.equals(promotionPasswordId, that.promotionPasswordId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(passwordregexid, expression, noofdigits, maxusagefrequency, fileInfoId,
        promotionPasswordId);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("passwordregexid", passwordregexid)
        .append("expression", expression)
        .append("noofdigits", noofdigits)
        .append("maxusagefrequency", maxusagefrequency)
        .append("fileInfoId", fileInfoId)
        .append("promotionPasswordId", promotionPasswordId)
        .toString();
  }
}

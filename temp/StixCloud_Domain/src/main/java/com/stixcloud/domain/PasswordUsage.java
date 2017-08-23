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
@Table(name = "PASSWORD_USAGE")
public class PasswordUsage implements Serializable {

  private static final long serialVersionUID = -6551559008479276254L;
  private Long passwordusageid;
  private Long passwordRegexId;
  private Long promoPasswordId;
  private String password;
  private Integer usedtimes;

  public PasswordUsage() {
  }

  public PasswordUsage(Long passwordusageid, Long passwordRegexId, Long promoPasswordId,
                       String password, Integer usedtimes) {

    this.passwordusageid = passwordusageid;
    this.passwordRegexId = passwordRegexId;
    this.promoPasswordId = promoPasswordId;
    this.password = password;
    this.usedtimes = usedtimes;
  }

  @Id
  @Column(name = "PASSWORDUSAGEID", nullable = false, precision = 0)
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "passwordUsageIdSeq")
  @GenericGenerator(name = "passwordUsageIdSeq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "PASSWORD_USAGE_ID_SEQ"),
          @Parameter(name = "optimizer", value = "none")
      })
  public Long getPasswordusageid() {
    return passwordusageid;
  }

  public void setPasswordusageid(Long passwordusageid) {
    this.passwordusageid = passwordusageid;
  }

  @Column(name = "PASSWORD_REGEX_ID", nullable = false, precision = 0)
  public Long getPasswordRegexId() {
    return passwordRegexId;
  }

  public void setPasswordRegexId(Long passwordRegexId) {
    this.passwordRegexId = passwordRegexId;
  }

  @Column(name = "PROMOTION_PASSWORD_ID", nullable = false, precision = 0)
  public Long getPromoPasswordId() {
    return promoPasswordId;
  }

  public void setPromoPasswordId(Long promotionPasswordId) {
    this.promoPasswordId = promotionPasswordId;
  }

  @Column(name = "PASSWORD", nullable = false, length = 50)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Column(name = "USEDTIMES", nullable = false, precision = 0)
  public Integer getUsedtimes() {
    return usedtimes;
  }

  public void setUsedtimes(Integer usedtimes) {
    this.usedtimes = usedtimes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PasswordUsage that = (PasswordUsage) o;
    return Objects.equals(passwordusageid, that.passwordusageid) &&
        Objects.equals(passwordRegexId, that.passwordRegexId) &&
        Objects.equals(promoPasswordId, that.promoPasswordId) &&
        Objects.equals(password, that.password) &&
        Objects.equals(usedtimes, that.usedtimes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(passwordusageid, passwordRegexId, promoPasswordId, password, usedtimes);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("passwordusageid", passwordusageid)
        .append("passwordRegexId", passwordRegexId)
        .append("promoPasswordId", promoPasswordId)
        .append("password", password)
        .append("usedtimes", usedtimes)
        .toString();
  }
}

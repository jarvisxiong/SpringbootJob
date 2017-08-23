package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "flags",
    "solicitationList"
})
public class PatronSolicitation implements Serializable {

  private static final long serialVersionUID = -7518429626729406487L;
  @JsonProperty("flags")
  private Flags flags;
  @JsonProperty("solicitationList")
  private List<Solicitation> solicitationList = new ArrayList<Solicitation>();

  public PatronSolicitation() {
  }

  private PatronSolicitation(Builder builder) {
    setFlags(builder.flags);
    setSolicitationList(builder.solicitationList);
  }

  /**
   * @return The flags
   */
  @JsonProperty("flags")
  public Flags getFlags() {
    return flags;
  }

  /**
   * @param flags The flags
   */
  @JsonProperty("flags")
  public void setFlags(Flags flags) {
    this.flags = flags;
  }

  /**
   * @return The solicitationList
   */
  @JsonProperty("solicitationList")
  public List<Solicitation> getSolicitationList() {
    return solicitationList;
  }

  /**
   * @param solicitationList The solicitationList
   */
  @JsonProperty("solicitationList")
  public void setSolicitationList(List<Solicitation> solicitationList) {
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

    PatronSolicitation that = (PatronSolicitation) o;

    return Objects.equals(this.flags, that.flags) &&
        Objects.equals(this.solicitationList, that.solicitationList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(flags, solicitationList);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("flags", flags)
        .append("solicitationList", solicitationList)
        .toString();
  }

  public static final class Builder {
    private Flags flags;
    private List<Solicitation> solicitationList;

    public Builder() {
    }

    public Builder(PatronSolicitation copy) {
      this.flags = copy.flags;
      this.solicitationList = copy.solicitationList;
    }

    public Builder flags(Flags flags) {
      this.flags = flags;
      return this;
    }

    public Builder solicitationList(List<Solicitation> solicitationList) {
      this.solicitationList = solicitationList;
      return this;
    }

    public PatronSolicitation build() {
      return new PatronSolicitation(this);
    }
  }
}
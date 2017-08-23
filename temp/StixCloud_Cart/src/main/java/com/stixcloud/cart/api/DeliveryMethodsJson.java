package com.stixcloud.cart.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by chongye on 19/10/2016.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeliveryMethodsJson implements Serializable {

  private static final long serialVersionUID = 3412773991386548812L;
  @JsonProperty("deliveryMethodList")
  private List<DeliveryMethodJson> deliveryMethodJsons;

  public DeliveryMethodsJson() {
  }

  public DeliveryMethodsJson(
      List<DeliveryMethodJson> deliveryMethodJsons) {
    this.deliveryMethodJsons = deliveryMethodJsons;
  }

  @JsonProperty("deliveryMethodList")
  public List<DeliveryMethodJson> getDeliveryMethodJsons() {
    return deliveryMethodJsons;
  }

  @JsonProperty("deliveryMethodList")
  public void setDeliveryMethodJsons(
      List<DeliveryMethodJson> deliveryMethodJsons) {
    this.deliveryMethodJsons = deliveryMethodJsons;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    DeliveryMethodsJson that = (DeliveryMethodsJson) o;

    return Objects.equals(this.deliveryMethodJsons, that.deliveryMethodJsons);
  }

  @Override
  public int hashCode() {
    return Objects.hash(deliveryMethodJsons);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("deliveryMethodJsons", deliveryMethodJsons)
        .toString();
  }
}

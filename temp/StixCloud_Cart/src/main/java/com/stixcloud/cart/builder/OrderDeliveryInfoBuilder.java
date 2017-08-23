package com.stixcloud.cart.builder;

import com.stixcloud.domain.OrderDeliveryInfo;

/**
 * Created by chongye on 23/12/2016.
 */
public final class OrderDeliveryInfoBuilder {
  private Long orderdeliveryinfoid;
  private Long deliveryMethod;
  private Integer addressType;
  private String addrline1;
  private String addrline2;
  private String addrline3;
  private String postalcode;
  private String city;
  private String state;
  private String country;
  private String email;
  private String mobilenum;

  private OrderDeliveryInfoBuilder() {
  }

  public static OrderDeliveryInfoBuilder anOrderDeliveryInfo() {
    return new OrderDeliveryInfoBuilder();
  }

  public OrderDeliveryInfoBuilder orderdeliveryinfoid(Long orderdeliveryinfoid) {
    this.orderdeliveryinfoid = orderdeliveryinfoid;
    return this;
  }

  public OrderDeliveryInfoBuilder deliveryMethod(Long deliveryMethod) {
    this.deliveryMethod = deliveryMethod;
    return this;
  }

  public OrderDeliveryInfoBuilder addressType(Integer addressType) {
    this.addressType = addressType;
    return this;
  }

  public OrderDeliveryInfoBuilder addrline1(String addrline1) {
    this.addrline1 = addrline1;
    return this;
  }

  public OrderDeliveryInfoBuilder addrline2(String addrline2) {
    this.addrline2 = addrline2;
    return this;
  }

  public OrderDeliveryInfoBuilder addrline3(String addrline3) {
    this.addrline3 = addrline3;
    return this;
  }

  public OrderDeliveryInfoBuilder postalcode(String postalcode) {
    this.postalcode = postalcode;
    return this;
  }

  public OrderDeliveryInfoBuilder city(String city) {
    this.city = city;
    return this;
  }

  public OrderDeliveryInfoBuilder state(String state) {
    this.state = state;
    return this;
  }

  public OrderDeliveryInfoBuilder country(String country) {
    this.country = country;
    return this;
  }

  public OrderDeliveryInfoBuilder email(String email) {
    this.email = email;
    return this;
  }

  public OrderDeliveryInfoBuilder mobilenum(String mobilenum) {
    this.mobilenum = mobilenum;
    return this;
  }

  public OrderDeliveryInfo build() {
    OrderDeliveryInfo orderDeliveryInfo = new OrderDeliveryInfo();
    orderDeliveryInfo.setOrderdeliveryinfoid(orderdeliveryinfoid);
    orderDeliveryInfo.setDeliveryMethod(deliveryMethod);
    orderDeliveryInfo.setAddressType(addressType);
    orderDeliveryInfo.setAddrline1(addrline1);
    orderDeliveryInfo.setAddrline2(addrline2);
    orderDeliveryInfo.setAddrline3(addrline3);
    orderDeliveryInfo.setPostalcode(postalcode);
    orderDeliveryInfo.setCity(city);
    orderDeliveryInfo.setState(state);
    orderDeliveryInfo.setCountry(country);
    orderDeliveryInfo.setEmail(email);
    orderDeliveryInfo.setMobilenum(mobilenum);
    return orderDeliveryInfo;
  }
}

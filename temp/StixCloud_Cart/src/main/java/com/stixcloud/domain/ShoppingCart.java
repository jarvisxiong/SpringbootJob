package com.stixcloud.domain;

import com.stixcloud.cart.SecurityUtils;
import com.stixcloud.cart.api.AddressJson;
import com.stixcloud.cart.api.PatronSolicitation;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by chongye on 28-Sep-16.
 */
@RedisHash(value = "shoppingcart")
public class ShoppingCart {
  @Id
  private String cartGuid;
  private LinkedList<CartItem> cartItems = new LinkedList<>();
  private String paymentMethodCode;
  private String deliveryMethodCode;
  private List<AddressJson> addressList;
  private PatronSolicitation patronSolicitation;
  private List<String> evoucherIdList;
  private String creditCardNo;
  private String creditCardExpiryYY;
  private String creditCardExpiryMM;
  private Long creditCardCSC;
  private String transactionReferenceNumber;
  private boolean purchaseTp = false;
  private String reservedTime;
  private boolean isFullyRedeemed = false;
  private String messageUuid;
  private BigDecimal payableAmount;

  @TimeToLive(unit=TimeUnit.MINUTES)
  private Long timeout;

  public ShoppingCart() {
  }

  private ShoppingCart(Builder builder) {
    cartGuid = builder.cartGuid;
    cartItems = builder.cartItems;
    paymentMethodCode = builder.paymentMethodCode;
    deliveryMethodCode = builder.deliveryMethodCode;
    addressList = builder.addressList;
    patronSolicitation = builder.patronSolicitation;
    evoucherIdList = builder.evoucherIdList;
    creditCardNo = builder.creditCardNo;
    creditCardExpiryYY = builder.creditCardExpiryYY;
    creditCardExpiryMM = builder.creditCardExpiryMM;
    creditCardCSC = builder.creditCardCSC;
    transactionReferenceNumber = builder.transactionReferenceNumber;
    purchaseTp = builder.purchaseTp;
    reservedTime = builder.reservedTime;
    isFullyRedeemed = builder.isFullyRedeemed;
    messageUuid = builder.messageUuid;
    timeout = builder.timeout;
    payableAmount=builder.payableAmount;
  }

  public String getCartGuid() {
    return cartGuid;
  }

  public LinkedList<CartItem> getCartItems() {
    return cartItems;
  }

  public void addToCart(List<CartItem> cartItems) {
    if (this.cartItems == null) {
      this.cartItems = new LinkedList<>();
    }
    this.cartItems.addAll(cartItems);
  }

  public String getPaymentMethodCode() {
    return paymentMethodCode;
  }

  public String getDeliveryMethodCode() {
    return deliveryMethodCode;
  }

  public List<AddressJson> getAddressList() {
    return addressList;
  }

  public PatronSolicitation getPatronSolicitation() {
    return patronSolicitation;
  }

  public List<String> getEvoucherIdList() {
    return evoucherIdList;
  }

  public String getCreditCardNo() {
    return creditCardNo;
  }

  public String getCreditCardExpiryYY() {
    return creditCardExpiryYY;
  }

  public String getCreditCardExpiryMM() {
    return creditCardExpiryMM;
  }

  public Long getCreditCardCSC() {
    return creditCardCSC;
  }

  public String getTransactionReferenceNumber() {
    return transactionReferenceNumber;
  }

  public boolean getPurchaseTp() {
    return purchaseTp;
  }

  public Long getTimeout() {
    return timeout;
  }

  public String getReservedTime() {
    return reservedTime;
  }

  public boolean getIsFullyRedeemed() {
    return isFullyRedeemed;
  }

  public String getMessageUuid() {
    return messageUuid;
  }
  
  public BigDecimal getPayableAmount() {
	return payableAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ShoppingCart that = (ShoppingCart) o;

    return Objects.equals(this.addressList, that.addressList) &&
        Objects.equals(this.cartGuid, that.cartGuid) &&
        Objects.equals(this.cartItems, that.cartItems) &&
        Objects.equals(this.creditCardCSC, that.creditCardCSC) &&
        Objects.equals(this.creditCardExpiryMM, that.creditCardExpiryMM) &&
        Objects.equals(this.creditCardExpiryYY, that.creditCardExpiryYY) &&
        Objects.equals(this.creditCardNo, that.creditCardNo) &&
        Objects.equals(this.deliveryMethodCode, that.deliveryMethodCode) &&
        Objects.equals(this.evoucherIdList, that.evoucherIdList) &&
        Objects.equals(this.isFullyRedeemed, that.isFullyRedeemed) &&
        Objects.equals(this.messageUuid, that.messageUuid) &&
        Objects.equals(this.patronSolicitation, that.patronSolicitation) &&
        Objects.equals(this.paymentMethodCode, that.paymentMethodCode) &&
        Objects.equals(this.purchaseTp, that.purchaseTp) &&
        Objects.equals(this.reservedTime, that.reservedTime) &&
        Objects.equals(this.timeout, that.timeout) &&
        Objects.equals(this.payableAmount, that.payableAmount) &&
        Objects.equals(this.transactionReferenceNumber, that.transactionReferenceNumber);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this,
        ToStringStyle.JSON_STYLE)
        .append("cartGuid", cartGuid)
        .append("cartItems", cartItems)
        .append("paymentMethodCode", paymentMethodCode)
        .append("deliveryMethodCode", deliveryMethodCode)
        .append("addressList", addressList)
        .append("patronSolicitation", patronSolicitation)
        .append("evoucherIdList", evoucherIdList)
        .append("creditCardNo", SecurityUtils.maskPartial(creditCardNo))
        .append("creditCardExpiryYY", creditCardExpiryYY)
        .append("creditCardExpiryMM", creditCardExpiryMM)
        .append("creditCardCSC", creditCardCSC)
        .append("transactionReferenceNumber", transactionReferenceNumber)
        .append("purchaseTp", purchaseTp)
        .append("reservedTime", reservedTime)
        .append("isFullyRedeemed", isFullyRedeemed)
        .append("messageUuid", messageUuid)
        .append("timeout", timeout)
        .append("payableAmount", payableAmount)
        .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressList, cartGuid, cartItems, creditCardCSC, creditCardExpiryMM,
        creditCardExpiryYY,
        creditCardNo, deliveryMethodCode, evoucherIdList, isFullyRedeemed, messageUuid,
        patronSolicitation, paymentMethodCode, purchaseTp, reservedTime, timeout,
        transactionReferenceNumber,payableAmount);
  }

  public static final class Builder {
    private String cartGuid;
    private LinkedList<CartItem> cartItems;
    private String paymentMethodCode;
    private String deliveryMethodCode;
    private List<AddressJson> addressList;
    private PatronSolicitation patronSolicitation;
    private List<String> evoucherIdList;
    private String creditCardNo;
    private String creditCardExpiryYY;
    private String creditCardExpiryMM;
    private Long creditCardCSC;
    private String transactionReferenceNumber;
    private boolean purchaseTp = false;
    private String reservedTime;
    private boolean isFullyRedeemed = false;
    private String messageUuid;
    private Long timeout;
    private BigDecimal payableAmount;

    public Builder() {
    }

    public Builder(ShoppingCart copy) {
      this.cartGuid = copy.cartGuid;
      this.cartItems = copy.cartItems;
      this.paymentMethodCode = copy.paymentMethodCode;
      this.deliveryMethodCode = copy.deliveryMethodCode;
      this.addressList = copy.addressList;
      this.patronSolicitation = copy.patronSolicitation;
      this.evoucherIdList = copy.evoucherIdList;
      this.creditCardNo = copy.creditCardNo;
      this.creditCardExpiryYY = copy.creditCardExpiryYY;
      this.creditCardExpiryMM = copy.creditCardExpiryMM;
      this.creditCardCSC = copy.creditCardCSC;
      this.transactionReferenceNumber = copy.transactionReferenceNumber;
      this.purchaseTp = copy.purchaseTp;
      this.reservedTime = copy.reservedTime;
      this.isFullyRedeemed = copy.isFullyRedeemed;
      this.messageUuid = copy.messageUuid;
      this.timeout = copy.timeout;
      this.payableAmount=copy.payableAmount;
    }

    public Builder cartGuid(String val) {
      cartGuid = val;
      return this;
    }

    public Builder cartItems(LinkedList<CartItem> val) {
      cartItems = val;
      return this;
    }

    public Builder paymentMethodCode(String val) {
      paymentMethodCode = val;
      return this;
    }

    public Builder deliveryMethodCode(String val) {
      deliveryMethodCode = val;
      return this;
    }

    public Builder addressList(List<AddressJson> val) {
      addressList = val;
      return this;
    }

    public Builder patronSolicitation(PatronSolicitation val) {
      patronSolicitation = val;
      return this;
    }

    public Builder evoucherIdList(List<String> val) {
      evoucherIdList = val;
      return this;
    }

    public Builder creditCardNo(String val) {
      creditCardNo = val;
      return this;
    }

    public Builder creditCardExpiryYY(String val) {
      creditCardExpiryYY = val;
      return this;
    }

    public Builder creditCardExpiryMM(String val) {
      creditCardExpiryMM = val;
      return this;
    }

    public Builder creditCardCSC(Long val) {
      creditCardCSC = val;
      return this;
    }

    public Builder transactionReferenceNumber(String val) {
      transactionReferenceNumber = val;
      return this;
    }

    public Builder purchaseTp(boolean val) {
      purchaseTp = val;
      return this;
    }

    public Builder reservedTime(String val) {
      reservedTime = val;
      return this;
    }

    public Builder isFullyRedeemed(boolean val) {
      isFullyRedeemed = val;
      return this;
    }

    public Builder messageUuid(String val) {
      messageUuid = val;
      return this;
    }

    public Builder timeout(Long val) {
      timeout = val;
      return this;
    }
    
    public Builder payableAmount(BigDecimal  val) {
      payableAmount = val;
      return this;
    }

    public ShoppingCart build() {
      return new ShoppingCart(this);
    }
  }
}

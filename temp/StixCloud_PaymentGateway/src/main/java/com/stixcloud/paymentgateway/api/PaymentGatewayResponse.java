package com.stixcloud.paymentgateway.api;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by chongye on 18-Aug-16.
 */
@RedisHash(value = "PaymentGatewayResponse")
public class PaymentGatewayResponse implements Serializable {
  @Id
  private String uuid;
  private boolean isPolled;
  private String message;
  @TimeToLive
  private long timeToLive;

  public PaymentGatewayResponse() {
  }

  private PaymentGatewayResponse(Builder builder) {
    uuid = builder.uuid;
    isPolled = builder.isPolled;
    message = builder.message;
    timeToLive = builder.timeToLive;
  }

  public String getUuid() {
    return uuid;
  }

  public boolean getPolled() {
    return isPolled;
  }

  public String getMessage() {
    return message;
  }


  public long getTimeToLive() {
    return timeToLive;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    PaymentGatewayResponse that = (PaymentGatewayResponse) o;

    return Objects.equals(this.isPolled, that.isPolled) &&
        Objects.equals(this.message, that.message) &&
        Objects.equals(this.uuid, that.uuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isPolled, message, uuid);
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("uuid", uuid)
        .append("isPolled", isPolled)
        .append("message", message)
        .toString();
  }

  public static final class Builder {
    private boolean isPolled;
    private String message;
    private long timeToLive;
    private String uuid;

    public Builder() {
    }

    public Builder(PaymentGatewayResponse copy) {
      this.uuid = copy.uuid;
      this.isPolled = copy.isPolled;
      this.message = copy.message;
      this.timeToLive = copy.timeToLive;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder timeToLive(long val) {
      timeToLive = val;
      return this;
    }

    public Builder uuid(String uuid) {
      this.uuid = uuid;
      return this;
    }

    public Builder isPolled(boolean isPolled) {
      this.isPolled = isPolled;
      return this;
    }

    public PaymentGatewayResponse build() {
      return new PaymentGatewayResponse(this);
    }
  }
}

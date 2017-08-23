package com.sistic.be.cart.api.model.postcommit;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

import javax.money.MonetaryAmount;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sistic.be.cart.api.model.TransactionPaymentDetails;
import com.sistic.be.cart.model.ShoppingCartModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "confirmOrderStatus", "orderId", "transactionRefNumber", "orderStatus", "transactionTime", "paymentList",
		"totalPayment", "paymentMethodCode", "shoppingCart" })
public class PostCommitOrderJson implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8030779058470654829L;
	
	@JsonProperty("confirmOrderStatus")
	private String confirmOrderStatus;
	@JsonProperty("orderId")
	private String orderId;
	@JsonProperty("transactionRefNumber")
	private String transactionRefNumber;
	@JsonProperty("orderStatus")
	private String orderStatus;
	@JsonProperty("transactionTime")
	private OffsetDateTime transactionTime;
	@JsonProperty("paymentList")
	private List<TransactionPaymentDetails> transactionPaymentDetailsList;
	@JsonProperty("totalPayment")
	private MonetaryAmount totalPayment;
	@JsonProperty("shoppingCart")
	ShoppingCartModel shoppingCartModel;

	/**
	 * No args constructor for use in serialization
	 */
	public PostCommitOrderJson() {
	}

	private PostCommitOrderJson(Builder builder) {
		confirmOrderStatus = builder.confirmOrderStatus;
		orderId = builder.orderId;
		transactionRefNumber = builder.transactionRefNumber;
		transactionRefNumber = builder.transactionRefNumber;
		orderStatus = builder.orderStatus;
		transactionPaymentDetailsList = builder.transactionPaymentDetailsList;
		totalPayment = builder.totalPayment;
		shoppingCartModel = builder.shoppingCartModel;
	}

	@JsonProperty("confirmOrderStatus")
	public String getonfirmOrderStatus() {
		return confirmOrderStatus;
	}

	@JsonProperty("orderId")
	public String getOrderId() {
		return orderId;
	}

	@JsonProperty("transactionRefNumber")
	public String getTransactionRefNumber() {
		return transactionRefNumber;
	}

	@JsonProperty("orderStatus")
	public String getOrderStatus() {
		return orderStatus;
	}

	@JsonProperty("paymentList")
	public List<TransactionPaymentDetails> getTransactionPaymentDetailsList() {
		return transactionPaymentDetailsList;
	}

	@JsonProperty("totalPayment")
	public MonetaryAmount getTotalPayment() {
		return totalPayment;
	}

	@JsonProperty("shoppingCart")
	public ShoppingCartModel getShoppingCart() {
		return shoppingCartModel;
	}
	
    @JsonProperty("transactionTime")
    public OffsetDateTime getTransactionTime() {
      return transactionTime;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PostCommitOrderJson that = (PostCommitOrderJson) o;
		return Objects.equals(confirmOrderStatus, that.confirmOrderStatus) && Objects.equals(orderId, that.orderId)
				&& Objects.equals(transactionRefNumber, that.transactionRefNumber)
				&& Objects.equals(orderStatus, that.orderStatus)
				&& Objects.equals(transactionPaymentDetailsList, that.transactionPaymentDetailsList)
				&& Objects.equals(shoppingCartModel, that.shoppingCartModel)
				&& Objects.equals(transactionTime, that.transactionTime)
				&& Objects.equals(totalPayment, that.totalPayment);
	}

	@Override
	public int hashCode() {
		return Objects.hash(confirmOrderStatus, orderId, transactionRefNumber, orderStatus,
				transactionPaymentDetailsList, totalPayment, shoppingCartModel, transactionTime);
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.JSON_STYLE).append("confirmOrderStatus", confirmOrderStatus)
				.append("orderId", orderId).append("transactionRefNumber", transactionRefNumber)
				.append("orderStatus", orderStatus)
				.append("transactionPaymentDetailsList", transactionPaymentDetailsList)
				.append("totalPayment", totalPayment)
				.append("transactionTime", transactionTime)
				.append("shoppingCartModel", shoppingCartModel)
				.toString();
	}

	public static class Builder {

		private String confirmOrderStatus;
		private String orderId;
		private String transactionRefNumber;
		private String orderStatus;
		private List<TransactionPaymentDetails> transactionPaymentDetailsList;
		private MonetaryAmount totalPayment;
		ShoppingCartModel shoppingCartModel;
		private OffsetDateTime transactionTime;

		public Builder() {
		}

		public Builder(PostCommitOrderJson copy) {
			this.confirmOrderStatus = copy.confirmOrderStatus;
			this.orderId = copy.orderId;
			this.transactionRefNumber = copy.transactionRefNumber;
			this.orderStatus = copy.orderStatus;
			this.transactionPaymentDetailsList = copy.transactionPaymentDetailsList;
			this.totalPayment = copy.totalPayment;
			this.shoppingCartModel = copy.shoppingCartModel;
			this.transactionTime = copy.transactionTime;
		}

		public Builder confirmOrderStatus(String confirmOrderStatus) {
			this.confirmOrderStatus = confirmOrderStatus;
			return this;
		}

		public Builder orderId(String orderId) {
			this.orderId = orderId;
			return this;
		}

		public Builder transactionRefNumber(String transactionRefNumber) {
			this.transactionRefNumber = transactionRefNumber;
			return this;
		}

		public Builder orderStatus(String orderStatus) {
			this.orderStatus = orderStatus;
			return this;
		}

		public Builder transactionPaymentDetailsList(List<TransactionPaymentDetails> transactionPaymentDetailsList) {
			this.transactionPaymentDetailsList = transactionPaymentDetailsList;
			return this;
		}

		public Builder totalPayment(MonetaryAmount totalPayment) {
			this.totalPayment = totalPayment;
			return this;
		}

		public Builder shoppingCart(ShoppingCartModel shoppingCartModel) {
			this.shoppingCartModel = shoppingCartModel;
			return this;
		}
		
		public Builder transactionTime(OffsetDateTime transactionTime) {
			this.transactionTime = transactionTime;
			return this;
		}

		public PostCommitOrderJson build() {
			return new PostCommitOrderJson(this);
		}

	}
}

package com.stixcloud.cart.builder;

import com.stixcloud.domain.TransactionLineItemProduct;

/**
 * Created by chongye on 16/12/2016.
 */
public final class TransactionLineItemProductBuilder {
  private Long txnlineitemproductid;
  private String transactionrefnumber;
  private Long txnLineItemId;
  private Long txnProductId;
  private Long txnCodeId;

  private TransactionLineItemProductBuilder() {
  }

  public static TransactionLineItemProductBuilder aTransactionLineItemProduct() {
    return new TransactionLineItemProductBuilder();
  }

  public TransactionLineItemProductBuilder txnlineitemproductid(Long txnlineitemproductid) {
    this.txnlineitemproductid = txnlineitemproductid;
    return this;
  }

  public TransactionLineItemProductBuilder transactionrefnumber(String transactionrefnumber) {
    this.transactionrefnumber = transactionrefnumber;
    return this;
  }

  public TransactionLineItemProductBuilder txnLineItemId(Long txnLineItemId) {
    this.txnLineItemId = txnLineItemId;
    return this;
  }

  public TransactionLineItemProductBuilder txnProductId(Long txnProductId) {
    this.txnProductId = txnProductId;
    return this;
  }

  public TransactionLineItemProductBuilder txnCodeId(Long txnCodeId) {
    this.txnCodeId = txnCodeId;
    return this;
  }

  public TransactionLineItemProduct build() {
    TransactionLineItemProduct transactionLineItemProduct = new TransactionLineItemProduct();
    transactionLineItemProduct.setTxnlineitemproductid(txnlineitemproductid);
    transactionLineItemProduct.setTransactionrefnumber(transactionrefnumber);
    transactionLineItemProduct.setTxnLineItemId(txnLineItemId);
    transactionLineItemProduct.setTxnProductId(txnProductId);
    transactionLineItemProduct.setTxnCodeId(txnCodeId);
    return transactionLineItemProduct;
  }
}

package com.stixcloud.cart.rules;


import com.stixcloud.cart.rules.postcommit.PostCommitOrder;

/**
 * Created by chetan on 03/01/2017.
 */
public class PostCommitCartRule extends BaseCartRule {
  PostCommitOrder postCommitOrder;

  public PostCommitOrder getPostCommitOrder() {
    return postCommitOrder;
  }

  public void setPostCommitOrder(PostCommitOrder postCommitOrder) {
    this.postCommitOrder = postCommitOrder;
  }

  public Boolean isPaymentApproved() {
    if (this.getShoppingCart() != null && this.getShoppingCart().getIsFullyRedeemed()) {
      return true;
    } else if (postCommitOrder != null && postCommitOrder.getPaymentResponse() != null) {
      return postCommitOrder.getPaymentResponse().getOrderStatus() == 0;
    }
    return false;
  }

}

package com.stixcloud.cart.rules;

import com.stixcloud.cart.rules.commit.CommitOrder;

/**
 * Created by chongye on 5/10/2016.
 */
public class CommitCartRule extends BaseCartRule {
  private CommitOrder commitOrder;

  public CommitCartRule() {
    super();
  }

  public CommitOrder getCommitOrder() {
    return commitOrder;
  }

  public void setCommitOrder(CommitOrder commitOrder) {
    this.commitOrder = commitOrder;
  }
}

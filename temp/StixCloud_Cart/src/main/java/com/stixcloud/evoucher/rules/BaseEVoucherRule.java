package com.stixcloud.evoucher.rules;

import com.stixcloud.evoucher.domain.EVoucherView;
import org.springframework.context.MessageSource;

/**
 * Created by dbthan on 13/10/2016.
 */

public class BaseEVoucherRule {

  private boolean isExecuted;
  private EVoucherView eVoucherView;
  protected MessageSource messageSource;

  public EVoucherView getEVoucherView() {
    return eVoucherView;
  }

  public void setEVoucherView(EVoucherView eVoucherView) {
    this.eVoucherView = eVoucherView;
  }

  public BaseEVoucherRule() {
  }

  public boolean isExecuted() {
    return isExecuted;
  }

  public void setExecuted(boolean isExecuted) {
    this.isExecuted = isExecuted;
  }
}

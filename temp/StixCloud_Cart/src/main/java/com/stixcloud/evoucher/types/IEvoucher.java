package com.stixcloud.evoucher.types;

import com.stixcloud.evoucher.api.EVoucherValidation;
import com.stixcloud.evoucher.domain.EVoucherView;

import java.util.List;

public interface IEvoucher {

  public EVoucherValidation validateBasicRule();

  public EVoucherValidation validateAtApplyEvoucher();

  public EVoucherValidation validateAtPreCommitOrder();

  public void setEvoucher(List<EVoucherView> evoucherViewList);
}

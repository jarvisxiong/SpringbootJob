package com.stixcloud.evoucher.service;

import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;

import java.util.List;

/**
 * Created by dbthan on 10/17/2016.
 */

public interface IEVoucherService {

  List<EVoucherView> getEVoucherView(List<String> eVoucherIdList);

  List<ProductPaymentMethod> getProductPaymentMethod(List<Long> productIdList);

  List<UserPaymentMethod> getUserPaymentMethod(Long profileInfoId);

  List<EVoucherCreditCardRegex> getCreditCardRegex(List<String> eVoucherIdList);

  List<ProductPromoterVenue> getPromoterAndVenueByProductIdList(List<Long> productIds);
}

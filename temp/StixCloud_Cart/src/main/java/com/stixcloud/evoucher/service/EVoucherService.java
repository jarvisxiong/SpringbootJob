package com.stixcloud.evoucher.service;

import com.stixcloud.evoucher.domain.EVoucherCreditCardRegex;
import com.stixcloud.evoucher.domain.EVoucherView;
import com.stixcloud.evoucher.domain.ProductPaymentMethod;
import com.stixcloud.evoucher.domain.ProductPromoterVenue;
import com.stixcloud.evoucher.domain.UserPaymentMethod;
import com.stixcloud.evoucher.repo.IEVoucherViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dbthan on 10/17/2016.
 */

@Service
public class EVoucherService implements IEVoucherService {

  @Autowired
  IEVoucherViewRepository eVoucherRepository;

  @Override
  public List<EVoucherView> getEVoucherView(List<String> eVoucherIdList) {
    return eVoucherRepository.getEVoucherView(eVoucherIdList);
  }

  @Override
  public List<ProductPaymentMethod> getProductPaymentMethod(List<Long> productIdList) {
    return eVoucherRepository.getProductPaymentMethod(productIdList);
  }

  @Override
  public List<UserPaymentMethod> getUserPaymentMethod(Long profileInfoId) {
    return eVoucherRepository.getUserPaymentMethod(profileInfoId);
  }

  @Override
  public List<EVoucherCreditCardRegex> getCreditCardRegex(List<String> eVoucherIdList) {
    return eVoucherRepository.getCreditCardRegex(eVoucherIdList);
  }

  @Override
  public List<ProductPromoterVenue> getPromoterAndVenueByProductIdList(List<Long> productIds) {
    return eVoucherRepository.getPromoterAndVenueByProductIdList(productIds);
  }
}

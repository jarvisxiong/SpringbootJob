package com.stixcloud.paymentgateway.repo;

import com.stixcloud.domain.PaymentGatewayConfig;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentGatewayConfigRepository
    extends ReadOnlyRepository<PaymentGatewayConfig, Long> {
  @Query(
      "select pgc from PaymentGatewayConfig pgc "
          + "where pgc.paymentmethodcode = :paymentMethodCode "
          + "and pgc.revenuecenterid in :revenueCenterIds "
          + "and pgc.profileInfoId = :profileInfoId")
  List<PaymentGatewayConfig> findPaymentGatewayConfiguration(
      @Param("paymentMethodCode") String paymentMethodCode,
      @Param("revenueCenterIds") List<Long> revenueCenterIds,
      @Param("profileInfoId") Long profileInfoId);

  @Query(
      "select pgc from PaymentGatewayConfig pgc "
          + "where pgc.paymentmethodcode = :paymentMethodCode "
          + "and pgc.revenuecenterid is null "
          + "and pgc.profileInfoId = :profileInfoId")
  List<PaymentGatewayConfig> findPaymentGatewayConfiguration(
      @Param("paymentMethodCode") String paymentMethodCode,
      @Param("profileInfoId") Long profileInfoId);
}

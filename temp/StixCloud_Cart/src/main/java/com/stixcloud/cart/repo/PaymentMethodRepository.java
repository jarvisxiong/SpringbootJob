package com.stixcloud.cart.repo;

import com.stixcloud.domain.PaymentMethod;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sengkai on 10/19/2016.
 */
public interface PaymentMethodRepository
    extends CrudRepository<PaymentMethod, Long>, PaymentMethodRepositoryCustom {
  @Query("SELECT DISTINCT PM FROM PaymentMethod PM "
      + " JOIN PaymentTplDetailLive PTPLD ON PM.paymentmethodid = PTPLD.paymentMethodId  "
      + " JOIN PaymentMethodTemplateLive PMTL ON PTPLD.paymentMethodTemplateId = PMTL.paymentmethodtemplateid "
      + " JOIN PaymentTplPrdtLive PMTPL ON PMTL.paymentmethodtemplateid = PMTPL.paymentMethodTemplateId "
      + " JOIN ProductLive PL ON PMTPL.productId = PL.productid "
      + " JOIN ProfilePaymentMethod PPM ON PM.paymentmethodid = PPM.paymentMethodId "
      + " WHERE PPM.profileInfoId = :profileInfoID "
      + " AND PL.productid = :productIDs "
      + " ORDER BY PM.ordermethod ")
  List<PaymentMethod> findCommonPaymentMethods(@Param("productIDs") Long productIDs,
                                               @Param("profileInfoID") Long profileInfoID);

  @Query("select distinct pm "
      + "from PaymentMethod pm "
      + "join ProfilePaymentMethod ppm "
      + "  on pm.paymentmethodid = ppm.paymentMethodId "
      + "where pm.paymentmethodcode = :paymentMethodCode "
      + "and ppm.profileInfoId = :profileInfoId")
  PaymentMethod getPaymentMethod(@Param("paymentMethodCode") String paymentMethodCode,
                                 @Param("profileInfoId") Long profileInfoId);

  @Query("select distinct pm "
      + "from PaymentMethod pm "
      + "join ProfilePaymentMethod ppm "
      + "  on pm.paymentmethodid = ppm.paymentMethodId "
      + "where pm.paymentmethodname = :paymentMethodName "
      + "and ppm.profileInfoId = :profileInfoId")
  PaymentMethod getPaymentMethodByName(@Param("paymentMethodName") String paymentMethodName,
                                       @Param("profileInfoId") Long profileInfoId);
}

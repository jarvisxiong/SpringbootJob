package com.stixcloud.barcode.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.barcode.constant.BarcodeConstant;
import com.stixcloud.barcode.domain.BarcodeFieldValues;
import com.stixcloud.barcode.domain.BarcodeFieldValuesBuilder;
import com.stixcloud.barcode.domain.BarcodeFieldValuesData;
import com.stixcloud.barcode.domain.BarcodeFieldValuesDataView;
import com.stixcloud.barcode.domain.BarcodeFieldView;
import com.stixcloud.barcode.repo.IBarcodeGenerationRepository;
import com.stixcloud.barcode.utils.BarcodeGenerationUtil;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.domain.TransactionProduct;

@Service
public class BarcodeGenerationService implements IBarcodeGenerationService {

  private static Logger LOGGER = LogManager.getLogger(BarcodeGenerationService.class);

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private IBarcodeGenerationRepository repository;

  protected BarcodeFieldValues barcodeFieldValues;
  private boolean checkSum;
  private String barcodePrefix;

  private void constructBarcode(StringBuffer sb, BarcodeFieldView barcodeField,
      BarcodeFieldValues barcodeFieldValues) {

    String dataType = barcodeField.getDataType();
    String staticValue = barcodeField.getStaticValue();
    // if the value is static, just retrieve and set in
    if (StringUtils.equals(BarcodeConstant.STATIC_VALUE, dataType)) {
      sb.append(staticValue);
      LOGGER.info("[generateBarcode] barcode static value:" + staticValue);
      // Set barcode prefix and checksum for STAR
    } else if (StringUtils.equals(BarcodeConstant.BARCODE_PREFIX, dataType)) {
      LOGGER.info("[generateBarcode] barcode prefix:" + staticValue);
      this.barcodePrefix = staticValue;
    } else if (StringUtils.equals(BarcodeConstant.CHECKSUM, dataType)) {
      LOGGER.info("[generateBarcode] checksum:" + staticValue);
      this.checkSum = StringUtils.equals("1", staticValue);
    } else {
      String value = barcodeFieldValues.getFieldValue(dataType);
      LOGGER.info("[generateBarcode] plainValue:" + value);
      value = BarcodeGenerationUtil.formatValue(value, barcodeField.getLength(),
          barcodeField.getPadprefix(), barcodeField.getPadValue());
      LOGGER.info("[generateBarcode] formattedValue:" + value);
      sb.append(value);
    }
  }

  private BarcodeFieldValues initialBarcodeFieldValues(BarcodeFieldValuesData barcodeFieldValue,
      String patronId, TransactionProduct txnProduct, String transactionId, Long productId) {

    return BarcodeFieldValuesBuilder.aBarcodeFieldValues()
        .setEventId(barcodeFieldValue.getExternalProductId() != null
            ? barcodeFieldValue.getExternalProductId() : barcodeFieldValue.getEventId())
        .setSectionCode(barcodeFieldValue.getSectionCode())
        .setTicketId(txnProduct.getTxnproductid())
        .setPriceClassCode(barcodeFieldValue.getPriceClassCode())
        .setReprintedCount(txnProduct.getReprintedcount())
        .setVenueId(barcodeFieldValue.getVenueId())
        .setAcsBarcodeRegenCount(txnProduct.getAcsbarcoderegencount()).setPatronId(patronId)
        .setTransactionId(transactionId).build();
  }

  @Override
  public String generateBarcode(Long transactionId, Long patronProfileId,
      TransactionProduct transactionProduct, List<BarcodeFieldValuesDataView> barcodeFieldDataList) throws ValidateCartException {
    StringBuffer sb = new StringBuffer();
    this.checkSum = false;
    this.barcodePrefix = "";
    List<BarcodeFieldView> barcodeFieldViewList =
        repository.getBarcodeFieldList(transactionProduct.getProductId(),
            transactionProduct.getPriceClassId(), transactionProduct.getPriceCatId());
    if (barcodeFieldViewList == null || barcodeFieldViewList.isEmpty()) {
      return BarcodeConstant.BARCODE_EMPTY;
    }

    BarcodeFieldValuesDataView barcodeFieldValueDataView = barcodeFieldDataList.stream()
        .filter(p -> p.getProductId().equals(transactionProduct.getProductId())
            && p.getPriceClassId().equals(transactionProduct.getPriceClassId())
            && p.getSeatInventoryId().equals(transactionProduct.getSalesInvId()))
        .findFirst().orElse(null);
    
    BarcodeFieldValuesData barcodeFieldValueData = null;
    if (barcodeFieldValueDataView != null) {
      barcodeFieldValueData = new BarcodeFieldValuesData(barcodeFieldValueDataView.getEventId(),
          barcodeFieldValueDataView.getSectionCode(),
          barcodeFieldValueDataView.getExternalProductId(),
          barcodeFieldValueDataView.getPriceClassCode(), barcodeFieldValueDataView.getVenueId());
    }
    if (barcodeFieldValueData == null) {
      throw new ValidateCartException(messageSource.getMessage(
          "barcode.generation.retrieving.definition.error", null, LocaleContextHolder.getLocale()));
    }

    this.barcodeFieldValues =
        initialBarcodeFieldValues(barcodeFieldValueData, patronProfileId.toString(),
            transactionProduct, transactionId.toString(), transactionProduct.getProductId());

    for (BarcodeFieldView barcodeField : barcodeFieldViewList) {
      constructBarcode(sb, barcodeField, this.barcodeFieldValues);
    }

    String barcodeStr = sb.toString();
    int encryptedType = barcodeFieldViewList.get(0).getEncryptedType();
    LOGGER.info("[generateBarcode] bcStr before encrypt:" + barcodeStr);
    if (encryptedType != -1 && !StringUtils.equals("", barcodeStr)) {
      barcodeStr = this.barcodePrefix
          + BarcodeGenerationUtil.getEncryptedBarcode(barcodeStr, encryptedType, this.checkSum);
    }

    return barcodeStr;
  }
}

package com.stixcloud.patron.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.money.Monetary;
import javax.money.MonetaryAmount;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.patron.api.TransactionHistoryResponse;
import com.stixcloud.patron.api.json.LineItemJson;
import com.stixcloud.patron.api.json.MailingAddressJson;
import com.stixcloud.patron.api.json.ProductJson;
import com.stixcloud.patron.api.json.TransactionJson;
import com.stixcloud.patron.constant.TransactionType;
import com.stixcloud.patron.domain.TransactionReferenceView;
import com.stixcloud.patron.repo.ITransactionHistoryRepository;

@Service
public class TransactionHistoryService implements ITransactionHistoryService {
  private static final Logger logger = LogManager.getLogger(TransactionHistoryService.class);
  private final String PRODUCT = "PRODUCT";
  private final String DELIVERYMETHOD = "DELIVERYMETHOD";

  @Autowired
  private ITransactionHistoryRepository repo;

  @Override
  public TransactionHistoryResponse getTransactions(Long patronProfileId, int pageSize, int pageNo,
      Locale locale) {
    logger.info("Start getting transaction history by patron id: " + patronProfileId + ", page: "
        + pageNo + ", page size: " + pageSize);
    if (pageNo <= 0 && pageSize <= 0) {
      logger.error("Invalid page number or page size");
      return null;
    }

    Long profileInfoId = TenantContextHolder.getTenant().getProfileInfoId();
    int startRow = (pageNo - 1) * pageSize;
    int endRow = (pageNo) * pageSize;
    List<String> transctionTypeList = new ArrayList<>();
    transctionTypeList.add(TransactionType.PURCHASE.getType());
    transctionTypeList.add(TransactionType.RETURN.getType());
    logger.info("Transaction types: " + transctionTypeList);

    List<BigDecimal> allTransactionIds =
        repo.getTransactionIdList(profileInfoId, patronProfileId, transctionTypeList);
    logger.info("All transacion ids: " + transctionTypeList);

    long numberOfTxns = allTransactionIds.stream().count();
    long totalPage = numberOfTxns / pageSize;
    if ((numberOfTxns % pageSize) != 0) {
      totalPage += 1;
    }

    if (pageNo > totalPage) {
      return null;
    }

    endRow = (int) (endRow > numberOfTxns ? numberOfTxns : endRow);
    List<BigDecimal> transactionIdsPerPage =
        new ArrayList<BigDecimal>(allTransactionIds.subList(startRow, endRow));
    logger.info("Transactions id for this request: " + transactionIdsPerPage);

    List<TransactionReferenceView> transactionReferenceList = repo.getTransactionReference(
        transactionIdsPerPage, profileInfoId, patronProfileId, transctionTypeList);

    LinkedHashMap<String, TransactionJson> transactionLinkedHashMap = new LinkedHashMap<>();
    Set<Long> lineItemSet = new HashSet<>();
    LinkedHashMap<Long, List<Long>> seatsLinkedHashMap = new LinkedHashMap<>();
    Set<String> txnRefNumSet = new HashSet<>();

    // group seats of each line item together
    transactionReferenceList.forEach(t -> {
      String txnRefNum = t.getTransactionRefNumber();
      transactionLinkedHashMap.put(txnRefNum, new TransactionJson(txnRefNum, t.getTransactionType(),
          t.getTransactedTime(), null, new ArrayList<>(), null));

      if (seatsLinkedHashMap.get(t.getTxnLineItemId()) == null) {
        seatsLinkedHashMap.put(t.getTxnLineItemId(), new ArrayList<>());
      }

      if (t.getSeatNo() != null) {
        List<Long> seats = seatsLinkedHashMap.get(t.getTxnLineItemId());
        if (!seats.contains(t.getSeatNo())) {
          seats.add(t.getSeatNo());
        }
        seatsLinkedHashMap.put(t.getTxnLineItemId(), seats);
      }
    });

    Map<String, Set<String>> paymentMethodMap =
        transactionReferenceList.stream().filter(t -> PRODUCT.equals(t.getLineItemType()))
            .collect(Collectors.groupingBy(TransactionReferenceView::getTransactionRefNumber,
                Collectors.mapping(TransactionReferenceView::getPaymentType, Collectors.toSet())));

    Map<String, TransactionReferenceView> deliveryMethodMap =
        transactionReferenceList.stream().filter(t -> DELIVERYMETHOD.equals(t.getLineItemType()))
            .filter(distinctByKey(t -> t.getTransactionRefNumber())).collect(Collectors
                .toMap(TransactionReferenceView::getTransactionRefNumber, Function.identity()));

    transactionReferenceList.stream().filter(t -> PRODUCT.equals(t.getLineItemType()))
        .forEach(t -> {
          String txnRefNum = t.getTransactionRefNumber();

          if (!lineItemSet.contains(t.getTxnLineItemId())) {
            lineItemSet.add(t.getTxnLineItemId());

            TransactionJson txn = transactionLinkedHashMap.get(txnRefNum);
            String purchasedWith = "";
            if (paymentMethodMap.get(txnRefNum) != null
                || !paymentMethodMap.get(txnRefNum).isEmpty()) {
              purchasedWith = paymentMethodMap.get(txnRefNum).toString();
              purchasedWith = purchasedWith.substring(1, purchasedWith.length() - 1);
            }

            txn.setPurchasedWith(purchasedWith);
            ProductJson product = null;


            if (PRODUCT.equals(t.getLineItemType())) {
              List<Long> seats = seatsLinkedHashMap.get(t.getTxnLineItemId());
              Collections.sort(seats);
              product = new ProductJson(t.getProductName(), t.getStartDate(), t.getEndDate(),
                  t.getPriceCategoryName(), t.getVenueName(), t.getSectionType(),
                  t.getSectionAlias(), t.getLevelAlias(), t.getRowAlias(), seats);
            }

            if (txn != null) {
              if (txnRefNumSet.add(txnRefNum)) {
                TransactionReferenceView txnRefView = deliveryMethodMap.get(txnRefNum);
                MonetaryAmount deliveryUnitPrice =
                    Money.of(new BigDecimal(0), Monetary.getCurrency(locale));

                LineItemJson deliveryMethodLineItem = new LineItemJson(DELIVERYMETHOD, null,
                    t.getDeliverymethodname(), t.getDeliverymethodcode(), null,
                    extractMailingAddress(t), 1l, null, null, null);
                if (txnRefView != null) {
                  deliveryUnitPrice = txnRefView.getBookingFee() != null
                      ? Money.of(txnRefView.getBookingFee(), Monetary.getCurrency(locale))
                      : deliveryUnitPrice;
                  deliveryMethodLineItem.setQuantity(txnRefView.getQuantity());
                  deliveryMethodLineItem.setDescription(txnRefView.getDescription());
                }

                deliveryMethodLineItem.setUnitPrice(deliveryUnitPrice);
                deliveryMethodLineItem.setSubTotal(deliveryUnitPrice);

                txn.getLineItems().add(deliveryMethodLineItem);
              }

              MonetaryAmount unitPrice = t.getUnitPrice() != null
                  ? Money.of(t.getUnitPrice(), Monetary.getCurrency(locale)) : null;
              MonetaryAmount bookingFee = t.getBookingFee() != null
                  ? Money.of(t.getBookingFee(), Monetary.getCurrency(locale)) : null;

              MonetaryAmount subTotal = null;
              if (unitPrice != null && bookingFee != null) {
                subTotal = unitPrice.multiply(t.getQuantity()).add(bookingFee);
              } else if (unitPrice != null) {
                subTotal = unitPrice.multiply(t.getQuantity());
              } else if (bookingFee != null) {
                subTotal = bookingFee;
              }


              LineItemJson lineItem = new LineItemJson(t.getLineItemType(), product, null, null,
                  t.getDescription(), null, t.getQuantity(), unitPrice, bookingFee, subTotal);

              txn.getLineItems().add(lineItem);
            }
          }
        });


    List<TransactionJson> txnList = new ArrayList<>(transactionLinkedHashMap.values());
    txnList.forEach(t -> {
      t.setTotalAmount(t.getLineItems().stream().map(LineItemJson::getSubTotal)
          .collect(Collectors.toList()).stream().reduce(MonetaryFunctions.sum()).get());
    });

    Collections.sort(txnList,
        (t1, t2) -> (-t1.getDateOfPurchase().compareTo(t2.getDateOfPurchase())));
    TransactionHistoryResponse result =
        new TransactionHistoryResponse(txnList, totalPage, pageNo, numberOfTxns);
    logger.info("End getting transaction history by patron id: " + patronProfileId + ", page: "
        + pageNo + ", page size: " + pageSize);
    return result;
  }

  private MailingAddressJson extractMailingAddress(TransactionReferenceView t) {
    MailingAddressJson result = null;

    if (t.getAddressrequired()) {
      Locale locale = LocaleContextHolder.getLocale();
      String country = null;
      if (t.getCountry() != null) {
        country = new Locale("", t.getCountry()).getDisplayCountry(locale);
      }

      if (t.getLineone() != null || t.getLinetwo() != null || t.getLinethree() != null
          || t.getPostalcode() != null || t.getCity() != null || t.getState() != null
          || country != null) {

        result = new MailingAddressJson(t.getLineone(), t.getLinetwo(), t.getLinethree(),
            t.getPostalcode(), t.getCity(), t.getState(), country);
      }
    }
    return result;
  }

  public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Map<Object, Boolean> seen = new ConcurrentHashMap<>();
    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
  }

}

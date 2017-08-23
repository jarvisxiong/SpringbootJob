package com.stixcloud.cart.rules.commit;

import static com.stixcloud.cart.FeeConstants.FeeChargeType.OUTSIDE_CHARGE;

import com.stixcloud.barcode.domain.BarcodeFieldValuesDataView;
import com.stixcloud.barcode.repo.IBarcodeFieldValuesRepository;
import com.stixcloud.barcode.service.IBarcodeGenerationService;
import com.stixcloud.cart.AddressType;
import com.stixcloud.cart.FeeConstants;
import com.stixcloud.cart.IFeeService;
import com.stixcloud.cart.SalesConstants;
import com.stixcloud.cart.ValidateCartException;
import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.api.DeliveryMethodJson;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.builder.EticketDeliveryBuilder;
import com.stixcloud.cart.builder.OrderDeliveryInfoBuilder;
import com.stixcloud.cart.builder.TicketHistoryBuilder;
import com.stixcloud.cart.builder.TransactionLineItemBuilder;
import com.stixcloud.cart.builder.TransactionLineItemProductBuilder;
import com.stixcloud.cart.builder.TransactionProductBuilder;
import com.stixcloud.cart.repo.AddressRepository;
import com.stixcloud.cart.repo.DeliveryMethodRepository;
import com.stixcloud.cart.repo.ETicketDeliveryRepository;
import com.stixcloud.cart.repo.MasterCodeTableRepository;
import com.stixcloud.cart.repo.OrderDeliveryInfoRepository;
import com.stixcloud.cart.repo.PatronPhoneRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.PmtChartLiveRepository;
import com.stixcloud.cart.repo.PriceCategoryLiveRepository;
import com.stixcloud.cart.repo.PriceClassLiveRepository;
import com.stixcloud.cart.repo.TaxClassHistoryRepository;
import com.stixcloud.cart.repo.TicketHistoryRepository;
import com.stixcloud.cart.repo.TransactionLineItemProductRepository;
import com.stixcloud.cart.repo.TransactionLineItemRepository;
import com.stixcloud.cart.repo.TransactionProductPaymentRepository;
import com.stixcloud.cart.repo.TransactionProductRepository;
import com.stixcloud.cart.rules.CommitCartRule;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.EticketDelivery;
import com.stixcloud.domain.Fee;
import com.stixcloud.domain.OrderDeliveryInfo;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.PriceCategoryLive;
import com.stixcloud.domain.PriceClassLive;
import com.stixcloud.domain.TicketHistory;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionLineItem;
import com.stixcloud.domain.TransactionLineItemProduct;
import com.stixcloud.domain.TransactionProduct;
import com.stixcloud.domain.TransactionProductPayment;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.spring.SpringRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * This rule is for all product related validation
 */
@SpringRule
public class CreateLineItems extends CommitCartRule {
  private static final Logger logger = LogManager.getLogger(CreateLineItems.class);
  @Autowired
  private MessageSource messageSource;

  @Autowired
  private PriceCategoryLiveRepository priceCategoryLiveRepository;
  @Autowired
  private TransactionLineItemRepository transactionLineItemRepository;
  @Autowired
  private MasterCodeTableRepository masterCodeTableRepository;
  @Autowired
  private DeliveryMethodRepository deliveryMethodRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private PatronPhoneRepository patronPhoneRepository;
  @Autowired
  private PatronProfileRepository patronProfileRepository;
  @Autowired
  private OrderDeliveryInfoRepository orderDeliveryInfoRepository;
  @Autowired
  private ETicketDeliveryRepository eTicketDeliveryRepository;
  @Autowired
  private TransactionProductRepository transactionProductRepository;
  @Autowired
  private TransactionLineItemProductRepository transactionLineItemProductRepository;
  @Autowired
  private TransactionProductPaymentRepository transactionProductPaymentRepository;
  @Autowired
  private TicketHistoryRepository ticketHistoryRepository;
  @Autowired
  private PmtChartLiveRepository pmtChartLiveRepository;
  @Autowired
  private TaxClassHistoryRepository taxClassHistoryRepository;
  @Autowired
  private PriceClassLiveRepository priceClassLiveRepository;

  @Autowired
  private IFeeService feeService;
  @Autowired
  private IBarcodeGenerationService barcodeGenerationService;
  @Autowired
  private IBarcodeFieldValuesRepository barcodeFieldValueRepo;
  
  @Priority
  public int getPriority() {
    return 3;
  }

  @Condition
  public boolean when() {
    return isCartNotEmpty();
  }

  @Action
  public void then() throws ValidateCartException {
    this.setExecuted(true);

    Transaction transaction = this.getCommitOrder().getTransaction();
    ShoppingCartJson shoppingCartJson = this.getCommitOrder().getShoppingCartJson();
    List<TransactionLineItem> transactionLineItems = new ArrayList<>();
    List<TransactionProduct> transactionProducts = new ArrayList<>();
    List<TransactionProductPayment> transactionProductPayments = new ArrayList<>();
    List<TransactionLineItemProduct> transactionLineItemProducts = new ArrayList<>();

    DeliveryMethodJson
        deliveryMethodJson =
        shoppingCartJson.getCommonDeliveryMethod().getDeliveryMethodJsons().stream()
            .filter(dm -> dm.getCode().equals(this.getShoppingCart().getDeliveryMethodCode()))
            .findFirst().get();

    DeliveryMethod deliveryMethod =
        deliveryMethodRepository.getDeliveryMethod(deliveryMethodJson.getCode());
    deliveryMethod.setCharge(deliveryMethodJson.getCharge());
    long totalBarcodeTime = 0;
    long startTimeTotal = System.currentTimeMillis();
//    shoppingCartJson.getLineItemList().forEach(li -> {
    PatronProfile patronProfile =
        patronProfileRepository.findOne(this.getCommitOrder().getPatronProfileId());
    for (CartLineItem li : shoppingCartJson.getLineItemList()) {
      
      CartItem cartItem = this.getShoppingCart().getCartItems().stream()
          .filter(ci -> ci.getCartItemId().equals(li.getCartItemId())).findFirst().get();

      TransactionLineItem productLineItem = createProductLineItem(transaction, li, cartItem);
      transactionLineItems.add(productLineItem);
      OrderDeliveryInfo orderDeliveryInfo =
          createDeliveryRelatedEntities(deliveryMethod, patronProfile, productLineItem);
      PriceClassLive priceClassLive = priceClassLiveRepository.findOne(cartItem.getPriceClassId());
      long startTimeGetBarcodeData = System.currentTimeMillis();
      List<BarcodeFieldValuesDataView> barcodeDataList =
          barcodeFieldValueRepo.getBarcodeValues(cartItem.getInventoryIdList(),
              priceClassLive.getPriceclassid(), li.getProduct().getProductId());
      long timeGetBarcodeData = System.currentTimeMillis() - startTimeGetBarcodeData;
      for (Long salesSeatInv : cartItem.getInventoryIdList()) {
        
        TransactionProduct transactionProduct = createTransactionProduct(productLineItem,
            transaction, deliveryMethod, salesSeatInv, orderDeliveryInfo.getOrderdeliveryinfoid(),
            priceClassLive.getIscomplimentaryticket());
        long startTime = System.currentTimeMillis();
        String
            barcodeString =
            barcodeGenerationService.generateBarcode(transaction.getTransactionid(),
                patronProfile.getPatronprofileid(), transactionProduct, barcodeDataList);
        long timeForEachBarcode = System.currentTimeMillis() - startTime;
        totalBarcodeTime += timeForEachBarcode;
        logger.info("Barcode Time taken "
            + timeForEachBarcode
            + " miliseconds");
        transactionProduct.setAcsbarcode(barcodeString);
        transactionProductRepository.save(transactionProduct);
        transactionProducts.add(transactionProduct);

        TransactionLineItemProduct lineItemProduct = createTransactionLineItemProduct(
            productLineItem, transactionProduct, transaction.getTxnCodeId());
        transactionLineItemProducts.add(lineItemProduct);

        createTicketHistory(transactionProduct.getTxnproductid(),
            productLineItem.getTransactionrefnumber(), productLineItem.getQuantity());
      }
      TransactionLineItem tli = createBookingFeeLineItem(transaction, li, cartItem);
      if (tli != null) {
        transactionLineItems.add(tli);
      }
      
      totalBarcodeTime += timeGetBarcodeData;
    }
    
    logger.info("Total create transaction time "
        + (System.currentTimeMillis() - startTimeTotal) + "\n");
    logger.info("Total create barcode time "
        + totalBarcodeTime + "\n");
    
    transactionLineItems
        .add(createDeliveryFeeLineItem(transaction, deliveryMethod,
            deliveryMethodJson.getFeeWaived()));
    logger.debug("txn line items = " + transactionLineItems);
    this.getCommitOrder().setTransactionLineItems(transactionLineItems);
    this.getCommitOrder().setTransactionProducts(transactionProducts);
    this.getCommitOrder().setTransactionLineItemProducts(transactionLineItemProducts);
    this.getCommitOrder().setTransactionProductPayments(transactionProductPayments);
  }

  private TransactionLineItem createBookingFeeLineItem(Transaction transaction, CartLineItem li,
                                                       CartItem cartItem) {
    HashSet<Fee> fees = (HashSet<Fee>) feeService
        .getFeesForProduct(FeeConstants.FeeCategory.FLAT_BASED, cartItem.getProductId(),
            cartItem.getPriceCatId(), li.getPriceclass().getPriceClassCode());
    Fee fee = (fees.isEmpty() ? null : (Fee) fees.toArray()[0]);

    logger.debug("createBookingFeeLineItem fee " + fee);
    if (fee != null) {
      return createFeeLineItem(transaction, li.getProduct().getProductId(),
          li.getBookingFee().multiply(li.getQuantity()).getNumber()
              .numberValueExact(BigDecimal.class),
          li.getBookingFee().getNumber().numberValueExact(BigDecimal.class),
          false, fee, null, FeeConstants.FeeCategory.FLAT_BASED.getName(),
          FeeConstants.LevyBy.PER_TICKET.getName());
    } else {
      return null;
    }
  }

  private TransactionLineItem createDeliveryFeeLineItem(Transaction transaction,
                                                        DeliveryMethod deliveryMethod,
                                                        Boolean isFeeWaived) throws ValidateCartException {
    //TODO : find another way to do this
    Fee fee =null; 
    
    Object[] fees = 
        feeService
            .retrieveMatchingFeesForShoppingCart(this.getShoppingCart(),
                FeeConstants.FeeCategory.DELIVERY_BASED).toArray();
    if(fees == null || fees.length == 0){
      throw new ValidateCartException(messageSource.getMessage(
          "precommit.error.deliveryMethod.noMatchingFee", null, LocaleContextHolder.getLocale()));
    }
    fee = (Fee) fees[0];
    String
        levyBy =
        masterCodeTableRepository
            .getLevyByForDeliveryMethodCode(deliveryMethod.getDeliverymethodcode(), fee.getFeeid());

    return createFeeLineItem(transaction, null,
        deliveryMethod.getCharge().getNumber().numberValueExact(BigDecimal.class), null,
        isFeeWaived, fee, deliveryMethod.getDescription(),
        FeeConstants.FeeCategory.DELIVERY_BASED.getName(), levyBy);
  }

  private TransactionLineItem createFeeLineItem(Transaction transaction, Long productId,
                                                BigDecimal totalLineItemValue, BigDecimal unitPrice,
                                                Boolean feeWaived, Fee fee, String description,
                                                String feeCategory, String levyBy) {
    TransactionLineItemBuilder builder = TransactionLineItemBuilder.aTransactionLineItem()
        .transactionId(transaction.getTransactionid())
        .transactionrefnumber(transaction.getTransactionrefnumber())
        .lineitemtype(SalesConstants.LineItemType.FEE.getValue())
        .description((StringUtils.isNotBlank(description) ?
            fee.getFeename() + "-" + description : fee.getFeename()))
        .totallineitemvalue(totalLineItemValue)
        .chargetype(OUTSIDE_CHARGE.getValue())
        .levyby(levyBy)
        .feecategory(feeCategory)
        .feename(fee.getFeename())
        .feeId(fee.getFeeid())
        .isfeewaived(feeWaived);
    if (unitPrice != null) {
      builder.unitprice(unitPrice);
    }
    if (productId != null) {
      builder.productId(productId);
    }

    return transactionLineItemRepository.save(builder.build());
  }

  private TransactionLineItem createProductLineItem(
      Transaction transaction, CartLineItem li, CartItem cartItem) {
    PriceCategoryLive
        priceCategoryLive =
        priceCategoryLiveRepository.findOne(cartItem.getPriceCatId());

    TransactionLineItem transactionLineItem = TransactionLineItemBuilder.aTransactionLineItem()
        .transactionId(transaction.getTransactionid())
        .transactionrefnumber(transaction.getTransactionrefnumber())
        .lineitemtype(SalesConstants.LineItemType.PRODUCT.getValue())
        .description(li.getProduct().getProductName())
        .basepricevalue(
            pmtChartLiveRepository.getStandardPriceClassValue(li.getProduct().getProductId(),
                cartItem.getPriceCatId()))
        .unitprice(li.getUnitPrice().getNumber().numberValueExact(BigDecimal.class))
        .quantity(li.getQuantity().intValue())
        .totallineitemvalue(
            li.getUnitPrice().multiply(li.getQuantity()).getNumber()
                .numberValueExact(BigDecimal.class))
        .productId(li.getProduct().getProductId())
        .productname(li.getProduct().getProductName())
        .venuename(li.getProduct().getVenue())
        .priceCategoryId(cartItem.getPriceCatId())
        .pricecategoryname(priceCategoryLive.getPricecategoryname())
        .pricecategorynum(priceCategoryLive.getPricecategorynumber())
        .priceClassId(cartItem.getPriceClassId())
        .priceclasscode(cartItem.getPriceClass())
        .priceclassname(li.getPriceclass().getPriceClassName())
        .productcategory(1)//PRODUCT CAT
        .build();
    return transactionLineItemRepository.save(transactionLineItem);
  }

  private TransactionProduct createTransactionProduct(TransactionLineItem li,
                                                      Transaction transaction,
                                                      DeliveryMethod deliveryMethod,
                                                      Long salesInventoryId, Long orderId,
                                                      Boolean isComplimentary) {
    TransactionProductBuilder builder = TransactionProductBuilder.aTransactionProduct()
        .productId(li.getProductId())
        .soldBy(transaction.getTransactedBy())
        .solddate(transaction.getTransactedtime())
        .priceCatId(li.getPriceCategoryId())
        .priceClassId(li.getPriceClassId())
        .salesInvId(salesInventoryId)
        .inventorytype(SalesConstants.ProductCategory.EVENT.getValue())
        .basepricevalue(li.getBasepricevalue())
        .pricevalue(li.getUnitprice())
        .deliverymethod(deliveryMethod.getDeliverymethodname())
        .orderId(orderId)
        .isreturned(false)
        .reprintedcount(0L)
        .printstatus(SalesConstants.PrintStatus.NOT_PRINTED.getValue())
        .iscomplimentary(isComplimentary)
        .tickettype(deliveryMethod.getTickettype())
        .taxrate(taxClassHistoryRepository
            .getApplicableTaxPercent(li.getProductId(), transaction.getTransactedtime())
            .longValue())
        .acsbarcoderegencount(0L)
        .updatedBy(getCommitOrder().getUserProfileId())
        .updateddate(OffsetDateTime.now());

    if (deliveryMethod.getTickettype().equals(SalesConstants.TicketType.PAPER_TICKET.getValue())) {
      builder.ticketstatus(SalesConstants.TicketStatus.NOTPRINTED.getValue().longValue());
    } else {
      builder.ticketstatus(SalesConstants.TicketStatus.SENT.getValue().longValue());
    }
    return transactionProductRepository.save(builder.build());
  }

  private OrderDeliveryInfo createDeliveryRelatedEntities(DeliveryMethod deliveryMethod,
                                                          PatronProfile patron,
                                                          TransactionLineItem transactionLineItem) {
    OrderDeliveryInfo orderDeliveryInfo = createOrderDeliveryInfo(patron, deliveryMethod);
    if (orderDeliveryInfo != null && deliveryMethod != null && (
        deliveryMethod.getDeliverymethodcode().equalsIgnoreCase("E_TICKET")
            || deliveryMethod.getDeliverymethodcode().equalsIgnoreCase("SIC_E_TICKET"))) {
      EticketDelivery eTicketDelivery = EticketDeliveryBuilder.anEticketDelivery()
          .linkid(eTicketDeliveryRepository.getUniqueLinkId())
          .transactionid(transactionLineItem.getTransactionId())
          .prdtid(transactionLineItem.getProductId())
          .orderId(orderDeliveryInfo.getOrderdeliveryinfoid())
          .build();
      eTicketDeliveryRepository.save(eTicketDelivery);
    }
    return orderDeliveryInfo;
  }

  private OrderDeliveryInfo createOrderDeliveryInfo(PatronProfile patron,
                                                    DeliveryMethod deliveryMethod) {
    Optional<Address>
        mailingAddress =
        addressRepository.findPatronAddresses(patron.getPatronprofileid()).stream()
            .filter(
                address -> address.getAddresstype().equals(AddressType.MAILING.getDescription()))
            .findFirst();

    OrderDeliveryInfoBuilder builder = OrderDeliveryInfoBuilder.anOrderDeliveryInfo();

    PatronPhone
        mobilePhone =
        patronPhoneRepository.getMobileNumberByPatronProfileId(patron.getPatronprofileid());

    if (mailingAddress.isPresent()) {
      Address address = mailingAddress.get();
      builder
          .addrline1(address.getLineone())
          .addrline2(address.getLinetwo())
          .addrline3(address.getLinethree())
          .city(address.getCity())
          .state(address.getState())
          .country(address.getCountry())
          .postalcode(address.getPostcode());
    }
    builder.email(patron.getEmailaddress());
    if (mobilePhone != null) {
      builder.mobilenum(mobilePhone.getPhonenumber());
    }
    builder.deliveryMethod(deliveryMethod.getDeliverymethodid());

    return orderDeliveryInfoRepository.save(builder.build());
  }

  private TicketHistory createTicketHistory(Long transactionProductId,
                                            String transactionReferenceNumber,
                                            Integer ticketQuantity) {
    TicketHistory ticketHistory = TicketHistoryBuilder.aTicketHistory()
        .transactioncode("Purchase")
        .transactionproductId(transactionProductId)
        .transactionrefnumber(transactionReferenceNumber)
        .remarks("Purchased " + ticketQuantity + " tickets")
        .updatedBy(getCommitOrder().getUserProfileId())
        .updateddate(OffsetDateTime.now())
        .build();
    return ticketHistoryRepository.save(ticketHistory);
  }

  private TransactionLineItemProduct createTransactionLineItemProduct(
      TransactionLineItem transactionLineItem, TransactionProduct transactionProduct,
      Long txnCodeId) {
    TransactionLineItemProduct
        lineItemProduct =
        TransactionLineItemProductBuilder.aTransactionLineItemProduct()
            .txnLineItemId(transactionLineItem.getTxnlineitemid())
            .txnProductId(transactionProduct.getTxnproductid())
            .transactionrefnumber(transactionLineItem.getTransactionrefnumber())
            .txnCodeId(txnCodeId)
            .build();
    return transactionLineItemProductRepository.save(lineItemProduct);
  }
}

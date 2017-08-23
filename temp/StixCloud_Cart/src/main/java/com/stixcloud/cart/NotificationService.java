package com.stixcloud.cart;

import com.stixcloud.cart.SalesConstants.TicketType;
import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.api.DeliveryMethodJson;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.repo.AddonMappingRepository;
import com.stixcloud.cart.repo.AddressRepository;
import com.stixcloud.cart.repo.ChannelRepository;
import com.stixcloud.cart.repo.DeliveryMethodRepository;
import com.stixcloud.cart.repo.ETicketDeliveryRepository;
import com.stixcloud.cart.repo.EVoucherRepository;
import com.stixcloud.cart.repo.EmailTemplateRepository;
import com.stixcloud.cart.repo.NotificationRepository;
import com.stixcloud.cart.repo.OrderDeliveryInfoRepository;
import com.stixcloud.cart.repo.PatronPhoneRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.ProductGroupLiveRepository;
import com.stixcloud.cart.repo.ProductGroupPromoterLiveRepository;
import com.stixcloud.cart.repo.ProductLiveRepository;
import com.stixcloud.cart.repo.TransactionPaymentRepository;
import com.stixcloud.cart.repo.TransactionProductFeeRepository;
import com.stixcloud.cart.repo.TransactionRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.Channel;
import com.stixcloud.domain.DeliveryMethod;
import com.stixcloud.domain.EmailTemplate;
import com.stixcloud.domain.EticketDelivery;
import com.stixcloud.domain.Notification;
import com.stixcloud.domain.OrderDeliveryInfo;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.ProductGroupPromoterLive;
import com.stixcloud.domain.ProductLive;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.Transaction;
import com.stixcloud.domain.TransactionPayment;
import com.stixcloud.dto.common.MessageDTO;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;


@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class NotificationService implements INotificationService {
  public static final String GUEST = "Guest";
  
  @Autowired
  private ShoppingCartService shoppingCartService;
  @Autowired
  private NotificationRepository notificationRepository;
  @Autowired
  private EmailTemplateRepository emailTemplateRepository;
  @Autowired
  private TransactionPaymentRepository transactionPaymentRepository;
  @Autowired
  private PatronPhoneRepository patronPhoneRepository;
  @Autowired
  private OrderDeliveryInfoRepository orderDeliveryInfoRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  VelocityConfigurer velocityConfigurer;
  @Autowired
  ProductLiveRepository productLiveRepository;
  @Autowired
  ETicketDeliveryRepository eTicketDeliveryRepository;
  @Autowired
  EVoucherRepository eVoucherRepository;
  @Autowired
  IShoppingCartService iShoppingCartService;
  @Autowired
  TransactionRepository transactionRepository;
  @Autowired
  ProductGroupLiveRepository productGroupLiveRepository;
  @Autowired
  ProductGroupPromoterLiveRepository productGroupPromoterLiveRepository;
  @Autowired
  ChannelRepository channelRepository;
  @Autowired
  DeliveryMethodRepository deliveryMethodRepository;
  @Autowired
  AmqpTemplate amqpTemplate;
  @Autowired
  PatronProfileRepository patronProfileRepository;
  @Autowired
  TransactionProductFeeRepository transactionProductFeeRepository;
  @Value("${cart.eticket.download.url}")
  private String eticketDownloadUrl;
  
  @Autowired
  private AddonMappingRepository addonRepo;
  
//  private static final List<String> ADDON_TYPE = Arrays.asList(new String[]{"DONATION", "TEST"});
  private static final String[] ADDON_TYPE = new String[]{"DONATION"};

  private final String SUBJECT = "subject";
  private final String SENDER = "sender";
  private static final Logger logger = LogManager.getLogger(NotificationService.class);

  @Override
  public void insertNotification(ShoppingCart cart) throws Exception {
    Transaction
        transaction =
        transactionRepository.findByTransactionRefNumber(cart.getTransactionReferenceNumber());
    PatronProfile patronProfile = patronProfileRepository.findOne(transaction.getPatronId());
    Notification
        notification =
        new Notification(cart.getTransactionReferenceNumber(), patronProfile.getEmailaddress(),
            "Booking Order", " ", "Activated");
    notification.setContent(generateEmailContent(cart, patronProfile));
    notificationRepository.save(notification);
    String senderAddress = "customerservice@sistic.com.sg";
    String afterCutContent = notification.getContent();
    String subject = notification.getSubject();
    String[] splitedContent = afterCutContent.split("<config_subject>");
    if (splitedContent != null && splitedContent.length >= 2) {
      logger.info("Notification content splits for transaction id " + notification.getTxnID());
      StringBuffer sb = new StringBuffer();
      int length = splitedContent.length;
      for (int i = 0; i < length - 1; i++) {
        sb.append(splitedContent[i]);
      }
      afterCutContent = sb.toString();
      String[] splitedEmailSending = splitedContent[1].toString().split("<config_email>");

      if (splitedEmailSending != null && splitedEmailSending.length >= 2) {
        logger.debug("Before cut <>, suject is: " + splitedContent[0].toString());
        logger.debug("Before cut <>, senderAddress is: " + splitedContent[1].toString());
        subject = splitedEmailSending[0].toString()
            .substring(1, splitedEmailSending[0].toString().length() - 1);

        senderAddress = splitedEmailSending[1].toString()
            .substring(1, splitedEmailSending[1].toString().length() - 1);
        logger.debug("After cut <>, suject is: " + subject);
        logger.debug("After cut <>, senderAddress is: " + senderAddress);
      }

      notification.setContent(afterCutContent);
      notification.setSubject(subject);
      notificationRepository.save(notification);
    } else {
      logger
          .info("Notification content is not split for transaction id " + notification.getTxnID());
    }
    logger.info(
        "Before sendNotification: Email sender is: " + senderAddress + ". Subject is: " + subject
            + " to email: " + notification.getRecipient());

    MessageDTO
        adminMessageDTO =
        new MessageDTO(afterCutContent, subject, senderAddress,
            notification.getRecipient(), null);
    amqpTemplate.convertAndSend("email.notification", adminMessageDTO);
    logger.info("Message Sent to Queue ");
  }

  public String generateEmailContent(ShoppingCart cart, PatronProfile patronProfile)
      throws Exception {
	    List<Long> productIds = shoppingCartService.getProductIdsByCartGuid(cart.getCartGuid());
//	    List<Long> productIds =new ArrayList<>();
	    Set<Long> productIdSet = new HashSet<Long>(productIds);
	    EmailTemplate emailTemplate = null;
	    for (Long p : productIdSet) {
	      ProductLive pLive = productLiveRepository.findOne(p);
	      emailTemplate = emailTemplateRepository.retrieveEmailTemplatesByCriterias("EmailConfirmation", p, 5l);
	      if (emailTemplate == null) {
	        emailTemplate = emailTemplateRepository.retrieveEmailTemplatesByCriterias("EmailConfirmation", 
	            		pLive.getProductGroupId(), 1l);
	      }
	      if (emailTemplate == null) {
	    	  Long profileId=121l;
	    	  try {
	    			  profileId=TenantContextHolder.getTenant().getProfileInfoId();
	    	  }catch(Exception e) {
	    		  logger.warn("Using default profileId "+profileId);
	    	  }
//	        Channel channel = channelRepository.getChannelByProfileInfoId(121l);
	    	Channel channel = channelRepository.getChannelByProfileInfoId(profileId);
	        if (channel != null) {
	          emailTemplate = emailTemplateRepository.retrieveEmailTemplatesByCriterias("EmailConfirmation", 
	            		  channel.getChannelid(), 2l);
	        }
	      }
	      if (emailTemplate == null) {
	        ProductGroupPromoterLive productGroupPromoter =
	            productGroupPromoterLiveRepository.findByProductGroup(pLive.getProductGroupId());
	        if (productGroupPromoter != null) {
	          emailTemplate = emailTemplateRepository.retrieveEmailTemplatesByCriterias("EmailConfirmation",
	                  productGroupPromoter.getPromoterId(), 3l);
	        }
	      }
	      if (emailTemplate != null) { break; }
	    }
	    
	    EmailTemplate defaultEmailTemplate=emailTemplateRepository.getDefaultEmailTemplate();
    	String defaultEmailTemplateStr= (!(defaultEmailTemplate==null || defaultEmailTemplate.getPath()==null)) ? defaultEmailTemplate.getPath() : "";	    	
	    
	    if (emailTemplate == null) {
			logger.info("Using email template default "+defaultEmailTemplateStr);
			return generateContent(cart, defaultEmailTemplateStr, patronProfile);
	    }
	    logger.info("Using email template "+ emailTemplate.getPath());
	    return generateContent(cart, emailTemplate.getPath(), patronProfile);

  }


  public String generateContent(ShoppingCart cart, String emailTemplatePath,
                                PatronProfile patronProfile) throws Exception {

    Map<String, Object> map = new HashMap<String, Object>();
    List<TransactionPayment> txnPayments = transactionPaymentRepository
        .findByTransactionRefNumber(cart.getTransactionReferenceNumber());
    BigDecimal totalAmount =BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
    StringBuffer sb = new StringBuffer();
   /* if(txnPayments!=null && !txnPayments.isEmpty()) {
        totalAmount =txnPayments.stream().filter(tp -> Boolean.TRUE.equals(tp.getIscredit()))
            .map(TransactionPayment::getTransacedamount).reduce(BigDecimal::add).get();
    }*/
    
    if(txnPayments!=null && !txnPayments.isEmpty()) {
          for(TransactionPayment tp:txnPayments){
                 BigDecimal transacedamount =BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
                 if(tp.getIscredit()) {
                     transacedamount =tp.getTransacedamount().setScale(2, BigDecimal.ROUND_HALF_UP);
                 }
                 String paymentInfo = cart.getPaymentMethodCode().replaceAll("[0-9]", "");
                  if(!tp.getIsevoucherpayment()) {
                    sb.append(paymentInfo + " ");
                  }
                  else{
                      sb.append(tp.getPaymentmethodname() + " ");
                  }
                    // sb.append("("+tpDTO.getAmount().toValueString()+")");
                    // Bugs #2200 Payment Method on Order Confirmation & Email Confirmation
                    // page is not displaying "S$"
                    sb.append("(" + "SGD " + transacedamount + ")");
                    sb.append("<br>");
                    totalAmount=totalAmount.add(transacedamount);
            }
           
    }
    
  
   
    map.put("transaction_payments", sb.toString());
    // map.put("patron_name", patron.getFirstName() + " " +
    // patron.getLastName() );
    String fullName = GUEST;
    String firstName = GUEST;
    if (null != patronProfile && StringUtils.isNotEmpty(patronProfile.getFirstname())
        && !StringUtils.equals("-", patronProfile.getFirstname())) {
      fullName = firstName = patronProfile.getFirstname();

      if (StringUtils.isNotEmpty(patronProfile.getLastname())
          && !StringUtils.equals("-", patronProfile.getLastname())) {
        fullName += " " + patronProfile.getLastname();
      }
    }
    map.put("patron_name", fullName);
    map.put("is_gift", "false");
    map.put("is_RedeemUsingGift", "false");
    map.put("patron_first_name", firstName);
    map.put("email", patronProfile.getEmailaddress());
    map.put("payment_method", cart.getPaymentMethodCode());
    map.put("gst_rate", "7");
    map.put("total_amount", "SGD" + " " + totalAmount.toString());
    map.put("sepang_total_amount", "MYR" + totalAmount.toString());
    map.put("evoucherList", eVoucherRepository.findAll(cart.getEvoucherIdList()));
    PatronPhone
        patronPhone =
        patronPhoneRepository.getMobileNumberByPatronProfileId(patronProfile.getPatronprofileid());
    List<Long> productIds = shoppingCartService.getProductIdsByCartGuid(cart.getCartGuid());
    String mobileNumber = "";
    
    if(null != patronPhone) {
      if ((patronPhone.getAreacode() != null) && (patronPhone.getPhonenumber() != null)) {
        mobileNumber = patronPhone.getAreacode() + patronPhone.getPhonenumber();
      } else if (patronPhone.getAreacode() == null) {
        mobileNumber = patronPhone.getPhonenumber();
      } else if (patronPhone.getPhonenumber() == null) {
        mobileNumber = patronPhone.getAreacode();
      }
    }
   
    map.put("MobileNumber", mobileNumber);

    // }

    map.put("Address1", "");
    map.put("Address2", "");
    map.put("Address3", "");
    map.put("City", "");
    map.put("Country", "");
    map.put("PostalCode", "");
    map.put("DeliveryMethodCode", "");

    List<OrderDeliveryInfo>
        orderDeliveryInfoList =
        orderDeliveryInfoRepository
            .findOrderInfoByTransactionRefNumber(cart.getTransactionReferenceNumber());
    OrderDeliveryInfo orderDeliveryInfo = orderDeliveryInfoList.get(0);
    List<EticketDelivery>
        eDeliveryList =
        eTicketDeliveryRepository.findOrderInfoByOrderIds(
            orderDeliveryInfoList.stream().map(o -> o.getOrderdeliveryinfoid())
                .collect(Collectors.toList()));

    List<Address> addresses = addressRepository.findPatronAddresses(121l);
    CollectionCountry coll = new CollectionCountry();
    coll.setMap();
    // Added for Tuleap.Task #1226 [bryanleong]: 13032016 - Start
    if (addresses != null && !addresses.isEmpty()) {

      // Assuming its getting the billing address, last confirm by Seng
      // Ket
      if (addresses.size() > 1) {
        for (Address address : addresses) {
          if (address.getAddresstype().equalsIgnoreCase(AddressType.BILLING.getDescription())) {
            map.put("Sepang_Address1", address.getLineone());
            map.put("Sepang_Address2", address.getLinetwo());
            map.put("Sepang_Address3", address.getLinethree());
            map.put("Sepang_City", address.getCity());
            map.put("Sepang_State", address.getState());
            map.put("Sepang_Country", coll.getMapCoutriesName().get(address.getCountry()) != null
                ? coll.getMapCoutriesName().get(address.getCountry()) : "");
            map.put("Sepang_PostalCode", address.getPostcode());
          }
        }
      } else {
        map.put("Sepang_Address1", addresses.get(0).getLineone());
        map.put("Sepang_Address2", addresses.get(0).getLinetwo());
        map.put("Sepang_Address3", addresses.get(0).getLinethree());
        map.put("Sepang_City", addresses.get(0).getCity());
        map.put("Sepang_State", addresses.get(0).getState());
        map.put("Sepang_Country",
            coll.getMapCoutriesName().get(addresses.get(0).getCountry()) != null
                ? coll.getMapCoutriesName().get(addresses.get(0).getCountry()) : "");
        map.put("Sepang_PostalCode", addresses.get(0).getPostcode());
      }
    } else {
      map.put("Sepang_Address1", "");
      map.put("Sepang_Address2", "");
      map.put("Sepang_Address3", "");
      map.put("Sepang_City", "");
      map.put("Sepang_State", "");
      map.put("Sepang_Country", "");
      map.put("Sepang_PostalCode", "");
    }
    if (orderDeliveryInfo != null) {

      String country = coll.getMapCoutriesName().get(orderDeliveryInfo.getCountry());
      map.put("Address1", orderDeliveryInfo.getAddrline1());
      map.put("Address2", orderDeliveryInfo.getAddrline2());
      map.put("Address3", orderDeliveryInfo.getAddrline3());
      map.put("City", orderDeliveryInfo.getCity());
      map.put("Country", country);
      map.put("PostalCode", orderDeliveryInfo.getPostalcode());
    }

    if (cart.getDeliveryMethodCode() != null) {
      map.put("DeliveryMethodCode", cart.getDeliveryMethodCode());
    }

    HashSet<TransactionProductDTO> transactionProductSet = new HashSet<TransactionProductDTO>();

    Set<Long> productIdSet = new HashSet<Long>(productIds);
    
    // hide donation details - get list of donations in product line items
    List<Long> addonProductIds = addonRepo.getDonationProducts(productIds, ADDON_TYPE);
    
    productIdSet.forEach(p -> {
      if(!addonProductIds.contains(p)){
        ProductLive productLive = productLiveRepository.findOne(p);
        TransactionProductDTO transactionProductDTO = new TransactionProductDTO();
        transactionProductDTO.setPrdtDescription(
            productLive.getDescription() == null ? productLive.getProductname()
                : productLive.getDescription());
        String strStart = "";
        String strEnd = "";
        String strStartTime = "";
        String strEndTime = "";
        if (productLive != null) {
  
            DateTimeFormatter
            formatter =
            DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATE4);
            OffsetDateTime startDate = productLive.getStartdate();
            OffsetDateTime endDate = productLive.getEnddate();
            try {
              strStart = startDate.format(formatter);
              strEnd = ((endDate != null) ? " - " + endDate.format(formatter) : "");
              strStartTime =
                  "(" + startDate.format(DateTimeFormatter.ofPattern(DateUtil.TIME_FORMAT_FOR_SALE));
              strEndTime = ((endDate != null)
                  ? " - " + endDate.format(DateTimeFormatter.ofPattern(DateUtil.TIME_FORMAT_FOR_SALE))
                  : "")
                  + ")";
            } catch (Exception e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
            }
            transactionProductDTO.setShowDateTime(
                productLive.getStartdate()
                    .format(DateTimeFormatter.ofPattern(DateUtil.TIME_FORMAT_FOR_SALE)));
            transactionProductDTO.setSepangStartDate(strStart);
            transactionProductDTO.setSepangEndDate(strEnd.isEmpty() ? "" : strEnd.substring(1));
            transactionProductDTO.setSepangRangeTime(strStartTime + strEndTime);
            EticketDelivery eDelivery = null;
            if (eDeliveryList != null && eDeliveryList.size() > 0) {
              eDelivery = eDeliveryList.stream().filter(e -> e.getPrdtid().equals(p)).findFirst().get();
            }
  
            if (eDelivery != null) {
              transactionProductDTO.setEticketLink(eticketDownloadUrl + eDelivery.getLinkid());
              //    transactionProductSet.add(transactionProductDTO);
              map.put("sistic_pac", 1);
            }
            
            transactionProductSet.add(transactionProductDTO);
            
          }
      }
    });
    map.put("transactionProductSet", transactionProductSet);
    FeeDetails feeDetails = new FeeDetails();
    Transaction
        t =
        transactionRepository.findByTransactionRefNumber(cart.getTransactionReferenceNumber());
    BigDecimal
        bookingFee =
        transactionProductFeeRepository
            .findBookingFeeByTransactionRefNumber(t.getTransactionrefnumber());
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    MonetaryAmount bookingFeeTotal = Money.of(bookingFee==null?BigDecimal.ZERO:bookingFee, currencyUnit);
    feeDetails.setFeeItems(
        convertLineItemToCartItem(shoppingCartService.getShoppingCartJson(cart.getCartGuid(),
            currencyUnit,
            "", TenantContextHolder
                .getTenant().getProfileInfoId(), null), bookingFeeTotal,
            cart.getDeliveryMethodCode(), addonProductIds));
    //"MASTERCARD_PICKUP,MAIL_WORLD_ELITE_MASTERCARD,VENUE_COLLECTION_STANDARD_CHARTERED_MASTERCARD", patronProfile.getPatronprofileid())));
    map.put("feeDetails", feeDetails);
    map.put("transaction_id", cart.getTransactionReferenceNumber());
    map.put("purchase_date", t.getTransactedtime()
        .format(DateTimeFormatter
            .ofPattern(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATETIME_FULL_REPORT)));
    // map.put("purchase_date",
    // DateUtil.formatDate(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATETIME_FULL_REPORT,
    // salesOrder.getTransactedDate()));
    map.put("account_num", patronProfile.getAccno());
    map.put("total_tickets", t.getTotalTickets());
    String
        content =
        VelocityEngineUtils.mergeTemplateIntoString(velocityConfigurer.getVelocityEngine(),
            emailTemplatePath/*"\\SISTIC\\emailConfirmation.vm"*/, map);
    String senderAddress = "";
    String subject = "";
    if (map.get(SENDER) != null && map.get(SUBJECT) != null && !""
        .equals(map.get(SENDER).toString().trim())
        && !"".equals(map.get(SUBJECT).toString().trim())) {

      senderAddress = map.get(SENDER).toString();
      subject = map.get(SUBJECT).toString();
      content =
          content + "<config_subject>" + "<" + subject + ">" + "<config_email>" + "<"
              + senderAddress + ">";
    }
    return content;
  }

  private List<CartItemsDTO> convertLineItemToCartItem(ShoppingCartJson cart,
                                                       MonetaryAmount bookingFee,
                                                       String deliveryMethodCode,
                                                       List<Long> donationIds) {

    List<CartItemsDTO> cartItemsList = new ArrayList<CartItemsDTO>();

    String deliveryMethodName = "";
    int ItemCount = 0;
    for (CartLineItem lineItem : cart.getLineItemList()) {
      CartItemsDTO cartItem = new CartItemsDTO();
      if (lineItem.getProduct().getProductId() != null) {
        ItemCount++;
        cartItem.setID(lineItem.getProduct().getProductId() + "_" + "_" + lineItem.getPriceclass());
        cartItem.setItem(lineItem.getProduct().getProductType()/* "Event" */);
        ProductLive
            productLive =
            productLiveRepository.findOne(lineItem.getProduct().getProductId());
        String strStart = "";
        String strEnd = "";
        if (productLive != null) {
          OffsetDateTime startDate = productLive.getStartdate();
          OffsetDateTime endDate = productLive.getEnddate();
          try {

            strStart = startDate
                .format(DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATE4));
            strEnd = ((endDate != null) ? " - " + startDate
                .format(DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATE4))
                : "");
          } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }

        boolean isDonation = donationIds.contains(productLive.getProductid());
        
        cartItem.setDescription(
            "<span class='cartProductDescription'>" + productLive.getProductname() + (isDonation ? "" : ("<br/>"
                + (productLive.getDescription() == null ? ""
                : productLive.getDescription() + "<br/>") + productLive.getStartdate().format(
                DateTimeFormatter
                    .ofPattern(DateUtil.DATE_FORMAT_DEFAULT_FOR_SHOW_DATETIME_FULL_REPORT))
                + lineItem.getProduct().getVenue() + "<br/>" + "Level:" + lineItem.getProduct()
                .getLevel()
                + ",Section:" + lineItem.getProduct().getSection() + (lineItem
                .getProduct().getRow() != null ? (",Row :" + lineItem
                .getProduct().getRow()) : "")
                + ((lineItem.getProduct().getSeatNo() != null && (!lineItem.getProduct().getSeatNo()
                .isEmpty())) ? (", Seat(s):" + lineItem.getProduct().getSeatNo())
                : (" - " + lineItem.getProduct().getSection().toUpperCase())) ) ) + "</span>");
        cartItem.setNgsDescription(
            "<span class='cartProductDescription'>" + productLive.getProductname()
                + (isDonation ? "" : ("<br/>" + productLive.getDescription() + "<br/>" + productLive.getStartdate()
                + lineItem.getProduct().getVenue() + "<br/>" + "Level:" + lineItem.getProduct()
                .getLevel()
                + ",Section:" + lineItem.getProduct().getSection() + (lineItem
                .getProduct().getRow() != null ? (",Row :" + lineItem
                .getProduct().getRow()) : "")
                + ((lineItem.getProduct().getSeatNo() != null && (!lineItem.getProduct().getSeatNo()
                .isEmpty())) ? (", Seat(s):" + lineItem.getProduct().getSeatNo())
                : (" - " + lineItem.getProduct().getSection().toUpperCase()))) ) + "</span>");

        cartItem.setQty(lineItem.getQuantity().intValue());
        BigDecimal unitPrice = new BigDecimal(lineItem.getUnitPrice().getNumber().toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal singleUnitPriceGST = unitPrice.divide(
            new BigDecimal("7")/* bizFeeService.returnMYRGST() */.add(new BigDecimal(1)), 2,
            RoundingMode.HALF_UP);
        BigDecimal
            unitPriceGST =
            unitPrice.subtract(singleUnitPriceGST).setScale(2, RoundingMode.HALF_UP);
        BigDecimal
            totalUnitPriceGST =
            unitPriceGST.multiply(new BigDecimal(cartItem.getQty())).setScale(2,
                RoundingMode.HALF_UP);
        String currency = "$";
        currency = lineItem.getUnitPrice().getCurrency().getCurrencyCode();
        cartItem.setUnitPriceSubtractGST(
            currency + singleUnitPriceGST.multiply(new BigDecimal(cartItem.getQty()))
                .setScale(2, RoundingMode.HALF_UP).toString());
        cartItem.setSingleUnitPriceGST(
            currency + singleUnitPriceGST.setScale(2, RoundingMode.HALF_UP).toString());
        cartItem
            .setUnitPriceGST(
                currency +totalUnitPriceGST.setScale(2, RoundingMode.HALF_UP).toString());
        cartItem.setGstPerLineItem(totalUnitPriceGST);
        cartItem.setPriceClass(lineItem.getPriceclass().getPriceClassName());
        cartItem.setNo(ItemCount);
        cartItem.setUnitPrice(unitPrice.toString());
        cartItem
            .setSubTotal( currency + " "+ (unitPrice.multiply(BigDecimal.valueOf(lineItem.getQuantity())).setScale(2, RoundingMode.HALF_UP).toString()));
        // cartItem.setTicketType("Standard");
        TicketType ticketType = TicketType.PAPER_TICKET;
        DeliveryMethodJson
            deliveryMethodJson =
            cart.getCommonDeliveryMethod().getDeliveryMethodJsons().stream()
                .filter(delivery -> delivery.getCode().equals(deliveryMethodCode)).findAny().get();
        DeliveryMethod
            deliveryMethod =
            deliveryMethodRepository.getDeliveryMethod(deliveryMethodJson.getCode());

        if (deliveryMethod != null) {
          ticketType =
              TicketType.getTicketType(TicketType.getTicketType(deliveryMethod.getTickettype()));
        }
        if (ticketType == TicketType.PAPER_TICKET || ticketType == TicketType.ETICKET) {
          cartItem.setTicketType(cartItem.getPriceClass());
        } else {
          cartItem.setTicketType(ticketType.getType());
        }
        deliveryMethodName = deliveryMethod.getDeliverymethodname();
        cartItemsList.add(cartItem);
       /* //Booking Fee
        CartItemsDTO cartItem2 = new CartItemsDTO();
        cartItem2.setNo(null);
        cartItem2.setItem("NoItem");
        cartItem2.setQty(null);
        cartItem2.setSubTotal(lineItem.getBookingFee().toString());
        cartItem2.setDescription("SISTIC Booking Fee");
        cartItem2.setTicketType("-");
        cartItemsList.add(cartItem2);*/
      }
    }
    //Delivery Fee
    DeliveryMethodJson
        deliveryMethodJson =
        cart.getCommonDeliveryMethod().getDeliveryMethodJsons().stream()
            .filter(delivery -> delivery.getCode().equals(deliveryMethodCode)).findAny().get();
    CartItemsDTO cartItem3 = new CartItemsDTO();
    cartItem3.setNo(null);
    cartItem3.setItem("NoItem");
    cartItem3.setQty(null);
    cartItem3.setSubTotal(deliveryMethodJson.getCharge().getCurrency().getCurrencyCode()+" "+deliveryMethodJson.getCharge().getNumber().numberValueExact(BigDecimal.class).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    cartItem3.setDescription("Handling Fee-" + deliveryMethodName);
    cartItem3.setTicketType("-");
    cartItemsList.add(cartItem3);

    //Booking Fee
    CartItemsDTO cartItem2 = new CartItemsDTO();
    cartItem2.setNo(null);
    cartItem2.setItem("NoItem");
    cartItem2.setQty(null);
    cartItem2.setSubTotal(bookingFee.getCurrency().getCurrencyCode()+" "+bookingFee.getNumber().numberValueExact(BigDecimal.class).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
    cartItem2.setDescription("SISTIC Booking Fee");
    cartItem2.setTicketType("-");
    cartItemsList.add(cartItem2);
    return cartItemsList;
  }

  public void testEmail(String email) throws Exception {
    MessageDTO
        adminMessageDTO =
        new MessageDTO("test", "test2", "customerservice@sistic.com.sg",
            "zhenhuix@gmail.com", null);
    amqpTemplate.convertAndSend("email.notification", adminMessageDTO);
  }
}

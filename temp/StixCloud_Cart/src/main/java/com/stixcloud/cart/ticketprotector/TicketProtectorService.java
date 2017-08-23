package com.stixcloud.cart.ticketprotector;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.cart.CartException;
import com.stixcloud.cart.IFeeService;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.SecurityUtils;
import com.stixcloud.cart.ShoppingCartJsonBuilder;
import com.stixcloud.cart.api.CartLineItem;
import com.stixcloud.cart.api.ShoppingCartJson;
import com.stixcloud.cart.repo.AddressRepository;
import com.stixcloud.cart.repo.PatronAdvanceProfileRepository;
import com.stixcloud.cart.repo.PatronPhoneRepository;
import com.stixcloud.cart.repo.PatronProfileRepository;
import com.stixcloud.cart.repo.ShoppingCartRepository;
import com.stixcloud.cart.repo.TransactionRepository;
import com.stixcloud.cart.rules.postcommit.PostCommitOrder;
import com.stixcloud.cart.ticketprotector.api.TicketProtectorRequest;
import com.stixcloud.cart.ticketprotector.domain.SubscriptionACK;
import com.stixcloud.cart.ticketprotector.domain.TicketProtector;
import com.stixcloud.cart.ticketprotector.repo.TicketProtectorRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.CartLineItemsView;
import com.stixcloud.domain.PatronAdvanceProfile;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.javamoney.moneta.Money;
import org.javamoney.moneta.function.MonetaryFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Service
public class TicketProtectorService implements ITicketProtectorService {

  public static final Logger logger = LogManager.getLogger(TicketProtectorService.class);
  private static final String XMLNS = "http://www.mondial-assistance.com/ecommerce/schema/";
  private static final String SALES_ORIGIN_TYPE = "Integrated";
  private static final String CODE = "code";
  @Value("${ticket.protector.use.manual.input.date}")
  public boolean useManualInputDate;
  @Value("${ticket.protector.manual.input.dd}")
  public int manualInputDd;
  @Value("${ticket.protector.manual.input.mm}")
  public int manualInputMm;
  @Value("${ticket.protector.manual.input.yy}")
  public int manualInputYy;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private ResourceLoader resourceLoader;
  @Autowired
  private IShoppingCartService shoppingCartService;
  @Autowired
  private IFeeService feeService;
  @Autowired
  private TransactionRepository transactionRepository;
  @Autowired
  private TicketProtectorRepository ticketProtectorRepository;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;
  @Autowired
  private PatronProfileRepository patronProfileRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private PatronPhoneRepository patronPhoneRepository;
  @Autowired
  private PatronAdvanceProfileRepository patronAdvanceProfileRepository;
  @Value("${waived.delivery.codes}")
  private String waivedDeliveryCodes;
  @Value("${ticket.protector.endpoint}")
  private String ticketProtectorEndpoint;
  @Value("${ticket.protector.premium.percent}")
  private double premiumPercent;
  @Value("${ticket.protector.use.system.date}")
  private boolean useSystemDate;
  @Value("${ticket.protector.security.key}")
  private String securityKey;
  @Value("${ticket.protector.partner.name}")
  private String partnerName;
  @Value("${ticket.protector.country}")
  private String country;
  @Value("${ticket.protector.language}")
  private String language;
  @Value("${ticket.protector.origin}")
  private String origin;
  @Value("${ticket.protector.travel.type}")
  private String travelType;
  @Value("${ticket.protector.currency}")
  private String currency;
  @Value("${ticket.protector.policy.cover}")
  private String policyCover;
  @Value("${ticket.protector.product.variant}")
  private String productVariantId;

  @Override
  public List<CartLineItem> getCartLineItemList(String cartGuid) throws CartException {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());

    LinkedList<List<CartLineItemsView>> cartLineItems =
        shoppingCartService.getCartLineItems(cartGuid, currencyUnit,
            TenantContextHolder.getTenant().getProfileInfoId());

    ShoppingCartJson
        builder =
        new ShoppingCartJsonBuilder(feeService)
            .cartLineItems(cartLineItems)
            .currencyUnit(currencyUnit)
            .build();

    return builder.getLineItemList();
  }

  @Override
  public MonetaryAmount getTicketProtectorAmount(String cartGuid) throws CartException {
    CurrencyUnit currencyUnit = Monetary.getCurrency(LocaleContextHolder.getLocale());
    MonetaryAmount totalPremium = Money.zero(currencyUnit);

    List<CartLineItem> cartLineItemList = getCartLineItemList(cartGuid);

    for (CartLineItem item : cartLineItemList) {
      totalPremium = totalPremium.add(getCartLineItemPremiumAmount(item));
    }

    return totalPremium;

  }

  private MonetaryAmount getCartLineItemPremiumAmount(CartLineItem cartLineItem) {

    double premiumPercent = this.premiumPercent / 100;
    return cartLineItem.getSubTotal().multiply(premiumPercent);
  }

  @Override
  public String processTicketProtector(PostCommitOrder postCommitOrder, ShoppingCart shoppingCart)
      throws CartException {

    List<CartLineItem> cartLineItemList = getCartLineItemList(shoppingCart.getCartGuid());

//     group by product name
    Map<String, List<CartLineItem>>
        cartLineItemMap =
        cartLineItemList.stream()
            .collect(Collectors.groupingBy(x -> x.getProduct().getProductName()));

    boolean errors = false;

//    1 ticketprotector request for each product
    for (Map.Entry<String, List<CartLineItem>> entry : cartLineItemMap.entrySet()) {
      TicketProtectorRequest
          request =
          createTicketProtectorRequest(postCommitOrder, shoppingCart, entry.getValue());
      if (getTicketProtectorResponse(shoppingCart.getTransactionReferenceNumber(), request) == 2) {
        errors = true;
      }
    }

    return errors ? "FAILURE" : "SUCCESS";
  }

  private TicketProtectorRequest createTicketProtectorRequest(PostCommitOrder postCommitOrder,
                                                              ShoppingCart shoppingCart,
                                                              List<CartLineItem> cartLineItemList)
      throws CartException {

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    PatronProfile
        patronProfile =
        patronProfileRepository.findOne(postCommitOrder.getPatronProfileId());
    PatronAdvanceProfile patronAdvanceProfile =
        patronAdvanceProfileRepository
            .getPatronAdvanceProfileByPatronProfileId(patronProfile.getPatronprofileid());

    Address patronBillingAddress =
        addressRepository.findPatronAddresses(postCommitOrder.getPatronProfileId())
            .stream()
            .filter(address -> address.getAddresstype().equalsIgnoreCase("Billing Address"))
            .findAny()
            .orElse(null);

    List<String>
        patronPhones =
        patronPhoneRepository.getNumbersByPatronProfileId(postCommitOrder.getPatronProfileId())
            .stream()
            .map(PatronPhone::getPhonenumber)
            .collect(Collectors.toList());

    CartLineItem cartLineItem = cartLineItemList.get(0);

    Locale countryLocale = new Locale("", patronBillingAddress.getCountry());

//    construct TicketProtectorRequest object to send over

//    requestValues
    TicketProtectorRequest.RequestValue requestValue = new TicketProtectorRequest.RequestValue();
    requestValue.setSecurityKey(securityKey);
    requestValue.setPartnerName(partnerName);
    requestValue.setCountry(country);
    requestValue.setIssueDate(LocalDate.now().format(dateTimeFormatter));
    TicketProtectorRequest.RequestValue.SalesOrigin
        salesOrigin =
        new TicketProtectorRequest.RequestValue.SalesOrigin();
    salesOrigin.setType(SALES_ORIGIN_TYPE);
    salesOrigin.setValue(origin);
    requestValue.setSalesOrigin(salesOrigin);
    requestValue.setLanguage(language);

    List<TicketProtectorRequest.RequestValue.Parameter> parameterList = new ArrayList<>();
    TicketProtectorRequest.RequestValue.Parameter
        parameter =
        new TicketProtectorRequest.RequestValue.Parameter();
    parameter.setName(CODE);
    parameter.setValue(cartLineItem.getProduct().getProductName());

    parameterList.add(parameter);
    requestValue.setParameterList(parameterList);

//    travelDescription
    TicketProtectorRequest.TravelDescription
        travelDescription =
        new TicketProtectorRequest.TravelDescription();
    String eventDate = cartLineItem.getProduct().getProductDate().format(dateTimeFormatter);
    travelDescription.setStartDate(eventDate);
    travelDescription.setEndDate(eventDate);
    travelDescription.setTravelType(travelType);
    travelDescription.setOriginLocation(country);
    travelDescription.setDestinationLocation(country);

    MonetaryAmount totalTravelPrice = cartLineItemList
        .stream()
        .map(CartLineItem::getSubTotal)
        .reduce(MonetaryFunctions.sum())
        .orElse(Money.zero(Monetary.getCurrency(LocaleContextHolder.getLocale())));

    travelDescription.setTotalTravelPrice(new TicketProtectorRequest.MoneyFormat(totalTravelPrice));
    travelDescription.setPolicyCover(policyCover);

//    productVariant
    TicketProtectorRequest.ProductVariant
        productVariant =
        new TicketProtectorRequest.ProductVariant();
    productVariant.setId(productVariantId);

//    contractHolder
    TicketProtectorRequest.ContractHolder
        contractHolder =
        new TicketProtectorRequest.ContractHolder();
    contractHolder.setPersonId(1L);
    contractHolder.setEmail(patronProfile.getEmailaddress());

    List<String> address = new ArrayList<>();
    address.add(patronBillingAddress.getLineone());
    address.add(patronBillingAddress.getLinetwo());
    address.add(patronBillingAddress.getLinethree());

    contractHolder.setAddress(address);
    contractHolder.setZipcode(patronBillingAddress.getPostcode());
    contractHolder
        .setTown(patronBillingAddress.getCity() == null ? "" : patronBillingAddress.getCity());
    contractHolder.setCountry(countryLocale.getDisplayCountry());

    contractHolder.setPhoneNumber(patronPhones);

//    insuredPerson
    List<TicketProtectorRequest.InsuredPerson> insuredPersonList = new ArrayList<>();

//    1 insured person for each line item
    for (int i = 0; i < cartLineItemList.size(); i++) {
      TicketProtectorRequest.InsuredPerson
          insuredPerson =
          new TicketProtectorRequest.InsuredPerson();
      insuredPerson.setId(i + 1L);
      insuredPerson.setTitle(shoppingCart.getTransactionReferenceNumber());
      insuredPerson.setSurname(patronProfile.getFirstname() + " " + patronProfile.getLastname());

      BigDecimal
          ticketSubTotal =
          cartLineItemList.get(i).getSubTotal().getNumber().numberValue(BigDecimal.class)
              .setScale(2, BigDecimal.ROUND_UP);
      insuredPerson.setFirstname(ticketSubTotal.toString());

      if (useManualInputDate) {
        LocalDate dob = LocalDate.of(manualInputYy, manualInputMm, manualInputDd);
        insuredPerson.setDob(dob.format(dateTimeFormatter));
      } else {
        insuredPerson.setDob(patronAdvanceProfile.getDob().format(dateTimeFormatter));
      }

      insuredPersonList.add(insuredPerson);
    }

//    payment
    TicketProtectorRequest.Payment payment = new TicketProtectorRequest.Payment();
    TicketProtectorRequest.Payment.PaymentHolder
        paymentHolder =
        new TicketProtectorRequest.Payment.PaymentHolder();
    paymentHolder.setLastName(patronProfile.getFirstname() + " " + patronProfile.getLastname());
    paymentHolder.setAddress(patronBillingAddress.getLineone());
    paymentHolder.setZipcode(patronBillingAddress.getPostcode());
    paymentHolder.setTown(patronBillingAddress.getCity());
    paymentHolder.setCountry(country);
    TicketProtectorRequest.Payment.CardInformation
        cardInformation =
        new TicketProtectorRequest.Payment.CardInformation();
    cardInformation.setCardNumber(shoppingCart.getCreditCardNo());

    int creditCardValidityMm = Integer.parseInt(shoppingCart.getCreditCardExpiryMM());
    int creditCardValidityYy = Integer.parseInt(shoppingCart.getCreditCardExpiryYY());

    LocalDate
        creditCardValidity =
        LocalDate.of(2000 + creditCardValidityYy, creditCardValidityMm, 1);
    cardInformation.setValidityDate(creditCardValidity.format(dateTimeFormatter));

    payment.setPaymentHolder(paymentHolder);
    payment.setCardInformation(cardInformation);

//    request
    MonetaryAmount totalPremium = getTicketProtectorAmount(shoppingCart.getCartGuid());

    TicketProtectorRequest ticketProtectorRequest = new TicketProtectorRequest();
    ticketProtectorRequest.setXmlns(XMLNS);
    ticketProtectorRequest.setRequestValue(requestValue);
    ticketProtectorRequest.setTravelDescription(travelDescription);
    ticketProtectorRequest.setProductVariant(productVariant);
    ticketProtectorRequest.setContractHolder(contractHolder);
    ticketProtectorRequest.setInsuredPerson(insuredPersonList);
    ticketProtectorRequest
        .setTotalPaidPremium(new TicketProtectorRequest.MoneyFormat(totalPremium));
    ticketProtectorRequest.setPayment(payment);
//    confirmationEmail not working in UAT
//    ticketProtectorRequest.setConfirmationEmail(patronProfile.getEmailaddress());
    ticketProtectorRequest.setConfirmationMessaging(true);
    ticketProtectorRequest.setPaymentMessaging(false);

    return ticketProtectorRequest;
  }

  private int getTicketProtectorResponse(String transactionRefNumber,
                                         TicketProtectorRequest ticketProtectorRequest) {

    String xmlOutput = createTicketProtectorXml(ticketProtectorRequest);
    String xmlResponse = sendXmlToTicketProtector(xmlOutput);
    SubscriptionACK acknowledgement = getTicketProtectorAcknowledgement(xmlResponse);
    Transaction
        transaction =
        transactionRepository.findByTransactionRefNumber(transactionRefNumber);
    String response;

    //    ticketprotector status: 1 = success, 2 = fail
    int status = 2;

    if (acknowledgement.getContractNumber() == null) {
      response = acknowledgement.getError().getErrorMessage();
    } else {
      response = acknowledgement.getContractNumber();
      status = 1;
    }

    ticketProtectorRequest.getPayment().getCardInformation().setCardNumber(
        SecurityUtils
            .maskPartial(ticketProtectorRequest.getPayment().getCardInformation().getCardNumber()));
    String xmlOutputToDb = createTicketProtectorXml(ticketProtectorRequest);
    BigDecimal totalPremium = ticketProtectorRequest.getTotalPaidPremium().getValue();

    TicketProtector
        ticketProtector =
        new TicketProtector(response, totalPremium, xmlOutputToDb, response, status,
            transaction.getTransactionid(), 0);
    ticketProtectorRepository.save(ticketProtector);

    return status;
  }

  private SubscriptionACK getTicketProtectorAcknowledgement(String xmlResponse) {

    SubscriptionACK acknowledgement = new SubscriptionACK();

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document xmlDoc = builder.parse(new InputSource(new StringReader(xmlResponse)));

      try {
        JAXBContext jaxbContext = JAXBContext.newInstance(SubscriptionACK.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        acknowledgement = (SubscriptionACK) unmarshaller.unmarshal(xmlDoc);

      } catch (JAXBException e) {
        e.printStackTrace();
      }

    } catch (SAXException | ParserConfigurationException | IOException e) {
      logger.error(e.getMessage());
    }

    return acknowledgement;

  }

  private String createTicketProtectorXml(TicketProtectorRequest request) {

    StringWriter stringWriter = new StringWriter();

    try {
      JAXBContext jaxbContext = JAXBContext.newInstance(TicketProtectorRequest.class);
      Marshaller marshaller = jaxbContext.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      marshaller.marshal(request, stringWriter);
      return stringWriter.toString();

    } catch (JAXBException e) {
      logger.error(e.getMessage());
    }

    return stringWriter.toString();

  }

  private String sendXmlToTicketProtector(String xmlOutput) {
    StringBuffer sb = new StringBuffer();

    try {
      URL url = new URL(ticketProtectorEndpoint);
      URLConnection urlConnection = url.openConnection();
      if (urlConnection instanceof HttpsURLConnection) {
        ((HttpsURLConnection) urlConnection)
            .setSSLSocketFactory((SSLSocketFactory) SSLSocketFactory.getDefault());
      }

      urlConnection.setRequestProperty("Method", "POST");
      urlConnection.setConnectTimeout(30000);
      urlConnection.setDoOutput(true);
      urlConnection.setDoInput(true);
      urlConnection.setAllowUserInteraction(true);

      OutputStream os = urlConnection.getOutputStream();
      os.write(xmlOutput.getBytes());

      InputStream returnData = urlConnection.getInputStream();

      sb = new StringBuffer();
      int ch;
      while ((ch = returnData.read()) != -1) {
        sb = sb.append(String.valueOf((char) ch));
      }
      returnData.close();

    } catch (IOException e) {
      logger.error(e.getMessage());
    }

    return sb.toString();
  }

}

package com.stixcloud.cart.ticketprotector.api;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.money.MonetaryAmount;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "subscription")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"requestValues", "travelDescription", "productVariant", "contractHolder",
    "insuredPerson", "totalPaidPremium", "payment",
    "confirmationEmail", "confirmationMessaging", "paymentMessaging"})
public class TicketProtectorRequest implements Serializable {

  @XmlTransient
  private static final long serialVersionUID = -5241559125539522880L;
  @XmlAttribute
  private String xmlns;
  private RequestValue requestValues;
  private TravelDescription travelDescription;
  private ProductVariant productVariant;
  private ContractHolder contractHolder;
  private List<InsuredPerson> insuredPerson;
  private MoneyFormat totalPaidPremium;
  private Payment payment;
  private String confirmationEmail;
  private boolean confirmationMessaging;
  private boolean paymentMessaging;

  public String getXmlns() {
    return xmlns;
  }

  public void setXmlns(String xmlns) {
    this.xmlns = xmlns;
  }

  public RequestValue getRequestValue() {
    return requestValues;
  }

  public void setRequestValue(
      RequestValue requestValue) {
    this.requestValues = requestValue;
  }

  public TravelDescription getTravelDescription() {
    return travelDescription;
  }

  public void setTravelDescription(
      TravelDescription travelDescription) {
    this.travelDescription = travelDescription;
  }

  public ProductVariant getProductVariant() {
    return productVariant;
  }

  public void setProductVariant(
      ProductVariant productVariant) {
    this.productVariant = productVariant;
  }

  public ContractHolder getContractHolder() {
    return contractHolder;
  }

  public void setContractHolder(
      ContractHolder contractHolder) {
    this.contractHolder = contractHolder;
  }

  public List<InsuredPerson> getInsuredPerson() {
    return insuredPerson;
  }

  public void setInsuredPerson(
      List<InsuredPerson> insuredPerson) {
    this.insuredPerson = insuredPerson;
  }

  public MoneyFormat getTotalPaidPremium() {
    return totalPaidPremium;
  }

  public void setTotalPaidPremium(
      MoneyFormat totalPaidPremium) {
    this.totalPaidPremium = totalPaidPremium;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }

  public String getConfirmationEmail() {
    return confirmationEmail;
  }

  public void setConfirmationEmail(String confirmationEmail) {
    this.confirmationEmail = confirmationEmail;
  }

  public boolean isConfirmationMessaging() {
    return confirmationMessaging;
  }

  public void setConfirmationMessaging(boolean confirmationMessaging) {
    this.confirmationMessaging = confirmationMessaging;
  }

  public boolean isPaymentMessaging() {
    return paymentMessaging;
  }

  public void setPaymentMessaging(boolean paymentMessaging) {
    this.paymentMessaging = paymentMessaging;
  }

  @XmlRootElement(name = "requestValues")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"securityKey", "partnerName", "country", "issueDate", "salesOrigin",
      "language",
      "parameterList"})
  public static class RequestValue {
    private String securityKey;
    private String partnerName;
    private String country;
    private String issueDate;
    private SalesOrigin salesOrigin;
    private String language;
    @XmlElementWrapper(name = "Parameters")
    @XmlElement(name = "Parameter")
    private List<Parameter> parameterList;

    public String getSecurityKey() {
      return securityKey;
    }

    public void setSecurityKey(String securityKey) {
      this.securityKey = securityKey;
    }

    public String getPartnerName() {
      return partnerName;
    }

    public void setPartnerName(String partnerName) {
      this.partnerName = partnerName;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public String getIssueDate() {
      return issueDate;
    }

    public void setIssueDate(String issueDate) {
      this.issueDate = issueDate;
    }

    public SalesOrigin getSalesOrigin() {
      return salesOrigin;
    }

    public void setSalesOrigin(
        SalesOrigin salesOrigin) {
      this.salesOrigin = salesOrigin;
    }

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }

    public List<Parameter> getParameterList() {
      return parameterList;
    }

    public void setParameterList(
        List<Parameter> parameterList) {
      this.parameterList = parameterList;
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class SalesOrigin {

      @XmlAttribute
      private String type;
      @XmlValue
      private String value;

      public String getType() {
        return type;
      }

      public void setType(String type) {
        this.type = type;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    public static class Parameter {

      @XmlAttribute
      private String name;
      @XmlAttribute
      private String value;

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public String getValue() {
        return value;
      }

      public void setValue(String value) {
        this.value = value;
      }
    }
  }

  @XmlRootElement(name = "travelDescription")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"startDate", "endDate", "travelType", "originLocation",
      "destinationLocation", "totalTravelPrice", "policyCover"})
  public static class TravelDescription {

    private String startDate;
    private String endDate;
    private String travelType;
    private String originLocation;
    private String destinationLocation;
    private MoneyFormat totalTravelPrice;
    private String policyCover;

    public String getStartDate() {
      return startDate;
    }

    public void setStartDate(String startDate) {
      this.startDate = startDate;
    }

    public String getEndDate() {
      return endDate;
    }

    public void setEndDate(String endDate) {
      this.endDate = endDate;
    }

    public String getTravelType() {
      return travelType;
    }

    public void setTravelType(String travelType) {
      this.travelType = travelType;
    }

    public String getOriginLocation() {
      return originLocation;
    }

    public void setOriginLocation(String originLocation) {
      this.originLocation = originLocation;
    }

    public String getDestinationLocation() {
      return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
      this.destinationLocation = destinationLocation;
    }

    public MoneyFormat getTotalTravelPrice() {
      return totalTravelPrice;
    }

    public void setTotalTravelPrice(
        MoneyFormat totalTravelPrice) {
      this.totalTravelPrice = totalTravelPrice;
    }

    public String getPolicyCover() {
      return policyCover;
    }

    public void setPolicyCover(String policyCover) {
      this.policyCover = policyCover;
    }
  }

  @XmlAccessorType(XmlAccessType.NONE)
  public static class ProductVariant {

    @XmlAttribute
    public String id;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }
  }

  @XmlRootElement(name = "contractHolder")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"email", "address", "zipcode", "town", "country", "phoneNumber"})
  public static class ContractHolder {

    @XmlAttribute
    private Long personId;
    private String email;
    private List<String> address;
    private String zipcode;
    private String town;
    private String country;
    private List<String> phoneNumber;

    public Long getPersonId() {
      return personId;
    }

    public void setPersonId(Long personId) {
      this.personId = personId;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public List<String> getAddress() {
      return address;
    }

    public void setAddress(List<String> address) {
      this.address = address;
    }

    public String getZipcode() {
      return zipcode;
    }

    public void setZipcode(String zipcode) {
      this.zipcode = zipcode;
    }

    public String getTown() {
      return town;
    }

    public void setTown(String town) {
      this.town = town;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public List<String> getPhoneNumber() {
      return phoneNumber;
    }

    public void setPhoneNumber(List<String> phoneNumber) {
      this.phoneNumber = phoneNumber;
    }
  }

  @XmlRootElement(name = "insuredPerson")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"title", "surname", "firstname", "dob"})
  public static class InsuredPerson {

    @XmlAttribute
    private Long id;
    private String title;
    private String surname;
    private String firstname;
    private String dob;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getSurname() {
      return surname;
    }

    public void setSurname(String surname) {
      this.surname = surname;
    }

    public String getFirstname() {
      return firstname;
    }

    public void setFirstname(String firstname) {
      this.firstname = firstname;
    }

    public String getDob() {
      return dob;
    }

    public void setDob(String dob) {
      this.dob = dob;
    }
  }

  @XmlRootElement(name = "payment")
  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlType(propOrder = {"paymentHolder", "cardInformation"})
  public static class Payment {

    private PaymentHolder paymentHolder;
    private CardInformation cardInformation;

    public PaymentHolder getPaymentHolder() {
      return paymentHolder;
    }

    public void setPaymentHolder(
        PaymentHolder paymentHolder) {
      this.paymentHolder = paymentHolder;
    }

    public CardInformation getCardInformation() {
      return cardInformation;
    }

    public void setCardInformation(
        CardInformation cardInformation) {
      this.cardInformation = cardInformation;
    }

    @XmlRootElement(name = "paymentHolder")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"lastName", "address", "zipcode", "town", "country"})
    public static class PaymentHolder {

      private String lastName;
      private String address;
      private String zipcode;
      private String town;
      private String country;

      public String getLastName() {
        return lastName;
      }

      public void setLastName(String lastName) {
        this.lastName = lastName;
      }

      public String getAddress() {
        return address;
      }

      public void setAddress(String address) {
        this.address = address;
      }

      public String getZipcode() {
        return zipcode;
      }

      public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
      }

      public String getTown() {
        return town;
      }

      public void setTown(String town) {
        this.town = town;
      }

      public String getCountry() {
        return country;
      }

      public void setCountry(String country) {
        this.country = country;
      }
    }

    @XmlRootElement(name = "cardInformation")
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(propOrder = {"cardNumber", "validityDate"})
    public static class CardInformation {

      private String cardNumber;
      private String validityDate;

      public String getCardNumber() {
        return cardNumber;
      }

      public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
      }

      public String getValidityDate() {
        return validityDate;
      }

      public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
      }
    }
  }

  @XmlAccessorType(XmlAccessType.NONE)
  public static class MoneyFormat {

    @XmlAttribute
    private String currency;
    @XmlValue
    private BigDecimal value;

    public MoneyFormat(MonetaryAmount monetaryAmount) {
      this.currency = monetaryAmount.getCurrency().toString();
      this.value =
          monetaryAmount.getNumber().numberValue(BigDecimal.class).setScale(2, BigDecimal.ROUND_UP);
    }

    public String getCurrency() {
      return currency;
    }

    public void setCurrency(String currency) {
      this.currency = currency;
    }

    public BigDecimal getValue() {
      return value;
    }

    public void setValue(BigDecimal value) {
      this.value = value;
    }
  }

}

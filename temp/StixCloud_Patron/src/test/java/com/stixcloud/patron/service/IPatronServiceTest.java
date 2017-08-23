package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.persistence.NoResultException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.domain.PatronAddress;
import com.stixcloud.domain.PatronAdvanceProfile;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.patron.api.PatronRequest;
import com.stixcloud.patron.api.PatronSolicitationUpdateRequest;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.TenantPropertiesTest;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.domain.Country;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.domain.PatronProfileView;
import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.repo.IEmailTemplateRepository;
import com.stixcloud.patron.repo.IPatronRepository;
import com.stixcloud.patron.repo.ITenantConfigRepository;
import com.stixcloud.patron.util.MasterCodeTableUtil;

@Ignore
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPatronServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronServiceTest.class);

  @Autowired
  private ObjectMapper objectMapper;

  @InjectMocks
  private PatronService patronService;
  @Mock
  private IPatronRepository patronRepo;
  @Mock
  private IPatronProfileJsonService patronProfileJsonService;
  @Mock
  private PatronProfileService patronProfileService;
  @Mock
  private PatronPhoneService patronPhoneService;
  @Mock
  private PatronInternetAccountService patronInternetAccountService;
  @Mock
  private PatronAdvanceProfileService patronAdvanceProfileService;
  @Mock
  private PatronAddressService patronAddressService;
  @Mock
  private MessageSource messageSource;
  @Mock
  private MasterCodeTableService masterCodeTableService;
  @Mock
  private ICountryService countryService;
  @Mock
  private IAddressService addressService;
  @Mock
  private MasterCodeTableUtil mctUtil;
  @Mock
  private IPatronSolicitationViewService patronSolicitationViewService;
  @Mock
  private IPatronSolicitationService patronSolicitationService;
  @Mock
  IEmailService emailService;
  @Mock
  private IEmailTemplateRepository emailTemplateRepo;
  @Mock
  private ITenantConfigRepository tenantConfigRepo;
  @Mock
  private VelocityEngine velocityEngine;

  private List<PatronProfileView> patronProfileList;
  private List<PatronProfileView> profileList;
  private List<ContactJson> contactNos;
  private IdentificationJson identification;
  private List<AddressJson> addresses;
  private CountryPhoneCodeJson countryResponse;
  private Long patronId;
  private Long userInfoId;
  private List<MasterCodeTable> masterCodeTables;
  private List<MasterCodeTable> genderTitleMct;
  private List<Country> coutries;
  // private SimpleDateFormat sdf;
  private PatronProfile insertedPatronProfile;
  List<PatronSolicitationView> patronSolicitationViews;
  private PatronSolicitationUpdateRequest solicitationUpdateRequest;
  private List<PatronSolicitation> patronSolicitationList;
  private List<PatronSolicitationJson> patronSolicitationJsonList;

  @Before
  public void setup() throws Exception {
    JacksonTester.initFields(this, objectMapper);
    TenantPropertiesTest.setUp();
    masterCodeTables = new ArrayList<MasterCodeTable>();
    genderTitleMct = new ArrayList<MasterCodeTable>();
    coutries = new ArrayList<Country>();
    // sdf = new SimpleDateFormat("dd-MM-yyyy");
    patronSolicitationViews = new ArrayList<>();
    patronId = 1L;
    userInfoId = 99L;
    patronProfileList = new ArrayList<PatronProfileView>();
    PatronProfileView patron1 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    PatronProfileView patron2 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Mailing Address",
        "121", "2", "CMC Tower 2", "15236", "VN", "T", 20000L, 15265L);
    patronProfileList.add(patron1);
    patronProfileList.add(patron2);

    MasterCodeTable mct1 = new MasterCodeTable(14L, "Title", true, "MR", "MR");
    MasterCodeTable mct2 =
        new MasterCodeTable(15L, "Identification Number Type", true, "Passport", "P");
    MasterCodeTable mct3 = new MasterCodeTable(16L, "Gender", true, "Male", "M");
    MasterCodeTable mct4 = new MasterCodeTable(98L, "Solicitation Type", true, "Tenant", "T");
    MasterCodeTable mct5 = new MasterCodeTable(99L, "Solicitation Type", true, "Venue", "V");
    MasterCodeTable mct6 = new MasterCodeTable(97L, "Solicitation Type", true, "Promoter", "P");
    masterCodeTables.add(mct1);
    masterCodeTables.add(mct2);
    masterCodeTables.add(mct3);
    masterCodeTables.add(mct4);
    masterCodeTables.add(mct5);
    masterCodeTables.add(mct6);

    setupForConvertToResponse();
    setupForGetPatronSolicitation();
    setupForUpdatePatronSolicitation();
    setupForRemoveDuplicateSolicitation();
  }

  private void setupForConvertToResponse() {
    profileList = new ArrayList<PatronProfileView>();
    PatronProfileView patron = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    profileList.add(patron);
    countryResponse = new CountryPhoneCodeJson("VN", 84);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact = new ContactJson("HOME", countryResponse, "84923654115");
    contactNos.add(contact);
    identification = new IdentificationJson("NRIC/FIN", "S1234567D");
    addresses = new ArrayList<AddressJson>();
    AddressJson address =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    addresses.add(address);
  }

  private void setupCaseManyAddress() {
    addresses = new ArrayList<AddressJson>();
    AddressJson address1 =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    AddressJson address2 =
        new AddressJson("Mailing Address", "VN", "121", "2", "CMC Tower 2", "15236");
    addresses.add(address1);
    addresses.add(address2);
  }

  private void setupCaseManyPhone() {
    patronProfileList = new ArrayList<PatronProfileView>();
    PatronProfileView patron3 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "MOBILE", "84953654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    PatronProfileView patron4 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    patronProfileList.add(patron4);
    patronProfileList.add(patron3);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact1 = new ContactJson("HOME", countryResponse, "84923654115");
    ContactJson contact2 = new ContactJson("MOBILE", countryResponse, "84953654115");
    contactNos.add(contact1);
    contactNos.add(contact2);
    addresses = new ArrayList<AddressJson>();
    AddressJson address =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    addresses.add(address);
  }

  private void setupCaseManyAddressManyPhone() {
    patronProfileList = new ArrayList<PatronProfileView>();
    PatronProfileView patron1 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    PatronProfileView patron2 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "HOME", "84923654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Mailing Address",
        "121", "2", "CMC Tower 2", "15236", "VN", "T", 20000L, 15265L);
    PatronProfileView patron3 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "MOBILE", "84953654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Billing Address",
        "111", "1", "CMC Tower", "15236", "VN", "T", 20000L, 15265L);
    PatronProfileView patron4 = new PatronProfileView("Mr", "CMC", "Corp", "cmc@gmail.com", 1988,
        "MOBILE", "84953654115", 84, "VN", "VN", "VN", "NRIC/FIN", "S1234567D", "Mailing Address",
        "121", "2", "CMC Tower 2", "15236", "VN", "T", 20000L, 15265L);
    patronProfileList.add(patron1);
    patronProfileList.add(patron2);
    patronProfileList.add(patron3);
    patronProfileList.add(patron4);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact1 = new ContactJson("HOME", countryResponse, "84923654115");
    ContactJson contact2 = new ContactJson("MOBILE", countryResponse, "84953654115");
    contactNos.add(contact1);
    contactNos.add(contact2);
    addresses = new ArrayList<AddressJson>();
    AddressJson address1 =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    AddressJson address2 =
        new AddressJson("Mailing Address", "VN", "121", "2", "CMC Tower 2", "15236");
    addresses.add(address1);
    addresses.add(address2);
  }

  private void setupForCreatePatron() {
    MasterCodeTable mct3 = new MasterCodeTable(16L, "Gender", true, "Male", "M");
    MasterCodeTable mct1 = new MasterCodeTable(14L, "Title", true, "MR", "MR");
    genderTitleMct.add(mct3);
    genderTitleMct.add(mct1);
    Country c1 = new Country(1L, "VN", "VND", "84");
    Country c2 = new Country(2L, "SG", "SGD", "65");
    coutries.add(c1);
    coutries.add(c2);
    insertedPatronProfile = new PatronProfile();
    insertedPatronProfile.setPatronprofileid(11L);
    Address address = new Address();
    address.setAddressid(11L);
    when(masterCodeTableService.findByCategory(anyString())).thenReturn(masterCodeTables);
    when(patronProfileService.getSequenceAccNo()).thenReturn(155L);
    when(patronProfileService.insertPatronProfile(any(PatronProfile.class)))
        .thenReturn(insertedPatronProfile);
    when(countryService.fillAllCountry()).thenReturn(coutries);
    when(addressService.save(any(Address.class))).thenReturn(address);
    when(mctUtil.getMasterCodeTable(anyString(), anyListOf(MasterCodeTable.class),
        anyListOf(MasterCodeTable.class))).thenReturn(genderTitleMct);
  }

  private void setupForGetPatronSolicitation() {
    /*
     * PatronSolicitationView solicitation = new PatronSolicitationView("Tenant", 152552L, "SISTIC",
     * "http://abcd.com", true, 252545L); PatronSolicitationView solicitation2 = new
     * PatronSolicitationView("Promoter", 152552L, "Esplanade", "http://ghijk.com", true, 252545L);
     * patronSolicitationViews.add(solicitation); patronSolicitationViews.add(solicitation2);
     */
  }

  private void setupForUpdatePatronSolicitation() throws Exception {
    solicitationUpdateRequest = objectMapper.readValue(
        this.getClass().getResource("/patron/json/solicitationUpdateRequest.json"),
        PatronSolicitationUpdateRequest.class);
    patronSolicitationList = new ArrayList<>();
    PatronSolicitation p1 = new PatronSolicitation(11L, 88L, 98L, 77L, 1L, true,
        OffsetDateTime.of(2017, 10, 10, 10, 10, 10, 10, ZoneOffset.UTC), 22L);
    PatronSolicitation p2 = new PatronSolicitation(12L, 88L, 97L, 77L, 99L, true,
        OffsetDateTime.of(2017, 10, 10, 10, 10, 10, 10, ZoneOffset.UTC), 22L);
    patronSolicitationList.add(p1);
    patronSolicitationList.add(p2);
    when(patronSolicitationService.findByPatronProfileId(anyLong(), anyLong()))
        .thenReturn(patronSolicitationList);
    when(masterCodeTableService.findByCategory(anyString())).thenReturn(masterCodeTables);
  }

  private void setupForRemoveDuplicateSolicitation() {
    /*
     * patronSolicitationJsonList = new ArrayList<>(); PatronSolicitationJson json1 = new
     * PatronSolicitationJson("Tenant", 99L, "SISTIC", "http://sistic.com.sg", false);
     * PatronSolicitationJson json2 = new PatronSolicitationJson("Tenant", 99L, "SISTIC",
     * "http://sistic.com.sg", true); PatronSolicitationJson json3 = new
     * PatronSolicitationJson("Tenant", 99L, "SISTIC", "http://sistic.com.sg", true);
     * PatronSolicitationJson json4 = new PatronSolicitationJson("Tenant", 98L, "SISTIC",
     * "http://sistic.com.sg", false); PatronSolicitationJson json5 = new
     * PatronSolicitationJson("Tenant", 99L, "SISTIC", "http://sistic.com.sg", false);
     * PatronSolicitationJson json6 = new PatronSolicitationJson("Promoter", 100L, "Promoter",
     * "http://sistic.com.sg", false); PatronSolicitationJson json7 = new
     * PatronSolicitationJson("Venue", 100L, "Venue", "http://sistic.com.sg", true);
     * PatronSolicitationJson json8 = new PatronSolicitationJson("Venue", 100L, "Venue",
     * "http://sistic.com.sg", false); patronSolicitationJsonList.add(json1);
     * patronSolicitationJsonList.add(json2); patronSolicitationJsonList.add(json3);
     * patronSolicitationJsonList.add(json4); patronSolicitationJsonList.add(json5);
     * patronSolicitationJsonList.add(json6); patronSolicitationJsonList.add(json7);
     * patronSolicitationJsonList.add(json8);
     */
  }

  @Test
  public void getPatronProfile() {

    when(patronRepo.getPatronProfile(anyLong())).thenReturn(patronProfileList);
    long startTime = System.nanoTime();
    List<PatronProfileView> result = patronService.getPatronProfile(152L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronProfileList, result);
  }

  @Test
  public void convertToResponseCase1Row() {

    PatronProfileJson expected = new PatronProfileJson("cmc@gmail.com", 20000L, "Mr", "CMC", "Corp",
        contactNos, 1988, "VN", "VN", identification, true, addresses);
    long startTime = System.nanoTime();
    PatronProfileJson actual = patronService.convertToResponse(profileList);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, actual);
  }

  @Test
  public void convertToResponseCaseManyAddress() {

    setupCaseManyAddress();
    PatronProfileJson expected = new PatronProfileJson("cmc@gmail.com", 20000L, "Mr", "CMC", "Corp",
        contactNos, 1988, "VN", "VN", identification, true, addresses);
    long startTime = System.nanoTime();
    PatronProfileJson actual = patronService.convertToResponse(patronProfileList);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, actual);
  }

  @Test
  public void convertToResponseCaseManyPhone() {

    setupCaseManyPhone();
    PatronProfileJson expected = new PatronProfileJson("cmc@gmail.com", 20000L, "Mr", "CMC", "Corp",
        contactNos, 1988, "VN", "VN", identification, true, addresses);
    long startTime = System.nanoTime();
    PatronProfileJson actual = patronService.convertToResponse(patronProfileList);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, actual);
  }

  @Test
  public void convertToResponseCaseManyAddressManyPhone() {

    setupCaseManyAddressManyPhone();
    PatronProfileJson expected = new PatronProfileJson("cmc@gmail.com", 20000L, "Mr", "CMC", "Corp",
        contactNos, 1988, "VN", "VN", identification, true, addresses);
    long startTime = System.nanoTime();
    PatronProfileJson actual = patronService.convertToResponse(patronProfileList);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, actual);
  }

  @Test
  public void checkExistEmailAddressCaseExist() {
    when(patronRepo.checkExistEmailAddress(anyString())).thenReturn(1);
    long startTime = System.nanoTime();
    boolean actual = patronService.checkExistEmailAddress("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void checkExistEmailAddressCaseNotExist() {
    when(patronRepo.checkExistEmailAddress(anyString())).thenReturn(0);
    long startTime = System.nanoTime();
    boolean actual = patronService.checkExistEmailAddress("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void updatePatronProfileCaseAdress() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(false);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request, null, patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(0)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCasePhone() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(true);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request, null,  patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(0)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCaseAdvanceProfile() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(false);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
        .thenReturn("You must have first name information. Please fill your first name");
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request, null,  patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(0)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCaseInternetAccount() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(false);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request,  null, patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(0)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCaseProfile() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(false);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request, null,  patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(1)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCaseNoneUpdate() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(false);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(false);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request,  null, patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(0)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(0)).updatePatron(request, patronId, userInfoId);
  }

  @Test
  public void updatePatronProfileCaseAllUpdate() throws Exception {
    PatronUpdateRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/updatePatronRequest.json"),
            PatronUpdateRequest.class);
    when(patronAddressService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronPhoneService.isChanged(any(PatronUpdateRequest.class), any(PatronProfileJson.class)))
        .thenReturn(true);
    when(patronInternetAccountService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronAdvanceProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    when(patronProfileService.isChanged(any(PatronUpdateRequest.class),
        any(PatronProfileJson.class))).thenReturn(true);
    long startTime = System.nanoTime();
    patronService.updatePatronProfile(request, null,  patronId, userInfoId);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(patronAddressService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronPhoneService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronInternetAccountService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronAdvanceProfileService, times(1)).updatePatron(request, patronId, userInfoId);
    verify(patronProfileService, times(1)).updatePatron(request, patronId, userInfoId);
  }

  @Test(expected = SisticApiException.class)
  public void insertPatronProfileCaseMandatoryField() throws Exception {
    MasterCodeTable mct1 = new MasterCodeTable(14L, "Title", true, "MR", "MR");
    MasterCodeTable mct3 = new MasterCodeTable(16L, "Gender", true, "Male", "M");
    genderTitleMct.add(mct3);
    genderTitleMct.add(mct1);
    when(mctUtil.getMasterCodeTable(anyString(), anyListOf(MasterCodeTable.class),
        anyListOf(MasterCodeTable.class))).thenReturn(genderTitleMct);
    PatronRequest request = objectMapper.readValue(
        this.getClass().getResource("/patron/json/createPatronRequestCaseMissMandatoryField.json"),
        PatronRequest.class);
    when(messageSource.getMessage(anyString(), any(), any(Locale.class)))
        .thenReturn("Missing mandatory field");
    patronService.createPatronProfile(request, 88L);
  }

  @Test
  public void insertPatronProfileCaseNormal() throws Exception {
    setupForCreatePatron();
    Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString(),
        anyString(), (HashMap<String, byte[]>) anyMapOf(String.class, byte[].class));
    when(emailTemplateRepo.getEmailTemplatePath(anyString(), anyString()))
        .thenReturn("/SISTIC/velocity/en/channel/45/sendToken.vm");
    when(tenantConfigRepo.findAll()).thenReturn(new ArrayList<>());
    PatronRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/createPatronRequest.json"),
            PatronRequest.class);
    ArgumentCaptor<PatronProfile> argumentPatronProfile =
        ArgumentCaptor.forClass(PatronProfile.class);
    ArgumentCaptor<PatronAdvanceProfile> argumentPatronAdv =
        ArgumentCaptor.forClass(PatronAdvanceProfile.class);
    ArgumentCaptor<PatronAddress> argumentPatronAddress =
        ArgumentCaptor.forClass(PatronAddress.class);
    ArgumentCaptor<PatronInternetAccount> argumentPatronAcc =
        ArgumentCaptor.forClass(PatronInternetAccount.class);
    ArgumentCaptor<List<PatronPhone>> argumentPatronPhone =
        ArgumentCaptor.forClass((Class) List.class);

    patronService.createPatronProfile(request, 33L);
    verify(patronProfileService, times(1)).insertPatronProfile(argumentPatronProfile.capture());
    verify(patronAdvanceProfileService, times(1))
        .insertPatronAdvanceProfile(argumentPatronAdv.capture());
    verify(patronAddressService, times(1)).insertPatronAddress(argumentPatronAddress.capture());
    verify(patronInternetAccountService, times(1))
        .insertPatronInternetAccount(argumentPatronAcc.capture());
    verify(patronPhoneService, times(1)).insertPatronPhone(argumentPatronPhone.capture());
  }

  @Test
  public void getPatronSolicitation() {
    /*
     * when(patronSolicitationViewService.getPatronSolicitationView(anyLong()))
     * .thenReturn(patronSolicitationViews); long startTime = System.nanoTime();
     * List<PatronSolicitationView> actual = patronService.getPatronSolicitationView(555L);
     * LOGGER.info("Time taken " + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime),
     * TimeUnit.NANOSECONDS) + " ms"); assertEquals(patronSolicitationViews, actual);
     */
  }

  @Test
  public void updatePatronSolicitationCaseUpdate() throws SisticApiException {
    long startTime = System.nanoTime();
    patronService.updatePatronSolicitation(solicitationUpdateRequest, 25L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<PatronSolicitation> argumentPatronSolicitation =
        ArgumentCaptor.forClass(PatronSolicitation.class);
    ArgumentCaptor<List<PatronSolicitation>> argumentPatronSolicitationList =
        ArgumentCaptor.forClass((Class) List.class);
    verify(patronSolicitationService, times(1)).update(argumentPatronSolicitationList.capture());
    verify(patronSolicitationService, times(1)).delete(argumentPatronSolicitationList.capture());
  }

  @Test(expected = SisticApiException.class)
  public void updatePatronSolicitationCaseUpdateError() throws SisticApiException, Exception {
    PatronSolicitationUpdateRequest solicitationUpdateRequest = objectMapper.readValue(
        this.getClass().getResource("/patron/json/solicitationUpdateErrorRequest.json"),
        PatronSolicitationUpdateRequest.class);
    long startTime = System.nanoTime();
    patronService.updatePatronSolicitation(solicitationUpdateRequest, 25L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<PatronSolicitation> argumentPatronSolicitation =
        ArgumentCaptor.forClass(PatronSolicitation.class);
    ArgumentCaptor<List<PatronSolicitation>> argumentPatronSolicitationList =
        ArgumentCaptor.forClass((Class) List.class);
    verify(patronSolicitationService, times(0)).update(argumentPatronSolicitationList.capture());
    verify(patronSolicitationService, times(0)).delete(argumentPatronSolicitationList.capture());
  }

  @Test(expected = SisticApiException.class)
  public void insertPatronProfileFieldTitleNotCorrect() throws Exception {
    when(mctUtil.getMasterCodeTable(anyString(), anyListOf(MasterCodeTable.class),
        anyListOf(MasterCodeTable.class))).thenReturn(genderTitleMct);
    PatronRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/createPatronRequest.json"),
            PatronRequest.class);
    patronService.createPatronProfile(request, 88L);
    Assert.fail();
  }

  @Test(expected = SisticApiException.class)
  public void insertPatronProfileFieldYearOfBirthNotCorrect() throws Exception {
    setupForCreatePatron();
    PatronRequest request = objectMapper.readValue(
        this.getClass().getResource("/patron/json/createPatronRequestYearOfBrithNotCorrect.json"),
        PatronRequest.class);
    patronService.createPatronProfile(request, 88L);
  }

  @Test(expected = SisticApiException.class)
  public void insertPatronProfileFieldContryCodeNotCorrect() throws Exception {
    setupForCreatePatron();
    PatronRequest request = objectMapper.readValue(
        this.getClass().getResource("/patron/json/createPatronRequestCountryCodeNotCorrect.json"),
        PatronRequest.class);
    patronService.createPatronProfile(request, 88L);
  }

  @Test(expected = SisticApiException.class)
  public void insertPatronProfileIdentityMctEmpty() throws Exception {
    //
    MasterCodeTable mct3 = new MasterCodeTable(16L, "Gender", true, "Male", "M");
    MasterCodeTable mct1 = new MasterCodeTable(14L, "Title", true, "MR", "MR");
    genderTitleMct.add(mct3);
    genderTitleMct.add(mct1);
    Country c1 = new Country(1L, "VN", "VND", "84");
    Country c2 = new Country(2L, "SG", "SGD", "65");
    coutries.add(c1);
    coutries.add(c2);
    insertedPatronProfile = new PatronProfile();
    insertedPatronProfile.setPatronprofileid(11L);
    Address address = new Address();
    address.setAddressid(11L);
    List<MasterCodeTable> masterCodeTablesClone = new ArrayList<MasterCodeTable>(masterCodeTables);
    masterCodeTablesClone.remove(1);
    when(masterCodeTableService.findByCategory(anyString())).thenReturn(masterCodeTablesClone);
    when(patronProfileService.getSequenceAccNo()).thenReturn(155L);
    when(patronProfileService.insertPatronProfile(any(PatronProfile.class)))
        .thenReturn(insertedPatronProfile);
    when(countryService.fillAllCountry()).thenReturn(coutries);
    when(addressService.save(any(Address.class))).thenReturn(address);
    when(mctUtil.getMasterCodeTable(anyString(), anyListOf(MasterCodeTable.class),
        anyListOf(MasterCodeTable.class))).thenReturn(genderTitleMct);
    //
    PatronRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/createPatronRequest.json"),
            PatronRequest.class);
    patronService.createPatronProfile(request, 88L);
    Assert.fail();
  }

  @Test
  public void isPatronLock() {
    Integer result = 1;
    when(patronRepo.isPatronLock(anyString())).thenReturn(result);
    Object actual = patronService.isPatronLock("dbthan@gmail.com");
    assertEquals(result, Integer.valueOf(actual.toString()));
  }

  @Test
  public void isPatronLockCaseError() {
    when(patronRepo.isPatronLock(anyString())).thenThrow(new NoResultException());
    Object actual = patronService.isPatronLock("dbthan@gmail.com");
    assertNull(actual);
  }

  @Test
  public void getPatronSolicitationOfTenant() {
    /*List<Object[]> objects = new ArrayList<>();
    Object[] object = new Object[5];
    object[2] = "National Gallery Singapore";
    object[3] = "http://www.nationalgallery.sg/";
    object[0] = "Promoter";
    object[1] = 1913;
    object[4] = false;
    objects.add(object);
    when(patronRepo.getPatronSolicitation(anyLong(), anyLong())).thenReturn(objects);
    List<PatronSolicitationJson> patronSolicitations = new ArrayList<>();
    patronSolicitations.add(new PatronSolicitationJson("Promoter", 1913L,
        "National Gallery Singapore", "http://www.nationalgallery.sg/", false));
    List<PatronSolicitationJson> actual = patronService.getPatronSolicitationOfSistic(88L);
    assertEquals(patronSolicitations, actual);*/
  }

  @Test
  public void getPatronSolicitationOfTenantCaseNull() {
    /*when(patronRepo.getPatronSolicitation(anyLong(), anyLong())).thenReturn(null);
    List<PatronSolicitationJson> patronSolicitations = new ArrayList<>();
    List<PatronSolicitationJson> actual = patronService.getPatronSolicitationOfSistic(88L);
    assertEquals(patronSolicitations, actual);*/
  }

  @Test
  public void removeDuplicateSolicitation() {
    /*
     * List<PatronSolicitationJson> expected = new ArrayList<>(); PatronSolicitationJson json1 = new
     * PatronSolicitationJson("Tenant", 99L, "SISTIC", "http://sistic.com.sg", true);
     * PatronSolicitationJson json2 = new PatronSolicitationJson("Tenant", 98L, "SISTIC",
     * "http://sistic.com.sg", false); PatronSolicitationJson json3 = new
     * PatronSolicitationJson("Promoter", 100L, "Promoter", "http://sistic.com.sg", false);
     * PatronSolicitationJson json4 = new PatronSolicitationJson("Venue", 100L, "Venue",
     * "http://sistic.com.sg", true); expected.add(json1); expected.add(json2); expected.add(json3);
     * expected.add(json4); List<PatronSolicitationJson> actual =
     * patronService.removeDuplicateSolicitation(patronSolicitationJsonList); assertEquals(expected,
     * actual);
     */
  }
}



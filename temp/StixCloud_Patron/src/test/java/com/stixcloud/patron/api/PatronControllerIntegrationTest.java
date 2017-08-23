package com.stixcloud.patron.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyMapOf;
import static org.mockito.Matchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.exception.JsonResponse;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.domain.Address;
import com.stixcloud.domain.PatronAddress;
import com.stixcloud.domain.PatronAdvanceProfile;
import com.stixcloud.domain.PatronPhone;
import com.stixcloud.domain.PatronSolicitation;
import com.stixcloud.domain.PatronInternetAccount;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.repo.IAddressRepository;
import com.stixcloud.patron.repo.IPatronAddressRepository;
import com.stixcloud.patron.repo.IPatronAdvanceProfileRepository;
import com.stixcloud.patron.repo.IPatronInternetAccountRepository;
import com.stixcloud.patron.repo.IPatronPhoneRepository;
import com.stixcloud.patron.repo.IPatronProfileRepository;
import com.stixcloud.patron.repo.IPatronSolicitationRepository;
import com.stixcloud.patron.service.IEmailService;
import com.stixcloud.patron.service.PatronProfileJsonService;

import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.RedisServerBuilder;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatronControllerIntegrationTest {

  private static final Logger LOGGER = LogManager.getLogger(PatronControllerIntegrationTest.class);
  private static final String TEST_TOKEN =
      "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXRyb25JRCI6IjE5MjE4MjgiLCJ1c2VyX25hbWUiOiJlc3BsdGVzdGluZ0B5YWhvby5jb20iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiLCJmb28iLCJiYXIiLCJwcm9kdWN0IiwiY2FydCIsInBheW1lbnQiXSwiZXhwIjoxNDgzMDA3OTY5LCJhdXRob3JpdGllcyI6WyJQQVRST04iXSwianRpIjoiYmM2ODg4YTgtNzEyOC00NWQ5LTk4MWUtNTIzMjZjNDE5ZmRhIiwiY2xpZW50X2lkIjoic2lzdGljIn0.6Tcth1HglUZcSwVmRQTwjpAiwIf4-GttTDKyOrXz99I";
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TestRestTemplate restTemplate;
  @Autowired
  private PatronProfileJsonService patronJsonService;
  @Autowired
  private IAddressRepository addressRepo;
  @Autowired
  private IPatronProfileRepository patronProfileRepo;
  @Autowired
  private IPatronAdvanceProfileRepository patronAdvRepo;
  @Autowired
  private IPatronAddressRepository patronAdressRepo;
  @Autowired
  private IPatronPhoneRepository patronPhoneRepo;
  @Autowired
  private IPatronInternetAccountRepository patronAccountRepo;
  @Autowired
  private IPatronSolicitationRepository patronSolicitationRepo;

  private JacksonTester<PatronProfileJson> patronProfileJacksonTester;
  private PatronProfileJson patronJson;

  private MockMvc mockMvc;
  private static RedisServer redisServer;
  @Value("${spring.mvc.locale}")
  private Locale locale;

  @Autowired
  private PatronController patronController;

  @Mock
  IEmailService emailService;

  @BeforeClass
  public static void setUp() throws Exception {
    RedisExecProvider customProvider = RedisExecProvider.defaultProvider()
        .override(OS.WINDOWS, Architecture.x86, "redis/redis-server-3.2.1.exe")
        .override(OS.WINDOWS, Architecture.x86_64, "redis/redis-server-3.2.1.exe");
    redisServer = new RedisServerBuilder().redisExecProvider(customProvider).build();
    redisServer.start();
  }

  @AfterClass
  public static void tearDown() {
    redisServer.stop();
  }

  @Before
  public void before() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    TenantPropertiesTest.setUp();

    JacksonTester.initFields(this, objectMapper);
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    objectMapper.setDateFormat(sdf);
    setUpRedisData();
  }

  private void setUpRedisData() throws Exception {
    List<ContactJson> contactNos;
    IdentificationJson identification;
    List<AddressJson> addresses;
    CountryPhoneCodeJson countryResponse;
    countryResponse = new CountryPhoneCodeJson("VN", 84);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact = new ContactJson("HOME", countryResponse, "19999999");
    ContactJson contact1 = new ContactJson("MOBILE", countryResponse, "16666666");
    contactNos.add(contact);
    contactNos.add(contact1);
    identification = new IdentificationJson("Passport", "6966969");
    addresses = new ArrayList<AddressJson>();
    AddressJson address =
        new AddressJson("Billing Address", "VN", "Duy Tan Billing", "100", "CMC 100", "88888");
    addresses.add(address);
    AddressJson address1 =
        new AddressJson("Mailing Address", "VN", "Duy Tan Mailing", "100", "CMC 100", "88888");
    addresses.add(address);
    addresses.add(address1);
    patronJson = new PatronProfileJson("dbthan@cmc.com.vn", 5555L, "MR", "Than", "Do", contactNos,
        1987, "VN", "VN", identification, true, addresses);
    patronJsonService.savePatronProfileJson(patronJson);
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql"})
  public void checkEmailCaseEmailIsAvailable() {
    JsonResponse expected = new JsonResponse("200", "Email is available.");
    long startTime = System.nanoTime();
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/registration/availability/dbthan@gmail.com", JsonResponse.class);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);

  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql"})
  public void checkEmailCaseEmailIsNotAvailable() {
    JsonResponse expected = new JsonResponse("400", "Email is already existed.");
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/registration/availability/dbthan@cmc.com.vn", JsonResponse.class);
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);

  }

  @Test
  @Sql({"/patron/sql/patronProfileViewData.sql"})
  public void getPatronProfile() throws Exception {
    ResponseEntity<PatronProfileJson> entity = restTemplate
        .getForEntity("/SISTIC/patrons/account/1921828/profile", PatronProfileJson.class);
    PatronProfileJson actual = entity.getBody();
    assertThat(patronProfileJacksonTester.write(actual))
        .isEqualToJson("/patron/json/viewPatronProfile.json");
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseAllNormal() throws Exception {
    JsonResponse response = updatePatron("/patron/json/patronUpdateAllRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron successful.");
    assertEquals(expected, response);
    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronProfileUpdate.json"), PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAdvanceProfileUpdate.json"),
        PatronAdvanceProfile.class);
    Address address = objectMapper
        .readValue(this.getClass().getResource("/patron/json/addressUpdate.json"), Address.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneUpdate.json"), PatronPhone.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountUpdate.json"),
        PatronInternetAccount.class);
    Address actualAddress = addressRepo.findOne(14420534L);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1921828L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1663743L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(3135703L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1369170L);
    address.setUpdateddate(actualAddress.getUpdateddate());
    address.setCreateddate(actualAddress.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertEquals(address, actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronAdv, actualPatronAdv);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronAccount, actualPatronAccout);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseMissingPhoneNumber() throws Exception {
    JsonResponse response =
        updatePatron("/patron/json/patronUpdateCaseMissingPhoneNumberRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron successful.");
    assertEquals(expected, response);
    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronProfileUpdate.json"), PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAdvanceProfileUpdate.json"),
        PatronAdvanceProfile.class);
    Address address = objectMapper
        .readValue(this.getClass().getResource("/patron/json/addressUpdate.json"), Address.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneUpdate.json"), PatronPhone.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountUpdate.json"),
        PatronInternetAccount.class);
    Address actualAddress = addressRepo.findOne(14420534L);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1921828L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1663743L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(3135703L);
    PatronPhone actualPatronPhone2 = patronPhoneRepo.findOne(3135704L);
    PatronPhone actualPatronPhone3 = patronPhoneRepo.findOne(1L);
    PatronPhone patronPhone2 = new PatronPhone(3135704l, false, 1921828L, "HOME", 49l, null, " ");
    PatronPhone patronPhone3 = new PatronPhone(1l, false, 1921828L, "OFFICE", 49l, null, " ");
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1369170L);
    address.setUpdateddate(actualAddress.getUpdateddate());
    address.setCreateddate(actualAddress.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertEquals(address, actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronAdv, actualPatronAdv);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronPhone2, actualPatronPhone2);
    assertEquals(patronPhone3, actualPatronPhone3);
    assertEquals(patronAccount, actualPatronAccout);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseMissingMobilePhoneNumber() throws Exception {
    JsonResponse response =
        updatePatronError("/patron/json/patronUpdateCaseMissingMobilePhoneNumberRequest.json");
    JsonResponse expected = new JsonResponse("400",
        "You must have mobile contact in you profile. Please fill your mobile contact information.");
    assertEquals(expected, response);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseMissingOptionalField() throws Exception {
    JsonResponse response =
        updatePatron("/patron/json/patronUpdateCaseMissingOptionalFieldRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron successful.");
    assertEquals(expected, response);
    PatronProfile patronProfile =
        objectMapper.readValue(
            this.getClass()
                .getResource("/patron/json/patronProfileUpdateCaseMissingOptionalField.json"),
            PatronProfile.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneUpdate.json"), PatronPhone.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountUpdate.json"),
        PatronInternetAccount.class);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1921828L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(3135703L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1369170L);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronAccount, actualPatronAccout);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseMissingRequiredField() throws Exception {
    JsonResponse response =
        updatePatronError("/patron/json/patronUpdateCaseMissingRequiredFieldRequest.json");
    JsonResponse expected = new JsonResponse("400",
        "You must have first name information. Please fill your first name.");
    assertEquals(expected, response);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql",
      "/patron/sql/patronUpdateExtraData.sql"})
  public void updatePatronProfileCaseAddNewAddressAndRemoveContact() throws Exception {
    JsonResponse response =
        updatePatron("/patron/json/patronUpdateCaseAddAddressRemoveContactRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron successful.");
    assertEquals(expected, response);
    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronProfileUpdate.json"), PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAdvanceProfileUpdate.json"),
        PatronAdvanceProfile.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneUpdate.json"), PatronPhone.class);
    Address address = objectMapper.readValue(
        this.getClass().getResource("/patron/json/addressUpdateAddNewAddress.json"), Address.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountUpdate.json"),
        PatronInternetAccount.class);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1921828L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(3135703L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1369170L);
    Address actualAddress = addressRepo.findOne(1L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1663743L);
    address.setUpdateddate(actualAddress.getUpdateddate());
    address.setCreateddate(actualAddress.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertEquals(address, actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronAccount, actualPatronAccout);

    assertEquals(patronAdv, actualPatronAdv);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronUpdateAllNormal.sql"})
  public void updatePatronProfileCaseWrongCurrentPassword() throws Exception {
    JsonResponse response =
        updatePatronError("/patron/json/patronUpdateCaseWrongCurrentPasswordRequest.json");
    JsonResponse expected = new JsonResponse("400",
        "Can't update patron password. Maybe current password is not correct.");
    assertEquals(expected, response);
  }

  @Test
  public void insertPatronCaseMissingMandatoryField() throws Exception {
    JsonResponse expected = new JsonResponse("400",
        "You must have first name information. Please fill your first name.");
    JsonResponse actual =
        createPatronError("/patron/json/createPatronRequestCaseMissMandatoryField.json");

    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql"})
  public void insertPatronCaseCannotGetIdentityType() throws Exception {
    JsonResponse expected =
        new JsonResponse("400", "Your identification is invalid. Please correct it.");
    JsonResponse actual =
        createPatronError("/patron/json/createPatronRequestInvalidIdentityType.json");
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/emailTemplateData.sql"})
  public void insertPatronCaseNormal() throws Exception {
    PatronRequest request =
        objectMapper.readValue(this.getClass().getResource("/patron/json/createPatronRequest.json"),
            PatronRequest.class);

    mockMvc
        .perform(post("/SISTIC/patrons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(request)))
        .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("200")))
        .andExpect(jsonPath("$.statusMessage", is("Create new patron successful.")));

    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronProfileInsert.json"), PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAdvanceProfileInsert.json"),
        PatronAdvanceProfile.class);
    PatronAddress patronAddress = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAddressInsert.json"), PatronAddress.class);
    Address address = objectMapper
        .readValue(this.getClass().getResource("/patron/json/addressInsert.json"), Address.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneInsert.json"), PatronPhone.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountInsert.json"),
        PatronInternetAccount.class);
    PatronSolicitation patronSolicitation = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronSolicitationInsert.json"),
        PatronSolicitation.class);
    Address actualAddress = addressRepo.findOne(1L);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1L);
    PatronAddress actualPatronAddress = patronAdressRepo.findOne(2L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(1L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1L);
    PatronSolicitation actualPatronSolicitation = patronSolicitationRepo.findOne(1L);
    address.setCreateddate(actualAddress.getCreateddate());
    address.setUpdateddate(actualAddress.getUpdateddate());
    patronProfile.setCreateddate(actualPatronProfile.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronSolicitation.setUpdateddate(actualPatronSolicitation.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertEquals(address, actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronAdv, actualPatronAdv);
    assertEquals(patronAddress, actualPatronAddress);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronAccount, actualPatronAccout);
    assertEquals(patronSolicitation, actualPatronSolicitation);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/emailTemplateData.sql"})
  public void insertPatronCaseMissingPhoneNumber() throws Exception {
    PatronRequest request = objectMapper.readValue(
        this.getClass().getResource("/patron/json/createPatronRequestCaseMissingPhoneNumber.json"),
        PatronRequest.class);

    mockMvc
        .perform(post("/SISTIC/patrons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(request)))
        .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("200")))
        .andExpect(jsonPath("$.statusMessage", is("Create new patron successful.")));

    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronProfileInsert.json"), PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronAdvanceProfileInsert.json"),
        PatronAdvanceProfile.class);
    Address address = objectMapper
        .readValue(this.getClass().getResource("/patron/json/addressInsert.json"), Address.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneInsert.json"), PatronPhone.class);
    PatronPhone patronPhone2 = new PatronPhone(2l, false, 1L, "HOME", 49l, null, " ");
    PatronPhone patronPhone3 = new PatronPhone(3l, false, 1L, "OFFICE", 49l, null, " ");
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountInsert.json"),
        PatronInternetAccount.class);
    PatronSolicitation patronSolicitation = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronSolicitationInsert.json"),
        PatronSolicitation.class);
    Address actualAddress = addressRepo.findOne(1L);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(1L);
    PatronPhone actualPatronPhone2 = patronPhoneRepo.findOne(2L);
    PatronPhone actualPatronPhone3 = patronPhoneRepo.findOne(3L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1L);
    PatronSolicitation actualPatronSolicitation = patronSolicitationRepo.findOne(1L);
    address.setCreateddate(actualAddress.getCreateddate());
    address.setUpdateddate(actualAddress.getUpdateddate());
    patronProfile.setCreateddate(actualPatronProfile.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronSolicitation.setUpdateddate(actualPatronSolicitation.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertEquals(address, actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronAdv, actualPatronAdv);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronPhone2, actualPatronPhone2);
    assertEquals(patronPhone3, actualPatronPhone3);
    assertEquals(patronAccount, actualPatronAccout);
    assertEquals(patronSolicitation, actualPatronSolicitation);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/emailTemplateData.sql"})
  public void insertPatronCaseMissingMobilePhoneNumber() throws Exception {
    PatronRequest request = objectMapper.readValue(
        this.getClass()
            .getResource("/patron/json/createPatronRequestCaseMissingMobilePhoneNumber.json"),
        PatronRequest.class);

    mockMvc
        .perform(post("/SISTIC/patrons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(request)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("400"))).andExpect(jsonPath("$.statusMessage", is(
            "You must have mobile contact in you profile. Please fill your mobile contact information.")));

  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/emailTemplateData.sql"})
  public void insertPatronCaseMissOptionalField() throws Exception {
    Mockito.doNothing().when(emailService).sendEmail(anyString(), anyString(), anyString(),
        anyString(), (HashMap<String, byte[]>) anyMapOf(String.class, byte[].class));
    PatronRequest request = objectMapper.readValue(
        this.getClass().getResource("/patron/json/createPatronRequestMissOptionalField.json"),
        PatronRequest.class);

    mockMvc
        .perform(post("/SISTIC/patrons")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(request)))
        .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("200")))
        .andExpect(jsonPath("$.statusMessage", is("Create new patron successful.")));

    PatronProfile patronProfile = objectMapper.readValue(
        this.getClass().getResource("/patron/json//patronProfileInsertMissingOptionalField.json"),
        PatronProfile.class);
    PatronAdvanceProfile patronAdv = objectMapper.readValue(
        this.getClass()
            .getResource("/patron/json/patronAdvanceProfileInsertMissingOptionalField.json"),
        PatronAdvanceProfile.class);
    PatronPhone patronPhone = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronPhoneInsert.json"), PatronPhone.class);
    PatronInternetAccount patronAccount = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronInternetAccountInsert.json"),
        PatronInternetAccount.class);
    PatronSolicitation patronSolicitation = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronSolicitationInsert.json"),
        PatronSolicitation.class);
    Address actualAddress = addressRepo.findOne(1L);
    PatronProfile actualPatronProfile = patronProfileRepo.findOne(1L);
    PatronAdvanceProfile actualPatronAdv = patronAdvRepo.findOne(1L);
    PatronAddress actualPatronAddress = patronAdressRepo.findOne(2L);
    PatronPhone actualPatronPhone = patronPhoneRepo.findOne(1L);
    PatronInternetAccount actualPatronAccout = patronAccountRepo.findOne(1L);
    PatronSolicitation actualPatronSolicitation = patronSolicitationRepo.findOne(1L);
    patronProfile.setCreateddate(actualPatronProfile.getCreateddate());
    patronProfile.setUpdateddate(actualPatronProfile.getUpdateddate());
    patronSolicitation.setUpdateddate(actualPatronSolicitation.getUpdateddate());
    patronAdv.setDob(actualPatronAdv.getDob());
    assertNull(actualAddress);
    assertEquals(patronProfile, actualPatronProfile);
    assertEquals(patronAdv, actualPatronAdv);
    assertNull(actualPatronAddress);
    assertEquals(patronPhone, actualPatronPhone);
    assertEquals(patronAccount, actualPatronAccout);
    assertEquals(patronSolicitation, actualPatronSolicitation);
  }

  @Test
  @Sql({"/patron/sql/patronSolicitationViewData.sql", "/patron/sql/basicData.sql"})
  public void getSolicitation() throws Exception {

    PatronSolicitationResponse response = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronSolicitationResponse.json"),
        PatronSolicitationResponse.class);
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1569068/subscriptions", null,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  @Ignore
  public void getSolicitationCaseHasOnlyTenantData() throws Exception {

    PatronSolicitationResponse response = objectMapper.readValue(
        this.getClass()
            .getResource("/patron/json/patronSolicitationResponseCaseHasOnlyTenantData.json"),
        PatronSolicitationResponse.class);
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1569069/subscriptions", null,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  @Ignore
  public void getSolicitationCaseHasTenantAndPromoterData() throws Exception {

    PatronSolicitationResponse response = objectMapper.readValue(
        this.getClass().getResource(
            "/patron/json/patronSolicitationResponseCaseHasTenantAndPromoterData.json"),
        PatronSolicitationResponse.class);
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1569069/subscriptions", null,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void getSolicitationCaseHasProductId() throws Exception {
    List<Long> productIds = new ArrayList<>();
    productIds.add(3L);
    productIds.add(2L);
    PatronSolicitationResponse response = objectMapper.readValue(
        this.getClass().getResource("/patron/json/patronSolicitationResponseCaseHasProductId.json"),
        PatronSolicitationResponse.class);
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1569068/subscriptions", productIds,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void getSolicitationCaseHasProductIdAndFilter() throws Exception {
    List<Long> productIds = new ArrayList<>();
    productIds.add(16L);
    productIds.add(161L);
    PatronSolicitationResponse response = objectMapper.readValue(
        this.getClass()
            .getResource("/patron/json/patronSolicitationResponseCaseHasProductIdAndFilter.json"),
        PatronSolicitationResponse.class);
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1569068/subscriptions", productIds,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationViewDataCaseError.sql"})
  public void getSolicitationCaseError() throws Exception {

    PatronSolicitationResponse response =
        new PatronSolicitationResponse(new ArrayList<PatronSolicitationJson>());
    PatronSolicitationResponse entity =
        restTemplate.postForEntity("/SISTIC/patrons/account/1469068/subscriptions", null,
            PatronSolicitationResponse.class).getBody();
    assertEquals(response, entity);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void updateSolicitationCaseAllNormal() throws Exception {
    JsonResponse response = updatePatronSolicitation("/patron/json/solicitationUpdateRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron subscriptions successful.");
    PatronSolicitation updateSolicitation = patronSolicitationRepo.findOne(962L);
    PatronSolicitation insertSolicitation = patronSolicitationRepo.findOne(1L);
    PatronSolicitation deleteSolicitation = patronSolicitationRepo.findOne(963L);
    PatronSolicitation updateExpected = new PatronSolicitation(962L, 1569068L, 200L, null, 1L,
        false, updateSolicitation.getUpdateddate(), 59L);
    PatronSolicitation insertExpected = new PatronSolicitation(1L, 1569068L, 202L, null, 2L, true,
        insertSolicitation.getUpdateddate(), 59L);
    assertEquals(expected, response);
    assertEquals(updateExpected, updateSolicitation);
    assertEquals(insertExpected, insertSolicitation);
    assertNull(deleteSolicitation);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void updateSolicitationCaseMissingRequiredField() throws Exception {
    JsonResponse response =
        updatePatronSolicitation("/patron/json/solicitationUpdateErrorRequest.json");
    JsonResponse expected =
        new JsonResponse("400", "All field is require, please fill it and try again.");
    assertEquals(expected, response);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void updateSolicitationCaseInvalidSolicitationStatus() throws Exception {
    JsonResponse response = updatePatronSolicitation(
        "/patron/json/solicitationUpdateCaseInvalidSolicitationStatusRequest.json");
    JsonResponse expected = new JsonResponse("400", "Solicitation status must be true.");
    assertEquals(expected, response);
  }

  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void updateSolicitationCaseInvalidSolicitationType() throws Exception {
    JsonResponse response = updatePatronSolicitation(
        "/patron/json/solicitationUpdateCaseInvalidSolicitationTypeRequest.json");
    JsonResponse expected = new JsonResponse("400", "Solicitation type can not found in database.");
    assertEquals(expected, response);
  }


  @Test
  @Sql({"/patron/sql/basicData.sql", "/patron/sql/patronSolicitationData.sql"})
  public void updateSolicitationCaseRequiredField() throws Exception {
    JsonResponse response = updatePatronSolicitation("/patron/json/solicitationUpdateRequest.json");
    JsonResponse expected = new JsonResponse("200", "Update patron subscriptions successful.");
    assertEquals(expected, response);
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql",
      "/patron/sql/emailTemplateData.sql"})
  public void handleForgotPasswordRequestCaseNormal() throws Exception {
    // ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
    // "/SISTIC/patron/auth/forgotpassword/dbthan@cmc.com.vn", null, JsonResponse.class);

    mockMvc.perform(post("/SISTIC/patrons/forgotpassword/dbthan@cmc.com.vn"))
        .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.httpStatus", is("200")))
        .andExpect(jsonPath("$.statusMessage", is("Request forgot password successful.")));


    // JsonResponse expected = new JsonResponse("200", "Request forgot password successful.");
    // assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void handleForgotPasswordRequestCaseError() throws Exception {
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/forgotpassword/cmc11@gmail.com", null, JsonResponse.class);
    JsonResponse expected = new JsonResponse("400", "Email does not exist.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void validateForgotPasswordTokenCaseNormal() {
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/forgotpassword/availability/dbthan@cmc.com.vn/47d59bfe-e1ab-477d-90c7-6db409394b2c",
        JsonResponse.class);
    JsonResponse expected = new JsonResponse("200", "Forgot password token is available.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void validateForgotPasswordTokenCaseWrongToken() {
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/forgotpassword/availability/dbthan@cmc.com.vn/47d59bfe-e12ab-477d-90c7-6db409394b2c",
        JsonResponse.class);
    JsonResponse expected =
        new JsonResponse("400", "Forgot password token is invalid or already expired.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void validateForgotPasswordTokenCaseTokenExpired() {
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/forgotpassword/availability/cmc1@gmail.com/bbfb991b-2166-4690-aaa9-90f24c2f327a",
        JsonResponse.class);
    JsonResponse expected =
        new JsonResponse("400", "Forgot password token is invalid or already expired.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void validateForgotPasswordTokenCaseStatusIsZero() {
    ResponseEntity<JsonResponse> entity = restTemplate.getForEntity(
        "/SISTIC/patrons/forgotpassword/availability/fionaong@live.com/114ecb3e-c1a9-4cbb-b7a3-ec1f72b9745b",
        JsonResponse.class);
    JsonResponse expected =
        new JsonResponse("400", "Forgot password token is invalid or already expired.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void updatePasswordCaseNormal() {
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/changepassword/dbthan@cmc.com.vn/47d59bfe-e1ab-477d-90c7-6db409394b2c?newPassword=12345678",
        null, JsonResponse.class);
    JsonResponse expected = new JsonResponse("200", "Reset password successful.");
    assertEquals(expected, entity.getBody());
    PatronInternetAccount actual = patronAccountRepo.findByEmailAddress("dbthan@cmc.com.vn");
    assertEquals("12345678", actual.getPassword());
    assertEquals(new Integer(0), actual.getLoginfailedcount());
  }

  @Test
  @Sql({"/patron/sql/forgotPasswordData.sql", "/patron/sql/patronInternetAccountData.sql"})
  public void updatePasswordCaseError() {
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/changepassword/dbthan@cmc.com.vn/47d59bfe-e12ab-477d-90c7-6db409394b2c?newPassword=12345678",
        null, JsonResponse.class);
    JsonResponse expected =
        new JsonResponse("400", "Reset password token is invalid or already expired.");
    assertEquals(expected, entity.getBody());
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void getCountryListResponseCaseNormal() throws Exception {
    CountryListResponse expectedCountryListResponse =
        objectMapper.readValue(this.getClass().getResource("/country/json/contryListResponse.json"),
            CountryListResponse.class);

    ResponseEntity<CountryListResponse> entityActual =
        restTemplate.getForEntity("/SISTIC/patrons/countries", CountryListResponse.class);

    assertEquals(expectedCountryListResponse, entityActual.getBody());
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void getCountryListResponseCaseNormalVN() throws Exception {
    CountryListResponse expectedCountryListResponse = objectMapper.readValue(
        this.getClass().getResource("/country/json/contryListResponseVN.json"),
        CountryListResponse.class);

    ResponseEntity<CountryListResponse> entityActual = restTemplate
        .getForEntity("/SISTIC/patrons/countries?lang=vi", CountryListResponse.class);

    assertEquals(expectedCountryListResponse, entityActual.getBody());
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueNoData.sql"})
  public void getCountryListResponseCaseCountryListResponseNoResult() {

    JsonResponse expected = new JsonResponse("400", "No data found.");
    ResponseEntity<JsonResponse> entity =
        restTemplate.getForEntity("/SISTIC/patrons/countries", JsonResponse.class);
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/country/sql/CountryFieldValueData.sql"})
  public void getCountryListResponseCaseLangCodeNotCorrect() throws Exception {
    CountryListResponse expectedCountryListResponse =
        objectMapper.readValue(this.getClass().getResource("/country/json/contryListResponse.json"),
            CountryListResponse.class);

    ResponseEntity<CountryListResponse> entityActual = restTemplate
        .getForEntity("/SISTIC/patrons/countries?lang=zz", CountryListResponse.class);

    assertEquals(expectedCountryListResponse, entityActual.getBody());
  }

  private JsonResponse updatePatronSolicitation(String jsonRequestPath) throws Exception {
    String uri = "/SISTIC/patrons/account/1569068/subscriptions/update";
    PatronSolicitationUpdateRequest request = objectMapper.readValue(
        this.getClass().getResource(jsonRequestPath), PatronSolicitationUpdateRequest.class);
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<PatronSolicitationUpdateRequest> httpEntity = new HttpEntity<>(request, httpHeaders);
    ResponseEntity<JsonResponse> entity =
        restTemplate.exchange(uri, HttpMethod.POST, httpEntity, JsonResponse.class);
    return entity.getBody();

  }

  private JsonResponse updatePatron(String jsonRequestPath) throws Exception {
    String uri = "/SISTIC/patrons/account/1921828/profile";

    PatronUpdateRequest request = objectMapper
        .readValue(this.getClass().getResource(jsonRequestPath), PatronUpdateRequest.class);

    HttpEntity<PatronUpdateRequest> patronUpdateRequestHttpEntity =
        getHttpEntityForPatronUpdateRequest(request);

    ResponseEntity<JsonResponse> entity = restTemplate.exchange(uri, HttpMethod.POST,
        patronUpdateRequestHttpEntity, JsonResponse.class);
    return entity.getBody();
  }

  private JsonResponse createPatronError(String jsonRequestPath) throws Exception {
    String uri = "/SISTIC/patrons";

    PatronRequest request = objectMapper
        .readValue(this.getClass().getResource(jsonRequestPath), PatronRequest.class);

    HttpEntity<PatronRequest> patronUpdateRequestHttpEntity =
        getHttpEntityForPatronCreationRequest(request);

    ResponseEntity<JsonResponse> entity = restTemplate.exchange(uri, HttpMethod.POST,
        patronUpdateRequestHttpEntity, JsonResponse.class);
    return entity.getBody();
  }

  private JsonResponse updatePatronError(String jsonRequestPath) throws Exception {
    String uri = "/SISTIC/patrons/account/1921828/profile";

    PatronUpdateRequest request = objectMapper
        .readValue(this.getClass().getResource(jsonRequestPath), PatronUpdateRequest.class);

    HttpEntity<PatronUpdateRequest> patronUpdateRequestHttpEntity =
        getHttpEntityForPatronUpdateRequest(request);

    ResponseEntity<JsonResponse> entity = restTemplate.exchange(uri, HttpMethod.POST,
        patronUpdateRequestHttpEntity, JsonResponse.class);
    return entity.getBody();
  }

  @Test
  @Sql({"/patron/sql/transactionData.sql", "/patron/sql/transactionCodeData.sql",
      "/patron/sql/masterCodeTableData.sql", "/patron/sql/transactionReferenceData.sql"})
  public void getTransactionHistory() throws Exception {
    mockMvc.perform(get("/SISTIC/patrons/account/1922192/transactions/1/5").locale(locale))
        .andExpect(status().isOk()).andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.totalPage", is(4))).andExpect(jsonPath("$.currentPage", is(1)))
        .andExpect(jsonPath("$.totalRow", is(16)))
        .andExpect(jsonPath("$.transactions.length()", is(5)));
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql"})
  public void updatePatronLoginFailedCount() throws Exception {
    JsonResponse expected = new JsonResponse("200", "Update patron lock failed count success.");
    long startTime = System.nanoTime();
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/accountstatus?email=dbthan@cmc.com.vn&loginSuccess=true", null,
        JsonResponse.class);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql"})
  public void updatePatronLoginFailedCountEmailNotExisted() throws Exception {
    JsonResponse expected = new JsonResponse("400", "Email does not exist.");
    long startTime = System.nanoTime();
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/accountstatus?email=dbtha1n@cmc.com.vn&loginSuccess=false", null,
        JsonResponse.class);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql"})
  public void updatePatronLoginFailedCountEmailLoginSuccessNull() throws SisticApiException {
    JsonResponse expected = new JsonResponse("400", "Invalid loginSuccess value.");
    long startTime = System.nanoTime();
    ResponseEntity<JsonResponse> entity = restTemplate.postForEntity(
        "/SISTIC/patrons/accountstatus?email=dbtha1n@cmc.com.vn&loginSuccess=", null,
        JsonResponse.class);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    JsonResponse actual = entity.getBody();
    assertEquals(expected, actual);
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql", "/patron/sql/forgotPasswordData.sql"})
  public void isPatronLockCaseNotExistEmail() throws Exception {
    mockMvc.perform(get("/SISTIC/patrons/accountstatus?email=dbtha444n@cmc.com.vn"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql", "/patron/sql/forgotPasswordData.sql"})
  public void isPatronLockCaseAccountLock() throws Exception {
    mockMvc.perform(get("/SISTIC/patrons/accountstatus?email=dbthancmc@gmail.com"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("true")).andDo(print());
  }

  @Test
  @Sql({"/patron/sql/patronInternetAccountData.sql", "/patron/sql/forgotPasswordData.sql"})
  public void isPatronLockCaseAccountNormal() throws Exception {
    mockMvc.perform(get("/SISTIC/patrons/accountstatus?email=dbthan@cmc.com.vn"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("false")).andDo(print());
  }

  private HttpEntity<PatronUpdateRequest> getHttpEntityForPatronUpdateRequest(
      PatronUpdateRequest request) {
    HttpHeaders httpHeaders = getHttpHeader();
    return new HttpEntity<>(request, httpHeaders);
  }

  private HttpEntity<PatronRequest> getHttpEntityForPatronCreationRequest(
      PatronRequest request) {
    HttpHeaders httpHeaders = getHttpHeader();
    return new HttpEntity<>(request, httpHeaders);
  }
  
  private HttpHeaders getHttpHeader(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.set(HttpHeaders.AUTHORIZATION, TEST_TOKEN);
    return httpHeaders;
  }
}

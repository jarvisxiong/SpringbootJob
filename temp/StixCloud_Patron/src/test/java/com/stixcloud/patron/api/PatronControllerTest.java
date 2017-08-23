package com.stixcloud.patron.api;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.common.exception.SisticApiException;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.api.json.PatronSolicitationJson;
import com.stixcloud.patron.domain.PatronProfileView;
import com.stixcloud.patron.domain.PatronSolicitationView;
import com.stixcloud.patron.service.ICountryService;
import com.stixcloud.patron.service.IForgotPasswordService;
import com.stixcloud.patron.service.IPatronService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PatronControllerTest {

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockMvc mvc;
  @Autowired
  private PatronController patronControllerTest;
  @MockBean
  private IPatronService patronService;
  @MockBean
  private IForgotPasswordService forgotPasswordService;

  private List<PatronProfileView> patronViewList;
  private List<PatronSolicitationView> patronSolicitationViewList;
  private List<PatronSolicitationJson> patronSolicitationJsonList;
  private static final String TEST_TOKEN =
	      "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXRyb25JRCI6IjE5MjE4MjgiLCJ1c2VyX25hbWUiOiJlc3BsdGVzdGluZ0B5YWhvby5jb20iLCJzY29wZSI6WyJyZWFkIiwid3JpdGUiLCJmb28iLCJiYXIiLCJwcm9kdWN0IiwiY2FydCIsInBheW1lbnQiXSwiZXhwIjoxNDgzMDA3OTY5LCJhdXRob3JpdGllcyI6WyJQQVRST04iXSwianRpIjoiYmM2ODg4YTgtNzEyOC00NWQ5LTk4MWUtNTIzMjZjNDE5ZmRhIiwiY2xpZW50X2lkIjoic2lzdGljIn0.6Tcth1HglUZcSwVmRQTwjpAiwIf4-GttTDKyOrXz99I";
	  
  @Mock
  private ICountryService countryService;

  @Before
  public void setup() { 
	this.mvc = MockMvcBuilders.standaloneSetup(patronControllerTest).build();
	patronViewList = new ArrayList<PatronProfileView>();
    patronSolicitationViewList = new ArrayList<>();
    PatronProfileView patron1 = new PatronProfileView("MR", "Than", "Do",
        "patronsynctstcloud01@test.com", 1995, "MOBILE", "126734", 65, "SG", "MM", "MM", "Passport",
        "C323984W", "Mailing Address", "123", "321", "123", "321", "MC", null, 11114L, 1920777L);
    PatronProfileView patron2 = new PatronProfileView("MR", "Than", "Do",
        "patronsynctstcloud01@test.com", 1995, "MOBILE", "126734", 65, "SG", "MM", "MM", "Passport",
        "C323984W", "Billing Address", "123", "321", "123", "321", "MC", null, 11114L, 1920777L);
    patronViewList.add(patron1);
    patronViewList.add(patron2);

    setupGetPatronSolicitation();
  }

  @Ignore
  private void setupGetPatronSolicitation() {
    /*
     * PatronSolicitationView solicitation = new PatronSolicitationView("Tenant", 152552L, "SISTIC",
     * "http://abcd.com", true, 252545L); PatronSolicitationView solicitation2 = new
     * PatronSolicitationView("Promoter", 152552L, "Esplanade", "http://ghijk.com", false, 252545L);
     * patronSolicitationViewList.add(solicitation); patronSolicitationViewList.add(solicitation2);
     * when(patronService.getPatronSolicitationView(anyLong())).thenReturn(
     * patronSolicitationViewList); List<PatronSolicitationJson> solicitationJson = new
     * ArrayList<>(); PatronSolicitationJson json1 = new PatronSolicitationJson("Tenant", 152552L,
     * "SISTIC", "http://abcd.com", true); PatronSolicitationJson json2 = new
     * PatronSolicitationJson("Promoter", 152552L, "Esplanade", "http://ghijk.com", false);
     * solicitationJson.add(json1); solicitationJson.add(json2); patronSolicitationJsonList = new
     * ArrayList<>(); PatronSolicitationJson solicitationJson1 = new
     * PatronSolicitationJson("Tenant", 152552L, "SISTIC", "http://abcd.com", true);
     * PatronSolicitationJson solicitationJson2 = new PatronSolicitationJson("Promoter", 152552L,
     * "Esplanade", "http://ghijk.com", false); patronSolicitationJsonList.add(solicitationJson1);
     * patronSolicitationJsonList.add(solicitationJson2);
     * when(patronService.convertSolicitationToResponse(anyListOf(PatronSolicitationView.class)))
     * .thenReturn(solicitationJson); when(patronService.retrievePatronSolicitation(anyLong(),
     * anyListOf(Long.class))) .thenReturn(patronSolicitationJsonList);
     * when(patronService.removeDuplicateSolicitation(anyListOf(PatronSolicitationJson.class)))
     * .thenReturn(patronSolicitationJsonList);
     */
  }

  @Test
  public void getPatronProfile() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/viewPatronProfile.json").toURI())));
    PatronProfileJson patronProfile =
        objectMapper.readValue(this.getClass().getResource("/patron/json/viewPatronProfile.json"),
            new TypeReference<PatronProfileJson>() {});
    given(patronService.getPatronProfile(anyLong())).willReturn(patronViewList);
    given(patronService.convertToResponse(anyListOf(PatronProfileView.class)))
        .willReturn(patronProfile);
    this.mvc.perform(get("/SISTIC/patrons/account/52514/profile").header("Authorization", TEST_TOKEN)).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void checkEmailCaseAvailable() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths
        .get(this.getClass().getResource("/patron/json/newPatronNotExistEmail.json").toURI())));

    given(patronService.checkExistEmailAddress(anyString())).willReturn(false);
    this.mvc.perform(get("/SISTIC/patrons/registration/availability/dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void checkEmailCaseNotAvailable() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/newPatronExistEmail.json").toURI())));

    given(patronService.checkExistEmailAddress(anyString())).willReturn(true);
    this.mvc.perform(get("/SISTIC/patrons/registration/availability/dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(jsonContent)).andDo(print());
  }

  @Test
  public void createNewPatronCaseMissingOrganization() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/createPatronRequest.json").toURI())));
    this.mvc
        .perform(post("/SISTIC/patrons")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest()).andDo(print());
  }

  @Test
  public void createNewPatron() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/createPatronRequest.json").toURI())));
    String expected = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/createPatronSuccess.json").toURI())));
    doNothing().when(patronService).createPatronProfile(any(PatronRequest.class),
        anyLong());
    this.mvc
        .perform(post("/SISTIC/patrons").content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void createNewPatronExist() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/createPatronRequest.json").toURI())));
    String expected = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/createNewPatronExist.json").toURI())));
    when(patronService.checkExistEmailAddress(anyString())).thenReturn(true);
    this.mvc
        .perform(post("/SISTIC/patrons").content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void updatePatronProfile() throws Exception {
    String jsonContent = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/updatePatronRequest.json").toURI())));
    String expected = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/updatePatronSuccess.json").toURI())));
    doNothing().when(patronService).updatePatronProfile(any(PatronUpdateRequest.class), any() ,anyLong(), anyLong());
    this.mvc
        .perform(post("/SISTIC/patrons/account/52545/profile").content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void getSolicitation() throws Exception {
    String expected = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/getPatronSolicitationSuccess.json").toURI())));
    this.mvc
        .perform(
            post("/SISTIC/patrons/account/56525/subscriptions").accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void getSolicitationCaseHasProductId() throws Exception {
    String expected = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/getPatronSolicitationSuccess.json").toURI())));
    this.mvc
        .perform(
            post("/SISTIC/patrons/account/56525/subscriptions").accept(MediaType.APPLICATION_JSON)
                .content("[155,555]").contentType(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void updateSolicitationCaseNormal() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths
        .get(this.getClass().getResource("/patron/json/solicitationUpdateRequest.json").toURI())));
    String expected = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/updatePatronSolicitationSuccess.json").toURI())));
    doNothing().when(patronService)
        .updatePatronSolicitation(any(PatronSolicitationUpdateRequest.class), anyLong());
    this.mvc
        .perform(post("/SISTIC/patrons/account/52654/subscriptions/update").content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void updateSolicitationCaseError() throws Exception {
    String jsonContent = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/solicitationUpdateErrorRequest.json").toURI())));
    String expected = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/updatePatronSolicitationError.json").toURI())));
    doThrow(new SisticApiException(null,"All field is require, please fill it and try again"))
        .when(patronService)
        .updatePatronSolicitation(any(PatronSolicitationUpdateRequest.class), anyLong());
    this.mvc
        .perform(post("/SISTIC/patrons/account/6525/subscriptions/update").content(jsonContent)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void handleForgotPasswordRequestCaseNormal() throws Exception {
    when(forgotPasswordService.handleForgotPassword(anyString(), anyString())).thenReturn(true);
    String expected = new String(Files.readAllBytes(Paths.get(
        this.getClass().getResource("/patron/json/requestForgotPasswordSuccess.json").toURI())));
    this.mvc.perform(post("/SISTIC/patrons/forgotpassword/dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void handleForgotPasswordRequestCaseError() throws Exception {
    when(forgotPasswordService.handleForgotPassword(anyString(), anyString())).thenReturn(false);
    String expected = new String(Files.readAllBytes(Paths
        .get(this.getClass().getResource("/patron/json/requestForgotPasswordError.json").toURI())));
    this.mvc.perform(post("/SISTIC/patrons/forgotpassword/dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void validateForgotPasswordTokenCaseNormal() throws Exception {
    when(forgotPasswordService.validateToken(anyString(), anyString())).thenReturn(true);
    String expected = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource("/patron/json/validateForgotPasswordTokenSuccess.json").toURI())));
    this.mvc
        .perform(
            get("/SISTIC/patrons/forgotpassword/availability/dbthan@cmc.com.vn/tokenstring").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void validateForgotPasswordTokenCaseError() throws Exception {
    when(forgotPasswordService.validateToken(anyString(), anyString())).thenReturn(false);
    String expected = new String(Files.readAllBytes(Paths.get(this.getClass()
        .getResource("/patron/json/validateForgotPasswordTokenError.json").toURI())));
    this.mvc
        .perform(
            get("/SISTIC/patrons/forgotpassword/availability/dbthan@cmc.com.vn/tokenstring").header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void updatePasswordCaseNormal() throws Exception {
    when(forgotPasswordService.updatePassword(anyString(), anyString(), anyString()))
        .thenReturn(true);
    String expected = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/updatePasswordSuccess.json").toURI())));
    this.mvc
        .perform(post(
            "/SISTIC/patrons/changepassword/dbthan@cmc.com.vn/tokenstring?newPassword=abcd1234").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());
  }

  @Test
  public void updatePasswordCaseError() throws Exception {
    when(forgotPasswordService.updatePassword(anyString(), anyString(), anyString()))
        .thenReturn(false);
    String expected = new String(Files.readAllBytes(
        Paths.get(this.getClass().getResource("/patron/json/updatePasswordError.json").toURI())));
    this.mvc
        .perform(post(
            "/SISTIC/patrons/changepassword/dbthan@cmc.com.vn/tokenstring?newPassword=abcd1234").header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().json(expected)).andDo(print());

  }

  @Test
  public void isPatronLockCaseNotExistEmail() throws Exception {
    when(patronService.isPatronLock(anyString())).thenReturn(null);
    this.mvc.perform(get("/SISTIC/patrons/accountstatus?email=dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andDo(print());
  }

  @Test
  public void isPatronLockCaseAccountLock() throws Exception {
    when(patronService.isPatronLock(anyString())).thenReturn(0);
    this.mvc.perform(get("/SISTIC/patrons/accountstatus?email=dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("true")).andDo(print());
  }

  @Test
  public void isPatronLockCaseAccountNormal() throws Exception {
    when(patronService.isPatronLock(anyString())).thenReturn(1);
    this.mvc.perform(get("/SISTIC/patrons/accountstatus?email=dbthan@cmc.com.vn").header("Authorization", TEST_TOKEN))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(content().string("false")).andDo(print());
  }

}

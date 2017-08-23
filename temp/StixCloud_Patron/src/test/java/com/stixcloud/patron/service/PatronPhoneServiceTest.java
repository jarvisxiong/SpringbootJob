package com.stixcloud.patron.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.domain.PatronPhone;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.domain.Country;
import com.stixcloud.patron.repo.IPatronPhoneRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PatronPhoneServiceTest {

  private final Logger LOGGER = LogManager.getLogger(PatronPhoneServiceTest.class);

  private List<PatronPhone> patronPhone;
  private PatronUpdateRequest request;
  private PatronProfileJson original;
  private List<ContactJson> contactNos;
  private CountryPhoneCodeJson countryResponse;
  private List<Country> countries;

  @InjectMocks
  private PatronPhoneService service;
  @Mock
  private IPatronPhoneRepository repo;
  @Mock
  private ICountryService countryService;
  @Mock
  private MessageSource messageSource;

  @Before
  public void setup() {
    original = new PatronProfileJson();
    request = new PatronUpdateRequest();
    patronPhone = new ArrayList<PatronPhone>();
    countries = new ArrayList<Country>();
    PatronPhone phone = new PatronPhone(1525L, true, 15254L, "MOBILE", 84L, "VN", "01623652452");
    patronPhone.add(phone);
    countryResponse = new CountryPhoneCodeJson("VN", 84);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact = new ContactJson("MOBILE", countryResponse, "84923654115");
    contactNos.add(contact);
    original.setContacts(contactNos);
    Country c1 = new Country(1L, "VN", "VND", "84");
    Country c2 = new Country(2L, "SG", "SGD", "65");
    countries.add(c1);
    countries.add(c2);
    when(messageSource.getMessage(anyString(), any(), any(Locale.class))).thenReturn("error");
  }

  @Test
  public void isChangedCaseFalse() {
    request.setContacts(contactNos);
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void isChangedCaseTrue() {
    request.setContacts(new ArrayList<>());
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void updatePatronCaseUpdate() throws Exception {
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronPhone);
    when(countryService.fillAllCountry()).thenReturn(countries);
    request.setContacts(contactNos);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patronPhone);
  }

  @Test
  public void updatePatronCaseInsert() throws Exception {
    ContactJson contact = new ContactJson("HOME", countryResponse, "84923654115");
    contactNos.add(contact);
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronPhone);
    when(countryService.fillAllCountry()).thenReturn(countries);
    request.setContacts(contactNos);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<List<PatronPhone>> argumentPatronPhone =
        ArgumentCaptor.forClass((Class) List.class);
    verify(repo, times(2)).save(argumentPatronPhone.capture());
  }

  @Test
  public void updatePatronCaseDelete() throws Exception {
    PatronPhone phone = new PatronPhone(1525L, true, 15254L, "HOME", 84L, "VN", "01623652452");
    patronPhone.add(phone);
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronPhone);
    when(countryService.fillAllCountry()).thenReturn(countries);
    request.setContacts(contactNos);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    ArgumentCaptor<List<PatronPhone>> argumentPatronPhone =
        ArgumentCaptor.forClass((Class) List.class);
    verify(repo, times(1)).delete(argumentPatronPhone.capture());
  }

  @Test
  public void updatePatronCaseNotCallSaveMethod() throws Exception {
    when(repo.findByPatronProfileId(anyLong())).thenReturn(null);
    when(countryService.fillAllCountry()).thenReturn(new ArrayList<Country>());
    request.setContacts(contactNos);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(0)).save(patronPhone);
  }

  @Test
  public void insertPatronPhone() {
    List<PatronPhone> patrons = new ArrayList<>();
    long startTime = System.nanoTime();
    service.insertPatronPhone(patrons);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patrons);
  }
}

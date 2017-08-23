package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.ContactJson;
import com.stixcloud.patron.api.json.CountryPhoneCodeJson;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.repo.PatronProfileJsonRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IPatronProfileJsonServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IPatronProfileJsonServiceTest.class);
  @InjectMocks
  private PatronProfileJsonService service;
  @Mock
  private PatronProfileJsonRepository repo;

  private PatronProfileJson patronJson;
  private List<ContactJson> contactNos;
  private IdentificationJson identification;
  private List<AddressJson> addresses;
  private CountryPhoneCodeJson countryResponse;

  @Before
  public void setup() {
    countryResponse = new CountryPhoneCodeJson("VN", 84);
    contactNos = new ArrayList<ContactJson>();
    ContactJson contact = new ContactJson("HOME", countryResponse, "84923654115");
    contactNos.add(contact);
    identification = new IdentificationJson("NRIC/FIN", "S1234567D");
    addresses = new ArrayList<AddressJson>();
    AddressJson address =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    addresses.add(address);
    patronJson = new PatronProfileJson("MR", "CMC", "TOWER", contactNos, 1998, "VN", "VN",
        identification, true, addresses);

  }

  @Test
  public void getPatronProfileJsonByEmail() {
    when(repo.findOne(anyString())).thenReturn(patronJson);
    long startTime = System.nanoTime();
    PatronProfileJson actual = service.getPatronProfileJsonByEmail("cmc@gmail.com");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(patronJson, actual);
  }

  @Test
  public void savePatronProfileJson() {
    PatronProfileJson patron = new PatronProfileJson();
    long startTime = System.nanoTime();
    service.savePatronProfileJson(patron);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patron);
  }
}

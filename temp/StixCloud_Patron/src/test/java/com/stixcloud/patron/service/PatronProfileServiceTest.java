package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.IdentificationJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.domain.PatronProfile;
import com.stixcloud.patron.repo.IPatronProfileRepository;
import com.stixcloud.patron.util.MasterCodeTableUtil;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PatronProfileServiceTest {

  private final Logger LOGGER = LogManager.getLogger(PatronProfileServiceTest.class);

  private IdentificationJson identification;
  private PatronUpdateRequest request;
  private PatronProfileJson original;
  private PatronProfile patron;
  private MasterCodeTable masterCodeTable;
  private List<MasterCodeTable> masterCodeTableList;
  private List<MasterCodeTable> genderTitleMct;

  @InjectMocks
  private PatronProfileService service;
  @Mock
  private IPatronProfileRepository repo;
  @Mock
  private MasterCodeTableService masterCodeService;
  @Mock
  private EntityManager em;
  @Mock
  private TypedQuery typedQuery;
  @Mock
  private MasterCodeTableUtil mctUtil;

  @Before
  public void setup() {
    original = new PatronProfileJson();
    request = new PatronUpdateRequest();
    patron = new PatronProfile();
    masterCodeTableList = new ArrayList<>();
    identification = new IdentificationJson("NRIC/FIN", "S1234567D");
    request.setFirstName("Than");
    request.setLastName("Than");
    request.setIdentification(identification);
    request.setNationality("VN");
    request.setCountryOfResidence("VN");
    masterCodeTable =
        new MasterCodeTable(1818L, "Identification Number Type", true, "NRIC/FIN", "N");
    masterCodeTableList.add(masterCodeTable);
    genderTitleMct = new ArrayList<>();
    MasterCodeTable mct1 = new MasterCodeTable(14L, "Title", true, "MR", "MR");
    MasterCodeTable mct2 = new MasterCodeTable(16L, "Gender", true, "Male", "M");
    genderTitleMct.add(mct2);
    genderTitleMct.add(mct1);
  }

  @Test
  public void isChangedCaseFalse() {
    original.setFirstName("Than");
    original.setLastName("Than");
    original.setIdentification(identification);
    original.setNationality("VN");
    original.setCountryOfResidence("VN");
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void isChangedCaseTrue() {
    original.setCountryOfResidence("SG");
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void updatePatronCaseCallSaveMethod() throws Exception {
    when(mctUtil.getMasterCodeTable(anyString(), anyListOf(MasterCodeTable.class),
        anyListOf(MasterCodeTable.class))).thenReturn(genderTitleMct);
    when(repo.findOne(anyLong())).thenReturn(patron);
    when(masterCodeService.findByCategory(anyString())).thenReturn(masterCodeTableList);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patron);
  }

  @Test
  public void updatePatronCaseNotCallSaveMethod() throws Exception {
    when(repo.findOne(anyLong())).thenReturn(null);
    long startTime = System.nanoTime();
    service.updatePatron(request, 152L, 99L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(0)).save(patron);
  }

  @Test
  public void getSequenceAccNo() {
    when(em.createNativeQuery(anyString())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(1L);
    long startTime = System.nanoTime();
    Long actual = service.getSequenceAccNo();
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(new Long(1L), actual);
  }

  @Test
  public void insertPatronProfile() {
    PatronProfile patron = new PatronProfile();
    long startTime = System.nanoTime();
    service.insertPatronProfile(patron);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    verify(repo, times(1)).save(patron);
  }
}

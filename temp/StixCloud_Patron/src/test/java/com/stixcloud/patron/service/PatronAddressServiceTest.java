package com.stixcloud.patron.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.stixcloud.domain.Address;
import com.stixcloud.domain.PatronAddress;
import com.stixcloud.patron.api.PatronUpdateRequest;
import com.stixcloud.patron.api.json.AddressJson;
import com.stixcloud.patron.api.json.PatronProfileJson;
import com.stixcloud.patron.repo.IPatronAddressRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PatronAddressServiceTest {

  private final Logger LOGGER = LogManager.getLogger(PatronAddressServiceTest.class);

  private PatronUpdateRequest request;
  private PatronProfileJson original;
  private List<AddressJson> addresses;
  private List<PatronAddress> patronAddresses;
  private Address address;
  private List<Address> addressList;

  @InjectMocks
  private PatronAddressService service;
  @Mock
  private IPatronAddressRepository repo;
  @Mock
  private IAddressService addressService;

  @Before
  public void setup() {
    addresses = new ArrayList<AddressJson>();
    addressList = new ArrayList<>();
    AddressJson jaddress =
        new AddressJson("Billing Address", "VN", "111", "1", "CMC Tower", "15236");
    addresses.add(jaddress);
    original = new PatronProfileJson();
    original.setAddresses(addresses);
    request = new PatronUpdateRequest();
    request.setAddresses(addresses);

    patronAddresses = new ArrayList<PatronAddress>();
    PatronAddress paddress = new PatronAddress(1234L, 1425L, 1563L, "T");
    patronAddresses.add(paddress);

    address =
        new Address(1525L, "Billing Address", "Line 1", "Line 2", "Line 3", "Ha Noi", "Ha Noi",
            "VN", "50000", 15, OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC), 15,
            OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC));
    addressList.add(address);
  }

  @Test
  public void isChangedCaseFalse() {
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertFalse(actual);
  }

  @Test
  public void isChangedCaseTrue() {
    request.setAddresses(new ArrayList<>());
    long startTime = System.nanoTime();
    boolean actual = service.isChanged(request, original);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertTrue(actual);
  }

  @Test
  public void updatePatronCaseUpdateNormal() throws Exception {
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronAddresses);
    when(addressService.findOne(anyLong())).thenReturn(address);
    when(addressService.findByIds(anyListOf(Long.class))).thenReturn(addressList);
    when(repo.findPatronAddress(anyListOf(Long.class))).thenReturn(patronAddresses);
    service.updatePatron(request, 152L, 99L);
    verify(addressService, times(1)).save(address);
  }

  @Test
  public void updatePatronCaseDeleteNormal() throws Exception {
    Address address1 =
        new Address(1525L, "Mailing Address", "Line 1", "Line 2", "Line 3", "Ha Noi", "Ha Noi",
            "VN", "50000", 15, OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC), 15,
            OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC));
    addressList.add(address1);
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronAddresses);
    when(addressService.findOne(anyLong())).thenReturn(address);
    when(addressService.findByIds(anyListOf(Long.class))).thenReturn(addressList);
    when(repo.findPatronAddress(anyListOf(Long.class))).thenReturn(patronAddresses);
    service.updatePatron(request, 152L, 99L);
    ArgumentCaptor<List<PatronAddress>> patronAddresses =
        ArgumentCaptor.forClass((Class) List.class);
    ArgumentCaptor<List<Address>> argumentAddresses = ArgumentCaptor.forClass((Class) List.class);
    verify(addressService, times(1)).save(address);
    verify(repo, times(1)).delete(patronAddresses.capture());
    verify(addressService, times(1)).deleteAddresses(argumentAddresses.capture());
  }

  @Test
  public void updatePatronCaseInsertNormal() throws Exception {
    AddressJson jaddress =
        new AddressJson("Mailing Address", "VN", "111", "1", "CMC Tower", "15236");
    addresses.add(jaddress);
    request.setAddresses(addresses);
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronAddresses);
    when(addressService.findOne(anyLong())).thenReturn(address);
    when(addressService.findByIds(anyListOf(Long.class))).thenReturn(addressList);
    when(repo.findPatronAddress(anyListOf(Long.class))).thenReturn(patronAddresses);
    when(addressService.save(any(Address.class))).thenReturn(address);
    service.updatePatron(request, 152L, 99L);
    ArgumentCaptor<PatronAddress> argumentPatronAddress =
        ArgumentCaptor.forClass(PatronAddress.class);
    verify(addressService, times(1)).save(address);
    verify(repo, times(1)).save(argumentPatronAddress.capture());
  }

  @Test
  public void updatePatronCaseNotCallSaveMethod() throws Exception {
    address =
        new Address(1525L, "Mailing Address", "Line 1", "Line 2", "Line 3", "Ha Noi", "Ha Noi",
            "VN", "50000", 15, OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC), 15,
            OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC));
    when(repo.findByPatronProfileId(anyLong())).thenReturn(patronAddresses);
    when(addressService.findOne(anyLong())).thenReturn(address);
    when(addressService.save(any(Address.class))).thenReturn(address);
    service.updatePatron(request, 152L, 99L);
    verify(addressService, times(0)).save(address);
  }
  
  @Test
  public void insertPatronAddress() {
    PatronAddress patron = new PatronAddress();
    service.insertPatronAddress(patron);
    verify(repo, times(1)).save(patron);
  }
}

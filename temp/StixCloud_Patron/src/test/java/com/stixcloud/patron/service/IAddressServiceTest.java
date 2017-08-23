package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
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

import com.stixcloud.domain.Address;
import com.stixcloud.patron.api.TenantPropertiesTest;
import com.stixcloud.patron.repo.IAddressRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IAddressServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IAddressServiceTest.class);

  @InjectMocks
  private AddressService service;
  @Mock
  private IAddressRepository repo;

  private Address address;

  @Before
  public void setup() throws Exception {
    TenantPropertiesTest.setUp();
    address = new Address(1525L, "Billing Address", "Line 1", "Line 2", "Line 3", "Ha Noi",
        "Ha Noi", "VN", "50000", 15, OffsetDateTime.of(2017, 12, 12, 12, 12,12,33, ZoneOffset.UTC), 15,
        OffsetDateTime.of(2017, 12, 12, 12, 12, 12, 33, ZoneOffset.UTC));

  }

  @Test
  public void findOne() {
    when(repo.findOne(anyLong())).thenReturn(address);
    long startTime = System.nanoTime();
    Address expected = service.findOne(19L);
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(expected, address);
  }
}

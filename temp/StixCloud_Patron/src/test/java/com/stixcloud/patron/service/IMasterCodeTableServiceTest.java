package com.stixcloud.patron.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
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

import com.stixcloud.domain.MasterCodeTable;
import com.stixcloud.patron.repo.IMasterCodeTableRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IMasterCodeTableServiceTest {

  private final Logger LOGGER = LogManager.getLogger(IMasterCodeTableServiceTest.class);
  
  @InjectMocks
  private MasterCodeTableService service;
  @Mock
  private IMasterCodeTableRepository repo;
  private MasterCodeTable masterCodeTable;
  private List<MasterCodeTable> masterCodeTableList;
  
  @Before
  public void setup() {
    masterCodeTableList = new ArrayList<MasterCodeTable>();
    masterCodeTable = new MasterCodeTable(1555L, "TITLE", true, "MR", "MR");
    masterCodeTableList.add(masterCodeTable);
  }
  
  @Test
  public void findByCategory() {
    when(repo.findByType(anyString())).thenReturn(masterCodeTableList);
    long startTime = System.nanoTime();
    List<MasterCodeTable> actual = service.findByCategory("TITLE");
    LOGGER.info("Time taken "
        + TimeUnit.MILLISECONDS.convert((System.nanoTime() - startTime), TimeUnit.NANOSECONDS)
        + " ms");
    assertEquals(masterCodeTableList, actual);
  }
}

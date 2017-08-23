package com.stixcloud.product.event;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stixcloud.domain.InternetContentProducts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by Chong Ye on 24/8/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class InternetContentProductsRepositoryTest {
  private final Logger logger = LogManager.getLogger(InternetContentProductsRepositoryTest.class);

  @InjectMocks
  InternetContentProductsRepository repositoryImpl;
  @Mock
  EntityManager em;
  @Mock
  Query query;
  @Autowired
  ObjectMapper objectMapper;

  private List<InternetContentProducts> contentProductsList;

  @Before
  public void setUp() throws Exception {
    contentProductsList = objectMapper.readValue(
        this.getClass().getResource("/sample/product/api/InternetContentProducts.json"),
        new TypeReference<List<InternetContentProducts>>() {
        });
  }

  /*@Test
  public void getProductsByInternetContentCode() throws Exception {
    when(em.createNativeQuery(anyString(), eq(InternetContentProducts.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.setParameter(anyString(), eq(Timestamp.class))).thenReturn(query);
    when(query.setParameter(anyString(), eq(Timestamp.class))).thenReturn(query);
    when(query.setParameter(anyString(), anyString())).thenReturn(query);
    when(query.getResultList()).thenReturn(contentProductsList);

    List<InternetContentProducts> list =
        repositoryImpl
            .getProductsByInternetContentCode("cpigs0916", LocalDate.now(), LocalDate.now(),
                "ABCD");

    assertFalse(list.isEmpty());
    assertEquals(list.size(), contentProductsList.size());
    assertTrue(this.contentProductsList.containsAll(list));
  }*/
}

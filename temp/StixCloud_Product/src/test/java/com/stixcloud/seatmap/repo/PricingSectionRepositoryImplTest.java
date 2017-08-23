package com.stixcloud.seatmap.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stixcloud.seatmap.dto.PricingSection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by chongye on 16-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PricingSectionRepositoryImplTest {
  @InjectMocks
  PricingSectionRepositoryImpl pricingSectionRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<PricingSection> typedQuery;

  private PricingSection createPricingSection() {
    return new PricingSection(138L, 1, 1400L, "T", 500L, 1200L, "Stall", "FREE SEATING",
        "General Admission", "GA");
  }

  @Test
  public void findOne() throws Exception {
    PricingSection mockResult = createPricingSection();
    when(em.createQuery(anyString(), eq(PricingSection.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(mockResult);

    PricingSection result = pricingSectionRepository.findOne(500L);

    verify(em).createQuery(anyString(), eq(PricingSection.class));
    verify(typedQuery).getSingleResult();
    assertEquals(mockResult, result);
  }

}

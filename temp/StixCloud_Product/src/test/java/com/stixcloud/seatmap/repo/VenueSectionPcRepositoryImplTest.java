package com.stixcloud.seatmap.repo;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Created by chongye on 15-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class VenueSectionPcRepositoryImplTest {
  @InjectMocks
  VenueSectionPcRepositoryImpl venueSectionPcRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<Long> typedQuery;

  private List<Long> createPcSectionIds() {
    List<Long> pcSectionIds = new ArrayList<>();
    pcSectionIds.add(1L);
    pcSectionIds.add(2L);
    return pcSectionIds;
  }

  @Test
  public void findPcSectionId() throws Exception {
    Long pcSectionId = random(Long.class);
    when(em.createQuery(anyString(), eq(Long.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyListOf(Long.class))).thenReturn(typedQuery);
    when(typedQuery.getSingleResult()).thenReturn(pcSectionId);

    Long result = venueSectionPcRepository.findPcSectionId(1L, createPcSectionIds());

    verify(typedQuery).getSingleResult();
    verify(em).createQuery(anyString(), eq(Long.class));
    assertFalse(result == null);
    assertEquals(result, pcSectionId);
  }
}

package com.stixcloud.seatmap.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stixcloud.seatmap.dto.PriceCategoryDetail;
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
 * Created by chongye on 16-Sep-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PriceCategoryDetailRepositoryImplTest {
  @InjectMocks
  PriceCategoryDetailRepositoryImpl priceCategoryDetailRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<PriceCategoryDetail> typedQuery;

  private List<PriceCategoryDetail> createPriceCategoryDetailList() {
    List<PriceCategoryDetail> priceCategoryDetails = new ArrayList<>();
    priceCategoryDetails.add(new PriceCategoryDetail(1, "Cat 1", -26099, 12176L));
    return priceCategoryDetails;
  }

  @Test
  public void findPriceCategoryDetailByProductId() throws Exception {
    List<PriceCategoryDetail> mockResult = createPriceCategoryDetailList();
    when(em.createQuery(anyString(), eq(PriceCategoryDetail.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(mockResult);

    List<PriceCategoryDetail> result =
        priceCategoryDetailRepository.findPriceCategoryDetailByProductId(1L);

    verify(em).createQuery(anyString(), eq(PriceCategoryDetail.class));
    verify(typedQuery).getResultList();
    assertEquals(mockResult, result);
  }

}

package com.stixcloud.seatmap.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stixcloud.domain.VenueDetailComponentsLc;
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
public class VenueDetailsComponentLcRepositoryImplTest {
  @InjectMocks
  VenueDetailComponentsLcRepositoryImpl venueDetailComponentsLcRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<VenueDetailComponentsLc> typedQuery;

  private List<VenueDetailComponentsLc> createVenueDetailComponentsLcList() {
    VenueDetailComponentsLc lc =
        new VenueDetailComponentsLc(169698L, 39228L, 171286L, 0, "N", "0", "196", "347", 0, 0,
            false, true, 0, 0, 0, 0);
    List<VenueDetailComponentsLc> detailComponentsLcList = new ArrayList<>();
    detailComponentsLcList.add(lc);
    return detailComponentsLcList;
  }

  @Test
  public void retrieveDetailViewComponents() throws Exception {
    List<VenueDetailComponentsLc> componentsLcList = createVenueDetailComponentsLcList();

    when(em.createQuery(anyString(), eq(VenueDetailComponentsLc.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(componentsLcList);

    List<VenueDetailComponentsLc> results =
        venueDetailComponentsLcRepository.retrieveDetailViewComponents(1L, 1L);

    verify(typedQuery).getResultList();
    verify(em).createQuery(anyString(), eq(VenueDetailComponentsLc.class));
    assertEquals(results, componentsLcList);
  }

}

package com.stixcloud.seatmap.repo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.stixcloud.seatmap.dto.SeatModel;
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
public class SeatModelRepositoryImplTest {
  @InjectMocks
  SeatModelRepositoryImpl seatModelRepository;
  @Mock
  EntityManager em;
  @Mock
  TypedQuery<SeatModel> typedQuery;

  private List<SeatModel> createSeatModelList() {
    List<SeatModel> seatModels = new ArrayList<>();
    seatModels.add(
        new SeatModel(2814435, 171286, 118492204, "DBS Arts Centre - Home of SRTCenterStallD10",
            "10", 10, 114, true, "372;129", 1, 0, "Stall", "RS", 2, null, "Center", 10200
        ));
    return seatModels;
  }


  @Test
  public void getSectionsForBlock() throws Exception {
    List<SeatModel> mockResult = createSeatModelList();
    when(em.createQuery(anyString(), eq(SeatModel.class))).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.setParameter(anyString(), anyLong())).thenReturn(typedQuery);
    when(typedQuery.getResultList()).thenReturn(mockResult);

    List<SeatModel> result = seatModelRepository.getSectionsForBlock(1L, 1L);

    verify(em).createQuery(anyString(), eq(SeatModel.class));
    verify(typedQuery).getResultList();
    assertEquals(mockResult, result);
  }

}

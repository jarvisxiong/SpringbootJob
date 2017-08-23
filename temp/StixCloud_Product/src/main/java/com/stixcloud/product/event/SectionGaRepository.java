package com.stixcloud.product.event;

import com.stixcloud.domain.RetrieveSectionGaView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Created by dbthan on 07-Sep-16.
 */
@Service
public class SectionGaRepository implements ISectionGaRepository {

  @Autowired
  private EntityManager em;

  /**
   * Get list section that have type is GA.
   * @return List Section GA
   */
  @Override
  public List<RetrieveSectionGaView> getSectionGaList(Long productId, Long profileInfoId) {

    String queryStr = "FROM RetrieveSectionGaView WHERE productId=?1 AND profileInfoId=?2";

    return em.createQuery(queryStr, RetrieveSectionGaView.class)
        .setParameter(1, productId)
        .setParameter(2, profileInfoId)
        .getResultList();
  }
}

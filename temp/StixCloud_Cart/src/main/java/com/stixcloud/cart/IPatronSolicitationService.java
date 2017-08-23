package com.stixcloud.cart;

import com.stixcloud.cart.api.PatronSolicitation;

import java.util.List;

/**
 * Created by sengkai on 12/6/2016.
 */
public interface IPatronSolicitationService {

  PatronSolicitation retrievePatronSolicitation(Long patronprofileid);

  PatronSolicitation retrievePatronSolicitation(Long patronprofileid, List<Long> productIds);

  void updatePatronSolicitation(Long patronprofileid,
                                PatronSolicitation patronSolicitation);
}

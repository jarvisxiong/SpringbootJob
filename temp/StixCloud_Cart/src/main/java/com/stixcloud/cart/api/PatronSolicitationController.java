package com.stixcloud.cart.api;

import com.stixcloud.cart.IPatronSolicitationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

/**
 * Created by sengkai on 12/7/2016.
 */
@RestController
@CrossOrigin
@RequestMapping("/{tenant}")
public class PatronSolicitationController {
  private static final Logger LOGGER = LogManager.getLogger(PatronSolicitationController.class);

  @Autowired
  IPatronSolicitationService iPatronSolicitationService;
  @Autowired
  private MessageSource messageSource;

  @RequestMapping(value = "/patron/{patronId}/products", method = RequestMethod.POST)
  @ResponseBody
  public Object getPatronSolicitationList(@PathVariable Long patronId,
                                          @Valid @RequestBody List<Long> productIds) {
    return iPatronSolicitationService.retrievePatronSolicitation(patronId, productIds);
  }
}

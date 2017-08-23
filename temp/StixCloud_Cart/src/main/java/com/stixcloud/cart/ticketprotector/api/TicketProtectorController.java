package com.stixcloud.cart.ticketprotector.api;

import com.stixcloud.cart.CartException;
import com.stixcloud.cart.ticketprotector.ITicketProtectorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import javax.money.MonetaryAmount;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/{tenant}")
public class TicketProtectorController {
  public static final Logger logger = LogManager.getLogger(TicketProtectorController.class);

  @Autowired
  ITicketProtectorService ticketProtectorService;

  @RequestMapping(value = "/cart/id/{cartGuid}/ticketprotector", method = RequestMethod.GET)
  @ResponseBody
  public MonetaryAmount getTicketProtectorAmount(HttpServletResponse response,
                                                 @PathVariable String cartGuid, Locale locale)
      throws
      CartException {
    response.setHeader("Access-Control-Allow-Origin", "*");

    return ticketProtectorService.getTicketProtectorAmount(cartGuid);
  }
}

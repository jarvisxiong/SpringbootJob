package com.stixcloud.cart.api;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stixcloud.cart.IFeeService;
import com.stixcloud.cart.IPatronSolicitationService;
import com.stixcloud.cart.IPaymentGatewayAPIService;
import com.stixcloud.cart.IShoppingCartService;
import com.stixcloud.cart.ticketprotector.ITicketProtectorService;
import com.stixcloud.evoucher.service.EVoucherRunEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;

//import com.stixcloud.ticketprotector.ITicketProtectorService;

/**
 * Created by chongye on 30-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest
public class ExceptionHandlerControllerTest {
  @Autowired
  private MockMvc mvc;
  @MockBean
  private IShoppingCartService shoppingCartService;
  @MockBean
  private IFeeService iFeeService;
  @MockBean
  private ITicketProtectorService ticketProtectorService;
  @MockBean
  private IPaymentGatewayAPIService iPaymentGatewayAPIService;
  @MockBean
  private IPatronSolicitationService iPatronSolicitationService;
  @MockBean
  private EVoucherRunEngine eVoucherRunEngine;

  @Test
  public void defaultExceptionHandler() throws Exception {
    String json = new String(
        Files.readAllBytes(
            Paths.get(this.getClass().getResource("/cart/json/addToCart_GA.json").toURI())));
    when(shoppingCartService.addToCart(any(AddToCartRequest.class), anyString()))
        .thenThrow(new RuntimeException("Unexpected Exception"));
    this.mvc.perform(post("/cart").contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void handleNoHandlerFoundException() throws Exception {
    this.mvc.perform(get("/icc/")).andExpect(status().isInternalServerError());
  }
}

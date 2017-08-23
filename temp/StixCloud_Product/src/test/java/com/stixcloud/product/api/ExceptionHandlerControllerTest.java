package com.stixcloud.product.api;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.stixcloud.product.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by chongye on 30-Aug-16.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionHandlerControllerTest {
  @Autowired
  MockMvc mvc;
  @MockBean
  EventService eventService;
  @MockBean
  private DetailSeatmapFactory detailSeatmapFactory;
  @MockBean
  private IPriceClass priceClass;

  @Test
  public void defaultExceptionHandler() throws Exception {
    when(eventService
        .getProductsByInternetContentCode(anyString(), anyObject(), anyInt()))
        .thenThrow(new RuntimeException("Unexpected Exception"));
    this.mvc.perform(get("/SISTIC/icc/cpigs0916"))
        .andExpect(status().isInternalServerError());
  }

  @Test
  public void handleNoHandlerFoundException() throws Exception {
    this.mvc.perform(get("/SISTIC/icc/"))
        .andExpect(status().isNotFound());
  }
}

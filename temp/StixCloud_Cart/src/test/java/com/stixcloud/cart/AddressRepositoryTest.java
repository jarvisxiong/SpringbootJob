package com.stixcloud.cart;

import static org.assertj.core.api.Assertions.assertThat;

import com.stixcloud.cart.repo.AddressRepository;
import com.stixcloud.domain.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by sengkai on 12/1/2016.
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressRepositoryTest {
  private final Logger logger = LogManager.getLogger(AddressRepositoryTest.class);

  @Autowired
  AddressRepository addressRepository;

  @Test
  @Sql("/cart/sql/patronAddresses.sql")
  public void findPatronAddresses() {
    logger.debug(" [AddressRepositoryTest.findPatronAddresses] :: >>>>> ");
    List<Address> patronAddresses = addressRepository.findPatronAddresses(1922400L);
    patronAddresses.forEach(System.out::println);

    assertThat(patronAddresses).isNotEmpty().hasSize(2);

    logger.debug(" [AddressRepositoryTest.findPatronAddresses] :: <<<<< ");
  }

  @Test
  @Sql("/cart/sql/patronAddresses.sql")
  public void findIsBillingMailingAddressSame() {
    logger.debug(" [AddressRepositoryTest.findIsBillingMailingAddressSame] :: >>>>> ");
    List<Address> patronAddresses = addressRepository.findPatronAddresses(1922400L);

    patronAddresses.forEach(System.out::println);

    assertThat(patronAddresses.stream().findAny()
        .equals("Billing Address"));

    Address test = patronAddresses.stream()
        .filter(address -> address.getLineone().equalsIgnoreCase("555"))
        .filter(address -> address.getLinetwo().equalsIgnoreCase("no man land"))
        .filter(address -> address.getLinethree().equalsIgnoreCase("no man land2"))
        .filter(address -> address.getCity().equalsIgnoreCase("SG"))
        .filter(address -> address.getState().equalsIgnoreCase("SG"))
        .filter(address -> address.getCountry().equalsIgnoreCase("SG"))
        .filter(address -> address.getPostcode().equalsIgnoreCase("444444"))
        .findFirst()
        .orElse(null);

    assertThat(test).isNotNull();

    logger.debug(" [AddressRepositoryTest.findIsBillingMailingAddressSame] :: <<<<< ");
  }

  @Test
  @Sql("/cart/sql/patronAddresses.sql")
  public void findIsBillingMailingAddressNotSame() {
    logger.debug(" [AddressRepositoryTest.findIsBillingMailingAddressSame] :: >>>>> ");
    List<Address> patronAddresses = addressRepository.findPatronAddresses(1922400L);

    patronAddresses.forEach(System.out::println);

    assertThat(patronAddresses.stream().findAny()
        .equals("Billing Address"));

    Address test = patronAddresses.stream()
        .filter(address -> address.getLineone().equalsIgnoreCase("55599"))
        .filter(address -> address.getLinetwo().equalsIgnoreCase("no man land"))
        .filter(address -> address.getLinethree().equalsIgnoreCase("no man land2"))
        .filter(address -> address.getCity().equalsIgnoreCase("SG"))
        .filter(address -> address.getState().equalsIgnoreCase("SG"))
        .filter(address -> address.getCountry().equalsIgnoreCase("SG"))
        .filter(address -> address.getPostcode().equalsIgnoreCase("444444"))
        .findFirst()
        .orElse(null);

    assertThat(test).isNull();

    logger.debug(" [AddressRepositoryTest.findIsBillingMailingAddressSame] :: <<<<< ");
  }
}

package com.stixcloud.cart;

import com.stixcloud.cart.repo.SalesSeatInventoryRepository;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.SalesSeatInventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Created by chongye on 7/10/2016.
 */
@RefreshScope
@Service
public class SalesSeatInventoryService implements ISalesSeatInventoryService {
  private static final Logger logger = LogManager.getLogger(SalesSeatInventoryService.class);
  @Autowired
  SalesSeatInventoryRepository salesSeatInventoryRepository;
  @Autowired
  private MessageSource messageSource;

  @Override
  public void releaseSeatsForGA(List<CartItem> cartItems) {
    cartItems.stream().filter(ci -> ci.getMode().equals(InventoryConstants.SECTION_TYPE_GA.getName()))
        .forEach(ci -> {
          Iterable<SalesSeatInventory> salesSeatInventories =
              salesSeatInventoryRepository.findAll(ci.getInventoryIdList());

          List<SalesSeatInventory> salesSeatInventoryList = new ArrayList<>();
          salesSeatInventories.forEach(salesSeatInventoryList::add);
          Map<Integer, List<SalesSeatInventory>> seatInventoryMap = salesSeatInventoryList.stream()
              .collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
          seatInventoryMap.entrySet().forEach(p -> {
            if (InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(p.getKey())) {
              updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_AVAILABLE);
            } else if (InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue()
                .equals(p.getKey())) {
              updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_HOLD);
            }
          });
          
        });
  }

  /**
   * Find seats based on arguments provided and return a list of seat inventories
   * @return list of SalesSeatInventory
   */
  @Override
  public List<SalesSeatInventory> findSeatsAndReserveForGA(
      Long productId, Long priceCatId, Long seatSectionId, int quantity) throws CartException {

    List<SalesSeatInventory> salesSeatInventories = salesSeatInventoryRepository
        .findAvailableSeatsForGA(productId, priceCatId, seatSectionId, quantity);

    if (salesSeatInventories.isEmpty()) {
      throw new CartException(messageSource.getMessage("inventory.error.find.seats.for.ga",
          null, LocaleContextHolder.getLocale()));
    }
    
    Map<Integer, List<SalesSeatInventory>> seatInventoryMap = salesSeatInventories.stream()
        .collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
    List<SalesSeatInventory> result = new ArrayList<>();
    seatInventoryMap.entrySet().stream().forEach(p -> {
      if (InventoryConstants.SALES_STATUS_AVAILABLE.getValue().equals(p.getKey())) {
        result.addAll(updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_RESERVED));
      }
    });
    return result;
  }

  @Override
  public List<SalesSeatInventory> updateSeatsReserveExpiry(List<Long> seatInvIds) {
    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.findAll(seatInvIds);

    List<SalesSeatInventory> inventoriesToBeUpdated = new ArrayList<>();
    inventories.forEach(inventoriesToBeUpdated::add);
    List<SalesSeatInventory> result = new ArrayList<>();
    Map<Integer, List<SalesSeatInventory>> seatInventoryMap = inventoriesToBeUpdated.stream()
        .collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
    seatInventoryMap.entrySet().forEach(p -> {
      if (InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(p.getKey())) {
        result
            .addAll(updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_RESERVED));
      } else if (InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue().equals(p.getKey())) {
        result.addAll(
            updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_HOLD_RESERVED));
      }
    });

    return result;
  }

  @Override
  public List<SalesSeatInventory> releaseSeats(List<Long> seatInvIds) {
    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.findAll(seatInvIds);

    List<SalesSeatInventory> inventoriesToBeUpdated = new ArrayList<>();
    inventories.forEach(inventoriesToBeUpdated::add);
    List<SalesSeatInventory> result = new ArrayList<SalesSeatInventory>();
    Map<Integer, List<SalesSeatInventory>> seatInventoryMap = inventoriesToBeUpdated.stream()
        .collect(Collectors.groupingBy(SalesSeatInventory::getSeatsalesstatus));
    seatInventoryMap.entrySet().forEach(p -> {
      if (InventoryConstants.SALES_STATUS_RESERVED.getValue().equals(p.getKey())) {
        result
            .addAll(updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_AVAILABLE));
      } else if (InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue().equals(p.getKey())) {
        result.addAll(updateSeats(p.getValue(), InventoryConstants.SALES_STATUS_HOLD));
      }
    });
    return result;
  }

  /**
   * Update seats reservation in seconds
   * @param seatInvIds list of salesseatinventory objects
   * @param timeToLive timeToLive in seconds
   * @return list of salesseatinventory objects with updated expiry
   */
  public List<SalesSeatInventory> updateSeatsReserveExpiry(List<Long> seatInvIds, Long timeToLive) {
    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.findAll(seatInvIds);

    List<SalesSeatInventory> inventoriesToBeUpdated = new ArrayList<>();
    inventories.forEach(inventoriesToBeUpdated::add);
    inventoriesToBeUpdated.forEach(x -> this.setInventoryToReserved(x, timeToLive));

    inventories =
        salesSeatInventoryRepository.save(inventoriesToBeUpdated);

    List<SalesSeatInventory> retList = new ArrayList<>();
    inventories.forEach(retList::add);
    return retList;
  }

  /**
   * Update seats to the sales status provided in the argument
   * @return list of salesseatinventory updated
   */
  private List<SalesSeatInventory> updateSeats(List<SalesSeatInventory> salesSeatInventories,
                                               InventoryConstants salesStatus) {
    switch (salesStatus) {
      case SALES_STATUS_RESERVED:
        salesSeatInventories.forEach(x -> this.setInventoryToReserved(x, null));
        break;
      case SALES_STATUS_AVAILABLE:
        salesSeatInventories.forEach(this::setInventoryToAvailable);
        break;
      case SALES_STATUS_SOLD:
        salesSeatInventories.forEach(this::setInventoryToSold);
        break;
      case SALES_STATUS_HOLD:
        salesSeatInventories.forEach(this::setInventoryToHold);
        break;
      case SALES_STATUS_HOLD_RESERVED:
        salesSeatInventories.forEach(x -> setInventoryToReservedHold(x, null));
        break;
    }

    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.save(salesSeatInventories);

    List<SalesSeatInventory> retList = new ArrayList<>();
    inventories.forEach(retList::add);
    return retList;
  }

  /**
   * Set SalesSeatInventory to reserved and update reserve expiry date
   * @param salesSeatInventory object to be updated
   * @param timeToLive timeToLive in seconds
   * @return salesSeatInventory with updated statuses
   */
  private void setInventoryToReserved(SalesSeatInventory salesSeatInventory,
                                      Long timeToLive) {
    //set timeout date
    if (timeToLive == null) {
      timeToLive =
          TimeUnit.SECONDS
              .convert(TenantContextHolder.getTenant().getSeatInventoryExpiry(), TimeUnit.MINUTES);
    }
    OffsetDateTime reserveExpiry = OffsetDateTime.now().plusSeconds(timeToLive);

    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_RESERVED.getValue());
    salesSeatInventory.setReserveexpirydate(reserveExpiry);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());
  }

  private void setInventoryToAvailable(SalesSeatInventory salesSeatInventory) {
    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_AVAILABLE.getValue());
    salesSeatInventory.setReserveexpirydate(null);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());
  }
  
  private void setInventoryToReservedHold(SalesSeatInventory salesSeatInventory, Long timeToLive) {
    // set timeout date
    if (timeToLive == null) {
      timeToLive = TimeUnit.SECONDS
          .convert(TenantContextHolder.getTenant().getSeatInventoryExpiry(), TimeUnit.MINUTES);
    }
    OffsetDateTime reserveExpiry = OffsetDateTime.now().plusSeconds(timeToLive);

    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue());
    salesSeatInventory.setReserveexpirydate(reserveExpiry);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());
  }
  
  private void setInventoryToHold(SalesSeatInventory salesSeatInventory) {
    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_HOLD.getValue());
    salesSeatInventory.setReserveexpirydate(null);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());
  }

  @Override
  public List<SalesSeatInventory> updateSeatsToSold(List<Long> seatInvIds) {
    Iterable<SalesSeatInventory> inventories = salesSeatInventoryRepository.findAll(seatInvIds);
    List<SalesSeatInventory> inventoriesToBeUpdated = new ArrayList<>();
    inventories.forEach(inventoriesToBeUpdated::add);
    SalesSeatInventory SalesSeatInventory = inventoriesToBeUpdated.stream()
        .filter(seat -> !(InventoryConstants.SALES_STATUS_RESERVED.getValue() == seat
            .getSeatsalesstatus()) && !(InventoryConstants.SALES_STATUS_HOLD_RESERVED.getValue() == seat
            .getSeatsalesstatus()))
        .findFirst().orElse(null);
    if (SalesSeatInventory != null) {  // if seat status is not reserved then  dont proceed
      return null;
    }
    return updateSeats(inventoriesToBeUpdated, InventoryConstants.SALES_STATUS_SOLD);
  }

  private void setInventoryToSold(SalesSeatInventory salesSeatInventory) {

    salesSeatInventory.setSeatsalesstatus(InventoryConstants.SALES_STATUS_SOLD.getValue());
    salesSeatInventory.setSoldBy(TenantContextHolder.getTenant().getUserInfoId());
    salesSeatInventory.setSolddate(OffsetDateTime.now());
    salesSeatInventory.setSalescyclestarttime(null);
    salesSeatInventory.setReserveexpirydate(null);
    salesSeatInventory.setSessionid(null);
    salesSeatInventory.setUpdatedtime(OffsetDateTime.now());
  }

  public OffsetDateTime getEarliestReservedTime(List<Long> seatInvIds) {
    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.findAll(seatInvIds);
    List<SalesSeatInventory> salesSeatInventories = new ArrayList<>();
    inventories.forEach(salesSeatInventories::add);

    SalesSeatInventory salesSeatInventory = salesSeatInventories.stream()
        .sorted(Comparator.comparing(SalesSeatInventory::getReserveexpirydate))
        .findFirst().orElse(null);

    return (salesSeatInventory != null ? salesSeatInventory.getReserveexpirydate() : null);
  }

  public OffsetDateTime getEarliestReservedStartTime(List<Long> seatInvIds) {
    Iterable<SalesSeatInventory> inventories =
        salesSeatInventoryRepository.findAll(seatInvIds);
    List<SalesSeatInventory> salesSeatInventories = new ArrayList<>();
    inventories.forEach(salesSeatInventories::add);

    SalesSeatInventory salesSeatInventory = salesSeatInventories.stream()
        .sorted(Comparator.comparing(SalesSeatInventory::getReserveexpirydate))
        .findFirst().orElse(null);

    return (salesSeatInventory != null ? salesSeatInventory.getUpdatedtime() : null);
  }
}

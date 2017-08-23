package com.stixcloud.product.event;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stixcloud.dto.PromoCodeDTO;

@Service
public class PromoService implements IPromoService {
  @Autowired
  IPromoCodeRepository repo;

  @Override
  public int getPromoState(String contentCode, String promoCode) {
    return repo.getPromoState(contentCode, promoCode);
  }

  @Override
  public int getPromoState(Long productId, String promoCode) {
    return repo.getPromoState(productId, promoCode);
  }

  @Override
  public Map<Long, Boolean> getProductAvailability(String contentCode, String... promoCode) {
    Set<Long> productIds = new HashSet<>();
    Set<Long> holdCodeIds = new HashSet<>();
    List<PromoCodeDTO> promos = new ArrayList<>();
    boolean isPromo = ArrayUtils.isNotEmpty(promoCode);
    if (isPromo) {
      promos = repo.getPromoCodes(contentCode, promoCode[0]);
    } else {
      promos = repo.getExclusivePromoCodes(contentCode);
    }

    promos.forEach(p -> {
      productIds.add(p.getProductId());
      if (null != p.getHoldCodeId()) {
        holdCodeIds.add(p.getHoldCodeId());
      }
    });

    if (CollectionUtils.isEmpty(productIds)) {
      return new HashMap<>();
    }

    return repo.getProductAvailability(productIds, holdCodeIds, isPromo);
  }

  public Set<Long> getHoldIds(Long productId, String promoCode) {
    List<PromoCodeDTO> promos = new ArrayList<>();
    if (productId != null && StringUtils.isNotEmpty(promoCode)) {
      promos = repo.getPromoCodes(productId, promoCode);
      if (CollectionUtils.isNotEmpty(promos)) {
        return promos.parallelStream().filter(p -> null != p.getHoldCodeId())
            .map(p -> p.getHoldCodeId()).collect(Collectors.toSet());
      }
    }
    return null;
  }
  
  @Override
  public Set<Long> getAvailableSeats(Long productId, Long priceCatId, Long sectionId,
      Set<Long> holdIds) {
    Set<Long> result = new HashSet<>();
    
    result.addAll(repo.getAvailableSeats(productId, priceCatId, sectionId, holdIds, true)
        .stream().map(BigDecimal::longValue).collect(Collectors.toSet()));

    return result;
  }

  @Override
  public List<PromoCodeDTO> getPromoCodes(Long productId, String... promoCode) {
    return ArrayUtils.isNotEmpty(promoCode) ? repo.getPromoCodes(productId, promoCode[0])
        : repo.getExclusivePromoCodes(productId);
  }

  public Set<Long> getPromoPriceClassIds(Long productId, String... promoCode) {
    if (ArrayUtils.isNotEmpty(promoCode)) {
      return getPromoCodes(productId, promoCode[0]).stream().map(PromoCodeDTO::getPriceClassId)
          .collect(Collectors.toSet());
    }

    return getPromoCodes(productId).stream().map(PromoCodeDTO::getPriceClassId)
        .collect(Collectors.toSet());
  }

}

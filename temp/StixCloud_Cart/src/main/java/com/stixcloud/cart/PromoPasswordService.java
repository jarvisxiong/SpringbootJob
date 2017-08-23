package com.stixcloud.cart;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.stixcloud.cart.repo.PasswordUsageRepository;
import com.stixcloud.cart.repo.PromoPasswordRepository;
import com.stixcloud.cart.repo.TransactionPasswordPromoRepository;
import com.stixcloud.cart.util.Utils;
import com.stixcloud.common.config.multitenant.TenantContextHolder;
import com.stixcloud.domain.CartItem;
import com.stixcloud.domain.PasswordUsage;
import com.stixcloud.domain.PromoPassword;
import com.stixcloud.domain.ShoppingCart;
import com.stixcloud.domain.TransactionPasswordPromo;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class PromoPasswordService implements IPromoPasswordService {
  
  private static final Logger logger = LogManager.getLogger(PromoPasswordService.class);

  private static final String INSERT_TYPE = "INSERT";
  private static final String UPDATE_TYPE = "UPDATE";
  private static final String SPLIT_CHAR = ":";

  @Autowired
  private PromoPasswordRepository promoRepo;
  @Autowired
  private PasswordUsageRepository passwordUsageRepo;
  @Autowired
  private TransactionPasswordPromoRepository transactionPasswordPromoRepository;

  @Override
  public void updateNumberUsage(ShoppingCart shoppingCart) {
    List<CartItem> cartItems = shoppingCart.getCartItems();
    OffsetDateTime now = OffsetDateTime.now();
    Long userInfoId = TenantContextHolder.getTenant().getUserInfoId();
    String transactionRefNumber = shoppingCart.getTransactionReferenceNumber();

    List<Long> priceClassIds =
        cartItems.stream().map(CartItem::getPriceClassId).collect(Collectors.toList());
    List<PromoPassword> promoPwds = promoRepo.getPromos(priceClassIds);
    Map<String, Integer> passwordRegexMap = new HashMap<>();
    for (CartItem item : cartItems) {
      if (!StringUtils.isBlank(item.getPromoPassword())) {
        List<PromoPassword> matchedPasswords = promoPwds.stream()
            .filter(x -> x.getPriceClassId().equals(item.getPriceClassId()) && Utils
                .validateRegularExpressions(item.getPromoPassword(), x.getRegexPattern()))
            .collect(Collectors.toList());
        if (matchedPasswords != null && !matchedPasswords.isEmpty()) {
          Long passwordRegexId = matchedPasswords.get(0).getPasswordRegexId();
          Integer numberUsage = passwordRegexMap.get(passwordRegexId);
          if (numberUsage == null) {
            if (!item.getPromoPassword().equals(matchedPasswords.get(0).getPasswordUsage())) {
              passwordRegexMap.put(
                  INSERT_TYPE + SPLIT_CHAR + passwordRegexId + SPLIT_CHAR + item.getPromoPassword()
                      + SPLIT_CHAR + matchedPasswords.get(0).getPromotionPasswordId(),
                  1);
            } else {
              passwordRegexMap.put(
                  UPDATE_TYPE + SPLIT_CHAR + passwordRegexId + SPLIT_CHAR + item.getPromoPassword()
                      + SPLIT_CHAR + matchedPasswords.get(0).getPromotionPasswordId(),
                  1);
            }

          } else {
            if (matchedPasswords.get(0).getPasswordUsage() == null) {
              passwordRegexMap.put(INSERT_TYPE + SPLIT_CHAR + passwordRegexId, 1 + numberUsage);
            } else {
              passwordRegexMap.put(UPDATE_TYPE + SPLIT_CHAR + passwordRegexId, 1 + numberUsage);
            }
          }
        }
      }

    }

    List<TransactionPasswordPromo> txnPasswordPromoList = new ArrayList<>();

    passwordRegexMap.forEach((k, v) -> {
      String[] key = k.split(SPLIT_CHAR);
      String password = key[2];
      Long promoPwdId = Long.valueOf(key[3]);
      if (INSERT_TYPE.equals(key[0])) {
        PasswordUsage usage = new PasswordUsage();
        usage.setPassword(key[2]);
        usage.setPasswordRegexId(Long.valueOf(key[1]));
        usage.setPromoPasswordId(Long.valueOf(key[3]));
        usage.setUsedtimes(v);
        passwordUsageRepo.save(usage);
      } else {
        passwordUsageRepo.updateNumberUsage(Long.valueOf(key[1]), v);
      }
      
      TransactionPasswordPromo txnPasswordPromo = new TransactionPasswordPromo(promoPwdId,
          password, transactionRefNumber, now, now, userInfoId, userInfoId);
      txnPasswordPromoList.add(txnPasswordPromo);
    });

    logger.info("Promo password usage updated: " + passwordRegexMap + ", transactionRefNumber=" + transactionRefNumber);
    transactionPasswordPromoRepository.save(txnPasswordPromoList);
  }
  
}

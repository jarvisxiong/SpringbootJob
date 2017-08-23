package com.sistic.be.cart.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sistic.be.analytics.json.AnalyticsJson;
import com.sistic.be.app.util.MoneyUtil;
import com.sistic.be.cart.model.AddonOptionsLineItem;
import com.sistic.be.cart.model.ProductLineItem;
import com.sistic.be.cart.model.ShoppingCartModel;

/**
 * This is a helper class for ShoppingCartModel object
 * @author jianhong
 *
 */

@Service
public class ShoppingCartModelHelper {
	
	public List<Long> getProductIds(ShoppingCartModel shoppingCartModel) {
		
		List<Long> productIds = new ArrayList<Long>();
		
		List<ProductLineItem> lineItems = shoppingCartModel.getLineItemList();
		for (ProductLineItem lineItem : lineItems) {
			Long productId = lineItem.getProduct().getProductId();
			productIds.add(productId);
		}
		
		return productIds;
	}
	
	
	public void updateTypeForAddonLineItems(ShoppingCartModel shoppingCartModel, List<AddonOptionsLineItem> addOnLineItems){
	  // addonLineItems is the list of added donation to shopping cart
	  List<ProductLineItem> items = shoppingCartModel.getLineItemList();
      for (AddonOptionsLineItem addon : addOnLineItems) {
        for (ProductLineItem productLineItem : items) {
          if(addon.getProductID().equals(productLineItem.getProduct().getProductId())){
            productLineItem.setType(addon.getAddonType());
            break;
          }
        }
      }
	}
	
	/**
   * Return list of product id which are not donation.
   * 1. product id is in list of selected product
   * 2. product id is not type of donation
   * @param shoppingCartModel
   * @return
   */
	public List<AddonOptionsLineItem> displayAddons(ShoppingCartModel shoppingCartModel, AddonOptionsLineItem[] addOnLineItems, List<Long> exclusion){
		List<Long> productIds = getProductIds(shoppingCartModel);
		if (exclusion != null && !exclusion.isEmpty()) {
			productIds.addAll(exclusion);
		}
		List<AddonOptionsLineItem> displayAddons = new ArrayList<>();
		List<AddonOptionsLineItem> addons = new ArrayList<>();
		if (addOnLineItems != null) {
			for (AddonOptionsLineItem addon : addOnLineItems) {
				if(!productIds.contains(addon.getProductID())){
					displayAddons.add(addon);
				}else{
					addons.add(addon);
				}
			}
		}
		shoppingCartModel.setAddOnLineItems(displayAddons);
		
		updateTypeForAddonLineItems(shoppingCartModel, addons);
		return addons;
	}
	
	public Long getProductIdByCartItemId(ShoppingCartModel shoppingCartModel, String lineitemid){
	  List<Long> productIds = new ArrayList<Long>();
	  shoppingCartModel.getLineItemList().stream().filter(e -> lineitemid.equals(e.getCartItemId())).forEach(p -> { productIds.add(p.getProduct().getProductId());});
	  if(productIds.size() != 1){
		  throw new IllegalStateException("There are more than 1 product Ids for cart item id " + lineitemid);
	  }
	  return productIds.get(0);
	}
	
	public List<ProductLineItem> getProductLineItems(ShoppingCartModel shoppingCartModel){
		  List<ProductLineItem> productLineItems = new ArrayList<ProductLineItem>();
		  shoppingCartModel.getLineItemList().stream()
				.forEach(p -> {
					productLineItems.add(p);
				});
		  return productLineItems;
	}
	
	public List<ProductLineItem> getProductLineItems(ShoppingCartModel shoppingCartModel, String cartItemId){
		  List<ProductLineItem> productLineItems = new ArrayList<ProductLineItem>();
		  shoppingCartModel.getLineItemList().stream()
	  			.filter(e -> cartItemId.equals(e.getCartItemId()))
  				.forEach(p -> {
  					productLineItems.add(p);
				});
		  return productLineItems;
	}
	
	public List<AnalyticsJson> createCartAnalytics(List<ProductLineItem> productLineItems) {
		
		List<AnalyticsJson> analytics = new ArrayList<AnalyticsJson>();
		for (ProductLineItem p : productLineItems) {
			AnalyticsJson item = new AnalyticsJson();
			item.setIcc(p.getIcc());
			item.setProductId(p.getProduct().getProductId());
			item.setProductName(p.getProduct().getProductName());
			item.setProductDate(p.getProduct().getProductDate());
			item.setVenue(p.getProduct().getVenue());
			item.setPriceClassCode(p.getPriceclass().getPriceClassCode());
			item.setPriceClassName(p.getPriceclass().getPriceClassName());
			item.setQuantity(p.getProduct().getSeatNo().size());
			item.setUnitPrice(MoneyUtil.getFormattedMonetaryString(p.getUnitPrice()));
			item.setCurrency(p.getUnitPrice().getCurrency());
			
			analytics.add(item);
		}
		
		return analytics;
	}

}

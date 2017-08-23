/**
 * 
 */
package com.sistic.be.cart.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sistic.be.configuration.gson.MoneyTypeAdapterFactory;

/**
 * @author jianhong
 *
 */

public class CommonDeliveryMethod implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6714354359378607783L;
	
	
	private List<DeliveryMethod> deliveryMethodList;
	
	public List<DeliveryMethod> getDeliveryMethodList() {
		return deliveryMethodList;
	}

	public void setDeliveryMethodList(List<DeliveryMethod> deliveryMethodList) {
		this.deliveryMethodList = deliveryMethodList.stream().sorted(Comparator.comparing(DeliveryMethod::getOrder)).collect(Collectors.toList());
	}
	
	@Deprecated
	@JsonIgnore
	public String getDeliveryCodes() {
		String str = "[";
		for (DeliveryMethod deliveryMethod : deliveryMethodList) {
			str += "\"" + deliveryMethod.getCode() + "\",";
		}
		
		return str.substring(0, str.length() - 1 ) + "]" ;
	}
	
	@JsonIgnore
	public String getDeliveryMethodJson() {
		Gson gson = new GsonBuilder().registerTypeAdapterFactory(new MoneyTypeAdapterFactory()).create();
		String jsonInString = gson.toJson(deliveryMethodList);
		
		return jsonInString;
	}
	
	@JsonIgnore
	public DeliveryMethod findDeliveryMethod(String deliveryMethodCode) {
		for (DeliveryMethod deliveryMethod : deliveryMethodList) {
			if (deliveryMethod.getCode().equals(deliveryMethodCode)) {
				return deliveryMethod;
			}
		}
		return null;
	}

}

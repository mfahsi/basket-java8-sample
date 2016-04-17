package com.mfa.shop.domain;

import java.math.BigDecimal;

/**
 * Represents a price entry in the price list.
 * there may be multiple offers for the same fruit with eventually different quantities
 * */
public class ItemPricingOffer {
	
	private String itemId;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal pricePerUnit;
	
	public ItemPricingOffer(String id, BigDecimal price, Integer quantity) {
		super();
		this.itemId=id;
		this.price = price;
		this.quantity = quantity;
		this.pricePerUnit = price.divide(new BigDecimal(quantity));
	}
	
	
	public String getItemId() {
		return itemId;
	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	
}

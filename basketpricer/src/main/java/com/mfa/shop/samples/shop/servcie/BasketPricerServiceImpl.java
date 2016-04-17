package com.mfa.shop.samples.shop.servcie;

import java.math.BigDecimal;
import java.util.Date;

import com.mfa.shop.domain.Basket;

public class BasketPricerServiceImpl implements BasketPricerService
{
	private ItemPricerService itemPricer;
	
	public BasketPricerServiceImpl(ItemPricerService itemPricer) {
		super();
		this.itemPricer = itemPricer;
	}

	@Override
	public BigDecimal priceBasket(Basket basket, Date asOf) throws RuntimeException {
	
		throw new UnsupportedOperationException();
	}
	
}
package com.mfa.shop.samples.shop.servcie;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public class ItemPricerServiceImpl implements ItemPricerService {

	private PriceListService priceList;
	
	public ItemPricerServiceImpl(PriceListService priceList) {
		super();
		this.priceList = priceList;
	}

	@Override
	public Optional<BigDecimal> priceItem(String itemId, int quantity, Date asOf) {
		throw new UnsupportedOperationException();
	}
	
	
	
	
}
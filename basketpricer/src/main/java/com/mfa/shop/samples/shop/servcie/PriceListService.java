package com.mfa.shop.samples.shop.servcie;

import java.util.Collection;
import java.util.Date;

import com.mfa.shop.domain.ItemPricingOffer;

//repository of prices
public interface PriceListService {

	Collection<ItemPricingOffer> getPricesForItem(String itemId, Date asOf);
	Collection<ItemPricingOffer> getPricesForItem(String itemId);
}

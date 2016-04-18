package com.mfa.shop.servcie;

import java.util.Collection;
import com.mfa.shop.domain.ItemPricingOffer;

//repository of prices
public interface PriceListService {

	Collection<ItemPricingOffer> getPricesForItem(String itemId);
}

package com.mfa.shop.servcie;

import java.util.Collection;
import java.util.stream.Collectors;

import com.mfa.shop.domain.ItemPricingOffer;

public class PriceListServiceWithStaticPricesImpl implements PriceListService {

	private Collection<ItemPricingOffer> offers;
	
	public PriceListServiceWithStaticPricesImpl(Collection<ItemPricingOffer> staticData)
	{
		offers = staticData;
	}
	
	public Collection<ItemPricingOffer> getPricesForItem(String itemId)
	{
		return offers.stream().filter(offer -> offer.getItemId().equals(itemId)).collect(Collectors.toList());
	}
}

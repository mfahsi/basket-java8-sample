package com.mfa.shop.samples.shop.servcie;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
		return getPricesForItem(itemId, Calendar.getInstance().getTime());
	}
	
	public Collection<ItemPricingOffer> getPricesForItem(String itemId, Date asOf)
	{
		return offers.stream().filter(offer -> offer.getItemId().equals(itemId)).collect(Collectors.toList());
	}
}

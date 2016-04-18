package com.mfa.shop.servcie;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

import com.mfa.shop.domain.ItemPricingOffer;

//price a given quantity of an item using the offers available in a price list service
public class ItemPricerServiceImpl implements ItemPricerService {

	private PriceListService priceList;
	
	public ItemPricerServiceImpl(PriceListService aPriceList) {
		super();
		this.priceList = aPriceList;
	}

	@Override
	public Optional<BigDecimal> priceItem(String itemId, int quantity) {
		Stream<ItemPricingOffer> offers = priceList.getPricesForItem(itemId).stream()
				.sorted((x,y)-> x.getPricePerUnit().compareTo(y.getPricePerUnit())) ;

		int quantityRemaining = quantity;
		BigDecimal totalPrice = new BigDecimal(0);
		Iterator<ItemPricingOffer> it = offers.iterator();
		while(quantityRemaining >0 && it.hasNext())
		{
			ItemPricingOffer offer = it.next();
			if(quantityRemaining >= offer.getQuantity())
			{
				int nbOfOffersToTake = quantityRemaining / offer.getQuantity();
				BigDecimal priceOfThisQuantity = offer.getPrice().multiply(new BigDecimal(nbOfOffersToTake));
				totalPrice = totalPrice.add(priceOfThisQuantity);
				quantityRemaining -= nbOfOffersToTake * offer.getQuantity(); 
			}
		}
		if(quantityRemaining == 0){
			return Optional.of(totalPrice);
		}else{
			return Optional.empty();
		}
	}
	
}
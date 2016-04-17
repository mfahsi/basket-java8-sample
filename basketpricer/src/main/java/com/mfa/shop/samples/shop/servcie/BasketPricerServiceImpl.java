package com.mfa.shop.samples.shop.servcie;

import java.math.BigDecimal;
import java.util.Optional;

import com.mfa.shop.domain.Basket;

public class BasketPricerServiceImpl implements BasketPricerService
{
	private ItemPricerService itemPricer;
	
	public BasketPricerServiceImpl(ItemPricerService itemPricer) {
		super();
		this.itemPricer = itemPricer;
	}

	@Override
	public BigDecimal priceBasket(Basket basket) throws RuntimeException {
			
		Optional<BigDecimal> result = basket.groupQuantitiesByItem().stream()
		.map(item ->  itemPricer.priceItem(item.getItemId(), item.getQuantity()))
		.reduce(Optional.of(new BigDecimal(0)), (sum, p) -> sum(sum,p),	 (sum, sum2) -> sum(sum,sum2));
		
		if(result.isPresent()){
			return result.get();
		}else
		{
			throw new RuntimeException("System was unable to compute basket value");
		}
		
	}
	
	 private static Optional<BigDecimal> sum(Optional<BigDecimal> a, Optional<BigDecimal> b){
			
		return a.isPresent() &&b.isPresent() ? Optional.of(a.get().add(b.get())) : Optional.empty();
			
	}
	
}
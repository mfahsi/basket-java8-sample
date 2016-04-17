package com.mfa.shop.samples.shop.servcie;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

public interface ItemPricerService {
	
	
	/**
	 * Calculate the price of a quantity of an item. may apply promotions (if implemented).
	 * May
	 * @param itemId identifier of the item/product
	 * @param quantity amount of items to price (countables)
	 * @param asOf : used to choose price
	 * @return price for the given quantity as per date, or None if unable to find the price
	 */
	Optional<BigDecimal> priceItem(String itemId, int quantity, Date asOf);

}

package com.mfa.shop.servcie;

import java.math.BigDecimal;
import java.util.Date;

import com.mfa.shop.domain.Basket;

public interface BasketPricerService {
	
	/**
	 * @param basket the basket to price
	 * @return basket total price
	 * @throws RuntimeException if unable to price any item
	 */
	BigDecimal priceBasket(Basket basket) throws RuntimeException;

}

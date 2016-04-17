package com.mfa.shop.samples.shop.servcie;

import java.math.BigDecimal;
import java.util.Date;

import com.mfa.shop.domain.Basket;

public interface BasketPricerService {
	
	BigDecimal priceBasket(Basket basket, Date asOf) throws RuntimeException;

}

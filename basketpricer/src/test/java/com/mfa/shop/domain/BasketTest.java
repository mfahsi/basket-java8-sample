package com.mfa.shop.domain;

import org.junit.Assert;
import org.junit.Test;


public class BasketTest {

	@Test
	public void test_count_basket_empty() {
		Basket basket = new Basket();
		Assert.assertTrue(basket.count("Bananas") == 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void test_add_null_to_basket_should_throw_illegalArgument_exception() {
		Basket basket = new Basket();
		basket.addToBasket(null);
	}

	@Test
	public void test_addToBasket_and_count_basket_not_empty() {
		Basket basket = new Basket();
		basket.addToBasket("Orange");
		basket.addToBasket("Apple");
		basket.addToBasket("Mango");
		basket.addToBasket("Apple");
		Assert.assertTrue(basket.count("Banana") == 0);
		Assert.assertTrue(basket.count("Apple") == 2);
		Assert.assertTrue(basket.count("Orange") == 1);
	}
}

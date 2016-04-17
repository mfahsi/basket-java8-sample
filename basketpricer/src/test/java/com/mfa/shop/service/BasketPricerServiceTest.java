package com.mfa.shop.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mfa.shop.domain.Basket;
import com.mfa.shop.domain.ItemPricingOffer;
import com.mfa.shop.samples.shop.servcie.BasketPricerService;
import com.mfa.shop.samples.shop.servcie.BasketPricerServiceImpl;
import com.mfa.shop.samples.shop.servcie.ItemPricerService;
import com.mfa.shop.samples.shop.servcie.ItemPricerServiceImpl;
import com.mfa.shop.samples.shop.servcie.PriceListService;

@RunWith(MockitoJUnitRunner.class)
public class BasketPricerServiceTest {
	
	@Mock
	PriceListService priceList;
	
	@InjectMocks
	ItemPricerService itemPricer = new ItemPricerServiceImpl(priceList); 
	
	@InjectMocks
	BasketPricerService basketPricer = new BasketPricerServiceImpl(itemPricer) ; 
	
	@Before
	public void setUp()
	{
		// define unit price for Orange, and a discounted price for 2 oranges
		when(priceList.getPricesForItem("Orange", anyObject())).thenReturn(Arrays.asList(new ItemPricingOffer("Orange",new BigDecimal(0.8),1), new ItemPricingOffer("Orange",new BigDecimal(1), 2)));
		when(priceList.getPricesForItem("Apple", anyObject())).thenReturn(Arrays.asList(new ItemPricingOffer("Apple",new BigDecimal(0.5),1)));
	}
	
	@Test
	public void should_price_item_correctly()
	{
		Basket basket = new Basket("Orange");
		assertEquals("1 oranges for worth 0.8 ", 0.8, basketPricer.priceBasket(basket, Calendar.getInstance().getTime()));
	
	}
	
	@Test
	public void should_price_item_choose_best_price_offer_correctly()
	{
		Basket basket = new Basket("Orange","Orange","Apple");
		assertEquals("3 oranges are priced 2 for 1 £ plus third one for  0.8 + 0.5 for an apple ", 2.3,  basketPricer.priceBasket(basket, Calendar.getInstance().getTime()));
	}

	@Test(expected=RuntimeException.class)
	public void should_not_return_value_if_no_price_found()
	{
		// Pear price not found !!
		Basket basket = new Basket("Orange","Pear");
		basketPricer.priceBasket(basket, Calendar.getInstance().getTime());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_not_return_value_if_item_null()
	{
		Basket basket = new Basket(null);
		basketPricer.priceBasket(basket, Calendar.getInstance().getTime());
	}
}

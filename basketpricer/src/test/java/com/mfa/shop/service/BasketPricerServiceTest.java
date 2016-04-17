package com.mfa.shop.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
	
	ItemPricerService itemPricer; 
	
	BasketPricerService basketPricer ; 
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
		// define unit price for Orange, and a discounted price for 2 oranges
		when(priceList.getPricesForItem("Orange")).thenReturn(Arrays.asList(new ItemPricingOffer("Orange",new BigDecimal("0.8"),1), new ItemPricingOffer("Orange",new BigDecimal("1"), 2)));
		when(priceList.getPricesForItem("Apple")).thenReturn(Arrays.asList(new ItemPricingOffer("Apple",new BigDecimal("0.5"),1)));
		when(priceList.getPricesForItem(null)).thenReturn(Arrays.asList());
		itemPricer = new ItemPricerServiceImpl(priceList); 
		basketPricer = new BasketPricerServiceImpl(itemPricer); 
	}
	
	@Test
	public void should_price_item_correctly()
	{
		Basket basket = new Basket("Apple");
		assertEquals("1 Apple for worth 0.5 ", new BigDecimal("0.5"), basketPricer.priceBasket(basket));
	
	}
	
	@Test
	public void should_price_item_choose_best_price_offer_correctly()
	{
		Basket basket = new Basket("Orange","Orange","Apple","Orange");
		assertEquals("3 oranges are priced 2 for 1 £ plus third one for  0.8 + 0.5 for an apple ", new BigDecimal("2.3"),  basketPricer.priceBasket(basket));
	}

	@Test(expected=RuntimeException.class)
	public void should_not_return_value_if_no_price_found()
	{
		// Pear price not found !!
		Basket basket = new Basket("Orange","Pear");
		basketPricer.priceBasket(basket);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_not_return_value_if_item_null()
	{
		Basket basket = new Basket(null);
		basketPricer.priceBasket(basket);
	}
}

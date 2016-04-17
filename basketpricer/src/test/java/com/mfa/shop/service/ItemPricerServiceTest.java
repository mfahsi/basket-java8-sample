package com.mfa.shop.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mfa.shop.domain.ItemPricingOffer;
import com.mfa.shop.samples.shop.servcie.ItemPricerService;
import com.mfa.shop.samples.shop.servcie.ItemPricerServiceImpl;
import com.mfa.shop.samples.shop.servcie.PriceListService;

@RunWith(MockitoJUnitRunner.class)
public class ItemPricerServiceTest {
	
	@Mock
	PriceListService priceList;
	
	@Test
	public void should_price_item_correctly()
	{
		// define unit price for Orange, we may have other offers in the futur
		when(priceList.getPricesForItem("Orange")).thenReturn(Arrays.asList(new ItemPricingOffer("Orange",new BigDecimal(0.8),1)));

		ItemPricerService pricer = new ItemPricerServiceImpl(priceList); 
		
		assertEquals("2 oranges are worth 0.8 * 2", 1.6,  pricer.priceItem("Orange", 2).get() );
	
	}
	
	@Test
	public void should_price_item_choose_best_price_offer_correctly()
	{
		// define unit price for Orange 0.8, and a discounted price of 1 for 2 oranges
		when(priceList.getPricesForItem("Orange")).thenReturn(Arrays.asList(new ItemPricingOffer("Orange",new BigDecimal(0.8),1), new ItemPricingOffer("Orange",new BigDecimal(1), 2)));
		ItemPricerService pricer = new ItemPricerServiceImpl(priceList); 
		assertEquals("3 oranges are priced 2 for 1 £ plus third one for  0.8 ", 1.8,  pricer.priceItem("Orange", 3).get() );
	
	}

	@Test
	public void should_not_return_value_if_no_price_found()
	{
		// define unit price for Orange 0.8, and a discounted price of 1 for 2 oranges
		when(priceList.getPricesForItem("Orange")).thenReturn(Collections.emptyList());
		ItemPricerService pricer = new ItemPricerServiceImpl(priceList); 
		assertFalse("No price available", pricer.priceItem("Orange", 3).isPresent() );
	
	}
	
	@Test
	public void should_not_return_value_if_item_null()
	{
		// define unit price for Orange 0.8, and a discounted price of 1 for 2 oranges
		when(priceList.getPricesForItem(null)).thenReturn(Collections.emptyList());
		ItemPricerService pricer = new ItemPricerServiceImpl(priceList); 
		assertFalse("No price available", pricer.priceItem(null, 3).isPresent() );
	
	}
}

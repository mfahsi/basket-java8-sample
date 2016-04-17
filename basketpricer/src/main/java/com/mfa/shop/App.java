package com.mfa.shop;

import java.math.BigDecimal;
import java.util.Arrays;

import com.mfa.shop.domain.Basket;
import com.mfa.shop.domain.ItemPricingOffer;
import com.mfa.shop.samples.shop.servcie.BasketPricerService;
import com.mfa.shop.samples.shop.servcie.BasketPricerServiceImpl;
import com.mfa.shop.samples.shop.servcie.ItemPricerServiceImpl;
import com.mfa.shop.samples.shop.servcie.PriceListService;
import com.mfa.shop.samples.shop.servcie.PriceListServiceWithStaticPricesImpl;

/**
 * Main app
 *
 */
public class App 
{
    public static void main( String[] args )
    {
            
    	//could externalyse in a file
        PriceListService prices = new PriceListServiceWithStaticPricesImpl(Arrays.asList(
        			new ItemPricingOffer("Banana",new BigDecimal("0.2"),1),
        			new ItemPricingOffer("Orange",new BigDecimal("0.8"),1), 
        			new ItemPricingOffer("Orange",new BigDecimal("1"), 2),//discount offer for 2 oranges
        			new ItemPricingOffer("Apple",new BigDecimal("0.3"),1),
        			new ItemPricingOffer("Lemon",new BigDecimal("0.25"),1),
        			new ItemPricingOffer("Peache",new BigDecimal("0.5"),1)
        			));
        BasketPricerService service = new BasketPricerServiceImpl(new ItemPricerServiceImpl(prices));
        
        Basket basket = new Basket(args);
        try {
        System.out.println("Your Basket Price is " +service.priceBasket(basket)+" £");
        }catch(IllegalArgumentException exc)
        {
        	System.out.println("Data input is invalide");
        	exc.printStackTrace();
        }
        catch(RuntimeException e)
        {
        	 System.out.println("System was unable to price one or few elements in the basket ");
        	 e.printStackTrace();
        }
        		
    }
}

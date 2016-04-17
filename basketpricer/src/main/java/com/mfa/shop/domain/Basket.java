package com.mfa.shop.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Basket {

	private List<BasketItem> basketItems = new ArrayList<BasketItem>();
	
	public Basket()
	{
		
	}
	public Basket(String... items)
	{
		if (items == null)
			throw new IllegalArgumentException(
					"Null is not a valide basket item");
		for (String item : items) {
			addToBasket(item);
		}
	}
	
	

	public void addToBasket(String item) {
		if (item == null) {
			throw new IllegalArgumentException(
					"Null is not a valide basket item");
		} else {
			Optional<BasketItem> items = basketItems.stream()
					.filter(element -> element.itemId.equals(item)).findAny();

			if (items.isPresent()) {
				items.get().quantity += 1;//side effect
			} else {
				basketItems.add(new BasketItem(item, 1));
			}
		}

	}

	public Integer count(String item) {
		return basketItems
				.stream()
				.filter(e -> e.itemId.equals(item))
				.reduce(0, (sum, p) -> sum += p.quantity,
						(sum1, sum2) -> sum1 + sum2);

	}

	public Collection<BasketItem> groupQuantitiesByItem() {
		Map<String,List<BasketItem>> quantitiesPerProduct = basketItems.stream().collect(
				Collectors.groupingBy(p -> p.getItemId()));
		
		return quantitiesPerProduct
				.entrySet()
				.stream()
				.map(e-> new BasketItem((String)e.getKey(), e.getValue().stream().reduce(0, (sum, p) -> sum += p.quantity,
						(sum1, sum2) -> sum1 + sum2))).collect(Collectors.toList());
		
	}

	public class BasketItem {
		private String itemId;
		private Integer quantity;

		public BasketItem(String itemId, Integer quantity) {
			super();
			this.itemId = itemId;
			this.quantity = quantity;
		}

		public String getItemId() {
			return itemId;
		}

		public Integer getQuantity() {
			return quantity;
		}

	}
	
	

}

package costexplorer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


//DiscountStrategy Interface
interface DiscountStrategy {
	double applyDiscount(double originalPrice);
}

//PercentageDiscount implementation
class PercentageDiscount implements DiscountStrategy {
	private double discountPercentage;

	public PercentageDiscount(double discountPercentage) {
	 this.discountPercentage = discountPercentage;
	}

	@Override
	public double applyDiscount(double originalPrice) {
		return originalPrice * (1 - discountPercentage / 100);
	}
}

//NoDiscount implementation
class FlatDiscount implements DiscountStrategy {
	private double discount;
	
	public FlatDiscount(double discount) {
		 this.discount = discount;
	}
	
	@Override
	public double applyDiscount(double originalPrice) {
		return originalPrice-discount;
	}
}

public class DiscountService {
	private Map<String, DiscountStrategy> discountMap;
	private DiscountFactory discountFactory;
		
	public DiscountService(List<Discount> discounts) {
		this.discountMap = new HashMap<>();
		this.discountFactory = new DiscountFactory();
		for (Discount discount : discounts) {
			discountMap.put(discount.getCode(), discountFactory.createDiscountStrategy(discount.getType(), discount.getValue()));
		}
	}
	
	//GetDiscountStrategy
	public DiscountStrategy getDiscountStrategy(String discountCode) {
		return discountMap.getOrDefault(discountCode, null);
	}
}
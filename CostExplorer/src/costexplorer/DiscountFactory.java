package costexplorer;

public class DiscountFactory {
	public DiscountStrategy createDiscountStrategy(DiscountType type,Integer value) {
		// check if passing the object is fine or just the type is sufficient
		switch(type) {
			case PERCENT:
				return new PercentageDiscount(value);
			case AMOUNT:
				return new FlatDiscount(value);
			default:
				throw new IllegalArgumentException("Unexpected value: " + type);
		}	
	}

}

package costexplorer;

enum DiscountType {
	PERCENT,
	AMOUNT
}

public class Discount {
	private DiscountType type;
	private String code;
	private Integer value;
	
	public Discount(DiscountType type, String code, Integer value) {
		this.type = type;
		this.code = code;
		this.value = value;
	}

	public DiscountType getType() {
		return type;
	}

	public String getCode() {
		return code;
	}

	public Integer getValue() {
		return value;
	}
}

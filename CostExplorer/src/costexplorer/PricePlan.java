package costexplorer;

public class PricePlan {
	private String planId;
	private Double monthlyCost;
	
	public PricePlan(String planId,Double cost) {
		this.planId=planId;
		this.monthlyCost=cost;
	}
	
	public Double getMonthlyCost() {
		return this.monthlyCost;
	}
	
	public String getPlanId() {
		return this.planId;
	}

}

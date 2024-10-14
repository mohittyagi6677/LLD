package costexplorer;

import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostService {
	private PricingPlanService pricePlanService;
	private DiscountService discountService;
	public CostService(PricingPlanService pricePlanService,DiscountService discountService) {
		this.pricePlanService=pricePlanService;
		this.discountService=discountService;
	}
	
	

	public List<Double> getMonthlyCostList(Customer customer) {
		List<Double> monthlyCost = new ArrayList<Double>();
		for(Month month: Month.values()) {
			YearMonth yearMonth = YearMonth.of(2024, month);
			Double cost=0.0;
			for(Product product: customer.getProducts()) {
				if(product.getSubscription().isSubscriptionValidForMonth(yearMonth)) {
					DiscountStrategy discountStrategy=discountService.getDiscountStrategy(product.getSubscription().getDiscountCode());
							cost+=	discountStrategy.applyDiscount((pricePlanService.getPlanForPlanId(product.getSubscription().getPlanID()).getMonthlyCost()));
				}
			}
			monthlyCost.add(cost);
		}
		return monthlyCost;
	}


	public Double getAnnualCost(Customer customer) {
		List<Double> monthlyList = getMonthlyCostList(customer);
		Double annualCost = 0.0;
		for(Double monthCost: monthlyList) {
			annualCost+=monthCost;
		}
		return annualCost;
	}
	
	public Map<Product,Double> getYearlyCostPerProduct(Customer customer) {
		Map<Product,Double> map = new HashMap<Product, Double>();
		for(Product product: customer.getProducts()) {
			Double yearlyCostForProduct=0.0;
			for(Month month:Month.values()){
				YearMonth yearMonth = YearMonth.of(2024, month);
				if(product.getSubscription().isSubscriptionValidForMonth(yearMonth)) {
					yearlyCostForProduct+=discountService.getDiscountStrategy(product.getSubscription().getDiscountCode()).applyDiscount((pricePlanService.getPlanForPlanId(product.getSubscription().getPlanID()).getMonthlyCost()));
				}
			}
			map.put(product, yearlyCostForProduct);	
			System.out.println("Yearly cost of product: "+product.getName()+" price: "+yearlyCostForProduct);
		}
		return map;
	}

}

package costexplorer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CostExplorerDemo {
	
	public static void main(String[] args) {
		PricingPlanService pricingPlanService = new PricingPlanService();
		pricingPlanService.addPlan("A", 30.0);
		pricingPlanService.addPlan("B", 40.0);
		pricingPlanService.addPlan("C", 50.0);
		
		DiscountService discountService = new DiscountService(Arrays.asList(new Discount(DiscountType.AMOUNT, "FLAT10", 10),
				new Discount(DiscountType.PERCENT, "5%", 5)));
		
		Subscription subscriptionWithPlanA = new Subscription(LocalDate.of(2024, 3, 28), "A",2,"FLAT10");
		Subscription subscriptionWithPlanB = new Subscription(LocalDate.of(2024, 7, 28), "B",1,"5%");
		
		Product productA = new Product("1", "First", subscriptionWithPlanA);
		Product productB = new Product("2", "Second", subscriptionWithPlanB);
		
		Customer customerWithProductAB = new Customer("customerA", "Mohit",List.of(productA,productB));
		
		CostExplorer costExplorer = new CostExplorer(pricingPlanService,discountService);
		costExplorer.getAnnualCost(customerWithProductAB);
		costExplorer.getMonthlyCostList(customerWithProductAB);
		costExplorer.getYearlyCostPerProduct(customerWithProductAB);
		
	}

}

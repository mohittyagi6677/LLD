package costexplorer;
import java.util.*;

interface ICostExplorer{
	List<Double> getMonthlyCostList(Customer customer);
	Double getAnnualCost(Customer customer);
	
	// 2nd follow up
	Map<Product,Double> getYearlyCostPerProduct(Customer customer);
}

public class CostExplorer implements ICostExplorer{
	private CostService costService;
	public CostExplorer(PricingPlanService pricePlanService,DiscountService discountService) {
		this.costService=new CostService(pricePlanService,discountService);
		
	}

	@Override
	public List<Double> getMonthlyCostList(Customer customer) {
		List<Double> monthlyCostList = costService.getMonthlyCostList(customer);
		System.out.println(monthlyCostList);
		return monthlyCostList;
	}

	@Override
	public Double getAnnualCost(Customer customer) {
		Double annualCost= costService.getAnnualCost(customer);
		System.out.println(annualCost);
		return annualCost;
	}

	@Override
	public Map<Product, Double> getYearlyCostPerProduct(Customer customer) {
		return costService.getYearlyCostPerProduct(customer);
	}

}

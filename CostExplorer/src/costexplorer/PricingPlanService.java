package costexplorer;
import java.util.*;

public class PricingPlanService {
	Map<String,PricePlan> planIdToPlanMap;
	public PricingPlanService() {
		planIdToPlanMap=new HashMap<String, PricePlan>();
	}
	
	public void addPlan(String planId,Double pricePerMonth) {
		planIdToPlanMap.put(planId, new PricePlan(planId, pricePerMonth));
	}
	
	public PricePlan getPlanForPlanId(String planId) {
		return this.planIdToPlanMap.get(planId);
	}
	

}

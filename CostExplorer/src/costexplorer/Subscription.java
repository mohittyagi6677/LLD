package costexplorer;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class Subscription {
	LocalDate stateDate;
	String planID;
	
	//1st follow up
	int trialPeriodInMonths;
	
	//3rd follow up
	String discountCode;
	
	
	public Subscription(LocalDate stateDate, String planID,int trialPeriodInMonths,String discountCode) {
		this.stateDate = stateDate;
		this.planID = planID;
		this.trialPeriodInMonths=trialPeriodInMonths;
		this.discountCode=discountCode;
	}
	
	public LocalDate getStateDate() {
		return stateDate;
	}
	public String getPlanID() {
		return planID;
	}
	
	public boolean isSubscriptionValidForMonth(YearMonth month) {
		YearMonth startYearMonth = YearMonth.of(stateDate.getYear(), stateDate.getMonth());
		return month.equals(startYearMonth.plusMonths(trialPeriodInMonths)) || month.isAfter(startYearMonth.plusMonths(trialPeriodInMonths));
	}
	
	public String getDiscountCode() {
		return this.discountCode;
	}
	
	

}

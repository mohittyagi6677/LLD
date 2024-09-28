package bike.rental.model;

import java.util.Date;

public class RentItem {
	Vehicle vehicle;
	Customer customer;
	boolean isReturned;
	Date startDate;
	Date endDate;
	
	public RentItem(Vehicle vehicle, Customer customer, Date startDate, Date endDate) {
		super();
		this.vehicle = vehicle;
		this.customer = customer;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public boolean isReturned() {
		return isReturned;
	}
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}

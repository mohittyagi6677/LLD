package bike.rental.service;

import java.util.*;

import bike.rental.model.*;
import bike.rental.util.LoggerUtil;

public class RentalStore {
	List<Customer> customers;
	List<Vehicle> vehicles;
	List<RentItem> rentedItems;
	static RentalStore rentalStore=null;
	
	private RentalStore() {
		this.customers=new ArrayList<Customer>();
		this.vehicles=new ArrayList<Vehicle>();
		this.rentedItems=new ArrayList<RentItem>();
	}
	public static RentalStore getInstance() {
		if(rentalStore==null) {
			synchronized (RentalStore.class) {
				if(rentalStore==null) {
					rentalStore=new RentalStore();
				}
				return rentalStore;
			}
		} else {
			return rentalStore;
		}
	}
	
	public List<Bike> getTotalBikes(){
		List<Bike> bikes = new ArrayList<>();
		for(Vehicle vehicle: vehicles) {
			if(VehicleType.BIKE.equals(vehicle.getType())) {
				bikes.add((Bike)vehicle);
			}
		}
		return bikes;
	}
	public List<RentItem> getRentedVehicles(){
		List<RentItem> rentedItems = new ArrayList<>();
		for(RentItem rentItem: rentedItems) {
			if(!rentItem.isReturned()) {
				rentedItems.add(rentItem);
			}
		}
		return rentedItems;

	}
	
	public Vehicle addSmallBikeToInventory() {
		SmallBike smallBike = new SmallBike(10);
		vehicles.add(smallBike);
		return smallBike;	
	}
	public Vehicle addLargeBikeToInventory() {
		LargeBike largeBike = new LargeBike(20);
		vehicles.add(largeBike);
		return largeBike;	
	}
	public Customer addCustomer(String name) {
		Customer customer = new Customer(name);
		customers.add(customer);
		return customer;
	}
	public Vehicle rentSmallBikeToCustomer(Customer customer,Date startDate,Date endDate) throws InterruptedException {
		LoggerUtil.log("Trying to rent small bike to customer",customer.getName());
		for(Vehicle vehicle: vehicles) {
			if(VehicleType.BIKE.equals(vehicle.getType()) && vehicle.isAvailable() && !vehicle.isRented()) {
				synchronized (vehicle) {
					if(!vehicle.isRented()) {
						Thread.sleep(1000); // dummy logic to simulate some task in booking
						vehicle.setRented(true);
						RentItem rentItem = new RentItem(vehicle, customer, startDate, endDate);
						rentedItems.add(rentItem);
						LoggerUtil.log("Rented a small bike to customer",customer.getName());
						return vehicle;
					}
				}
			}
		}
		LoggerUtil.log("Could not rent small bike to customer",customer.getName());
		return null;
	}
	
	public Integer returnRentedVehicle(Vehicle vehicle) {
		for(RentItem rentItem: rentedItems) {
			if(rentItem.getVehicle().equals(vehicle)) {
				int days = (int)(new Date().getTime()-rentItem.getStartDate().getTime())/(1000*3600*24);
				if(days==0) {
					days++;
				}
				rentItem.setReturned(true);
				vehicle.setRented(false);
				int price= rentItem.getVehicle().getPricePerDay()*days;
				LoggerUtil.log("Vehicle returned to store vehicle type",vehicle.getType(),"customer",rentItem.getCustomer().getName(),"price",price);
				return price;
			}
		}
		LoggerUtil.log("Vehicle was not rented in first place");
		return null;
	}
	
	public List<Vehicle> getActiveRentedVehiclesForCustomer(Customer customer){
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(RentItem rentItem: rentedItems) {
			if(rentItem.getCustomer().equals(customer) && !rentItem.isReturned()) {
				vehicles.add(rentItem.getVehicle());
			}
		}
		LoggerUtil.log("Total rented vehciles by customer",customer.getName(),"are",vehicles.size());
		return vehicles;
		
	}
	
	
	

}

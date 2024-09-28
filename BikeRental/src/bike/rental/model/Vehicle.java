package bike.rental.model;

public abstract class Vehicle {
	int pricePerDay;
	VehicleType type;
	boolean isAvailable=true;
	boolean isRented=false;
	public Vehicle(int price,VehicleType type) {
		this.pricePerDay=price;
		this.type=type;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerHour) {
		this.pricePerDay = pricePerHour;
	}
	public VehicleType getType() {
		return type;
	}
	public void setType(VehicleType type) {
		this.type = type;
	}
	
	
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public boolean isRented() {
		return isRented;
	}
	public void setRented(boolean isRented) {
		this.isRented = isRented;
	}
	
	

}

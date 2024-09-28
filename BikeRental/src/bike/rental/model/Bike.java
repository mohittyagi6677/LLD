package bike.rental.model;
enum Size{
	SMALL,LARGE,XLARGE;
}
public abstract class Bike extends Vehicle{
	Size size;
	public Bike(int price,Size size) {	
		super(price, VehicleType.BIKE);
		this.size=size;
	}

}


package bike.rental.model;

enum ScooterType{
	GAS,ELECTRIC
}
public abstract class Scooter extends Vehicle{
	ScooterType type;

	public Scooter(int price,ScooterType type) {
		super(price, VehicleType.SCOOTER);
		this.type=type;
	}

}

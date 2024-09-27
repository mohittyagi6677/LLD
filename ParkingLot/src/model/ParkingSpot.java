package model;

public class ParkingSpot {
	int id;
	VehicleType vehicleType;
	boolean isAvailable;
	
	public ParkingSpot(int id,VehicleType veh) {
		this.id=id;
		this.vehicleType=veh;
		this.isAvailable=true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}

package model;

import java.util.*;

public class ParkingFloor {
	int floorNumber;
	List<ParkingSpot> spots;
	
	public ParkingFloor(int floor) {
		floorNumber=floor;
		spots = new ArrayList<>();
	}
	
	public void addParkingSpot(VehicleType vehicle) {
		spots.add(new ParkingSpot(spots.size(), vehicle));
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public List<ParkingSpot> getSpots() {
		return spots;
	}

	public void setSpots(List<ParkingSpot> spots) {
		this.spots = spots;
	}
	
	

}

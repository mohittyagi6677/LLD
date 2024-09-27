package service;

import model.*;

public class Application {
	
	public static void main(String[] args) {
		ParkingLot parkingLot = new ParkingLot();
		ParkingFloor floor1 = parkingLot.addParkingFloor();
		ParkingFloor floor2 = parkingLot.addParkingFloor();
		parkingLot.addParkingSpotToFloor(floor1, VehicleType.BIKE);
		parkingLot.addParkingSpotToFloor(floor1, VehicleType.CAR);
		parkingLot.addParkingSpotToFloor(floor2, VehicleType.BIKE);
		
		Vehicle car1 = new Car();
		Vehicle car2 = new Car();
		
		Vehicle bike1 = new Bike();
		Vehicle bike2 = new Bike();
		Vehicle bike3 = new Bike();
		
		parkingLot.checkInVehicle(car1);
		parkingLot.checkInVehicle(car2);
		parkingLot.checkInVehicle(bike1);
		parkingLot.checkInVehicle(bike2);
		
		parkingLot.checkOutVehicle(car1);
		parkingLot.checkInVehicle(car2);
		
		parkingLot.checkInVehicle(bike3);
		
		parkingLot.checkOutVehicle(bike1);
		parkingLot.checkOutVehicle(bike2);
		
	}

}

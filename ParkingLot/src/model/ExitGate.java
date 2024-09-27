package model;

import java.util.*;


public class ExitGate {
	Map<VehicleType,Integer> priceMapPerHour=new HashMap<>();
	public ExitGate() {
		priceMapPerHour.put(VehicleType.BIKE, 10);
		priceMapPerHour.put(VehicleType.CAR, 30);
		priceMapPerHour.put(VehicleType.TRUCK, 50);
	}
	
	public double checkOutVehicle(Vehicle vehicle) {
		vehicle.parkingTicket.parkingSpot.isAvailable=true;
		int hoursSpent = (int) ((new Date().getTime() - vehicle.parkingTicket.entryTime.getTime())/3600);
		if(hoursSpent==0) {
			hoursSpent++;
		}
		Double price= (double) (priceMapPerHour.get(vehicle.parkingTicket.getParkingSpot().vehicleType)*hoursSpent);
		System.out.println("Checking out vehicle: "+vehicle.vehicleType+" with spot: "+vehicle.parkingTicket.parkingSpot.id+" price: "+price);
		return price;
	}

}

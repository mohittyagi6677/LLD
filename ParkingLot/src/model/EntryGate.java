package model;

import java.util.Date;

import service.ParkingLot;

public class EntryGate {
	
	public ParkingTicket checkInVehicle(ParkingLot parkingLot,Vehicle vehicle) {
		for(ParkingFloor floor: parkingLot.getFloors()) {
			for(ParkingSpot spot: floor.getSpots()) {
				if(spot.vehicleType.equals(vehicle.vehicleType) && spot.isAvailable) {
					spot.isAvailable=false;
					ParkingTicket ticket= new ParkingTicket(new Date(),spot);
					vehicle.checkIn(ticket);
					return ticket;
				}
			}
		}
		System.out.println("Unable to check in vehicle since no spot is available in any floor");
		return null;
	}

}

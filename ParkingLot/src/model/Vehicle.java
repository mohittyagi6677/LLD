package model;

public class Vehicle {
	VehicleType vehicleType;
	ParkingTicket parkingTicket;
	
	public void checkIn(ParkingTicket ticket) {
		System.out.println("Checking IN vehicle: "+vehicleType+" with spot: "+ticket.parkingSpot.id);
		this.parkingTicket=ticket;
	}

}

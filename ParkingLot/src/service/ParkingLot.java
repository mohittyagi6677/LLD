package service;

import java.util.*;

import model.EntryGate;
import model.ExitGate;
import model.ParkingFloor;
import model.Vehicle;
import model.VehicleType;

public class ParkingLot {
	
	EntryGate entryGate;
	ExitGate exitGate;
	List<ParkingFloor> floors;
	
	public ParkingLot() {
		this.entryGate=new EntryGate();
		this.exitGate=new ExitGate();
		floors=new ArrayList<>();
	}
	
	public ParkingFloor addParkingFloor() {
		ParkingFloor floor= new ParkingFloor(floors.size());
		floors.add(floor);
		return floor;
	}
	
	public void addParkingSpotToFloor(ParkingFloor parkFloor,VehicleType vehType) {
		parkFloor.addParkingSpot(vehType);
	}
	
	public boolean checkInVehicle(Vehicle vehicle) {
		return entryGate.checkInVehicle(this, vehicle)!=null;
		
	}
	
	public double checkOutVehicle(Vehicle Vehicle) {
		return exitGate.checkOutVehicle(Vehicle);
	}

	public EntryGate getEntryGate() {
		return entryGate;
	}

	public void setEntryGate(EntryGate entryGate) {
		this.entryGate = entryGate;
	}

	public ExitGate getExitGate() {
		return exitGate;
	}

	public void setExitGate(ExitGate exitGate) {
		this.exitGate = exitGate;
	}

	public List<ParkingFloor> getFloors() {
		return floors;
	}

	public void setFloors(List<ParkingFloor> floors) {
		this.floors = floors;
	}
	

}

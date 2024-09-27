package model;

import java.util.Date;

public class ParkingTicket {
	ParkingSpot parkingSpot;
	Date entryTime;
	
	public ParkingTicket(Date time,ParkingSpot spot) {
		this.entryTime=time;
		this.parkingSpot=spot;
	}
	public ParkingSpot getParkingSpot() {
		return parkingSpot;
	}
	public void setParkingSpot(ParkingSpot parkingSpot) {
		this.parkingSpot = parkingSpot;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	
	

}

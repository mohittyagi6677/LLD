package bike.rental;

import java.util.Date;

import bike.rental.model.*;
import bike.rental.service.RentalStore;

public class Application {
	static Vehicle rentedVehicleForMohit=null;
	static Vehicle rentedVehicleForTanmay=null;
	
	public static void main(String[] args) throws InterruptedException {
		RentalStore.getInstance().addSmallBikeToInventory();
		Customer mohit=RentalStore.getInstance().addCustomer("Mohit");
		Customer tanmay=RentalStore.getInstance().addCustomer("Tanmay");
		new Thread(()->{
			try {
				rentedVehicleForMohit=RentalStore.getInstance().rentSmallBikeToCustomer(mohit, new Date(), new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		new Thread(()->{
			try {
				rentedVehicleForTanmay=RentalStore.getInstance().rentSmallBikeToCustomer(tanmay, new Date(), new Date());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}).start();
		Thread.sleep(2000);
		RentalStore.getInstance().returnRentedVehicle(rentedVehicleForMohit);
		RentalStore.getInstance().returnRentedVehicle(rentedVehicleForTanmay);
		rentedVehicleForTanmay=RentalStore.getInstance().rentSmallBikeToCustomer(tanmay, new Date(), new Date());
	}

}

package model;

import java.util.ArrayList;
import util.DateTime;

public class Car extends Vehicle{
	
	public Car(String vehicleID, int year, String make, String model, int passengers,
			   String type, Status status, ArrayList<RentalRecord> Records,
			   DateTime lastMaintenance, int rate, String image) {
		super(vehicleID, year, make, model, passengers, type, status, Records, lastMaintenance, rate, image);
	}
	
	public Car(String vehicleID, int year, String make, String model, int passengers,
			   String type, String image) {
		super(vehicleID, year, make, model, passengers, type, image);
	}
	
	public Car(String vehicleID, int year, String make, String model, int passengers,
			   String type, int rate, String image) {
		super(vehicleID, year, make, model, passengers, type, image);
		this.rate = rate;
	}
	
	@Override
	public boolean checkID(String id) {
		if (id.substring(0,2).equals("C_"))
			return true;
		else
			return false;
	}
	
/*	public void performMaintenance() throws CompleteMaintenanceException{
		switch(status) {
			case AVAILABLE:
				System.out.println("Maintenance is being performed now");
			case RENTED:
				System.out.println("The car is currently rented so maintenance cannot be completed");
			case MAINTENANCE:
				System.out.println("The car is already undertaking maintenance");
			default:
				throw new CompleteMaintenanceException(status);
		}
	}*/

	
	@Override
	public void setRate(int rate) {
		int passengers = getPassengers();
		switch(passengers) {
		case 4:
			rate = 78;
			break;
		case 7:
			rate = 113;
			break;
		}
		
	}
	
	public void setLateRate() {
		double lateRate = 0.0;
		if (getRate() == 78) {
			lateRate = 97.5;
		}
		else if (getRate() == 113) {
			lateRate = 141.25;
		}
	}

}
	

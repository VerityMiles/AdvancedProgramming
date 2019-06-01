package model;

import java.util.ArrayList;

import util.DateTime;

public class Van extends Vehicle {
	
	public Van(String vehicleID, int year, String make, String model,
			   String type, Status status, ArrayList<RentalRecord> Records,
			   DateTime lastMaintenance, int rate, String image) {
		super(type, year, make, model, 15, "Van", status, Records, lastMaintenance, 235, image);
	}
	
	public Van(String vehicleID, int year, String make, String model, String type, String image) {
		super(vehicleID, year, make, model, 15, "Van", image);
	}
	
	//still same as vehicle
	public void performMaintenance() throws NotAvailableException{
		if (status == Status.AVAILABLE) {
			System.out.println("This vehicle is available to perform maintenance" + "\n" +
								"Vehicle entering maintenance now");
			//setStatus(Status.MAINTENANCE);
			System.out.println(getStatus());
			DateTime today = new DateTime(); 
			setlastMaintenance(today);
		}
		else
			throw new NotAvailableException(status);
	}

	public DateTime nextMaintenanceDate(DateTime lastMaintenance) {
		return new DateTime(lastMaintenance, 12);
	}

	@Override
	public void rent(String customerID, DateTime rentDate, int numOfRentDay, int rate) throws NotAvailableException, RequiresMaintenanceException {
		super.rent(customerID, rentDate, numOfRentDay, rate);
		DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);
		//check if estimatedReturnDate before next maintenance
		int maintenanceOverlay = util.DateTime.diffDays(estimatedReturnDate, rentDate);
		if (maintenanceOverlay > 0) {
			//System.out.println("Your van is not available to rent as it is required for maintenance");
			throw new RequiresMaintenanceException();
		}
	}
	
	@Override
	public boolean checkID(String id) {
		if (id.substring(0,2).equals("V_"))
			return true;
		else
			return false;
	}

	@Override
	public void setPassengers(int passengers) {
		passengers = 15;
	}
	
	@Override
	public void setRate(int rate) {
		rate = 235;
	}

	@Override
	public String toString() {
		String returnString = super.toString();
		return returnString;
	}
	
	public void setLateRate() {
		double lateRate;
		lateRate = 299.0;
	}

}

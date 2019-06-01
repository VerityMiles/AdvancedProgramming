package model;

import java.util.*;
import util.DateTime;

public abstract class Vehicle {
	
	protected String vehicleID;
	private int year;
	private String make;
	private String model;
	private int passengers;
	private String type;
	protected Status status;
	public ArrayList<RentalRecord> Records;
	private DateTime lastMaintenance;
	protected int rate;
	protected String image;
	
	public Vehicle(String vehicleID, int year, String make, String model, int passengers,
				   String type, Status status, ArrayList<RentalRecord> Records,
				   DateTime lastMaintenance, int rate, String image) {
		this.vehicleID = vehicleID;
		this.year = year;
		this.make = make;
		this.model = model;
		this.passengers = passengers;
		this.type = type;
		this.status = status;
		Records = new ArrayList<RentalRecord>();
		this.lastMaintenance = lastMaintenance;
		this.rate = rate;
		this.image = image;
	}
	
	public Vehicle(String vehicleID, int year, String make, String model, int passengers,
			   	   String type, String image) {
		this.vehicleID = vehicleID;
		this.year = year;
		this.make = make;
		this.model = model;
		this.passengers = passengers;
		this.type = type;
		status = Status.AVAILABLE;
		Records = new ArrayList<RentalRecord>();
		lastMaintenance = null;
		rate = 0;
		image = null;
	}
	
	public Vehicle(String vehicleID, String type) {
		this.vehicleID = vehicleID;
		this.type = type;
	}
	
	public String getVehicleID() {
		return vehicleID;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getPassengers() {
		return passengers;
	}
	
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public DateTime getlastMaintenance() {
		return lastMaintenance;
	}
	
	public void setlastMaintenance(DateTime lastMaintenance) {
		this.lastMaintenance = lastMaintenance;
	}
	
	public int getRate() {
		return rate;
	}
	
	public String toString() {
		String returnString = getVehicleID() + ":" + getYear() + ":" + getMake() + ":" + getModel() + 
							  ":" + getPassengers() + ":";
		if (status.name().equals("AVAILABLE")) {
			returnString += "Available";
		}
		else if (status.name().equals("RENTED")) {
			returnString += "Rented:";
		}
		return returnString;
	}
	
	public String getDetails() {
		String returnString = null;
		String base;
		base = "Vehicle ID:        " + getVehicleID() + "\n" +
		   "Year:              " + getYear() + "\n" +
		   "Make:              " + getMake() + "\n" +
		   "Model:             " + getModel() + "\n" +
		   "Number of seats:   " + getPassengers() + "\n" +
		   "Status:            " + getStatus() + "\n";
		   
		if (type == "Van") {
			base = base + 
					"Last maintenance date: " +  getlastMaintenance() + "\n";
		}
			
		if (Records.isEmpty()) {
			returnString = "RENTAL RECORD:     " + "empty" + "\n";
		}
		else if (Records.size() == 1) {
			returnString = "RENTAL RECORD\n" +
						  "Record ID:             " + Records.get(0).getRecordID() + "\n" +
						  "Rent Date:             " + Records.get(0).getRentDate() + "\n" +
						  "Estimated Return Date  " + Records.get(0).getEstimatedReturnDate() + "\n";
			if (status != Status.RENTED) {
				returnString = returnString + 
						  "Actual Return Date     " + Records.get(0).getActualReturnDate() + "\n" +
						  "Rental Fee:            " + Records.get(0).getRentalFee() + "\n" +
						  "Late Fee:              " + Records.get(0).getLateFee() + "\n";
			}
		}
		else if (Records.size() <= 10){
			for (int i = 0; i <= Records.size()-1; i++) {
				returnString = "RENTAL RECORD\n" + returnString + 
						  "Record ID:             " + Records.get(i).getRecordID() + "\n" +
						  "Rent Date:             " + Records.get(i).getRentDate() + "\n" +
						  "Estimated Return Date  " + Records.get(i).getEstimatedReturnDate() + "\n\n" +
						  "Actual Return Date     " + Records.get(i).getActualReturnDate() + "\n" +
						  "Rental Fee:            " + Records.get(i).getRentalFee() + "\n" +
						  "Late Fee:              " + Records.get(i).getLateFee() + "\n\n";
						}
					}
		return base + returnString;
	}

	public void rent(String customerID, DateTime rentDate, int numOfRentDay, int rate) throws NotAvailableException, RequiresMaintenanceException {
		try{
			if (numOfRentDay < 1) {
			System.out.println("You must rent the car for at least one day");
			}
			if (numOfRentDay > 14) {
				System.out.println("You can't rent a vehicle for more than 14 days");
			}
		}
		catch (Exception e) {
			System.out.println("ERROR!");
		}
		if (status == Status.AVAILABLE) {
			System.out.println("Your vehicle is available");
			DateTime estimatedReturnDate = new DateTime(rentDate, numOfRentDay);
			double rentalFee = numOfRentDay * rate;
			RentalRecord record = new RentalRecord(vehicleID, customerID, rentDate, numOfRentDay, rentalFee);
			record.setRentalFee(rentalFee);
			Records.add(record);
		}
		else {
			throw new NotAvailableException(status);
		}
	}
	
	public void performMaintenance() throws NotAvailableException, CompleteMaintenanceException{
		if (status == Status.AVAILABLE) {
			System.out.println("This vehicle is available to perform maintenance" + "\n" +
								"Vehicle entering maintenance now");
			}
		else
			throw new NotAvailableException(status);
	}
	
	public void completeMaintenance() throws CompleteMaintenanceException{
		if (status == Status.MAINTENANCE) {
			System.out.println("Vehicle in maintenance" + "\n"+
								"Ending maintenance now");
		}
		else {
			throw new CompleteMaintenanceException(status);
		}
	}
	
	public void returnVehicle(DateTime date) throws ReturnException{
		if (status == Status.RENTED) {
			RentalRecord lastRecord = Records.get(Records.size()-1);
			lastRecord.setActualReturnDate(date);
			int daysOver = util.DateTime.diffDays(lastRecord.getActualReturnDate(), lastRecord.getEstimatedReturnDate());
			if (daysOver >= 0) {
				lastRecord.setLateFee(daysOver * lastRecord.getLateFee());
			}
			else
				lastRecord.setLateFee(0);
			status = Status.AVAILABLE;
		}
		else {
			throw new ReturnException();
		}
	}
	
	public abstract void setRate(int rate);
	public abstract void setLateRate();
	public abstract boolean checkID(String id);
	
}

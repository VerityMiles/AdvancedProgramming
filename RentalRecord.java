package model;

import java.sql.Date;

import util.DateTime;

public class RentalRecord {
	
	private String recordID;
	private String customerID;
	private Vehicle vehicle;
	private DateTime rentDate;
	private DateTime estimatedReturnDate;
	private int rentDays;
	private DateTime actualReturnDate;
	private double rentalFee;
	private double lateFee;

	util.DateTime date = new util.DateTime();
	
	public RentalRecord(DateTime rentDate, DateTime estimatedReturnDate, int rentDays, DateTime actualReturnDate,
			double rentalFee, double lateFee, DateTime date) {
		super();
		this.rentDate = rentDate;
		this.estimatedReturnDate = estimatedReturnDate;
		this.rentDays = rentDays;
		this.actualReturnDate = actualReturnDate;
		this.rentalFee = rentalFee;
		this.lateFee = lateFee;
		this.date = date;
	}

	public RentalRecord(String vehicleID, String customerID, DateTime rentDate, int rentDays, double rentalFee) {
		this.recordID = vehicleID + "_" + customerID +"_" + rentDate.getEightDigitDate();
		this.rentDate = rentDate;
		this.rentDays = rentDays;
		this.rentalFee = rentalFee;
		estimatedReturnDate = new DateTime(rentDate, rentDays);
	}
	
	
	public RentalRecord(String vehicleID, String customerID, DateTime rentDate, int rentDays,
			DateTime estimatedReturnDate, DateTime actualReturnDate, int rate, double rentalFee, double lateFee) {
		this.recordID = vehicleID + "_" + customerID + "_" + rentDate.getEightDigitDate();
		this.rentDate = rentDate;
		this.rentDays = rentDays;
		this.rentalFee = rentalFee;
		
	}

	public String getRecordID() {
		return recordID;
	}
	
	public String getCustomerID() {
		return customerID;
	}
	
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	public DateTime getRentDate() {
		return rentDate;
	}
	
	public DateTime getEstimatedReturnDate() {
		return estimatedReturnDate;
	}

	public DateTime getActualReturnDate() {
		return actualReturnDate;
	}

	public double getRentalFee() {
		return rentalFee;
	}
	
	public double getLateFee() {
		return lateFee;
	}

	public void setDate() {
		date.getEightDigitDate();
	}
	
	public void setRentalFee(double fee) {
		rentalFee = fee;
	}
	
	public void setDate(int day, int month, int year) {
		date.setDate(day, month, year);
	}
	
	public void setActualReturnDate(int day, int month, int year) {
		actualReturnDate = new DateTime(day, month, year);
	}
	
	public void setActualReturnDate(DateTime today) {
		actualReturnDate = today;
	}
	
	public void setLateFee(double fee) {
		lateFee = fee;
	}
	
	public String toString() {
		if (actualReturnDate == null) {
			return recordID + ":" + rentDate + ":" + estimatedReturnDate + ":none:none:none";
		}
		else {
			return recordID + ":" + estimatedReturnDate + ":" + actualReturnDate + ":" + rentalFee + ":" + lateFee;
		}
	}
	
	public String getDetails() {
		String details;
		details =  "Record ID: \t" + recordID + "\nRent Date: \t" + rentDate + "\nEstimated Return Date: \t" + estimatedReturnDate;
		if (actualReturnDate != null) {
			details += "\nActual Return Date: \t" + actualReturnDate + "\nRental Fee: \t" + rentalFee + "\nLate Fee: \t" + lateFee;
		}
		return details;
	}
	
}

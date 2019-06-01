package model;

public class InvalidIDException extends Exception{
	
	public InvalidIDException(String vehicleId) {
		super(vehicleId + " does not exist");
	}

}
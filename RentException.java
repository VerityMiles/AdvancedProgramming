package model;

public class RentException extends Exception{
	
	public RentException() {
		super("This vehicle is not available to be rented");
	}

}

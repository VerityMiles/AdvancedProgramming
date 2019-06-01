package model;

public class InvalidPassengersException extends Exception{

	public InvalidPassengersException() {
		super("Cars can only have either 4 or 7 passengers");
	}
	
}

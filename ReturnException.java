package model;

public class ReturnException extends Exception{
	
	public ReturnException() {
		super("If the car is not currently rented, you cannot return it");
	}
}

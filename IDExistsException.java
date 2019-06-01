package model;

public class IDExistsException extends Exception {

	public IDExistsException() {
		super("That ID already exists.\n Please enter a unique key.");		
	}
}

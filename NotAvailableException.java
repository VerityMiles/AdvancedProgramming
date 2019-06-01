package model;

public class NotAvailableException extends Exception{
	
	private Status status;

	public NotAvailableException(Status status) {
		super("This vehicle is not available");
		this.status = status;
	}
	
	

}

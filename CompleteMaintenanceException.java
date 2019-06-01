package model;

public class CompleteMaintenanceException extends Exception {

	private Status status;	
	
	public CompleteMaintenanceException(Status status) {
		super("Maintenance cannot be performed at this time");
		this.status = status;
		
	}

}

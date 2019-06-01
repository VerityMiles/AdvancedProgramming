package model;

public class RequiresMaintenanceException extends Exception{
	
	public RequiresMaintenanceException() {
		super("This van cannot be rented as it requires maintenance");
	}

}

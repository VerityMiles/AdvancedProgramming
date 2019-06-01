package model;

public class IncorrectIDStartException extends Exception{

	public IncorrectIDStartException() {
		super("Car IDs must start with a C_.\n"+
				"Van IDs must start with a V_");
	}
}

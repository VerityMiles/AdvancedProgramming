package view;

public class IncorrectTableException extends Exception{
	
	public IncorrectTableException() {
		super("There are only two tables in the database VEHICLES and RENTAL_RECORDS\n" +
	"Please enter either of these");
	}
}



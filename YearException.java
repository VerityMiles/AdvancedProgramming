package model;

public class YearException extends Exception{
	
	public YearException() {
		super("We do not stock cars from that year");
	}

}

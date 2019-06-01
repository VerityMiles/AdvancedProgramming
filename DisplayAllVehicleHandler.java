package view;

import app.ThriftyRent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class DisplayAllVehicleHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e) {
		System.out.println("You have chosen to see all vehicles");
		
		ThriftyRent mainApp = new app.ThriftyRent();
		//need to import data from database into thriftyrent app
		
		for (String currentKey : mainApp.vehicles.keySet()) {
			System.out.println(mainApp.vehicles.get(currentKey).getDetails());
		}
		
	}
}

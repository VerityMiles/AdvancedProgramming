package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddCarHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		System.out.println("You have chosen to add a CAR");
		
		//Scene addCar = new Stage();
		GUIAddCar test = new GUIAddCar();
		test.start(new Stage());
		
	}
}


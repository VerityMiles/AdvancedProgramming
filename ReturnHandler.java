package view;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ReturnHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e) {
		System.out.println("You have chosen to return to the main menu");
		
		GUITest test = new GUITest();
		try {
			test.start(new Stage());
		} catch (FileNotFoundException e1) {
			System.out.println("Your file was not found");
			e1.printStackTrace();
		}
		
	}

}

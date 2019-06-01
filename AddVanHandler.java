package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AddVanHandler implements EventHandler<ActionEvent> {
	@Override
	public void handle(ActionEvent e) {
		System.out.println("You have chosen to add a VAN");
	}
}


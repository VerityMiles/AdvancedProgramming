package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ExportDataHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e) {
		System.out.println("You have chosen to export all data");
	}

}
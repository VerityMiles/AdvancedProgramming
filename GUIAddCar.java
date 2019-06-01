package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
//import javafx.scene.control.ListView; - for scrollable list
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.scene.text.*;

public class GUIAddCar extends Application{
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	@Override
	public void start(Stage primaryStage) {
		
		GridPane gridVehicle = new GridPane();
		gridVehicle.setAlignment(Pos.TOP_LEFT);
		gridVehicle.setPadding(new Insets(20,20,20,20));
		gridVehicle.setHgap(10);
		gridVehicle.setVgap(10);

		
		//Add a vehicle ID
		gridVehicle.add(new Text(500,500, "Please enter a Vehicle ID"), 1, 2);
		gridVehicle.add(new Text(500,500, "Car ID must start with C_"), 2, 2);
		TextField carID = new TextField();
		gridVehicle.add(carID, 3, 2);
		
		EventHandler<ActionEvent> idEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String vehicleID = carID.getText();
			}
		};
		carID.setOnAction(idEvent);
		
		
		//What year was your vehicle made?
		gridVehicle.add(new Text(500,500, "Please enter the year the vehicle was made"), 1, 4);
		gridVehicle.add(new Text(500,500, "The year must be between 1950 and 2019"), 2, 4);
		gridVehicle.add(new Button("type year"), 3, 4);
		
		//What make?
		gridVehicle.add(new Text(500,500, "Please enter the car's make"), 1, 5);
		gridVehicle.add(new Text(500,500, "For example: Toyota, Holden, Mazda"), 2, 5);
		gridVehicle.add(new Button("type make"), 3, 5);
		
		//What model?
		gridVehicle.add(new Text(500,500, "Please enter the car's model"), 1, 6);
		gridVehicle.add(new Text(500,500, "For example: Corolla, Camry, Accord"), 2, 6);
		gridVehicle.add(new Button("type model"), 3, 6);
		
		//Set number of passengers
		gridVehicle.add(new Text(500,500, "Please choose the number of passengers"), 1, 7);
		gridVehicle.add(new Button("4"), 3, 7);
		gridVehicle.add(new Button("7"), 4, 7);
		
		Button seat4Button = new Button("4");
		seat4Button.setOnAction(new AddCarHandler());
		gridVehicle.add(seat4Button, 3, 7);
		
		Button seat7Button = new Button("7");
		seat7Button.setOnAction(new AddVanHandler());
		gridVehicle.add(seat7Button, 4, 7);
		
		//Final thanks
		gridVehicle.add(new Text(500,500, "Thank you. That is all the information we are after!"), 1, 8);
	
		Scene sceneTest = new Scene(gridVehicle);
		primaryStage.setScene(sceneTest);
		primaryStage.show();
		
		
	}
	

}

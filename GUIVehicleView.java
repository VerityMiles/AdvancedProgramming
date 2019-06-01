package view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import app.ThriftyRent;
import model.Car;
import model.RentalRecord;
import model.Van;
import model.Vehicle;
import databasetesting.Database;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GUIVehicleView extends GUITest{

	public static void main(String[] args) {
		
	}
	
	public void addtoMainApp(Connection con, ThriftyRent mainApp, String DB_NAME) {

		ResultSet vehicleResult = null;
		ResultSet rentalResult = null;

		Database testDB = new Database();
		try {
			con = testDB.addConnection(DB_NAME);
			vehicleResult = testDB.vehicleSelectQuery(con);

			while (vehicleResult.next()) {
				Vehicle vehicle = null;
				if (vehicleResult.getString("Type").compareTo("Car") == 0) {
					vehicle = new Car(vehicleResult.getString("VehicleID"), 
						vehicleResult.getInt("Year"),vehicleResult.getString("Make"),
						vehicleResult.getString("Model"), vehicleResult.getInt("Passengers"),
						vehicleResult.getString("Type"), vehicleResult.getInt("Rate"),
						vehicleResult.getString("Image"));
				}
				else if (vehicleResult.getString("Type").compareTo("Van") == 0) {
					vehicle = new Van(vehicleResult.getString("VehicleID"), 
							vehicleResult.getInt("Year"),vehicleResult.getString("Make"),
							vehicleResult.getString("Model"),vehicleResult.getString("Type"),
							vehicleResult.getString("Image"));
					}
				
				mainApp.addVehicle(vehicleResult.getString("VehicleID"), vehicle);
			}
			
//			rentalResult = testDB.rentalSelectQuery(con);
//			
//			while (rentalResult.next()) {
//				RentalRecord record = new RentalRecord(rentalResult.getString("VehicleID"),
//						rentalResult.getString("Customer"), rentalResult.getDate("RentDate"),
//						rentalResult.getInt("Days"), rentalResult.getDate("EstimatedReturnDate"),
//						rentalResult.getDate("ActualReturnDate"), rentalResult.getInt("Rate"),
//						rentalResult.getDouble("RentalFee"), rentalResult.getDouble("LateFee"));
//				
//			}
		}
		catch (ClassNotFoundException e) {
			System.err.println("Database connection was unsuccessful");
			System.exit(0);
		}
		catch (SQLException se) {
			System.err.println("Database connection was unsuccessful");
			System.exit(0);
		}

		//mainApp.addVehicle(vehicleID, vehicle);

		Application.launch();



	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {


		//tableview working
		TableView<Vehicle> tableView = new TableView<Vehicle>();

		TableColumn<Vehicle, String> columnID = new TableColumn<>("Vehicle ID");
		TableColumn<Vehicle, Integer> columnYear = new TableColumn<>("Year");
		TableColumn<Vehicle, String> columnMake = new TableColumn<>("Make");
		TableColumn<Vehicle, String> columnModel = new TableColumn<>("Model");
		TableColumn<Vehicle, Integer> columnPassengers = new TableColumn<>("Seats");
		TableColumn<Vehicle, String> columnStatus = new TableColumn<>("Status");
		TableColumn<Vehicle, Date> columnMaintenance = new TableColumn<>("Maintenance");

		tableView.getColumns().addAll(columnID, columnYear, columnMake, columnModel,
				columnPassengers, columnStatus, columnMaintenance);
		//loop through column
		autoFitTable(tableView);

		//need to work out how to add data from database

		ScrollPane scrollable = new ScrollPane(tableView);

		Scene scene = new Scene(scrollable);

		primaryStage.setScene(scene);
		primaryStage.show();	

	}

	@SuppressWarnings("rawtypes")
	public static void autoFitTable( TableView tableView) {

		ObservableList<TableColumn> columns = tableView.getColumns();

		for (TableColumn column : columns) {
			column.setMinWidth(100);
		}

	}

}
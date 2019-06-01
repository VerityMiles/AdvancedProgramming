package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import model.Car;
import model.Van;
import model.Vehicle;
import javafx.geometry.Pos;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import app.ThriftyRent;
import databasetesting.Database;

public class GUITest extends Application{

	public static void main(String[] args) {
		
		//ThriftyRent mainApp = new app.ThriftyRent();
				
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) throws FileNotFoundException{

		//loading all data into mainApp for use throughout program
		ThriftyRent mainApp = new app.ThriftyRent();
		
		//All together menu
		Pane screenMenu = new VBox(0);
		Pane searchMenu = searchMenu();
		Pane scrollable = scrollMenu();
		Pane mainMenu = mainMenu();
		screenMenu.getChildren().addAll(searchMenu, scrollable);
		Pane allMenus = new HBox(0);
		allMenus.getChildren().addAll(mainMenu, screenMenu);

		Scene menuScene = new Scene(allMenus, 900, 900);
		primaryStage.setScene(menuScene);
		primaryStage.show();	
	}

	public Pane mainMenu() {
		//Create main menu
		Pane mainMenu = new VBox(0);

		ArrayList<Button> mainArray = new ArrayList<Button>();
	
		Button addCarButton = new Button("Add car");
		addCarButton.setOnAction(new AddCarHandler());
		mainArray.add(addCarButton);
		
		Button addVanButton = new Button("Add vehicle");
		addVanButton.setOnAction(new AddVanHandler());
		mainArray.add(addVanButton);
		
		Button rentButton = new Button("Rent vehicle");
		//rentButton.setOnAction(new RentHandler());
		mainArray.add(rentButton);
		
		Button returnButton = new Button("Return vehicle");
		//returnButton.setOnAction(new ReturnHandler());
		mainArray.add(returnButton);
		
		Button vehicleMaintenanceButton = new Button("Vehicle maintenance");
		//vehicleMaintenanceButton.setOnAction(new MaintenanceHandler());
		mainArray.add(vehicleMaintenanceButton);
		
		mainArray.add(new Button("Complete maintenance"));
		mainArray.add(new Button("Display all vehicles"));

		//import/ export data
		Button importDataButton = new Button("Import Data");
		importDataButton.setOnAction(new ImportDataHandler());
		mainArray.add(importDataButton);
		Button exportDataButton = new Button("Export All Data");
		exportDataButton.setOnAction(new ExportDataHandler());
		mainArray.add(exportDataButton);

		for (int i = 0; i < 10; i++) {
			mainArray.get(i).setMinWidth(200);
			//mainArray.get(i).setOnAction(handler1);
			mainMenu.getChildren().add(mainArray.get(i));
		}
		return mainMenu;
	}

	public Pane searchMenu() {
		//Create search menu
		Pane searchMenu = new HBox(0);

		ArrayList<Button> searchArray = new ArrayList<Button>();
		searchArray.add(new Button("Search"));
		searchArray.add(new Button("Type"));
		searchArray.add(new Button("Seats"));
		searchArray.add(new Button("Status"));
		searchArray.add(new Button("Make"));

		for (int i = 0; i <5; i++) {
			searchArray.get(i).setMinWidth(100);
			searchMenu.getChildren().add(searchArray.get(i));
		}	
		return searchMenu;
	}

	public Pane scrollMenu() throws FileNotFoundException {
		
		DisplayAllVehicleHandler handler1 = new DisplayAllVehicleHandler();
		
		//Scroll Top Left Menu
		GridPane gridMake = new GridPane();
		gridMake.setAlignment(Pos.TOP_LEFT);
		gridMake.setMinWidth(100);
		/*gridMake.add(new Button("Make"), 1, 1);
		gridMake.add(new Button("Model"), 2, 1);*/

		ArrayList<Button> scrollTopArray = new ArrayList<Button>();
		scrollTopArray.add(new Button("Make"));
		scrollTopArray.add(new Button("Model"));

		for (int i = 0; i < 2; i++) {
			scrollTopArray.get(i).setMinWidth(200);
			scrollTopArray.get(i).setOnAction(handler1);
			gridMake.getChildren().add(scrollTopArray.get(i));
		}

		//Image
		Image imageTest = new Image(new FileInputStream("C:\\Users\\verit\\Documents\\RMIT\\AdvancedProgramming\\Assignments\\Assignment2\\RawDatabase\\RMIT\\AP_Assignment2\\Images\\C001.jpg"));
		ImageView imageView = new ImageView(imageTest);
		imageView.setFitHeight(100);
		imageView.setFitWidth(100);
		imageView.setPreserveRatio(true);

		//Scroll Left Menu		
		Pane gridLeft = new VBox(0);
		gridLeft.getChildren().addAll(gridMake, imageView);

		//Scroll Middle Menu
		Pane scrollMiddleGrid = new VBox(0);
		ArrayList<Button> scrollMiddleArray = new ArrayList<Button>();
		scrollMiddleArray.add(new Button("Rate"));
		scrollMiddleArray.add(new Button("Type"));
		scrollMiddleArray.add(new Button("Year"));
		scrollMiddleArray.add(new Button("Passengers"));

		for (int i = 0; i < 4; i++) {
			scrollMiddleArray.get(i).setMinWidth(200);
			scrollMiddleArray.get(i).setOnAction(handler1);
			scrollMiddleGrid.getChildren().add(scrollMiddleArray.get(i));
		}

		//Scroll Right Menu
		Pane scrollRightGrid = new VBox(0);
		ArrayList<Button> scrollRightArray = new ArrayList<Button>();
		scrollRightArray.add(new Button("Book Now"));
		scrollRightArray.add(new Button("Vehicle Status"));

		for (int i = 0; i < 2; i++) {
			scrollRightArray.get(i).setMinWidth(120);
			scrollRightArray.get(i).setOnAction(handler1);
			scrollRightGrid.getChildren().add(scrollRightArray.get(i));
		}

		//Whole scrollable menu
		Pane scrollAll = new HBox(0);
		scrollAll.getChildren().addAll(gridLeft, scrollMiddleGrid, scrollRightGrid);
		ScrollPane scrollable = new ScrollPane();
		scrollable.setContent(scrollAll);
		
		return scrollAll;
	}
	
//	public void addtoMainApp(Connection con, String DB_NAME) {
//
//		ResultSet vehicleResult = null;
//		ResultSet rentalResult = null;
//
//		Database testDB = new Database();
//		try {
//			con = testDB.addConnection(DB_NAME);
//			vehicleResult = testDB.vehicleSelectQuery(con);
//
//			while (vehicleResult.next()) {
//				Vehicle vehicle = null;
//				if (vehicleResult.getString("Type").compareTo("Car") == 0) {
//					vehicle = new Car(vehicleResult.getString("VehicleID"), 
//						vehicleResult.getInt("Year"),vehicleResult.getString("Make"),
//						vehicleResult.getString("Model"), vehicleResult.getInt("Passengers"),
//						vehicleResult.getString("Type"), vehicleResult.getInt("Rate"),
//						vehicleResult.getString("Image"));
//				}
//				else if (vehicleResult.getString("Type").compareTo("Van") == 0) {
//					vehicle = new Van(vehicleResult.getString("VehicleID"), 
//							vehicleResult.getInt("Year"),vehicleResult.getString("Make"),
//							vehicleResult.getString("Model"),vehicleResult.getString("Type"),
//							vehicleResult.getString("Image"));
//					}
//				
//				//mainApp.addVehicle(vehicleResult.getString("VehicleID"), vehicle);
//			}
//			
////			rentalResult = testDB.rentalSelectQuery(con);
////			
////			while (rentalResult.next()) {
////				RentalRecord record = new RentalRecord(rentalResult.getString("VehicleID"),
////						rentalResult.getString("Customer"), rentalResult.getDate("RentDate"),
////						rentalResult.getInt("Days"), rentalResult.getDate("EstimatedReturnDate"),
////						rentalResult.getDate("ActualReturnDate"), rentalResult.getInt("Rate"),
////						rentalResult.getDouble("RentalFee"), rentalResult.getDouble("LateFee"));
////				
////			}
//		}
//		catch (ClassNotFoundException e) {
//			System.err.println("Database connection was unsuccessful");
//			System.exit(0);
//		}
//		catch (SQLException se) {
//			System.err.println("Database connection was unsuccessful");
//			System.exit(0);
//		}
//
//		//mainApp.addVehicle(vehicleID, vehicle);
//		//return mainApp;
//	}


}

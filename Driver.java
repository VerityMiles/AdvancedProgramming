package main;

import app.ThriftyRent;
import javafx.application.Application;
import model.*;
import util.DateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver{

	public static void main(String[] args) throws RentException, CompleteMaintenanceException, NotAvailableException, ReturnException, RequiresMaintenanceException {
		ThriftyRent mainApp = new app.ThriftyRent();
		
		//Setting up a scanner
		Scanner sc = new Scanner(System.in);
		Integer input = 0;
		
		//Main menu
		do {
			System.out.println("\n" + "****ThiftyRent SYSTEM MENU****" + "\n");
			String format = "%-25s%s%n";
			String[] menu = {"Add Vehicle:", "Rent Vehicle:", "Return Vehicle:", "Vehicle Maintenance:", "Complete Maintenance:", "Display All Vehicles:", "Exit Program:"};
			
			for (int i = 0; i < 7; i++) {
				System.out.printf(format, menu[i], i+1);
			}
			
			System.out.println("Enter your choice:");
			
			try {
				input = sc.nextInt();
			}
			catch (Exception e) {
				System.out.println("Please enter an integer between 1 and 7");
				input = sc.nextInt();
			}
			
			String vehicleID;
	
			if (input == 1) {
				//add new vehicle
				System.out.println("You have chosen to add a new vehicle to Thrifty Rental");
				String vehicleType = null;
				
				do {
					System.out.println("Please enter the type of vehicle you wish to add.");
				
					System.out.println("Enter 1 for a car and 2 for a van");
					try {
						input = sc.nextInt();
					}
					catch (InputMismatchException ime) {
						System.out.println("Please enter either 1 or 2");
						input = sc.nextInt();
					}
					
					if (input == 1) {
						//CAR
						vehicleType = "Car";
					}
					else if (input == 2) {
						//VAN
						vehicleType = "Van";
					}
			
				}
				while (input > 2 || input < 1);
				
				try {
					System.out.println("Please enter the VehicleID");
					System.out.println("Car ID must start with C_\n and Van ID must start with V_.");
					vehicleID = sc.next();

					/*boolean check = mainApp.checkIDStart(vehicleID, vehicleType); 
					if ( check == false) {
						System.out.println("Invalid vehicle ID");
						input = 8;
					}*/
					System.out.println(vehicleID);

					int year;
					do {
						System.out.println("Please enter the year the car was produced in\n" +
											"The year must be between 1950 and 2019");
						year = sc.nextInt();
					}
					while (year > 2019 || year < 1950 );
					
				
					System.out.println("Please enter the car's make");
					System.out.println("For example, Toyota, Honda, Nissan");
					String make = sc.next();
				
					System.out.println("Please enter the car's model");
					System.out.println("For example, Corolla, Camry, Accord");
					String model = sc.next();

					if (input == 1) {
						System.out.println("Please enter the number of passengers the car can hold");
						int passengers;
						int rate = 0;
						do {
							passengers = sc.nextInt();
													
							if (passengers == 4 ) {
								rate = 78;
							}
							else if (passengers == 7) {
								rate = 113;
							}
							else {
							System.out.println("Please enter either 4 or 7 passengers for the car");
							}
						}
						while (passengers != 4 && passengers !=7);
						
						mainApp.addVehicle(vehicleID, new Car(vehicleID, year, make, model, passengers, "Car", rate));
					}
					
					else if (input == 2) {
						mainApp.addVehicle(vehicleID, new Van(vehicleID, year, make, model, "Van"));
					}
								
				System.out.println("There are now " + mainApp.vehicles.size() + " vehicles in the store.");
				
				//return to main menu
				System.out.println();
				input = 8;
				
				}
				catch (InputMismatchException ime) {
					System.out.println("You have added in the incorrect format of data");
					System.out.println("Taking you back to main menu...");
					input = 8;
				}
			}
			else if (input == 2) {
				//rent vehicle
				System.out.println("You have chosen to rent a vehicle");
				System.out.println("Add in the ID of the vehicle you wish to rent");
				vehicleID = sc.next();
				
				if (mainApp.vehicles.containsKey(vehicleID)) {
					System.out.println("The vehicle ID you entered was: " + vehicleID);
				}
				else {
					System.out.println("Your key doesn't exist");
					System.out.println("Please add one of the above keys");
					vehicleID = sc.next();
				}
				
				//check status of vehicle
				System.out.println(mainApp.vehicles.get(vehicleID).getStatus());
				if (mainApp.vehicles.get(vehicleID).getStatus() == Status.AVAILABLE) {
					System.out.println("Please add in the customer ID");
					String customerID = sc.next();
					System.out.println("Please enter the number of days you wish to rent the vehicle for");
					int days = sc.nextInt();
					System.out.println("If you would like to rent the vehicle today type 1.");
					System.out.println("Otherwise type 2");
					util.DateTime date;
					int dateStart = sc.nextInt();
					if (dateStart == 1) {
						date = new DateTime();
					}
					else {
						System.out.println("Please enter the number of the month you wish to start your rental");
						System.out.println("For example, enter 4 for April");
						int month = sc.nextInt();
						System.out.println("Please enter the number of the day you wish to start your rental");
						int day = sc.nextInt();
						date = new DateTime(day, month, 2019);
					}
					
					int rate = mainApp.vehicles.get(vehicleID).getRate();
					mainApp.vehicles.get(vehicleID).rent(customerID, date, days, rate);
					System.out.println("Congratulations you have rented a " + mainApp.vehicles.get(vehicleID).getMake() + 
										" " + mainApp.vehicles.get(vehicleID).getModel());
					
					mainApp.vehicles.get(vehicleID).setStatus(Status.RENTED);
					
				}
				else {
					System.out.println("The vehicle you wish to rent is currently not available");
					System.out.println("You will be taken back to the main menu to decide what to do next.");
				}
				
				//return to main menu
				System.out.println();
				input = 8;
				
			}
		
			else if (input == 3) {
				//return vehicle
				System.out.println("You have chosen to return a vehicle");
				System.out.println("Add in the ID of the vehicle you wish to return");
				vehicleID = sc.next();
				if (mainApp.vehicles.get(vehicleID).getStatus() == Status.RENTED) {
					DateTime today = new DateTime();
					mainApp.vehicles.get(vehicleID).returnVehicle(today);
				}
				
				mainApp.vehicles.get(vehicleID).Records.get(0);
				
				//return to main menu
				System.out.println();
				input = 8;
				
			}
		
			else if (input == 4) {
				//vehicle maintenance
				System.out.println("You have chosen to perform vehicle maintenance" + "\n" +
									"Please enter the ID of the vehicle you wish to perform maintenance on");
				vehicleID = sc.next();
				mainApp.vehicles.get(vehicleID).performMaintenance();				
				//return to main menu
				System.out.println();
				input = 8;

			}
		
			else if (input == 5) {
				//complete maintenance
				System.out.println("You have chosen to complete vehicle maintenance\n" +
									"Please enter the ID of the vehicle you wish to complete maintenance on");
				vehicleID = sc.next();
				mainApp.vehicles.get(vehicleID).completeMaintenance();
				//return to main menu
				System.out.println();
				input = 8;
			
			}
			
			else if (input ==6) {
				//display all vehicles
				System.out.println("You have chosen to display all vehicles" + "\n");
				//mainApp.vehicles.
				for (String currentKey : mainApp.vehicles.keySet()) {
					System.out.println(mainApp.vehicles.get(currentKey).getDetails());
				}
				
				//return to main menu
				System.out.println();
				input = 8;
				
			}
		
			else if (input == 7){
				System.out.println("You have chosen to exit the program");
				break;
			}
			
		}
	while (input >= 8 || input <1);
	}

}

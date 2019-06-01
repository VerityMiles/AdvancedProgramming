package app;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import model.*;

public class ThriftyRent {

	Scanner sc = new Scanner(System.in);
	Integer input = 0;
	
	public void setVehicles(Map<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public Map <String, Vehicle> vehicles = new HashMap<>();
	
	public Map<String, Vehicle> getVehicles() {
		return vehicles;
	}
	
	public void addVehicle(String vehicleID, Vehicle vehicle) {
		//add in check id
		if (vehicle.checkID(vehicleID) == false) {
			System.out.println("Your vehicle ID does not have the correct start");
		}
		else if (vehicle.checkID(vehicleID) == true) {
			vehicles.put(vehicleID, vehicle);
		}
		else {
			System.out.println("Vehicle could not be added");
		}
	}
//	
//	public void addVehicletest() {
//		String vehicleType = null;
//		String vehicleID = null;
//	
//		do {
//			System.out.println("Please enter the type of vehicle you wish to add.");
//	
//			System.out.println("Enter 1 for a car and 2 for a van");
//			try {
//				input = sc.nextInt();
//			}
//
//			catch (InputMismatchException ime) {
//				System.out.println("Please enter either 1 or 2");
//				input = sc.nextInt();
//			}
//		
//			if (input == 1) {
//				//CAR
//				vehicleType = "Car";
//			}
//			else if (input == 2) {
//				//VAN
//				vehicleType = "Van";
//			}
//
//		}
//		while (input > 2 || input < 1);
//	
//		try {
//			System.out.println("Please enter the VehicleID");
//			System.out.println("Car ID must start with C_\n and Van ID must start with V_.");
//			vehicleID = sc.next();
//
//		/*boolean check = mainApp.checkIDStart(vehicleID, vehicleType); 
//		if ( check == false) {
//			System.out.println("Invalid vehicle ID");
//			input = 8;
//		}*/
//			System.out.println(vehicleID);
//
//			int year;
//			do {
//				System.out.println("Please enter the year the car was produced in\n" +
//								"The year must be between 1950 and 2019");
//				year = sc.nextInt();
//			}
//			while (year > 2019 || year < 1950 );
//		
//	
//			System.out.println("Please enter the car's make");
//			System.out.println("For example, Toyota, Honda, Nissan");
//			String make = sc.next();
//	
//			System.out.println("Please enter the car's model");
//			System.out.println("For example, Corolla, Camry, Accord");
//			String model = sc.next();
//
//			if (input == 1) {
//				System.out.println("Please enter the number of passengers the car can hold");
//				int passengers;
//				int rate = 0;
//				do {
//					passengers = sc.nextInt();
//										
//					if (passengers == 4 ) {
//						rate = 78;
//					}
//					else if (passengers == 7) {
//						rate = 113;
//					}
//					else {
//						System.out.println("Please enter either 4 or 7 passengers for the car");
//					}
//				}
//				while (passengers != 4 && passengers !=7);
//			
//				mainApp.addVehicle(vehicleID, new Car(vehicleID, year, make, model, passengers, "Car", rate));
//			}
//		
//			else if (input == 2) {
//				mainApp.addVehicle(vehicleID, new Van(vehicleID, year, make, model, "Van"));
//			}
//					
//			System.out.println("There are now " + mainApp.vehicles.size() + " vehicles in the store.");
//	
//			//return to main menu
//			System.out.println();
//			input = 8;
//	
//		}
//		catch (InputMismatchException ime) {
//			System.out.println("You have added in the incorrect format of data");
//			System.out.println("Taking you back to main menu...");
//			input = 8;
//		}
//	}
//	
	
	
	public void checkID(Map vehicles, String vehicleID) throws IDExistsException{
		if (vehicles.isEmpty()) {
			System.out.println("This is the first vehicle to be added");
		}
		else if (vehicles.containsKey(vehicleID)) {
			throw new IDExistsException();
		}
		else {
			System.out.println("That key does not already exist");
			System.out.println("You are ok to add into the system");
		}
	}
	
	public void checkIDStart(String vehicleID, String vehicleType) throws IncorrectIDStartException {
		if (vehicleType == "Car") {
			if (vehicleID.substring(0,2).equals("C_"))
				System.out.println("All good");
			else
				throw new IncorrectIDStartException();
		}
		else if (vehicleType == "Van") {
			if (vehicleID.substring(0,2).equals("V_"))
				System.out.println("All good");
			else
				throw new IncorrectIDStartException();
		}
	}
	
	public void showVehicles() {
		System.out.println("This store contains: ");
		for (String currentKey : vehicles.keySet()) {
			System.out.println(vehicles.get(currentKey).getDetails());
		}
	}
}

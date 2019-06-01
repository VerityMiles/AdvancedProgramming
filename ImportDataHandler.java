package view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import databasetesting.Database;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ImportDataHandler implements EventHandler<ActionEvent>{
	@Override
	public void handle(ActionEvent e){
		
		Scanner sc = new Scanner(System.in);
		Database testDB = new Database();
		
		String db_Name = testDB.getDBName();
		System.out.println("Please input the file you wish to import from");
		String input = sc.nextLine();
		System.out.println("Please select which table you would like to import to");
		String inputTable = sc.nextLine();
		System.out.println("You have chosen to insert the data within " + input+ 
				" into " + inputTable + " table");
		
		//Import vehicle data
		if (inputTable.equals("VEHICLES") == true) {
			try {
				testDB.importData(input, "VEHICLES", db_Name);
			} catch (ClassNotFoundException e1) {
				System.out.println("Class not found exception");
			} catch (ParseException e1) {
				System.out.println("parse exception");
			} catch (SQLException e1) {
				System.out.println("sql exception");
			} catch (NullPointerException npe) {
				System.out.println("null pointer");
			} catch (IOException ioe) {
				System.out.println("IO error");
			} catch (Exception e2) {
				System.out.println("other error");
				e2.printStackTrace();
			}
			
		}
		
		//Import rental record data
		else if (inputTable == "RENTAL_RECORDS") {
			try {
				testDB.importData(input, "RENTAL_RECORDS", db_Name);
			} catch (ClassNotFoundException e1) {
				System.out.println("class not found exception");
			} catch (ParseException e1) {
				System.out.println("parse exception");
			} catch (SQLException e1) {
				System.out.println("sql exception");
			} catch (IOException ieo) {
				System.out.println("io error");
			} catch (Exception e2) {
				System.out.println("other error");
				e2.printStackTrace();
			}
		}
		else {
			//throw new ImportDataHandlerException();
			System.out.println("Import data handler exception");
			System.out.println("please enter either VEHICLES or RENTAL_RECORDS");
		}
		
		
	}


}



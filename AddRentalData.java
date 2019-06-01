package databasetesting;

import java.sql.Connection;
import java.sql.Statement;

public class AddRentalData {
	public static void main(String[] args) {
		final String DB_NAME = "rentalDB";
		final String TABLE_NAME = "RENTAL_RECORDS";
		
		//use try-with-resources Statement
		try {
			Connection con = ConnectDatabase.getConnection(DB_NAME);
			Statement stmt = con.createStatement();
			String query = "INSERT INTO " + TABLE_NAME 
					+  "  VALUES ('C_001', 'Verity', 'C_001_Verity_10012018', 10012018, 12012018, 2, 78, '', '', '', '' )";

			System.out.println(query);				
			int result = stmt.executeUpdate(query);
			System.out.println(result);
			con.commit();
			
			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

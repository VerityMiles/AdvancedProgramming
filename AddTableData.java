package databasetesting;

import java.sql.Connection;
import java.sql.Statement;

public class AddTableData {
	public static void main(String[] args) {
		final String DB_NAME = "rentalDB";
		final String TABLE_NAME = "VEHICLES";

		//use try-with-resources Statement
		try {
			Connection con = ConnectDatabase.getConnection(DB_NAME);
			System.out.println("dbname os: " + DB_NAME);
			Statement stmt = con.createStatement();
			String query = "INSERT INTO " + TABLE_NAME 
					+ "  VALUES ('C_001', 2019, 'Subaru', 'Forester', 7, 'Car',"
					+ "NULL, NULL, NULL, 113, 'C_001.jpg')";
			System.out.println(query);

			int result = stmt.executeUpdate(query);
			System.out.println(result);
			System.out.println("line 23");

			con.commit();

			System.out.println("Insert into table " + TABLE_NAME + " executed successfully");
			System.out.println(result + " row(s) affected");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

package databasetesting;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableExist {
	public static void main(String[] args) throws SQLException {

		final String DB_NAME = "rentalDB";

		// IMPORTANT: table name is uppercase
		final String TABLE_NAME = "RENTAL_RECORDS";
		
		// use try-with-resources Statement
		try (Connection con = ConnectDatabase.getConnection(DB_NAME)) {

			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
			
			if(tables != null) {
				if (tables.next()) {
					System.out.println("Table " + TABLE_NAME + " exists.");
				}
				else {
					System.out.println("Table " + TABLE_NAME + " does not exist.");
				}	
				tables.close();
			} else {
				System.out.println(("Problem with retrieving database metadata"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

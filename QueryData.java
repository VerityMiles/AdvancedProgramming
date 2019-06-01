package databasetesting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryData {
	public static void main(String[] args) {
		final String DB_NAME = "rentalDB";
		final String TABLE_NAME = "VEHICLES";
		
		//use try-with-resources Statement
		try {
			Connection con = ConnectDatabase.getConnection(DB_NAME);
			Statement stmt = con.createStatement();
			String query = "SELECT * FROM " + TABLE_NAME;
			System.out.println(query);
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				System.out.println(resultSet.getFetchSize());
				while(resultSet.next()) {
					System.out.println("line22");
					System.out.printf("Id: %s | Vehicle Type: %s | Make: %s | Model: %s | Year: %d\n",
							resultSet.getString("VehicleID"), resultSet.getString("Type"), 
							resultSet.getString("Make"), resultSet.getString("Model"),
							resultSet.getInt("Year"));
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

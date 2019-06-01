package databasetesting;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "rentalDB";
		final String TABLE_NAME = "VEHICLES";
		
		//use try-with-resources Statement
		try (Connection con = ConnectDatabase.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE vehicles ("
										+ "VehicleID VARCHAR(8) NOT NULL,"
										+ "Year INT NOT NULL," 
										+ "Make VARCHAR(50) NOT NULL,"
										+ "Model VARCHAR(50) NOT NULL,"
										+ "Passengers INT NOT NULL,"
										+ "Type VARCHAR(10),"
										+ "Status VARCHAR(100),"
										+ "Records VARCHAR(255),"
										+ "LastMaintenance DATE,"
										+ "Rate INT,"
										+ "Image VARCHAR(10),"
										+ "PRIMARY KEY (VehicleID))");
			if(result == 0) {
				System.out.println("Table " + TABLE_NAME + " has been created successfully");
			} else {
				System.out.println("Table " + TABLE_NAME + " is not created");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

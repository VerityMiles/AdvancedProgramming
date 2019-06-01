package databasetesting;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateRentalTable {
	public static void main(String[] args) throws SQLException {
		
		final String DB_NAME = "rentalDB";
		final String TABLE_NAME = "RENTAL_RECORDS";
		
		//use try-with-resources Statement
		try (Connection con = ConnectDatabase.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			int result = stmt.executeUpdate("CREATE TABLE rental_records ("
										+ "VehicleID VARCHAR(8) NOT NULL,"
										+ "CustomerID VARCHAR(50) NOT NULL," 
										+ "RecordID VARCHAR(58) NOT NULL,"
										+ "RentalDate DATE NOT NULL,"
										+ "EstimatedReturnDate DATE NOT NULL,"
										+ "ActualReturnDate DATE,"
										+ "Days INT,"
										+ "Rate INT,"
										+ "RentalFee INT,"
										+ "LateDays INT,"
										+ "TotalFee DOUBLE,"
										+ "PRIMARY KEY (RecordID),"
										+ "FOREIGN KEY (VehicleID) REFERENCES VEHICLES(VehicleID))");
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

package databasetesting;

import java.io.*;
import java.util.Scanner;
//import app.ThriftyRent;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Database {

	//database variables
	private String db_Name = "rentalDB";
	private String tableName;
	private String query;
	
	//constructors
	public Database(String db_Name, String tableName, String query) {
		this.db_Name = db_Name;
		this.tableName = tableName;
		this.query = query;		
	}
	
	public Database() {		
	}

	public String getDBName() {
		return db_Name;
	}

	public void importData(String input, String tableName, String db_Name) throws ClassNotFoundException, SQLException, ParseException, IOException {
		Connection con = addConnection(db_Name);
		System.out.println("Testing that this gets called");
		String line = null;
		FileInputStream file = null;
		Scanner fileScan = null;
		System.out.println("You have chosen the following file: " + input);
		
		//pulling in data input from file
		try {

			file = new FileInputStream(input);
			fileScan = new Scanner(file);
			System.out.println("this is file: " + fileScan);
		}
		catch (FileNotFoundException fnfe){
			System.out.println("Your file was not found");
			System.out.println("Please choose another file");
		}
		catch (Exception e) {
			System.out.println("other exception");
			e.printStackTrace();
		}
		
		//add for each line
		while (fileScan.hasNextLine()) {
			System.out.println("database line 85");
			line = fileScan.nextLine();
			System.out.println("line 81 " + line);
			if (tableName == "VEHICLES") {				
				query = vehicleQuery(line, con);
			}

			else if (tableName == "RENTAL_RECORDS") {
				query = rentalQuery(line, con);
			}
			else {
				query = null;
				System.out.println("THIS TABLE DOESN'T EXIST");
			}
			updateQuery(query, db_Name, tableName);
			System.out.println(line);
			
		}
		
		//clean up - not WORKINGGGGGGGGGGGGGGGGGGGGGGG
		System.out.println("import data setup all good");
		fileScan.close();
		file.close();
		System.out.println("close");

	}

	public void updateQuery(String query, String db_Name, String tableName) {
		//setting up variables
		Connection con = null;
		int rowsAdded = 0;

		//add database connection
		try{
			con = addConnection(db_Name);
			Statement stmt = con.createStatement();
			//execute update
			System.out.println("query is: " + query);
			rowsAdded = stmt.executeUpdate(query);
			con.commit();

		}
		catch(Exception e) {
			System.out.println("HELLLPPPPPPPPP");
			e.getMessage();
		}

		System.out.println("Insert into table " + tableName + " executed successfully");
		System.out.println(rowsAdded + " row(s) affected");
		System.out.println("Update query all good");
	}

	@SuppressWarnings("null")
	public String vehicleQuery(String line, Connection con) throws SQLException {
//		String query = "INSERT INTO VEHICLES "
//				+ "VALUES( ?, ?, ?, ?, ?, Car, Available, NULL, NULL, NULL)";
		System.out.println("Within vehicle query");
		PreparedStatement preparedStmt = con.prepareStatement("INSERT INTO VEHICLES "
				+ "VALUES( ?, ?, ?, ?, ?, Car, Available, NULL, NULL, NULL, ?)");
//		PreparedStatement preparedStmt = con.prepareStatement("INSERT INTO VEHICLES "
//				+ "VALUES( ?, ?, ?, ?, ?, Car, Available, NULL, NULL, NULL)");
		
		System.out.println("line139");
		System.out.println(line);
		String[] tokens = line.split("\\t");
		System.out.println("tokens length: " + tokens.length);
		//pulling data from input file into query
		try {
			System.out.println("token 0: " + tokens[0]);
			preparedStmt.setString(1, tokens[0]);
			System.out.println("token 1: " + tokens[1]);
			System.out.println("token 2: " + tokens[2]);
//			preparedStmt.setInt(2, Integer.parseInt(tokens[1]));
//			preparedStmt.setString(3, tokens[2]);
//			preparedStmt.setString(4, tokens[3]);
//			preparedStmt.setInt(5, Integer.parseInt(tokens[4]));
//			preparedStmt.executeUpdate();
			System.out.println("prepared: " + preparedStmt);
			
//		} catch (SQLException e) {
//			System.out.println("SQL exception");
		} catch (NullPointerException npe) {
			System.out.println("null pointer exception");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("vehicle query all good");
		return query;
	}

	public String rentalQuery(String line, Connection con) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
		String query = "INSERT INTO VEHICLES "
				+ "VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = null;
		
		String[] tokens = line.split(",");
		//pulling data from input file into query
		try {
			preparedStmt.setString(1, tokens[0]);
			preparedStmt.setString(2, tokens[1]);
			preparedStmt.setString(3, tokens[2]);
			java.util.Date date = format.parse(tokens[3].toString());			
			preparedStmt.setDate(4, (Date) date);
			date = format.parse(tokens[4].toString());			
			preparedStmt.setDate(5, (Date) date);
			date = format.parse(tokens[5].toString());			
			preparedStmt.setDate(6, (Date) date);
			preparedStmt.setInt(7, Integer.parseInt(tokens[6]));
			preparedStmt.setInt(8, Integer.parseInt(tokens[7]));
			preparedStmt.setInt(9, Integer.parseInt(tokens[8]));
			preparedStmt.setInt(10, Integer.parseInt(tokens[9]));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Rental query all good");
		return query;
	}

	public Connection addConnection(String db_Name) throws SQLException, ClassNotFoundException {
		//Registering the HSQLDB JDBC driver
		Class.forName("org.hsqldb.jdbc.JDBCDriver");
		
		//add database connection
		Connection con = DriverManager.getConnection
				("jdbc:hsqldb:file:database/" + db_Name, "SA", "");
		System.out.println("Connection added");
		return con;
	}
	
	public void exportData(Connection con){
		FileWriter writer = null;
		ResultSet result = null;
		
		try{
			writer = new FileWriter("export_data.txt");
			con = addConnection(db_Name);
			Statement stmt = con.createStatement();
			result = stmt.executeQuery(
					"SELECT * FROM VEHICLES");
			writer.write("Vehicle Id|Year|Make|Model|Passengers|Status|Last Maintenance|Rate\n");
			
			while(result.next()) {
				//step where I send to file writer
				writer.write(result.getString("VehicleID") + "|" + result.getInt("Year") + "|" + 
						result.getString("Make") + "|" + result.getString("Model") + "|" + 
						result.getInt("Passengers") + "|" + result.getString("Status") + "|" +
						result.getDate("LastMaintenance") + "|" + result.getString("Rate")+ "\n");
			}
			
			result = stmt.executeQuery("SELECT * FROM RENTAL_RECORDS");
			writer.write("Record Id|Rent Date|Estimated Return Date|Actual Return Date|Rental Fee|Late Fee\n");
			
			while(result.next()) {
				//step where I send to file writer
				writer.write(result.getString("RecordID") + "|" + result.getDate("RentDate") + "|" + 
						result.getDate("EstimatedReturnDate") + "|" + result.getDate("ActualReturnDate") + "|" + 
						result.getInt("Rate")+ "|" + result.getInt("RentalFee") + "|"+ 
						result.getInt("LateFee") + "\n");
			}
			
		}
		catch (IOException ioe) {
			System.err.println("File cannot be created, or cannot be opened");
			System.exit(0);
		}
		catch(Exception e) {
			System.out.println("HELP!");
		}

		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	System.out.println("Data exported");
	}
	
	public ResultSet vehicleSelectQuery(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM VEHICLES");
		return result;
	}
	
	public ResultSet rentalSelectQuery(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM RENTAL_RECORDS");
		return result;
	}
	
//	public ThriftyRent insertData(ResultSet vehicleResult, ResultSet rentalResult) throws SQLException {
//		while (vehicleResult.next()) {
//			add
//		}
//		
//		return null;
//	}

}

package databasetesting;

import java.util.Scanner;
import java.sql.*;

public class ImportDataTest {

	public static void main(String[] args) {
		String dbName = "rentalDB"; 
		
		//add connection
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection
					("jdbc:hsqldb:file:database/" + dbName, "SA", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection added");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("TableName");
		String tableName = null;
		//tableName = sc.nextLine();
		tableName = "VEHICLES";
		
		String query = "INSERT INTO VEHICLES "
				+ "VALUES( ?, ?, ?, ?, ?, Car, Available, NULL, NULL, NULL, ?)";
		PreparedStatement preparedStmt = null;

		System.out.println("line139");
		System.out.println(line);
		String[] tokens = line.split(",");
		try {
			System.out.println("HELP");
			preparedStmt.setString(1, tokens[0]);
			preparedStmt.setInt(2, Integer.parseInt(tokens[1]));
			preparedStmt.setString(3, tokens[2]);
			preparedStmt.setString(4, tokens[3]);
			preparedStmt.setInt(5, Integer.parseInt(tokens[4]));
			preparedStmt.setString(6,  tokens[5]);
			preparedStmt.execute();
			System.out.println(preparedStmt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("vehicle query all good");
		

	}

}

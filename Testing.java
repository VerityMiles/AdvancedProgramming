package databasetesting;

import java.io.*;
import java.util.Scanner;

public class Testing {
	public static void main(String[] args) {
		String input = "C:\\Users\\verit\\Documents\\RMIT\\AdvancedProgramming\\Assignments\\Assignment2\\RawDatabase\\VEHICLES.txt";

		FileInputStream file = null;
		try {
			file = new FileInputStream(input);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Scanner fileScan = new Scanner(file);
		System.out.println("this is file: " + fileScan);
		fileScan.nextLine();

	}
}

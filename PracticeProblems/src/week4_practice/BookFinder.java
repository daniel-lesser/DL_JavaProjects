package week4_practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BookFinder {

	String fileName;
	String searchString;

	public void getUserInputs() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a file name:");
		fileName = input.next();

		System.out.println("Enter what you are searching for:");
		searchString = input.next();

		input.close();
	}

	public StringBuilder loadRecords() {

		Scanner fileIn = null;
		StringBuilder myString = new StringBuilder();

		try {
			File file = new File(fileName);
			fileIn = new Scanner(file);
		}

		catch (FileNotFoundException e) {
			System.out.println("Your file " + fileName + " was not found.  Sorry.");
			e.printStackTrace();
			System.exit(0);
		}

		while (fileIn.useDelimiter(";").hasNext()) {
			myString.append(fileIn.next() + ";");
		}

		return myString;
	}

	public String [] searchRecords(StringBuilder myBuild) {
		
		String [] inputArray = myBuild.toString().split("\n");
//		for (String s : inputArray) {
//			System.out.print(s);
//		}
		int count = 0;
		
		for (String s : inputArray) {
			if (s.toUpperCase().contains(searchString.toUpperCase())) {
				count++;
			}
		}
		
//		System.out.println(count);
		
		String [] outputArray = new String [count];
		
		int num = 0;
		for (int i = 0; i<inputArray.length; i++) {
			if (inputArray[i].toUpperCase().contains(searchString.toUpperCase())) {
				outputArray[num] = inputArray[i];
				num++;
			}
		}
		
//		System.out.println(outputArray.length);
//		for (String s : outputArray) {
//			System.out.println(s);
//		}
		
		return outputArray;
	}

	public void printOutput(String[] results) {

		if (results.length == 0) {
			System.out.println("Found records: 0");
			System.out.println("Sorry " + searchString + " was not found in " + fileName);
		} else {
			System.out.println("Found records: " + results.length);
			for (String s : results) {
				System.out.print(s);
			}
		}

	}

	public static void main(String[] args) {

		BookFinder bf = new BookFinder();

		bf.getUserInputs();
		StringBuilder myBuild = bf.loadRecords();
		String[] results = bf.searchRecords(myBuild);
		bf.printOutput(results);

	}

}

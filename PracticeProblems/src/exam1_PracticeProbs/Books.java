package exam1_PracticeProbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Books {

	String fileName;
	String searchString;

	public void getUserInputs() {

		System.out.println("Please enter the name of the file you would like to read");
		Scanner fileNameScan = new Scanner(System.in);
		fileName = fileNameScan.next();

		System.out.println("Please enter the word you would like to search for");
		searchString = fileNameScan.next();

		fileNameScan.close();

	}

	public StringBuilder loadRecords() {

		File file;
		Scanner fileInput;
		StringBuilder sb = new StringBuilder();
		try {
			file = new File(fileName);
			fileInput = new Scanner(file);
			while (fileInput.hasNextLine()) {
				sb.append(fileInput.nextLine() + "\n");
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(0);

		}

		return sb;
	}

	public String[] searchRecords(StringBuilder sb) {

		int count = 0;
		String [] records = sb.toString().split("\n");
	
		
		for (int i = 0; i < records.length; i++) {
			if (records[i].toLowerCase().contains(searchString.toLowerCase())) {
				count++;
			}
		}
		
		int num = 0;
		String [] outputRecords = new String [count];
		for (int i = 0; i < records.length; i++) {
			if (records[i].toLowerCase().contains(searchString.toLowerCase())) {
				outputRecords[num] = records[i];
				num++;
			}
		}
		
		return outputRecords;
	}

	public void printOutput(String[] input) {

		if (input.length>0) {
			System.out.println("Found Records: " + input.length);
			for (String s : input) {
				System.out.println(s);
			}
		}
		else {
			System.out.println("No records found");
		}

		
	}

	public static void main(String[] args) {

		Books b = new Books();
		b.getUserInputs();
		b.printOutput(b.searchRecords(b.loadRecords()));
		
//from lesson.  would take each line of books, split it up on the ;, take the 3rd
//item of that split (i.e. the # of pages), trim it, and sum it up for all the book entries.
		
//		int numPages = 0;
//		for (String book : books) {
//			numPages+= Integer.parseInt(book.split(";")[2].trim());
//		}
		
	}

}

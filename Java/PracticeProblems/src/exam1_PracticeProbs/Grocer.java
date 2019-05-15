package exam1_PracticeProbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Grocer {

	static String[][] receipt;

	public static void main(String[] args) {

		System.out.println("Please enter the name of the file you would like to read: ");
		Scanner scanIn = new Scanner(System.in);
		String fileName = scanIn.next();
		File file;

		Scanner scanFile;

		StringBuilder sb = new StringBuilder();

		int rowCount = 0;
		try {
			file = new File(fileName);
			scanFile = new Scanner(file);

			// takes the file input and breaks adds to stringbuilder w/ row breaks
			while (scanFile.hasNextLine()) {
				sb.append(scanFile.nextLine() + "\n");
				rowCount++;
			}

			// takes the stringbuilder and breaks it into an array of rows
			String[] content = sb.toString().split("\n");

			// takes each row of the stringbuilder and breaks it into an array of items.
			receipt = new String[rowCount][];
			for (int i = 0; i < rowCount; i++) {
				String[] items = content[i].toString().split(",");
				String[] insideArray = new String[items.length];

				for (int j = 0; j < items.length; j++) {
					insideArray[j] = items[j].toString().trim();

				}
				receipt[i] = insideArray;
			}

		}

		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.");
			e.getStackTrace();
			System.exit(0);
		}

		for (int i = 0; i < receipt.length; i++) {
			for (int j = 0; j < receipt[i].length; j++) {
				System.out.print(receipt[i][j] + " ");
			}
			System.out.println();
		}

	}

}

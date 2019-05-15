package exam1_PracticeProbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rainbow {

	String[][] colorVotes; // stores the data read from the file
	String[] votedColors; // stores the list of unique colors voted for
	int[] voteCounts; // stored the count the vote for each color. The count of votes for the color in
						// votedColors[i] will be stored in voteCounts[i]

	public static void main(String[] args) {
		Rainbow rainbow = new Rainbow();
		rainbow.readFile();
		rainbow.calculateVoteCounts();
		rainbow.printVoteCounts();
	}

	/**
	 * readFile reads Colors.txt, and loads the data into colorVotes. It also
	 * populates the votedColors array.
	 */
	void readFile() {

		// get file name and declare the fileInput scanner
		System.out.println("Please enter the name of the file you would like to load");
		Scanner fileNameScan = new Scanner(System.in);
		String fileName = fileNameScan.next();
		Scanner fileInput;

		StringBuilder sbVotedColors = new StringBuilder(); // this is to read which colors have been voted for even once

		try {
			// try to initiate the fileInput scanner
			File file = new File(fileName);
			fileInput = new Scanner(file);

			// Moving the fileInput to a strinbuilder with line breaks and counting the
			// number of lines
			int count = 0;
			StringBuilder sb = new StringBuilder();
			while (fileInput.hasNextLine()) {
				sb.append(fileInput.nextLine() + "\n");  
				count++; 
			}

			// splitting the stringbuilder lines into an array of lines
			String[] content = sb.toString().split("\n"); //note: "\\s+" splits on 1+ white spaces
			// initiating the colorVotes array # of rows based on rows counted
			colorVotes = new String[count][];
			// running through the rows and splitting each row into individual arrays of the
			// contents
			for (int i = 0; i < content.length; i++) {
				String[] inputArray = content[i].toString().split(",");

				// trimming the individual words AND adding the word to the sbVotedColors
				// stringbuilder if its not already inside
				for (int j = 0; j < inputArray.length; j++) {
					inputArray[j] = inputArray[j].toString().trim();
					if (sbVotedColors.toString().contains(inputArray[j])) {
						continue;
					} else {
						sbVotedColors.append(inputArray[j] + ",");
					}
				}
				colorVotes[i] = inputArray; // putting the row of words into the rows of colorVotes
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found");
			e.printStackTrace();
			System.exit(0);
		}

		// for testing purposes only
//		for (int i = 0; i < colorVotes.length; i++) {
//			for (int j = 0; j < colorVotes[i].length; j++) {
//				System.out.print(colorVotes[i][j] + " ");
//			}
//			System.out.println();
//		}

		// for testing
//		System.out.println(sbVotedColors.toString());

		// putting the voted colors into the array and initialize size of voteCounts
		votedColors = sbVotedColors.toString().split(",");
		voteCounts = new int[votedColors.length];
//		for (String s : votedColors) {
//			System.out.println(s);
//		}	

	}

	/**
	 * calculateVoteCounts() counts the number of votes for each color in the
	 * votedColors array and loads the count in voteCounts array. The vote count for
	 * the color in votedColors[i] is stored in voteCounts[i]
	 */
	void calculateVoteCounts() {

		for (int i = 0; i < colorVotes.length; i++) {
			for (int j = 0; j < (colorVotes[i].length); j++) {
				for (int k = 0; k < votedColors.length; k++) {
					if (colorVotes[i][j].equalsIgnoreCase(votedColors[k])) {
						voteCounts[k]++;
					}
				}
			}

		}

	}

	/**
	 * printVoteCounts[] prints the output as shown in the hand-out using
	 * votedColors and voteCounts arrays
	 */
	void printVoteCounts() {
		for (int i = 0; i < votedColors.length; i++) {
			System.out.printf("Number of votes for %s color = %d%n", votedColors[i], voteCounts[i]);
		}
	}

}

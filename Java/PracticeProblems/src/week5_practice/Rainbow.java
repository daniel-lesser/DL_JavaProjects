package week5_practice;

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

		
		Scanner userFile = new Scanner(System.in);
		Scanner fileInput;
		Scanner rowFileCount;

		String fileName;
		File fileIn;

		int rowCount = 0;
		int columnCount = 0;

		System.out.println("Please enter the name of the file to read: ");
		fileName = userFile.next();
		StringBuilder sb = new StringBuilder();
		
		//remove this once builder code below works.
		//votedColors = new String [] {"Violet", "Indigo", "Blue", "Green", "Yellow", "Orange", "Red"};
		//create string builder, if builder already 
		//contains the [][] value, dont add, otherwise add, then split into array
		
		try {
			fileIn = new File(fileName);
			fileInput = new Scanner(fileIn);

			while (fileInput.hasNextLine()) {
				sb.append(fileInput.nextLine() + "\n");
				rowCount++;
			}
			
			//testing => works
			//System.out.println(sb.toString());
			
			//this is to find out which colors were voted for.
			String votedColorsContent[] = sb.toString().split("\n");
			StringBuilder votedColorsSB = new StringBuilder();
			for (int i = 0; i < rowCount; i++) {
				String [] s = votedColorsContent[i].toString().split(",");
				for (int k = 0; k < s.length; k++) {
					s[k] = s[k].trim();
				}
				for (int j = 0; j < s.length; j++) {
					if (votedColorsSB.toString().contains(s[j].trim())) {
						continue;
					}
					else {
						votedColorsSB.append(s[j].trim() + " ");
					}
				}
				
			}

			//converts the votedColors stringbuilder to the votedColors array
			votedColors = votedColorsSB.toString().split(" ");
			for (int i = 0; i < votedColors.length; i++) {
				votedColors[i] = votedColors[i].trim();
			}
			
//			for (int i = 0; i < votedColors.length; i++) {
//				System.out.println(votedColors[i] + ";");
//			}
			
			//this is to split out all the votes into the multidimensional array
			String content[] = sb.toString().split("\n");
			colorVotes = new String[rowCount][];

			for (int i = 0; i < rowCount; i++) {
				String[] s = content[i].toString().split(",");
				String[] insideArray = new String[s.length];

				for (int j = 0; j < s.length; j++) {
					insideArray[j] = s[j].toString().trim();
				}
				colorVotes[i] = insideArray;
			}

		}

		catch (FileNotFoundException e) {
			System.out.println("Sorry, the file you entered could not be found");
			e.printStackTrace();
			System.exit(0);
		}

//testing => works
//		System.out.println("---------");
//		for (int i = 0; i < colorVotes.length; i++) {
//			for (int j = 0; j < colorVotes[i].length; j++) {
//				System.out.print(colorVotes[i][j] + ", ");
//			}
//			System.out.println();
//		}
//		System.out.println("---------");
		

		
	}

	/**
	 * calculateVoteCounts() counts the number of votes for each color in the
	 * votedColors array and loads the count in voteCounts array. The vote count for
	 * the color in votedColors[i] is stored in voteCounts[i]
	 */
	void calculateVoteCounts() { 
		
		voteCounts = new int[] {0,0,0,0,0,0,0};
		
		for (int i = 0; i < colorVotes.length; i++) {
			for (int j = 0; j < colorVotes[i].length; j++) {
				
				if (colorVotes[i][j].equalsIgnoreCase(votedColors[0])) {
					voteCounts[0]+= 1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[1])) {
					voteCounts[1]+=1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[2])) {
					voteCounts[2]+=1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[3])) {
					voteCounts[3]+=1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[4])) {
					voteCounts[4]+=1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[5])) {
					voteCounts[5]+=1;
				}
				else if (colorVotes[i][j].equalsIgnoreCase(votedColors[6])) {
					voteCounts[6]+=1;
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

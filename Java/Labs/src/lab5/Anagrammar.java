//Daniel Lesser dlesser
package lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrammar {
	String[] words; // stores all words from the dictionary
//	String filename, clue;
	boolean isInDictionary; // true if the clue word exists in dictionary
	boolean hasAnagrams; // true if the clue word has anagrams
	String[] anagramArray; // stores all anagrams of clue-word, if found

	// DO NOT change main method
//	public static void main(String[] args) {
//		Anagrammar ag = new Anagrammar();
//		ag.getInputs();
//		ag.loadWords();
//		ag.findAnagrams();
//		ag.printResult();
//	}

	// DO NOT change loadWords method
	/**
	 * loadWords method reads the file and loads all words into the words array
	 */
	void loadWords() {
		Scanner fileInput = null;
		StringBuilder fileContent = new StringBuilder();
		try {
			fileInput = new Scanner(new File("Dictionary.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (fileInput.hasNextLine())
			fileContent.append(fileInput.nextLine() + "\n");
		words = fileContent.toString().split("\n");
	}

	/**
	 * getInputs method takes two inputs from the user to initialize the member
	 * variables filename and clue
	 */
//	void getInputs() {
//		System.out.println("Enter file name");
//
//		Scanner fileIn = new Scanner(System.in);
//		filename = fileIn.nextLine();
//
//		System.out.println("Enter the word");
//		clue = fileIn.nextLine().toLowerCase().trim();
//		fileIn.close();
//	}

	/**
	 * findAnagrams method traverses the words array and looks for anagrams of the
	 * clue. While doing so, if the clue-word itself is found in words array, it
	 * sets isInDictionary to true. If it finds any anagram of the clue, it sets
	 * hasAnagram to true. It loads the anagrams into anagramArray
	 */
	void findAnagrams(String clue) {

		for (String s : words) {
			if (s.equalsIgnoreCase(clue)) {
				isInDictionary = true;
				break;
			} else {
				isInDictionary = false;
			}
		}

		// testing
//		System.out.println(isInDictionary);

		char[] clueCharArray = clue.toCharArray();

		// testing
//		for (char c : clueCharArray) {
//			System.out.println(c);
//		}

		Arrays.sort(clueCharArray);

		// testing
//		for (char c : clueCharArray) {
//			System.out.println(c);
//		}

		int count = 0;
		StringBuilder anagrams = new StringBuilder();

		for (String s : words) {
			s = s.toLowerCase();
			char[] wordsCharArray = s.toCharArray();
			Arrays.sort(wordsCharArray);

			if (Arrays.equals(wordsCharArray, clueCharArray)) {
				if (s.equalsIgnoreCase(clue)) {
					continue;
				} else {
					anagrams.append(s + " ");
					count++;
				}

			}

		}

		// testing
//		System.out.println("count: " + count);
//		System.out.println(anagrams);

		if (count != 0) {
			anagramArray = new String[count];
			anagramArray = anagrams.toString().split(" ");
			hasAnagrams = true;
		} else {
			anagramArray = null;
			hasAnagrams = false;
		}

//		System.out.println("anagramArray length is " + anagramArray.length);

		// testing
//		for (String s : anagramArray) {
//			System.out.println(s);
//		}

	}

	/**
	 * printResult method prints the outputs as shown in the lab-handout
	 */
//	void printResult() {
//
//		if (isInDictionary == false) {
//			System.out.println(clue + " not found in the dictionary");
//		}
//
//		if (anagramArray == null) {
//			System.out.println("Sorry! No anagram found!");
//		} else {
//			System.out.println(anagramArray.length + " anagram(s) found");
//			for (int i = 0; i < anagramArray.length; i++) {
//				System.out.print((i + 1) + ". ");
//				System.out.println(anagramArray[i]);
//			}
//		}
//
//	}
}

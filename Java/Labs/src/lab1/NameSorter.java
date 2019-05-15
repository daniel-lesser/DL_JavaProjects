//dlesser Daniel Lesser

package lab1;

import java.util.Arrays;
import java.util.Scanner;

/**
 * NameSorter class takes n number of names in the form of string inputs from
 * the user. It then asks user for which name to search for. It sorts the names
 * entered by the user, and then prints the position of the search-name in the
 * sorted list of the names, if it is found.
 */

public class NameSorter {
	Scanner input = new Scanner(System.in);

	/**
	 * getNameInputs takes an int parameter n and creates an array of size n. It
	 * then asks user to Enter n names that get stored in the array. It uses the
	 * helper method toTitleCase() to convert all names to Title case. It returns
	 * the array filled with names entered by the user.
	 */
	String[] getNameInputs(int n) {

		String[] nameArray = new String[n];
		for (int i = 0; i < nameArray.length; i++) {
			System.out.println("Enter name " + (i+1));
			nameArray[i] = input.next();
		}

		for (int k = 0; k < nameArray.length; k++) {
			nameArray[k] = toTitleCase(nameArray[k]);
		}

		// here for testing purposes only
//		for (int p = 0; p < nameArray.length; p++) {
//			System.out.println(nameArray[p]);
//		}

		return nameArray;
	}

	/**
	 * toTitleCase() takes one string argument as name and returns the string in
	 * title case. If the name is null or the string is empty, it returns null.
	 */
	String toTitleCase(String name) {
		// write your code here

		if (name == null || name.trim() == "") {
			return null;
		} else {
			String temp = "";

			char c = name.charAt(0);
			temp = name.substring(1, name.length()).toLowerCase();
			c = Character.toUpperCase(c);
			temp = c + temp;

			return temp;
		}

	}

	/**
	 * sortAndSearch() takes two arguments. The first is an array of strings and the
	 * second is a searchString. The method first sorts the array in increasing
	 * alphabetical order, and prints it in that order. It then searches for the
	 * searchString in a case-insensitive way. If the searchString is found, it
	 * returns the position of the searchString according to the sorted list. If it
	 * is not found, then it returns -1.
	 */
	int sortAndsearch(String[] strings, String searchString) {
		// write your code here
		if (strings != null) {
			Arrays.sort(strings);
		}

		if (searchString != null) {
			System.out.println("*** Names in sorted order ***");
			for (int i = 0; i < strings.length; i++) {
				System.out.println((i+1) + ". " + strings[i]);
			}
			for (int j = 0; j < strings.length; j++) {
				if (searchString.equalsIgnoreCase(strings[j])) {
					return j;
				}
			}

		}

		return -1;

	}

	/** DO NOT CHANGE THIS METHOD */
	public static void main(String[] args) {
		NameSorter ns = new NameSorter();
		System.out.println("*** How many names to store? ***");
		int n = ns.input.nextInt();
		if (n > 0) {
			String[] names = ns.getNameInputs(n);
			System.out.println("*** Enter the name to search ***");
			String name = ns.input.next();
			int position = ns.sortAndsearch(names, name);
			if (position >= 0)
				System.out.println(name + " found at position " + (position + 1));
			else
				System.out.println("Sorry! " + name + " not found!");
		} else
			System.out.println("Good Bye!");
	}
}

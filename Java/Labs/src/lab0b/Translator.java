package lab0b;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Translator takes an integer as inputs and print all its digits as their
 * word-equivalents. For example, if user enters 365, then the program prints
 * 'Three Six Five'.
 */
public class Translator {

	// The translate() method takes an int argument and
	// returns a string with word equivalents for all digits in the int
	// For example, if n = 365, then translate(n) will return the string 'Three Six
	// Five'
	String translate(int n) {

		int holder = 0;
		if (n == 0) {
			return "Zero";
		}
		
		if (n < 0) {
			holder = n;
			n = Math.abs(n);
		}
		
		ArrayList<Integer> myArray = new ArrayList<Integer>();
		int sum = n;
		ArrayList<String> myArrayString = new ArrayList<String>();

		while (sum > 0) {
			myArray.add(sum % 10);
			sum = sum / 10;
		}

		for (int i = 0; i < myArray.size(); i++) {
			switch (myArray.get(i)) {
			case 0: {
				myArrayString.add("Zero");
				break;
			}
			case 1: {
				myArrayString.add("One");
				break;
			}
			case 2: {
				myArrayString.add("Two");
				break;
			}
			case 3: {
				myArrayString.add("Three");
				break;
			}
			case 4: {
				myArrayString.add("Four");
				break;
			}
			case 5: {
				myArrayString.add("Five");
				break;
			}
			case 6: {
				myArrayString.add("Six");
				break;
			}
			case 7: {
				myArrayString.add("Seven");
				break;
			}
			case 8: {
				myArrayString.add("Eight");
				break;
			}
			case 9: {
				myArrayString.add("Nine");
				break;
			}

			}
		}
		
		String result = "";
		
		if (holder < 0) {
			result = "Minus ";
		}
		result = result + myArrayString.get((myArrayString.size()-1));
		for (int k = 2; k <= myArrayString.size(); k++) {
			result = result + " " + myArrayString.get((myArrayString.size()-k));
		}

		// write your code here
		return result;
	}

	// DO NOT CHANGE THIS METHOD
	// The main method takes user input as an integer
	// and invokes the translate() method to print its word-equivalent
	public static void main(String[] args) {
		Translator t = new Translator();
		System.out.println("Enter an integer");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		System.out.println(t.translate(n));
		input.close();
	}
}

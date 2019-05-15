package week4_practice;

import java.util.ArrayList;

public class Twister {

	public String[] twist(String[] names) {
		
		String tempString = "";
		
		for (int i = 0; i < (names.length / 2); i++) {
			tempString = names[names.length-1-i];
			names[names.length-1-i] = names[i];
			names[i] = tempString;
		}
		
		char [] charArray;
		char tempChar;
		for (int j = 0; j < names.length; j++) {
			StringBuilder builder = new StringBuilder();

			charArray = names[j].toCharArray();
			for (int k = 0; k <(charArray.length / 2); k++) {
				tempChar = charArray[charArray.length-1-k];
				charArray[charArray.length-1-k] = charArray[k];
				charArray[k] = tempChar;
			}

			for (int p = 0; p < charArray.length; p++) {
				builder.append(charArray[p]);
			}
			
			names[j] = builder.toString();
			
		}
		
//		//testing
//		for (String i : names) {
//			System.out.println(i);
//		}
		
		return names;
	}

	public int[] twist(int[] numbers) {
		
		int temp = 0;
		for (int i = 0; i < (numbers.length / 2); i++) {
			temp = numbers[numbers.length-1-i];
			numbers[numbers.length-1-i] = numbers[i];
			numbers[i] = temp;
		}
		
		ArrayList<Integer> myInt = new ArrayList<Integer>();
		
		for (int j = 0; j < numbers.length; j++) {
			StringBuilder builder = new StringBuilder();
			myInt.clear();
			int sum = numbers[j];
			do {
				myInt.add(sum % 10);
				sum = sum/10;
			}
			while (sum > 0); 
			
			Object[] myIntArray = myInt.toArray();
			
			for (int p = 0; p < myInt.size(); p++) {
				builder.append(myIntArray[p]);
			}
			
			numbers[j] = Integer.parseInt(builder.toString());
			
		}
		
		//testing
//		for (int i : numbers) {
//			System.out.println(i);
//		}
		
		return numbers;
	}

	public static void main(String[] args) {

		String[] someNames = { "James", "Anne", "Zach", "Bob" };
		int[] someNumbers = { 43, 67, 12, 90 };

		Twister test = new Twister();

		String[] someNamesReversed = test.twist(someNames);
		int[] someNumbersReversed = test.twist(someNumbers);

		
		System.out.println("*** Twisted names ***");
		for (int i = 0; i < someNamesReversed.length; i++) {
			System.out.println(someNamesReversed[i]);
		}

		System.out.println("*** Twisted numbers ***");
		for (int i = 0; i < someNumbersReversed.length; i++) {
			System.out.println(someNumbersReversed[i]);
		}

	}

}

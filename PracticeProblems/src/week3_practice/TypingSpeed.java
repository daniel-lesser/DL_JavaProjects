package week3_practice;

import java.util.Scanner;

public class TypingSpeed {

	public static void main(String[] args) {

		String testSentence = "This is a test sentence to test your typing proficiency.  Type this as fast as you can!";
		double time = 0;
		
		System.out.println(testSentence);
		Scanner input = new Scanner(System.in);
		
		long startTime = System.currentTimeMillis() / 1000;
		String myString = input.nextLine();				
		long endTime = System.currentTimeMillis() / 1000;
		
		String [] myStringArray = myString.split(" ");
		
		time = (endTime - startTime);
		double wordsPerMin = myStringArray.length / time * 60;
		
		System.out.println("time in seconds: " + time);
		System.out.println("string words: " + myStringArray.length);

		
		if (myString.equals(testSentence)) {
			System.out.println("Your typing is accurate!");
			System.out.println("Your speed is " + wordsPerMin + " words per min");
		}
		else if (myString.equalsIgnoreCase(testSentence)) {
			System.out.println("Your typing good, but you made some case-errors!");
			System.out.println("Your speed is " + wordsPerMin + " words per min");
		}
		else {
			System.out.println("Your typing has errors!");
			System.out.println("Your speed is " + wordsPerMin + " words per min");
		}
		
		
	}

}

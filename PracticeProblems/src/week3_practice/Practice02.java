package week3_practice;

import java.util.Scanner;

public class Practice02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Please enter the numbers, separated by a space");

		Scanner input = new Scanner(System.in);

		String numbers = input.nextLine();

		Scanner tokens = new Scanner(numbers);

		double sum = 0;
		while (tokens.hasNext()) {
			sum += tokens.nextDouble();
		}
		
		System.out.println("The sum is: " + sum);

	}

}

//Daniel Lesser dlesser

package lab0b;

import java.math.BigInteger;
import java.util.Scanner;

public class Factorial {
	public int calculateFactorial(int number) {
		// write your code here to calculate factorial using data-type as int
		// it should take care of number being 0 or negative as well.
		int result = number;

		if (number <= 0) {
			result = 1;
			return result;
		}

		else {
			for (int i = 1; i < number; i++) {
				result = result * (number - i);
			}
			return result;
		}

	}

	public BigInteger calculateBigFactorial(int number) {
		// write your code here to calculate factorial using data-type as BigInteger
		// it should take care of number being 0 or negative as well.

		BigInteger result = BigInteger.valueOf(number);

		if (number <= 0) {
			result = BigInteger.ONE;
			return result;
		}

		else {
			for (int i = 1; i < number; i++) {
				result = result.multiply(BigInteger.valueOf(number-i));
			}
			return result;
		}

	}

	public static void main(String[] args) {
		Factorial f = new Factorial(); // create Factorial object

		System.out.println("Factorial upto which number?"); // display console message
		Scanner input = new Scanner(System.in); // create object to capture keyboard input
		int number = input.nextInt(); // get user input
		input.close();
		// Write code here to print the output as shown
		System.out.println("Factorials using int");
		for (int i = 1; i <= number; i++) {
			System.out.printf(i + "!" + " = ");
			System.out.println(f.calculateFactorial(i));
		}
		System.out.println("Factorials using BigInteger");
		for (int i = 1; i <= number; i++) {
			System.out.printf(i + "!" + " = ");
			System.out.println(f.calculateBigFactorial(i));
		}

	}
}

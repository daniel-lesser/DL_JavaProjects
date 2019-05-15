package series_practice10;

import java.util.Scanner;

public class Factorial extends Series {

	@Override
	public int getNthNumber(int number) {
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

	public static void main(String[] args) {
		Factorial f = new Factorial(); // create Factorial object

		System.out.println("Factorial up to which number?"); // display console message
		Scanner input = new Scanner(System.in); // create object to capture keyboard input
		int number = input.nextInt(); // get user input
		input.close();
		// Write code here to print the output as shown
		System.out.println("Factorials using int");
		for (int i = 1; i <= number; i++) {
			System.out.printf(i + "!" + " = ");
			System.out.println(f.getNthNumber(i));
		}
	}

}

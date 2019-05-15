package dinner_Practice;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Scanner;

/**
 * This is a dinner simulation program to illustrate several OO concepts such as
 * abstract classes, interfaces, static variables, static final constants, and
 * polymorphism. The Dinner class starts the program.
 */

public class Dinner {

	/**
	 * main method prints the menu and takes user input in the variable 'choice'. It
	 * then invokes the getFood() method and passes the choice to it. After
	 * getFood() method returns the appropriate Food object, the main method invokes
	 * the eatFood() method and passes that Food object to it. It then prints how
	 * many calories have been consumed. The above is repeated in a loop till the
	 * user enters 'n' to the question, 'Want some more?'
	 */
	public static void main(String[] args) {

		String answer = "y";

		do {
			Dinner dinner = new Dinner();
			int choice = 0;
			System.out.println("What would you like to eat?");
			System.out.println("1. Pizza");
			System.out.println("2. Chips");
			System.out.println("3. Ice cream");

			Scanner input = new Scanner(System.in);
			choice = input.nextInt();

			Food f = dinner.getFood(choice);
			dinner.eatFood(f);

			System.out.println("You have consumed " + f.calories + " calories");
			System.out.println("Want some more (y/n)");
			answer = input.next();
		}

		while (answer.equals("y"));
		System.out.println("Good night!");

	}

	/**
	 * getFood() takes choice and returns Pizza object, Chips object, or IceCream
	 * object for choices 1, 2, and 3 respectively.
	 */
	public Food getFood(int choice) {

		Food f;
		switch (choice) {

		case (1): {
			return f = new Pizza();
		}

		case (2): {
			return f = new Chips();
		}

		case (3): {
			return f = new IceCream();
		}

		default: {
			System.out.println("You did not enter a choice between 1 and 3.  Exiting");
			System.exit(0);
			return null;
		}

		}

	}

	/**
	 * eatFood() method takes Food object as an argument. It checks if the object is
	 * Pizza. If yes, then it invokes its heatIt() method Then it invokes the eat()
	 * method of the Food object.
	 */

	public void eatFood(Food f) {

		if (f instanceof Pizza) {
			((Pizza) f).heatIt();
			System.out.println("Now its hot @ " + f.temperature + " degrees");
		}
		System.out.println(f.eat());

	}
}

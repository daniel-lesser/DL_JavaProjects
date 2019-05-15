package diner_practice9;

import java.text.NumberFormat;
import java.util.Random;

public class Diner {

	static Guest guestTypes[] = { new Family(), new Couple(), new Group(), new Individual() };
	static int[] guestCount = { 0, 0, 0, 0 };
	static int countOfDiners = 0;

	static int drinkPrice = 2;
	static int soupPrice = 3;
	static int saladPrice = 5;
	static int entreePrice = 7;
	static int dessertPrice = 4;
	static int[] menuPrices = { drinkPrice, soupPrice, saladPrice, entreePrice, dessertPrice };
	static int bill = 0;

	public void runDiner() {

		Kitchen dinersKitchen = new Kitchen();
		while (dinersKitchen.canServe == true) { // checking that we were able to serve the last guest

			// generates a random guest type and assigns them to be the current guest
			Random r = new Random();
			int dinerType = r.nextInt(4);
			Guest guest = guestTypes[dinerType];

			// checks that we can serve the guest, and if so, serves them, updates stock,
			// and increases count
			dinersKitchen.updateStock(guest);
			if (dinersKitchen.canServe == true)
				guestCount[dinerType]++;

		}

		calculateBill();
		printReport(dinersKitchen.getStock());

	}

	public int calculateBill() {

		int numDrinks = 0, numSoup = 0, numSalad = 0, numEntree = 0, numDessert = 0;
		int[] receipts = { numDrinks, numSoup, numSalad, numEntree, numDessert };

		// counting up how many of each type of dish has been eaten across all guest
		// types
		for (int i = 0; i < guestTypes.length; i++) {
			for (int j = 0; j < receipts.length; j++) {
				receipts[j] = receipts[j] + (guestCount[i] * guestTypes[i].allOrders[j]);
				// adds current receipt count to (the # of guest of certain type * # of dishes
				// for that type)
			}
			countOfDiners = countOfDiners + guestCount[i];
		}

		for (int i = 0; i < receipts.length; i++) {
			bill = bill + receipts[i] * menuPrices[i];
		}

		return bill;
	}

	public void printReport(int[] stock) {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		System.out.println("******* Total income for the day: " + formatter.format(bill) + " *******");
		System.out.println("** Guest type count **");
		System.out.println("Family : " + guestCount[0]);
		System.out.println("Couple : " + guestCount[1]);
		System.out.println("Individual : " + guestCount[3]);
		System.out.println("Group : " + guestCount[2] + "\n");
		System.out.println("******* Total number of orders: " + countOfDiners + " *******");
		System.out.println("** Closing Stock **\n");
		System.out.println("Drink: " + stock[0]);
		System.out.println("Soup: " + stock[1]);
		System.out.println("Salad: " + stock[2]);
		System.out.println("Entree: " + stock[3]);
		System.out.println("Dessert: " + stock[4]);

	}

	public static void main(String[] args) {
		Diner d = new Diner();
		d.runDiner();
	}

}

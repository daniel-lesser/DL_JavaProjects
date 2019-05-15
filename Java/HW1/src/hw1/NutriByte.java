//Daniel Lesser dlesser
package hw1;

import java.util.Scanner;

public class NutriByte {
	Model model = new Model(); // will handle all data read from the data files
	Scanner input = new Scanner(System.in); // to be used for all console i/o in this class

//	static final String PRODUCT_FILE = "data/Products.csv";
//	static final String NUTRIENT_FILE = "data/Nutrients.csv";
//	static final String SERVING_SIZE_FILE = "data/ServingSize.csv";

	static final String PRODUCT_FILE = "data/SampleProducts.csv";
	static final String NUTRIENT_FILE = "data/SampleNutrients.csv";
	static final String SERVING_SIZE_FILE = "data/SampleServingSize.csv";

	// do not change this method
	public static void main(String[] args) {
		NutriByte nutriByte = new NutriByte();
		nutriByte.model.readProducts(PRODUCT_FILE);
		nutriByte.model.readNutrients(NUTRIENT_FILE);
		nutriByte.model.readServingSizes(SERVING_SIZE_FILE);
		switch (nutriByte.getMenuChoice()) {
		case 1: {
			nutriByte.printSearchResults(
					nutriByte.searchProductsWithSelectedIngredients(nutriByte.getIngredientChoice()));
			break;
		}
		case 2: {
			int nutrientChoice = nutriByte.getNutrientChoice();
			nutriByte.printSearchResults(nutriByte.searchProductsWithSelectedNutrient(nutrientChoice), nutrientChoice);
			break;
		}
		case 3:
		default:
			System.out.println("Good Bye!");
			break;
		}
	}

	// do not change this method
	int getMenuChoice() {
		System.out.println("*** Welcome to NutriByte ***");
		System.out.println("--------------------------------------------------");
		System.out.println("1. Find ingredient(s)");
		System.out.println("2. Find a nutrient");
		System.out.println("3. Exit");
		input = new Scanner(System.in);
		return input.nextInt();
	}

	// do not change this method
	String getIngredientChoice() {
		input.nextLine();
		System.out.println("Type the keywords to search for the ingredients");
		System.out.println("--------------------------------------------------");
		return input.nextLine();
	}

	int getNutrientChoice() {

		System.out.println("Select the nutrient you are looking for");
		System.out.println("--------------------------------------------------");

		int counter = 1; // for numbering
		int counterNut = 0; // for indexing
		double numRows = Math.ceil(model.nutrients.length / 3); // to count number of rows needed
		double extraItems = model.nutrients.length % numRows; // to count number of extra items required

		// prints 3 columns of nutrients for the user to search against
		for (int i = 0; i < numRows; i++) {
			System.out.printf("%-2d. %-40s", counter, model.nutrients[counterNut].getNutrientName());
			System.out.printf("%-2d. %-40s", counter + 1, model.nutrients[counterNut + 1].getNutrientName());
			System.out.printf("%-2d. %-40s", counter + 2, model.nutrients[counterNut + 2].getNutrientName());
			System.out.println();

			counter += 3;
			counterNut += 3;
		}

		// prints additional row of remaining items if necessary
		if (extraItems == 1) {
			System.out.println((counter) + ". " + model.nutrients[counterNut]);
			System.out.println();
		}
		if (extraItems == 2) {
			System.out.printf("%-2d. %-40s", counter, model.nutrients[counterNut].getNutrientName());
			System.out.printf("%-2d. %-40s", counter + 1, model.nutrients[counterNut + 1].getNutrientName());
			System.out.println();
		}

		return input.nextInt();
	}

	Product[] searchProductsWithSelectedIngredients(String searchString) {

		// take the input string and break into an array of strings based on input.
		StringBuilder sb = new StringBuilder();
		sb.append(searchString);
		String ingredients[] = sb.toString().split(" ");
		for (int i = 0; i < ingredients.length; i++) {
			ingredients[i] = ingredients[i].trim();
		}

		// running through the products array to find out the number of products that
		// have the ingredient
		int count = 0;
		for (int i = 0; i < model.products.length; i++) {
			boolean flag = false;
			for (int j = 0; j < ingredients.length; j++) {
				if (model.products[i].getIngredients().toLowerCase().contains(ingredients[j].toLowerCase())) {
					count++;
					break;
				}
			}
		}

		// returns an array of size zero if no matching products
		if (count == 0) {
			Product zeroProducts[] = new Product[0];
			return zeroProducts;
		}

		// initializes an array with the correct number of matching products
		Product productWithIngredients[] = new Product[count];

		// fills an array with the relevant products
		int counter = 0;
		for (int i = 0; i < model.products.length; i++) {
			for (int j = 0; j < ingredients.length; j++) {
				if (model.products[i].getIngredients().toLowerCase().contains(ingredients[j].toLowerCase())) {
					productWithIngredients[counter] = model.products[i];
					counter++;
					break;
				}
			}
		}

		// for testing purposes
//		for (int i = 0; i < productWithIngredients.length; i++) {
//			System.out.println(i + " " + productWithIngredients[i].getProductName() + "--------"
//					+ productWithIngredients[i].getIngredients());
//		}

		return productWithIngredients;
	}

	Product[] searchProductsWithSelectedNutrient(int menuChoice) {

		// grabbing nutrient code based on menuChoice (-1 to counteract numbering)
		String enteredNutrient = model.nutrients[menuChoice - 1].getNutrientCode().trim();
		String ndbNumber = "";

		// looping through the productNutrients array to identify how many products have
		// the nutrient AND have a measurement quantity > 0
		int count = 0;
		for (int i = 0; i < model.productNutrients.length; i++) {
			if ((model.productNutrients[i].getNutrientCode().trim().equalsIgnoreCase(enteredNutrient))
					&& (model.productNutrients[i].getQuantity() > 0)) {
				count++;
			}
		}

		// read all the product ndb id numbers into an array to be cross referenced
		// against
		String productsNumsWithNutrients[] = new String[count];
		int counter1 = 0;
		for (int i = 0; i < model.productNutrients.length; i++) {
			if ((model.productNutrients[i].getNutrientCode().trim().equalsIgnoreCase(enteredNutrient))
					&& (model.productNutrients[i].getQuantity() > 0)) {
				productsNumsWithNutrients[counter1] = model.productNutrients[i].getNdbNumber();
				counter1++;
			}
		}

		// adding products to an array that have the nutrient
		Product productsWithNutrient[] = new Product[count];
		int counter2 = 0;
		for (int i = 0; i < model.products.length; i++) {
			for (int j = 0; j < productsNumsWithNutrients.length; j++) {
				if (model.products[i].getNdbNumber().equalsIgnoreCase(productsNumsWithNutrients[j])) {
					productsWithNutrient[counter2] = model.products[i];
					counter2++;
					break;
				}
			}
		}

		return productsWithNutrient;
	}

	void printSearchResults(Product[] searchResults) {

		System.out.println("*** " + searchResults.length + " products found ***");

		// prints out the products (and manufacturers) that have the ingredient if there
		// were any
		if (searchResults.length > 0) {
			for (int i = 0; i < searchResults.length; i++) {
				System.out.println((i + 1) + ". " + searchResults[i].getProductName() + " from "
						+ searchResults[i].getManufacturer());
			}
		}

	}

	void printSearchResults(Product[] searchResults, int nutrientChoice) {

		System.out.println("*** " + searchResults.length + " products found ***");
		String nutrientCode = model.nutrients[nutrientChoice - 1].getNutrientCode();

		// prints out the household measurement, product number, product name and
		// output_uom for the products that matched the nutrientChoice
		if (searchResults.length > 0) {
			for (int i = 0; i < searchResults.length; i++) {

				// grabbing the quantity for the product
				float quantity = 0f;
				for (int j = 0; j < model.productNutrients.length; j++) {
					if ((model.productNutrients[j].getNdbNumber().equalsIgnoreCase(searchResults[i].getNdbNumber()))
							&& (model.productNutrients[j].getNutrientCode().equalsIgnoreCase(nutrientCode))) {
						quantity = model.productNutrients[j].getQuantity();
						break;
					}
				}

				float content;
				content = (float) (searchResults[i].getServingSize() * quantity) / 100f;

				System.out.printf("%d . %3.2f %s of %s: %s has %3.2f g of %s", (i + 1),
						searchResults[i].getHouseholdSize(), searchResults[i].getHouseholdUom(),
						searchResults[i].getNdbNumber(), searchResults[i].getProductName(), content,
						model.nutrients[nutrientChoice - 1].getNutrientName());
				System.out.println();
			}
		}

	}
}

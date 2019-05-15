//Daniel Lesser dlesser
package hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Model {

	// for testing purposes only!
//	static final String PRODUCT_FILE = "data/SampleProducts.csv";
//	static final String NUTRIENT_FILE = "data/SampleNutrients.csv";
//	static final String SERVING_SIZE_FILE = "data/SampleServingSize.csv";
	
//	static final String PRODUCT_FILE = "data/Products.csv";
//	static final String NUTRIENT_FILE = "data/Nutrients.csv";
//	static final String SERVING_SIZE_FILE = "data/ServingSize.csv";

	// arrays to be filled by respective read methods
	Product products[];
	Nutrient nutrients[];
	ProductNutrient productNutrients[];

	// reads the product.csv file and loads product objects into the product array
	public void readProducts(String fileName) {

		// opening two scanners, one to read # of lines, one to read data.
		try {

			// opening the file streams
			Scanner fileRowCount = new Scanner(new FileReader(fileName));
			Scanner fileRead = new Scanner(new FileReader(fileName));
			int rowCount = -1; // setting to -1 to omit header line

			// reading the row count
			while (fileRowCount.hasNextLine()) {
				fileRowCount.nextLine();
				rowCount++;
			}

			// initializing the products array
			products = new Product[rowCount];

			fileRead.nextLine(); // here to eat the first line of header text in the file

			// reads the file one line at a time and appends to the StringBuilder
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rowCount; i++) {
				sb.append(fileRead.nextLine() + "\n");
			}

			// breaks the StringBuilder into an array of rows of data
			String fileContent[] = sb.toString().split("\n");

			for (int i = 0; i < fileContent.length; i++) {
				// splitting the input on ","
				String tempArray[] = fileContent[i].toString().split("\",\"");

				// removes the extra " from first and last items in the array
//				System.out.println("Item number " + i); //for testing
				for (int j = 0; j < tempArray.length; j++) {
					tempArray[j] = tempArray[j].replaceAll("\"", "");
//					System.out.print(tempArray[j].toString() + "----\t"); //for testing
				}
//				System.out.println(); //for testing

				// for testing purposes only
//				System.out.println("line length " +tempArray.length);
//				for (String s : tempArray) {
//					System.out.print(s + "+");
//				}
//				System.out.println();

				// creates a new Product object and inputs it into the product array
				Product tempProduct = new Product(tempArray[0], tempArray[1], tempArray[4], tempArray[7]);
				products[i] = tempProduct;
			}

			// closing Scanners to prevent memory leakage
			fileRowCount.close();
			fileRead.close();
		}

		// catching file not found errors
		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.  Exiting");
			e.printStackTrace();
			System.exit(0);
		}

	}

	// reads the nutrients.csv file and loads nutrients objects into the nutrients
	// and productNutrients arrays
	public void readNutrients(String fileName) {

		// first go through the nutrients file to fill in the ProductNutrients array
		// opening two scanners, one to read # of lines, one to read data.
		try {
			// opening the file streams
			Scanner fileRowCount = new Scanner(new FileReader(fileName));
			Scanner fileRead = new Scanner(new FileReader(fileName));
			int rowCount = -1; // setting to -1 to omit header line

			// reading the row count
			while (fileRowCount.hasNextLine()) {
				fileRowCount.nextLine();
				rowCount++;
			}

			// initializing the products array
			productNutrients = new ProductNutrient[rowCount];

			fileRead.nextLine(); // here to eat the first line of header text in the file

			// reads the file one line at a time and appends to the StringBuilder
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rowCount; i++) {
				sb.append(fileRead.nextLine() + "\n");
			}

			// breaks the StringBuilder into an array of rows of data
			String fileContent[] = sb.toString().split("\n");

			for (int i = 0; i < fileContent.length; i++) {
				// splitting the input on ","
				String tempArray[] = fileContent[i].toString().split("\",\"");

				// removes the extra " from first and last items in the array
				for (int j = 0; j < tempArray.length; j++) {
					tempArray[j] = tempArray[j].replaceAll("\"", "");
				}

				// creates a new productNutrient object and inputs it into the productNutrients
				// array
				ProductNutrient tempProductNutrients = new ProductNutrient(tempArray[0], tempArray[1], tempArray[2],
						Float.valueOf(tempArray[4]), tempArray[5]);
				productNutrients[i] = tempProductNutrients;
			}

			// closing Scanners to prevent memory leakage
			fileRowCount.close();
			fileRead.close();
		}

		// catching file not found errors
		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.  Exiting");
			e.printStackTrace();
			System.exit(0);
		}

		// Second go through the nutrients file to fill in the nutrients array
		// opening two scanners, one to read # of lines, one to read data.
		try {
			// opening the file streams
			Scanner fileRowCount = new Scanner(new FileReader(fileName));
			Scanner fileRead = new Scanner(new FileReader(fileName));

			int rowCount = -1; // setting to -1 to omit header line

			// reading the row count
			while (fileRowCount.hasNextLine()) {
				fileRowCount.nextLine();
				rowCount++;
			}

			// initializing a interim nutrients array to grab ALL nutrient entries,
			// regardless of uniqueness
			Nutrient interimNutrients[] = new Nutrient[rowCount];

			fileRead.nextLine(); // here to eat the first line of header text in the file

			// reads the file one line at a time and appends to the StringBuilder
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rowCount; i++) {
				sb.append(fileRead.nextLine() + "\n");
			}

			// breaks the StringBuilder into an array of rows of data
			String fileContent[] = sb.toString().split("\n");

			for (int i = 0; i < fileContent.length; i++) {
				// splitting the input on ","
				String tempArray[] = fileContent[i].toString().split("\",\"");

				// removes the extra " from first and last items in the array
				for (int j = 0; j < tempArray.length; j++) {
					tempArray[j] = tempArray[j].replaceAll("\"", "");
				}

				// creates a new productNutrient object and inputs it into the interim Nutrients
				// array
				Nutrient tempNutrient = new Nutrient(tempArray[1], tempArray[2], tempArray[5]);
				interimNutrients[i] = tempNutrient;
			}

			// Running through the complete interim Nutrients array to identify number of
			// rows of uniqueness
			int countUniqueNutrients = 1; // start at one since first row inherently unique
			for (int i = 1; i < interimNutrients.length; i++) {
				// runs through all previous rows of interimNutrients data to compare current
				// row against. If no matches, increments the unique count.
				boolean flag = true;
				for (int j = 0; j < i; j++) {
					if (interimNutrients[i].getNutrientCode().equals(interimNutrients[j].getNutrientCode())) {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					countUniqueNutrients++;
				}
			}

			// initializing the nutrients array with the count of unique nutrients
			nutrients = new Nutrient[countUniqueNutrients];
			nutrients[0] = interimNutrients[0];
			// counter to keep tracking of which index to place data. Start at 1 to include
			// first row. If no matches are found, the nutrient is added.
			int counter = 1;
			for (int i = 1; i < interimNutrients.length; i++) {
				boolean flag = true;
				for (int j = 0; j < counter; j++) {
					if (interimNutrients[i].getNutrientCode().equals(nutrients[j].getNutrientCode())) {
						flag = false;
						break;
					}
				}
				if (flag == true) {
					nutrients[counter] = interimNutrients[i];
					counter++;
				}
			}

			// closing Scanners to prevent memory leakage
			fileRowCount.close();
			fileRead.close();
		}

		// catching file not found errors
		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.  Exiting");
			e.printStackTrace();
			System.exit(0);
		}

	}

	// reads the servingSize.csv and populates four variables: servingSize,
	// servingUom,
	// householdSize, householdUom for each product in the products array
	public void readServingSizes(String fileName) {

		// opening two scanners, one to read # of lines, one to read data.

		try {
			// opening the file streams
			Scanner fileRowCount = new Scanner(new FileReader(fileName));
			Scanner fileRead = new Scanner(new FileReader(fileName));
			int rowCount = -1; // setting to -1 to omit header line

			// reading the row count
			while (fileRowCount.hasNextLine()) {
				fileRowCount.nextLine();
				rowCount++;
			}

			fileRead.nextLine(); // here to eat the first line of header text in the file

			// reads the file one line at a time and appends to the StringBuilder
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < rowCount; i++) {
				sb.append(fileRead.nextLine() + "\n");
			}

			// breaks the StringBuilder into an array of rows of data
			String fileContent[] = sb.toString().split("\n");

			for (int i = 0; i < fileContent.length; i++) {
				// splitting the input on ","
				String tempArray[] = fileContent[i].toString().split("\",\"");

				// removes the extra " from first and last items in the array
				for (int j = 0; j < tempArray.length; j++) {
					tempArray[j] = tempArray[j].replaceAll("\"", "");
				}

				// for testing purposes only
//				System.out.println("line length " +tempArray.length);
//				for (String s : tempArray) {
//					System.out.print(s + "+");
//				}
//				System.out.println();

				// initializes the appropriate Product with its serving information
				for (int k = 0; k < products.length; k++) {
					if (tempArray[0].equals(products[k].getNdbNumber())) {
						if (tempArray[1].length()==0) products[k].setServingSize(0.0f);
						else products[k].setServingSize(Float.parseFloat(tempArray[1]));
						products[k].setServingUom(tempArray[2]);
						if (tempArray[3].length()==0) products[k].setHouseholdSize(0.0f);
						else products[k].setHouseholdSize(Float.parseFloat(tempArray[3]));
						products[k].setHouseholdUom(tempArray[4]);
					}

				}

			}

			// closing Scanners to prevent memory leakage
			fileRowCount.close();
			fileRead.close();
		}

		// catching file not found errors
		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.  Exiting");
			e.printStackTrace();
			System.exit(0);
		}

	}

	// for testing purposes only
	public static void main(String[] args) {

//		Model m = new Model();

		// testing products array and method
//		m.readProducts(PRODUCT_FILE);
//		for (int i = 0; i < m.products.length; i++) {
//			System.out.println("Product " + i + " is comprised of " + m.products[i].getNdbNumber() + " "
//					+ m.products[i].getProductName() + " " + m.products[i].getManufacturer() + " "
//					+ m.products[i].getIngredients());
//		}

//		m.readServingSizes(SERVING_SIZE_FILE);
		
//		for (int i = 0; i < m.products.length; i++) {
//			System.out.println("Product " + i + " is comprised of " + m.products[i].getNdbNumber() + " "
//					+ m.products[i].getProductName() + " " + m.products[i].getManufacturer() + " "
//					+ m.products[i].getIngredients() + " " + m.products[i].getServingSize() + " "
//					+ m.products[i].getServingUom() + " " + m.products[i].getHouseholdSize() + " "
//					+ m.products[i].getHouseholdUom());
//		}

		// testing productNutrients array and method
//		m.readNutrients(NUTRIENT_FILE);
//		for (int i = 0; i < m.productNutrients.length; i++) {
//			System.out.println("Product " + i + " is comprised of " + m.productNutrients[i].getNdbNumber() + " "
//					+ m.productNutrients[i].getNutrientCode() + " " + m.productNutrients[i].getNutrientName() + " "
//					+ m.productNutrients[i].getQuantity() + " " + m.productNutrients[i].getNutrientUom());
//		}

//		for (int i = 0; i < m.nutrients.length; i++) {
//			System.out.println(
//					"NUTRIENT CODE " + m.nutrients[i].getNutrientCode() + " IS CALLED " + m.nutrients[i].getNutrientName()
//							+ " AND ITS UNIT OF MEASUREMENT IS " + m.nutrients[i].getNutrientUom());
//		}

	}

}

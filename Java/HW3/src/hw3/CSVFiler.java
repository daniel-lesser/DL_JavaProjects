//Daniel Lesser / dlesser
package hw3;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

//takes the csv file name, reads the first line to identify 
//the type of person to create and their profile.
//creates a person object and assigns to NutriByte.person
//returns true if the file was read correctly, false otherwise
public class CSVFiler extends DataFiler {

	@Override
	public boolean readFile(String fileName) throws InvalidProfileException {

		// need to add exception handling (see hw3)
		CSVFormat csvFormat = CSVFormat.DEFAULT;
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(fileName), csvFormat);
			int rowCount = 0;
			for (CSVRecord csvRecord : csvParser) {
				if (rowCount == 0) { // person creation
					StringBuilder sb1 = new StringBuilder();
					for (int i = 0; i < csvRecord.size(); i++) {
						sb1.append(csvRecord.get(i).trim() + ",");
					}

					Person tempPerson = validatePersonData(sb1.toString());
					if (tempPerson != null) {
						NutriByte.person = tempPerson;
						rowCount++;
					} else {
						return false;
					}

				} else if (rowCount > 0) {
					// if not first row in file, creates a new product based on information
					// then calls the validateProductData() method to verify it is a valid product
					// and add in remaining details
					// before populating in the dietProductsList
					try {
						rowCount++;
						StringBuilder sb = new StringBuilder();
						for (int i = 0; i < csvRecord.size(); i++) {
							sb.append(csvRecord.get(i).trim() + ",");
						}

						String tempProductDetails = sb.toString();
						Product tempProduct;

						if (validateProductData(tempProductDetails) != null) {
							tempProduct = validateProductData(tempProductDetails);
							NutriByte.person.dietProductsList.add(tempProduct);
						}
						else {
							new InvalidProfileException("Cannot read: " + sb.toString()
									+ "\nThe data must be - String, Number, Number \nfor ndbNumber, serving size, household size");
						}
					} catch (InvalidProfileException e) {
						new InvalidProfileException("Cannot read: " + csvRecord
								+ "\nThe data must be - String, Number, Number \nfor ndbNumber, serving size, household size");
						
						// do nothing additional if the product doesn't exist as one in the list
						// prevents clearing of data in the controller class
					}

				} else
					return false;
			}
			// testing
//			System.out.println("dietproductslist size: " + NutriByte.person.dietProductsList.size());
//			for (Product p : NutriByte.person.dietProductsList) {
//				System.out.println("dietProductsList Item: " + p);
//			}
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}

		return false;
	}

	// throws InvalidProfileException if the profile does not meet the parameters
	public Person validatePersonData(String s) throws InvalidProfileException {
		String[] inputPerson = s.split(",");
		Person tempPerson;
		try {
			// checking for length of profile
			if (inputPerson.length < 5) {
				throw (new InvalidProfileException("Could not read profile data: Insufficient information provided"));
			}
			// checking for gender
			if (!inputPerson[0].trim().toLowerCase().equalsIgnoreCase("female")
					&& !inputPerson[0].trim().equalsIgnoreCase("male")) {
				throw (new InvalidProfileException("The profile gender must be Female or Male as the first word"));
			}
			// checking for Age
			try {
				float tempAge = Float.valueOf(inputPerson[1]);
			} catch (NumberFormatException e) {
				throw (new InvalidProfileException(
						"Invalid data for Age: " + inputPerson[1] + "\nAge must be a number"));
			}
			// checking for weight
			try {
				float tempWeight = Float.valueOf(inputPerson[2]);
			} catch (NumberFormatException e) {
				throw (new InvalidProfileException(
						"Invalid data for Weight: " + inputPerson[2] + "\nWeight must be a number"));
			}
			// checking for height
			try {
				float tempHeight = Float.valueOf(inputPerson[3]);
			} catch (NumberFormatException e) {
				throw (new InvalidProfileException(
						"Invalid data for Height: " + inputPerson[3] + "\nHeight must be a number"));
			}
			// checking for physical activity level
			if (!inputPerson[4].trim().equalsIgnoreCase("1") && !inputPerson[4].trim().equalsIgnoreCase("1.0")
					&& !inputPerson[4].trim().equalsIgnoreCase("1.1") && !inputPerson[4].trim().equalsIgnoreCase("1.25")
					&& !inputPerson[4].trim().equalsIgnoreCase("1.48")) {
				throw (new InvalidProfileException(
						"Invalid physical activity level: " + inputPerson[4] + "\nMust be: 1.0, 1.1, 1.25, or 1.48"));
			}

			// proceed with regular person creation if data validated
			StringBuilder sb = new StringBuilder();
			for (int i = 5; i < inputPerson.length; i++) { // only for grabbing the ingredients to watch
				sb.append(inputPerson[i].trim() + ", ");
			}
			if (inputPerson[0].trim().equalsIgnoreCase("female")) {
				tempPerson = new Female(Float.parseFloat(inputPerson[1]), Float.parseFloat(inputPerson[2]),
						Float.parseFloat(inputPerson[3]), Float.parseFloat(inputPerson[4]), sb.toString());
			} else {
				tempPerson = new Male(Float.parseFloat(inputPerson[1]), Float.parseFloat(inputPerson[2]),
						Float.parseFloat(inputPerson[3]), Float.parseFloat(inputPerson[4]), sb.toString());

			}
			return tempPerson;
		}

		catch (InvalidProfileException e) {
			// need to figure out what to do here / how to clear out all the data in all the
			// views
			NutriByte.view.searchResultSizeLabel.setText("");
			NutriByte.view.productsComboBox.setItems(null);
			throw (new InvalidProfileException("Could not read profile data"));

		}

	}

	//

	// checks the product for valid/invalid or missing data. Skips the product and
	// reads the next product if invalid
	public Product validateProductData(String s) {
		// check if we have that product in the productsMap. If yes, creates a new
		// product with the additional details. If anything fails, throw an exception
		// and return null
		try {

			String[] inputProduct = s.split(",");

			// checks to make sure we have 3 items. if not, return null.
			if (inputProduct.length != 3) {
				return null;
			}

			String ndbNumber = inputProduct[0].trim();
			float servingSize = 0;
			float householdSize = 0;
			servingSize = Float.valueOf(inputProduct[1].trim());
			householdSize = Float.valueOf(inputProduct[2].trim());

			try {
				Model.productsMap.containsKey(ndbNumber);
				return new Product(Model.productsMap.get(ndbNumber), servingSize, householdSize);

			} catch (NullPointerException e) {
				new InvalidProfileException("No product found with code: " + ndbNumber);
				return null;
			}

		} catch (NullPointerException e) {
			throw new InvalidProfileException("No Product found with this code ");

		} catch (InputMismatchException e) {
			new InvalidProfileException("Cannot read: " + s
			+ "\nThe data must be - String, Number, Number for ndbNumber, serving size, household size");
			return null;
		} catch (Exception e) {
			new InvalidProfileException("Cannot read: " + s
			+ "\nThe data must be - String, Number, Number for ndbNumber, serving size, household size");
			return null;
		}

	}

	@Override
	public void writeFile(String filename) {

		boolean writeFlag = false;
		try {
			boolean sex = (NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() == "Male"
					|| NutriByte.view.genderComboBox.getSelectionModel().getSelectedItem() == "Female");
			float age = Float.valueOf(NutriByte.view.ageTextField.getText());
			float weight = Float.valueOf(NutriByte.view.weightTextField.getText());
			float height = Float.valueOf(NutriByte.view.heightTextField.getText());

			if (age > 0 && weight > 0 && height > 0) {
				writeFlag = true;

			} else {
				new InvalidProfileException("Your profile is not complete enough to save");

			}

		} catch (Exception e) {
			new InvalidProfileException("Your profile is not complete enough to save");
		}

		if (writeFlag) {
			try {
				PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));

				String gender = null;
				if (NutriByte.person instanceof Male) {
					gender = "Male";
				} else
					gender = "Female";

				out.print(gender + "," + NutriByte.person.age + "," + NutriByte.person.weight + ","
						+ NutriByte.person.height + "," + NutriByte.person.physicalActivityLevel);
				String ingredientsList[] = NutriByte.person.ingredientsToWatch.split(",");
				for (String ingredient : ingredientsList) {
					out.print("," + ingredient);
				}
				out.println("");
				for (Product p : NutriByte.person.dietProductsList) {
					out.println(p.getNdbNumber() + "," + p.getServingSize() + "," + p.getHouseholdSize());
				}

				out.close();
			} catch (FileNotFoundException e) {
				new InvalidProfileException("The file you tried to save to is open, or could not be found.");
			}

			catch (IOException e) {
				new InvalidProfileException("Your profile failed to write");
			}
		}

	}

}

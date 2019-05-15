//Daniel Lesser / dlesser
package hw3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

public class Model {

	static ObservableMap<String, Product> productsMap = FXCollections.observableHashMap();
	static ObservableMap<String, Nutrient> nutrientsMap = FXCollections.observableHashMap();
	ObservableList<Product> searchResultsList = FXCollections.observableArrayList();

	// reads the products csv file using commons-csv JAR and adds Products objects
	// to observable productsMap
	public void readProducts(String fileName) {

		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(fileName), csvFormat);
			for (CSVRecord csvRecord : csvParser) {
				Product product = new Product(csvRecord.get(0), csvRecord.get(1), csvRecord.get(4), csvRecord.get(7));
				productsMap.put(csvRecord.get(0), product);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// reads the nutrients file using commons-csv JAR. Only adds a new nutrient to
	// the observable nutrientsMap if does NOT contain that nutrient key already.
	public void readNutrients(String fileName) {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(fileName), csvFormat);
			for (CSVRecord csvRecord : csvParser) {
				Nutrient nutrient = new Nutrient(csvRecord.get(1), csvRecord.get(2), csvRecord.get(5));
				nutrientsMap.put(csvRecord.get(1), nutrient);
				if (Float.parseFloat(csvRecord.get(4)) != 0) {
					productsMap.get(csvRecord.get(0)).getProductNutrients().put(csvRecord.get(1),
							productsMap.get(csvRecord.get(0)).new ProductNutrient(csvRecord.get(1),
									Float.parseFloat(csvRecord.get(4))));
				}

			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// checking if product's nutrients
//		for (Map.Entry<String, Product> p : productsMap.entrySet()) {
//			System.out.println("p: " + p.getValue().getProductNutrients().keySet());
//		}
	}

	// reads the servingSize csv file using commons-csv JAR and populates those
	// fields in the appropriate product. If no serving size is provided, set = 0.
	public void readServingSizes(String fileName) {
		CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader();
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(fileName), csvFormat);
			for (CSVRecord csvRecord : csvParser) {
				Product p = productsMap.get(csvRecord.get(0).trim());

				p.setServingUom(csvRecord.get(2));
				if (!csvRecord.get(3).equals("")) {
					p.setHouseholdSize(Float.parseFloat(csvRecord.get(3)));
				} else {
					p.setHouseholdSize(0f);
				}

				p.setHouseholdUom(csvRecord.get(4));

				if (!csvRecord.get(1).equals("")) {
					p.setServingSize(Float.parseFloat(csvRecord.get(1)));
				} else {
					p.setServingSize(0f);
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean readProfile(String fileName) throws InvalidProfileException {
		if (fileName.endsWith("csv")) {
			DataFiler read = new CSVFiler();
			return read.readFile(fileName);
		}
		if (fileName.endsWith("xml")) {
			DataFiler read = new XMLFiler();
			return read.readFile(fileName);
		}

		return false;
	}

	// allows user to save profile data and products in a new profile file as a csv
	public void writeProfile(String fileName) throws InvalidProfileException {
		if (fileName.endsWith("csv")) {
			DataFiler write = new CSVFiler();
			write.writeFile(fileName);
		}
		if (fileName.endsWith("xml")) {
			DataFiler write = new XMLFiler();
			write.writeFile(fileName);
		}

	}

}

//Daniel Lesser / dlesser
package hw2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

//takes the csv file name, reads the first line to identify 
//the type of person to create and their profile.
//creates a person object and assigns to NutriByte.person
//returns true if the file was read correctly, false otherwise
public class CSVFiler extends DataFiler {

	@Override
	public boolean readFile(String fileName) {

		CSVFormat csvFormat = CSVFormat.DEFAULT;
		try {
			CSVParser csvParser = CSVParser.parse(new FileReader(fileName), csvFormat);
			for (CSVRecord csvRecord : csvParser) {
				StringBuilder sb = new StringBuilder();
				for (int i = 5; i < csvRecord.size(); i++) {
					sb.append(csvRecord.get(i).trim() + ", ");
				}
				if (csvRecord.get(0).trim().equalsIgnoreCase("female")) {
					NutriByte.person = new Female(Float.parseFloat(csvRecord.get(1)), Float.parseFloat(csvRecord.get(2)),
							Float.parseFloat(csvRecord.get(3)), Float.parseFloat(csvRecord.get(4)), sb.toString());
				} else if (csvRecord.get(0).trim().equalsIgnoreCase("male")) {
					NutriByte.person = new Male(Float.parseFloat(csvRecord.get(1)), Float.parseFloat(csvRecord.get(2)),
							Float.parseFloat(csvRecord.get(3)), Float.parseFloat(csvRecord.get(4)), sb.toString());
				} else
					return false;
			}
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

	@Override
	public void writeFile(String filename) {
		// TODO Auto-generated method stub
		// leaving unwritten for HW2. Saved for HW3
	}

}

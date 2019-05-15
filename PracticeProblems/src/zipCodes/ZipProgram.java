package zipCodes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class ZipProgram {

	ArrayList<Zipcode> zipcodes = new ArrayList<Zipcode>();
	ArrayList<County> counties = new ArrayList<County>();

	public void readZipFile(String f) {

		try {
			File file = new File(f);
			Scanner fileRead = new Scanner(file);
			while (fileRead.hasNextLine()) {

				StringBuilder sb = new StringBuilder();
				sb.append(fileRead.nextLine());
				String[] locationArray = sb.toString().split(",");
				for (int i = 0; i < locationArray.length; i++) {
					locationArray[i] = locationArray[i].trim();
				}

				Zipcode tempLocation = new Zipcode(locationArray[0], locationArray[1], locationArray[2]);
				zipcodes.add(tempLocation);
			}
			fileRead.close();

		}

		catch (FileNotFoundException e) {
			e.getStackTrace();
		}

	}

	public String getFileName() {
		System.out.println("Please enter the name of the file you want to read");
		Scanner userIn = new Scanner(System.in);
		String fileName = userIn.next();
		userIn.close();
		return fileName;
	}

	public void sortZips() {
		Collections.sort(zipcodes);
	}

	public void populateCounties() {
		Iterator<Zipcode> iter = zipcodes.iterator();
		String countyName = iter.next().getCounty();
		int zipCount = 1;
		while (iter.hasNext()) {
			if (iter.next().getCounty().equalsIgnoreCase(countyName)) {
				zipCount++;
			} else {
				zipCount++;
				County tempCounty = new County(countyName, zipCount);
				counties.add(tempCounty);
				countyName = iter.next().getCounty();
				zipCount = 1;
			}
		}

	}

	public void printResults() {
		int count = 1;
		System.out.println("#. County \t\t Count of Zipcodes");
		for (County c : counties) {
			System.out.printf("%3d. %-30s : %3d \n", count++, c.getCountyName(), c.getCountZip());
		}
	}

	public void sortCounties() {
		Collections.sort(counties);
	}

	public static void main(String[] args) {

		ZipProgram z = new ZipProgram();
		z.readZipFile(z.getFileName());
		z.sortZips();
		z.populateCounties();
		z.sortCounties();
		z.printResults();

//		for (Zipcode p : z.zipcodes) {
//			System.out.print(p.getZipcode()+",");
//			System.out.print(p.getCity()+",");
//			System.out.print(p.getCounty()+"\n");
//			
//		}

	}

}

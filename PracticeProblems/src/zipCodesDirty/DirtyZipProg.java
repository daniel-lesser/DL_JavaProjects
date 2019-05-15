package zipCodesDirty;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import zipCodes.County;
import zipCodes.ZipProgram;
import zipCodes.Zipcode;

public class DirtyZipProg {

	ArrayList<ZipcodeDirty> zipcodes = new ArrayList<ZipcodeDirty>();
	Map<String, ZipcodeDirty> zipMap = new HashMap<>();
	ArrayList<ZipcodeDirty> duplicates = new ArrayList<ZipcodeDirty>();
	ArrayList<ZipcodeDirty> dirties = new ArrayList<ZipcodeDirty>();

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

				ZipcodeDirty tempLocation = new ZipcodeDirty(locationArray[0], locationArray[1], locationArray[2]);
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

	public void checkDuplications() {
		Collections.sort(this.zipcodes, new ZipcodeComparator());
		Iterator<ZipcodeDirty> iter = zipcodes.iterator();
		ZipcodeDirty zip1 = iter.next();
		while (iter.hasNext()) {
			ZipcodeDirty zip2 = iter.next();
			if (zip2.equals(zip1)) {
				if (!duplicates.contains(zip1)) {
					duplicates.add(zip1);
				}
				duplicates.add(zip2);
			}
			zip1 = zip2;
		}

	}

	public void checkDirties() {
		Collections.sort(this.zipcodes, new ZipcodeComparator());
		Iterator<ZipcodeDirty> iter = zipcodes.iterator();
		ZipcodeDirty zip1 = iter.next();
		while (iter.hasNext()) {
			ZipcodeDirty zip2 = iter.next();
			if (zip2.getZipcode().equals(zip1.getZipcode())
					&& (!zip2.getCity().equals(zip1.getCity()) || !zip2.getCounty().equals(zip1.getCounty()))) {
				if (!dirties.contains(zip1)) {
					dirties.add(zip1);
				}
				dirties.add(zip2);
			}
			zip1 = zip2;
		}

	}
	
	public void mapIt() {
		Iterator<ZipcodeDirty> iter = zipcodes.iterator();
		while (iter.hasNext()) {
			ZipcodeDirty z1 = iter.next();
			zipMap.put(z1.getZipcode().trim(), z1);
		}
//		System.out.println("zipmap");
//		for (ZipcodeDirty z : zipMap.values()) {
//			System.out.println(z.getZipcode() + ", " + z.getCounty() + ", " +z.getCity());
//		}
	}

	public class ZipcodeComparator implements Comparator<ZipcodeDirty> {

		@Override
		public int compare(ZipcodeDirty z1, ZipcodeDirty z2) {
			return z1.getZipcode().compareTo(z2.getZipcode());
		}

	}
	
	public void theSearch() {
		System.out.println("Please enter a zip code to search");
		Scanner userInput = new Scanner(System.in);
		String zip = userInput.next();
		ZipcodeDirty z = zipMap.get(zip.trim());
		System.out.println("Found county: " + z.getCounty() +". city: " + z.getCity());
		userInput.close();
		
	}

	public static void main(String[] args) {
		DirtyZipProg p = new DirtyZipProg();
		p.readZipFile("DirtyZipcodes.txt");
		p.checkDuplications();
		System.out.println("These are the duplicate zipcodes:");
		for (ZipcodeDirty z : p.duplicates) {
			System.out.println(z.getZipcode() + ", " + z.getCity() + ", " + z.getCounty());
		}
		System.out.println("");
		System.out.println("These are the dirty zipcodes:");
		p.checkDirties();
		for (ZipcodeDirty z : p.dirties) {
			System.out.println(z.getZipcode() + ", " + z.getCity() + ", " + z.getCounty());
		}
		System.out.println("");
		p.mapIt();
		p.theSearch();

	}

}

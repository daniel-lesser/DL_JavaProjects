package exam1_PracticeProbs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Item {
	String itemName;
	int qty;
	float unitPrice;
	String unit;
}

public class Grocer_FINAL {

	public Item[] readFile() {

		System.out.println("Please enter the name of the file you would like to read: ");
		Scanner scanIn = new Scanner(System.in);
		String fileName = scanIn.next();
		File file;

		Scanner scanCount;
		Scanner scanFile;

		int count = 0;

		// going through the file to find out how long the receipt is (# items)
		try {
			file = new File(fileName);
			scanCount = new Scanner(file);
			while (scanCount.hasNextLine()) {
				scanCount.nextLine();
				count++;
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.");
			e.printStackTrace();
			System.exit(0);
		}

		Item[] items = new Item[count];
		StringBuilder sb;

		// going through the file a second time to create items (rows of info)
		// and assign to each slot of items[]
		try {
			file = new File(fileName);
			scanFile = new Scanner(file);

			for (int i = 0; i < count; i++) {
				sb = new StringBuilder();
				sb.append(scanFile.nextLine());
				String content[] = sb.toString().split(",");
				for (int j = 0; j < content.length; j++) {
					content[j] = content[j].toString().trim();
				}
				Item myItem = new Item();
				myItem.itemName = content[0];
				myItem.qty = Integer.valueOf(content[1]);
				myItem.unit = content[2];
				myItem.unitPrice = Float.valueOf(content[3]);
				items[i] = myItem;
			}
		}

		catch (FileNotFoundException e) {
			System.out.println("Sorry, your file was not found.");
			e.printStackTrace();
			System.exit(0);
		}

//		for (int i = 0; i < items.length; i++) {
//			System.out.print(items[i].itemName + ",");
//			System.out.print(items[i].qty + ",");
//			System.out.print(items[i].unit + ",");
//			System.out.print(items[i].unitPrice + ",");
//			System.out.println();
//
//		}
		return items;
	}

	public void generateBill(Item[] items) {

		int count = 0;
		System.out.println("#\tItem\t\tQty\tUnit\t\tUnit price\tTotal price");
		for (int i = 0; i < items.length; i++) {
			System.out.printf("%-8d%-16s%-8d%-16s$%-16.2f$%-16.2f%n", 
					++count, items[i].itemName, items[i].qty, 
					items[i].unit, items[i].unitPrice, items[i].qty*items[i].unitPrice);
		}

	}

	public static void main(String[] args) {

		Grocer_FINAL myGroceries = new Grocer_FINAL();
		myGroceries.generateBill(myGroceries.readFile());

	}

}

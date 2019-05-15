package week4_practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class Grocer_06 {

	public String itemName;
	public int qty;
	public float unitPrice;
	public String unit;
	
	public String [] readfile() {
		System.out.println("Please enter the name of the grocery receipt you would like to read");
		Scanner input = new Scanner(System.in);
		String receipt = input.next();
		
		File file = null;
		Scanner fileInput = null;
		
		try {
			file = new File(receipt);
			fileInput = new Scanner(file).useDelimiter(",");
		}
		
		catch (FileNotFoundException e) {
			System.out.println("Your file could not be found.  Sorry");
			e.printStackTrace();
			System.exit(0);
		}
		
		ArrayList<String> custReceipt = new ArrayList<String>();
		
		while (fileInput.hasNextLine()) {
			String [] theLine = fileInput.nextLine().split(",");
			for (int j = 0; j < theLine.length; j++) {
				custReceipt.add(theLine[j]);
			}
		}
		
		//testing
		for (int i = 0; i < custReceipt.size(); i++) {
			System.out.println(custReceipt.get(i));
		}
		
		String [] output = new String[custReceipt.size()];
		output = (String []) custReceipt.toArray(output);
//		System.out.println("output is " + output.length);
		return output;
	}
	
	public void generateBill(String [] items) {
		
		System.out.println("#\tItem\t\tQty\t\tUnit\t\tUnit price\t\tTotal price");
		
		int count = 0;
		int x = 1;
		int y = 3;
		double quant = 0;
		double price = 0;
		
		for (int i = 0; i < items.length/4; i++) {
			
			System.out.print((count + 1) + "\t");
			for (int j = 0 + (count*4); j < 4 + (count*4); j ++) {
				System.out.print(items[j] + "\t\t");
			}

			
//			System.out.println(items[x+count]);
//			System.out.println(items[y+count]);
			quant = Double.valueOf(items[x+count*4]);
			price = Double.valueOf(items[y+count*4]);
			
			System.out.println("\t" + quant*price);
			count++;
			System.out.println();

		}
	
	}
	
	public static void main(String[] args) {

		Grocer_06 gr = new Grocer_06();
		

		
		gr.generateBill(gr.readfile());
		
	}

}

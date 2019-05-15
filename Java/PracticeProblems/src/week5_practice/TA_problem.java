//do not use this exact code on exam

package week5_practice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TA_problem {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner input = new Scanner(new File("GameScores.txt"));
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		while (input.hasNextLine()) {
			sb.append(input.nextLine() + "\n");
			count++;
		}

		int arr1[][] = new int [count][];
		
		String [] content = sb.toString().split("\n");  //splitting all content into array of the lines
		
		for (int i = 0; i < count; i++) {
			String s [] = content[i].toString().split(","); //splits each line into the digits
			int arr2[] = new int[s.length]; //initialized length of inner array
			
			for (int j = 0; j < s.length; j++) {
				arr2[j] = Integer.valueOf(s[j].trim()); //can use parseint insteaad.  putting the values 
				//of the first line into places of arr2
			}
			
			arr1[i] = arr2;  //places the array2 we made into the first spot of arr1 and then runs through
			
		}
		
		for (int i = 0; i < arr1.length; i++) {
			
			for (int j = 0; j < arr1[i].length; j++) {
				System.out.print(arr1[i][j] + ", ");

			}
			System.out.println();
		}
		
		
	}

}

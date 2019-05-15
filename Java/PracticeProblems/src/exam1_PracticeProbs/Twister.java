package exam1_PracticeProbs;

public class Twister {

	static String[] someNames = {"James", "Anne", "Zach", "Bob"};
	static int[] someNumbers = {43, 67, 12, 90};
	
	String[] twist (String[] names) {
		
		//reverse order
		for (int i = 0; i < names.length/2; i++) {
			String temp = names[i];
			names[i] = names[names.length-i-1];
			names[names.length-i-1] = temp;
		}
		
		for (int i = 0; i < names.length; i++) {
			StringBuilder s = new StringBuilder(); 
			s.append(names[i].toString());
			s.reverse();
			names[i] = s.toString();
		}

		
//		for (int i = 0; i < names.length; i++) {
//			System.out.println(names[i]);
//		}
		
		return names;
	}
	
	int[] twist(int[] numbers) {
		
		for (int i = 0; i < numbers.length/2; i++) {
			int temp = numbers[i];
			numbers[i] = numbers[numbers.length-i-1];
			numbers[numbers.length-i-1] = temp;
		}
		
		for (int i = 0; i < numbers.length; i++) {
			StringBuilder s = new StringBuilder();
			s.append(numbers[i]);
			s.reverse();
			numbers[i] = Integer.valueOf(s.toString());
		}
		
		return numbers;
	}
	
	public static void main(String[] args) {

		Twister twisty = new Twister();
		String names [] = twisty.twist(someNames);
		int numbers [] = twisty.twist(someNumbers);

		
		for (String s : names) {
			System.out.println(s);
		}
		
		System.out.println();
		
		for (int i : numbers) {
			System.out.println(i);
		}
	}

}

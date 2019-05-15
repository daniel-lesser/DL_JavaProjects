package series_practice10;

public class Fibonacci extends Series {

	
	
	@Override
	public int getNthNumber(int number) {

		if (number == 1) { return 0; }
		
		int firstNum = 0;
		int secondNum = 1;
		int result = 1;

		for (int i = 2; i <= number-1; i++) {
			result = firstNum + secondNum;
			firstNum = secondNum;
			secondNum = result;
		}
		
		return result;
	}

	public static void main(String[] args) {
		Series fib = new Fibonacci();
		for (int i = 0; i < 15; i++) {
			System.out.println("The " + (i+1) + "th number is " + fib.getNthNumber(i+1));
		}
		
	}

}

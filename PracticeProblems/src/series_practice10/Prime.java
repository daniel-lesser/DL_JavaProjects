package series_practice10;

public class Prime extends Series {

	@Override
	public int getNthNumber(int nth) {

		int primeNum = 1;
		int div = 0;
		boolean flag = false;
		int primeCount = 1;

		while (primeCount <= nth) {

			primeNum += 1;
			div = primeNum / 2;
			flag = false;

			for (int j = 2; j <= div; j++) {
				if (primeNum % j == 0) {
					flag = true;
					break;
				}

			}

			if (flag == false) {
				primeCount++;
			}

		}

		return primeNum;
	}

	// for testing
	public static void main(String[] args) {
		Prime p = new Prime();
		int input = 99;
		int primeNum = p.getNthNumber(input);
		System.out.println("Prime Number for " + input + " is " + primeNum);

	}

}

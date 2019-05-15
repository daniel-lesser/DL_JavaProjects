package lab0a;

public class Numbers {
	public static void main(String[] args) {
		Numbers n = new Numbers();
		System.out.println(n.sayWhat(5));
		System.out.println(n.nPrime(6));
	}

	// fix this method
	public String sayWhat(int number) {

		String result = "";

		if (number % 2 == 0)
			result = "even";
		else
			result = "odd";
		return result;
	}

	// fix this method
	public boolean isPrime(int number) {

		int div = number / 2;
		boolean result = true;

		if (number == 1 || number == 0)
			result = false;

		for (int i = 2; i <= div; i++) {
			// can you improve this loop??
			if (number % i == 0)
				result = false;
		}
		return result;
	}

	public int nPrime(int nPrime) {

		int nPrimeIs = 0;

		if (nPrime == 0) {
			System.out.println("That is not a valid choice");
			return 0;
		}

		if (nPrime == 1) {
			nPrimeIs = 2;
			return nPrimeIs;
		}

		int primecount = 1;
		int i = 3;
		int div = 2;
		int flag = 0;

		while (primecount < nPrime) {
			div = i / 2;
			flag = 0;
			for (int j = 2; j <= div; j++) {
				if (i % j == 0) {
					flag = 1;
					break;
				}
			}

			if (flag == 0) {
				nPrimeIs = i;
				primecount++;
			}
			i++;
		}

		return nPrimeIs;

	}
}

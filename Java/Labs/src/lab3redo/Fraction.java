package lab3redo;

public class Fraction {

	public int numerator;
	public int denominator;

	public Fraction() {
		super();
		numerator = 1;
		denominator = 1;
	}

	public Fraction(int numerator, int denominator) {
		super();
		this.numerator = numerator;
		this.denominator = denominator;
	}

	public String toString() {
		String s;
		s = numerator + "/" + denominator;
		return s;
	}

	public double toDecimal() {

		double d;
		d = (double) numerator / denominator;
		return d;
	}

	public Fraction add(Fraction f) {
		
		int num, denom;
		num = this.numerator * f.denominator + f.numerator * this.denominator;
		denom = this.denominator * f.denominator;
		
		Fraction f2 = new Fraction(num, denom); 
		int gcd = f2.findGCD(num, denom);
		f2.numerator = num / gcd;
		f2.denominator = denom / gcd;
		
		return f2;
	}

	public int findGCD(int n, int d) {

		if (n == 0) {
			return 1;
		}
		if (d == 0) {
			return n;
		} else
			return findGCD(d, n % d);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

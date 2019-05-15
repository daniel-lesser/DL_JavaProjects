//Daniel Lesser dlesser
package lab3;

public class Fraction {

	int numerator;
	int denominator;

	public Fraction() {
		this.numerator = 1;
		this.denominator = 1;

	}

	public Fraction(int numerator, int denominator) {

		this.numerator = numerator;
		this.denominator = denominator;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(numerator);
		sb.append("/");
		sb.append(denominator);
		String output = sb.toString();

		return output;
	}

	public double toDecimal() {
		double output = (double) numerator / denominator;
		return output;
	}

	public Fraction add(Fraction f) {

		int newNumer = (this.numerator * f.denominator) + (f.numerator * this.denominator);
		int newDenom = this.denominator * f.denominator;
		Fraction f1 = new Fraction(newNumer, newDenom);
		int GCD;

//		if (numerator > denominator) {
			GCD = f.findGCD(newNumer, newDenom);
//		} else {
//			GCD = f.findGCD(newDenom, newNumer);
//
//		}
		f1.numerator = f1.numerator / GCD;
		f1.denominator = f1.denominator / GCD;

		return f1;
	}

	public int findGCD(int numerator, int denominator) {

		if (numerator == 0) {
			return 1;
		} else if (denominator == 0) {
			return numerator;
		} else {
			return findGCD(denominator, numerator % denominator);
		}

	}

	public static void main(String[] args) {

		Fraction f = new Fraction(1, 2);
		Fraction f2 = new Fraction(3, 4);
		Fraction f3 = f.add(f2);

		System.out.println(f3.toString());
	}

}

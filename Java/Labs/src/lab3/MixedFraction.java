//Daniel Lesser dlesser
package lab3;

public class MixedFraction extends Fraction {
	int naturalNumber;

	public MixedFraction(int naturalNumber, int numerator, int denominator) {
		super(numerator, denominator);
		this.naturalNumber = naturalNumber;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(naturalNumber);
		sb.append(" ");
		sb.append(numerator);
		sb.append("/");
		sb.append(denominator);
		String output = sb.toString();

		return output;
	}

	public double toDecimal() {
		double output = (double) (naturalNumber * denominator + numerator) / denominator;
		return output;
	}

	public Fraction toFraction() {

		Fraction f = new Fraction(naturalNumber * denominator + numerator, denominator);

		return f;
	}
	
	public Fraction add(MixedFraction mf) {
		
		Fraction f1 = toFraction();
		Fraction f2 = mf.toFraction();
		return f2.add(f1);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

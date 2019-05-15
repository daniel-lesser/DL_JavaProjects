package dinner_Practice;

public class IceCream extends Food {
	public final int ICECREAM_CALORIES = 500;

	public IceCream() {
		calories += ICECREAM_CALORIES;
		System.out.println("Serving Ice cream");
	}

	@Override
	public String eat() {

		return "Slurp Slurp";
	}

}

//dlesser / Daniel Lesser
package lab9;

public class Individual extends Guest {

	static int individualsServed = 0;

	public Individual() {

		this.mealOrder = 1;
	}

	@Override
	void placeOrder() {
		individualsServed++;
	}

}

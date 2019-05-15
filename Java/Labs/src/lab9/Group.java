//dlesser / Daniel Lesser
package lab9;

import java.util.Random;

public class Group extends Guest {

	static int groupsServed = 0;

	public Group() {
		this.mealOrder = 4;
	}

	@Override
	void placeOrder() {
		groupsServed++;
	}

	public static void main(String[] args) {
		Random random = new Random();
		int num = random.nextInt(2);

		System.out.println(num);
	}

}

//dlesser / Daniel Lesser
package lab9;

import java.util.Queue;
import java.util.Random;

public class DynamicKitchen implements Runnable {

	int mealStock = 175, mealRate = 6;
	boolean underStock;
	int guestsServed, income;
	Queue<Guest> guestQ;

	DynamicKitchen(Queue<Guest> guestQ) {
		this.guestQ = guestQ;
	}

	@Override
	public void run() {

		Random random = new Random();
		Guest g;
		while (DynamicDiner.dinerOpen && underStock ==false) {
			synchronized (guestQ) {
				g = guestQ.poll();
			}

			if (g != null) {

				g.placeOrder();
				guestsServed++;
				mealStock -= g.mealOrder;
				income += 6 * g.mealOrder;
				if (mealStock <= 4) {
					underStock = true;
				}

				try {
					Thread.sleep(5 + random.nextInt(16));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

			}

		}

	}
}

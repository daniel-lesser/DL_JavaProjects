package diner_practice9;

public class Kitchen {

	private int drinkStock = 120;
	private int soupStock = 75;
	private int saladStock = 75;
	private int entreeStock = 120;
	private int dessertStock = 65;

	boolean canServe = true;

	private int[] stock = { drinkStock, soupStock, saladStock, entreeStock, dessertStock };

	public int[] getStock() {
		return stock;
	}

	public void updateStock(Guest g) {

		for (int i = 0; i < stock.length; i++) {
			stock[i] -= g.allOrders[i];
		}

		checkStock(g);

	}

	// returns false if the stock for any item has fallen to 4 or less, leading the
	// closure for the day
	private void checkStock(Guest g) {

		for (int i = 0; i < stock.length; i++) {
			if (stock[i] <= 4) {
				canServe = false;
			}
		}

	}

}

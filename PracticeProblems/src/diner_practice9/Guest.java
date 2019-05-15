package diner_practice9;

public abstract class Guest {

	protected int drink;
	protected int soup;
	protected int salad;
	protected int entree;
	protected int dessert;
	protected int[] allOrders = { drink, soup, salad, entree, dessert };

	public int[] getAllOrders() {
		return allOrders;
	}

	public void setAllOrders(int[] allOrders) {
		this.allOrders = allOrders;
	}

	public abstract void placeOrder();

	public int getDrink() {
		return drink;
	}

	public void setDrink(int drink) {
		this.drink = drink;
	}

	public int getSoup() {
		return soup;
	}

	public void setSoup(int soup) {
		this.soup = soup;
	}

	public int getSalad() {
		return salad;
	}

	public void setSalad(int salad) {
		this.salad = salad;
	}

	public int getEntree() {
		return entree;
	}

	public void setEntree(int entree) {
		this.entree = entree;
	}

	public int getDessert() {
		return dessert;
	}

	public void setDessert(int dessert) {
		this.dessert = dessert;
	}

	public Guest() {
		super();
	}

}

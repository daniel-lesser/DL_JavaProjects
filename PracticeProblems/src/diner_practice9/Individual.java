package diner_practice9;

public class Individual extends Guest {

	public Individual() {
		super();
		this.drink = 1;
		this.soup = 1;
		this.salad = 0;
		this.entree = 1;
		this.dessert = 0;
		this.allOrders[0] = this.drink;
		this.allOrders[1] = this.soup;
		this.allOrders[2] = this.salad;
		this.allOrders[3] = this.entree;
		this.allOrders[4] = this.dessert;
	}
	
	@Override
	public void placeOrder() {
		// TODO Auto-generated method stub

	}

}

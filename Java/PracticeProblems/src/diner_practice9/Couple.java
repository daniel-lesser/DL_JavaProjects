package diner_practice9;

public class Couple extends Guest {

	public Couple() {
		super();
		this.drink = 2;
		this.soup = 1;
		this.salad = 1;
		this.entree = 2;
		this.dessert = 1;
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

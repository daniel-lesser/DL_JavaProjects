package diner_practice9;

public class Family extends Guest {

	public Family() {
		super();
		this.drink = 4;
		this.soup = 2;
		this.salad = 3;
		this.entree = 4;
		this.dessert = 2;
		this.allOrders[0] = this.drink;
		this.allOrders[1] = this.soup;
		this.allOrders[2] = this.salad;
		this.allOrders[3] = this.entree;
		this.allOrders[4] = this.dessert;
	}

	@Override
	public void placeOrder() {
		
		
	}

}

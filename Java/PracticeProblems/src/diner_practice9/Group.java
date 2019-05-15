package diner_practice9;

public class Group extends Guest {

	public Group() {
		super();
		this.drink = 4;
		this.soup = 3;
		this.salad = 3;
		this.entree = 4;
		this.dessert = 3;
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

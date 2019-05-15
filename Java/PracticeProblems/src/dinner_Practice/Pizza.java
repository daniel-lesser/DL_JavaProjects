package dinner_Practice;

public class Pizza extends Food implements Heatable {

	public final int PIZZA_CALORIES = 800;
	
	public Pizza() {
		calories+= PIZZA_CALORIES;
		System.out.println("Serving Pizza");
	}
	
	@Override
	public void heatIt() {
		temperature = Pizza.HOTSERVINGTEMPERATURE;
	}

	@Override
	public String eat() {
		return "Chomp Chomp";
	}

}

package dinner_Practice;

public abstract class Food {

	static int calories = 0;
	int temperature;

	public Food() {
		super();
		System.out.println("Here comes food!");
	}
	
	public abstract String eat();

}

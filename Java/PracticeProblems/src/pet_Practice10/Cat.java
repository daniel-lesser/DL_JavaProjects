package pet_Practice10;

public class Cat extends Pet {

	static int catCount = 0;
	
	@Override
	String talk() {
		this.petCount++;
		catCount++;
		return "Meow...";
	}

}

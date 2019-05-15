package pet_Practice10;

public class Dog extends Pet {

	static int dogCount = 0;
	@Override
	String talk() {
		this.petCount++;
		dogCount++;
		return "Bark...";
	}

}

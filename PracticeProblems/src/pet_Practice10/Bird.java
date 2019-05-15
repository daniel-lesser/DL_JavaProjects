package pet_Practice10;

public class Bird extends Pet {

	static int birdCount = 0;
	
	@Override
	String talk() {
		this.petCount++;
		birdCount++;
		return "Tweet...";
	}

}

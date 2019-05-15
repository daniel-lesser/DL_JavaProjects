package problem14;

public class Patient {

	static int count=0;
	long startTime;
	long endTime;
	int id;
	
	
	public Patient() {
		startTime = System.currentTimeMillis();
		id = ++count;
	}
	
}

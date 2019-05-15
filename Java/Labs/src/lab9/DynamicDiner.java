//dlesser / Daniel Lesser
package lab9;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class DynamicDiner implements Runnable{

	Queue<Guest> guestQ = new LinkedList<>();
	DynamicKitchen kitchen = new DynamicKitchen(guestQ);
	long startTime, endTime;
	static volatile boolean dinerOpen = true;
	int maxQLength, guestsEntered;

	public static void main(String[] args) {
		DynamicDiner dynamicDiner = new DynamicDiner();
		dynamicDiner.startTime = System.currentTimeMillis();
		Thread t1 = new Thread(dynamicDiner.kitchen);
		t1.start();
		dynamicDiner.run();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dynamicDiner.printReport();
		dynamicDiner.checkAssertions();
	}

	@Override
	public void run() {
		
		Random random = new Random();
		Guest g;
		while (dinerOpen) {
			int num = random.nextInt(2);
			
			if (num ==1) {
				g = new Individual();
			}
			else {
				g = new Group();
			}
			
			synchronized (guestQ) {
				guestQ.offer(g);
			}
			
			guestsEntered++;
			if (guestQ.size() > maxQLength) {
				maxQLength = guestQ.size();
			}
		
			
			try {
				Thread.sleep(1+random.nextInt(30));
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (System.currentTimeMillis() - startTime > 1000) {
				dinerOpen = false;
				endTime = System.currentTimeMillis();
				
			}
			
		}
		

		
	}

	void printReport() {
		System.out.println("-------------- Guests--------------");
		System.out.printf("%-25s%5d%n", "Total guests entered:", guestsEntered);
		System.out.printf("%-25s%5d%n", "Individuals served:", Individual.individualsServed);
		System.out.printf("%-25s%5d%n", "Groups served:", Group.groupsServed);
		System.out.printf("%-25s%5d%n", "Total guests served:", kitchen.guestsServed);
		System.out.printf("%-25s%5d%n", "Guests declined service:", guestQ.size());
		System.out.println("--------- Kitchen -----------");
		System.out.printf("%-25s%5d%n", "Meals left:", kitchen.mealStock);
		System.out.printf("%-25s%s%n", "Closing status", (kitchen.underStock) ? "Under stock" : "Overstock" );
		System.out.println("-------------- Diner -------------- ");
		System.out.printf("%-25s%5d%n", "Max Q length", maxQLength);
		System.out.printf("%-25s%,d ms%n", "Diner was open for: ", endTime - startTime);
		System.out.printf("%-25s$%,5d%n", "Income:", kitchen.income);
		System.out.println("-----------------------------------");
	}
	
	void checkAssertions() {
		//following statements will check final numbers and throw assertion error when incorrect
		assertTrue(guestsEntered == Individual.individualsServed + Group.groupsServed + guestQ.size());
		assertTrue(kitchen.guestsServed == Individual.individualsServed + Group.groupsServed);
		assertTrue((kitchen.income == (Individual.individualsServed + Group.groupsServed * 4) * kitchen.mealRate));
		if (!kitchen.underStock) assertTrue(endTime - startTime > 1000);
		if (kitchen.underStock) assertTrue(kitchen.mealStock <=4 );
		if (endTime - startTime < 1000) assertTrue(kitchen.underStock);
	}
}

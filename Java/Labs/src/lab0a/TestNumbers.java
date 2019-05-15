package lab0a;

import static org.junit.Assert.*;
import org.junit.Test;
public class TestNumbers {
	Numbers n  =  new Numbers();
	
	@Test
	public void testSayWhat() {
		
		assertEquals("Testing Numbers", "odd", n.sayWhat(5));
		//write an   assertEquals statement here to test sayWhat() method 
		//passing '5' as its parameter and checking if it returns odd
		
		assertEquals("Testing Numbers", "even", n.sayWhat(4));

		//write an assertEquals statement here to test sayWhat() method passing '4' as 
		//its parameter and checking if it returns even
		
	}
	
	@Test
	public void testPrime() {
		assertEquals("Test prime", true, n.isPrime(5) );
	}
	
	@Test
	public void testNPrime() {
		assertEquals("Test Prime Count", 5, n.nPrime(3));
	}
}
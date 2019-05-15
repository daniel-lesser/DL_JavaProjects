package lab0b;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.math.BigInteger;

public class TestFactorial {
	
	Factorial fact = new Factorial();
	
	@Test
	public void testZeroFactorial() {
		assertEquals("Test factorial of 0", 1, fact.calculateFactorial(0));
	}
	
	@Test
	public void testNegativeFactorial() {
		assertEquals("Test factorial of negative number", 1, fact.calculateFactorial(-1));
	}
	
	@Test
	public void testPrimitiveSmallFactorial() {
		assertEquals("Test factorial of number < 14", 24, fact.calculateFactorial(4));
	}
	
	@Test
	public void testPrimitiveBigFactorial() {
		assertEquals("Test factorial of 14", 1278945280, fact.calculateFactorial(14));
	}
	
	@Test
	public void testZeroBigFactorial() {
		assertEquals("Test big-factorial of 0", 1, fact.calculateBigFactorial(0).intValue());
	}
	
	@Test
	public void testNegativeBigFactorial() {
		assertEquals("Test big-factorial of negative number", 1, fact.calculateBigFactorial(-1).intValue());
	}

	@Test
	public void testSmallBigFactorial() {
		assertEquals("Test big-factorial of number < 14", BigInteger.valueOf(24), fact.calculateBigFactorial(4));
	}
	
	@Test
	public void testBigBigFactorial() {
		assertEquals("Test big-factorial of 14", new BigInteger("87178291200"), fact.calculateBigFactorial(14));
	}
}

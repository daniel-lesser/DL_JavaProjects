package lab0b;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTranslator {

	Translator t = new Translator();
	
	@Test
	public void test1_translateZero() {
		assertEquals("zero", t.translate(0).trim().toLowerCase());
	}
	
	@Test
	public void test2_translateNegative() {
		assertEquals("minus two", t.translate(-2).trim().toLowerCase());
	}
	
	@Test
	public void test3_translatePositive() {
		assertEquals("two", t.translate(2).trim().toLowerCase());
	}

	@Test
	public void test4_translateTens() {
		assertEquals("two four", t.translate(24).trim().toLowerCase());
	}
	
	@Test
	public void test5_translateHundreds() {
		assertEquals("eight six four", t.translate(864).trim().toLowerCase());
	}
}

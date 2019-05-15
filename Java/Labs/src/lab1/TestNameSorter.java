package lab1;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNameSorter {
	NameSorter nameSorter = new NameSorter();
	String[] names = {"Lisa", "Sarah", "Ben", "Adam", "Ryan"};
	
	@Test
	public void test1_sortAndSearchExistingNameSameCase() {
		int position = nameSorter.sortAndsearch(names, "Ben");
		assertEquals("Test search existing name same case", 1, position);
	}
	
	@Test
	public void test2_sortAndSearchExistingNameDifferentCase() {
		int position = nameSorter.sortAndsearch(names, "ben");
		assertEquals("Test search existing name different case", 1, position);
	}
	
	@Test
	public void test3_sortAndSearchMissingName() {
		int position = nameSorter.sortAndsearch(names, "Ann");
		assertEquals("Test search missing name", -1, position);
	}
	
	@Test
	public void test4_sortAndSearchNullName() {
		int position = nameSorter.sortAndsearch(names, null);
		assertEquals("Test search null name", -1, position);
	}

	@Test
	public void test5_sortAndSearchNullArray() {
		String[] names = null;
		int position = nameSorter.sortAndsearch(names, null);
		assertEquals("Test search null array", -1, position);
	}
	
	@Test
	public void test6_toTitleCaseFromAllLower() {
		assertEquals("Test all lower case input", "Lisa", nameSorter.toTitleCase("lisa"));
	}
	
	@Test
	public void test7_toTitleCaseFromAllUpper() {
		assertEquals("Test all upper case input", "Lisa", nameSorter.toTitleCase("LISA"));
	}
	
	@Test
	public void test8_toTitleCaseFromTitle() {
		assertEquals("Test title case input", "Lisa", nameSorter.toTitleCase("Lisa"));
	}
	
	@Test
	public void test9_toTitleCaseNull() {
		assertEquals("Test null input", null, nameSorter.toTitleCase(null));
	}
	
	@Test
	public void test10_toTitleCaseEmpty() {
		assertEquals("Test null input", null, nameSorter.toTitleCase(""));
	}
	
	
}

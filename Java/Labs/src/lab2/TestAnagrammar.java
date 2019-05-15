package lab2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestAnagrammar {
	
	static Anagrammar ag;

	@BeforeClass
	public static void setupBeforeClass() {
		ag = new Anagrammar();
		ag.filename = "dictionary.txt";
		ag.loadWords();
	}

	@Test
	public void testInDictionaryNoAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.clue = "complex";
		ag.findAnagrams();
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
	
	@Test
	public void testInDictionaryHasAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.clue = "free";
		ag.findAnagrams();
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
		assertEquals("Test anagram count", 2, ag.anagramArray.length);
	}

	@Test
	public void testNotInDictionaryHasAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.clue = "abc";
		ag.findAnagrams();
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
		assertEquals("Test anagram count", 2, ag.anagramArray.length);
	}
	
	@Test
	public void testNotInDictionaryNoAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.clue = "xyz";
		ag.findAnagrams();
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
}

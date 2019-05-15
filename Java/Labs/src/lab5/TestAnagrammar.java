//Daniel Lesser / dlesser
package lab5;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestAnagrammar {
	
	static Anagrammar ag;

	@BeforeClass
	public static void setupBeforeClass() {
		ag = new Anagrammar();
		ag.loadWords();
	}

	@Test
	public void testInDictionaryNoAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.findAnagrams("complex");
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
	
	@Test
	public void testInDictionaryHasAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.findAnagrams("free");
		assertEquals("Test in dictionary", true, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
		assertEquals("Test anagram count", 2, ag.anagramArray.length);
	}

	@Test
	public void testNotInDictionaryHasAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.findAnagrams("abc");
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", true, ag.hasAnagrams);
		assertEquals("Test anagram count", 2, ag.anagramArray.length);
	}
	
	@Test
	public void testNotInDictionaryNoAnagram() {
		ag.isInDictionary = false;
		ag.hasAnagrams = false;
		ag.findAnagrams("xyz");
		assertEquals("Test in dictionary", false, ag.isInDictionary);
		assertEquals("Test has anagrams", false, ag.hasAnagrams);
	}
}

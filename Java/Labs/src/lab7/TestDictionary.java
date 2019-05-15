package lab7;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDictionary {
	static Dictionary dictionary;
	@BeforeClass 
	public static void setupClass() {
		dictionary = new Dictionary();
		dictionary.loadWordList();
		dictionary.loadSingleMap();
		dictionary.loadMultiMap();
	}
	@Test
	public void testSingleMapContainsKey() {
		assertEquals("Test singleMap word exists", true, dictionary.singleMap.containsKey("wise") );
	}
	@Test
	public void testMultiMapContainsKey() {
		assertEquals("Test multieMap word exists", true, dictionary.multiMap.containsKey("wise") );
	}
	@Test
	public void testSingleMapMeaning() {
		assertEquals("Test singleMap meaning", "(v.) Way of being or acting; manner; mode; fashion.", dictionary.singleMap.get("wise").meaning );
	}
	@Test
	public void testMultiMapMeaningSize() {
		assertEquals("Test singleMap meaning", 6, dictionary.multiMap.get("wise").size() );
	}
	
}

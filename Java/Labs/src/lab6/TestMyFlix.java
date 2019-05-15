package lab6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMyFlix {
	MyFlix myFlix;
	
	@Before
	public void setupClass() {
		myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
	}

	@Test
	public void test1_readDBLength() {
		assertEquals(30492, myFlix.movieDBStrings.length);
	}
	
	@Test
	public void test2_moviesLength() {
		assertEquals(30492, myFlix.moviesList.size());
	}
	
	@Test
	public void test3_moviesContent() {
		boolean found = false;
		for (Movie movie : myFlix.moviesList) {
			if (movie.movieId == 29) {
				assertEquals ("The Time Machine", movie.movieName);
				assertEquals (2002, movie.movieYear);
				assertEquals("USA", movie.countries);
				assertEquals(2, movie.movieGenres.size());
				assertEquals(true, movie.movieGenres.contains("action") && movie.movieGenres.contains("adventure"));
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test4_genresLength() {
		assertEquals(23, myFlix.genresList.size());
	}
	
	@Test
	public void test5_genresSort() {
		Collections.sort(myFlix.genresList);
		assertEquals("drama", myFlix.genresList.get(0).genreName);
		assertEquals("news", myFlix.genresList.get(22).genreName);
	}
	
	@Test
	public void test6_genresContentMovieCount() {
		boolean found = false;
		for (Genre genre: myFlix.genresList) {
			if (genre.genreName.equals("comedy")) {
				assertEquals(9155, genre.genreMovies.size());
				found = true;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test7_genresContentMovieName() {
		Movie movieToCheck = new Movie(18, "Back to the future", 1985, "USA");
		boolean found = false;
		for (Genre genre: myFlix.genresList) {
			if (genre.genreName.equals("comedy")) {
				assertEquals(true, genre.genreMovies.contains(movieToCheck));  //uses Movie's equals()
				found = true;
				break;
			}
		}
		assertTrue(found);
	}
	
	@Test
	public void test8_searchMovieName() {
		List<Movie> searchResults = myFlix.findMovies("ying");
		assertEquals(100, searchResults.size());
	}
	
	@Test
	public void test9_movieHash() {
		assertEquals(49, (new Movie(18, "Back to the future", 1985, "USA")).hashCode());
	}
	
	@Test
	public void test10_genreHash() {
		assertEquals(95844998, new Genre("drama").hashCode());
	}
	
	@Test
	public void test11_genreEquals() {
		Genre genre1 = new Genre("drama");
		Genre genre2 = new Genre("drama");
		assertEquals(true, genre1.equals(genre2));  
				
	}
}

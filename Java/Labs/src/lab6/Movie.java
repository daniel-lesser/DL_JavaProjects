//Daniel Lesser / dlesser
package lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Movie implements Comparable<Movie> {

	int movieId;
	String movieName;
	int movieYear;
	String countries;
	List<String> movieGenres = new ArrayList<String>();

	public Movie(int movieId, String movieName, int movieYear, String countries) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieYear = movieYear;
		this.countries = countries;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieId);
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (getClass() != o.getClass())
			return false;
		Movie m = (Movie) o;
		return movieId == m.movieId;

	}

	@Override
	public int compareTo(Movie movie) {
		return this.movieName.compareTo(movie.movieName);
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

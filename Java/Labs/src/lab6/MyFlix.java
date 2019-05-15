//Daniel Lesser / dlesser

package lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MyFlix {
	List<Movie> moviesList = new ArrayList<>();
	List<Genre> genresList = new ArrayList<>();
	String[] movieDBStrings;

	// do not change this method
	public static void main(String[] args) {
		MyFlix myFlix = new MyFlix();
		myFlix.movieDBStrings = myFlix.readMovieDB("MoviesDB.tsv");
		myFlix.loadMovies();
		myFlix.loadGenres();
		Collections.sort(myFlix.moviesList);
		Collections.sort(myFlix.genresList);
		myFlix.showMenu();
	}

	// do not change this method
	void showMenu() {
		System.out.println("*** Welcome to MyFlix ***");
		System.out.println("1. Search for a movie");
		System.out.println("2. List of genres");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt();
		input.nextLine();
		switch (choice) {
		case 1: {
			System.out.println("Enter the string to search in movie names");
			String searchString = input.nextLine();
			printSearchResults(findMovies(searchString));
			break;
		}
		case 2: {
			printGenreReport();
			break;
		}
		default:
			break;
		}
		input.close();
	}

	// do not change this method
	// readMovieDB reads all data from the MovieDB file
	// and loads each row as a string in movieDBStrings
	String[] readMovieDB(String filename) {
		StringBuilder movies = new StringBuilder();
		try {
			Scanner input = new Scanner(new File(filename));
			while (input.hasNextLine()) {
				movies.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return movies.toString().split("\n");
	}

	void loadMovies() {
		// test to see what string [] looks like
//		for (String s : movieDBStrings) {
//			System.out.println(s);
//		}

		for (int i = 0; i < movieDBStrings.length; i++) {
			List<String> genres = new ArrayList<>();
			String[] tempArray = movieDBStrings[i].split("\t");
			Movie m = new Movie(Integer.parseInt(tempArray[0].trim()), tempArray[1].trim(),
					Integer.parseInt(tempArray[2].trim()), tempArray[3].trim());
			moviesList.add(m);
			for (int j = 4; j < tempArray.length; j++) {
				moviesList.get(i).movieGenres.add(tempArray[j].trim());
//				m.movieGenres.add(tempArray[j].trim());
			}

		}

	}

	void loadGenres() {

		for (int i = 0; i < moviesList.size(); i++) {
			for (int j = 0; j < moviesList.get(i).movieGenres.size(); j++) {
				Genre newGen = new Genre(moviesList.get(i).movieGenres.get(j));
				if (!genresList.contains(newGen)) {
					newGen.genreMovies.add(moviesList.get(i)); // adds the movie that fit the genre to genre's movie
																// list
					genresList.add(newGen); // adds the genre to the list of genres
				} else { // genre is already in list of genres
					for (Genre g : genresList) { // go through the list of genres
						if (g.equals(newGen)) { // if the newgen matches something in that list of genres
							g.genreMovies.add(moviesList.get(i)); // adds this movie to that genre's list of movies
						}
					}
				}

			}
		}

	}

	List<Movie> findMovies(String searchString) {
		List<Movie> movies = new ArrayList<Movie>();
		for (int i = 0; i < moviesList.size(); i++) {
			if (moviesList.get(i).movieName.toLowerCase().contains(searchString.toLowerCase())) {
				movies.add(moviesList.get(i));
			}
		}
		return movies;
	}

	void printGenreReport() {
		int count = 0;
		for (int i = 0; i < genresList.size(); i++) {
			System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count, genresList.get(i).genreName,
					genresList.get(i).genreMovies.size());

		}

		// write your code here
		// print statement to print the report as per the format
		// System.out.printf("%3d. %-15s Number of movies: %,6d%n", ++count,
		// genre.genreName, genre.genreMovies.size());
	}

	void printSearchResults(List<Movie> searchResults) {

		int count = 0;
		System.out.println(searchResults.size() + " movies matching the search criteria");
		for (int i = 0; i < searchResults.size(); i++) {

			System.out.printf("%3d. %-50s\tYear: %d Countries: %-20s\t", ++count, searchResults.get(i).movieName,
					searchResults.get(i).movieYear, searchResults.get(i).countries);

			System.out.print("Genre: ");
			for (int j = 0; j < searchResults.get(i).movieGenres.size(); j++) {
				System.out.printf("%s ", searchResults.get(i).movieGenres.get(j));

			}
			System.out.println("");
		}

	}

	// write your code here
	// print statements to print the genre report
	// System.out.printf("%3d. %-50s\tYear: %d Countries: %-20s\t", ++count,
	// movie.movieName, movie.movieYear, movie.countries);
	// ...
	// System.out.printf("%s ", genre);
}

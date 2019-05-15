package problem13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchQ {
	String fileName; 		//stores file name input by the user
	String searchWord;		//stores search word input by user
	StringBuilder fileContent = new StringBuilder();	//stores file content
	String fileContent_str [];
	Queue<String> searchHistory = new LinkedList<>();	//stores last 5 successful searches
	ArrayList<Integer> positions;						//stores the positions of last successful searchWord
	Scanner input = new Scanner(System.in);				//takes user input for filename and searchWord

	public static void main(String[] args) {
		SearchQ sq = new SearchQ();
		sq.readFile();
		sq.getSearchWord();
		while (sq.searchWord.length()>0) {
			sq.printSearchHistory();
			sq.getSearchWord();
		} 
		System.out.println("Bye Bye");
	}

	/**readFile() asks the user for the filename and reads its contents into fileContent
	 */
	public void readFile()  {
		System.out.println("Enter file name");
		fileName = input.nextLine();
		try {
			Scanner fileInput = new Scanner (new File(fileName));
			while (fileInput.hasNextLine()) {
				fileContent.append(fileInput.nextLine() + "\n");
			}
			fileInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//some code from video about scanning
//		try (Scanner s = new Scanner(new BufferedReader(new FileReader("text.txt")));
//				) {
//			s.useDelimiter(",");
//			while (s.hasNext()) {
//				System.out.println(s.next());
//			}
//		}
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		
		
		
	}

	/************************** Write your code below this line ******************************/
	
	/**getSearchWord() asks user for searchWord, initializes searchWord with the input
	 * It then invokes getWordPositions(searchWord) which returns positions arraylist
	 * If positions arraylist's size is 0, it means the word wasn't found so it prints a message accordingly
	 * else, it checks if searchHistory size is >= 5. If yes, it removes (polls) the queue. 
	 * If not, it adds (offers) the new search word
	 * 
	 */
	public void getSearchWord() {
		System.out.println("Enter search word.  To quit, simply press Enter with no words");
		searchWord = input.nextLine().toLowerCase(); 
		positions = getWordPositions(searchWord);
		if (positions.size()==0) {
			System.out.println("Sorry "+ searchWord + " not found");
			return;
			}

		if (searchHistory.size()>=5) {
			searchHistory.poll();
			searchHistory.offer(searchWord);
		}
		else {
			searchHistory.offer(searchWord);
		}
		
	}

	/** printSearchHistory() prints the output as required
	 * using the positions arrayList and the searchHistory queue
	 */
	public void printSearchHistory() {
		System.out.print(searchWord + " found in " + positions.size() + " positions: ");
		for (int i : positions) {
			System.out.print(i+", ");
		}
		System.out.println("");
		System.out.println("***Your Search History***");
		for (String s : searchHistory) {
			System.out.println(s);
		}
		
	}

	/** getWordPositions() searches for the searchWord in the fileContent
	 *  It uses "[^a-zA-Z0-9']+" as the regex for parsing the words.
	 *  It stores all positions at which the word is found in the positions arrayList
	 */
	public ArrayList<Integer> getWordPositions(String searchWord) {
		positions = new ArrayList<>();
		fileContent_str = fileContent.toString().toLowerCase().split("[^a-zA-Z0-9']+");
		
		ArrayList<String> fileContent_Array = new ArrayList<>(Arrays.asList(fileContent_str));

		int count = 0;
		for (String s : fileContent_Array) {
			if (s.equalsIgnoreCase(searchWord)) {
				positions.add(count);
			}
			count++;
		}
		
//		String [] parsed = fileContent_str.split("[^a-zA-Z0-9']+");
		
//		Pattern pattern = Pattern.compile(searchWord);
//		Matcher m = pattern.matcher(fileContent_Array);
//		while (m.find()) {
//			positions.add(m.start());
//			
//		}
		return positions;
	}

}
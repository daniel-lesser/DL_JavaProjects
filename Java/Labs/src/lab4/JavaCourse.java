//Daniel Lesser dlesser
package lab4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class JavaCourse {
	Content[] contents;

	public static void main(String[] args) {
		JavaCourse javaCourse = new JavaCourse();
		javaCourse.loadContentsArray(javaCourse.readJavaContent());
		javaCourse.printCourseSummary();
		javaCourse.peruseContent();
	}

	/**
	 * readJavaContent() reads JavaContent.csv and loads each of its row as a string
	 * into an array. It returns the array.
	 */
	String[] readJavaContent() {
		StringBuilder contentStrings = new StringBuilder();
		try {
			Scanner input = new Scanner(new File("JavaContent.csv"));
			while (input.hasNextLine()) {
				contentStrings.append(input.nextLine() + "\n");
			}
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contentStrings.toString().split("\n");
	}

	void loadContentsArray(String[] contentStringsArray) {

		int contentCount = 0;
		for (String s : contentStringsArray) {
			contentCount++;
		}

		contents = new Content[contentCount];
		for (int i = 0; i < contentStringsArray.length; i++) {

			StringBuilder s = new StringBuilder();

			if (contentStringsArray[i].contains("Video,")) {
				s.append(contentStringsArray[i]);
				String[] vid = s.toString().split(",");
				for (int j = 0; j < vid.length; j++) {
					vid[j] = vid[j].trim();
				}
				Content newContent;
				newContent = new Video(vid[1], Integer.parseInt(vid[2]));
				contents[i] = newContent;
			}

			if (contentStringsArray[i].contains("Practice,")) {
				s.append(contentStringsArray[i]);
				String[] vid = s.toString().split(",");
				for (int j = 0; j < vid.length; j++) {
					vid[j] = vid[j].trim();
				}
				Content newContent;
				newContent = new PracticeProblem(vid[1], Integer.parseInt(vid[2]), Integer.parseInt(vid[3]));
				contents[i] = newContent;
			}

		}

	}

	// do not change this method
	void printCourseSummary() {
		System.out.println("*** JAVA COURSE SUMMARY ***");
		System.out.println("------------------------------------------");
		System.out.printf("%-30s%3d%n", "Total contents:", Content.getContentCount());
		System.out.printf("%-30s%3d%n", "Total videos:", Video.getVideoCount());
		System.out.printf("%-30s%3d%n", "Total practice problems:", PracticeProblem.getPracticeProblemCount());
		System.out.println("------------------------------------------");
		System.out.printf("%-30s%3d min.%n", "Total practice time:", PracticeProblem.getTotalPracticeTime());
		System.out.printf("%-30s%3d min.%n", "Total video time:", Video.getTotalVideoTime());
		System.out.println("------------------------------------------");
	}

	void peruseContent() {

		System.out.println("List of Java Contents");
		for (int i = 0; i < contents.length; i++) {
			System.out.printf("\n%2d. %-30s %s  %2d min.", i+1, contents[i].getContentName(), "Learning time:",
					contents[i].getLearningTime());
		}
		System.out.println();
		System.out.println();
		System.out.println("Enter the content number between 1 and 12: ");
		Scanner input = new Scanner(System.in);
		int choice = input.nextInt() - 1;

		if (contents[choice] instanceof PracticeProblem) {
			Random r = new Random();
			int speedMax = 35 + r.nextInt(11); 
			((PracticeProblem) contents[choice]).download(speedMax);
		}
		contents[choice].learn();

	}
}

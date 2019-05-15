//dlesser / Daniel Lesser
package lab8;

import java.util.Random;

public class Student {

	static int totalStudentsCreated = 0;
	static int totalStudentsHelped = 0;
	int studentID;
	Random random = new Random(); //unclear
	
	
	public Student() {
		
		totalStudentsCreated++;
		studentID = totalStudentsCreated;
		
	}
	
	public int askQuestion() {
		
		totalStudentsHelped++;
		int randNum = 5+ random.nextInt(26);
		return randNum;
		
	}
	
	//for testing random
	public static void main(String[] args) {
		Student s = new Student();
		for (int i = 0; i < 50; i++) {
			System.out.println(s.askQuestion());
		}
	}

	
}

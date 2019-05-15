//dlesser / Daniel Lesser
package lab8;

public class TA implements Runnable, Comparable<TA> {
	int studentsHelped;
	static int totalHelpTime;
	int helpTime;
	static final int MAX_HELP_TIME = 120;
	int taID;
	static int taCount = 0;

	public TA() {
		taCount++;
		taID = taCount;
	}

	@Override
	public void run() {
		Student s = null;
		while ((helpTime < MAX_HELP_TIME) && JavaCourse.allDone == false) {

			synchronized (JavaCourse.studentQ) { // should this be inside the >0 if statement?
				s = JavaCourse.studentQ.poll();
			}
			if (s != null) {
				int numMins = s.askQuestion();
				studentsHelped++;
				totalHelpTime += numMins;
				helpTime += numMins;
				try {
					Thread.sleep(numMins);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println(spacer(taID) + "TA" + taID + ":Student" + s.studentID + ":" + numMins + "min");

				if (totalHelpTime >= taCount * MAX_HELP_TIME && JavaCourse.allDone ==false) {
					JavaCourse.allDone = true;
					System.out.println("******* All done flag set by TA" + taID);
				}

			}

		}

	}

	@Override
	public int compareTo(TA o) {
		return o.helpTime - this.helpTime;// check for order
	}

	// do not change this method
	String spacer(int taID) {
		StringBuilder spaces = new StringBuilder();
		for (int i = 1; i < taID; i++) {
			spaces.append("\t\t");
		}
		return spaces.toString();
	}
}

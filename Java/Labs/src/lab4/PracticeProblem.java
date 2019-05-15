//Daniel Lesser dlesser
package lab4;

public class PracticeProblem extends Content implements Downloadable {

	private static int practiceProblemCount = 0;
	private static int totalPracticeTime = 0;
	int fileSize;

	PracticeProblem(String contentName, int learningTime, int fileSize) {
		super(contentName, learningTime);
		this.fileSize = fileSize;
		practiceProblemCount++;
		totalPracticeTime = totalPracticeTime + super.learningTime;
	}

	public static int getPracticeProblemCount() {
		return practiceProblemCount;
	}

	public static int getTotalPracticeTime() {
		return totalPracticeTime;
	}

	public int getFileSize() {
		return fileSize;
	}

	@Override
	public float download(int speed) {

		System.out.println("Expected time to download " + this.contentName + " @" + this.INTERNET_SPEED_MBPS
				+ " MBPS is " + (float) this.fileSize / this.INTERNET_SPEED_MBPS + " seconds");

		System.out.println("Actual time to download " + this.contentName + " @" + speed + " MBPS is "
				+ (float) this.fileSize / speed + " seconds"); 

		return (float) fileSize / speed; // check that this is what they want and calc is correct
		
	}

	@Override
	public void learn() {
		System.out.println("Now working on " + super.getContentId() + ": " + super.contentName + " for "
				+ super.learningTime + " minutes");

	}

}

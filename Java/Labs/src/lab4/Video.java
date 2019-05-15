//Daniel Lesser dlesser
package lab4;

public class Video extends Content {

	private static int totalVideoTime = 0;
	private static int videoCount = 0;

	Video(String contentName, int learningTime) {
		super(contentName, learningTime);
		videoCount++;
		totalVideoTime = totalVideoTime + learningTime;

	}

	public static int getTotalVideoTime() {
		return totalVideoTime;
	}

	public static int getVideoCount() {
		return videoCount;
	}

	@Override
	public void learn() {
		System.out.println("Watching " + super.getContentId() + ": " + super.contentName + " for " + super.learningTime + " minutes");
	}

}

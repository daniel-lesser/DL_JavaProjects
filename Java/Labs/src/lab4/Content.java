//Daniel Lesser dlesser
package lab4;


//do not change this class
public abstract class Content {
	protected int contentId;			//will store unique content id generated by the constructor
	protected String contentName;		//content name as given in data file
	protected int learningTime;			//time to peruse the content in minutes
	private static int contentCount;	//total count of content objects (video or practice-problem) created so far
	
	Content(String contentName, int learningTime) {
		setContentId(++contentCount);	//increment the count and assign to contentID
		setContentName(contentName);	//initialize content name
		setLearningTime(learningTime);	//initialize learning time
	}
	
	//getters
	public int getContentId() { return contentId;} 
	public String getContentName() { return contentName; }
	public int getLearningTime() { return learningTime; }
	public static int getContentCount() {return contentCount; }
	
	//setters
	public void setContentId(int contentId) { this.contentId = contentId; }
	public void setContentName(String contentName) { this.contentName = contentName; }
	public void setLearningTime(int learningTime) { this.learningTime = learningTime; }
	
	//to be overridden by child classes
	public abstract void learn();
}

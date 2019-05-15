//Daniel Lesser dlesser
package lab4;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class TestJavaCourse {

	static JavaCourse javaCourse;
	
	@BeforeClass
	public static void setupClass() {
		javaCourse = new JavaCourse();
		javaCourse.loadContentsArray(javaCourse.readJavaContent());
	}

	@Test
	public void test1_contentCount_Array() {
		assertEquals(12, javaCourse.contents.length);
	}
	
	@Test
	public void test2_contentCount_Class() {
		assertEquals(12, Content.getContentCount());
	}
	
	@Test
	public void test3_practiceCount() {
		assertEquals(4, PracticeProblem.getPracticeProblemCount());
	}
	
	@Test
	public void test4_videoCount() {
		assertEquals(8, Video.getVideoCount());
	}
	
	@Test
	public void test5_practiceTime() {
		assertEquals(115, PracticeProblem.getTotalPracticeTime());
	}
	
	@Test
	public void test6_videoTime() {
		assertEquals(48, Video.getTotalVideoTime());
	}
	
	@Test
	public void test7_contentDownloadable() {
		assertTrue(javaCourse.contents[7] instanceof Downloadable);
	}
	
	@Test
	public void test8_downloadTime() {
		assertEquals(0.33, ((Downloadable)javaCourse.contents[7]).download(45), 0.01);
	}

}

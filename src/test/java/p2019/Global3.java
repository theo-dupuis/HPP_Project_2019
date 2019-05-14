package p2019;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class Global3 {
	/** exceed limit by 1 ms **/
	/** Likes **/
	@Test
	public void test3_withoutFile() {
		String commentsPath = "Test Cases/T3_comments.dat";
		String friendshipsPath = "Test Cases/T3_friendships.dat";
		String likesPath = "Test Cases/T3_likes.dat";
		String Expected_Output = "2010-12-22T02:14:33.000+0000,I see,-\r\n" + 
				"2010-12-22T02:14:35.000+0000,I see,LOL\r\n" + 
				"2010-12-22T02:14:35.001+0000,LOL,-";
		// TODO d = 3 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test3_withFile() {
		String commentsPath = "Test Cases/T3_comments.dat";
		String friendshipsPath = "Test Cases/T3_friendships.dat";
		String likesPath = "Test Cases/T3_likes.dat";
		String Expected_Output = "2010-12-22T02:14:33.000+0000,I see,-\r\n" + 
				"2010-12-22T02:14:35.000+0000,I see,LOL\r\n" + 
				"2010-12-22T02:14:35.001+0000,LOL,-";
		String outputFilePath = "output.txt";
		// TODO d = 3 and k = 2
		File outputfile = new File(outputFilePath);
		assertTrue(outputfile.exists());
		try {
			// expectedLinesOutputInFile
			String eLOIF = new String(Files.readAllBytes(Paths.get(outputFilePath)));
			assertEquals(eLOIF,Expected_Output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/** Friendships **/
	@Test
	public void test4_withoutFile() {
		String commentsPath = "Test Cases/T4_comments.dat";
		String friendshipsPath = "Test Cases/T4_friendships.dat";
		String likesPath = "Test Cases/T4_likes.dat";
		String Expected_Output = "2010-12-22T02:14:33.000+0000,I see,-\r\n" + 
				"2010-12-22T02:14:35.000+0000,I see,LOL\r\n" + 
				"2010-12-22T02:14:35.001+0000,LOL,-";
		// TODO d = 3 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test4_withFile() {
		String commentsPath = "Test Cases/T4_comments.dat";
		String friendshipsPath = "Test Cases/T4_friendships.dat";
		String likesPath = "Test Cases/T4_likes.dat";
		String Expected_Output = "2010-12-22T02:14:33.000+0000,I see,-\r\n" + 
				"2010-12-22T02:14:35.000+0000,I see,LOL\r\n" + 
				"2010-12-22T02:14:35.001+0000,LOL,-";
		String outputFilePath = "output.txt";
		// TODO d = 3 and k = 2
		File outputfile = new File(outputFilePath);
		assertTrue(outputfile.exists());
		try {
			// expectedLinesOutputInFile
			String eLOIF = new String(Files.readAllBytes(Paths.get(outputFilePath)));
			assertEquals(eLOIF,Expected_Output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

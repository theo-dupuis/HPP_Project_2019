package p2019;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class Global4 {
	/**  noLike friendship after**/
	@Test
	public void test1_withoutFile() {
		String commentsPath = "Test Cases/T5_comments.dat";
		String friendshipsPath = "Test Cases/T5_friendships.dat";
		String likesPath = "Test Cases/T5_likes.dat";
		String Expected_Output = "";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test1_withFile() {
		String commentsPath = "Test Cases/T5_comments.dat";
		String friendshipsPath = "Test Cases/T5_friendships.dat";
		String likesPath = "Test Cases/T5_likes.dat";
		String Expected_Output = "";
		String outputFilePath = "output.txt";
		// TODO d = 7200 and k = 2
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
	/**  noLike friendship before**/
	@Test
	public void test2_withoutFile() {
		String commentsPath = "Test Cases/T6_comments.dat";
		String friendshipsPath = "Test Cases/T6_friendships.dat";
		String likesPath = "Test Cases/T6_likes.dat";
		String Expected_Output = "";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test2_withFile() {
		String commentsPath = "Test Cases/T6_comments.dat";
		String friendshipsPath = "Test Cases/T6_friendships.dat";
		String likesPath = "Test Cases/T6_likes.dat";
		String Expected_Output = "";
		String outputFilePath = "output.txt";
		// TODO d = 7200 and k = 2
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

package p2019;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class Global1 {
	/** Basic 1 **/
	@Test
	public void test1_withoutFile() {
		String commentsPath = "Test Cases/T1_comments.dat";
		String friendshipsPath = "Test Cases/T1_friendships.dat";
		String likesPath = "Test Cases/T1_likes.dat";
		String Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:15:33.827+0000,LOL,I see";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test1_withFile() {
		String commentsPath = "Test Cases/T1_comments.dat";
		String friendshipsPath = "Test Cases/T1_friendships.dat";
		String likesPath = "Test Cases/T1_likes.dat";
		String Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:15:33.827+0000,LOL,I see";
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

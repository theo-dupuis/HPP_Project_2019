package p2019;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class Global2 {
	/** Basic 2 **/
	@Test
	public void test_withoutFile() {
		String commentsPath = "Test Cases/T2_comments.dat";
		String friendshipsPath = "Test Cases/T2_friendships.dat";
		String likesPath = "Test Cases/T2_likes.dat";
		String Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:17:00.827+0000,LOL,I see";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test_withFile() {
		String commentsPath = "Test Cases/T2_comments.dat";
		String friendshipsPath = "Test Cases/T2_friendships.dat";
		String likesPath = "Test Cases/T2_likes.dat";
		String Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:17:00.827+0000,LOL,I see";
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

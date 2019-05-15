package p2019;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class Global1 {
	String commentsPath="";
	String friendshipsPath="";
	String likesPath="";
	String Expected_Output="";
	String outputFilePath="";
	@Before
	public void pathCreation() {
		commentsPath = "Test_Cases/T1_comments.dat";
		URL url = Thread.currentThread().getContextClassLoader().getResource(commentsPath);
		commentsPath = url.getPath();
		friendshipsPath = "Test_Cases/T1_friendships.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(friendshipsPath);
		friendshipsPath = url.getPath();
		likesPath = "Test_Cases/T1_likes.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(likesPath);
		likesPath = url.getPath();

		Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:15:33.827+0000,LOL,I see";
	}
	/** Basic 1 **/
	@Test
	public void test1_withoutFile() {

		Expected_Output = "2010-12-22T02:15:31.827+0000,I see,-\r\n" + 
				"2010-12-22T02:15:32.827+0000,I see,LOL\r\n" + 
				"2010-12-22T02:15:33.827+0000,LOL,I see";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test1_withFile() {
		// TODO d = 7200 and k = 2
		
		URL url = Thread.currentThread().getContextClassLoader().getResource(outputFilePath);
		outputFilePath = url.getPath();
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

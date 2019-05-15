package p2019;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class Global5 {
	/** Graph weigth multiple event **/
	@Test
	public void test_withoutFile() {
		String commentsPath = "Test Cases/T7_comments.dat";
		String friendshipsPath = "Test Cases/T7_friendships.dat";
		String likesPath = "Test Cases/T7_likes.dat";
		String Expected_Output = "2010-12-22T02:14:38.827+0000,Theo is crazy,-\r\n" + 
				"2010-12-22T02:14:40.827+0000,Theo is crazy,I think Romain is more crazy than him\r\n" + 
				"2010-12-22T02:15:38.001+0000,I think Romain is more crazy than him,Theo is crazy";
		// TODO d = 7200 and k = 2
		String output = "fail";
		assertEquals(Expected_Output,output);
	}
	@Test
	public void test_withFile() {
		String commentsPath = "Test Cases/T7_comments.dat";
		String friendshipsPath = "Test Cases/T7_friendships.dat";
		String likesPath = "Test Cases/T7_likes.dat";
		String Expected_Output = "2010-12-22T02:14:38.827+0000,Theo is crazy,-\r\n" + 
				"2010-12-22T02:14:40.827+0000,Theo is crazy,I think Romain is more crazy than him\r\n" + 
				"2010-12-22T02:15:38.001+0000,I think Romain is more crazy than him,Theo is crazy";
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

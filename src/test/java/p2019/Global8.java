package p2019;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Global8 {
	String commentsPath="";
	String friendshipsPath="";
	String likesPath="";
	String Expected_Output="";
	String outputFilePath="";
	@Before
	public void pathCreation() {
		commentsPath = "Test_Cases/T8_comments.dat";
		URL url = Thread.currentThread().getContextClassLoader().getResource(commentsPath);
		commentsPath = url.getPath();
		friendshipsPath = "Test_Cases/T8_friendships.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(friendshipsPath);
		friendshipsPath = url.getPath();
		likesPath = "Test_Cases/T8_likes.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(likesPath);
		likesPath = url.getPath();

		Expected_Output = "2010-12-22T02:14:38.827+0000,Theo is crazy,-,-,-\r\n" + 
				"2010-12-22T02:14:40.827+0000,Theo is crazy,I think Romain is more crazy than him,-,-\r\n" + 
				"2010-12-22T02:14:41.827+0000,Theo is crazy,I think Romain is more crazy than him,I see,-\r\n" + 
				"2010-12-22T02:14:42.827+0000,Theo is crazy,LOL,I see,I think Romain is more crazy than him\r\n" + 
				"2010-12-22T02:15:29.827+0000,Theo is crazy,I think Romain is more crazy than him,LOL,I see\r\n" + 
				"2010-12-22T02:15:30.827+0000,Theo is crazy,I think Romain is more crazy than him,I see,LOL\r\n" + 
				"2010-12-22T02:15:38.001+0000,I think Romain is more crazy than him,Theo is crazy,I see,LOL";
		
	}
	/** other k test **/
	@Test
	public void test_withFile() {
		URL url = Thread.currentThread().getContextClassLoader().getResource(outputFilePath);
		outputFilePath = url.getPath();
		// TODO d = 7200 and k = 4
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
	@After
	public void removeFile() {
		File outputfile = new File(outputFilePath);
		outputfile.delete();
	}

}

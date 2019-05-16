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

public class Global3 {
	String commentsPath="";
	String friendshipsPath="";
	String likesPath="";
	String Expected_Output="";
	String outputFilePath="Output.txt";
	@Before
	public void pathCreation() {
		commentsPath = "Test_Cases/T3_comments.dat";
		URL url = Thread.currentThread().getContextClassLoader().getResource(commentsPath);
		commentsPath = url.getPath();
		friendshipsPath = "Test_Cases/T3_friendships.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(friendshipsPath);
		friendshipsPath = url.getPath();
		likesPath = "Test_Cases/T3_likes.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(likesPath);
		likesPath = url.getPath();

		Expected_Output = "2010-12-22T02:14:33.000+0000,I see,-\r\n" + 
				"2010-12-22T02:14:35.000+0000,I see,LOL\r\n" + 
				"2010-12-22T02:14:35.001+0000,LOL,-\r\n";
	}
	/** exceed limit by 1 ms **/
	/** Likes **/
	@Test
	public void test1_withFile() {
		String[] args = {"3","2",commentsPath,likesPath,friendshipsPath};
		MyApp.main(args);
		URL url = Thread.currentThread().getContextClassLoader().getResource(outputFilePath);
		outputFilePath = url.getPath();
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
	@After
	public void removeFile() {
		File outputfile = new File(outputFilePath);
		outputfile.delete();
	}
}

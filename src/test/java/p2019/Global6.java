package p2019;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Global6{

	String commentsPath="";
	String friendshipsPath="";
	String likesPath="";
	String Expected_Output="";
	String outputFilePath="Output.txt";
	@Before
	public void pathCreation() {
		commentsPath = "Test_Cases/T6_comments.dat";
		URL url = Thread.currentThread().getContextClassLoader().getResource(commentsPath);
		commentsPath = url.getPath();
		friendshipsPath = "Test_Cases/T6_friendships.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(friendshipsPath);
		friendshipsPath = url.getPath();
		likesPath = "Test_Cases/T6_likes.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(likesPath);
		likesPath = url.getPath();

		Expected_Output = "";
	}
	/**  noLike friendship before**/
	@Test
	public void test2_withFile() {
		String[] args = {"7200","2",commentsPath,likesPath,friendshipsPath};
		MyApp.main(args);
		// TODO d = 7200 and k = 2
		File outputfile = new File(outputFilePath);
		assertTrue(outputfile.exists());
		try {
			// expectedLinesOutputInFile
			String eLOIF = new String(Files.readAllBytes(Paths.get(outputFilePath)));
			assertEquals(Expected_Output,eLOIF);
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

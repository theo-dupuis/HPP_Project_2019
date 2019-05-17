package p2019;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import org.junit.Before;
import org.junit.Test;

public class main {
	String commentsPath="";
	String friendshipsPath="";
	String likesPath="";
	String Expected_Output="";
	String outputFilePath="Output.txt";
	@Before
	public void pathCreation() {
		commentsPath = "Test_Cases/comments.dat";
		URL url = Thread.currentThread().getContextClassLoader().getResource(commentsPath);
		commentsPath = url.getPath();
		friendshipsPath = "Test_Cases/friendships.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(friendshipsPath);
		friendshipsPath = url.getPath();
		likesPath = "Test_Cases/likes.dat";
		url = Thread.currentThread().getContextClassLoader().getResource(likesPath);
		likesPath = url.getPath();
	}
	@Test
	public void test1_withFile() {
		String[] args = {"3600","5",commentsPath,likesPath,friendshipsPath};
		MyApp.main(args);
		File outputfile = new File(outputFilePath);
		assertTrue(outputfile.exists());
	}
}

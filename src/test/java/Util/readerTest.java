package Util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class readerTest {

	@Test
	public void test() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test_Cases/T1_comments.dat");
		String fileToReadPath = url.getPath();
		String firstLineStringReaded = "2010-12-22T02:14:32.827+0000|34359738627|99|I see|Baoping Wu||529360";
		// TODO ReadFirstLine
		String firstLineReaded = "toto";//TODO
		//assertEquals(firstLineStringReaded, firstLineReaded);
		Reader commentReader = new Reader("comment");
		File file = new File(fileToReadPath);
		assertTrue(file.exists());
		commentReader.processFile(fileToReadPath);
	}

}

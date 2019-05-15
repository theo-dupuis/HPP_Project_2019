package Util;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import org.junit.Test;

import util.Reader;

public class readerTest {

	@Test
	public void test() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("Test_Cases/T1_comments.dat");
		String fileToReadPath = url.getPath();
		String firstLineStringReaded = "2010-12-22T02:14:32.827+0000|34359738627|99|I see|Baoping Wu||529360";
		Reader reader = new Reader("comment");
		reader.processFile(fileToReadPath);
		// TODO verification of a added queue object
	}

}

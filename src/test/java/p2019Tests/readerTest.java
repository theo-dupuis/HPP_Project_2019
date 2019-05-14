package p2019Tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class readerTest {

	@Test
	public void test() {
		String fileToReadPath = "Test Cases/T1_comments.dat";
		String firstLineStringReaded = "2010-12-22T02:14:32.827+0000|34359738627|99|I see|Baoping Wu||529360";
		// TODO ReadFirstLine
		String firstLineReaded = "toto";//TODO
		assertEquals(firstLineStringReaded, firstLineReaded);
	}

}

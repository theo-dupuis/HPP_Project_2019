package p2019;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class LikeTest {

	@Test
	public void test() {
		Like like = new Like("2010-12-22T02:14:38.000+0000","20","3");
		Date expectedTS = new Date();
		expectedTS.setTime(1292980478000L);
		assertEquals(expectedTS.toString(),like.getTimeStamp());
		assertEquals("20",like.getComment());
	}

}

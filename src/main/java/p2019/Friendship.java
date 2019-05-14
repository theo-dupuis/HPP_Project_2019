package p2019;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Friendship {


	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS+0000");
	private Date timeStamp_;
	private int user1Id_;
	private int user2Id_;
	public Friendship(String ts, String user1Id, String user2Id) {
		try {
			timeStamp_ = format.parse(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		user1Id_ = Integer.parseInt(user1Id);
		user2Id_ = Integer.parseInt(user2Id);
	}

}

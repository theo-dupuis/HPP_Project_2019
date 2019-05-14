package p2019;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS+0000");
	private Date timeStamp_;
	private int commentId_;
	private String comment_;
	
	public Comment(String ts, String commentId, String comment)
	{
		try {
			timeStamp_ = format.parse(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commentId_ = Integer.parseInt(commentId);
		comment_ = comment;
	}
	
	
	
}

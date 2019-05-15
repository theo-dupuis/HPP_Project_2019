package p2019;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class Comment {
	
	private String id;
	
	private Date creationTimeStamp;
	private Date lastUpdateTimeStamp;
	
	private int range;
	private String content;
	private List<Community> communities;
	
	public Comment(String ts, String commentId, String comment)
	{
		try {
			creationTimeStamp = MyApp.dateFormat.parse(ts);
			lastUpdateTimeStamp = creationTimeStamp;
		} catch (ParseException e) {
			System.out.println("Error while formatting timestamp");
		}
		
		id = commentId;
		content = comment;
		range =0;
		communities = new ArrayList<>();
	}
}

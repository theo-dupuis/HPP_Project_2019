package p2019;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

import graph.User;

public class Comment implements Observer{
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS+0000");
	private Date creationTimeStamp_;
	private Date lastUpdateTimeStamp_;
	private int commentId_;
	private String comment_;
	private int score_;
	
	public Comment(String ts, String commentId, String comment)
	{
		try {
			creationTimeStamp_ = format.parse(ts);
			lastUpdateTimeStamp_ = creationTimeStamp_;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commentId_ = Integer.parseInt(commentId);
		comment_ = comment;
		score_ =0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId_;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (commentId_ != other.commentId_)
			return false;
		return true;
	}

	@Override
	public void update(Observable o, Object arg) {
		try {
			lastUpdateTimeStamp_ = format.parse((String)arg);
		} catch (ParseException e) {
			System.out.println("Error while updating timestamp");
		}
		
	}
	
	public void processLike(String ts) {
		try {
			lastUpdateTimeStamp_ = format.parse(ts);
		} catch (ParseException e) {
			System.out.println("Error while updating timestamp");
		}
	}
}

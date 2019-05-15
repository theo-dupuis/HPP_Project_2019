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

import graph.User;

public class Comment implements Observer{
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
	private Date creationTimeStamp_;
	private Date lastUpdateTimeStamp_;
	private String commentId_;
	private String comment_;
	private int score_;
	private List<User> likers;
	
	public Comment(String ts, String commentId, String comment)
	{
		try {
			creationTimeStamp_ = format.parse(ts);
			lastUpdateTimeStamp_ = creationTimeStamp_;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commentId_ = commentId;
		comment_ = comment;
		score_ =0;
		likers = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commentId_;
		return result;
	}

	public void addLiker(User liker)
	{
		likers.put(liker.getId(), liker);
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
		
		processFriendship();
		
	}
	
	public void processLike(String ts, User user) {
		try {
			lastUpdateTimeStamp_ = format.parse(ts);
		} catch (ParseException e) {
			System.out.println("Error while updating timestamp");
		}
		
		for(User u : likers)
			if(user.isFriend(u))
				score_+=1; // Score chez tous les utilisateurs ?
		
	}
	
	public void processFriendship() {
		
	}
}

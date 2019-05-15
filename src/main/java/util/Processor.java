package util;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import p2019.Comment;
import p2019.Community;
import p2019.MyApp;
import p2019.User;

public class Processor {
	
	public static void process(String fileType, String data) {
		switch (fileType) {
		case "comment" :
			processComment(data);
			break;
		case "like" :
			processLike(data);
			break;
		case "friendship" :
			processFriendship(data);
			break;
		default:
			System.out.println("fileType not recognized");
			break;
		}
	}
	
	private static void processComment(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		new Comment(dataHolder[0],dataHolder[1],dataHolder[3]);
	}
	
	private static void processLike(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String userID = dataHolder[1];
		String commentID = dataHolder[2];
		
		User user;
		Comment comment = MyApp.comments.get(commentID);
		
		if (!comment.updateTimeStamp(timeStamp))
			return;
		
		if(MyApp.users.containsKey(userID))
			user = MyApp.users.get(userID);
		else {
			user = new User(userID);
			MyApp.users.put(userID, user);
		}

		
		for(User u : user.getFriends()) {
			if(comment.getCommunities().containsKey(u)) {
				Community comm = comment.getCommunities().get(u);
				comm.addUser(user);
				comment.sizeHasChanged(comm);
				return;
			}
		}
		comment.getCommunities().put(user, new Community(user));
	}
	
	private static void processFriendship(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		User user1 = MyApp.users.get(dataHolder[1]);
		User user2 = MyApp.users.get(dataHolder[2]);
		
		user1.getFriends().add(user2);
		user2.getFriends().add(user1);
		Date date = null;
		Date datelimit = null;
		try {
			date = MyApp.dateFormat.parse(timeStamp);
			datelimit = new Date(date.getTime()-MyApp.duration*1000); // si le commentaire a ete post� avant datelimit, on ignore
		} catch (ParseException e) {
			System.out.println("Error while formatting timestamp");
		}
		for(Comment c : user1.getComments()) {
			if (c.getCreationTimeStamp().before(datelimit))
			{
				continue;
			}
			if(user2.getComments().contains(c)) {
				c.communityMerge(user1, user2);
				c.updateTimeStamp(timeStamp);
			}
		}
	}
}
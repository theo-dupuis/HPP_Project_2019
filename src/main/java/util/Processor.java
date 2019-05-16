package util;

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
		MyApp.comments.put(dataHolder[1],new Comment(dataHolder[0],dataHolder[1],dataHolder[3]));
	}
	
	private static void processLike(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String userID = dataHolder[1];
		String commentID = dataHolder[2];
		
		User user = putAndGet(userID);
		Comment comment = MyApp.comments.get(commentID);
		
		if (!comment.updateTimeStamp(timeStamp))
			return;
		

		Community c = new Community(user);
		comment.getCommunities().put(user, c);
		for(User u : user.getFriends()) {
			if(comment.getCommunities().containsKey(u)) {
				Community comm = comment.getCommunities().get(u);
				comm.merge(c);
				comment.getCommunities().put(user, comm);
			}
		}
		comment.sizeHasChanged(comment.getCommunities().get(user));
	}
	
	private static void processFriendship(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String user1Id = dataHolder[1];
		String user2Id = dataHolder[2];
		
		User user1 = putAndGet(user1Id);
		User user2 = putAndGet(user2Id);
		
		user1.getFriends().add(user2);
		user2.getFriends().add(user1);
		
		for(Comment c : user1.getComments()) {
			if (!c.updateTimeStamp(timeStamp))
			{
				continue;
			}
			if(user2.getComments().contains(c)) {
				c.communityMerge(user1, user2);
				c.updateTimeStamp(timeStamp);
			}
		}
	}
	
	public static User putAndGet(String id) {
		User user;
		
		if(MyApp.users.containsKey(id))
			user = MyApp.users.get(id);
		else {
			user = new User(id);
			MyApp.users.put(id, user);
		}
		return user;
		
	}
}

package util;

import java.util.Iterator;
import java.util.regex.Pattern;

import p2019.Comment;
import p2019.Community;
import p2019.MyApp;
import p2019.User;

public class Processor {
	
	public static void processComment(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		MyApp.comments.put(dataHolder[1],new Comment(dataHolder[0],dataHolder[1],dataHolder[3]));
	}
	
	public static void processLike(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String userID = dataHolder[1];
		String commentID = dataHolder[2];
		
		User user = putAndGet(userID);
		Comment comment = isAliveAndGet(commentID);
		
		if(comment == null || !comment.updateTimeStamp(timeStamp))
			return;
		
		user.getComments().add(comment);
		
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
	
	public static void processFriendship(String data) {
		String[] dataHolder = data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String user1Id = dataHolder[1];
		String user2Id = dataHolder[2];
		
		clearCommentList(timeStamp);
		
		User user1 = putAndGet(user1Id);
		User user2 = putAndGet(user2Id);
		
		user1.getFriends().add(user2);
		user2.getFriends().add(user1);
		
		for(Comment c : user1.getComments()) {
			if(user2.getComments().contains(c)) {
				c.communityMerge(user1, user2);
			}
		}
	}
	
	private static User putAndGet(String id) {
		User user;

		if(MyApp.users.containsKey(id))
			user = MyApp.users.get(id);
		else {
			user = new User(id);
			MyApp.users.put(id, user);
		}
		
		return user;
	}
	
	private static Comment isAliveAndGet(String commentID) {
		if(MyApp.comments.containsKey(commentID))
			return MyApp.comments.get(commentID);
		else
			return null;
	}
	
	private static void clearCommentList(String ts) {
		Iterator<String> it = MyApp.comments.keySet().iterator();
		while(it.hasNext())
			if(!MyApp.comments.get(it.next()).updateTimeStamp(ts))
				it.remove();
	}
}

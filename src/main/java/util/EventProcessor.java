package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;

import p2019.Comment;
import p2019.Community;
import p2019.MyApp;
import p2019.User;

public class EventProcessor {
	
	public static void orderEvent() {
		 
		List<Event> events = new ArrayList<>();
		
		Event comment = null;
		Event like = null;
		Event friendship = null;
		
		while(true) {
			String flag = "";
			switch (flag) {
			case "comment":
				
				break;
			case "like":
				break;
			case "friendship":
				break;
			default:
				comment = MyApp.commentsEvents.take();
				like = MyApp.likeEvents.take();
				friendship = MyApp.friendshipEvents.take();
				break;
			}
			
			events.add(comment);
			events.add(like);
			events.add(friendship);
			
			Collections.sort(events, comparatorTimeStamp);
			Event e = events.remove(0);
			switch (e.fileType) {
			case "comment":
				flag = "comment";
				break;
			case "like":
				flag = "like";
				break;
			case "friendship":
				flag = "friendship";
				break;
			default:
				System.out.println("Erro");
				break;
			}
			
			process(e);
		
		}
	}
	
	public static void process(Event e) {
		switch (e.fileType) {
		case "comment" :
			processComment(e);
			break;
		case "like" :
			processLike(e);
			break;
		case "friendship" :
			processFriendship(e);
			break;
		default:
			System.out.println("fileType not recognized");
			break;
		}
	}
	
	private static void processComment(Event e) {
		String[] dataHolder = e.data.split(Pattern.quote("|"));
		MyApp.comments.put(dataHolder[1],new Comment(dataHolder[0],dataHolder[1],dataHolder[3]));
	}
	
	private static void processLike(Event e) {
		String[] dataHolder = e.data.split(Pattern.quote("|"));
		
		String timeStamp = dataHolder[0];
		String userID = dataHolder[1];
		String commentID = dataHolder[2];
		
		User user = putAndGet(userID);
		Comment comment = MyApp.comments.get(commentID);
		
		if (!comment.updateTimeStamp(timeStamp))
			return;
		
		for(User u : user.getFriends()) {
			if(comment.getCommunities().containsKey(u)) {
				Community comm = comment.getCommunities().get(u);
				comm.addUser(user);
				comment.sizeHasChanged(comm);
				return;
			}
		}
		Community c = new Community(user);
		comment.getCommunities().put(user, c);
		comment.sizeHasChanged(c);
	}
	
	private static void processFriendship(Event e) {
		String[] dataHolder = e.data.split(Pattern.quote("|"));
		
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
	
	private static Comparator<Event> comparatorTimeStamp = new Comparator<Event>() {
		@Override
        public int compare(Event a, Event b) {
			if(a.timeStamp.compareTo(b.timeStamp) < 0) {
				return 1;
			} else if(a.timeStamp.compareTo(b.timeStamp) > 0) {
				return -1;
			} else {
				if(a.fileType == FileType.Comment.toString())
					return 1;
				return -1;
			}
        }
	};
}

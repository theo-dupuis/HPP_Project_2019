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

public class Processor {

	public static void launch(String... str) {
		Reader commentReader = new Reader(FileType.Comment.toString(),str[0]);
		Reader likeReader = new Reader(FileType.Like.toString(),str[1]);
		Reader friendshipReader = new Reader(FileType.Friendship.toString(),str[2]);

		String comment = commentReader.processLine();
		String like = likeReader.processLine();
		String friendship = friendshipReader.processLine();

		List<String> list = new ArrayList<>();
		list.add(comment);
		list.add(like);
		list.add(friendship);
		while(comment != null || like != null || friendship != null) {
			Collections.sort(list, comparatorTimeStamp);

			String first = list.remove(0);
			if(first.equals(comment)) {
				processComment(first);
				comment = commentReader.processLine();
				list.add(comment);
			}
			else if(first.equals(like)) {
				processLike(first);
				like = likeReader.processLine();
				list.add(like);
			}
			else {
				processFriendship(first);
				friendship = friendshipReader.processLine();
				list.add(friendship);
			}
		}
		
		commentReader.releaseReader();
		likeReader.releaseReader();
		friendshipReader.releaseReader();
	}

	private static Comparator<String> comparatorTimeStamp = new Comparator<String>() {
		@Override
		public int compare(String a, String b) {
			if(a == null)
				return 1;
			if(b == null)
				return -1;

			return a.split(Pattern.quote("|"))[0].compareTo(b.split(Pattern.quote("|"))[0]);
		}
	};
	
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
}

package Util;

import p2019.Comment;
import p2019.Friendship;
import p2019.Like;

public class Processor {
	
	public static void processComment(String data) {
		String[] dataHolder = data.split("|");
		new Comment(dataHolder[0],dataHolder[1],dataHolder[3]);
	}
	
	public static void processFriendship(String data) {
		String[] dataHolder = data.split("|");
		new Friendship(dataHolder[0],dataHolder[1],dataHolder[2]);
	}
	
	public static void processLike(String data) {
		String[] dataHolder = data.split("|");
		new Like(dataHolder[0],dataHolder[1],dataHolder[2]);
	}
}

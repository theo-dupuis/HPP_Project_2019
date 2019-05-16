package util;

import p2019.MyApp;

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
		Event comment = new Event("comment", data);
		MyApp.commentsEvents.add(comment);
		
	}
	
	private static void processLike(String data) {
		Event like = new Event("like", data);
		MyApp.likeEvents.add(like);
		
	}
	
	private static void processFriendship(String data) {
		Event friendship = new Event("friendship", data);
		MyApp.friendshipEvents.add(friendship);
		
	}
}

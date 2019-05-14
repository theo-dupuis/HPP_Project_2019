package Util;

import p2019.Comment;
import p2019.Friendship;
import p2019.Like;

public class Processor {
	
	private static void processComment(String data) {
		String[] dataHolder = data.split("|");
		new Comment(dataHolder[0],dataHolder[1],dataHolder[3]);
	}
	
	private static void processFriendship(String data) {
		String[] dataHolder = data.split("|");
		new Friendship(dataHolder[0],dataHolder[1],dataHolder[2]);
	}
	
	private static void processLike(String data) {
		String[] dataHolder = data.split("|");
		new Like(dataHolder[0],dataHolder[1],dataHolder[2]);
	}

	public static void process(String fileType, String data) {
		switch (fileType) {
		case "comment" :
			processComment(data);
			break;
		case "friendship" :
			processFriendship(data);
			break;
		case "like" :
			processLike(data);
			break;

		default:
			System.out.println("fileType not recognized");
			break;
		}
	}
}

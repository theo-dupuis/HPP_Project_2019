package p2019;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import util.Processor;
import util.Rank;

public class MyApp {
	
	public static Map<String, Comment> comments = new HashMap<>();
	public static Map<String, User> users = new HashMap<>();
	public static Rank rank = new Rank();
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
	public static int duration;
	public static int k;
	
	public static void main(String[] args) {
		duration = Integer.parseInt(args[0]);
		k = Integer.parseInt(args[1]);
		
		String fileNameComment = args[2];
		String fileNameLike = args[3];
		String fileNameFriendship = args[4];
		
		Processor.launch(fileNameComment,fileNameLike,fileNameFriendship);		
	}
}

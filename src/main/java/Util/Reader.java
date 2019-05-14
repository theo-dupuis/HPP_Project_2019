package Util;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Reader {

	public static void processFile(String fileName) {
		String data = "";
		try {
			data = readFileAsString(fileName);
		} catch (Exception e) {
			System.out.println("Error while reading");
		}

		if (fileName.contains(FileType.Comment.toString()))
			Processor.processComment(data);
		else if(fileName.contains(FileType.Friendship.toString()))
			Processor.processFriendship(data);
		else if(fileName.contains(FileType.Like.toString()))
			Processor.processLike(data);

	}

	private static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}
}

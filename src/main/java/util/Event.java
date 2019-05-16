package util;

import java.util.regex.Pattern;

public class Event {
	public final String fileType;
	public final String timeStamp;
	public final String data;
	
	public Event(String fileType, String data) {
		this.fileType = fileType;
		this.timeStamp = data.split(Pattern.quote("|"))[0];
		this.data = data;
	}
	
}

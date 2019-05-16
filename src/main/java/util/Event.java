package util;

public class Event {
	private final String timeStamp;
	private final String[] data;
	
	public Event(String[] data) {
		this.timeStamp = data[0];
		this.data = data;
	}
}

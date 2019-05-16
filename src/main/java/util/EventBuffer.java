package util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EventBuffer {
	private List<Event> queue = new ArrayList<>();
	
	public EventBuffer() {
		
	}
	
	public void add(Event e) {
		queue.add(e);
	}
	
	public void insert(Event e) {
		queue.add(0,e);
	}
	
	public Event take() {
		if(!queue.isEmpty())
			return queue.remove(0);
		return null;
	}
}

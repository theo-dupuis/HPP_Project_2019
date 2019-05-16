package util;

import java.util.concurrent.BlockingQueue;

public class EventBuffer {
	private BlockingQueue<Event> queue;
	
	public EventBuffer() {
		
	}
	
	public void add(Event e) {
		try {
			queue.put(e);
		} catch (InterruptedException e1) {
			System.out.println("Error while adding to queue");
		}
	}
	
	public Event take() {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			System.out.println(e);
			return null;
		}
	}
}

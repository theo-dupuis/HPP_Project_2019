package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import util.Reader;

public class CacheProcessorThread implements Runnable {

	String comment;
	String like;
	String friendship;
	
	Reader commentReader;
	Reader likeReader;
	Reader friendshipReader;

	BlockingQueue<String> processQueue;
	final Object lock = new Object();

	public CacheProcessorThread(BlockingQueue<String> process) {
		comment = commentReader.processLine();
		like = likeReader.processLine();
		friendship = friendshipReader.processLine();

		processQueue = process;
	}
	
	public CacheProcessorThread(BlockingQueue<String> process, String...strings) {
		
		commentReader = new Reader(strings[0]);
		likeReader = new Reader(strings[1]);
		friendshipReader = new Reader(strings[2]);
		
		comment = commentReader.processLine();
		like = likeReader.processLine();
		friendship = friendshipReader.processLine();

		processQueue = process;
	}

	@Override
	public void run() {
		List<String> list = new ArrayList<>();
		String first = null;
		list.add(comment);
		list.add(like);
		list.add(friendship);

		while(comment != null || like != null || friendship != null) {
			Collections.sort(list, comparatorTimeStamp);

			first = list.remove(0);

			if(first.equals(comment)) {
				first = "C|"+first;
				comment = commentReader.processLine();
				list.add(comment);
			}
			else if(first.equals(like)) {
				first = "L|"+first;
				like = likeReader.processLine();
				list.add(like);
			}
			else {
				first = "F|"+first;
				friendship = friendshipReader.processLine();
				list.add(friendship);
			}

			try {
				processQueue.put(first);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		try {
			processQueue.put("END");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Comparator<String> comparatorTimeStamp = new Comparator<String>() {
		@Override
		public int compare(String a, String b) {
			if(a == null)
				return 1;
			if(b == null)
				return -1;

			return a.split(Pattern.quote("|"))[0].compareTo(b.split(Pattern.quote("|"))[0]);
		}
	};

}

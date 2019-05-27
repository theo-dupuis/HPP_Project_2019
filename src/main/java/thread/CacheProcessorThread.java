package thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import util.Cache;
import util.Reader;

public class CacheProcessorThread implements Runnable {

	String comment = "";
	String like = "";
	String friendship = "";

	Reader commentReader;
	Reader likeReader;
	Reader friendshipReader;

	BlockingQueue<String> processQueue;
	Cache cache;

	public CacheProcessorThread(Cache c, BlockingQueue<String> process) {

		cache = c;
		processQueue = process;
	}

	@Override
	public void run() {
		List<String> list = new ArrayList<>();
		String first = null;
		String type = "";
		
		comment = cache.comment;
		like = cache.like;
		friendship = cache.friendship;
		
		while(comment != null || like != null || friendship != null) {
			if(!cache.signal) {
				switch (type) {
				case "C|":
					comment = cache.comment;
					list.add(comment);
					break;
				case "L|":
					like = cache.like;
					list.add(like);
					break;
				case  "F|":
					friendship = cache.friendship;
					list.add(friendship);
					break;

				default:
					list.add(comment);
					list.add(like);
					list.add(friendship);
					break;
				}
				
				Collections.sort(list, comparatorTimeStamp);
				first = list.remove(0); //Might trigger a NPE ?
				if(first == null)
					break;
				
				if(first.equals(comment)) {
					type = "C|";
					cache.comment = null;
				} else if(first.equals(like)) {
					type = "L|";
					cache.like = null;
				} else if(first.equals(friendship)){
					type = "F|";
					cache.friendship = null;
				}

				try {
					processQueue.put(type + first);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(cache.readerDone == false)
					cache.signal = true;
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

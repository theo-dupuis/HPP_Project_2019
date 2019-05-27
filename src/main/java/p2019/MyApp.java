package p2019;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import thread.CacheProcessorThread;
import thread.ProcessorThread;
import thread.ReaderThread;
import util.Cache;
import util.Rank;

public class MyApp {
	
	public static Map<String, Comment> comments = new HashMap<>();
	public static Map<String, User> users = new HashMap<>();
	public static Rank rank = new Rank();
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0000");
	public static int duration;
	public static int k;
	
	public static void main(String[] args) {
		
		// Variables initialization :
		duration = Integer.parseInt(args[0]);
		k = Integer.parseInt(args[1]);
		String fileNameComment = args[2];
		String fileNameLike = args[3];
		String fileNameFriendship = args[4];
		
		// Cache initialization :
		Cache cache = new Cache();
		
		// Queue initialization :
		BlockingQueue<String> processQueue = new LinkedBlockingDeque<>();
		
		// READER THREAD (fill up the (string) caches)
		Thread reader = new Thread(new ReaderThread(cache,fileNameComment,fileNameLike,fileNameFriendship), "READER THREAD");
		
		// PROCESS READER THREAD (sort caches by time stamp and fill up the queue)
		Thread cacheProcessor = new Thread(new CacheProcessorThread(cache, processQueue), "PROCESS READER THREAD");
		
		// PROCESS QUEUE THREAD (process items in queue)
		Thread processor = new Thread(new ProcessorThread(processQueue), "PROCESS THREAD");
		
		
		// RANK AND OUTPUT THREAD
		// Rank & Output are handled by the main thread
		
		reader.start();
		cacheProcessor.start();
		processor.start();
		
		try {
			reader.join();
			cacheProcessor.join();
			processor.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

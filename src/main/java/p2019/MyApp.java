package p2019;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
	public static String FILL = "FILLIT";
	public static int duration;
	public static int k;
	public static ArrayList<Thread> threads = new ArrayList<>(4);
	
	public static void main(String[] args) {
		
		// Variables initialization :
		duration = Integer.parseInt(args[0]);
		k = Integer.parseInt(args[1]);
		String fileNameComment = args[2];
		String fileNameLike = args[3];
		String fileNameFriendship = args[4];
		
		// Caches initialization :
		Cache cache = new Cache();
		
		//Queue initialization :
		BlockingQueue<String> processQueue = new LinkedBlockingDeque<>();
		
		// READER THREAD (fill up the (string) caches)
		threads.add(new Thread(new ReaderThread(cache,fileNameComment,fileNameLike,fileNameFriendship), "READER THREAD"));
		threads.get(0).start();
		
		// PROCESS READER THREAD (sort caches by time stamp and fill up the queue)
		threads.add(new Thread(new CacheProcessorThread(cache, processQueue), "PROCESS READER THREAD"));
		
		threads.get(1).start();
		//new Thread(new CacheProcessorThread(processQueue,fileNameComment,fileNameLike,fileNameFriendship), "PROCESS READER THREAD").start();
		
		// PROCESS QUEUE THREAD (process items in queue)
		threads.add(new Thread(new ProcessorThread(processQueue), "PROCESS THREAD"));
		threads.get(2).start();
		
		// RANK AND OUTPUT THREAD
		// Rank & Outuput are handled by the main thread ?
		for(Thread t: threads)
		{
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

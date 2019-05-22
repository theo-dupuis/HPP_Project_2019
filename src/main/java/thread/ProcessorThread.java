package thread;

import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import util.Processor;

public class ProcessorThread implements Runnable {
	
	BlockingQueue<String> queue;
	
	public ProcessorThread(BlockingQueue<String> processQueue) {
		queue = processQueue;
	}
	
	@Override
	public void run() {
		String data = null;
		while(true) {
			try {
				data = queue.take();
				if(data.equals("END"))
					break;
				processData(data);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void processData(String data) {
		String[] split = data.split(Pattern.quote("|"), 2);
		String identifier = split[0];
		System.out.println(split[1]);
		
		switch (identifier) {
		case "C":
			Processor.processComment(split[1]);
			break;
		case "L" :
			Processor.processLike(split[1]);
			break;
		case "F" : 
			Processor.processFriendship(split[1]);
			break;
		default:
			break;
		}
	}

}

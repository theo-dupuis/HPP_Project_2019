package thread;


import util.Cache;
import util.Reader;

public class ReaderThread implements Runnable {
	
	String tempComment;
	String tempLike;
	String tempFriendship;

	Reader commentReader;
	Reader likeReader;
	Reader friendshipReader;
	
	Cache cache;

	public ReaderThread(Cache c, String...strings) {

		cache = c;
		commentReader = new Reader(strings[0]);
		likeReader = new Reader(strings[1]);
		friendshipReader = new Reader(strings[2]);
		
		cache.comment = commentReader.processLine();
		cache.like = likeReader.processLine();
		cache.friendship = friendshipReader.processLine();
	}

	@Override
	public void run() {
		tempComment = commentReader.processLine();
		tempLike = likeReader.processLine();
		tempFriendship = friendshipReader.processLine();
		
		while(tempComment != null || tempLike != null || tempFriendship != null) {
			if(cache.signal) {
				cache.signal = false;
				
				if(tempComment != null && cache.comment == null) {
					cache.comment = tempComment;
					tempComment = commentReader.processLine();
				} else if (tempLike != null && cache.like == null) {
					cache.like = tempLike;
					tempLike = likeReader.processLine();
				} else if (tempFriendship != null && cache.friendship == null){
					cache.friendship = tempFriendship;
					tempFriendship = friendshipReader.processLine();
				}
			}
		}

		commentReader.releaseReader();
		likeReader.releaseReader();
		friendshipReader.releaseReader();
	}

}

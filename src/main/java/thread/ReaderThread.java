package thread;


import util.Reader;

public class ReaderThread implements Runnable {

	String comment;
	String like;
	String friendship;

	Reader commentReader;
	Reader likeReader;
	Reader friendshipReader;

	public ReaderThread(String...strings) {
		comment = strings[0];
		like = strings[1];
		friendship = strings[2];

		commentReader = new Reader(strings[3]);
		likeReader = new Reader(strings[4]);
		friendshipReader = new Reader(strings[5]);

	}

	@Override
	public void run() {
		// Ensure caches not null
		comment = commentReader.processLine();
		like = likeReader.processLine();
		friendship = friendshipReader.processLine();

		try {
			wait();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch blo
			e1.printStackTrace();
		}

		while(comment != null || like != null || friendship != null) {
			if(comment != null)
				comment = commentReader.processLine();
			if(like != null)
				like = likeReader.processLine();
			if(friendship != null)
				friendship = friendshipReader.processLine();

			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		commentReader.releaseReader();
		likeReader.releaseReader();
		friendshipReader.releaseReader();
	}

}

package util;

public class Cache {
	public String comment = null;
	public String like = null;
	public String friendship = null;
	
	public volatile boolean signal = false; // TRUE : Reader is doing ops over Cache; FALSE : CacheProcessor
	public boolean readerDone = false;
	
	public Cache() {
		
	}
	
	
}

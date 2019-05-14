package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
	private HashMap<String, User> friendship;
	
	public Graph() {
		friendship = new HashMap<>();
	}
	
	public void addUser(String id) {
		friendship.putIfAbsent(id, new User(id));
	}
	
	public void addFriendship(String ts, String id1, String id2) {
		User a = new User(id1);
		User b = new User(id2);
		
		addUser(id1);
		addUser(id2);
		a.addFriend(b, ts);
		b.addFriend(a, ts);
	}


	
}
